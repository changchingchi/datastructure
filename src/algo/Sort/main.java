package algo.Sort;

public class main {

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        ms.sortArray(new int[]{5,2,3,1});

        QuickSort qs = new QuickSort();
        qs.sort(new int[]{5,2,3,1});
    }

}
