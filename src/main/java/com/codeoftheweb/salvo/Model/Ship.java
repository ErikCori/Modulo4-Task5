package com.codeoftheweb.salvo.Model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;

    private String type;

    @ElementCollection
    @Column(name = "locations")
    private List<String> locations = new ArrayList<>();

    //Constructor
    public Ship(){}
    public Ship(GamePlayer gamePlayer, String type, List locations) {
        this.gamePlayer = gamePlayer;
        this.type = type;
        this.locations = locations;
    }

    //Getters
    public long getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public List getLocations() {
        return locations;
    }
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
}
