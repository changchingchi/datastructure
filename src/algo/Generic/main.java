package algo.Generic;

import algo.UnionFind.Point;
import algo.UnionFind.Solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class main {
    public static void main(String[] args) {
        System.out.println(max(3, 5, 3));

        List<Integer> result = new ArrayList<>();

        result.add(33);
        result.add(20);
        result.add(10);
        result.add(55);


        Collections.sort(result, (o1, o2) -> o1 - o2);
        System.out.println(result.toString());

    }

    //3 params with type T and return type T. the <T extends...> part is just the syntax to
    //tell compiler that my input type is bounded to anything that impliments comparable.
    public static <T extends Comparable<T>> T max(T x, T y, T z) {

        T max = x;
        if (y.compareTo(x) > 0) {
            max = y;
        }
        if (z.compareTo(max) > 0) {
            max = z;
        }

        return max;
    }
}
