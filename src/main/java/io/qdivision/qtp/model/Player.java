package io.qdivision.qtp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private String name;
    private int id;
    private List<Frame> frameList;
    private Integer score;

public Player(String name){
    this.name = name;
    this.frameList = generateFrames();
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
    this.score = Scorer.calculateScore(frames);
    System.out.println(score);
    return score;
}

public List<Frame> testFrame(){
    List<Frame> sample = generateFrames();

    Frame frame0 = new Frame(0);
    frame0.setRolls(Arrays.asList(10, 0));
    sample.set(0, frame0);

    Frame frame1 = new Frame(1);
    frame1.setRolls(Arrays.asList(4, 4));
    sample.set(1, frame1);

    Frame frame2 = new Frame(2);
    frame2.setRolls(Arrays.asList(4, 4));
    sample.set(2, frame2);

    Frame frame3 = new Frame(3);
    frame3.setRolls(Arrays.asList(8, 0));
    sample.set(3, frame3);

    Frame frame4 = new Frame(4);
    frame4.setRolls(Arrays.asList(4, 4));
    sample.set(4, frame4);

    Frame frame5 = new Frame(5);
    frame5.setRolls(Arrays.asList(4, 4));
    sample.set(5, frame5);

    Frame frame6 = new Frame(6);
    frame6.setRolls(Arrays.asList(0, 10));
    sample.set(6, frame6);

    Frame frame7 = new Frame(7);
    frame7.setRolls(Arrays.asList(4, 4));
    sample.set(7, frame7);

    Frame frame8 = new Frame(8);
    frame8.setRolls(Arrays.asList(4, 4));
    sample.set(8, frame8);

    Frame frame9 = new Frame(9);
    frame9.setRolls(Arrays.asList(9, null, 0));
    sample.set(9, frame9);
    return sample;
}

}
