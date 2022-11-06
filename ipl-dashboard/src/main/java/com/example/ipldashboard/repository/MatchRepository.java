package com.example.ipldashboard.repository;


import java.time.LocalDate;
import java.util.List;

import com.example.ipldashboard.model.Match;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface MatchRepository extends CrudRepository<Match, Long> {

      // basically writing SQL queries with method names
       List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

       @Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) and m.date between :dateStart and :dateEnd order by date desc")
       List<Match> getMatchesByTeamBetweenDates(
                @Param("teamName")  String teamName,
                @Param("dateStart") LocalDate startDate,
                @Param("dateEnd")   LocalDate endDate);

       default List<Match> findLatestMatchesByTeam(String teamName, int count) {
             return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count)) ;
       }

}
