package algo.resursiveBinarySearch;

/**
 * Created by chchi on 8/22/17.
 */
public class app {
    public static void main(String[] args) {
        int[] array = {1, 2, 4, 6, 9, 10, 11, 20, 27, 29};
        System.out.print(binarySearch(20, 0, array.length - 1, array));
    }

    /**
     * @param target the value to search
     * @param array  the data collection
     * @return the index of the target if available, otherwise return -1
     */
    public static int binarySearch(int target, int startIndex, int endIndex, int[] array) {
        int mid;
        mid = ((startIndex + endIndex) / 2);
        if (array[mid] == target) return mid;
        if (array[mid] > target) return binarySearch(target, startIndex, mid - 1, array);
        else return binarySearch(target, mid + 1, endIndex, array);
    }
}
