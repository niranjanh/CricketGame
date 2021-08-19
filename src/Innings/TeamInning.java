package Innings;

import Overs.Over;
import Players.Player;
import Teams.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamInning {
    public String teamName;
    public int noOfPlayes;
    public int noOfOvers;
    public Team team;
    public int oversBowled;
    public int score;
    public int runsInExtras;
    public int noOfWickets;
    public Extras exttas;
    public List<Player> onPitchPlayers;
    public Over currentOver;
    public int currentOverNo;
    public List<List<String>> inningsOvers;
    public int nextPlayerNoToBat = 0;
    public boolean isFirstInning = false;
    public int target = 0;

    public TeamInning(String teamName, int noOfPlayes, int noOfOvers, Team team) {
        this.teamName = teamName;
        this.isFirstInning = false;
        this.noOfOvers = noOfOvers;
        this.noOfPlayes = noOfPlayes;
        this.team = team;
        this.oversBowled = 0;
        this.score = 0;
        this.runsInExtras = 0;
        this.noOfWickets = 0;
        this.exttas = new Extras(teamName);
        this.onPitchPlayers = new ArrayList<>();
        this.currentOverNo = 0;
        this.inningsOvers = new ArrayList<>();
    }

    public void setFirstInning(boolean firstInning) {
        this.isFirstInning = firstInning;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public void startInning() {
        this.enterNewPlayer(true);
        this.enterNewPlayer(false);
    }

    public void enterNewPlayer(boolean onStrike) {
        Player player = this.team.getPlayer(this.nextPlayerNoToBat++);
        player.gotStrike(onStrike);
        player.entered();
        this.onPitchPlayers.add(player);
    }

    public boolean isTeamAllOut() {
        return this.nextPlayerNoToBat == this.noOfPlayes;
    }

    public void startNewOver() {
        ++this.currentOverNo;
        this.currentOver = new Over(this.currentOverNo);
        this.currentOver.printOverNo();
    }

    public boolean bowl(String state) {
        this.currentOver.ballBowled(state);
        if (state.equals("W")) {
            this.wicket();
            if (this.isTeamAllOut()) {
                return true;
            }
            this.enterNewPlayer(true);
        } else {
            this.scoreRun(state);
        }

        if (this.matchWon()) {
            return true;
        }

        if (this.currentOver.overFinished()) {
            this.endOver();
            return true;
        }

        return false;
    }

    public boolean matchWon() {
        if (this.isFirstInning) return false;

        if (this.score == this.target) {
            return  true;
        }

        return false;
    }

    public boolean isInningOver() {
        return this.currentOverNo == this.noOfOvers;
    }

    public void wicket() {
        ++this.noOfWickets;
        for (Player player: this.onPitchPlayers) {
            if (player.onStrike) {
                player.gotOut();
                this.onPitchPlayers.remove(player);
                break;
            }
        }
    }

    public void scoreRun(String state) {
        if (state.length() == 2) {
            ++this.score;
            this.exttas.addExtra(state);
        }

        if (state.length() == 1) {
            int run = Integer.parseInt(state);
            for (Player player: this.onPitchPlayers) {
                if (player.onStrike) {
                    this.score += run;
                    player.madeRun(run);
                }
            }

            if (run % 2 != 0) {
                this.changeStrike();
            }
        }
    }

    public void endOver() {
        this.inningsOvers.add(this.currentOver.getFullOverState());
        this.changeStrike();
    }

    public void printScoreCard() {
        this.team.getTeamScoreCard();
        this.printInningSummary();
    }

    public void printInningSummary() {
        System.out.println("Total: " + this.score + "/" + this.noOfWickets);
        System.out.println("Overs: " + this.oversBowled + "." + this.currentOver.validBalls);
    }

    private void changeStrike() {
        for (Player player: this.onPitchPlayers) {
            player.changeStrike();
        }
    }
}
