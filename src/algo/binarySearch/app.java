package algo.binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chchi on 8/22/17.
 */
public class app {
    public  void main(String[] args) {
//        int[] array = {1, 2, 4, 6, 9};
//        int[][] test = new int[][]{{1, 2, 3, 6, 5}, {16, 41, 23, 22, 6}, {15, 17, 24, 21, 7}, {14, 18, 19, 20, 10}, {13, 14, 11, 10, 9}};
//
//        System.out.print(Arrays.toString(findPeakElementII(test).toArray()));

//        int[] woods = {232,124,456};
//        System.out.print(woodCut(woods, 7));

        int[] array ={1,3,4,2,2};
        double a = 2;
        double b = 16;
//        System.out.print(a/b);


        System.out.println(findFreq(new int[]{1,2,2,2,3,3,3},2));
    }

    /**
     * @param target the value to search
     * @param array  the data collection
     * @return the index of the target if available, otherwise return -1
     */

    //九章模板
    //given a sorted array.
    public  int binarySearch(int target, int[] array) {
        int start = 0;
        int end = array.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (array[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (array[start] == target) {
            return start;
        }
        if (array[end] == target) {
            return end;
        }

        return -1;
    }



    //=========

// int[][] test = new int[][]{
// {1, 2, 3, 6, 5},
// {16,41,23,22,6},
// {15,17,24,21,7},
// {14,18,19,20,10},
// {13,14,11,10,9}}

    //return index of peak in 2d array.
    //return index of 41 or index of 24 in this example.
    public  List<Integer> findPeakElementII(int[][] array) {
        //思路 每次都取中間行 然後針對行找最大值
        //看最大值的上面或是下面哪個大去哪個
        List<Integer> result = new ArrayList<>();
        if (array == null || array.length == 0) return result;

        int start = 0;
        int end = array.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int maxInRow = findMax(array[mid]);

            if (array[mid][maxInRow] < array[mid - 1][maxInRow]) {
                end = mid;
            } else if (array[mid][maxInRow] < array[mid + 1][maxInRow]) {
                start = mid;
            } else {
                //找到peak
                result.add(array[mid][maxInRow]);
                return result;
            }
        }
        return result;
    }

    private  int findMax(int[] array) {
        int maxIndex = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }



    //=========

    //Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could
    // have equal or more than k pieces with the same length. What is the longest length you can get from the n
    // pieces of wood? Given L & k, return the maximum length of the small pieces.

    //[232, 124, 456] k=7 return 114.

    //三段木頭分別為232 124 456長度 切七刀的每個最大長度為114
    public  int woodCut(int[] woods, int k) {
        //思路：2分法
        //1~456當中隨意找一個數 然後看能不能切成k刀 可以的話就往後移動
        if (woods == null || woods.length == 0) return 0;
        if (woods.length == 1) return woods[0];

        int start = 1;
        int end = findMaxValue(woods);

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int target = checkPiece(woods, mid);
            if (target >= k) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (checkPiece(woods, end)==k){
            return end;
        }

        if (checkPiece(woods, start)==k){
            return start;
        }

            return 0;
    }


    private  int checkPiece(int[] woods, int mid){
        int target = 0;
        for (int i = 0; i < woods.length; i++) {
            target = target + woods[i] / mid;
        }

        return target;
    }

    private  int findMaxValue(int[] array) {
        int Max = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > Max) {
                Max = array[i];
            }
        }
        return Max;
    }


    //=========
    //leetcode 287
    //Input: [1,3,4,2,2]
    //Output: 2

//    You must not modify the array (assume the array is read only).
//    You must use only constant, O(1) extra space.
//    Your runtime complexity should be less than O(n2).
//    There is only one duplicate number in the array, but it could be repeated more than once.

     int findDuplicate ( int[] nums){

        //思路：目測約有三種解 但是因為題目各種要求，所以只能用二分答案
        //利用一個性質：當前的數字必須要滿足 nums[i]<=i

        //利用mid告訴我們
        //[1,3,4,2,2] nums
        //[0,1,2,3,4] index 因為數字在1~n之間 若個數(index)的counter大於index表示有重複
        //
        //[0,1,3,4,5] counter 找到則返回index （跟原本的數組沒關係）
        //     ^

        int start = 0;
        int end = nums.length-1;

        while(start+1<end){
            int mid = start + (end-start)/2;
            int check = check(nums,mid);
            if(check<=mid){
                start = mid;
            }else{
                end = mid;
            }
        }

        if(check(nums, start)<=start){
            return end;
        }
        return start;
    }

    //找小於等於mid的個數
    private  int check(int[] nums, int mid){
        int count = 0;
        for(int num:nums){
            if(num<=mid){
                count++;
            }
        }
        return count;
    }


//    https://www.geeksforgeeks.org/count-number-of-occurrences-or-frequency-in-a-sorted-array/
    // use less than O(N) method
//int arr[] = {1, 2, 2, 3, 3, 3, 3};  target = 2 -->return 2;
    public  int findFreq(int[] array, int target){
    //find the first occurance
    //find the last
    // last - first +1
        return findLastOccu(array,target) - findFirstOccu(array, target) +1 ;


    }


     int findFirstOccu(int[] array, int target){
        int start = 0;
        int end = array.length-1 ;

        while(start+1<end){
            int mid = start + (end-start)/2;
            if(target>=array[mid]){
                end = mid;
            }else {
                start = mid;
            }
        }
        if(array[start] == target){
            return start;
        }

        if(array[end] == target){
            return end;
        }
        return -1;
    }

     int findLastOccu(int[] array, int target){
        int start = 0;
        int end = array.length-1 ;

        while(start+1<end){
            int mid = start + (end-start)/2;
            if(target>=array[mid]){
                start = mid;
            }else {
                end = mid;
            }
        }

        if(array[end] == target){
            return end;
        }

        if(array[start] == target){
            return start;
        }


        return -1;
    }


}
