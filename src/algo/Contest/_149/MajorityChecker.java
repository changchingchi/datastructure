package algo.Contest._149;

import java.util.Arrays;

class MajorityChecker {

    int[] array;

    public MajorityChecker(int[] arr) {
        array = arr;
    }

    public int query(int left, int right, int threshold) {
        int[] sub = Arrays.copyOfRange(array, left,right+1);
        int[] freq = new int[20000];
        for(int i:sub){
            freq[i]++;
        }
        for(int i=0;i<freq.length;i++){
            if(freq[i]>=threshold) return i;
        }

        return -1;
    }
}