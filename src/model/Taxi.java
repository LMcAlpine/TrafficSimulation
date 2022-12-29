package model;

import java.util.Map;

/**
 * Class for the Taxi vehicle extends AbstractVehicle
 */
public class Taxi extends AbstractVehicle {

    /**
     * how long this taxi will be dead for
     */
    private static final int DEATH_TIME = 15;
    /**
     * the current direction of the taxi
     */
    private Direction currentDirection;

    public Taxi(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);

        this.currentDirection = theDir;
    }

    /**
     * Returns whether the taxi may move onto the given type of
     * terrain, when the streetlights are the given color.
     *
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether this taxi may move onto the given type of
     * terrain when the streetlights are the given color.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if (theTerrain == Terrain.STREET || (theTerrain == Terrain.CROSSWALK && (theLight == Light.GREEN))) {
            return true;
        }
        // true if the terrain is a traffic light and the light is green or yellow. False otherwise.
        return theTerrain == Terrain.LIGHT && (theLight == Light.GREEN || theLight == Light.YELLOW);

    }

    /**
     * Returns the direction this taxi would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this taxi would like to move.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {

        // if the terrain in front is a street, crosswalk or a light
        if ((theNeighbors.get(getDirection()) == Terrain.STREET) || (theNeighbors.get(getDirection()) == Terrain.CROSSWALK) || (theNeighbors.get(getDirection()) == Terrain.LIGHT)) {
            return getDirection();
        }
        // if the terrain to the left is a street, crosswalk, or a light
        if ((theNeighbors.get(getDirection().left()) == Terrain.STREET) || (theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) || (theNeighbors.get(getDirection().left()) == Terrain.LIGHT)) {
            return getDirection().left();
        }
        // if the terrain to the right is a street, crosswalk, or a light
        if (theNeighbors.get(getDirection().right()) == Terrain.STREET || (theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) || theNeighbors.get(getDirection().right()) == Terrain.LIGHT) {

            return getDirection().right();
        }
        // if none of those pass return the reverse direction
        return currentDirection = getDirection().reverse();


    }


}
