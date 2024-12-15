package Test;

import Model.ScoreboardModel;

public class ScoreboardTest {
    public static void main(String[] args) {
        ScoreboardModel scoreboard = new ScoreboardModel();

        System.out.println("1) Starting scores:");
        for (String highscore : scoreboard.getHighscores()) {
            System.out.println(highscore);
        }

        System.out.println("\n2) New scores are being added...");
        scoreboard.addHighscore("John", 2500);
        scoreboard.addHighscore("Sarah", 4500);
        scoreboard.addHighscore("Invalid Player", -500);

        System.out.println("\n3) Updated scores:");
        for (String highscore : scoreboard.getHighscores()) {
            System.out.println(highscore);
        }
    }
}