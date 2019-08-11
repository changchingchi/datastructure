package algo.LinkedListAndArray;

import java.util.Iterator;
import java.util.List;

public class main {
    public static void main(String[] args){
//        ListNode list = new ListNode(4);
//        list.next = new ListNode(2);
//        list.next.next = new ListNode(3);
//        list.next.next.next = new ListNode(1);
//        ListNode result = new Solutions().reverseList2(list);
//        while(result.next!=null){
//            System.out.println(result.val);
//            result = result.next;
//        }
//        System.out.println(result.val);
//
        ListNode one  = new ListNode(3);
        ListNode two  = new ListNode(5);
        ListNode three  = new ListNode(8);
        ListNode four  = new ListNode(9);

        one.next = two;
        two.next = three;
        three.next = four;

        System.out.println(new Solutions().removePrime(one));
//
//        one.random = four;
//        two.random = three;
//        three.random  = two;
//        four.random = null;
//
//        System.out.println(new Solutions().copyRandomList(one));

//                System.out.println(new Solutions().hasCycle(list));

//        int[] a = new int[]{0};
//        int[] b = new int[]{1};
//
//       new Solutions().merge(a,0,b,1);
//        new Solutions().sortList(list);


//        System.out.println(new Solutions().subarraySum(new int[]{1,-1,0}));
//    System.out.println(new Solutions().isPalindrome(one));
//        new Solutions().findFirstNonRepeating("geeksforgeeks");

//        new Solutions().wordPattern("jquery", "jquery");
        new Solutions().advantageCount(new int[]{5621,1743,5532,3549,9581}, new int[]{913,9787,4121,5039,1481});

        System.out.println(new Solutions().singleElement(new int[]{1, 1, 2}));
        System.out.println(new Solutions().singleElement(new int[]{3, 3, 2, 3, 3}));
        System.out.println(new Solutions().singleElement(new int[]{2, 2, 1, 1, 9, 9, 5, 2, 2}));
        new Solutions().intersect(new int[]{1,2,2,1}, new int[]{2,2});
    }

}
