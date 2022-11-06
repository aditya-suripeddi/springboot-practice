package com.example.ipldashboard.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Tell how to generate Ids to avaoid: 
    // //javax.persistence.EntityExistsException: A different object with the same identifier value was already associated with the session : [com.example.ipldashboard.model.Team#0]
    private long id; 
    private String teamName ;
    private long totalMatches;
    private long totalWins;

    @Transient // tell jpa don't bother with this field, it does not manifest as db entry
    private List<Match> matches;

    public List<Match> getMatches() {
        return matches;
    }


    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }


    public Team() {} // No default constructor for entity: : com.example.ipldashboard.model.Team; nested exception is org.hibernate.InstantiationException: No default constructor for entity: : com.example.ipldashboard.model.Team

    
    public Team(String teamName, long totalMatches) {
        this.teamName = teamName;
        this.totalMatches = totalMatches;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public long getTotalMatches() {
        return totalMatches;
    }
    public void setTotalMatches(long totalMatches) {
        this.totalMatches = totalMatches;
    }
    public long getTotalWins() {
        return totalWins;
    }
    public void setTotalWins(long totalWins) {
        this.totalWins = totalWins;
    }

    @Override
    public String toString() {
        return "Team [teamName=" + teamName + ", totalMatches=" + totalMatches + ", totalWins=" + totalWins + "]";
    }

    

}
 