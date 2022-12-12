package guis;

import com.jogamp.opengl.awt.GLCanvas;
import guis.controller.SliderController;
import guis.view.SliderView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SwingManager {

    public static void build(JFrame mainFrame, GLCanvas glCanvas) {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(glCanvas, BorderLayout.CENTER);


        JPanel uiPanel = new JPanel();
        uiPanel.setLayout(new BoxLayout(uiPanel, BoxLayout.Y_AXIS));
        uiPanel.setBorder(new EmptyBorder(10, 3, 0, 3));

        SliderView waterView = new SliderView("water");
        SliderView grassView = new SliderView("grass");

        SliderController waterController = new SliderController(waterView);
        SliderController grassController = new SliderController(grassView);

        uiPanel.add(waterView.getPanel());
        uiPanel.add(grassView.getPanel());

        mainPanel.add(uiPanel, BorderLayout.EAST);
        mainFrame.getContentPane().add(mainPanel);
    }
}
