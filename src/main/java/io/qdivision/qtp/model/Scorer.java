package io.qdivision.qtp.model;

import java.util.List;

class Scorer {
    public static Integer calculateScore(List<Frame> frames){
        System.out.println("scoring");
        for (Frame frame: frames){
            if (isComplete(frame)){
                if(isStrike(frame) || isSpare(frame)){
                    if (frame.getId() != 9 && isComplete(frames.get(frame.getId() + 1))){
                        if(isStrike(frame)){
                            System.out.println("STRIKE");
                            if (frames.get(frame.getId() + 1).getRolls().get(0) != 10) {
                                Integer nextFrameTotal = frames.get(frame.getId() + 1).getRolls().get(0) + frames.get(frame.getId() + 1).getRolls().get(1);
                                frame.setFrameScore(10 + nextFrameTotal);
                            } else {
                                if (frame.getId() != 8 && isComplete(frames.get(frame.getId() + 2))){
                                    Integer nextFrameTotal = frames.get(frame.getId() + 1).getRolls().get(0) + frames.get(frame.getId() + 2).getRolls().get(0);
                                    frame.setFrameScore(10 + nextFrameTotal);
                                } else {
                                    System.out.println("8 BABY");
                                    Integer nextFrameTotal = frames.get(frame.getId() + 1).getRolls().get(0) + frames.get(frame.getId() + 1).getRolls().get(1);
                                    frame.setFrameScore(10 + nextFrameTotal);
                                }
                            }
                        } else if (isSpare(frame)){
                            System.out.println("SPARE");
                            Integer nextFrameTotal = frames.get(frame.getId() + 1).getRolls().get(0);
                            frame.setFrameScore(10 + nextFrameTotal);
                        }
                    } else if (frame.getId() == 9){
                        Integer nextFrameTotal = frame.getRolls().get(0) +  frame.getRolls().get(1) + frame.getRolls().get(2);
                        frame.setFrameScore(nextFrameTotal);
                    } else {
                        return null;
                    }
                } else {
                    frame.setFrameScore(frame.getRolls().get(0) + frame.getRolls().get(1));
                }
            } else {
                return null;
            }
            System.out.println(frame.getId() + ":" + frame.getFrameScore());
        }
        return 0;
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
}
