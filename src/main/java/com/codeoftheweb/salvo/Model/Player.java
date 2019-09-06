package com.codeoftheweb.salvo.Model;


import com.codeoftheweb.salvo.Model.GamePlayer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
}
