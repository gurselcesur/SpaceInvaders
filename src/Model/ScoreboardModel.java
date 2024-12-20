package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoreboardModel {
    private List<ScoreEntry> highscores;
    private static final String SCOREBOARD_FILE = "resources/scoreboard.txt";

    public ScoreboardModel() {
        highscores = new ArrayList<>();
        loadHighscoresFromFile();
    }

    /**
     * Load high scores from the file at initialization.
     */
    private void loadHighscoresFromFile() {
        File file = new File(SCOREBOARD_FILE);
        if (!file.exists()) {
            return; // If file does not exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-", 2);
                if (parts.length == 2) {
                    String playerName = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    highscores.add(new ScoreEntry(playerName, score));
                }
            }
            sortHighscores(); // List the uploaded scores
        } catch (IOException e) {
            System.err.println("Failed to load scores from file: " + e.getMessage());
        }
    }

    /**
     * Add a new high score and save the updated highscores to the file in sorted order.
     * @param playerName Name of the player.
     * @param score Score to add.
     */
    public void addHighscore(String playerName, int score) {
        highscores.add(new ScoreEntry(playerName, score));
        sortHighscores(); // sort the scores from high to low
        saveHighscoresToFile(); // save updated scores to file
    }

    /**
     * Save sorted high scores to the scoreboard file.
     */
    private void saveHighscoresToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCOREBOARD_FILE))) {
            for (ScoreEntry entry : highscores) {
                writer.write(entry.getPlayerName() + " - " + entry.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Failed to save scores to file: " + e.getMessage());
        }
    }

    /**
     * Get the list of high scores.
     * @return List of high score strings.
     */
    public List<String> getHighscores() {
        List<String> highscoreStrings = new ArrayList<>();
        for (ScoreEntry entry : highscores) {
            highscoreStrings.add(entry.getPlayerName() + " - " + entry.getScore());
        }
        return highscoreStrings;
    }

    // Private method to sort the highscores from highest to lowest
    private void sortHighscores() {
        highscores.sort(Comparator.comparingInt(ScoreEntry::getScore).reversed());
    }

    /**
     * Inner class to hold score entries.
     */
    private static class ScoreEntry {
        private final String playerName;
        private final int score;

        public ScoreEntry(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }
    }
}