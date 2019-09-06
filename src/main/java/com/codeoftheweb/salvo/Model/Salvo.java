package com.codeoftheweb.salvo.Model;

import com.codeoftheweb.salvo.Model.GamePlayer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Salvo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;

    private int turn;

    @ElementCollection
    @Column(name = "locations")
    private List<String> locations = new ArrayList<>();

    //Constructor
    public Salvo(){}
    public Salvo(GamePlayer gamePlayer, int turn, List<String> locations) {
        this.gamePlayer = gamePlayer;
        this.turn = turn;
        this.locations = locations;
    }

    //Getters
    public long getId() {
        return id;
    }
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
    public int getTurn() {
        return turn;
    }
    public List<String> getLocations() {
        return locations;
    }

}