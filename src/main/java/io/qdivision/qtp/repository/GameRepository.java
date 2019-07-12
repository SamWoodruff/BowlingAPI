package io.qdivision.qtp.repository;

import io.qdivision.qtp.model.Frame;
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

    public Game addPlayer(Player player, String id){
        Game game = gameMap.get(id);
        player.setId(game.getPlayerList().size());
        game.addPlayer(player);
        return game;
    }

    public Frame addRollToFrame(String gameId, Integer playerId, Integer frameId, Integer rollId, Integer roll){
        Game game = gameMap.get(gameId);
        validateGame(game);

        Frame currentFrame = gameMap.get(gameId).getPlayerList().get(playerId).getFrameList().get(frameId);
        currentFrame.addRollToFrame(rollId, roll);
        for(Player player : game.getPlayerList()){
            player.tallyScore(player.getFrameList());
        }
        return currentFrame;
    }

    public void validateGame(Game game){
        if(game == null){
            throw new GameNotFoundException();
        }
    }

    public Game updateGame(String gameId, Game game){
        Game previousGameState = gameMap.remove(gameId);
        validateGame(previousGameState);

        for(Player player : game.getPlayerList()){
            player.tallyScore(player.getFrameList());
        }
        gameMap.put(gameId,game);
        return game;
    }
}
