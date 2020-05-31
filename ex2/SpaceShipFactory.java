

public class SpaceShipFactory {

    //This class represents a Space Ship Factory.


    /**
     * This method gets a strings arguments array and returns an array of
     * all the space ships that should play in the game. "h" means human
     * space ship, "r" is runner, "b" is basher, "a" is aggressive, "d"
     * is drunkard, and "s" is special space ship.
     * @param args a strings arguments array.
     * @return an array of all the space ships that should play in the game,
     * according to the input strings (see above).
     */

    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] spaceShips = new SpaceShip[args.length];
        for(int i = 0; i < args.length; i++){
            if (args[i].equals("h"))
                spaceShips[i] = new HumanSpaceShip();
            if (args[i].equals("r"))
                spaceShips[i] = new RunnerSpaceShip();
            if (args[i].equals("b"))
                spaceShips[i] = new BasherSpaceShip();
            if (args[i].equals("a"))
                spaceShips[i] = new AggressiveSpaceShip();
            if (args[i].equals("d"))
                spaceShips[i] = new DrunkardSpaceShip();
            if (args[i].equals("s"))
                spaceShips[i] = new SpecialSpaceShip();
        }

        return spaceShips;
    }
}
