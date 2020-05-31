
/**
 * This class is an abstract class of a simple hash set,
 * which OpenHashSet and ClosedHashSet inherits from.
 * It represents an abstract simple data structure of a hash set.
 */
public abstract class SimpleHashSet implements SimpleSet {

    protected final int INITIAL_CAPACITY = 16;
    protected int capacityMinusOne = INITIAL_CAPACITY - 1;
    protected float upperLoadFactor = 0.75f;
    protected float lowerLoadFactor = 0.25f;

    /**
     *  This method returns the upper load factor of the hash set.
     * @return the upper load factor of the hash set.
     */
    public float getUpperLoadFactor(){

        return upperLoadFactor;
    }

    /**
     *  This method returns the lower load factor of the hash set.
     * @return the lower load factor of the hash set.
     */
    public float getLowerLoadFactor(){

        return lowerLoadFactor;
    }

    /**
     *  This method returns the current capacity of the hash set.
     * @return the current capacity of the hash set.
     */
    public int capacity(){

        return capacityMinusOne + 1;
    }

}
