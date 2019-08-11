package hashAndHeap;


import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 3 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4

//        TopKLargestNumbers topk = new TopKLargestNumbers(3);
//        topk.add(3);
//        topk.add(10);
//        System.out.println(Arrays.toString(topk.topk().toArray()));
//        topk.add(1000);
//        topk.add(-99);
//        System.out.println(Arrays.toString(topk.topk().toArray()));
//        topk.add(4);
//        System.out.println(Arrays.toString(topk.topk().toArray()));

    }
}
