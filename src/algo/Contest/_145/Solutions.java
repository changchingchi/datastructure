package algo.Contest._145;

import java.util.*;

public class Solutions {


    public int[] relativeSortArray(int[] arr1, int[] arr2) {

        int[] res = new int[arr1.length];
        List<int[]> map = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        for(int i=0;i<arr1.length;i++){
            list.add(arr1[i]);
        }

        for(int i =0;i<arr2.length;i++){
            int freq = findFreq(arr1, arr2[i]);
            map.add(new int[] {arr2[i],freq}); //since arr2 is distinct no need to GET first.
            while(list.contains(arr2[i])){
                list.remove(Integer.valueOf(arr2[i]));
            }
        }

        //(2,3), (1,1) , (4,1), (3,2), (9,1), (6,1), (7,0), (19,0)
        //now we have to add elements that's not in arr2
        int cur = 0;
        for(int[] i:map){
            int key = i[0];
            int freq = i[1];
            while(freq>0){
                res[cur]=key;
                freq--;
                cur++;
            }
        }

        Collections.sort(list);
        for(int i = 0;i<list.size();i++){
            res[cur] = list.get(i);
            cur++;
        }

        return res;
    }


    int findFreq(int[] arr1, int target){
        int count = 0;
        for(int i:arr1){
            if(i==target){
                count++;
            }
        }

        return count;
    }




    /////

    public int longestWPI(int[] hours) {
        int res = 0, score = 0, n = hours.length;
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);
        for (int i = 0; i < n; ++i) {
            score += hours[i] > 8 ? 1 : -1;
            if (score > 0) {
                res = i + 1;
            } else {
                seen.putIfAbsent(score, i);
                if (seen.containsKey(score - 1))
                    res = Math.max(res, i - seen.get(score - 1));
            }
        }
        return res;
    }

}
