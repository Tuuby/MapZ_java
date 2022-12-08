package guis;

import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;
import java.awt.*;

public class SwingManager {
    private static JLabel WaterlevelLabel;
    private static JSlider WaterlevelSlider;


    public static void build(JFrame mainFrame, GLCanvas glCanvas) {

        Font myFont = new Font("SansSerif", Font.PLAIN, 12);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(glCanvas, BorderLayout.CENTER);


        JPanel uiPanel = new JPanel();
        uiPanel.setLayout(new BoxLayout(uiPanel, BoxLayout.Y_AXIS));

        WaterlevelLabel = new JLabel("Waterlevel");
        WaterlevelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        WaterlevelLabel.setFont(myFont);
        uiPanel.add(WaterlevelLabel);

        WaterlevelSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 100);
        WaterlevelSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
        WaterlevelSlider.setMajorTickSpacing(100);
        WaterlevelSlider.setMinorTickSpacing(16);
        WaterlevelSlider.setPaintTicks(true);
        WaterlevelSlider.setPaintLabels(true);
        uiPanel.add(WaterlevelSlider);

        mainPanel.add(uiPanel, BorderLayout.EAST);
        mainFrame.getContentPane().add(mainPanel);
    }
}
