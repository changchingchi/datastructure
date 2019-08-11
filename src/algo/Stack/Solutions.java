package algo.Stack;

import java.util.Stack;

public class Solutions {

    public String decodeString(String s) {
        if (s == null) {
            return s;
        }
    //s = "3[a]2[bc]", return "aaabcbc".
        //s = "3[a2[c]]", return "accaccacc".
        //s = "2[abc]3[cd]ef", return "abcabccdcdcdef".


        Stack<Object> stack = new Stack<>();
        int number = 0;
        for(char c : s.toCharArray()){
            if(Character.isDigit(c)){
                //為數字 考慮多位數字時候如何解決
                number = number*10+ (c-'0'); //變為多位數
            }else if(c=='['){
                //告訴我們準備進入字符了 已沒有倍數
                stack.push((int)number);
                number = 0; //reset
            }else if(c==']'){
                String string = popStack(stack);
                int times = (int)stack.pop(); //必須是常數 因為 ']' 結尾
                StringBuilder sb2 = new StringBuilder();
                for(int i = 0;i<times;i++){
                    sb2.append(string);
                }
                stack.push(sb2.toString());
            }else{
                //正常char
                stack.push(String.valueOf(c));
            }
        }

        return popStack(stack);
    }

    //POP 當前一直到遇到數字或是為空的 反轉過後的字串
    private String popStack(Stack<Object> stack) {

        StringBuilder sb = new StringBuilder();
        Stack<String> temp = new Stack<>();
        while(!stack.isEmpty() && stack.peek() instanceof String){
           temp.push((String) stack.pop()); //利用一個stack 反轉
        }

        while(!temp.isEmpty()){
            sb.append(temp.pop());
        }

        return sb.toString();
    }

    //好難 單調站
    int largestRectangleArea(int[] height){
        if(height == null || height.length ==0){
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for(int i=0;i<height.length;i++){
            int current = (i==height.length)?-1:height[i];
            while (!stack.isEmpty() && current <= height[stack.peek()]){

                int h = height[stack.pop()];
                int w = stack.isEmpty()? i : i - stack.peek() - 1;
                max = Math.max(max, h*w);
            }
            stack.push(i);
        }
        return  max;
    }
}
