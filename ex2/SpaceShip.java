import java.awt.Image;
import oop.ex2.*;
import java.lang.Math;


/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */

public abstract class SpaceShip{

    /**
     * This class is an abstract class of a generic space ship.
     * All types of space ships inherits from this class and use its constructor.
     */

    private SpaceShipPhysics physics;
    private boolean shield_Status;
    private int currentCoolDown;
    private int currentHealth;
    private int currentEnergy;
    private int maxEnergy;
    private static final int TELEPORT_ENERGY_COST = 100;
    private static final int SHIELD_ENERGY_COST = 3;
    private static final int FIRE_ENERGY_COST = 15;
    private static final int INITIAL_MAX_ENERGY = 250;
    private static final int INITIAL_HEALTH = 25;
    private static final int INITIAL_CURRENT_ENERGY = 200;
    private static final int BASHING_MAX_ENERGY_BONUS = 20;
    private static final int BASHING_CURRENT_ENERGY_PUNISHMENT = 15;
    private static final int GETTING_HIT_WHILE_UNSHIELDED_CURRENT_ENERGY_PUNISHMENT = 10;
    private static final int GETTING_HIT_WHILE_UNSHIELDED_MAX_ENERGY_PUNISHMENT = 5;
    private static final int COOL_DOWN_AFTER_A_SHOT = 7;
    private static final int CURRENT_ENERGY_PUNISHMENT_FOR_A_SHOT_SHIELDED_SHIP = 2;
    private static final int PUNISHMENT_FOR_BEING_SHOT_OR_COLLIDED_WHILE_UNSHIELDED = 1;
    static final int RIGHT_TURN = -1;
    static final int NO_TURN = 0;
    static final int LEFT_TURN = 1;


    /**
     * This method is the Space Ship constructor.
     */
    public SpaceShip(){

        reset();

    }

    /**
     * This abstract method checks whether or not the space ship should teleport this round.
     * @param game a SpaceWars game.
     * @return true if space ship should teleport, false otherwise.
     */

    abstract public boolean teleportStatus(SpaceWars game);


    /**
     * This abstract method checks whether or not the space ship should accelerate this round.
     * @param game a SpaceWars game.
     * @return true if space ship should accelerate, false otherwise.
     */

    abstract public boolean accelerateStatus(SpaceWars game);


    /**
     * This abstract method checks which turn the space ship should turn this round.
     * @param game a SpaceWars game.
     * @return 1 if the spaces ship should turn left, 0 if the space ship should not turn,
     * and -1 if the space ship should turn right.
     */

    abstract public int turnStatus(SpaceWars game);


    /**
     * This abstract method checks whether or not the space ship should use its shields
     * this round.
     * @param game a SpaceWars game.
     * @return true if the space ship should use its shields this round,  false otherwise.
     */

    abstract public boolean shieldsStatus(SpaceWars game);


    /**
     * This abstract method checks whether or not the space ship should shoot this round.
     * @param game a SpaceWars game.
     * @return true if the space ship should shoot this round, false otherwise.
     */

    abstract public boolean shootStatus(SpaceWars game);


    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * @param game a SpaceWars game.
     */

    public void doAction(SpaceWars game) {

        if(teleportStatus(game))
            teleport();
        physics.move(accelerateStatus(game), turnStatus(game));
        shield_Status = false;

        if(shieldsStatus(game))
            shieldOn();

        if(shootStatus(game))
            fire(game);

        coolDownUpdate();
        chargeEnergy();

    }


    /**
     * This method updates the cool down counter of the space ship.
     */

    private void coolDownUpdate(){
        if(currentCoolDown > 0)
            currentCoolDown -= 1;
    }


    /**
     * This method charges the energy for the space ship according to its current velocity
     * and its maximum velocity.
     */

    private void chargeEnergy(){

        double chargeRatio = physics.getVelocity() / physics.getMaxVelocity();
        int roundedChargeRatio = (int)chargeRatio;

        int chargeAmount = 1 + (2 * roundedChargeRatio);
        currentEnergy += chargeAmount;
        if(currentEnergy > maxEnergy)
            currentEnergy = maxEnergy;
    }


    /**
     * This method is called every time a collision with this ship occurs 
     */

    public void collidedWithAnotherShip(){
        if(shield_Status){
            maxEnergy += BASHING_MAX_ENERGY_BONUS;
            currentEnergy -= BASHING_CURRENT_ENERGY_PUNISHMENT;
            currentEnergy = Math.max(0, currentEnergy);
        } else {
            reduceEnergyAndHealthForUnshieldedSpaceShip();
        }
    }

    /**
     * This method reduces energy and health for an unshielded space ship.
     */
    private void reduceEnergyAndHealthForUnshieldedSpaceShip(){
        currentHealth -= PUNISHMENT_FOR_BEING_SHOT_OR_COLLIDED_WHILE_UNSHIELDED;
        currentHealth = Math.max(0, currentHealth);
        currentEnergy -= GETTING_HIT_WHILE_UNSHIELDED_CURRENT_ENERGY_PUNISHMENT;
        currentEnergy = Math.max(0, currentEnergy);
        maxEnergy -= GETTING_HIT_WHILE_UNSHIELDED_MAX_ENERGY_PUNISHMENT;
        maxEnergy = Math.max(0, maxEnergy);


    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){

        physics = new SpaceShipPhysics();
        currentHealth = INITIAL_HEALTH;
        maxEnergy = INITIAL_MAX_ENERGY;
        currentEnergy = INITIAL_CURRENT_ENERGY;
        currentCoolDown = 0;
        shield_Status = false;
    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return currentHealth <= 0;
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {

        return physics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets shot at (with or without a shield).
     */
    public void gotShot() {
        if(!shield_Status){
            reduceEnergyAndHealthForUnshieldedSpaceShip();

        } else {
            currentEnergy -= CURRENT_ENERGY_PUNISHMENT_FOR_A_SHOT_SHIELDED_SHIP;
            currentEnergy = Math.max(0, currentEnergy);
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage(){
        if(shield_Status){
            return getShieldedImage();
        } else {
            return getUnshieldedImage();
        }
    }

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
       if(currentEnergy >= FIRE_ENERGY_COST && currentCoolDown == 0){
           game.addShot(physics);
           currentEnergy -= FIRE_ENERGY_COST;
           currentCoolDown = COOL_DOWN_AFTER_A_SHOT;
       }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn(){
        if(currentEnergy >= SHIELD_ENERGY_COST){
            shield_Status = true;
            currentEnergy -= SHIELD_ENERGY_COST;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
       if(currentEnergy >= TELEPORT_ENERGY_COST){
           physics = new SpaceShipPhysics();
           currentEnergy -= TELEPORT_ENERGY_COST;
       }
    }

    /**
     * This method returns the space ship's image while it is unshielded.
     * @return an image of the unshielded space ship.
     */
    public Image getUnshieldedImage(){

        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }

    /**
     * This method returns the space ship's image while it is shielded.
     * @return an image of the shielded space ship.
     */
    public Image getShieldedImage(){

        return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
    }
}
