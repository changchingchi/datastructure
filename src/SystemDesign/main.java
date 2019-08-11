package SystemDesign;

public class main {

    public static void main(String[] args) throws InterruptedException {

        /**
         *
         * Consumer and producer example
         *
         * */

        PC pc = new PC(5);

        Thread t1 = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Thread t2 = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
