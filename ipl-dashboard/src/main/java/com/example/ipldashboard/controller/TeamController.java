package com.example.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import com.example.ipldashboard.model.Match;
import com.example.ipldashboard.model.Team;
import com.example.ipldashboard.repository.MatchRepository;
import com.example.ipldashboard.repository.TeamRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin
public class TeamController {
    
    private TeamRepository teamRepository; 
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }


    @GetMapping("/teams")
    public Iterable<Team> getAllTeams() {
        return this.teamRepository.findAll();
    } 


    @GetMapping("/teams/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
       Team team =  teamRepository.findByTeamName(teamName);
       team.setMatches( matchRepository.findLatestMatchesByTeam(teamName, 4) );
       return team;
    }


    @GetMapping("/teams/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable("teamName") String teamName, @RequestParam int year) {
       LocalDate startDate = LocalDate.of(year, 1, 1) ;
       LocalDate endDate = LocalDate.of(year+1, 1, 1) ;

       return this.matchRepository.getMatchesByTeamBetweenDates(
           teamName,
           startDate,
           endDate
       );
    }
}
