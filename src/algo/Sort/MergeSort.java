package algo.Sort;

import java.util.Arrays;

public class MergeSort {

    public int[] sortArray(int[] nums) {
        if(nums.length<=1) return nums;

        int pivot = nums.length/2;
        //this is divide step in D&C
        int[] left = sortArray(Arrays.copyOfRange(nums,0,pivot));
        int[] right = sortArray(Arrays.copyOfRange(nums,pivot, nums.length));
        return merge(left, right);
    }
    //this is the merge step in divide and merge(conquer)
    //merge two arrays into a sorted array
    int[] merge(int[] left, int[] right){
        int[] res = new int[left.length+right.length];

        int ls = 0;
        int rs = 0;
        int resStart = 0;
        while(ls<left.length && rs<right.length){
            if(left[ls]<right[rs]){
                res[resStart] = left[ls];
                resStart++;
                ls++;
            }else if(left[ls]>=right[rs]){
                res[resStart] = right[rs];
                resStart++;
                rs++;
            }
        }

        //handle in case different length
        while(ls<left.length){
            res[resStart] = left[ls];
            resStart++;
            ls++;
        }

        while(rs<right.length){
            res[resStart] = right[rs];
            resStart++;
            rs++;
        }

        return res;
    }
}
