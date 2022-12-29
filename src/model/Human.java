package model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class Human extends AbstractVehicle  {

    /**
     * how long the Human will be dead for
     */

    private static final int DEATH_TIME = 45;
    /**
     * the current direction of the Human
     */
    private Direction currentDirection;
    /**
     * Random object to choose random directions
     */

    private Random random;

    //private Terrain initialTerrain;


    /**
     * create a Human vehicle with the provided x and y coordinates, and direction
     *
     * @param theX   x coordinate of a Human
     * @param theY   y coordinate of a Human
     * @param theDir direction of a Human
     */
    public Human(int theX, int theY, Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME);
        this.currentDirection = theDir;
        this.random = getRandom();
    }

    /**
     * Returns whether this Human may move onto the given type of
     * terrain, when the streetlights are the given color.
     *
     * @param theTerrain The terrain.
     * @param theLight   The light color.
     * @return whether this Human may move onto the given type of
     * terrain when the streetlights are the given color.
     */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {


        if (theTerrain == Terrain.CROSSWALK && (theLight == Light.YELLOW || theLight == Light.RED)) {

            return true;
        }
        // return true if the terrain is grass. False otherwise.
        return theTerrain == Terrain.GRASS;
    }

    /**
     * Returns the direction this Human would like to move, based on the given
     * map of the neighboring terrain.
     *
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this Human would like to move.
     */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {

        // valid directions for a Human
        ArrayList<Direction> validDirections = new ArrayList<>();

        // to pick a random element out of the validDirections ArrayList
        final int randomElement;

        // if the terrain to the left is grass or there is a crosswalk
        if (theNeighbors.get(getDirection().left()) == Terrain.GRASS || (theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK)) {
            validDirections.add(getDirection().left());
        }
        // if the terrain to the right is grass or there is a crosswalk
        if (theNeighbors.get(getDirection().right()) == Terrain.GRASS || ((theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK))) {
            validDirections.add(getDirection().right());
        }
        // if the terrain in front is grass or there is a crosswalk
        if (theNeighbors.get(getDirection()) == Terrain.GRASS || (theNeighbors.get(getDirection()) == Terrain.CROSSWALK)) {
            validDirections.add(getDirection());
        }
        // if there are no valid directions. Reverse the direction
        if (validDirections.isEmpty()) {
            currentDirection = getDirection().reverse();
        } else {
            // if there are valid directions then pick a random direction
            randomElement = random.nextInt(validDirections.size());
            currentDirection = validDirections.get(randomElement);
        }
        return currentDirection;


    }


}
