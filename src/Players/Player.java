package Players;

public class Player {
    public String name;
    public int score;
    public int ballsFaced;
    public int noOfSixes;
    public int noOfFours;
    public boolean out;
    public boolean batted;
    public boolean onStrike;
    public int battingPosition;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.ballsFaced = 0;
        this.noOfSixes = 0;
        this.noOfFours = 0;
        this.out = false;
        this.batted = false;
        this.onStrike = false;
    }

    public void entered() {
        this.batted = true;
    }

    public void gotStrike(boolean onStrike) {
        this.onStrike = onStrike;
    }

    public void setBattingPosition(int pos) {
        this.battingPosition = pos;
    }

    public void gotOut() {
        this.out = true;
        this.onStrike = false;
        this.ballFaced();
    }

    public void madeRun(int n) {
        this.score += n;
        this.ballFaced();
        if (n == 6) {
            this.hitSix();
        }

        if (n == 4) {
            this.hitFour();
        }
    }

    private void ballFaced() {
        ++this.ballsFaced;
    }

    private void hitSix() {
        ++this.noOfSixes;
    }

    private void hitFour() {
        ++this.noOfFours;
    }

    public void changeStrike() {
        this.onStrike = !this.onStrike;
    }


}
