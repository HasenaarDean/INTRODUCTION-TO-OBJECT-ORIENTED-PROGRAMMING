

public class BasherSpaceShip extends SpaceShip {

    /**
     * This class represents the Basher Space Ship which always tries to
     * bash the closest space ship in the game.
     */

    private static final double MINIMAL_SHIELDS_DISTANCE = 0.19;

    /**
     * This method checks whether or not the space ship should teleport this round.
     * @param game a SpaceWars game.
     * @return true if space ship should teleport, false otherwise.
     */

    public boolean teleportStatus(SpaceWars game){

        return false;
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
     * This method checks whether or not the space ship should accelerate this round.
     * @param game a SpaceWars game.
     * @return true if space ship should accelerate, false otherwise.
     */

    public boolean accelerateStatus(SpaceWars game){

        return true;
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


    /**
     * This method checks whether or not the space ship should shoot this round.
     * @param game a SpaceWars game.
     * @return true if the space ship should shoot this round, false otherwise.
     */

    public boolean shootStatus(SpaceWars game){

        return false;
    }

}
