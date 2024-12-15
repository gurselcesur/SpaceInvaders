package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseScreen extends JFrame {
    private JPanel panel1;
    private JButton resumeButton;
    private JButton mainMenuButton;
    private JButton exitButton;

    public PauseScreen() {
        // Paneli ekleme ve pencere ayarları
        add(panel1);
        setTitle("Pause");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Butonlar için aksiyon dinleyicileri ekleme
        addButtonListeners();
    }

    private void addButtonListeners() {
        // Resume (Devam Et) butonu
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Oyunu devam ettirme mantığını buraya yazabilirsiniz
                System.out.println("Resume button clicked! Game resumes.");
                // Örneğin, duraklatma ekranını kapatma
                dispose();
            }
        });

        // Ana Menüye Dön butonu
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ana menüye dönme mantığını buraya yazabilirsiniz
                System.out.println("Main Menu button clicked! Returning to main menu.");
                // Gerekirse bu pencereyi kapatın
                dispose();
            }
        });

        // Çıkış butonu
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Programdan çıkış mantığını buraya yazabilirsiniz
                System.out.println("Exit button clicked! Exiting game.");
                System.exit(0); // Uygulamayı kapatır
            }
        });
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
