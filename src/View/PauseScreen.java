package View;

import javax.swing.*;

public class PauseScreen extends JFrame {
    private JPanel panel1;
    private JButton resumeButton;
    private JButton mainMenuButton;
    private JButton exitButton;

    public PauseScreen() {
        add(panel1);
        setTitle("Pause");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public JButton getResumeButton() {
        return resumeButton;
    }

    public JButton getMainMenuButton() {
        return mainMenuButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

}
