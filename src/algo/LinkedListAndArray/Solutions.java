package algo.LinkedListAndArray;

import java.util.*;

public class Solutions {

    public ListNode reverseList(ListNode head) {

//1 2 3 4
//c
        if (head == null) {
            return head;
        }

        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            ListNode temp = cur.next; //cache
            cur.next = prev; //斷開
            prev = cur; // move prev 指針 to curr (將cur的值賦給 prev)
            cur = temp; // move cur 指針 to temp
        }
        return prev;
    }


    public ListNode reverseList2(ListNode head) {

//1 2 3 4
//c
        if (head == null) {
            return head;
        }

        ListNode cur = head;
        ListNode prev = null;
        ListNode next = null;

        while (cur != null) {
            next = cur;
            cur = cur.next;
            next.next = prev;
            prev = next;
        }

        head = prev;
        return head;
    }


    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return head;

        //deep copy 需要一個hashmap來記錄 當前跟newNode的mapping
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode pre = dummy, newNode;

        while (head != null) {
            //走過一遍並且更新newNode

            if (map.containsKey(head)) {
                newNode = map.get(head); // 以head為key的value
            } else {
                //不在map中，存入以便random point可以比對
                newNode = new RandomListNode(head.label);
                map.put(head, newNode);
            }

            pre.next = newNode; //連接起來

            //處理random
            if (head.random != null) {
                if (map.containsKey(head.random)) {
                    newNode.random = map.get(head.random);
                } else {
                    //存入
                    newNode.random = new RandomListNode(head.random.label);
                    map.put(head.random, newNode.random);
                }
            }
            pre = newNode;
            head = head.next;
        }

        return dummy.next;
    }


    public boolean hasCycle(ListNode head) {
        //方法1:簡單粗暴：存入hashset 查看是否有重複
        if (head == null) return false;

        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }


    //解法二：倒過來有後往前 先塞最大
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        if (nums1 == null || nums2 == null || m == 0 || n == 0) {
            return;
        }
        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] < nums2[j]) {
                nums1[index] = nums2[j];
                j--;
                index--;
            } else {
                nums1[index] = nums1[i];
                i--;
                index--;
            }
        }

        while (i >= 0) {
            nums1[index] = nums1[i];
            index--;
            i--;
        }
        while (j >= 0) {
            nums1[index] = nums2[j];
            index--;
            j--;
        }
    }


    //遞歸三要素
    //merge sort : 1. 分半 2.sort 左 , sort右 3.合併

    //遞歸的定義：1. 分半 2.sort 左 , sort右 3.合併
    public ListNode sortList(ListNode head) {
        //鍊錶操作merge只花費O(1) space
        // nlogn time complexity

        //遞歸的出口
        if (head == null || head.next == null) return head;
        //以merge sort出發
        //find mid (half)
        //sort left and right
        //merge

        //遞歸的拆解
        ListNode mid = findMid(head);
        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);
        return mergeTwoSort(left, right);
    }

    private ListNode findMid(ListNode head) {

        //fast and slow
        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    private ListNode mergeTwoSort(ListNode left, ListNode right) {
        // dummy and tail
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (left != null && right != null) {
            if (left.val < right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            //每次做完一次輪迴更新tail
            tail = tail.next;
        }

        if (left != null) {
            tail.next = left;
        }

        if (right != null) {
            tail.next = right;
        }

        return dummy.next;
    }

    //move the front one to last
    public ListNode moveToLast(ListNode head) {
        if (head == null || head.next == null) return head;
        //two pointer
        ListNode seclast = null;
        ListNode last = head;

        while (last.next != null) {
            seclast = last;
            last = last.next;
        }
        seclast.next = null;
        last.next = head;

        return last;
    }

    //全連續子數組之和 => presum[j+1] - presum[i] = k
    //求連續子數組和為0
    public List<Integer> subarraySum(int[] nums) {

        int len = nums.length;

        ArrayList<Integer> ans = new ArrayList<Integer>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        map.put(0, -1); //(sum, index)
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (map.containsKey(sum)) {
                //因為題目 求 和=0 所以其實是求 hashmap裡面是不是有 出現過的 presum[j+1] = presum[i]
                ans.add(map.get(sum) + 1); //加回來原本的dummy -1
                ans.add(i);
                return ans;
            }
            map.put(sum, i);
        }

        return ans;
    }

    public boolean isPalindrome(ListNode head) {
        //快慢指針 走到一半 反轉右半邊
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //handle odd
        if (fast != null) {
            slow = slow.next; //skip the mid one
        }

        ListNode prev = null;
        ListNode next = null;
        ListNode cur = slow;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        fast = head;

        while (fast != null && slow != null) {
            System.out.println("head: " + head.val + " prev:" + prev.val);
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }

        return true;
    }

    //    prime : 3 5 7 13...
    // e.g. list: 1 3 2 5 8 9 --> 1 2 8 9
    public ListNode removePrime(ListNode head) {
        if (head == null) return head;

        ListNode prev = null;
        ListNode dummy = new ListNode(1);
        prev = dummy;
        ListNode cur = head;
        dummy.next = cur;
        while (cur != null) {
            if (isPrime(cur.val)) {
                //remove
                prev.next = cur.next;
                cur = cur.next;
            } else {
                //not remove
                prev = prev.next;
                cur = cur.next;
            }
        }

        return dummy.next;

    }

    boolean isPrime(int i) {
        if (i == 2) return true;
        if (i % 2 == 0) return false;
        if (i <= 1) return false;

        for (int n = 3; n * n <= i; n += 2) {
            if (i % n == 0) return false;
        }
        return true;
    }

//https://www.geeksforgeeks.org/find-first-non-repeating-character-stream-characters/
    //geeksforgeeksandgeeksquizfor

    //一個visited數組來表記是否char出現兩次以上
    void findFirstNonRepeating(String s) {

        boolean[] visited = new boolean[256];
        List<Character> list = new ArrayList<>();

        for (char c : s.toCharArray()) {
            if (!visited[c]) {
                if (!list.contains(c)) {
                    list.add(c);
                } else {
                    list.remove((Character) c);
                    visited[c] = true;
                }
            }

            if (list.size() != 0) {
                System.out.println(list.get(0));
            }
        }


    }


    public boolean wordPattern(String pattern, String str) {
        String[] array = str.split(" ");

        if (pattern.length() != array.length) return false;
        Map<String, List<Integer>> map = new HashMap<>();


        for (int i = 0; i < pattern.length(); i++) {
            String c = pattern.charAt(i) + "";
            if (!map.containsKey(c)) {
                map.put(c, new ArrayList<>());
            }
            map.get(c).add(i);
        }

        //a(0,3)
        //b(1,2)


        for (List<Integer> cur : map.values()) {
            int p = cur.get(0);
            for (int i = 0; i < cur.size(); i++) {
                if (!array[cur.get(i)].equals(array[p])) return false;
            }
        }
        return true;
    }


    public int[] findPermutation(String s) {
        int[] res = new int[s.length()];

        for (int i = 1; i <= res.length; i++) {
            res[i - 1] = i;
        }
        //12345

        //DDDI
        //   i
        //   e

        //43215

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'D') {
                System.out.println(s.charAt(i));
                int end = i;
                while (end < s.length() && s.charAt(end) != 'I') {
                    end++; //find the next I from current D
                }
                reverse(res, i, end - 1);

            }
            System.out.println("i: " + i);
        }

        return res;

    }

    void reverse(int[] res, int start, int end) {
        int s = start;
        int e = end;
        while (s < e) {
            System.out.println("ss");
            int temp = res[s];
            res[s] = res[e];
            res[e] = temp;
            s++;
            e--;
        }
    }



    public int[] advantageCount(int[] A, int[] B) {
        int[] Aclone = A.clone();
        Arrays.sort(Aclone);
        int[] res = new int[A.length];
        boolean[] visited = new boolean[A.length];
        boolean found ;
        for(int i=0;i<B.length;i++){
            int cur = B[i];
            found = false;
            System.out.println("cur: "+cur);
            for(int j=0;j<Aclone.length;j++){
                if(Aclone[j]>cur&&!visited[j]){
                    System.out.println("aclone: "+Aclone[j]);
                    res[i] = Aclone[j];
                    visited[j] = true;
                    found = true;
                    break;
                }
            }
            System.out.println("cur i:" + i);
            if(!found){
                System.out.println("not found: "+ A[i]);
                for(int k=0;i<Aclone.length ; k++){
                    if( !visited[k]) {
                        res[i] = Aclone[k];
                        visited[k] = true;
                        break;
                    }
                }

            }

        }

        return res;
    }

//    Given an array nums of length n. All elements appear in pairs except one of them. Find this single element that appears alone.
//    Pairs of the same element cannot be adjacent:
//
//            [2, 2, 1, 2, 2] // ok
//            [2, 2, 2, 2, 1] // not allowed
//    Example 1:
//
//    Input: [2, 2, 1, 1, 9, 9, 5, 2, 2]
    //                             i
    //                                j
//    Output: 5
//    Example 2:
//
//    Input: [1, 1, 2]
//    Output: 2
//    Example 3:
//
//    Input: [3, 3, 2, 3, 3]
//    Output: 2

    //[2, 2, 1, 2, 2]
    //          i
    //             j
    int singleElement(int[] nums){

        int i=0;
        int j=i+1;
        int res = -1;
        while(i<nums.length){
            if(i==nums.length-1){
                res = nums[i];
                break;
            }
            if(nums[i]==nums[j]){
                i+=2;
                j=i+1;
            }else{
                res = nums[i];
                break;
//                i++;
//                j=i+1;
            }
        }

        return res;
    }
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length<nums2.length) return intersect(nums2, nums1);

        //nums1 alway large than nums2
        List<Integer> res = new ArrayList<>();
        int max = 0;
        for(int i=0;i<nums1.length-nums2.length+1;i++){
            List<Integer> temp = new ArrayList<>();
            for(int j=i;j-i<nums2.length;j++){
                // System.out.println(nums1[j] +":"+nums2[j]);
                if(nums1[j] == nums2[j-i]){
                    temp.add(nums1[j]);
                }else {
                    continue;
                }
            }
            if(temp.size()>max){
                res = temp;
                max = temp.size();
            }
        }

        int[] ret = new int[res.size()];
        int c = 0;
        for(int i: res){
            ret[c] = res.get(c);
            c++;
        }

        return ret;

    }

}
