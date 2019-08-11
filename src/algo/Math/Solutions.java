package algo.Math;

import java.util.*;

public class Solutions {
    public String addBinary(String a, String b) {
        int i = a.length()-1;
        int j = b.length()-1;

        int sum = 0;
        int carry = 0;
        int result = 0;
        StringBuilder sb  = new StringBuilder();

        while(i>=0 || j>=0){
            int m = i>=0 ? Character.getNumericValue(a.charAt(i)) : 0;
            int n = j>=0 ? Character.getNumericValue(b.charAt(j)) : 0;

            sum = m+n+carry;
            System.out.println(sum);

            sb.insert(0, sum%2+"");
            carry = sum/2;
            i--;
            j--;
            System.out.println(m+" "+n);
        }

        if(carry!=0){
            sb.insert(0,1);
        }

        return sb.toString();
    }

//    https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/92956/Java-accepted-simple-solution
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if(nums == null) return list;

        for(int i =0; i<nums.length; i++){
            int index = Math.abs(nums[i])-1;
             if(nums[index]>0){
            nums[index] = -nums[index];
             }
        }

        for(int i=0; i<nums.length; i++){
            if(nums[i]>0){
                list.add(i+1);
            }
        }

        return list;
    }

    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int result = Integer.MIN_VALUE;

        for (int house : houses) {
            int index = Arrays.binarySearch(heaters, house);
            if (index < 0) {
                index = -(index + 1);
            }
            int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
            int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

            result = Math.max(result, Math.min(dist1, dist2));
        }

        return result;
    }

    public int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum == k) max = i + 1;

            else if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return max;
    }


    public List<String> subdomainVisits(String[] cpdomains) {
        //break down everything

        Map<String, Integer> map = new HashMap<>(); // substring and its count

        for(String s: cpdomains){
            String[] ss = s.split(" ");
            int freq = Integer.valueOf(ss[0]);
            String[] parts = ss[1].split(".");

            for(String p : parts){
                if(map.containsKey(p)){
                    map.put(p, map.get(p)+freq);
                }else{
                    map.put(p, freq);
                }
            }
        }
        System.out.println(map.size());

        List<String> result = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            result.add(entry.getKey()+" "+entry.getValue());
        }

        return result;
    }


    public boolean isPrime(int n){

        if(n==2 || n == 3) return true;

        if(n%2==0) return false;

        for(int i=3 ;i*i<=n ;i+=2){
            if(n%i==0) return false;
        }

        return true;

    }


    public void gameOfLife(int[][] board) {
        //use two bits to update the next/current state
        //another loop to shift the bit to next state

        //00 --> 0  curent dead next dead
        //01 --> 1  current live next dead
        //10 --> 2  current dead next live
        //11 --> 3  current live next live

        int row = board.length;
        int col = board[0].length;

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                int live = getNeighbor(i,j,board);
                System.out.print(live);
                if(board[i][j] == 1){
                    //currnet live case
                    if(live<2 || live>3){
                        board[i][j] = 1; //01
                    }else if(live==2|| live ==3){
                        board[i][j] = 3; //11
                    }
                }else{
                    //current dead case
                    if(live==3){
                        board[i][j] = 2; //10
                    }else{
                        board[i][j] = 0;//00
                    }
                }
            }
        }

        for(int i = 0 ; i<row; i++){
            for(int j = 0; j<col; j++){
                // System.out.print(board[i][j]);
                if(board[i][j] == 2 || board[i][j] == 3){
                    //10 or 11 : state changed
                    board[i][j] = board[i][j]>>1;
                }
            }
        }
    }
    int getNeighbor(int i, int j, int[][] board){
        int live = 0;
        int[][] dir = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};
        for(int k=0;k<8;k++){
            int newX = i+dir[k][0];
            int newY = j+dir[k][1];
            if(newX>=0 && newY>=0 && newX<board.length && newY<board[0].length){
                //check current state
                int temp = board[newX][newY]&1;
                live+=temp;

            }
        }


        return live;
    }


    public String gcdOfStrings(String str1, String str2) {
        int l1 = str1.length();
        int l2 = str2.length();

        if(l1<l2) return gcdOfStrings(str2, str1);

            for(int i=0;i<l2;i++){
                //A, AB, ABA, ABAB
                String s = str2.substring(0,i+1);
                System.out.println(s);

                String temp = str1.replaceAll(s,"");
                System.out.println(":::"+temp+" and s:" +s);
                if(temp.length()==0) return s;
            }

        return "";
    }

    public int[] addbinary(int[] arr1, int[] arr2) {
        List<Integer> list = new ArrayList<>();
        int carry = 0;
        int sum = 0;

        int i = arr1.length-1;
        int j = arr2.length-1;
        while(i>=0 || j>=0 || carry !=0){
            int inti = i>=0 ? arr1[i]:0;
            int intj = j>=0 ? arr2[j]:0;
            i--;
            j--;
            sum = inti+intj+carry;
            list.add(sum&1); //put last digit into result
            carry = (sum >> 1);
        }

        int[] res = new int[list.size()];
        for(int k=0;k<list.size();k++){
            res[k] = list.get(list.size()-k-1);
        }

        return res;
    }

    public String baseNeg2(int N) {
        if (N == 0)
            return "0";
        StringBuilder res = new StringBuilder();
        while (N != 0) {

            res.append((N & 1));
            if(N%2 != 0) N--;
            N = N / -2;
        }
        String result = res.reverse().toString();
        return result;
    }


    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += expand(s, i, i);
            count += expand(s, i, i + 1);

        }

        return count;
    }

    int expand(String s, int start, int end) {
        int count = 0;
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            count++;
            start--;
            end++;
        }
        return count;
    }




}
