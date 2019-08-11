package test;

import java.util.UUID;
import java.util.concurrent.*;

public class main {


    //callback pattern 練習
    //ClassA 包含一個callback function.
    //首先建立classA worker class 並且定義interface
    //在worker class 裡面處理事件，當事件完成調用interface的自定義callback function
    //在主程式中，創建一個classA的物件，定呼叫且傳入一個listener去接收callback完成的事件
    public static void main(String[] args) throws InterruptedException {
        ClassA a = new ClassA();
        a.onFoundGirl(new onFoundListener() {
            @Override
            public void onFound(String result) {
                System.out.println(result);
            }
        });

        System.out.println("calling someLongAsyncOperation ...");
        Future<MyAnswer> future = new main().someLongAsyncOperation();
        System.out.println("calling someLongAsyncOperation done.");

        // do something else

        System.out.println("wait for answer ...5 sec");

//        Thread.sleep(5000);
        MyAnswer myAnswer = null;
        try {
            myAnswer = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.printf("wait for answer done. Answer is: %s", myAnswer.value);

        executorService.shutdown();
    }


    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    public Future<MyAnswer> someLongAsyncOperation(){

        Future<MyAnswer> future = executorService.submit(() -> {
            Thread.sleep(3000);
            return new MyAnswer(UUID.randomUUID().toString());
        });

        return future;
    }
    static class MyAnswer {
        final String value;

        MyAnswer(String value) {
            this.value = value;
        }
    }
}