public class NoiseMain {

    public static void main(String[] args) {
        NoiseMap map = new NoiseMap(10, 10, 1231, 0);
        map.generateElevation();
        System.out.println(map);
    }
}
