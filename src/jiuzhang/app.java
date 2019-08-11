package jiuzhang;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chchi on 8/22/17.
 */
public class app {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3};
//        int[][] twoDarray = new int[][]{
//                {1,5,10,11,16,23,24,26,29,34,41,48,49,56,63,67,71,74,75},
//                {97,118,131,150,160,182,202,226,251,273,289,310,326,349,368,390,401,412,428},
//                {445,455,466,483,501,519,538,560,581,606,631,643,653,678,702,726,748,766,781},
//                {792,817,837,858,872,884,901,920,936,957,972,982,1001,1024,1044,1063,1086,1098,1111},
//                {1129,1151,1172,1194,1213,1224,1234,1250,1267,1279,1289,1310,1327,1348,1371,1393,1414,1436,1452},
//                {1467,1477,1494,1510,1526,1550,1568,1585,1599,1615,1625,1649,1663,1674,1693,1710,1735,1750,1769
//                }
//        };
//        app solution = new app();
//        ArrayList<Integer> a = new ArrayList<>();
//        a.add(4);
//        a.add(5);
//        a.add(1);
//        a.add(2);
//        a.add(3);
//        solution.recoverRotatedSortedArray(a);

        Solution solution = new Solution();
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);


        List<Integer> a = solution.preorderTraversal(treeNode);


    }


    public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        //思路：二分法找出最接近row的頭 再用二分法找出此row
        if(matrix == null || matrix.length==0){
            return false;
        }
        int row = matrix.length;
        int start = 0;
        int end = row-1;
        int resultRow;
        int mid=0;
        int cellLength=0;
        while(start+1<end){
             mid = start+(end-start)/2;
             cellLength = matrix[mid].length;
            if(matrix[mid][cellLength-1]==target){
                return true;
            }else if(matrix[mid][cellLength-1]>target){
                end = mid;
            }else {
                start = mid;
            }
        }
        //at this moment, "end" should be the row search
        resultRow = end;
        if(matrix[resultRow][start]==target||matrix[resultRow][end]==target){
            return true;
        }
        start = 0;
        end = matrix[end].length-1;

        while(start+1<end){
             mid = start + (end-start)/2;
            if(matrix[resultRow][mid]==target){
                return true;
            }else if(matrix[resultRow][mid]>target){
                end = mid;
            }else {
                start = mid;
            }
        }
        if(matrix[resultRow][start]==target
                ||
                matrix[resultRow][end]==target){
            return true;
        }
        return false;
    }
    public int binarySearchTemplate(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                //查找第一個的情況，若求任一個則直接退出當前mid
                start = mid;
            } else if (nums[mid] < target) {
                start = mid;
                // or start = mid + 1
            } else {
                end = mid;
                // or end = mid - 1
            }
        }

        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }


    /*
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        //basic validation
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }

        List<Integer> subset = new ArrayList<>();
        recursionForPermute(nums, subset, results);
        return results;
    }

    private void recursionForPermute(int[] nums,
                                     List<Integer> subset,
                                     List<List<Integer>> results) {

        //每次都先確認是否長度符合，如果符合就退出此次遞歸
        if (subset.size() == nums.length) {
            results.add(new ArrayList<>(subset));
            return;
        }
        //每次都從0開始但是跳過重複的
        for (int i = 0; i < nums.length; i++) {
            if (subset.contains(nums[i])) {
                //如果當前subset已經包含，跳過此次iteration
                continue;
            }
            subset.add(nums[i]);
            System.out.println("subset: " + subset);
            recursionForPermute(nums, subset, results);
            subset.remove(subset.size() - 1); //鏡像處理，因為我們將subset在各層之間傳遞，如果不抹去紀錄，
            //則影響下一次遞歸處理。
        }
    }


    /*
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets(int[] nums) {
        // write your code here
        //若求排列組合，則DFS優先考慮
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null) {
            return results;
        }
        if (nums.length == 0) {
            results.add(new ArrayList<>());
            return results;
        }
        Arrays.sort(nums);//去重
        List<Integer> subset = new ArrayList<>();
        recursion(subset, nums, 0, results);
        return results;

    }

    private void recursion(List<Integer> subset, int[] nums, int start, List<List<Integer>> results) {

        results.add(new ArrayList<>(subset));
        System.out.println("results: " + results);
        for (int i = start; i < nums.length; i++) {
            subset.add(nums[i]);
            System.out.println("subset: " + subset);
            recursion(subset, nums, i + 1, results);
            subset.remove(subset.size() - 1);
        }
    }

    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     *
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) {
        // write your code here
        if (source == null || target == null) return -1;
        int result = -1;
        int j;
        for (int i = 0; i < source.length() - target.length() + 1; i++) {
            for (j = 0; j < target.length(); j++) {
                if (source.charAt(j + i) != target.charAt(j)) {
                    //一但不符合就跳出此次迴圈
                    break;
                }
            }
            //j在跳出迴圈之前又被加了一次所以比較target.length而不是target.length-1
            if (j == target.length()) return i;
        }
        return result;
    }

    /**
     * @param A      an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    public int closestNumber(int[] A, int target) {
        // Write your code here
        if (A.length == 0) {
            return -1;
        }
        //write binarysearch and compare left and right
        return bs(A, target, 0, A.length - 1);
    }

    private int bs(int[] A, int target, int left, int right) {

        if (left <= right) {
            int mid = (left + right) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (mid != A.length - 1 && mid > 0 && (A[mid] < target && target < A[mid + 1])) {
                //in (mid,m+1) zone;
                return target - A[mid] < A[mid + 1] - target ? mid : mid + 1;
            } else if (mid != 0 && mid < A.length - 1 && (A[mid] > target && target > A[mid - 1])) {
                // in (mid-1,mid) zone;
                return A[mid] - target < target - A[mid - 1] ? mid : mid - 1;
            } else if (mid == 0 || mid == A.length - 1) {
                return mid;
            } else if (A[mid] >= target) {
                return bs(A, target, left, mid - 1);
            } else {
                return bs(A, target, mid + 1, right);
            }
        }
        return -1;
    }

    /*
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int firstPosition(int[] nums, int target) {
        // write your code here
        if (nums.length == 0) {
            return -1;
        }
        return bsfirst(nums, target, 0, nums.length - 1);
    }

    private int bsfirst(int[] nums, int target, int left, int right) {
        if (left <= right) {
            int mid = (left + right) / 2;
            if ((mid == 0 || nums[mid - 1] < target) && nums[mid] == target) {
                // we found the first occourance
                return mid;
            } else if (nums[mid] > target) {
                return bsfirst(nums, target, left, mid - 1);
            } else {
                return bsfirst(nums, target, mid + 1, right);
            }
        }

        return -1;
    }

    /*
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int lastPosition(int[] nums, int target) {
        // write your code here
        if (nums.length == 0) {
            return -1;
        }
        return bslast(nums, target, 0, nums.length - 1);
    }

    private int bslast(int[] nums, int target, int left, int right) {
        if (left <= right) {
            int mid = (left + right) / 2;
            if ((mid == nums.length - 1 || nums[mid + 1] > target) && nums[mid] == target) {
                // we found the last occourance
                return mid;
            } else if (nums[mid] > target) {
                return bslast(nums, target, left, mid - 1);
            } else {
                return bslast(nums, target, mid + 1, right);
            }
        }
        return -1;

    }


    public void recoverRotatedSortedArray(List<Integer> nums) {
        // write your code here
        //找到轉折點
        int pivot = -1;
        for(int i = 0; i<nums.size()-1;i++){
            if(nums.get(i+1)<nums.get(i)){
                pivot = i;
                break;
            }
        }

        //三步翻轉法
        reverse(nums,0,pivot);
        reverse(nums,pivot+1,nums.size()-1);
        reverse(nums,0,nums.size()-1);


    }

    private void reverse(List<Integer> nums, int start, int end){
        int temp = 0;

        // 7 8 1 2 3 4 5
        while(start<=(start+end)/2){
            temp = nums.get(start);
            nums.set(start,nums.get(end));
            nums.set(end,temp);
            start++;
            end -- ;
        }
    }




}
