package test.LamdaLearning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class PlayWithLambdaSolutions {
    public static void main(String[] args) {
        String[] planetNames = { "Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};

        sortByName(new ArrayList<String>(Arrays.asList(planetNames)));
        sortByStringLength(new ArrayList<String>(Arrays.asList(planetNames)));

        //TODO: use the higher order function sortThenDo to sort by name then print out the list
         sortThenDo(new ArrayList<String>(Arrays.asList(planetNames)),
                 ((o1, o2) -> o1.charAt(0)-o2.charAt(0)),
                 System.out::println
         );
    }

    private static void sortByName(List<String> planets) {
        //ascending
        planets.sort(((o1, o2) -> o1.charAt(0)-o2.charAt(0)));
    }

    private static void sortByStringLength(List<String> planets) {
        //ascending
        planets.sort(((o1, o2) -> o1.length()-o2.length()));
    }

    private static void sortThenDo(List<String> l, Comparator<String> sorter, Consumer<String> action) {
        l.sort(sorter);
        l.forEach(action);
    }

}