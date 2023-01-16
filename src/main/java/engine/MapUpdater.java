package engine;

import noise.NoiseMap;
import noise.TerrainMap;

public class MapUpdater {
    private static NoiseMap map;

    public static void render() {
        if (map != null) {
            map.render();
        }
    }

    public static void setMap(NoiseMap map) {
        MapUpdater.map = map;
    }

    public static NoiseMap getMap() { return MapUpdater.map; }
}
