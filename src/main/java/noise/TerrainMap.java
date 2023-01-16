package noise;

import graphics.Graphics;

import java.awt.*;
import java.util.Random;

public class TerrainMap extends NoiseMap {

    private Noise noise;

    private short[][] elevation;
    private long elevationSeed;
    private double elevationScale;

    private short waterlevel;

    public TerrainMap(int width, int height) {
        super(width, height);
        Random rnd = new Random();
        setElevationSeed(rnd.nextInt());
        setElevationScale(0.004f);
        setWaterlevel((short) 100);
    }

    public long getElevationSeed() {
        return elevationSeed;
    }

    public void setElevationSeed(long elevationSeed) {
        this.elevationSeed = elevationSeed;
    }

    public double getElevationScale() {
        return elevationScale;
    }

    public void setElevationScale(double elevationScale) {
        this.elevationScale = elevationScale;
    }

    public short getWaterlevel() {
        return waterlevel;
    }

    public void setWaterlevel(short waterlevel) {
        this.waterlevel = waterlevel;
    }

    @Override
    public void generateMap() {
        generateElevation();
    }

    public void generateElevation() {
        elevation = generateNoise(8, 0.5f, 2, elevationSeed);
    }
    public short[][] generateNoise(int octaves, double persistance, double lacunarity, long seed) {
        noise = new PerlinNoise(seed);
        short[][] generatedNoise = new short[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                double maxAmplitude = 0;
                double amplitude = 1;
                double frequency = elevationScale;
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

    public void render() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                float d;// ratio for brightness, here the highest elevation value
                short noiseValue = elevation[x][y];
                Color c;
                if (noiseValue < waterlevel) {
                    d = noiseValue / (float) waterlevel;
                    c = Color.decode("#0000ff");
                    graphics.Graphics.setColor(c, d);
                }
                else {
                    d = noiseValue / 512f; // TODO: find highest value
                    c = Color.decode("#00ff00");
                    graphics.Graphics.setColor(c, d);
                }
                Graphics.drawPixel(x, y);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Noise.NoiseMap{")
                .append("width=").append(width)
                .append(", height=").append(height)
                .append(",\nvalues=[");
        for (int i = 0; i < width; i++) {
            builder.append("[");
            for (int j = 0; j < height; j++) {
                if (j == 0)
                    builder.append(elevation[i][j]);
                else
                    builder.append(", ").append(elevation[i][j]);
            }
            builder.append("]\n");
        }
        builder.append("]}");
        return builder.toString();
    }
}
