package noise;

import graphics.Graphics;

import java.awt.*;
import java.util.Random;

public abstract class NoiseMap {
    protected int width;
    protected int height;

    public NoiseMap(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract short[][] generateNoise(int octaves, double persistance, double lacunarity, long seed);

    public abstract void render();
}
