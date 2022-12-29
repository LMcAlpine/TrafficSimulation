package model;

import java.util.Random;

/**
 * AbstractVehicle implements a Vehicle interface and provides the default behavior for all the vehicle child classes
 */
abstract class AbstractVehicle implements Vehicle {

    /**
     * the x coordinate of a vehicle
     */
    private int theX;
    /**
     * the y coordinate of a vehicle
     */
    private int theY;


    /**
     * the direction of a vehicle
     */

    private Direction theDir;

    /**
     * how long a vehicle will stay dead
     */
    private final int deathTime;
    /**
     * /Random object to choose random directions
     */
    private final Random random;
    /**
     * if a vehicle is dead or alive
     */
    private boolean isAlive;
    /**
     * the number of pokes on a vehicle
     */
    private int numberOfPokes;


    /**
     * the initial X coordinate of a vehicle
     */
    private  final int initialX;
    /**
     * the initial Y coordinate of a vehicle
     */
    private  final int initialY;
    /**
     * the initial direction of a vehicle
     */
    private final Direction initialDirection;

    /**
     * create a vehicle with the given x and y coordinates, the direction and the time a vehicle will be dead
     *
     * @param theX      x coordinate for a vehicle
     * @param theY      y coordinate for a vehicle
     * @param theDir    the direction a vehicle travels
     * @param deathTime how long a vehicle will be dead
     */
    protected AbstractVehicle(int theX, int theY, Direction theDir, int deathTime) {

        this.theX = theX;
        this.theY = theY;
        this.theDir = theDir;
        this.deathTime = deathTime;
        this.random = new Random();

        initialX = theX;
        initialY = theY;
        initialDirection = theDir;

        isAlive = true;
    }

    /**
     * get the Random object to be used in the child classes
     *
     * @return the Random object
     */
    public Random getRandom() {
        return random;
    }

    /**
     * get the x coordinate of a vehicle
     *
     * @return the x coordinate of a vehicle
     */
    @Override
    public int getX() {
        return this.theX;
    }

    /**
     * get the y coordinate of a vehicle
     *
     * @return the y coordinate of a vehicle
     */
    @Override
    public int getY() {
        return this.theY;
    }

    /**
     * logic for if a vehicle has collided with another vehicle
     *
     * @param theOther The other vehicle.
     */
    @Override
    public void collide(Vehicle theOther) {
        if (isAlive() && theOther.isAlive() && (deathTime > theOther.getDeathTime())) {
            isAlive = false;
        } else if (isAlive() && theOther.isAlive() && (deathTime < theOther.getDeathTime()) || isAlive() && theOther.isAlive() && (deathTime == theOther.getDeathTime())) {
            isAlive = true;
        }
    }

    /**
     * set the vehicle to a new x coordinate
     *
     * @param theX The new x-coordinate.
     */
    @Override
    public void setX(int theX) {
        this.theX = theX;

    }

    /**
     * set the vehicle to a new y coordinate
     *
     * @param theY The new y-coordinate.
     */
    @Override
    public void setY(int theY) {
        this.theY = theY;
    }

    /**
     * Returns the number of updates between this vehicle's death and when it
     * should be revived.
     *
     * @return the number of updates.
     */
    @Override
    public int getDeathTime() {
        return this.deathTime;
    }


    /**
     * Returns this vehicle's direction.
     *
     * @return the direction a vehicle is facing.
     */
    @Override
    public Direction getDirection() {
        return this.theDir;
    }


    /**
     * Returns whether this Vehicle object is alive and should move on the map.
     *
     * @return true if the object is alive, false otherwise.
     */
    @Override
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Called by the UI to notify a dead vehicle that 1 movement round has
     * passed, so that it will become 1 move closer to revival.
     */
    @Override
    public void poke() {
        if (!isAlive) {
            if (numberOfPokes != deathTime) {
                if (numberOfPokes < deathTime) {
                    numberOfPokes += 1;
                }
            } else {
                isAlive = true;
                setDirection(Direction.random());
                numberOfPokes = 0;

            }
        }

    }

    /**
     * Moves this vehicle back to its original position.
     */
    @Override
    public void reset() {
        setX(initialX);
        setY(initialY);
        setDirection(initialDirection);
        isAlive = true;
        numberOfPokes = 0;

    }

    /**
     * Sets this object's facing direction to the given value.
     *
     * @param theDir The new direction.
     */
    @Override
    public void setDirection(Direction theDir) {
        this.theDir = theDir;


    }

    /**
     * gets the name of a file so the images can be displayed in another method
     *
     * @return the name of the file
     */
    @Override
    public String getImageFileName() {
        if (!isAlive) {
            return getClass().getSimpleName().toLowerCase() + "_dead.gif";
        }
        return toString();
    }

    /**
     * @return a string representation of the vehicle image
     */
    @Override
    public String toString(){
        return getClass().getSimpleName().toLowerCase() + ".gif";
    }


}
