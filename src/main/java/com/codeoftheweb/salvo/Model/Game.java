package com.codeoftheweb.salvo.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private Set<GamePlayer> gamePlayers;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private Set<Score> scores;

    private Date creationDate;

    //Constructor
    public Game(){}
    public Game(Date creationDate){
        this.creationDate = creationDate;
    }

    //Getters
    public long getId(){
        return id;
    }
    public Date getCreationDate(){
        return creationDate;
    }
    public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }
    public Set<Score> getScores() { return scores; }
}
