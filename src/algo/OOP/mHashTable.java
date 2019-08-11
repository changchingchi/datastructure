package algo.OOP;

import javax.jws.Oneway;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by chchi on 8/25/17.
 */
public class mHashTable {
    ArrayList<mDoubleLinkedList> mHashTable;
    int size;

    mHashTable(int size) {
        this.size = size;
        mHashTable = new ArrayList<>();
        mHashTable.ensureCapacity(size);
        for(int i =0;i<size;i++){
            mHashTable.add(null);
        }
    }

    public void add(String key, Object data) {
        //2 cases to handle.

        //1. add with same key (jim) and we want to update the value of jim only.
        mDoubleLinkedList node = getNodeForKey(key);
        if (node != null) {
            //exist and we want to update the value with same key
            node.data = data;
            return;
        }

        //2. add different keys and we want to check if node is there,
        // if so, we insert at front,
        // if not, we create and set.
        int mKey = getIndexForKey(key);
        //not exist. its a new node.
        mDoubleLinkedList newData = new mDoubleLinkedList(key, data);
        if (mHashTable.get(mKey) != null) {
            //exist, we insert into.
            newData.next = mHashTable.get(mKey);
            newData.next.prev = newData;
        }
        mHashTable.set(mKey, newData);
        System.out.println(mHashTable.toArray());
    }

    private int getIndexForKey(String key) {
        return Math.abs(key.hashCode()) % size;
    }

    // in the same list, there might be different key but same index. e.g. jim and bob will have same index.
    private mDoubleLinkedList getNodeForKey(String key) {
        int index = getIndexForKey(key);
        mDoubleLinkedList node = mHashTable.get(index);
        while (node != null) {
            if (node.key == key) {
                return node;
            }
            node = node.next;
        }
        return null;
    }
}

class mDoubleLinkedList {

    mDoubleLinkedList prev;
    mDoubleLinkedList next;
    Object data;
    String key;

    public mDoubleLinkedList(String key, Object data) {
        this.data = data;
        this.key = key;
    }

}
