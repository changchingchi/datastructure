package algo.recursionanddp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chchi on 8/22/17.
 */
public class app {
    public static void main(String[] args) {
//        boolean[][] array = new boolean[2][2];
//
//        for(int i = 0; i < 2;i++){
//            for(int j = 0; j<2; j++){
//                array[i][j]=true;
//            }
//        }
//
//
//        System.out.print(solution.getPath(array));
//
//        getRow(3);


   System.out.println(solution.lexicalOrder(13));
    }


    public static int fib(int n) {
        return fib(n,new int[n+1]);
    }

    private static int fib(int n, int[] memo) {
        if(n==0 || n==1 ) return n;
        if(memo[n]==0){
            memo[n]= fib(n-1,memo)+fib(n-2,memo);
        }
        return memo[n];
    }

    public static List<Integer> getRow(int rowIndex) {
        //dp and return dp[rowIndex][]

//             1
//             11
//             121
//             1331

        int[][] dp = new int[rowIndex+1][rowIndex+1];

        for(int j = 0; j<=rowIndex ; j++){
            dp[0][j] =1;
            for(int i = 1; i<=j ; i++){
                dp[i][j] = dp[i-1][j-1] + dp[i][j-1];
                System.out.println(dp[i][j]);
            }
        }

        List<Integer> result = new ArrayList<>();
        for(int i = 0;i<=rowIndex;i++){
            result.add(dp[rowIndex][i]);
        }
        return result;
    }

}
