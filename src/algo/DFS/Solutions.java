package algo.DFS;

import algo.TreeTraverse.TreeNode;

import java.util.*;

public class Solutions {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0 || nums == null) return result;

        //DFS : we need to dedup first by sorting the array first.
        Arrays.sort(nums); //[1,2,3]  vs [3,2,1]
        //遞歸的定義:最初放入空的subset
        helper(result, 0, new ArrayList<>(), nums);
        return result;
    }

    //遞歸定義: 在nums中找到以subset開頭的子集 並且最後放到result
    private void helper(List<List<Integer>> result,
                        int startIndex,
                        ArrayList<Integer> subset,
                        int[] nums
    ) {
        //把上層subset放入結果在開始另一層遞歸
        result.add(new ArrayList(subset));

        if(startIndex == nums.length)
            return;


        //遞歸拆解
        for (int i = startIndex; i < nums.length; i++) {  // 列舉出每一個nums[i] 開頭   // for loop完成 及為遞歸的出口
            subset.add(nums[i]);
            helper(result, i + 1, subset, nums);
            subset.remove(subset.size() - 1);
        }
    }


    public boolean isPalindrome(String s) {
        if (s == null) return false;
        if (s.length() < 2) return true; // 1 char
        s = s.replaceAll("[^A-Za-z0-9]", "");
        s = s.toLowerCase();
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }


    public List<List<String>> partition(String s) {
        //all possible --> DFS 思路
        List<List<String>> results = new ArrayList<>();

        if (s == null || s.length() == 0) {
            return results;
        }

        List<String> partition = new ArrayList<>();
        helper(s, 0, partition, results);
        return results;
    }

    //遞歸的定義
    //startIndex 代表著切割位
    // a aab
    //  ^     -->startIndex  當startIndex長度和s長度一樣時 表示已到尾 為遞歸的出口
    private void helper(String s,
                        int startIndex,
                        List<String> partition,
                        List<List<String>> results) {
        //遞歸的出口
        if (startIndex == s.length()) {
            //切割完成  "aaab " s.length = 4
            //              ^  startIndex = 4
            results.add(new ArrayList(partition));
            System.out.println("results: " + results);
            return;
        }

        //遞歸的拆解
        for (int i = startIndex; i < s.length(); i++) {

            String subString = s.substring(startIndex, i + 1);
            System.out.println("subString: " + subString + " startIndex: " + startIndex + " i: " + i);
            if (!isPalindrome(subString)) {
                continue;
            }
            partition.add(subString);
            helper(s, i + 1, partition, results);
            partition.remove(partition.size() - 1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        //DFS
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }

        Arrays.sort(nums);
        List<Integer> curPermutation = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        helper(nums, set, curPermutation, results);
        return results;

    }

    private void helper(int[] nums,
                        Set<Integer> set,  // 與subsets題目相比：本題不需要去重，需要一個set來記錄當前數有沒有被使用過（被放到過permuatation)裡。
                        List<Integer> curPermutation,
                        List<List<Integer>> results
    ) {
        //遞歸出口
        if (set.size() == nums.length) {
            results.add(new ArrayList(curPermutation));
            return;
        }

        //遞歸的定義：題目為組合，所以每次都從0開始
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            curPermutation.add(nums[i]);
            System.out.println(curPermutation);
            set.add(nums[i]);

            helper(nums, set, curPermutation, results);
            curPermutation.remove(curPermutation.size() - 1);
            set.remove(nums[i]);
        }
    }


    public List<List<Integer>> permute3(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        helper3(nums, 0, result, new ArrayList<>());
        return result;
    }


    void helper3(int[] nums, int start, List<List<Integer>> result, List<Integer> temp){
        if(temp.size() == nums.length){
            result.add(new ArrayList<>(temp));
            return;
        }

        for(int i=0;i<nums.length;i++){
            if(temp.contains(nums[i])) continue; //skip the same position element
            temp.add(nums[i]);
            helper3(nums, i+1, result, temp);
            temp.remove(temp.size()-1);
        }
    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        //DFS：注意題目包含重複數字
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        Arrays.sort(nums);
        List<Integer> curPermutation = new ArrayList<>();
        boolean[] visited = new boolean[nums.length]; // 查看當前是否放入過
        helperUnique(nums, visited, curPermutation, results);
        return results;
    }

    //遞歸的定義：找出以curPermutation 開頭的組合 並且沒有在set裡面出現過
    private void helperUnique(int[] nums,
                              boolean[] visited,
                              List<Integer> curPermutation,
                              List<List<Integer>> results) {

        //遞歸的出口
        if (curPermutation.size() == nums.length) {
            results.add(new ArrayList(curPermutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == true) {
                continue;
            }

            //越界，去重複   visited[i-1] == false 條件表示跳過了一個
            if (i != 0 && nums[i] == nums[i - 1] && visited[i - 1] == false) {
                continue;
            }

            curPermutation.add(nums[i]);
            visited[i] = true;
            helperUnique(nums, visited, curPermutation, results);
            curPermutation.remove(curPermutation.size() - 1);
            visited[i] = false;
        }
    }


    List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        if (n <= 0) {
            return results;
        }

        search(results, new ArrayList<Integer>(), n);
        return results;
    }

    /*
     * results store all of the chessboards
     * cols store the column indices for each row
     */

    //看不懂
    private void search(List<List<String>> results,
                        List<Integer> cols,
                        int n) {
        if (cols.size() == n) {
            results.add(drawChessboard(cols));
            return;
        }

        for (int colIndex = 0; colIndex < n; colIndex++) {
            if (!isValid(cols, colIndex)) { // 確認當前位置適合符合題目要求
                continue;
            }
            cols.add(colIndex);
            search(results, cols, n);
            cols.remove(cols.size() - 1);
        }
    }

    private List<String> drawChessboard(List<Integer> cols) {
        List<String> chessboard = new ArrayList<>();
        for (int i = 0; i < cols.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < cols.size(); j++) {
                sb.append(j == cols.get(i) ? 'Q' : '.');
            }
            chessboard.add(sb.toString());
        }
        return chessboard;
    }

    //cols store the column indices for each row.
    private boolean isValid(List<Integer> cols, int column) {
        int row = cols.size();
        for (int rowIndex = 0; rowIndex < cols.size(); rowIndex++) {
            if (cols.get(rowIndex) == column) { // 上下左右
                return false;
            }
            if (rowIndex + cols.get(rowIndex) == row + column) { //右上左下 斜角攻擊
                return false;
            }
            if (rowIndex - cols.get(rowIndex) == row - column) { //右下左上 斜角攻擊
                return false;
            }
        }
        return true;
    }

//    Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
//    find all unique combinations in candidates where the candidate numbers sums to target.
//
//    The same repeated number may be chosen from candidates unlimited number of times.

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {

        if (remain == 0) {
            list.add(new ArrayList<>(tempList));
            return;
        }
//        if(remain < 0) return;

        for (int i = start; i < nums.length; i++) {
            if (remain < nums[i]) break;
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
            tempList.remove(tempList.size() - 1);
        }

    }

//    Given a collection of candidate numbers (candidates) and a target number (target),
//    find all unique combinations in candidates where the candidate numbers sums to target.
//
//    Each number in candidates may only be used once in the combination.

    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        backtrack2(list, new ArrayList<>(), nums, target, 0);

        return list;

    }

    //
    private void backtrack2(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain == 0) {
            list.add(new ArrayList<>(tempList));
            System.out.println("templist: " + tempList);
            return;
        }
        if (remain < 0) return;

        for (int i = start; i < nums.length; i++) {
            System.out.println("templist: " + tempList);

            System.out.println("remain: " + remain + " i: " + i + " start: " + start + " nums[i]: " + nums[i]);

            if (remain < nums[i]) break;
            if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack2(list, tempList, nums, remain - nums[i], i + 1);
            tempList.remove(tempList.size() - 1);
        }

    }

    public List<List<String>> partition2(String s) {
        List<List<String>> list = new ArrayList<>();
        backtrack3(list, new ArrayList<>(), s, 0);
        return list;
    }

    public void backtrack3(List<List<String>> list, List<String> tempList, String s, int start) {
        if (start == s.length())
            list.add(new ArrayList<>(tempList));
        else {
            for (int i = start; i < s.length(); i++) {
                System.out.println("templist: " + tempList);

                System.out.println(" start: " + start + " i: " + i + " s[i]: " + s.charAt(i));

                if (isPalindrome(s, start, i)) {
                    tempList.add(s.substring(start, i + 1));
                    backtrack3(list, tempList, s, i + 1);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }

    public boolean isPalindrome(String s, int low, int high) {
        while (low < high)
            if (s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }


    int max = Integer.MIN_VALUE;
    int count = 1;
    int pre = Integer.MIN_VALUE;

    public int[] findMode(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        int[] r = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            r[i] = result.get(i);
        }
        return r;
    }

    void helper(TreeNode root, List<Integer> result) {
        if (root == null) return;
        helper(root.left, result);
        if (pre == root.val) {
            count++;
        } else {
            pre = root.val;
            count = 1;
        }
        if (count > max) {
            System.out.println(count);

            max = count;
            result.clear();
            result.add(root.val);
        } else if (count == max) {
            result.add(root.val);
        }
        helper(root.right, result);
    }
//    s = "abcd", map = {"abc":1, "ab":1, "cd":1} return true because s can be broken into "ab" and "cd"
//    s = "aaab", map = {"a":2, "b":2} return false as not enough a

    public boolean wordBreakFB(String s, Map<String, Integer> worDict) {
        return word_break_fb(s, worDict, 0);
    }

    boolean word_break_fb(String s, Map<String, Integer> wordDict, int start) {
        if (start == s.length()) return true;

        for (int end = start + 1; end <= s.length(); end++) {
            String sub = s.substring(start, end);

            if (wordDict.containsKey(sub) && wordDict.get(sub) > 0) {
                //update map first before next recursion
                int count = wordDict.get(sub);
                count--;

                wordDict.put(sub, count);
                if (word_break_fb(s, wordDict, end)) {
                    return true;
                }
                count++;
                wordDict.put(sub, count);
            }
        }

        return false;
    }

    boolean wordbreakfb(String s, Map<String, Integer> dict, int start) {
        if (start == s.length()) return true;

        for (int i = start + 1; i < s.length(); i++) {
            String sub = s.substring(start, i);

            if (dict.containsKey(sub) && dict.get(sub) > 0) {
                //only the valid set

                dict.put(sub, dict.get(sub) - 1);
                if (wordbreakfb(s, dict, i)) {
                    return true;
                }
                dict.put(sub, dict.get(sub) + 1);
            }
        }
        return false;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        return word_Break(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }
    public boolean word_Break(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))
                    && word_Break(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }



    public List<String> wordBreakII(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        Map<String, List<String>> visited = new HashMap<>(); //prefix and its possible wordbreak

        result = word_break_II(s, new HashSet<>(wordDict), visited);
        return result;
    }

    private List<String> word_break_II(String s, Set<String> set, Map<String, List<String>> visited) {
        if (s.length() == 0) return null; // reach the end;

        if (visited.containsKey(s)) return visited.get(s);

        List<String> result = new ArrayList<>();
        for (int i = 1; i <= s.length(); i++) {
            String sub = s.substring(0, i); // 每次都從頭往後找i 個
            List<String> temp = null;
            if (set.contains(sub)) {
                temp = word_break_II(s.substring(i), set, visited); //由i開始以後的子集
                if (temp == null) //reach the end so add into result
                {
                    result.add(sub);
                } else {
                    //??
                    for (String str : temp) {
                        result.add(sub + " " + str);
                    }
                }
            }
        }
        visited.put(s, result);
        return result;
    }

//    Given an array of n integers, return the result of all possible equations you can generate by adding the following operators in-between the numbers: +, -, *, /, () (for prioritization).
//
//    Examples:
//
//            [4, 2]: Can generate 4 + 2 = 6, 4 - 2 = 2, 4 * 2 = 8, 4 / 2 = 2 -> Return [6,2,8,2]
//
//            [4, 2, 3]: Can generate: 4 + 2 + 3, 4 + 2 - 3, 4 + 2 * 3, 4 + 2 / 3
//            4 - 2 + 3, 4 - 2 - 3, 4 - 2 * 3, 4 - 2 / 3
//    Etc.
//    But we can prioritize operations as well: 4 + (2 * 3) = 10 != (4 + 2) * 3 = 18


    public  List<Integer> generate(List<Integer> nums) {
        return generate(nums, new HashMap<>());
    }

    private  List<Integer> generate(List<Integer> nums, Map<List<Integer>, List<Integer>> memo) {
        if (nums.size() <= 1) return nums;
        if (memo.containsKey(nums)) return memo.get(nums);

        List<Integer> result = new ArrayList<>();

        for (int i = 1; i < nums.size(); i++) {
            List<Integer> left = generate(nums.subList(0, i), memo);
            List<Integer> right = generate(nums.subList(i, nums.size()), memo);

            for (int a : left) {
                for (int b : right) {
                    result.add(a + b);
                    result.add(a - b);
                    result.add(a * b);
                    if (b != 0) result.add(a / b);
                }
            }
        }

        memo.put(nums, result);
        return result;
    }



    public List<Integer> diffWaysToCompute(String input) {
        Map<String, List<Integer>> memo = new HashMap<>();
        return helperdiffWaysToCompute(input, memo);
    }

    List<Integer> helperdiffWaysToCompute(String input, Map<String, List<Integer>> memo){
        if(memo.containsKey(input)) return memo.get(input);

        List<Integer> cur = new ArrayList<>();
        for(int i=0;i<input.length();i++){
            if(input.charAt(i)=='+'||
                    input.charAt(i)=='*'||
                    input.charAt(i) == '-'){
                //4+2
                String left = input.substring(0,i); //4
                String right = input.substring(i+1);//2

                List<Integer> leftResult = helperdiffWaysToCompute(left, memo);
                List<Integer> rightResult = helperdiffWaysToCompute(right, memo);

                for(Integer l: leftResult){
                    for(Integer r:rightResult){
                        int c = 0;
                        switch (input.charAt(i)){
                            case '+': c = l+r;
                                break;
                            case '-': c = l-r;
                                break;
                            case '*': c=  l*r;
                                break;
                        }
                        cur.add(c);
                    }

                }
            }
        }

        if(cur.size()==0){
            cur.add(Integer.valueOf(input));
        }

        memo.put(input, cur);
        return cur;
    }


    public List<String> generateAbbreviations(String word){
        List<String> ret = new ArrayList<String>();
        backtrack(ret, word, 0, "", 0);

        return ret;
    }

    private void backtrack(List<String> ret, String word, int pos, String cur, int count) {
        if (pos == word.length()) {
            if (count > 0) cur += count;
            ret.add(cur);
            return;
        }
        backtrack(ret, word, pos + 1, cur, count + 1);
        String s;
        if(count> 0){
            s = cur+count + word.charAt(pos);
        }else{
            s = cur + word.charAt(pos);
        }
        backtrack(ret, word, pos + 1, s, 0);
    }


    public String reverseString(String s){
        return helper_reverseString(s, 0);
    }

    private String helper_reverseString(String s, int i) {
        String temp = "";
        if(i<s.length()){
            temp = reverseString(s.substring(i+1)) + s.charAt(i);
        }
        return temp;
    }

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> results = new ArrayList<>();
        if (n <=3) {
            return results;
        }

        getFactors(n, 2, new ArrayList<Integer>(), results);
        return results;
    }

    private void getFactors(int n, int start, List<Integer> current, List<List<Integer>> results) {
        if (n == 1) {
            if (current.size() > 1) {
                results.add(new ArrayList<Integer>(current));
            }
            return;
        }


        for (int i = start; i <= (int) Math.sqrt(n); i++) {  // ==> here, change 1
            if (n % i != 0) {
                continue;
            }
            current.add(i);
            getFactors(n/i, i, current, results);
            current.remove(current.size()-1);
        }

        int i = n; // ===> here, change 2
        current.add(i);
        getFactors(n/i, i, current, results);
        current.remove(current.size()-1);
    }


    public List<List<Integer>> pathSumII(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        pathSum_helper(root, sum, new ArrayList<>(), res);
        return res;
    }

    private void pathSum_helper(TreeNode root, int sum, List<Integer> crt, List<List<Integer>> res) {
        if (root == null) return;
        sum -= root.val;
//        if(sum<0) return;   --> we cannot do this cause we dont know if all nodes value is positive or not.
        crt.add(root.val);
        if (root.left == null && root.right == null && sum == 0) {
            res.add(new ArrayList<>(crt));
//            return;
        }
        pathSum_helper(root.left, sum, crt, res);
        pathSum_helper(root.right, sum, crt, res);
        crt.remove(crt.size() - 1);
    }


    public boolean canPartition(int[] nums) {

        int sum = 0;
        for(int i:nums){
            sum+=i;
        }
        //if sum is odd number then it cant be divied into two set equal sum.
        if(sum%2!=0) return false;

        //handle sum is even case;
        return canfind(nums, 0, 0, sum/2);
    }


    boolean canfind(int[] nums, int start, int sum, int target){
        if(sum>target){
            return false;
        }
        if(sum == target){
            return true;
        }

        for(int i=start; i<nums.length; i++){
            sum+=nums[i];
            if(canfind(nums, i+1, sum, target)){
                // System.out.println("true");
                return true;
            }
            sum-=nums[i];
        }
        return false;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        List<List<String>> res = new ArrayList<List<String>>();
        Map<String, List<String>> neighbors = new HashMap<String, List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();

        wordList.add(beginWord);
        wordList.add(endWord);

        //BFS from end to start and find neighbors and distance
        bfs(neighbors, distance, endWord, beginWord, wordList);

        //DFS based on distance and its neighbors with BETTER route.
        List<String> temp = new ArrayList<>();
        temp.add(beginWord);

        dfs(res, temp, beginWord, endWord, distance, neighbors);

        return res;
    }

    void dfs(List<List<String>> res, List<String> temp, String start, String end,  Map<String, Integer> distance, Map<String, List<String>> neighbors){
        if(start.equals(end)){
            //found the target
            res.add(new ArrayList<>(temp));
            return;
        }

        for(int i=0 ;i<neighbors.get(start).size();i++){
            String next = neighbors.get(start).get(i);
            temp.add(next);
            if(distance.containsKey(next) && distance.get(start) == distance.get(next)+1){
                dfs(res, temp, next, end, distance, neighbors);
            }
            temp.remove(temp.size()-1);
        }
    }


    void bfs(Map<String, List<String>> neighbor, Map<String, Integer> distance,
             String start, String end, List<String> dict){
        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        distance.put(start, 0); // target word to itself is 0 distance.
        for(String s:dict){
            neighbor.put(s, new ArrayList<>()); //init neighbor table
        }

        while(!queue.isEmpty()){
            String cur = queue.poll(); //get current node
            List<String> neighbors= findNeighbors(cur, dict);
            for(String n: neighbors){
                //for each eighbors, update neighbor table and update distance.
                neighbor.get(n).add(cur); //add cur into neighrs table
                if(!distance.containsKey(n)){
                    distance.put(n, distance.get(cur)+1);
                    queue.add(n);
                }
            }
        }
    }

    List<String> findNeighbors(String cur, List<String> dict){
        List<String> res = new ArrayList<>();

        //fun part. we need to iterate all letters and check each of them if they are in dict

        for(char c = 'a'; c<='z'; c++){
            for(int i=0;i<cur.length();i++){
                if(c==cur.charAt(i)) continue;
                String next = replace(cur, i, c); //replace i with c.
                if(dict.contains(next)){
                    res.add(next);
                }
            }
        }

        return res;
    }


    // replace character of a string at given index to a given character
    // return a new string
    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }

//DFS + BFS

//因為我們要求出所有路徑 --> ＤＦＳ
//但是在求之前我們要先知道有哪些neibors and distance to endWord





    public Node construct(int[][] g) { return build(0, 0, g.length - 1, g.length - 1, g);}

    Node build(int r1, int c1, int r2, int c2, int[][] g) {
        if (r1 > r2 || c1 > c2) return null;
        boolean isLeaf = true;
        int val = g[r1][c1];
        for (int i = r1; i <= r2; i++)
            for (int j = c1; j <= c2; j++)
                if (g[i][j] != val) {
                    isLeaf = false;
                    break;
                }
        if (isLeaf)
            return new Node(val == 1, true, null, null, null, null);
        int rowMid = (r1 + r2) / 2, colMid = (c1 + c2) / 2;
        return new Node(false, false,
                build(r1, c1, rowMid, colMid, g),//top left
                build(r1, colMid + 1, rowMid, c2, g),//top right
                build(rowMid + 1, c1, r2, colMid, g),//bottom left
                build(rowMid + 1, colMid + 1, r2, c2, g));//bottom right
    }

//    https://leetcode.com/problems/construct-quad-tree/
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }


    public boolean makesquare(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        int sum = 0;
        for(int i:nums){
            sum+=i;
        }
        if(sum%4!=0) return false;

        int side = (int)sum/4;

        //we need to find 4 side;
        Arrays.sort(nums);
        return  dfs(nums, 0, 0 , side, 0 ,new boolean[nums.length]);
    }

    private boolean dfs(int[] nums, int index, int tmp, int target, int group, boolean[] visited) {
        if (group == 4) return true;
        if (tmp > target) return false;
        if (target == tmp) return dfs(nums, 0, 0, target, group + 1, visited);

        for (int i = index; i < nums.length; i++) {
            if (visited[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
            visited[i] = true;
            if (dfs(nums, i + 1, tmp + nums[i], target, group, visited)) return true;
            visited[i] = false;
        }

        return false;
    }



    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        return dfs(nums, target, new HashMap<>());
    }


    int dfs(int[] nums, int remaining, Map<Integer,Integer> memo){
        if(memo.containsKey(remaining)){
            return memo.get(remaining);
        }

        if(remaining==0){
            return 1; //全部都跑完了 表示我們找到了一組
        }

        int count = 0;
        for(int i=0;i<nums.length;i++){
            if(remaining<nums[i]) break;
            count+=dfs(nums,remaining-nums[i], memo);
        }

        memo.put(remaining, count);
        return count;
    }


    //dfs + memo
//    https://leetcode.com/problems/minimum-path-sum/discuss/185358/Easy-Recursive-Solution-with-memo-(Java)
//    Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
//
//    Note: You can only move either down or right at any point in time.
//
//            Example:
//
//    Input:
//            [
//            [1,3,1],
//            [1,5,1],
//            [4,2,1]
//            ]
//    Output: 7
//    Explanation: Because the path 1→3→1→1→1 minimizes the sum.
    public int minPathSum(int[][] grid) {
        //init grid first
        int[][] memo = new int[grid.length][grid[0].length];
        for(int[] i:memo){
            Arrays.fill(i, Integer.MAX_VALUE);
        }

        return dfs_minPathSum(grid, 0, 0, memo);
    }

    // DFS走數組過程中帶入一個memo紀錄已經走過的路
    int dfs_minPathSum(int[][] grid, int row, int col , int[][] memo){
        //越界返回預設值
        if(!isValid(grid,row,col)) return Integer.MAX_VALUE;

        if(memo[row][col]!=Integer.MAX_VALUE) return memo[row][col];


        //最後一點 返回原值
        if(row == grid.length && col == grid[0].length) {
            return grid[row][col];
        }

        //當前value+ 左邊或是下面誰比較小 因為minpath.
        memo[row][col] = grid[row][col] + Math.min(dfs_minPathSum(grid, row+1, col, memo), dfs_minPathSum(grid, row, col+1, memo));
        return memo[row][col];
    }

    boolean isValid(int[][] grid, int row, int col){
        return row>=0 && row<grid.length && col>=0 && col<grid[0].length;
    }

    //check if x,y can reach i,j
    boolean[][]  visited  =  new boolean[3][3];
    int[][] dir = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    boolean findMaze(int[][] grid, int x, int y, int i, int j){
        if(!isValidMaze(grid, x, y))  return false;
        if(x==i && y == j) return true;

        visited[x][y] = true;

//        for(int k=0;k<4;k++){
//            int newX = x + dir[k][0];
//            int newY = y + dir[k][1];
//            if(isValidMaze(grid,  newX, newY)){
//                if(findMaze(grid, newX, newY, i, j)) return true;
//            }
//        }
//        return false;
        return findMaze(grid, x+1, y, i, j) ||
                findMaze(grid, x, y+1, i, j)||
                findMaze(grid, x-1, y, i, j)||
                findMaze(grid, x, y-1, i, j);

    }

    boolean isValidMaze(int[][] grid, int row, int col){
        return row>=0 && row<grid.length && col>=0 && col<grid[0].length&& grid[row][col]!=1 &&  !visited[row][col];
    }
}




