package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * class for the ATV. Extends AbstractVehicle
 */
public class ATV extends AbstractVehicle {

    /**
     * how long the ATV will be dead for
     */
    private static final int DEATH_TIME = 25;
    /**
     * the current direction of the ATV
     */
    private Direction currentDirection;

    /**
     * Random object to choose random directions
     */
    private Random random;


    /**
     * create an ATV object with the provided x and y coordinates and direction
     *
     * @param theX   x coordinate of an ATV
     * @param theY   y coordinate of an ATV
     * @param theDir direction of an ATV
     */
    public ATV(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
        this.currentDirection = theDir;
        this.random = getRandom();
    }

    /**
     * Returns whether this ATV may move onto the given type of
     * terrain, when the streetlights are the given color.
     *
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether this ATV may move onto the given type of
     * terrain when the streetlights are the given color.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        // the ATV can go everywhere except a wall. Return true if not a wall
        return theTerrain == Terrain.STREET || theTerrain == Terrain.CROSSWALK || theTerrain == Terrain.GRASS || theTerrain == Terrain.LIGHT || theTerrain == Terrain.TRAIL;
    }

    /**
     * Returns the direction this ATV would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this ATV would like to move.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {

        // valid directions for the ATV
        ArrayList<Direction> validDirections = new ArrayList<>();
        // to pick a random element out of the validDirections ArrayList
        final int randomElement;
        // all valid terrains if they are straight in front of the ATV
        if (theNeighbors.get(getDirection()) == Terrain.STREET || theNeighbors.get(getDirection()) == Terrain.CROSSWALK || theNeighbors.get(getDirection()) == Terrain.LIGHT || theNeighbors.get(getDirection()) == Terrain.GRASS || theNeighbors.get(getDirection()) == Terrain.TRAIL) {
            validDirections.add(getDirection());
        }
        // all valid terrains if they are to the left of the ATV
        if (theNeighbors.get(getDirection().left()) == Terrain.STREET || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK || theNeighbors.get(getDirection().left()) == Terrain.LIGHT || theNeighbors.get(getDirection().left()) == Terrain.GRASS || theNeighbors.get(getDirection().left()) == Terrain.TRAIL) {
            validDirections.add(getDirection().left());
        }
        // all valid terrains if they are to the right of the ATV
        if (theNeighbors.get(getDirection().right()) == Terrain.STREET || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK || theNeighbors.get(getDirection().right()) == Terrain.LIGHT || theNeighbors.get(getDirection().right()) == Terrain.GRASS || theNeighbors.get(getDirection().right()) == Terrain.TRAIL) {
            validDirections.add(getDirection().right());
        }
        // if the ATV is on a wall. Go a random Direction.
        if (theNeighbors.get(getDirection()) == Terrain.WALL || theNeighbors.get(getDirection().right()) == Terrain.WALL || theNeighbors.get(getDirection().left()) == Terrain.WALL) {
            // add the valid directions
            validDirections.add(getDirection());
            validDirections.add(getDirection().left());
            validDirections.add(getDirection().right());
            // select a random direction
            randomElement = random.nextInt(validDirections.size());
            // change the current direction to the random direction
            currentDirection = validDirections.get(randomElement);
        }


        return currentDirection;


    }


}
