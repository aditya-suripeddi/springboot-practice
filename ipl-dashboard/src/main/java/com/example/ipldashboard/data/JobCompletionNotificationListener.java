package com.example.ipldashboard.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import com.example.ipldashboard.model.Team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final EntityManager em; // EntityManager is JPA way of interacting with database 

  @Autowired
  public JobCompletionNotificationListener(EntityManager entityManager) {
    this.em = entityManager;
  }

  @Override
  @Transactional // javax.persistence.TransactionRequiredException: No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call
  public void afterJob(JobExecution jobExecution) {
 
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {

           log.info("!!! JOB FINISHED! Time to verify the results");
           Map<String, Team> teamData = new HashMap<>();

           // JPA QUERY LANGUAGE - JPQL kinda like SQL but allows 
           // you to use the model class 

          em.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
            .getResultList()
            .stream()
            .map( e  ->  new Team( (String) e[0], (long) e[1]))
            .forEach(team -> teamData.put(team.getTeamName(), team) );

          em.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
            .getResultList()
            .stream()
            .forEach( e -> {
               //  one exception is if a team always played in second position, 
               //  then we may have to add it to hashMap freshly, but we ignore that here
                 Team team  = teamData.get( (String) e[0] ) ;                
                 team.setTotalMatches(team.getTotalMatches() + (long) e[1] );
            });


          em.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
            .getResultList()
            .stream()
            .forEach( e -> {
                Team team = teamData.get( (String) e[0]) ;
                 if(team != null) team.setTotalWins( (long) e[1]); // did not get useful error message here as in video!
            });

            // persist teamData 
            teamData.values().forEach(team -> em.persist(team) );
       
            teamData.values().forEach(team -> System.out.println(team));
    }
  }



}