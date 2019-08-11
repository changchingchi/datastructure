package JavaPractice;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class main {

    public static void main(String[] args) {
        Parent parent = new Child();
        System.out.println("Parent x: "+parent.x);

        Child child = new Child();
        System.out.println("Child x: "+child.x);

//        Child child1 = (Child) new Parent();
//        System.out.println("Child1 x: "+child1.x);


        //we create a parent reference that refer to parent
        Parent child2 = new Child();
        System.out.println(child2.x);


        //you can compare key  only.
        TreeMap<String, Integer> map = new TreeMap<String, Integer>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

    }
}




class Parent{
    int x = 5;

    @Override
    public String toString() {
        return "Parent{" +
                "x=" + x +
                '}';
    }
}

class Child extends Parent{
    int x = 6;

    void set(){

    }

    @Override
    public String toString() {
        return "Child{" +
                "x=" + x +
                '}';

    }


}