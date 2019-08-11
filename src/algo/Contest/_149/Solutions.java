package algo.Contest._149;

import java.util.*;

public class Solutions {


    public int dayOfYear(String date) {
        int[] monthes = new int[]{0,31,28,31,30,31,30,31,31,30,31,30,31};

        String[] parts = date.split("-");
        int year = Integer.valueOf(parts[0]);
        if(year%4!=0){

        }else if(year%100!=0){
            monthes[2] = 29;
        }else if(year%400!=0){

        }else{
            monthes[2] = 29;
        }

        int month = Integer.valueOf(parts[1]);
        int day = Integer.valueOf(parts[2]);
        int res = 0;
        for(int i=0;i<month;i++){
            res+=monthes[i];
        }

        res+=day;

        return res;
    }


//    //超時解 需要加memo
//    int count = 0;
//    int[][] visited;
//    int mod = (int)Math.pow(10,9)+7;
//    public int numRollsToTarget(int d, int f, int target) {
//        // int[][] visited = new int[d][f+1];
//        // Arrays.fill(visited,-1);
//        int[] nums = new int[f];
//        for(int i=1;i<=f;i++){
//            nums[i-1] = i;
//        }
//        dfs(nums, 0, target, d,visited);
//        return count;
//    }
//
//    void dfs(int[] nums, int start, int remaining, int d, int[][] visited){
//        if(start == nums.length) return;
//        // if(visited[d][start]!=-1) return visited[d][start];
//        if(remaining == 0){
//            count = (count+1)%(mod);
//            return;
//        }
//
//        for(int i=start;i<nums.length;i++){
//            if(d==0) return;
//
//            remaining-=nums[i];
//
//            if(remaining<0) break;
//
//            // visited[d][start] = remaining;
//            dfs(nums, start, remaining , d-1,visited);
//            remaining+=nums[i];
//        }
//    }


}
