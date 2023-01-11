package noise;

import graphics.Graphics;

import java.awt.*;
import java.util.Random;

public class NoiseMap {
    private int width;
    private int height;
    private long elevationSeed;
    private long moistureSeed;
    private short[][] elevation;
    private short[][] moisture;
    private boolean[][] trees;
    private double scale = 0.01;
    private short waterlevel = 0;
    private short weedlevel = 0;
    private int elevationOffset;
    private int moistureOffset;
    private Noise noise;

    public NoiseMap(int width, int height) {
        this.width = width;
        this.height = height;
        elevation = new short[width][height];
        moisture = new short[width][height];
        trees = new boolean[width][height];
    }

    public NoiseMap(int width, int height, long eSeed, long mSeed) {
        this(width, height);
        elevationSeed = eSeed;
        moistureSeed = mSeed;
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

    public long getMoistureSeed() {
        return moistureSeed;
    }

    public void setMoistureSeed(long moistureSeed) {
        this.moistureSeed = moistureSeed;
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

    public short getWeedlevel() {
        return weedlevel;
    }

    public void setWeedlevel(short weedlevel) {
        this.weedlevel = weedlevel;
    }

    public int getElevationOffset() {
        return elevationOffset;
    }

    public void setElevationOffset(int elevationOffset) {
        this.elevationOffset = elevationOffset;
    }

    public int getMoistureOffset() {
        return moistureOffset;
    }

    public void setMoistureOffset(int moistureOffset) {
        this.moistureOffset = moistureOffset;
    }

    public double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public void generateElevation() {
        noise = new OpenSimplexNoise(elevationSeed);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Values are between 1 & -1; so we multiply by 145 (255 / 1.75) to get the values between -127 & 127
                // Additionally we add 127 to get the values positive only between 0 & 255
                double el = (noise.noise(x * (scale * 0.5), y * (scale * 0.5))
                        + 0.5 * noise.noise(x * (scale * 2), y * (scale * 2))
                        + 0.25 * noise.noise(x * (scale * 4), y * (scale * 4)))
                        * 145 + 127;
                elevation[x][y] = (short) (Math.pow(el, 2) / 255);
                if (elevationOffset < 0) {
                    if (elevation[x][y] >= Short.MIN_VALUE - elevationOffset)
                        elevation[x][y] += elevationOffset;
                    else
                        elevation[x][y] = Short.MIN_VALUE;
                } else if (elevationOffset > 0) {
                    if (elevation[x][y] <= Short.MAX_VALUE - elevationOffset)
                        elevation[x][y] += elevationOffset;
                    else
                        elevation[x][y] = Short.MAX_VALUE;
                }
            }
        }
    }

    public void generateMoisture() {
        noise = new OpenSimplexNoise(moistureSeed);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double mo = (noise.noise(x * (scale * 0.5), y * (scale * 0.5))
                        + 0.5 * noise.noise(x * (scale * 2), y * (scale * 2))
                        + 0.25 * noise.noise(x * (scale * 4), y * (scale * 4)))
                        * 145;
                moisture[x][y] = (short) mo;
                if (moistureOffset < 0) {
                    if (moisture[x][y] >= Short.MIN_VALUE - moistureOffset)
                        moisture[x][y] += moistureOffset;
                    else
                        moisture[x][y] = Short.MIN_VALUE;
                } else if (moistureOffset > 0) {
                    if (moisture[x][y] <= Short.MAX_VALUE - moistureOffset)
                        moisture[x][y] += moistureOffset;
                    else
                        moisture[x][y] = Short.MAX_VALUE;
                }
            }
        }
    }

    public void distributeTrees() {
        Random rnd = new Random();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                short m = moisture[x][y];
                if (rnd.nextInt(255) < m / 8f * scale)
                    trees[x][y] = true;
            }
        }
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
                    Graphics.setColor(c, d);
                }
                else {
                    d = noiseValue / 512f; // TODO: find highest value
                    c = Color.decode("#00ff00");
                    Graphics.setColor(c, d);
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
