package io.qdivision.qtp.repository;

import io.qdivision.qtp.model.Game;
import io.qdivision.qtp.model.Player;

import java.util.*;

public class GameRepository {

    Map<String, Game> gameMap = new HashMap<>();

    public List<Game> getGames(){
        return new ArrayList<>(gameMap.values());
    }

    public Game saveGame(Game newGame){
        var id = UUID.randomUUID().toString();
        newGame.setId(id);
        gameMap.put(id, newGame);

        return newGame;
    }

    public void addPlayer(Player player, String id){
        Game game = gameMap.get(id);
        player.setId(game.getPlayerList().size());
        game.addPlayer(player);
    }
}
