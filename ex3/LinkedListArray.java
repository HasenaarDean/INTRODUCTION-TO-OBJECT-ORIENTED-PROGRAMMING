import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class represents a linked list data structure.
 * We use this class in order to create an array of linked lists.
 */
public class LinkedListArray {

    private LinkedList<String> linkedList1 = new LinkedList<String>();

    /**
     * Add a specified element to the linked list if it's not already in it.
     * @param addString New value to add to the set.
     * @return False iff newValue already exists in the set.
     */
    public void add(String addString){

        linkedList1.add(addString);
    }

    /**
     * Look for a specified value in the set.
     * @param searchString Value to search for.
     * @return True iff searchVal is found in the set.
     */
    public boolean contains(String searchString){

        return linkedList1.contains(searchString);
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public void delete(String toDelete){


        linkedList1.remove(toDelete);

    }

    /**
     * This method returns an iterator for the linked list.
     * @return an iterator for the linked list.
     */
    public Iterator<String> getIterator(){

        return linkedList1.iterator();
    }

}
