package algo.binarySearch;

/**
 * Created by chchi on 8/22/17.
 */
public class app {
    public static void main(String[] args) {
        int[] array = {1,2,4,6,9};
        System.out.print(binarySearch(6,array));
    }

    /**
     * @param target the value to search
     * @param array the data collection
     * @return the index of the target if available, otherwise return -1
     * */
    public static int binarySearch(int target, int[] array) {
        int start = 0;
        int end = array.length;
        int mid;
        int result = -1;
        while (start <= end) {
            mid = ((start + end) / 2);
            if (array[mid] == target) result = mid;
            if (array[mid] > target) end = mid - 1;
            else start = mid + 1;
        }
        return result;
    }

}
