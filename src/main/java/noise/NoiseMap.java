package noise;

import graphics.Graphics;

import java.util.Random;

public class NoiseMap {
    private int width;
    private int height;
    private long elevationSeed;
    private final short[][] elevation;
    private double scale = 0.1;
    private short waterlevel = 0;
    private int elevationOffset;
    private Noise noise;

    public NoiseMap(int width, int height) {
        this.width = width;
        this.height = height;
        elevation = new short[width][height];
    }

    public NoiseMap(int width, int height, long eSeed, long mSeed) {
        this(width, height);
        elevationSeed = eSeed;
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

    public long getElevationSeed() {
        return elevationSeed;
    }

    public void setElevationSeed(long elevationSeed) {
        this.elevationSeed = elevationSeed;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public short getWaterlevel() {
        return waterlevel;
    }

    public void setWaterlevel(short waterlevel) {
        this.waterlevel = waterlevel;
    }

    public double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    // renders only blue canvas so far
    public void generateElevation(int octaves, double persistance, double lacunarity) {
        noise = new PerlinNoise(elevationSeed);

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
                elevation[x][y] = (short) noiseValue;
            }
        }
    }

    public void generateElevation() {
        generateElevation(5, 0.5f, 2);
    }
    public void render() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (elevation[x][y] < waterlevel) {
                    Graphics.setColor(0, elevation[x][y] / 255f, (elevation[x][y] + 127) / 255f, 1);
                    Graphics.drawPixel(x, y);
                }
                else {
                    Graphics.setColor(elevation[x][y] / 255f - 0.3f, 0.9f - (elevation[x][y] / 1023f), 0, 1);
                    Graphics.drawPixel(x, y);
                }
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
