package algo.mathandlogicpuzzle;

import java.util.Arrays;

/**
 * Created by chchi on 8/22/17.
 */
public class app {
    public static void main(String[] args) {
        sieveOfEratorsthenes(10);
    }

    /**
     * @param max find prime from 1 to max
     *
     * @return a boolean array indicates prime when true. (otherwise false)
     * */
    public static boolean[] sieveOfEratorsthenes(int max) {
        boolean[] result = new boolean[max+1];
        int prime = 2; //starting prime.
        Arrays.fill(result,true);
        while(prime <=Math.sqrt(max)){

            //cross off multiple of current prime.
            for(int i = prime*prime; i<result.length; i+=prime){
                //i+=prime is counting the multiple of current prime.
                //and we set it as false.
                result[i] =false;
            }

            int next = prime+1;
            while(next<result.length && !result[next]){
                next++;
            }
            prime = next;
        }
        return result;
    }

}
