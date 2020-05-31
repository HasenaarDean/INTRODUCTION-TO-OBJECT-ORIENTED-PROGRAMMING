import java.util.Random;


public class DrunkardSpaceShip extends SpaceShip {

    /**
     * This class represents the Drunkard Space Ship which always acts randomly, and
     * it seems like the pilot of this space ship had a tad too much to drink.
     */

    private static final int[] turnIntsArray = {RIGHT_TURN, NO_TURN, LEFT_TURN};

    private static final boolean[] boolArray = {true, true, false, false, false, false, false};


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
     * This method returns a random int, 1, 0 or -1, from the turn ints array.
     * 1 represents a left turn, 0 represents no turn, and -1 represents right turn.
     * @return randomly returns 1, 0 or -1 from the turn ints array.
     */

    private int randomInt(){
        Random randomGenerator = new Random();
        int randomIndex = randomGenerator.nextInt(turnIntsArray.length);
        return turnIntsArray[randomIndex];
    }


    /**
     * This method checks whether or not the space ship should teleport this round.
     * @param game a SpaceWars game.
     * @return true if space ship should teleport, false otherwise.
     */

    public boolean teleportStatus(SpaceWars game){

        return randomBool();
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

        return randomBool();
    }


    /**
     * This method checks whether or not the space ship should shoot this round.
     * @param game a SpaceWars game.
     * @return true if the space ship should shoot this round, false otherwise.
     */

    public boolean shootStatus(SpaceWars game){

        return randomBool();
    }


    /**
     * This method checks which turn the space ship should turn this round.
     * @param game a SpaceWars game.
     * @return 1 if the spaces ship should turn left, 0 if the space ship should not turn,
     * and -1 if the space ship should turn right.
     */

    public int turnStatus(SpaceWars game){

        return randomInt();
    }
}
