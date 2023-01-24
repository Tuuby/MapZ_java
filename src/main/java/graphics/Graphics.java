package graphics;

import com.jogamp.opengl.GL2;
import java.awt.*;

public class Graphics {

    private static GL2 gl;

    private static float red = 1;
    private static float green = 1;
    private static float blue = 1;
    private static float alpha = 1;

    public static void drawPixel(float x, float y) {
        gl = EventListener.gl;

        gl.glColor4f(red, green, blue, alpha);

        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2f(x, y);
        gl.glEnd();
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


    /**
     * set color with hsb properties
     * @param hue color tone in range [0, 360]
     * @param saturation intensity of the color [0, 100]
     * @param brightness / value of the color [0, 100]
     */
    public static void setHSBColor(float hue, float saturation, float brightness) {
        hue /= 360f;
        saturation /= 100f;
        brightness /= 100f;
        int rgb = Color.HSBtoRGB(hue, saturation, brightness);

        red = ((rgb>>16)&0xFF) / 255f;
        green = ((rgb>>8)&0xFF) / 255f;
        blue = (rgb&0xFF) / 255f;
        alpha = 1f;
    }
}
