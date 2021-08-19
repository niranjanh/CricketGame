package Overs;

import java.util.ArrayList;
import java.util.List;

public class Over {
    public int overNo;
    public int validBalls;
    public List<String> over;

    public Over(int overNo) {
        this.overNo = overNo;
        this.validBalls = 0;
        this.over = new ArrayList<>();
    }

    public void printOverNo() {
        System.out.println();
        System.out.println();
        System.out.println("Over " + this.overNo + ":");
    }

    public void ballBowled(String state) {
        this.over.add(state);
        if (this.isValidBall(state)) {
            ++this.validBalls;
        }
    }

    public boolean isValidBall(String state) {
        if (state.equals("Wd") || state.equals("Nb")) {
            return false;
        }
        return true;
    }

    public boolean overFinished() {
        if (this.validBalls == 6) {
            return true;
        }

        return false;
    }

    public List<String> getFullOverState() {
        return this.over;
    }
}
