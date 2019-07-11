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

    public void addRollToFrame(String gameId, Integer playerId, Integer frameId, Integer rollId, Integer roll){
        Frame currentFrame = gameMap.get(gameId).getPlayerList().get(playerId).getFrameList().get(frameId);
        currentFrame.addRollToFrame(rollId, roll);
        Game previousGameState = gameMap.remove(gameId);
        for(Player player : previousGameState.getPlayerList()){
            player.tallyScore(player.getFrameList());
        }
    }

    public Game updateGame(String gameId, Game game){
        Game previousGameState = gameMap.remove(gameId);
        if(previousGameState == null){
            throw new GameNotFoundException();
        }else{
            for(Player player : previousGameState.getPlayerList()){
                player.tallyScore(player.getFrameList());
            }
            return gameMap.put(gameId,game);
        }
    }
}
