package engine;

import noise.TerrainMap;

public class MapUpdater {
    private static TerrainMap map;

    public static void render() {
        if (map != null) {
            map.render();
        }
    }

    public static void setMap(TerrainMap map) {
        MapUpdater.map = map;
    }

    public static TerrainMap getMap() { return MapUpdater.map; }
}
