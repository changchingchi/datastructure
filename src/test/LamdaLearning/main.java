package test.LamdaLearning;

import java.util.*;
import java.util.stream.Collectors;

public class main {


    public static void main(String[] args) throws InterruptedException {

        System.out.println("aa".hashCode());
//
//        List<Integer> list = new ArrayList<>();
//
//        list.add(3);
//        list.add(5);
//
//
//        Comparator<Integer> c = (i1, i2) -> i1 - i2;
//        list.sort(c); //descending
//        System.out.println(Arrays.toString(list.toArray()));
//
//        list.sort(Comparator.reverseOrder()); //descending
//        System.out.println(Arrays.toString(list.toArray()));
//
//
//        int[] a = {1, 2, 1,5,10};
//        System.out.println(operation(a, array -> {
//            int sum = 0;
//            for (int i : array) {
//                sum = sum + i;
//            }
//            return sum;
//        }));


        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(9);
        lists.add(7);
        lists.add(6);

        Map<Integer, String> map = new HashMap<>();


//        List<Integer> listSortedAndFilter = lists;
        lists.stream()
                .sorted((o1, o2) -> o2 - o1)
                .filter(x -> x > 6)
//                .map(x -> x*5)
                .forEach(System.out::println);


//        List<Integer> listReduceForSum = lists;

        System.out.println(lists.stream()
                .reduce(0, (i1, i2) -> i1 + i2));


        ArrayList<String> names = new ArrayList<>();
        names.add("hello");
        names.add("world");
        names.add(null);


        ArrayList<String> empty = new ArrayList<>();
        empty.add("empty");


        System.out.println(
                changeCase(names)  //or empty
                        .orElse(empty));

    }

    static Optional<List<String>> changeCase(List<String> name) {
        if (name == null) {
            return Optional.ofNullable(name);
        }
        System.out.println(name.size());
        return Optional.of(name.stream().filter(x -> x != null)
                .map(x -> x.toUpperCase())
                .collect(Collectors.toList())
        );
    }


    private static int operation(int[] array, CustomOperation add) {
        return add.operate(array);
    }
}