package hashAndHeap;

import java.util.*;

/**
 * Impelement a data structure provide two interfaces,
 * <p>
 * add(number) : Add a number to the data structure
 * <p>
 * topk(): return the top k largest elements in the data structure in list
 * <p>
 * e.g.
 * a.add(3)
 * a.add(10)
 * a.topk() --> return [10,3]
 * a.add(1000)
 * a.add(-99)
 * a.topk() --> return [1000,10,3]
 * a.add(4)
 * a.topk() --> return [1000,10,4]
 */
public class TopKLargestNumbers {
//versions 1: space O(n)
//    int k;
//    Queue<Integer> queue;
//    List<Integer> result;
//
//    public TopKLargestNumbers(int k) {
//        this.k = k;
//        queue = new PriorityQueue<>();
//
//    }
//
//    /**
//     * number to be added
//     **/
//    public void add(int num) {
//        queue.offer(num);   //logn
//    }
//
//    /**
//     * return the top k elements in list
//     */
//    public List<Integer> topk() { //
//        int counter = 0;
//        result = new ArrayList<>();
//        while (!queue.isEmpty() && counter < k) {
//            result.add(queue.poll());
//            counter++;
//        }
//
//        for (Integer i : result) {
//            queue.offer(i);
//        }
//        return result;
//    }


    //version 2 : space O(k)
        int k;
    Queue<Integer> minHeap;
    List<Integer> result;

    public TopKLargestNumbers(int k) {
        this.k = k;
        minHeap = new PriorityQueue<>();
    }




    /**
     * number to be added
     **/
    public void add(int num) {
        //只保留最大的三個 其中又把新的跟minHeap裡面的頂：最小值相比 比較大就踢掉
        if(minHeap.size()<k){
            minHeap.add(num);
            return;
        }
        if(num>minHeap.peek()){
            minHeap.poll();
            minHeap.offer(num);
        }
    }

    /**
     * return the top k elements in list
     */
    public List<Integer> topk() {
        result = new ArrayList<>();
        Iterator iterator = minHeap.iterator();
        while(iterator.hasNext()){
            result.add((Integer) iterator.next()); //O(k)
        }
        Collections.sort(result,Collections.reverseOrder());  // Logk
        return result;
    }
}
