package graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import engine.MapUpdater;

public class EventListener implements GLEventListener {
    public static GL2 gl = null;

    public void init(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(0.5f, 0.5f, 0.5f, 1);

        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glEnable(GL2.GL_BLEND);

        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

        Graphics.setColor(1, 1, 1, 1);
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {
        System.out.println("Window is closed!");
        System.exit(0);
    }

    public void display(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        MapUpdater.render();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
        gl = glAutoDrawable.getGL().getGL2();

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        Renderer.unitsTall = Renderer.getWindowHeight() / ((Renderer.getWindowWidth()) / Renderer.unitsWide);

        gl.glOrtho(0, Renderer.unitsWide, Renderer.unitsTall, 0, -1, 1);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }
}
