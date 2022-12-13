package guis.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SeedView {
    private final JPanel panel;
    private final JTextField seedTextField;
    private final JButton randomSeedButton;
    private final JButton generateButton;

    public SeedView(int seedValue) {
        Font font = new Font("SansSerif", Font.PLAIN, 12);
        // main panel
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(0, 0, 20, 0));

        // top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setMaximumSize(new Dimension(1000, 25));

        // label
        JLabel seedLabel = new JLabel("Seed");
        seedLabel.setFont(font);
        seedLabel.setBorder(new EmptyBorder(0, 0, 0, 5));

        // textfield
        seedTextField = new JTextField(String.valueOf(seedValue));
        seedTextField.setMaximumSize(new Dimension(500, 25));

        // button for generating random value
        randomSeedButton = new JButton("Random");

        // button for generating
        generateButton = new JButton("Generate");

        topPanel.add(seedLabel, BorderLayout.LINE_START);
        topPanel.add(seedTextField, BorderLayout.CENTER);
        topPanel.add(randomSeedButton, BorderLayout.LINE_END);

        panel.add(topPanel);
        panel.add(generateButton);
    }
    public JPanel getPanel() {
        return this.panel;
    }
    public JTextField getTextField() {
        return this.seedTextField;
    }
    public JButton getRandomSeedButton() { return this.randomSeedButton; }
    public JButton getButton() {
        return this.generateButton;
    }
}
