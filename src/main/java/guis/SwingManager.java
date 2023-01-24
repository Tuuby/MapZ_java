package guis;

import com.jogamp.opengl.awt.GLCanvas;
import engine.MapUpdater;
import graphics.Renderer;
import guis.controller.SeedController;
import guis.controller.SliderController;
import guis.view.SeedView;
import guis.view.SliderView;
import noise.TerrainMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SwingManager {

    public static void build(JFrame mainFrame, GLCanvas glCanvas) {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(glCanvas, BorderLayout.CENTER);


        JPanel uiPanel = new JPanel();
        uiPanel.setLayout(new BoxLayout(uiPanel, BoxLayout.Y_AXIS));
        uiPanel.setPreferredSize(new Dimension(280, 0));
        uiPanel.setBorder(new EmptyBorder(10, 3, 0, 3));

        SeedView seedView = new SeedView(0); // TODO: get actual init seed value from map
        SeedController seedController = new SeedController(seedView, value -> {
            TerrainMap map = (TerrainMap) MapUpdater.getMap();
            if(map.getElevationSeed() != value) {
                map.setElevationSeed(value);
                map.generateElevation();
                MapUpdater.render();
                Renderer.render();
            }
        });

        uiPanel.add(seedView.getPanel());

        SliderView waterView = new SliderView("water", 0, 255);
        SliderView grassView = new SliderView("grass", 0, 255);

        SliderController waterController = new SliderController(waterView, value -> {
            ((TerrainMap)MapUpdater.getMap()).setWaterlevel((short) value);
            Renderer.render();
        });
        SliderController grassController = new SliderController(grassView, value -> {
            // MapUpdater.getMap().setWeedlevel((short) value);
            Renderer.render();
        });

        uiPanel.add(waterView.getPanel());
        uiPanel.add(grassView.getPanel());

        mainPanel.add(uiPanel, BorderLayout.EAST);
        mainFrame.getContentPane().add(mainPanel);
    }
}
