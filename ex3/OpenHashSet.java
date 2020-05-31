import java.util.Iterator;

/**
 * This class represents an Open hash set, which inherits from SimpleHashSet.
 * An open hash set is a data structure in which every cell in the Open hash set
 * contains a linked list. In addition, the index of each string is determined by the
 * hash function and the clamp formula.
 */
public class OpenHashSet extends SimpleHashSet {

    private LinkedListArray[] linkedListArray;
    private int numOfItemsInHashList = 0;


    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){

        this();
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;

    }

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16), upper load
     * factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet(){

        newLinkedList();
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75), and
     * lower load factor (0.25).
     * @param data Values to add to the set.
     */
    public OpenHashSet(java.lang.String[] data){

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
        int clampIndex = clamp(newValue);
        linkedListArray[clampIndex].add(newValue);
        numOfItemsInHashList += 1;
        doubleHashSize();
        return true;

    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for.
     * @return True iff searchVal is found in the set.
     */
    public boolean contains(java.lang.String searchVal){
        int clampedIndex = clamp(searchVal);
        return linkedListArray[clampedIndex].contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete.
     * @return True iff toDelete is found and deleted.
     */
    public boolean delete(java.lang.String toDelete){

        if(contains(toDelete)){
            int clampedIndex = clamp(toDelete);
            linkedListArray[clampedIndex].delete(toDelete);
            numOfItemsInHashList -= 1;
            halveHashSize();
            return true;
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
     * @return The valid index in the hash set the input string will be stored in.
     */
    public int clamp(String stringToInsert){

        return (Math.abs(stringToInsert.hashCode()) % capacity());
    }

    /**
     * This method initializes a new linked list.
     */
    private void newLinkedList(){
        linkedListArray = new LinkedListArray[capacity()];
        for(int i = 0; i < linkedListArray.length; i++){
            linkedListArray[i] = new LinkedListArray();
        }
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
        LinkedListArray[] listBeforeRehash = linkedListArray;
        capacityMinusOne = 2 * capacityMinusOne + 1;
        numOfItemsInHashList = 0;
        newLinkedList();
        for(LinkedListArray linkedListArray : listBeforeRehash){
            Iterator<String> hashIterator = linkedListArray.getIterator();
            while(hashIterator.hasNext()){
                add(hashIterator.next());
            }
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
        LinkedListArray[] listBeforeRehash = linkedListArray;
        capacityMinusOne /= 2;
        numOfItemsInHashList = 0;
        newLinkedList();
        for(LinkedListArray linkedListArray : listBeforeRehash){
            Iterator<String> hashIterator = linkedListArray.getIterator();
            while(hashIterator.hasNext()){
                add(hashIterator.next());
        }
    }
            return true;
}

}
