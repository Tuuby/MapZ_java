package noise;

/**
 * Base class for all noise functions
 */
public abstract class Noise {
    
    /**
     * return noise value for 2D coordinates
     * @param x x-coord
     * @param y y-coord
     * @return noise value
     */
    public abstract double noise(double x, double y);
}
