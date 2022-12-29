package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * class for the Truck. Extends AbstractVehicle
 */
public class Truck extends AbstractVehicle {

    /**
     * how long the Truck will be dead for (it never dies)
     */
    private static final int DEATH_TIME = 0;
    /**
     * the current direction of the Truck
     */
    private Direction currentDirection;
    /**
     * Random object to choose random directions
     */
    private Random random;

    /**
     * create a Truck object with the provided x and y coordinates and direction
     *
     * @param theX   x coordinate of a Truck
     * @param theY   y coordinate of a Truck
     * @param theDir direction of a Truck
     */
    public Truck(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
        this.currentDirection = theDir;
        this.random = getRandom();

    }


    /**
     * Returns whether this Truck may move onto the given type of
     * terrain, when the streetlights are the given color.
     *
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether this Truck may move onto the given type of
     * terrain when the streetlights are the given color.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        if (theTerrain == Terrain.STREET || (theTerrain == Terrain.CROSSWALK && (theLight == Light.GREEN || theLight == Light.YELLOW))) {
            return true;
        }
        // the truck goes through all lights
        return theTerrain == Terrain.LIGHT && (theLight == Light.GREEN || theLight == Light.YELLOW || theLight == Light.RED);
    }

    /**
     * Returns the direction this Truck would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this Truck would like to move.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {

        // valid directions for the Truck
        ArrayList<Direction> validDirections = new ArrayList<>();
        // to pick a random element out of the validDirections ArrayList
        final int randomElement;


        // if the terrain to the left is a street, crosswalk, or a light
        if (theNeighbors.get(getDirection().left()) == Terrain.STREET || ((theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK)) || theNeighbors.get(getDirection().left()) == Terrain.LIGHT) {
            validDirections.add(getDirection().left());
        }
        // if the terrain to the right is a street, crosswalk, or a light
        if (theNeighbors.get(getDirection().right()) == Terrain.STREET || ((theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) || theNeighbors.get(getDirection().right()) == Terrain.LIGHT)) {
            validDirections.add(getDirection().right());

        }
        // if the terrain in front is a street, crosswalk, or a light
        if (theNeighbors.get(getDirection()) == Terrain.STREET || ((theNeighbors.get(getDirection()) == Terrain.CROSSWALK)) || theNeighbors.get(getDirection()) == Terrain.LIGHT) {
            validDirections.add(getDirection());
        }

        // if there are no valid directions. Reverse the truck
        if (validDirections.isEmpty()) {
            currentDirection = getDirection().reverse();
        } else {
            // pick a random direction from the valid directions
            randomElement = random.nextInt(validDirections.size());
            currentDirection = validDirections.get(randomElement);
        }
        return currentDirection;

    }

}
