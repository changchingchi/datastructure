package algo.OOP;

import java.util.*;

/**
 * Created by chchi on 8/22/17.
 */
public class app {
    public static void main(String[] args) {
//        List<List<Integer>> a= fourSum(new int[]{1,0,-1,0,-2,2},0);
//        twoSum(new int[]{3,2,4}, 6);
        int[] a = new int[]{1, 0, -1, 0, -2, 2};
        System.out.println(fourSum2(a, 0));

    }

    public static List<List<Integer>> fourSum2(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }
    private static List<List<Integer>> kSum(int[] nums, int start, int k, int target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(k == 2) { //two pointers from left and right
            int left = start, right = len - 1;
            while(left < right) {
                int sum = nums[left] + nums[right];
                if(sum == target) {
                    List<Integer> path = new ArrayList<Integer>();
                    path.add(nums[left]);
                    path.add(nums[right]);
                    res.add(path);
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    while(left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < target) { //move left
                    left++;
                } else { //move right
                    right--;
                }
            }
        } else {
            for(int i = start; i < len - (k - 1); i++) {
                if(i > start && nums[i] == nums[i - 1]) continue;
                //use current number to reduce ksum into k-1sum
                List<List<Integer>> temp = kSum(nums, i + 1, k - 1, target - nums[i]);
                for(List<Integer> t : temp) {
                    t.add(0, nums[i]);
                }
                res.addAll(temp);
            }
        }
        return res;
    }


    public static List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < num.length - 2; i++) {
            if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
                int lo = i + 1, hi = num.length - 1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo + 1]) lo++;
                        while (lo < hi && num[hi] == num[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
                }
            }
        }
        return res;
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> array = new ArrayList<>();
        int n = nums.length;
        if (n < 4) return null;
        Arrays.sort(nums);
        for (int i = 0; i < n - 3; i++) {
            if (i == 0 || i > 0 && nums[i] != nums[i - 1]) {
                if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
                if (nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) continue;
                for (int j = i + 1; j < n - 2; j++) {
                    if (j == i + 1 || j > i + 1 && nums[j] != nums[j - 1]) {
                        //3sum
                        int lo = j + 1;
                        int hi = n - 1;
                        while (lo < hi) {
                            if (nums[i] + nums[j] + nums[lo] + nums[hi] == target) {
                                array.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
                                while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                                while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                                lo++;
                                hi--;
                            } else if (nums[i] + nums[j] + nums[lo] + nums[hi] > target) {
                                hi--;
                            } else {
                                lo++;
                            }
                        }
                    }
                }
            }
        }
        return array;
    }

    public static int[] twoSum(int[] nums, int target) {
        //7 2 11 15
        if (nums == null || nums.length == 0) return nums;
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[0] * 2 > target || nums[n - 1] * 2 < target) return nums;
        for (int i = 0; i < n - 1; i++) {
            int lo = i;
            int hi = n - 1;
            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                if (sum == target) {
                    return new int[]{nums[lo], nums[hi]};
                } else if (sum < target) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return nums;
    }
}