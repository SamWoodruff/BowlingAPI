package io.qdivision.qtp.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {
    private String id;
    private List<Player> playerList = new ArrayList<>();

    public void addPlayer(Player player){
        playerList.add(player);
    }
}
