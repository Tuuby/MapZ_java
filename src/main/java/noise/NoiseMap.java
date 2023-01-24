package noise;

import graphics.Graphics;

import java.awt.*;
import java.util.Random;

public abstract class NoiseMap {

    protected Noise noise;
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

    public abstract void generateMap();

    public abstract void render();

    protected short[][] generateNoise(int octaves, double persistance, double lacunarity, double scale, long seed) {
        noise = new PerlinNoise(seed);
        short[][] generatedNoise = new short[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                double maxAmplitude = 0;
                double amplitude = 1;
                double frequency = scale;
                double noiseValue = 0;

                // generating the noise values in multiple octaves
                for (int i = 0; i < octaves; i++) {
                    noiseValue += noise.noise(x * frequency, y * frequency) * amplitude;
                    maxAmplitude += amplitude;
                    amplitude *= persistance;
                    frequency *= lacunarity;
                }

                // normalize to the maximum amplitude
                noiseValue /= maxAmplitude;

                // normalize to values between the low and high thresholds
                short low = 0;
                short high = 255;
                noiseValue = noiseValue * (high - low) / 2 + (double) (high + low) / 2;

                // stretch through a quadratic function
                noiseValue = Math.pow(noiseValue, 2) / high;

                // assigning the values to the array
                generatedNoise[x][y] = (short) noiseValue;
            }
        }
        return generatedNoise;
    }
}
