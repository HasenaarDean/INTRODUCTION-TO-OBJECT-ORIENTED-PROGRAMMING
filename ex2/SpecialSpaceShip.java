import java.util.Random;


public class SpecialSpaceShip extends SpaceShip {

    /**
     * This class represents the Special Space Ship which has the
     * attributes of all the other space ship types, except the human type.
     * This space ship is considered very strong one, due to its special behaviour
     * of using the attributes of almost all the other space ship types.
     */

    private static final double MINIMAL_DISTANCE_FROM_CLOSEST_SHIP = 0.25;
    private static final double MINIMAL_ANGLE_TO_CLOSEST_SHIP = 0.23;
    private static final double MINIMAL_SHIELDS_DISTANCE = 0.19;
    private static final double MINIMAL_FIRING_ANGLE = 0.21;
    private static final boolean[] boolArray = {true, true, false, false, false, false, false};


    /**
     * This method checks whether or not the space ship is in a safe position in relation to the
     * closest space ship. a position is not safe when the nearest space ship is closer than 0.25
     * units, and if its angle to the ship is less than 0.23 radians.
     * @param game a SpaceWars game.
     * @return true if the position is safe, false otherwise.
     */

    private boolean isPositionSafe(SpaceWars game){

        SpaceShip closestSpaceShip = game.getClosestShipTo(this);
        double closestSpaceShipAngle = closestSpaceShip.getPhysics().angleTo(this.getPhysics());
        if(closestSpaceShipAngle < 0)
            closestSpaceShipAngle *= -1;
        double closestSpaceShipDistance = closestSpaceShip.getPhysics().distanceFrom(this.getPhysics());

        return closestSpaceShipAngle >= MINIMAL_ANGLE_TO_CLOSEST_SHIP ||
                closestSpaceShipDistance >= MINIMAL_DISTANCE_FROM_CLOSEST_SHIP;
    }


    /**
     * This method checks whether or not the space ship should teleport this round.
     * @param game a SpaceWars game.
     * @return true if space ship should teleport, false otherwise.
     */

    public boolean teleportStatus(SpaceWars game){

        return isPositionSafe(game);

    }


    /**
     * This method returns a random boolean, true or false, from the booleans array.
     * @return randomly returns true or false from the booleans array.
     */

    private boolean randomBool(){
        Random randomGenerator = new Random();
        int randomIndex = randomGenerator.nextInt(boolArray.length);
        return boolArray[randomIndex];
    }


    /**
     * This method checks whether or not the space ship should accelerate this round.
     * @param game a SpaceWars game.
     * @return true if space ship should accelerate, false otherwise.
     */

    public boolean accelerateStatus(SpaceWars game){

        return randomBool();
    }


    /**
     * This method checks whether or not the space ship should use its shields
     * this round.
     * @param game a SpaceWars game.
     * @return true if the space ship should use its shields this round,  false otherwise.
     */

    public boolean shieldsStatus(SpaceWars game){

        SpaceShip closestSpaceShip = game.getClosestShipTo(this);
        double closestSpaceShipDistance = closestSpaceShip.getPhysics().distanceFrom(this.getPhysics());
        return closestSpaceShipDistance <= MINIMAL_SHIELDS_DISTANCE;
    }


    /**
     * This method checks whether or not the space ship should shoot this round.
     * @param game a SpaceWars game.
     * @return true if the space ship should shoot this round, false otherwise.
     */

    public boolean shootStatus(SpaceWars game){

        SpaceShip closestSpaceShip = game.getClosestShipTo(this);
        double angleToClosestSpaceShip = getPhysics().angleTo(closestSpaceShip.getPhysics());
        if(angleToClosestSpaceShip < 0)
            angleToClosestSpaceShip *= -1;
        return angleToClosestSpaceShip < MINIMAL_FIRING_ANGLE;

    }


    /**
     * This method checks which turn the space ship should turn this round.
     * @param game a SpaceWars game.
     * @return 1 if the spaces ship should turn left, 0 if the space ship should not turn,
     * and -1 if the space ship should turn right.
     */

    public int turnStatus(SpaceWars game){

        SpaceShip closestSpaceShip = game.getClosestShipTo(this);
        double angleToClosestSpaceShip = getPhysics().angleTo(closestSpaceShip.getPhysics());
        if(angleToClosestSpaceShip > 0)
            return LEFT_TURN;
        else if(angleToClosestSpaceShip == 0){
            return NO_TURN;
        } else {
            return RIGHT_TURN;
        }

    }

}
