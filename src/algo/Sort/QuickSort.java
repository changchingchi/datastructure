package algo.Sort;

public class QuickSort {



    int[] sort(int[] nums){
        qSort(nums, 0, nums.length-1);
        return nums;
    }

    private void qSort(int[] nums, int left, int right) {
        //here we do two things, partition and divide . Since we pass nums, it wil be modify automatically.

        if(left<right){
            int pivot = partition(nums, left, right);
            //every run, pivot element already has its final value.
            qSort(nums, left, pivot-1);
            qSort(nums,pivot+1, right);
        }
    }

    private int partition(int[] nums, int left, int right) {
    //in this case, we use last element as pivot

        int pivot = nums[right];

        //now we doing swap so that everything less than pivot goes on its left, larger goes on its right.
        int slow = left;
        for(int fast = left; fast<right; fast++){
            if(nums[fast]<pivot){
                swap(nums, fast, slow);
                slow++;
            }
        }
        //now pivot still at the last, we need to swap it with slow. so that its at its final position.
        swap(nums, right, slow);
        return slow;
    }

    void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j]= temp;
    }

}
