package algo.twoPointer;

import com.sun.xml.internal.xsom.impl.scd.Iterators;
import javafx.util.Pair;

import java.util.*;

public class Solutions {

//    public int[] maxSlidingWindow(int[] nums, int k) {
//        if(nums.length < k ) return new int[0];
//        int resultLength = nums.length-k+1;
//        int[] result = new int[resultLength];
//        for(int i=0;i<nums.length-k+1;i++){
//            result[i]=findMaxFromArray(nums,i,i+k);
//        }
//        return result;
//    }


    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;


        //保持一個index在原地 等待下一個unique可交換
        int index = 0;
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            //只算不一樣的否則跳過

            if (nums[i] != nums[i + 1]) {
                //發現unique
                index++;
                nums[index] = nums[i + 1];
                count++;
            }
        }

        return count + 1;
    }

    public boolean isPalindrome(String s) {
        if (s == null) return false;
        if (s.length() < 2) return true; // 1 char

        // s = s.replaceAll("[^A-Za-z0-9]","");
        // s = s.toLowerCase();

        //     if(s.length()==0) return true;
        //     for(int i=0, j=s.length()-1; i<=j;i++,j--){
        //         if(s.charAt(i)!=s.charAt(j)){
        //             return false;
        //         }
        //     }
        //     return true;
        // }

        //雙指針問題
        //用刪去法 而不是regex.

        int left = 0;
        int right = s.length() - 1;


        while (left < right) {
            int leftType = type(s.charAt(left));
            int rightType = type(s.charAt(right));
            if (leftType == -1) {
                left++;
                continue;
            }
            if (rightType == -1) {
                right--; //跳過非字母 數字
                continue;
            }

            if (leftType != rightType ||
                    ((s.charAt(left) != s.charAt(right)) && Math.abs(s.charAt(left) - s.charAt(right)) != 32)) {
                return false;
            }
            left++;
            right--;
        }
        return true;


    }

    private int type(char c) {
        if ((c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
            //its a letter.
            return 1;
        } else if (c >= 48 && c <= 57) {
            //its a number
            return 0;
        }

        //something else.
        return -1;
    }

    public int[] twoSum(int[] nums, int target) {
        //7 2 11 15
        if (nums == null || nums.length == 0) return null;

        //思路1:hash
//         Hashtable<Integer,Integer> table = new Hashtable<>();
//         for(int i = 0; i<nums.length; i++){
//             if(table.containsKey(target-nums[i])){
//                 return new int[]{table.get(target-nums[i]),i};
//             }
//                         table.put(nums[i],i);

//         }
//           throw new IllegalArgumentException("No two sum solution");
        //思路2:兩指針
        Pair<Integer, Integer>[] newNums = new Pair[nums.length];
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            newNums[i] = new Pair<>(nums[i], i);
        }

        Arrays.sort(newNums, new pairComparator());
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            if (newNums[left].getKey() + newNums[right].getKey() > target) {
                //已超過target,捨去當前最大
                right--;
            } else if (newNums[left].getKey() + newNums[right].getKey() < target) {
                left++;
            } else {
                //found
                result[0] = newNums[left].getValue();
                result[1] = newNums[right].getValue();
                break;
            }
        }
        return result;
    }

    class pairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair o1, Pair o2) {
            return (int) o1.getKey() - (int) (o2.getKey());
        }
    }


    public int threeSumClosest(int[] nums, int target) {
        //a+b+c = target
        //a+b = target-c
        if (nums == null || nums.length < 3) return -1; //dummy

        Arrays.sort(nums);

        int result = Integer.MAX_VALUE;
        //遍歷當前當作target
        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            int newTarget = target - nums[i];
            result = twosumHelper(nums, start, end, newTarget, result);
            result = result - nums[i];
        }
        return result;
    }

    //轉換成twosum closest to new target
    private int twosumHelper(int[] nums,
                             int start,
                             int end,
                             int target,
                             int result) {
        while (start < end) {
            int curClosestSum = Math.abs(target - (nums[start] + nums[end]));
            result = Math.min(curClosestSum, result);
            if (nums[start] + nums[end] < target) {
                start++;
            } else {
                end--;
            }
        }
        return result;
    }


    public void sleepSort(int[] nums) {
        //看數字幾秒就睡多久
        if (nums == null || nums.length == 1) ;

        sleepThread[] threadpool = new sleepThread[nums.length];
        for (int i = 0; i < nums.length; i++) {
            threadpool[i] = (new sleepThread((nums[i])));
            threadpool[i].start();
        }
    }

    class sleepThread extends Thread {

        int time = 0;

        public sleepThread(int time) {
            this.time = time;
        }


        @Override
        public void run() {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time);
        }
    }

    public int[] pancakeSort(int[] nums) {
        //思路：兩個flip 由最後開始找最大的--> 1st flip
        // 找到之後 數組 0->i 再一次flip --> 2nd flip

        if (nums == null || nums.length == 0) return nums;

        for (int i = nums.length - 1; i > 0; i--) {
            int max = findMaxIndex(nums, i);
            flip(nums, max);
            flip(nums, i);
            System.out.println(Arrays.toString(nums));
        }

        return nums;
    }

    private void flip(int[] nums, int max) {
        //reverse 0->max inclusive
        int left = 0;
        int right = max;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    private int findMaxIndex(int[] nums, int cur) {
        int maxIndex = 0;
        for (int i = 0; i <= cur; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

//    Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of \
//    which the sum ≥ s. If there isn't one, return 0 instead.
//
//    For example, given the array [2,3,1,2,4,3] and s = 7,
//    the subarray [4,3] has the minimal length under the problem constraint


    public int minimizeSize(int[] nums, int s) {
        int sum = 0;
        int result = Integer.MAX_VALUE;
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            while (j < nums.length && sum < s) {
                sum = sum + nums[j];
                j++;
            }
            if (sum >= s) {
                result = Math.min(result, j - i);
            }

            sum = sum - nums[i]; // prepare for next
        }

        return result;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;

        Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int result = Integer.MIN_VALUE;
        for (left = 0; left < s.length(); left++) {
            while (right < s.length() && !set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
            }

            result = Math.max(result, right - left);

            set.remove(s.charAt(left)); //reset
        }

        return result;
    }


    public String minWindow(String s, String t) {
        if (s == null || t == null) return "";

        String result = "";
        int resultLength = Integer.MAX_VALUE;
        int j = 0;

        //init t
        int[] t_array = new int[256];
        for (int i = 0; i < t.length(); i++) {
            t_array[t.charAt(i)]++;
        }

        int[] s_array = new int[256];
        for (int i = 0; i < s.length(); i++) {
            while (j < s.length() && !isValid(s_array, t_array)) {
                s_array[s.charAt(j)]++;
                j++;
            }

            if (isValid(s_array, t_array) && resultLength > s.substring(i, j).length()) {
                result = s.substring(i, j);
                resultLength = result.length();
            }
            s_array[s.charAt(i)]--;
        }

        return result;
    }

    private boolean isValid(int[] s_array, int[] t_array) {


        //思想一 不通：當有重複元素時候，此方法會有bug 試想 t = bba s = bbaa
//         for (int i = 0; i < s.length(); i++) {
//             s_array[(int) s.charAt(i)] = 1;
//         }


//         for (int i = 0; i < t.length(); i++) {
//             if (s_array[(int) t.charAt(i)] != 1) {
//                 return false;
//             }
//         }

        //思想二 counter 最後逐一比較數組各位置 counter是否一樣
        //便利每一個 並且滿足s_array 各元素總是要大於t_array
        for (int i = 0; i < 256; i++) {
            if (s_array[i] < t_array[i]) {
                return false;
            }
        }
        return true;
    }


    //Longest Substring with At Most K Distinct Characters

//    Given a string, find the length of the longest substring T that contains at most k distinct characters.
//
//    For example, Given s = “eceba” and k = 2,
//
//    T is "ece" which its length is 3.
//

    //前後雙指針
    public String LengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || k == 0) return "";
        String result = "";
        int resultLength = Integer.MIN_VALUE;
        Map<Character, Integer> s_hash = new HashMap<>();
        int j = 0;

        for (int i = 0; i < s.length(); i++) {
            while (j < s.length() && !isValidLongestSubString(s_hash, k)) {
                if (s_hash.containsKey(s.charAt(j))) {
                    s_hash.put(s.charAt(j), s_hash.get(s.charAt(j))+1);
                } else {
                    s_hash.put(s.charAt(j), 1);
                }
                j++;
            }
            //比大小
            if (s.substring(i, j).length() > resultLength) {
                result = s.substring(i, j);
                resultLength = result.length();
            }
            char c = s.charAt(i);
            if(s_hash.containsKey(c)){
                int value = s_hash.get(c);
                if(value>1){
                    s_hash.put(c, s_hash.get(c)-1);
                }else{
                    s_hash.remove(c);
                }
            }
        }
        return result;
    }

    private boolean isValidLongestSubString(Map<Character, Integer> s_hash, int k) {
        if (s_hash.size() < k) {
            return false;
        }
        return true;
    }

    class  Node{
        public int value, row, col;
        public Node(int _v, int _id, int _i) {
            this.value = _v;
            this.row = _id;
            this.col = _i;
        }
    }

//    Kth Smallest Element in a Sorted Matrix - LeetCode
//    matrix = [
//            [ 1,  5,  9],
//            [10, 11, 13],
//            [12, 13, 15]
//            ],
//    k = 8,
//
//            return 13.


    public int kthSmallest(int[][] arrays, int k) {
        Queue<Node> queue = new PriorityQueue<>(k, ((o1, o2) -> o1.value - o2.value)); // ascending order
        int n = arrays.length;
        int i;
        for (i = 0; i < n; i++) {
            //初始化第一排 直後對著heap操作 1 , 10 , 12
            if (arrays[i].length > 0) {
                int row = i;
                int col = 0;
                int value = arrays[row][col];
                queue.add(new Node(value, row, col));
            }
        }


        for (i  = 0; i < k; i++) { //第k個
            Node temp = queue.poll(); //把最小的拿出來 並看是座標是在哪一row 如果沒到盡頭 則須補下一個
            int rrow = temp.row;
            int ccol = temp.col;
            int vvalue = temp.value;

            if (i == k - 1)
                return vvalue;

            if (ccol < arrays[rrow].length-1) { //當前row還沒到盡頭, 必須把當前row的下一個給加到queue裡面

                ccol++;
                vvalue = arrays[rrow][ccol];
                queue.add(new Node(vvalue, rrow, ccol));
            }
        }

        return -1;
    }

    public int kthLargestElement(int k, int[] nums) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length){
            return -1;
        }
        return partition(nums, 0, nums.length - 1, nums.length - k);
    }

    private int partition(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[k];
        }

        int left = start, right = end;
        int pivot = nums[(start + end) / 2];

        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }

        if (k <= right) {
            return partition(nums, start, right, k);
        }
        if (k >= left) {
            return partition(nums, left, end, k);
        }
        return nums[k];
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

//    373. Find K Pairs with Smallest Sums

//    You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
//
//    Define a pair (u,v) which consists of one element from the first array and one element from the second array.
//
//    Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.



    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //思路 minHeap priorityQueue
        //固定一組數組，移動另一組。需要紀錄當前位置

        List<int[]> result = new ArrayList<>();
        if(nums1 == null || nums2 == null ) return result;
        if(nums1.length == 0|| nums2.length == 0) return result;

        //custom comparator returning ascending order.
        PriorityQueue<tuple> queue = new PriorityQueue<>((tuple o1, tuple o2)->(o1.num1+o1.num2 - (o2.num1+ o2.num2)));
        //put k elements into queue

        for(int i=0 ;i<nums1.length && i<k;i++){
            queue.add(new tuple(nums1[i],nums2[0],0));
        }

        int counter = 1;
        while(!queue.isEmpty() && counter <=k) { //總共做0~k次
            tuple cur = queue.poll();
            result.add(new int[]{cur.num1, cur.num2});

            if(counter == k){
                return result;
            }

            int curCol = cur.col;
            int value = cur.num1+cur.num2;

            if(curCol < nums2.length-1){ // 捕進去
                curCol++;
                queue.add(new tuple(cur.num1, nums2[curCol], curCol));
            }
            counter++;
        }

        return result;
    }

    class tuple {
        int num1;
        int num2;
        int col; // current index to check if array reaching the end.
        tuple(int num1, int num2, int col){
            this.num1 = num1;
            this.num2 = num2;
            this.col = col;
        }
    }


    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return sum;
    }

    private int twoSumSmaller(int[] nums, int startIndex, int target) {
        int sum = 0;
        int left = startIndex;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                sum += right - left;
                left++;
            } else {
                right--;
            }
        }
        return sum;
    }
}

