package graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import resource.ImageResource;

import java.awt.*;

public class Graphics {

    private static GL2 gl;

    private static float red = 1;
    private static float green = 1;
    private static float blue = 1;
    private static float alpha = 1;

    private static float rotation = 0;

    public static void drawPixel(float x, float y) {
        gl = EventListener.gl;

        gl.glColor4f(red, green, blue, alpha);

        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2f(x, y);
        gl.glEnd();
    }

    public static void drawImage(ImageResource image, float x, float y, float width, float height) {
        gl = EventListener.gl;

        Texture texture = image.getTexture();

        if (x - width / 2 > Renderer.unitsWide ||
            x + width / 2 < 0 ||
            y - height / 2 > Renderer.unitsTall ||
            y + height / 2 < 0) {
            return;
        }

        if (texture != null) {
            gl.glBindTexture(GL2.GL_TEXTURE_2D, texture.getTextureObject());
        }

        gl.glTranslatef(x, y, 0);
        gl.glRotatef(rotation, 0, 0, 1);

        gl.glColor4f(1, 1, 1, 1);
        gl.glBegin(GL2.GL_QUADS);

        gl.glTexCoord2f(0, 0);
        gl.glVertex2f(-width / 2, -height / 2);
        gl.glTexCoord2f(1, 0);
        gl.glVertex2f(width / 2, -height / 2);
        gl.glTexCoord2f(1,1);
        gl.glVertex2f(width / 2,  height / 2);
        gl.glTexCoord2f(0,1);
        gl.glVertex2f(-width / 2, height / 2);
        gl.glEnd();
        gl.glFlush();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);

        gl.glRotatef(-rotation, 0, 0, 1);
        gl.glTranslatef(-x, -y, 0);
    }

    public static void setColor(float r, float g, float b, float a) {
        red = Math.max(0, Math.min(1, r));
        green = Math.max(0, Math.min(1, g));
        blue = Math.max(0, Math.min(1, b));
        alpha = Math.max(0, Math.min(1, a));
    }

    /**
     * set color to paint with dynamic brightness
     * @param c Color to set
     * @param d dividend to calculate brightness from (elevation/draw-level)
     */
    public static void setColor(Color c, float d) {
        float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
        float hue = hsb[0];
        float saturation = hsb[1];
        float brightness = 0.5f; // base brightness to add to
        float brightnessGain = (1f - brightness) * d; // brightness to add based on fracture
        brightness += brightnessGain;

        int rgb = Color.HSBtoRGB(hue, saturation, brightness);

        red = ((rgb>>16)&0xFF) / 255f;
        green = ((rgb>>8)&0xFF) / 255f;
        blue = (rgb&0xFF) / 255f;
        alpha = c.getAlpha() / 255f;
    }

    public static void setRotation(float rotation) {
        Graphics.rotation = rotation;
    }
}
