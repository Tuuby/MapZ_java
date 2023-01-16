import engine.MapUpdater;
import graphics.Renderer;
import noise.NoiseMap;
import noise.TerrainMap;

public class NoiseMain {

    public static void main(String[] args) {
        NoiseMap map = new TerrainMap(800, 800);
        map.generateMap();
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
