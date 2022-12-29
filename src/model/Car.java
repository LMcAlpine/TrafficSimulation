package model;

import java.util.Map;


/**
 * class for the Car vehicle. Extends AbstractVehicle
 */
public class Car extends AbstractVehicle {


    /**
     * how long the car will be dead for
     */
    private static final int DEATH_TIME = 15;
    /**
     * the current direction of the car
     */
    private Direction currentDirection;



    /**
     * create a car object with the provided x and y coordinates. And direction
     *
     * @param theX   x coordinate of a car
     * @param theY   y coordinate of a car
     * @param theDir direction of a car
     */
    public Car(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
        this.currentDirection = theDir;

    }


    /**
     * method to check if a car is able to pass a terrain
     *
     * @param theTerrain The terrain.
     * @param theLight   The traffic light color.
     * @return if a car can pass the terrain return true, otherwise return false
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {

        if (theTerrain == Terrain.STREET || (theTerrain == Terrain.CROSSWALK && (theLight == Light.GREEN))) {
            return true;
        }
        return theTerrain == Terrain.LIGHT && (theLight == Light.GREEN || theLight == Light.YELLOW);
    }

    /**
     * Returns the direction this car would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this car would like to move.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {


        // if the terrain in front is a street, crosswalk, or light. Turn in that direction
        if (theNeighbors.get(getDirection()) == Terrain.STREET || (theNeighbors.get(getDirection()) == Terrain.CROSSWALK) || theNeighbors.get(getDirection()) == Terrain.LIGHT) {

            return getDirection();
        }
        // if the terrain to the left is a street, crosswalk, or a light. Turn in that direction.
        if (theNeighbors.get(getDirection().left()) == Terrain.STREET || (theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) || theNeighbors.get(getDirection().left()) == Terrain.LIGHT) {

            return getDirection().left();
        }
        // if the terrain to the right is a street, crosswalk or light. Turn in that direction.
        if (theNeighbors.get(getDirection().right()) == Terrain.STREET || (theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) || theNeighbors.get(getDirection().right()) == Terrain.LIGHT) {

            return getDirection().right();
        }
        // return the reverse of the direction if nothing passes.

        return currentDirection = getDirection().reverse();

    }


}
