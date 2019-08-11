package algo.twoPointer;

import java.util.Arrays;

public class main {
    public static void main(String[] args){
        int[] testset = new int[]{2,3,1,4,3,2};
//        System.out.println((new Solutions().removeDuplicates(testset)));

//        String s = "Sore was I ere I saw Eros.";
//        System.out.println(new Solutions().isPalindrome(s));

        int result  =new Solutions().minimizeSize(testset,7);



//        int result2 = new Solutions().lengthOfLongestSubstring("abcabcbb");
//        System.out.println(result2);

//        String result3 = new Solutions().minWindow("ADOBECODEBANC","ABC");
//        System.out.println(result3);

//        String result4 = new Solutions().LengthOfLongestSubstringKDistinct("eceba",3);
//        System.out.println(result4);

        int[][] testset2 = new int[][]{{1,5,9},{10,11,13},{12,13,15}};
        System.out.println(new Solutions().kthSmallest(testset2, 8));
//        int[] test1 = new int[]{1,7,11};
//        int[] test2 = new int[]{2,4,6};
//        System.out.println(new Solutions().kSmallestPairs(test1,test2,5));
        new Solutions().threeSumSmaller(new int[]{2,0,0,2,-2},2);
    }
}
