package com.codeoftheweb.salvo.Model;


import com.codeoftheweb.salvo.Model.GamePlayer;
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

    //Controller

    public Map<String, Object> makePlayerDto(){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", this.getId());
        dto.put("email", this.getUsername());
        return dto;
    }

    public int getWins(Set<Score> scores){
        return scores.filter(score -> score.getScore() == 1).count();
    }
}
