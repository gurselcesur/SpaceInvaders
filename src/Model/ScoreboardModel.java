package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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
     * Load high scores from the file.
     */
    private void loadHighscoresFromFile() {
        highscores.clear(); // Clear existing highscores to prevent duplication
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
            sortHighscores(); // Sort the scores after loading
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
        sortHighscores(); // Sort the scores from high to low
        saveHighscoresToFile(); // Save updated scores to file
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
     * Always reload the scores from the file to ensure the latest data is fetched.
     * @return List of high score strings.
     */
    public List<String> getHighscores() {
        loadHighscoresFromFile(); // Reload the scores from the file
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

    /**
     * This method writes a new score to the scoreboard file. It reads the existing scores from a file, adds the new score,
     * sorts the scores in descending order, and then writes the sorted scores back to the file.
     *
     * @param username The username of the player.
     * @param score The score achieved by the player.
     */
    public void writeScoreToFile(String username, int score) {
        // Create a list to store the current scores
        List<String> scoreList = new ArrayList<>();

        // Read the existing scoreboard.txt file
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/scoreboard.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scoreList.add(line);  // Add each line from the file to the list
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the scoreboard file: " + e.getMessage());
        }

        // Add the new score to the list
        scoreList.add(username + " - " + score);

        // Sort the list in descending order based on the score
        scoreList.sort((entry1, entry2) -> {
            // Extract the scores and compare them
            int score1 = Integer.parseInt(entry1.split(" - ")[1]);
            int score2 = Integer.parseInt(entry2.split(" - ")[1]);
            return Integer.compare(score2, score1);  // Sorting in descending order
        });

        // Write the sorted list of scores back to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/scoreboard.txt"))) {
            for (String entry : scoreList) {
                writer.write(entry);  // Write each score entry to the file
                writer.newLine();  // Add a new line after each score
            }
            System.out.println("Score written to scoreboard.txt: " + username + " - " + score);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the scoreboard file: " + e.getMessage());
        }
    }


}