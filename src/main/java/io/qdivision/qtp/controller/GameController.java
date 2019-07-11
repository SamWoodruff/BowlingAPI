package io.qdivision.qtp.controller;

import io.qdivision.qtp.model.Game;
import io.qdivision.qtp.model.NewPlayerRequest;
import io.qdivision.qtp.model.Player;
import io.qdivision.qtp.repository.GameNotFoundException;
import io.qdivision.qtp.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class GameController {
    GameRepository gameRepository = new GameRepository();

    @GetMapping("/games")
    public List<Game> getGame(@RequestParam(value="id", defaultValue = "all") String id){
        List<Game> games =
                id.equals("all") ?
                        gameRepository.getGames() :
                        gameRepository.getGames().stream()
                                .filter(temp -> temp.getId().equals(id))
                                .collect(Collectors.toList());
        if(games.size() == 0){
            throw new GameNotFoundException();
        }else{
            log.info("Game:{} requested", id);
            return games;
        }
    }


    @PostMapping("games/addPlayer/{gameId}")
    public void savePlayer(@RequestBody NewPlayerRequest newPlayer, @PathVariable String gameId){
        Player player = new Player(newPlayer.getName());
        gameRepository.addPlayer(player, gameId);
    }

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public String newGame(@RequestBody Game newGame){
        newGame = gameRepository.saveGame(newGame);
        log.info("Game:{} created", newGame);
        return newGame.getId();
    }


    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String SongNotSavedException(IllegalArgumentException ex) {
        log.warn("Game not created",ex);
        return "Game not created";
    }

    @ResponseBody
    @ExceptionHandler(GameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String GameNotFoundException(GameNotFoundException ex) {
        log.warn("Game not found",ex);
        return "Game not found";
    }



}
