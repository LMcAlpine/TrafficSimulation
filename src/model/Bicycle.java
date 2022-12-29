package model;

import java.util.Map;

/**
 * class for the bicycle. Extends AbstractVehicle
 */
public class Bicycle extends AbstractVehicle {

    /**
     * how long the bicycle will be dead for
     */
    private static final int DEATH_TIME = 35;
    /**
     * the current direction of the bicycle
     */
    private Direction currentDirection;

    /**
     * create a bicycle object with the provided x abd y coordinates. And direction
     *
     * @param theX   x coordinates of a bicycle
     * @param theY   y coordinates of a bicycle
     * @param theDir direction of a car
     */
    public Bicycle(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
        this.currentDirection = theDir;
    }

    /**
     * Returns whether this Bicycle may move onto the given type of
     * terrain, when the streetlights are the given color.
     *
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether this Bicycle may move onto the given type of
     * terrain when the streetlights are the given color.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {

        // Bicycles prefer trails
        if (theTerrain == Terrain.TRAIL) {
            return true;
        }
        if ((theTerrain == Terrain.STREET)) {
            return true;
        }
        if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {
            return true;
        }
        // if the terrain is a traffic light, and it is green return true. Otherwise, return false
        return (theTerrain == Terrain.LIGHT) && theLight == Light.GREEN;
    }

    /**
     * Returns the direction this Bicycle would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this Bicycle would like to move.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {

        // prefer trail
        // trail in front
        if (theNeighbors.get(getDirection()) == Terrain.TRAIL) {
            return currentDirection = getDirection();
        }
        // trail to the left
        if (theNeighbors.get(getDirection().left()) == Terrain.TRAIL) {
            return currentDirection = getDirection().left();
        }
        //trail to the right
        if (theNeighbors.get(getDirection().right()) == Terrain.TRAIL) {
            return currentDirection = getDirection().right();
        }
        // if a street or a traffic light is in front
        if (theNeighbors.get(getDirection()) == Terrain.STREET || theNeighbors.get(getDirection()) == Terrain.LIGHT) {
            return currentDirection = getDirection();
        }
        // if a street or traffic light is to the left
        if (theNeighbors.get(getDirection().left()) == Terrain.STREET || theNeighbors.get(getDirection().left()) == Terrain.LIGHT) {
            return currentDirection = getDirection().left();
        }
        // if a street or traffic light is to the right
        if (theNeighbors.get(getDirection().right()) == Terrain.STREET || theNeighbors.get(getDirection().right()) == Terrain.LIGHT) {
            return currentDirection = getDirection().right();
        }
        // if a crosswalk is in front
        if (theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
            return currentDirection = getDirection();
        }
        // if a crosswalk is to the left
        if (theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
            return currentDirection = getDirection().left();
        }
        // if a crosswalk is to the right
        if (theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
            return currentDirection = getDirection().right();
        }

        // if none of those pass. Reverse the direction
        return currentDirection = getDirection().reverse();
    }


}
