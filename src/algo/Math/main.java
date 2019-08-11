package algo.Math;

import sun.jvm.hotspot.oops.Array;

import java.util.*;

public class main {
    public static void main(String[] args) {
//

//                System.out.println(new Solutions().addBinary("10","01"));
//
//        System.out.println(new Solutions().findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
//        System.out.println(new Solutions().findRadius(  new int[]{1,2,3,4}, new int[]{1,4}));
//        System.out.println(new Solutions().subdomainVisits(new String[]{"9001 discuss.leetcode.com"}));

//        new Solutions().gameOfLife(new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}});

//         new Solutions().gcdOfStrings("TAUXXTAUXXTAUXXTAUXXTAUXX", "TAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXXTAUXX");
//        new Solutions().baseNeg2(6);

//        convert("paypalishiring", 3);
//        System.out.println(numberToWords(1000010));
//        new Solutions().countSubstrings("abc");

        List<String> cities = Arrays.asList(
                "Milan",
                "london",
                "San Francisco",
                "Tokyo",
                "New Delhi"
        );
        System.out.println(cities);
//[Milan, london, San Francisco, Tokyo, New Delhi]
        cities.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println(cities);

        Collections.sort(cities);
        System.out.println(cities);

//[london, Milan, New Delhi, San Francisco, Tokyo]
        cities.sort(Comparator.naturalOrder());
        System.out.println(cities);
    }


    //this method got some little bug, we need to dedupte the result.
    static List<List<int[]>> pairWithEqualSum(int[] nums) {
        List<List<int[]>> res = new ArrayList<>();

        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 0; i--) {
            List<int[]> tempList = new ArrayList<>();
            int right = i;
            int left = 0;
            int tempr = right;
            int templ = left;
            int sum = nums[right] + nums[left];
            boolean flag = false;
            while (templ < tempr) {

                int temp = nums[templ] + nums[tempr];

                if (temp > sum) {
                    tempr--;
                } else if (temp < sum) {
                    templ++;
                } else if (sum == temp) {
                    if (flag) {
                        tempList.add(new int[]{nums[templ], nums[tempr]});
                    }
                    flag = true;
                    tempr--;
                    templ++;
                }
            }
            if (!tempList.isEmpty()) {
                tempList.add(new int[]{nums[left], nums[right]});
                res.add(new ArrayList<>(tempList));
            }
        }

        for (int i = 0; i < nums.length; i++) {
            List<int[]> tempList = new ArrayList<>();
            int right = nums.length - 1;
            int left = i;
            int tempr = right;
            int templ = left;
            int sum = nums[right] + nums[left];
            boolean flag = false;
            while (templ < tempr) {

                int temp = nums[templ] + nums[tempr];

                if (temp > sum) {
                    tempr--;
                } else if (temp < sum) {
                    templ++;
                } else if (sum == temp) {
                    if (flag) {
                        tempList.add(new int[]{nums[templ], nums[tempr]});
                    }
                    flag = true;
                    tempr--;
                    templ++;
                }
            }
            if (!tempList.isEmpty()) {
                tempList.add(new int[]{nums[left], nums[right]});
                res.add(new ArrayList<>(tempList));
            }
        }

        return res;
    }


    static List<List<int[]>> pairWithEqualSum2(int[] nums) {
        //use hashmap directly store all the sum
        List<List<int[]>> result = new ArrayList<>();
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                if (!map.containsKey(sum)) {
                    map.put(sum, new ArrayList<>());
                }
                map.get(sum).add(new int[]{nums[i], nums[j]});
            }
        }

        for (Map.Entry<Integer, List<int[]>> e : map.entrySet()) {
            if (e.getValue().size() == 2) {
                result.add(new ArrayList<>(e.getValue()));
            }
        }

        return result;
    }

    //https://leetcode.com/problems/zigzag-conversion/
    public static String convert(String s, int numRows) {

        if(s == null || s.isEmpty() || numRows <=1) return s;

        StringBuilder[] sb = new StringBuilder[numRows];

        for(int i=0;i<numRows;i++){
            sb[i] = new StringBuilder();
        }

        int count = 0;
        while(count<s.length()){

            //vertical
            for(int i = 0; i<numRows && count<s.length(); i++){
                sb[i].append(s.charAt(count));
                count++;
            }

            //go back up
            for(int i= numRows-1-1; i>=1 && count<s.length();i--){
                sb[i].append(s.charAt(count));
                count++;
            }
        }

        for(int i=1; i<sb.length; i++){
            sb[0].append(sb[i]);
        }

        return sb[0].toString();
    }



    public static String numberToWords(int num) {

        //1. create num to string map.
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourthtenn");
        map.put(15, "Fifthteen");
        map.put(16, "Sixthteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Fourty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninty");

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("One",1);
        map2.put("Two",2);
        map2.put("Three",3);
        map2.put("Four",4);
        map2.put("Five",5);
        map2.put("Six",6);
        map2.put("Seven",7);
        map2.put("Eight",8);
        map2.put("Nine",9);
        //process per 3 digit

        Stack<String> stack = new Stack<>();
        int scale = 0;
        while(num>0){
            int temp = num%1000; //last 3 digit
            String temp_res = helper(temp, scale, map, map2);
            if(!temp_res.isEmpty()){
                stack.push(temp_res);
            }

//            System.out.println(temp_res);
            scale++;
            num = num/1000;
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop()+" ");
        }

        return sb.toString().trim();
    }

    static String  helper(int num, int scale, Map<Integer, String> map, Map<String, Integer> map2){
        //123
        int origin = num;
        StringBuilder sb = new StringBuilder();
        Stack<String> stack = new Stack<>();
        for(int i = 0 ; i <3 && num>0; i++){

            int temp = num%10; //last digit
            if(i==1 ){ //handle second digit and it is = 1
                if(temp == 1){
                    int last = map2.getOrDefault(stack.pop(),0); //take the last digit back
                    stack.push(map.get(temp*10+last)); //113 --> instead of 3 it become thirteen.
                }else{
                    temp*=10;
                    stack.push(map.get(temp));
                }

            }else if(i==2){
                //first digit 100s
                stack.push(map.get(temp)+" Hundred");
            }else{
                stack.push(map.get(temp));
            }
            num = num/10;
        }

        //append and handle scale;

        while(!stack.isEmpty()){
            String s = stack.pop();
            if(s!=null){
                sb.append(s + " ");
            }

        }
        if(origin > 0){
            if(scale == 1){
                sb.append("Thousand");
            }else if(scale ==2){
                sb.append("Million");
            }else if(scale ==3){
                sb.append("Billion");
            }

        }

        return sb.toString().trim();
    }
}
