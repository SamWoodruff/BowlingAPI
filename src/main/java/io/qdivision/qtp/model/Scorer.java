package io.qdivision.qtp.model;

import java.util.List;

class Scorer {
    public static Integer calculateScore(List<Frame> frames){
        Integer score = 0;
        for (Frame frame : frames){
            if (isComplete(frame)){
                if(isStrike(frame) || isSpare(frame)){
                    if (frame.getId() != 9 && isComplete(frames.get(frame.getId() + 1))){
                        if(isStrike(frame)){
                            if (frames.get(frame.getId() + 1).getRolls().get(0) != 10) {
                                Integer nextFrameTotal = frames.get(frame.getId() + 1).getRolls().get(0) + frames.get(frame.getId() + 1).getRolls().get(1);
                                frame.setFrameScore(10 + nextFrameTotal);
                                setAccumulativeScore(frame, frames, score);
                            } else {
                                if (frame.getId() != 8 && isComplete(frames.get(frame.getId() + 2))){
                                    Integer nextFrameTotal = frames.get(frame.getId() + 1).getRolls().get(0) + frames.get(frame.getId() + 2).getRolls().get(0);
                                    frame.setFrameScore(10 + nextFrameTotal);
                                    setAccumulativeScore(frame, frames, score);
                                } else {
                                    Integer nextFrameTotal = frames.get(frame.getId() + 1).getRolls().get(0) + frames.get(frame.getId() + 1).getRolls().get(1);
                                    frame.setFrameScore(10 + nextFrameTotal);
                                    setAccumulativeScore(frame, frames, score);
                                }
                            }
                        } else if (isSpare(frame)){
                            Integer nextFrameTotal = frames.get(frame.getId() + 1).getRolls().get(0);
                            frame.setFrameScore(10 + nextFrameTotal);
                            setAccumulativeScore(frame, frames, score);
                        }
                    } else if (frame.getId() == 9){
                        if(isStrike(frame) || isSpare(frame)) {
                            Integer nextFrameTotal = frame.getRolls().get(0) + frame.getRolls().get(1) + frame.getRolls().get(2);
                            frame.setFrameScore(nextFrameTotal);
                            setAccumulativeScore(frame, frames, score);
                        } else {
                            Integer nextFrameTotal = frame.getRolls().get(0) + frame.getRolls().get(1);
                            frame.setFrameScore(nextFrameTotal);
                            setAccumulativeScore(frame, frames, score);
                        }
                    } else {
                        setAccumulativeScore(frame, frames, score);
                        return score;
                    }
                } else {
                    frame.setFrameScore(frame.getRolls().get(0) + frame.getRolls().get(1));
                    setAccumulativeScore(frame, frames, score);
                }
            } else {
                setAccumulativeScore(frame, frames, score);
                return score;
            }
            System.out.println(frame.getId() + ":" + frame.getFrameScore());
            if (frame.getFrameScore() != null){
                score += frame.getFrameScore();
            }
        }
        System.out.println(score);
        return score;
    }

    public static boolean isStrike(Frame frame){
        if(frame.getRolls().get(0) == 10){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isSpare(Frame frame){
        if(frame.getRolls().get(0) + frame.getRolls().get(1) == 10){
            return true;
        }else{
            return false;
        }
    }


    public static boolean isComplete(Frame frame){
        Boolean complete = true;
        for (Integer roll: frame.getRolls()){
            if (roll == null){
                complete = false;
            }
        }
        return complete;
    }

    public static void setAccumulativeScore(Frame frame, List<Frame> frames, Integer total){
        Integer sum = 0;
        for (Integer i = 0; i <= frame.getId(); i++){
            if (frames.get(i).getFrameScore() == null){
                frames.get(i).setAccumulativeScore(total);
                return;
            } else {
                sum += frames.get(i).getFrameScore();
                frames.get(i).setAccumulativeScore(sum);
            }
        }
    }
}
