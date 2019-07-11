package io.qdivision.qtp.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Frame {
    List<Integer> rolls;
    private Integer id;
    private Integer frameScore;

    public Frame(int id){
        this.id = id;
        rolls = generateRolls();
    }

    public ArrayList<Integer> generateRolls(){
        int size = this.id != 9 ? 2 : 3;
        ArrayList<Integer> balls= new ArrayList<>();
//        for (int i = 0; i < size; i++){
//            balls.add(5);
//        }
        if (this.id != 9) {
            balls.add(5);
            balls.add(0);
        } else {
            balls.add(5);
            balls.add(5);
            balls.add(5);
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

    public void setRoll(int roll, Integer value){
        this.rolls.set(roll, value);
    }

}
