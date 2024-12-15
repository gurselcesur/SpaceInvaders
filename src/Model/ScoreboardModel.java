package Model;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardModel {
    private List<String> highscores;

    public ScoreboardModel() {
        // Initialize with dummy data (replace with actual implementation)
        highscores = new ArrayList<>();
        highscores.add("Player1 - 5000");
        highscores.add("Player2 - 4000");
        highscores.add("Player3 - 3000");
        highscores.add("Player4 - 2000");
        highscores.add("Player5 - 1000");
    }

    /**
     * Get the list of high scores.
     * @return List of high scores.
     */
    public List<String> getHighscores() {
        return new ArrayList<>(highscores); // Return a copy to protect encapsulation
    }

    /**
     * Add a new high score.
     * @param score The high score to add.
     */
    public void addHighscore(String score) {
        highscores.add(score);
        // Optionally sort or limit the size of the list
    }
}