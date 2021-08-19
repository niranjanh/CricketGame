package Teams;

import Players.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {
    public String teamName;
    public List<Player> players;
    public Player onStrikePlayer;

    public Team(String teamName, int n) {
        this.teamName = teamName;
        this.players = new ArrayList<>();

    }

    public void addPlayer(String playerName) {
        this.players.add(new Player(playerName));
    }

    public Player getPlayer(int battingPosition) {
        return this.players.get(battingPosition);
    }

    public void getTeamScoreCard() {
        System.out.println("ScoreCard for " + this.teamName);
        System.out.println("Name\tScore\t6s\t\t4s\t\tBalls");
        for (Player p: this.players) {
            System.out.print(p.name + (!p.out && p.batted ? "*" : "") + (p.onStrike && !p.out ? "(s)" : "") + "\t\t");
            System.out.print(p.score + "\t\t");
            System.out.print(p.noOfSixes + "\t\t");
            System.out.print(p.noOfFours + "\t\t");
            System.out.println(p.ballsFaced + "\t\t");
        }
        System.out.println();
        System.out.println();
    }
}
