package graphics;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Renderer {
    private static GLProfile profile = null;
    private static GLWindow window = null;

    public static int screenWidth = 1000;
    public static int screenHeight = 800;

    public static float unitsWide = 800;
    public static float unitsTall = 0;

    public static void init() {
        GLProfile.initSingleton();
        profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);

        window = GLWindow.create(caps);
        window.setSize(screenWidth, screenHeight);
        window.setResizable(false);
        window.addGLEventListener(new EventListener());

        window.setVisible(true);
    }

    public static void render() {
        if (window == null)
            return;

        window.display();
    }

    public static int getWindowHeight() {
        return window.getHeight();
    }

    public static int getWindowWidth() {
        return window.getWidth();
    }

    public static GLProfile getProfile() {
        return profile;
    }
}
