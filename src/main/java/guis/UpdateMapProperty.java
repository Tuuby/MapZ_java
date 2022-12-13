package guis;

/**
 * Interface for defining noise map update method (called in slider controller)
 * allows for custom update for each slider instance/ noise map property
 */
public interface UpdateMapProperty {

    void update(long value);
}
