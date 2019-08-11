package algo.DP;

import javafx.util.Pair;

import java.util.*;

public class Solutions {
    int min = Integer.MAX_VALUE;
    int h;
    Map<String, Integer> hash;

    //https://www.jiuzhang.com/solutions/triangle/
    public int minimumTotal(List<List<Integer>> triangle) {
        //方法一:DFS  便利每個節點 O(n^2)

//        if (triangle == null) return -1;
//        h = triangle.size();
//        traverse(0,0,0, triangle);
//        return min;
//
//
//        //方法2: divide and conquer
//        if (triangle == null) return -1;
//        h = triangle.size();
//        return devideAndConquer(0,0,triangle);

//        //方法3: divide and conquer + memorization(hash)
//        if (triangle == null) return -1;
//        h = triangle.size();
//        hash = new HashMap<>();
//        return devideAndConquerMemorization(0, 0, triangle);

        //方法四 DP top down
        if (triangle == null) return -1;
        int row = triangle.size();
        int col = row;
        int[][] sum = new int[row][col];

        sum[0][0] = triangle.get(0).get(0);
        //初始化三角形左右 因為沒有鄰居
        for (int i = 1; i < row; i++) {
            sum[i][0] = triangle.get(i).get(0) + sum[i - 1][0];
        }
        //畫圖出來就懂了
        for (int i = 1; i < col; i++) {
            sum[i][i] = triangle.get(i).get(i) + sum[i - 1][i - 1];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < i; j++) {
                sum[i][j] = Math.min(sum[i - 1][j], sum[i - 1][j - 1]) + triangle.get(i).get(j);
            }
        }
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < col; i++) {
            if (sum[row - 1][i] < result) {
                result = sum[row - 1][i];
            }
        }
        return result;
    }

    private int devideAndConquerMemorization(int x, int y, List<List<Integer>> triangle) {
        //終止條件
        if (x == h) {
            return 0;
        }

        if (hash.containsKey(x + "" + y + "")) {
            return hash.get(x + "" + y + "");
        }

        //讓左小弟和右小弟分別找值，然後大哥找最小值

        //[
        //    [2],          [2]
        //   [3,4],  ==>   [5,
        //  [6,5,7],
        // [4,1,8,3]
        //]
        int curSum = triangle.get(x).get(y) + Math.min(devideAndConquerMemorization(x + 1, y, triangle)
                , devideAndConquerMemorization(x + 1, y + 1, triangle));
        hash.put(x + "" + y + "", curSum);
        return curSum;
    }


    private int devideAndConquer(int x, int y, List<List<Integer>> triangle) {
        //終止條件
        if (x == h) {
            return 0;
        }

        //讓左小弟和右小弟分別找值，然後大哥找最小值
        return triangle.get(x).get(y) + Math.min(devideAndConquer(x + 1, y, triangle)
                , devideAndConquer(x + 1, y + 1, triangle));
    }

    private void traverse(int x, int y, int sum, List<List<Integer>> triangle) {
        //遞歸的出口
        if (x == h) {
            //比較誰小
            if (sum < min) {
                min = sum;
            }
            return;
        }
        traverse(x + 1, y, sum + triangle.get(x).get(y), triangle);
        traverse(x + 1, y + 1, sum + triangle.get(x).get(y), triangle);
    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //同unique path
        //遇到obstacle 則把當格作為0
        if (obstacleGrid == null) return -1;

        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        int[][] sum = new int[row][col];
        //init two sides

        for (int i = 0; i < row; i++) {
            sum[i][0] = 1;
        }

        for (int i = 0; i < col; i++) {
            sum[0][i] = 1;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    sum[i][j] = 0; // 遇到障礙 變成0
                    continue;
                }
                sum[i][j] = sum[i - 1][j] + sum[i][j - 1];
            }
        }

        return sum[row - 1][col - 1];
    }


    // DP1.
    public boolean canJump1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

//        int len = A.length;
//        boolean[] can = new boolean[len];
//        can[0] = true;
//
//        for (int i = 1; i < len; i++) {
//            can[i] = false;
//            for (int j = 0; j < i; j++) {
//                // j can arrive and can jump to i.
//                if (can[j] && A[j] == i - j) {
//                    can[i] = true;
//                    break;
//                }
//            }
//        }

//        return can[len - 1];

        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //i點還剩下可以走的最大距離
            //轉換方程關係式 必考
            // 2 3 1 1 4
            //   ^ ==> 當i走到3的時候，當前累積還可以走的最大距離為 2(上一點 i-1) 再-1，因為上一點到此點時已經又走了一步 所以扣回
            // f[i] = f[i-1]-1 上一點走到當前點還剩下可以走得最大距離
            //      =  num[i] 當前可以走的最大距離

            // f[i] = Math.max(f[i-1]-1,num[i]
            dp[i] = Math.max(dp[i - 1] - 1, nums[i]);
            if (dp[i] == 0 && i != nums.length - 1) {
                return false;
            }
        }
        return true;
    }


    public int coinChange(int[] coins, int amount) {
        // DP, time complexity: O(ClogC + amount / smallest_coin), C = |coins|
        // space complexity: O(amount)
        // opt[i] = min_j(opt[j]) + 1 if j - i is a denomination in coins
//            Arrays.sort(coins);
        int[] opt = new int[amount + 1];
        opt[0] = 0;
        for (int i = 1; i <= amount; i++) {
            opt[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && (opt[i - coins[j]] != Integer.MAX_VALUE)) {
                    //f[i] 為可湊成i的最小硬幣數 所以湊齊amount 的話 除了當前的c 硬幣 還有amount-c硬幣
                    //c 為硬幣 amount-c 為另一個硬幣

                    //opt[i - coins[j]] = amount - c 可湊成的最小硬幣數
                    //+1 = 當前的c (1個）
                    opt[i] = Math.min(opt[i], opt[i - coins[j]] + 1);
                }
            }
        }

        System.out.println(Arrays.toString(opt));
        return opt[amount] == Integer.MAX_VALUE ? -1 : opt[amount];


//            if (coins == null || coins.length == 0 || amount <= 0)
//                return 0;
//            int[] minNumber = new int[amount + 1];
//            for (int i = 1; i <= amount; i++) {
//                minNumber[i] = Integer.MAX_VALUE;
//                for (int j = 0; j < coins.length; j++) {
//                    if (coins[j] <= i && minNumber[i - coins[j]] != Integer.MAX_VALUE)
//                        minNumber[i] = Integer.min(minNumber[i], 1 + minNumber[i - coins[j]]);
//                }
//            }
//            if (minNumber[amount] == Integer.MAX_VALUE)
//                return -1;
//            else
//                return minNumber[amount];
    }
//參考https://leetcode.com/problems/maximal-square/
    //但現在條件變成如果正方形只能有對角線是1其餘必須為0


    public int maximalSquareII(int[][] matrix) {
//        DP 轉化方程 f[i][j] = f[i-1][j-1] + 1 (當前matrix[i][j]如果可以貢獻）

        int row = matrix.length;
        int col;
        if (row > 0) {
            col = matrix[0].length;
        } else {
            return 0;
        }

        int edge = Integer.MIN_VALUE;
        int[][] f = new int[row][col];
        for (int i = 0; i < row; i++) {
            f[i][0] = matrix[i][0];
            //初始化時也需要比較最大edge
            edge = Math.max(f[i][0], edge);
        }
        for (int i = 0; i < col; i++) {
            f[0][i] = matrix[0][i];
            edge = Math.max(f[0][i], edge);
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] > 0 && matrix[i - 1][j] == 0 && matrix[i][j - 1] == 0) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = 0;
                }
                edge = Math.max(edge, f[i][j]);
            }
        }

        return edge * edge;
    }

    //coins in a line
//    There are n coins with different value in a line. Two players take turns to take one or
//    two coins from left side until there are no more coins left. The player who take the last coin
//      wins.Could you please decide the first player will win or lose?
//    If the first player wins, return true, otherwise return false.

    boolean firstWillwin(int n) {
        //像這種兩人輪流由前往後拿 看第一個人贏的方式都是 n%(2+1) != 0  --> 速解
        //DP解 則是通解 最好是要了解

        boolean[] dp = new boolean[n + 1];  // dp[i] 剩下i個先手是否能贏
        boolean[] flags = new boolean[n + 1];

        return firstWillwinMemoerySearch(dp, flags, n);
    }

    boolean firstWillwinMemoerySearch(boolean[] dp, boolean[] flags, int n) {
        if (flags[n]) {
            return dp[n];
        }
        flags[n] = true;

        if (n <= 0) {
            dp[n] = false;
        } else if (n == 1) {
            dp[n] = true;
        } else if (n == 2) {
            dp[n] = true;
        } else if (n == 3) {
            dp[n] = false;
        } else {
            dp[n] = (firstWillwinMemoerySearch(dp, flags, n - 2) && firstWillwinMemoerySearch(dp, flags, n - 3)) ||
                    (firstWillwinMemoerySearch(dp, flags, n - 3) && firstWillwinMemoerySearch(dp, flags, n - 4));  //dp[n-3] 其實是critical part 間接證明了 n%(3) == 0 一定輸

        }
        return dp[n];
    }


    //coins in a line 2
    //    There are n coins with different value in a line. Two players take turns to take one or
//    two coins from left side until there are no more coins left. The player who take the coins
//    with the most value wins.Could you please decide the first player will win or lose?
//    If the first player wins, return true, otherwise return false.
    boolean firstWillwinII(int[] n) {
        int[] dp = new int[n.length + 1];
        boolean[] flags = new boolean[n.length + 1];

        int sum = 0;
        for (int i : n) {
            sum += i;
        }

//        return sum/2 < firstWillwinIIMemoerySearch(dp,flags , n);

        //起始點為剩下n.length長度個硬幣
        return sum < 2 * firstWillwinIIMemoerySearch(dp, flags, n, n.length);

    }

    //dp[] => 先手還剩下i個硬幣時候可以拿到的最大總和
    // i ==> 剩下i個硬幣
    int firstWillwinIIMemoerySearch(int[] dp, boolean[] flags, int[] n, int i) {
        if (flags[i]) {
            return dp[i];
        }
        flags[i] = true;

        //init
        if (i <= 0) {
            dp[i] = 0;
        } else if (i == 1) {
            dp[i] = n[n.length - 1];  //倒數第一個
        } else if (i == 2) {
            dp[i] = n[n.length - 1] + n[n.length - 2];  //倒數第一個和第二個
        } else if (i == 3) {
            dp[i] = n[n.length - 2] + n[n.length - 3]; //就算有三個 只能拿第一個跟第二個
        } else {
            dp[i] = Math.max(
                    Math.min(firstWillwinIIMemoerySearch(dp, flags, n, i - 2),
                            firstWillwinIIMemoerySearch(dp, flags, n, i - 3)) + n[n.length - i],
                    Math.min(firstWillwinIIMemoerySearch(dp, flags, n, i - 3),
                            firstWillwinIIMemoerySearch(dp, flags, n, i - 4)) + n[n.length - i] + n[n.length - i + 1]
            );

        }
        return dp[i];
    }


//https://www.jianshu.com/p/79f17ca1584c

//    There are n coins in a line. Two players take turns to take a coin from one of the two ends of the line until there are no more coins left. The player with the larger amount of money wins.
//    Could you please decide the first player will win or lose?
//    Example
//    Given array A = [3,2,2], return true.
//    Given array A = [1,2,4], return true.
//    Given array A = [1,20,4], return false.


    //經典區間型DP
    public boolean firstWillWinIII(int[] values) {

        if (values == null) return false;

        int[][] dp = new int[values.length][values.length];
        boolean[][] flags = new boolean[values.length][values.length];
        int result = firstWillWinIIIMemoerySearch(0, values.length - 1, values, flags, dp);
        int sum = 0;
        for (int i : values) {
            sum += i;
        }
        return sum < 2 * result;

    }


    //dp[i][j] 表示 先手在 i->j 區間可以取得的最大值
    int firstWillWinIIIMemoerySearch(int left, int right, int[] values, boolean[][] flags, int[][] dp) {
        if (flags[left][right]) {
            return dp[left][right];
        }

        flags[left][right] = true;

        if (left == right) {
            dp[left][right] = values[left];
        } else if (left + 1 == right) {
            dp[left][right] = Math.max(values[left], values[right]);
        } else {

            int pickFirst = values[left] +
                    Math.min(firstWillWinIIIMemoerySearch(left + 2, right, values, flags, dp), //後手選了first
                            firstWillWinIIIMemoerySearch(left + 1, right - 1, values, flags, dp)// 後手選了last
                    );

            int pickLast = values[right] +
                    Math.min(firstWillWinIIIMemoerySearch(left + 1, right - 1, values, flags, dp),//後手選了first
                            firstWillWinIIIMemoerySearch(left, right - 2, values, flags, dp)// 後手選了last
                    );

            dp[left][right] = Math.max(pickFirst, pickLast);
        }
        return dp[left][right];
    }


    int kSum(int[] A, int k, int target) {

        //dp[i][j][t] 從前i 個數取j個數之和為target
        // 取 dp[i-1][j-1][t-A[i-1]]
        //不取 dp[i-1][j][t]


        //使用三维动规数组dp[i][j][t]，表示从0遍历到A[i]后找到的j个元素之和为t的情况的总数。
        // 最后返回从整个A数组找到的k个元素之和为target的情况总数即可。操作如下：
        //首先，若第i个元素，也就是A[i-1]，大于t，那么“从前i个元素中取j个元素令j个元素之和为t的所有情况”和第i个元素无关。
        // 也就是说dp[i][j][t] = dp[i-1][j][t]。而如果最后一个元素A[i-1] <= t，
        // 那么这个元素一定能带来一些不同的“从前i个元素中取j个元素令j个元素之和为t的情况”，
        // 但是，还要加上完全不考虑这个元素A[i-1]时的解的集合，也就是dp[i-1][j-1][t-A[i-1]]。
        //  因为，加上这个元素之后的dp[i][j-1][t-A[i-1]]不会影响已经遍历过的dp[i-1][j-1][t-A[i-1]]。

        int n = A.length;
        int[][][] dp = new int[n + 1][k + 1][target + 1];

        //init
        for (int i = 0; i <= n; i++) {
            dp[i][0][0] = 1;  //不管有幾個 取零個總和為零 只有一種
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k && j <= i; j++) {
                for (int t = 1; t <= target; t++) {
                    if (t >= A[i - 1]) { // A[i-1] current
                        dp[i][j][t] = dp[i - 1][j][t] + dp[i - 1][j - 1][t - A[i - 1]];
                    } else {
                        //不符合條件 取之前的結果
                        dp[i][j][t] = dp[i - 1][j][t];
                    }
                }
            }
        }

        return dp[n][k][target];
    }


    //how many ones in eleven to the power of n ? 11^n? (n is very big)
    public int OnesInElevenPower(int n) {
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

            for (int k = n - 1; k >=0 ; k--) {
                int sum = dp[n][k];
                if (sum >= 10) {

                    dp[n][k] = sum % 10; //個位
                    int carry = sum/10;
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
        return count;
    }


    public int findNumberOfLIS(int[] nums) {
        int n = nums.length, res = 0, max_len = 0;
        int[] len =  new int[n], cnt = new int[n];
        for(int i = 0; i<n; i++){
            len[i] = cnt[i] = 1;
            for(int j = 0; j <i ; j++){
                if(nums[i] > nums[j]){
                    if(len[i] == len[j] + 1)cnt[i] += cnt[j];
                    if(len[i] < len[j] + 1){
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j];
                    }
                }
            }
            if(max_len == len[i])res += cnt[i];
            if(max_len < len[i]){
                max_len = len[i];
                res = cnt[i];
            }
        }
        return res;
    }




    public List<List<String>> findLadders(String start, String end,
                                          Set<String> dict) {
        List<List<String>> ladders = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();

        dict.add(start);
        dict.add(end);

        bfs(map, distance, end, start, dict);

        List<String> path = new ArrayList<String>();

        dfs(ladders, path, start, end, distance, map);

        return ladders;
    }

    void dfs(List<List<String>> ladders, List<String> path, String crt,
             String end, Map<String, Integer> distance,
             Map<String, List<String>> map) {
        path.add(crt);
        if (crt.equals(end)) {
            ladders.add(new ArrayList<String>(path));
        } else {
            for (String next : map.get(crt)) {
                if (distance.containsKey(next) && distance.get(crt) == distance.get(next) + 1) {
                    dfs(ladders, path, next, end, distance, map);
                }
            }
        }
        path.remove(path.size() - 1);
    }

    void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
             String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        distance.put(start, 0);
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }

        while (!q.isEmpty()) {
            String crt = q.poll();

            List<String> nextList = expand(crt, dict);
            for (String next : nextList) {
                map.get(next).add(crt);
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(crt) + 1);
                    q.offer(next);
                }
            }
        }
    }

    List<String> expand(String crt, Set<String> dict) {
        List<String> expansion = new ArrayList<String>();

        for (int i = 0; i < crt.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch != crt.charAt(i)) {
                    String expanded = crt.substring(0, i) + ch
                            + crt.substring(i + 1);
                    if (dict.contains(expanded)) {
                        expansion.add(expanded);
                    }
                }
            }
        }

        return expansion;
    }

    public double knightProbability(int N, int K, int r, int c) {
        int[] dirX = new int[]{-2,-1,1,2,2,1,-1,-2};
        int[] dirY = new int[]{1,2,2,1,-1,-2,-2,-1};
        double[][] dp = new double[N][N]; //after K moves, how many ways to get dp[i][j]

        dp[r][c] = 1; // after k move 走到 r c 的方法只有一種 (不動)
        for(int k = 0; k<K ;k++){
            double[][] dp2 = new double[N][N]; //each round of move;
            for(int i = 0; i<N;i++){
                for(int j = 0; j<N;j++){
                    for(int m = 0;m<8;m++){
                        int newX = i+dirX[m];
                        int newY = j+dirY[m];
                        if(isBound(N,newX, newY)){
                            if(dp[i][j]==0) continue;
                            dp2[newX][newY]+=dp[i][j]; //sum of all possible moves from last round.
                        }
                    }
                }
            }
            dp = dp2; //copy current rount to final result
        }
        double count =0;
        for(int i=0;i<N;i++){
            for(int j =0;j<N;j++){
                count += dp[i][j]; // 走了Ｋ層之後
            }
        }

        return count / (Math.pow(8, K));
    }

    boolean isBound(int N, int r, int c){
        return r>=0 && c>=0 && r<N && c<N;
    }


}

