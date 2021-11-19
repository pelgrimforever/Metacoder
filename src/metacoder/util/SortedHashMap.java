package metacoder.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * HashMap extention
 * contains a sorted ArrayList with same order as input (FIFO)
 * @author Franky Laseure
 */
public class SortedHashMap extends HashMap {
	
    private ArrayList sortedlist = new ArrayList();

    /**
     * constructor
     * set array size
     * @param maxsize ArrayList size
     */
    public SortedHashMap(int maxsize) {
        super();
        for(int i=0; i<maxsize; i++) sortedlist.add(null);
    }

    /**
     * add element to HashMap
     * add element in position sequence
     * @param key HashMap key
     * @param value HashMap value
     * @param sequence array sequence number
     */
    public void put(Object key, Object value, int sequence) {
        super.put(key, value);
        sortedlist.set(sequence, value);
    }

    /**
     * @return sorted ArrayList
     */
    public ArrayList getSortedArrayList() {
        return sortedlist;
    }
}
