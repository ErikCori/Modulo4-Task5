package com.codeoftheweb.salvo.Controller;


import com.codeoftheweb.salvo.Model.*;
import com.codeoftheweb.salvo.Repository.GamePlayerRepository;
import com.codeoftheweb.salvo.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @RequestMapping("/games")
    public List<Object> getAllGames() {
        return gameRepository.findAll().stream().map(game -> makeGameDto(game)).collect(Collectors.toList());
    }
    private Map<String, Object> makeGameDto(Game game){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", game.getId());
        dto.put("created", game.getCreationDate());
        dto.put("gamePlayers", game.getGamePlayers().stream().map(g -> makeGamePlayerDto(g)));
        return dto;
    }
    private Map<String, Object> makeGamePlayerDto(GamePlayer gamePlayer){
        Map<String, Object> dto= new LinkedHashMap<>();
        dto.put("id", gamePlayer.getId());
        dto.put("player", makePlayerDto(gamePlayer.getPlayer()));
        return dto;
    }
    private Map<String, Object> makePlayerDto(Player player){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", player.getId());
        dto.put("email", player.getUsername());
        return dto;
    }

    @RequestMapping("/game_view/{gamePlayerId}")
    public Map<String, Object>gameView(@PathVariable long gamePlayerId){
        return gameViewDto(gamePlayerRepository.getOne(gamePlayerId));
    }
    private Map<String, Object> gameViewDto(GamePlayer gamePlayer){

        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", gamePlayer.getGame().getId());
        dto.put("created", gamePlayer.getGame().getCreationDate());
        dto.put("gamePlayers", gamePlayer.getGame().getGamePlayers().stream().map(g ->makeGamePlayerDto(g)));
        dto.put("ships", getShipList(gamePlayer.getShips()));
        dto.put("salvoes", gamePlayer.getGame().getGamePlayers().stream()
                                                                .flatMap(gp->gp.getSalvoes()
                                                                                .stream()
                                                                                .map(salvo -> makeSalvoDto(salvo))
                                                                        )
                                                                .collect(Collectors.toList())
        );
        return dto;
    }
    private Map<String, Object> makeShipDto(Ship ship){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("type", ship.getType() );
        dto.put("locations", ship.getLocations());
        return dto;
    }

    private List<Map<String, Object>> getShipList(Set<Ship> ships){
        return ships.stream().map(ship-> makeShipDto(ship)).collect(Collectors.toList());
    }

    private Map<String, Object> makeSalvoDto(Salvo salvo){
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("turn", salvo.getTurn());
        dto.put("player", salvo.getGamePlayer().getPlayer().getId());
        dto.put("salvoLocations", salvo.getLocations());
        return dto;
    }

}
