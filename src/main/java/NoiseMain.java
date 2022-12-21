import engine.MapUpdater;
import graphics.Renderer;
import noise.NoiseMap;

public class NoiseMain {

    public static void main(String[] args) {
        NoiseMap map = new NoiseMap(800, 800, 131, 0);
        map.setWaterlevel((short) 100);
        map.generateElevation(5, 0.5f, 2);
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
