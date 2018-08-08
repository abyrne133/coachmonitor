package models;

import java.util.HashMap;

public class SliderAnswer extends Answer {
    public Integer theMin;
    public Integer theMax;
    public Integer current;
    public HashMap comments;

    public SliderAnswer(Integer theMin, Integer theMax, Integer current, HashMap comments){
        this.theMin = theMin;
        this.theMax = theMax;
        this.current = current;
        this.comments = comments;
    }
}
