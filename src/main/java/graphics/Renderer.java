package graphics;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import guis.SwingManager;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Renderer {
    private static GLProfile profile = null;
    private static GLCanvas glCanvas = null;
    private static JFrame mainFrame = null;

    public static int screenWidth = 1000;
    public static int screenHeight = 800;

    public static float unitsWide = 800;
    public static float unitsTall = 0;

    public static void init() {
        GLProfile.initSingleton();
        profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);

        glCanvas = new GLCanvas(caps);
        glCanvas.addGLEventListener(new EventListener());

        mainFrame = new JFrame("MapZ");
        mainFrame.setResizable(false);
        mainFrame.requestFocus();

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.dispose();
                System.exit(0);
            }
        });

        mainFrame.setSize(screenWidth, screenHeight);
        SwingManager.build(mainFrame, glCanvas);
        mainFrame.setVisible(true);
    }

    public static void render() {
        if (glCanvas == null)
            return;

        glCanvas.display();
    }

    public static int getWindowHeight() {
        return mainFrame.getHeight();
    }

    public static int getWindowWidth() {
        return mainFrame.getWidth();
    }

    public static GLProfile getProfile() {
        return profile;
    }

    public static void stop() {
        mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
    }
}
