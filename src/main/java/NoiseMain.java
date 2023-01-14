import engine.MapUpdater;
import graphics.Renderer;
import noise.NoiseMap;
import noise.TerrainMap;

public class NoiseMain {

    public static void main(String[] args) {
        TerrainMap map = new TerrainMap(800, 800);
        map.setElevationSeed(1321);
        map.setElevationScale(0.004f);
        map.setWaterlevel((short) 100);
        map.generateElevation();
        MapUpdater.setMap(map);
        Renderer.init();
        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
