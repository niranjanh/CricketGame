/*
*
*
*
* */


import Innings.TeamInning;
import Teams.Team;

import java.util.Scanner;

class CricketGame {
    public static void main(String args[]) {
        System.out.println("Hello World");

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter no of players in each team");
        int noOfPlayers = sc.nextInt();
        System.out.println(noOfPlayers + " Players");

        System.out.println("Please enter no of overs");
        int noOfOvers = sc.nextInt();
        System.out.println(noOfOvers + " Overs");

        System.out.println();
        System.out.println();
        System.out.println("Please enter names of players from Team 1");
        int count = 0;
        Team team1 = new Team("Team-1", noOfPlayers);
        while (count < noOfPlayers && sc.hasNext()) {
            String playerName = sc.next();
            team1.addPlayer(playerName);
            ++count;
        }

        System.out.println();
        System.out.println();
        System.out.println("Please enter names of players from Team 2");
        int count1 = 0;
        Team team2 = new Team("Team-2", noOfPlayers);
        while (count1 < noOfPlayers) {
            String playerName = sc.next();
            team2.addPlayer(playerName);
            ++count1;
        }

        System.out.println();
        System.out.println();
        System.out.println("Please enter 1 for Team-1 or enter 2 for Team-2 to choose first batting team");
        int battingTeam = sc.nextInt();

        TeamInning teamInningOne;
        TeamInning teamInningTwo;
        if (battingTeam == 1) {
            teamInningOne = new TeamInning("Team-1", noOfPlayers, noOfOvers, team1);
            teamInningTwo = new TeamInning("Team-2", noOfPlayers, noOfOvers, team2);
        } else {
            teamInningOne = new TeamInning("Team-2", noOfPlayers, noOfOvers, team2);
            teamInningTwo = new TeamInning("Team-1", noOfPlayers, noOfOvers, team1);
        }

        teamInningOne.startInning();
        teamInningOne.setFirstInning(true);

        while (true) {
            teamInningOne.startNewOver();

            while (true) {
                String bowlState = sc.next();
                boolean overEnd = teamInningOne.bowl(bowlState);
                if (overEnd) {
                    break;
                }
            }
            if (teamInningOne.isInningOver() || teamInningOne.isTeamAllOut()) {
                break;
            }
            teamInningOne.printScoreCard();
        }
        System.out.println("First Inning final scorecard");
        teamInningOne.printScoreCard();
        System.out.println();
        System.out.println();

        System.out.println("====================================");
        System.out.println("STARTING THE SECOND INNING");
        teamInningTwo.startInning();
        teamInningTwo.setFirstInning(false);
        teamInningTwo.setTarget(teamInningOne.score + 1);

        while (true) {
            teamInningTwo.startNewOver();

            while (true) {
                String bowlState = sc.nextLine();
                boolean overEnd = teamInningTwo.bowl(bowlState);
                if (overEnd) {
                    break;
                }
            }
            if (teamInningOne.matchWon() || teamInningOne.isInningOver() || teamInningOne.isTeamAllOut()) {
                break;
            }
            teamInningTwo.printScoreCard();
        }

        System.out.println("Second Inning final scorecard");
        teamInningTwo.printScoreCard();
        System.out.println();
        System.out.println();


        System.out.println("--------------------------------------");
        System.out.println("--------------------------------------");
        System.out.println("--------------------------------------");

        System.out.println("\t\t\t RESULT: \t\t\t");
        if (teamInningOne.score > teamInningTwo.score) {
            System.out.println("\t\t\t TEAM-1 won by " + (teamInningOne.score - teamInningTwo.score) + " runs");
        } else if (teamInningOne.score < teamInningTwo.score) {
            System.out.println("\t\t\t TEAM-2 won by " + (teamInningTwo.noOfPlayes - teamInningTwo.noOfWickets) + " wickets");
        } else {
            System.out.println("\t\t\t Match Draw");
        }

        System.out.println("--------------------------------------");
        System.out.println("--------------------------------------");
        System.out.println("--------------------------------------");

    }
}
