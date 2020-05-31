import oop.ex2.GameGUI;
import java.awt.*;



public class HumanSpaceShip extends SpaceShip{
    // This class represents the Human Space Ship which is controlled by the user.


    /**
     * This method checks whether or not the space ship should teleport this round.
     * @param game a SpaceWars game.
     * @return true if space ship should teleport, false otherwise.
     */
    public boolean teleportStatus(SpaceWars game){

        return game.getGUI().isTeleportPressed();
    }


    /**
     * This method checks whether or not the space ship should accelerate this round.
     * @param game a SpaceWars game.
     * @return true if space ship should accelerate, false otherwise.
     */

    public boolean accelerateStatus(SpaceWars game){

        return game.getGUI().isUpPressed();
    }


    /**
     * This method checks which turn the space ship should turn this round.
     * @param game a SpaceWars game.
     * @return 1 if the spaces ship should turn left, 0 if the space ship should not turn,
     * and -1 if the space ship should turn right.
     */

    public int turnStatus(SpaceWars game){
        int turnNum = NO_TURN;
        if(game.getGUI().isRightPressed())
            turnNum = RIGHT_TURN;
        if(game.getGUI().isLeftPressed())
            turnNum = LEFT_TURN;
        return turnNum;
    }


    /**
     * This method checks whether or not the space ship should use its shields
     * this round.
     * @param game a SpaceWars game.
     * @return true if the space ship should use its shields this round,  false otherwise.
     */

    public boolean shieldsStatus(SpaceWars game){

        return game.getGUI().isShieldsPressed();
    }


    /**
     * This method checks whether or not the space ship should shoot this round.
     * @param game a SpaceWars game.
     * @return true if the space ship should shoot this round, false otherwise.
     */

    public boolean shootStatus(SpaceWars game){

        return game.getGUI().isShotPressed();
    }


    /**
     * This method returns the human space ship's image while it is unshielded.
     * @return an image of the unshielded human space ship.
     */

    public Image getUnshieldedImage(){

        return GameGUI.SPACESHIP_IMAGE;
    }


    /**
     * This method returns the human space ship's image while it is shielded.
     * @return an image of the shielded human space ship.
     */

    public Image getShieldedImage(){

        return GameGUI.SPACESHIP_IMAGE_SHIELD;
    }

}
