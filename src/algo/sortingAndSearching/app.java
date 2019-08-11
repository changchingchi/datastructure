package algo.sortingAndSearching;

import java.util.*;

/**
 * Created by chchi on 8/22/17.
 */
public class app {
    public static void main(String[] args) {
        int[] array = {4, 15, 5, 12, 1, 3};
        int[] a = {2, 4, 6, 8, 0, 0, 0, 0};
        int[] b = {1, 3, 5, 7};
        String[] s = {"abc","asd","acb","dsa"};
        System.out.println(groupAnagrams2(s));
    }

    /**
     * @param target the value to search
     * @param array  the data collection
     * @return the index of the target if available, otherwise return -1
     */
    public static int[] bubbleSort(int[] array) {
        if (array.length == 0 || array.length < 1) return array;
        int n = array.length;
        int temp;
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < n - j - 1; i++) {
                if (array[i] > array[i + 1]) {
                    temp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }

    public static int[] selectionSort(int[] array) {
        if (array.length == 0 || array.length < 1) return array;
        int n = array.length;
        int temp;
        int min;
        for (int i = 0; i < n - 1; i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            //swap current array[i] with array[min]
            temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
        return array;
    }


    public static int[] quickSort(int[] array, int left, int right) {
        int secondSegmentIndex = partition(array, left, right);
        // the final secondSegmentIndex index is at its final position. the secondSegmentIndex forms two subarrays.
        // We then recusively divide and conqure till it has only one element in each sub array. (base condition)
        if (left < secondSegmentIndex - 1) {
            quickSort(array, left, secondSegmentIndex - 1);
        }
        if (secondSegmentIndex < right) {
            quickSort(array, secondSegmentIndex, right);
        }
        return array;
    }

    /**
     * The goal of the partition is to break the array into two segments.
     * The first segment contains elements [1,2,3,3]. All of these values are less than or equal to four.
     * The second segment contains elements [5,6,4]. All of these values are greater than or equal to four.
     */
    private static int partition(int[] array, int left, int right) {

        int pivot = array[(left + right) / 2];
//        int pivot = getMedian(array, left, right);
        while (left <= right) {
            while (array[left] < pivot) left++;
            while (array[right] > pivot) right--;
            if (left <= right) {
                //swap
                int temp = array[right];
                array[right] = array[left];
                array[left] = temp;
                right--;
                left++;

            }
        }

        return left;
    }

    public static void swap(int[] a, int left, int right) {
        int temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

    public static int getMedian(int[] a, int left, int right) {
        int center = (left + right) / 2;

        if (a[left] > a[center])
            swap(a, left, center);

        if (a[left] > a[right])
            swap(a, left, right);

        if (a[center] > a[right])
            swap(a, center, right);

        swap(a, center, right);
        return a[right];
    }

    public static int[] mergeTwoSortedArray(int[] a, int[] b) {
        // a = 1,3,5,7
        // b = 2,4,6,8
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        //we need two index to see who arrives the end first. then we copy the other's rest of elements.
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                c[k] = a[i];
                i++;
                k++;
            } else {
                c[k] = b[j];
                j++;
                k++;
            }
        }

        //a finished,  b has elements left, move all of them to c
        while (j < b.length) {
            c[k] = b[j];
            j++;
            k++;
        }
        while (i < a.length) {
            c[k] = a[i];
            i++;
            k++;
        }
        return c;
    }

    public static int[] sortedMerge(int[] A, int m, int[] B, int n) {
        //assume A have enough space to hold B.
        // A and B are sorted array.
        // A = {2,4,6,8, , , , }
        // B = 1,3,5,7

        int i = m - 1;
        int j = n - 1;
        int k = i + j + 1; //last index of merged array A;
        while (i >= 0 && j >= 0) {
            //from end to start
            if (A[i] > B[j]) {
                A[k] = A[i];
                k--;
                i--;
            } else {
                A[k] = B[j];
                k--;
                j--;
            }
        }
        while (i >= 0) { // why >=0 ? because we i-- after copy the last element, i == -1.
            A[k] = A[i];
            i--;
            k--;
        }
        while (j >= 0) {
            A[k] = B[j];
            j--;
            k--;
        }
        return A;
    }

    //10.2 write a method to sort an array of strings so that all the anagrams are next to each other
    public static String groupAnagrams(String[] array) {
        anagramComparator comparator = new anagramComparator();
        Arrays.sort(array, comparator);
        return Arrays.toString(array);
    }

    static class anagramComparator implements Comparator<String> {

        //make each input string sorted.
        public String preprocessing(String s) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }

        @Override
        public int compare(String o1, String o2) {
            //use String method compareTo to compares two strings lexicographically.
            return preprocessing(o1).compareTo(preprocessing(o2));
        }
    }

    public static String groupAnagrams2(String[] array){
        //1. loop thru all strings
        //2. sort each of them and use sorted string as key, original string as value in hashmap(keep adding into the same cell
        //3. loop thru hashmap and form a result.
        Map<String,ArrayList<String>> map = new HashMap<>();
        char[] s = null;
        for(String i:array){
            s=i.toCharArray();
            Arrays.sort(s);
            String newString = new String(s);
            if(map.containsKey(newString)){
                map.get(newString).add(i);
            }else{
                ArrayList<String> list = new ArrayList<>();
                list.add(i);
                map.put(newString,list);
            }
        }
        int k = 0;
        for(String key: map.keySet()){
            ArrayList<String> anagram = map.get(key);
            for(int i =0;i<anagram.size();i++){
               array[k]=anagram.get(i);
               k++;
            }
        }
       return Arrays.toString(array);
    }

    public static int SearchInRotatedArray(int[] a, int left, int right, int x){
        int mid = (left+right)/2;
        if(a[mid]==x){
            return mid;
        }
        if(a[left]<a[mid]){
            //left to mid is in normal accesding order
            if(x>=a[left] && x<=a[mid]){
                //check if x is in this range, if not, start recursion for the other half.
                SearchInRotatedArray(a,left,mid-1,x);
            }else{
                SearchInRotatedArray(a,mid+1,right,x);
            }
        }else if(a[left]>a[mid]){
            //right to mid is in normal order, thus binary search for x
           if(x>=a[mid] && x<=a[right]){
               SearchInRotatedArray(a,mid+1,right,x);
            }else{
               SearchInRotatedArray(a,left,mid-1,x);
           }
        }
        return -1; // not found
    }
}
