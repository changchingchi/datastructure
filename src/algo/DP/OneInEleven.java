package algo.DP;

import java.util.Arrays;

public class OneInEleven {

    public final static void main(String[] args) {
        //how many ones in eleven to the power of n ? 11^n? (n is very big)
        int n = Integer.parseInt(args[0]);
        int[][] dp = new int[n + 1][n + 1];

        dp[0][0] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        System.out.println(Arrays.toString(dp[n]));

        //update carry and sum for last row only.

        for (int k = n - 1; k >= 0; k--) {
            int sum = dp[n][k];
            if (sum >= 10) {

                dp[n][k] = sum % 10; //個位
                int carry = sum / 10;
                dp[n][k - 1] += carry;   //進位
                sum = sum / 10;
            }
        }
        int count = 0;
        for (int i : dp[n]) {
            if (i == 1) {
                count++;
            }
        }
        System.out.println(Arrays.toString(dp[n]));
        System.out.println("Answer is ==> " + count);
    }
}
