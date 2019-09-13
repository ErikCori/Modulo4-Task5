package com.codeoftheweb.salvo.Model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private Set<GamePlayer> gamePlayers;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private Set<Score> scores;

    private String username;

    //Constructor
    public Player(){}

    public Player(String username){

        this.username = username;
    }

    //Getters
    public long getId(){
        return id;
    }
    public String getUsername() {
        return username;
    }
    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }
    public Set<Score> getScores() { return scores; }
    //Controller

    public Map<String, Object> makePlayerDto(){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("email", this.getUsername());
        return dto;
    }

    public float getWins(){
        return getScores().stream().filter(score -> score.getScore() == 1).count();
    }
    public float getTied(){
        return getScores().stream().filter(score -> score.getScore() == (float) 0.5).count();
    }
    public float getLoses(){
        return getScores().stream().filter(score -> score.getScore()== 0).count();
    }
    public float totalScore(){
        return (float) (getWins() *1 + getTied() * 0.5);
    }
}

