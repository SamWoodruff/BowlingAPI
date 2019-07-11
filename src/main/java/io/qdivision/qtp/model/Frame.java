package io.qdivision.qtp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Frame {
    List<Integer> rolls;
    private Integer id;
    private Integer frameScore;
    private Integer accumulativeScore;

    public Frame(int id){
        this.id = id;
        rolls = generateRolls();
    }

    public ArrayList<Integer> generateRolls(){
        int size = this.id != 9 ? 2 : 3;
        ArrayList<Integer> balls= new ArrayList<>();
        for (int i = 0; i < size; i++){
            balls.add(null);
        }

        return balls;
    }

    public Integer sumUpTo(int index){
        Integer sum = 0;
        for (int i = 0; i < index; i++){
            if (this.rolls.get(i) != null){
                sum += this.rolls.get(i);
            }
        }
        return sum;
    }

    public void addRollToFrame(Integer rollId, Integer value){
        this.rolls.set(rollId, value);
    }

}
