package test;

//this is a worker class
public class ClassA {

    public void onFoundGirl(onFoundListener mOnFoundListener) {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.print("running...."+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //when worker class (this) finishes work, call the interface class callback.
        mOnFoundListener.onFound("found a girl.");
    }
}
