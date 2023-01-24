package noise;

import graphics.Graphics;

import java.awt.*;
import java.util.Random;

public class TerrainMap extends NoiseMap {

    private short[][] elevation;
    private long elevationSeed;
    private double elevationScale;

    private short[][] ore;
    private long oreSeed;
    private double oreScale;

    private short waterlevel;

    public TerrainMap(int width, int height) {
        super(width, height);

        Random rnd = new Random();
        setElevationSeed(rnd.nextInt());
        setElevationScale(0.004f);
        setWaterlevel((short) 100);

        setOreSeed(rnd.nextInt());
        setOreScale(0.007f);
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

    public short[][] getOre() {
        return ore;
    }

    public void setOre(short[][] ore) {
        this.ore = ore;
    }

    public long getOreSeed() {
        return oreSeed;
    }

    public void setOreSeed(long oreSeed) {
        this.oreSeed = oreSeed;
    }

    public double getOreScale() {
        return oreScale;
    }

    public void setOreScale(double oreScale) {
        this.oreScale = oreScale;
    }

    @Override
    public void generateMap() {
        generateElevation();
        generateOre();
    }

    public void generateElevation() {
        elevation = generateNoise(8, 0.5f, 2, elevationScale, elevationSeed);
    }

    public void generateOre() {
        ore = generateNoise(4, 0.5f, 2, oreScale, oreSeed);
    }

    public void render() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (ore[x][y] > 90 && elevation[x][y] > waterlevel) {
                    Graphics.setHSBColor(9, 33, 45);
                } else {
                    float d;// ratio for brightness, here the highest elevation value
                    float elevation = this.elevation[x][y];
                    Color c;
                    if (elevation < waterlevel) {
                        Graphics.setHSBColor(240, 100, (elevation/waterlevel) * 50 + 50);
                    }
                    else {
                        Graphics.setHSBColor(120, 100, 100 - ((elevation / 255f) * 65));
                    }
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
