package SystemDesign;

import java.util.LinkedList;
import java.util.Queue;

public class PC {

    int cap ;
    Queue<Integer> queue = new LinkedList<>();
    PC(int capacity){
        this.cap = capacity;
    }

    void produce() throws InterruptedException {
        int value = 0;
        while(true) {
            synchronized (this) {
                if (queue.size() == cap)
                    wait();

                System.out.println("producing value: " + value);
                queue.add(value++);
                notify();
                Thread.sleep(1000);
            }
        }
    }

    void consume() throws InterruptedException {
        while(true){
            synchronized (this){
                if(queue.size() == 0)
                    wait();

                System.out.println("consuming value: "+ queue.poll());
                notify();
                Thread.sleep(1000);
            }
        }
    }
}
