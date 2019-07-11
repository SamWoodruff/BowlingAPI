package io.qdivision.qtp.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Player {
    private String name;
    private int id;
    private List<Frame> frameList;
    private Integer score;

public Player(String name){
    this.name = name;
    this.frameList = generateFrames();
    this.score = 0;
    this.score = tallyScore(this.frameList);
}

private List<Frame> generateFrames(){
    List<Frame> frames = new ArrayList<>();
    for (int i = 0; i < 10; i++){
        frames.add(new Frame(i));
    }
    return frames;
}

public Integer tallyScore(List<Frame> frames){
    Integer score = 0;
    score = Scorer.calculateScore(frames);
    return score;
}

}
