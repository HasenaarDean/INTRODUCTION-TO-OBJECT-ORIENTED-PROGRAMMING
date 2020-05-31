
/**
 * This class represents a closed hash set, which inherits from SimpleHashSet.
 * A closed hash set is a data structure in which every cell in the closed hash set has
 * one string maximum, or null if no string was added yet, and if an item is deleted
 * from the cell, it is marked by the string 'The item was deleted'.
 * In addition, the index of each string is determined by the hash function and the clamp formula.
 */
public class ClosedHashSet extends SimpleHashSet {

    private String[] hashList;
    private int numOfItemsInHashList = 0;
    private final static int CLAMPING_PARAMETER = 2;
    private static final String ITEM_WAS_DELETED = "The item was deleted";


    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet(){

        hashList = new String[capacity()];
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        this();
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75), and
     * lower load factor (0.25).
     * @param data Values to add to the set.
     */
    public ClosedHashSet(java.lang.String[] data){
        this();
        for(String val : data){
            add(val);
        }
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set.
     * @return False iff newValue already exists in the set.
     */
    public boolean add(java.lang.String newValue){
        if(contains(newValue))
            return false;
        int validPlace;
        for(int i = 0; i < hashList.length; i++){
            validPlace = clamp(newValue, i);
            if(hashList[validPlace] == null || hashList[validPlace] == ITEM_WAS_DELETED){
                hashList[validPlace] = newValue;
                numOfItemsInHashList += 1;
                doubleHashSize();
                return true;
            }
        }
        return false;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for.
     * @return True iff searchVal is found in the set.
     */
    public boolean contains(java.lang.String searchVal){
        int clampIndex;
        for(int i = 0; i < hashList.length; i++){
            clampIndex = clamp(searchVal, i);
            if(hashList[clampIndex] == null)
                return false;
            if(hashList[clampIndex].equals(searchVal))
                return true;
        }
        return false;
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete.
     * @return True iff toDelete is found and deleted.
     */
    public boolean delete(java.lang.String toDelete){
        if(contains(toDelete)){
            int clampedIndex;
            for(int i = 0; i < hashList.length; i++){
                clampedIndex = clamp(toDelete, i);
                if(hashList[clampedIndex].equals(toDelete)){
                    hashList[clampedIndex] = ITEM_WAS_DELETED;
                    numOfItemsInHashList -= 1;
                    halveHashSize();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method returns the number of elements currently in the set.
     * @return The number of elements currently in the set.
     */
    public int size(){

        return numOfItemsInHashList;

    }

    /**
     * This method calculates the clamped index that the input string gets.
     * The clamped index represents the place in the hash set the input string will be stored in.
     * @param stringToInsert The string we want to insert to the hash set.
     * @param numOfIteration The number of iterations we are now in the clamp formula.
     * @return The valid index in the hash set the input string will be stored in.
     */
    public int clamp(String stringToInsert, int numOfIteration){

        return (stringToInsert.hashCode() + (numOfIteration + numOfIteration * numOfIteration) /
                CLAMPING_PARAMETER)&(capacity()-1);
    }

    /**
     * This method is called every time we add another value to the hash set.
     * This method checks whether or not the hash set's size should be doubled,
     * according to the Upper Load Factor value, and re-hashes the new doubled hash set accordingly.
     * @return true if the hash set's size should be doubled, false otherwise.
     */
    private boolean doubleHashSize(){

        if(((float)size() / capacity()) <= getUpperLoadFactor())
            return false;
        String[] listBeforeRehash = hashList;
        capacityMinusOne = 2 * capacityMinusOne + 1;
        numOfItemsInHashList = 0;
        hashList = new String[capacity()];
        for(String str : listBeforeRehash){
            if(str != null && !(str == ITEM_WAS_DELETED))
                add(str);
        }
        return true;
    }

    /**
     * This method is called every time we delete a value in the hash set.
     * This method checks whether or not the hash set's size should be halved,
     * according to the Lower Load Factor value, and re-hashes the new halved hash set accordingly.
     * @return true if the hash set's size should be halved, false otherwise.
     */
    private boolean halveHashSize(){
        if(((float)size() / capacity()) >= getLowerLoadFactor())
            return false;
        String[] listBeforeRehash = hashList;
        capacityMinusOne = capacityMinusOne / 2;
        numOfItemsInHashList = 0;
        hashList = new String[capacity()];
        for(String str : listBeforeRehash){
            if(str != null && !(str == ITEM_WAS_DELETED))
                add(str);
        }
        return true;
    }
}
