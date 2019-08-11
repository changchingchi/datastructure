package algo.TreeTraverse;

import java.util.*;

public class Solutions {

    public  TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;

        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);

        return t1;
    }


     int findheight(TreeNode root) {
        if (root == null) return 0;

        int right = findheight(root.right) + 1;
        int left = findheight(root.left) + 1;

        return Math.max(right, left);

    }



    public  String serialize2(TreeNode root) {
        //level order
        //only print when null

        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            TreeNode cur = queue.poll();
            for(int i = 0 ;i<size; i++){
                sb.append(cur.val + ",");
                if(cur.left != null){
                    queue.add(cur.left);
                }else{
                    sb.append("null,");
                }

                if(cur.right !=null){
                    queue.add(cur.right);
                }else{
                    sb.append("null,");
                }
            }
        }

        return sb.toString().substring(0, sb.length()-1);
    }

    public  String serialize(TreeNode root) {

        //遇到null不放到queue裡面 直接打印
        StringBuilder sb = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur != null) {
                    sb.append(cur.val + ",");
                    queue.add(cur.left);
                    queue.add(cur.right);
                } else {
                    sb.append("null,");
                }
            }
        }

        return sb.deleteCharAt(sb.length()-1).toString();

    }

    public  TreeNode deSerialize(String data) {

           /* Constructed binary tree is
            10
          /   \
        8      2
      /  \    /
    3   5   2
    */
        // 10,8,2,3,5,2,null,null,null,null,null,null,null
        if (data == null || data.length() == 0) return null;
        String[] nodes = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();

        TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
        queue.add(root);

        for (int i = 1; i < nodes.length; i += 2) {

            TreeNode cur = queue.poll();

            //left
            if (!nodes[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(nodes[i]));
                cur.left = left;
                queue.add(cur.left);
            }

            //right
            if (!nodes[i+1].equals("null") && i + 1 < nodes.length) {
                TreeNode right = new TreeNode(Integer.parseInt(nodes[i + 1]));
                cur.right = right;
                queue.add(cur.right);
            }

        }


        return root;
    }

    public  TreeNode sortedArrayToBST(int[] nums) {

        if(nums==null) return null;
        return helper(nums, 0, nums.length-1);
    }

     TreeNode helper(int[] nums, int start, int end){
        if(start>end) return null;

        int mid = start + (end - start)/2;
        TreeNode res = new TreeNode(nums[mid]);
        System.out.println("mid: "+mid);
        res.left = helper(nums, start, mid-1);
        res.right = helper(nums, mid+1, end);

        return res;
    }


     TreeNode pree = null;
    public  boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false;
        if (pree != null && pree.val >= root.val) return false;
        pree = root;
        return isValidBST(root.right);
    }

    public  int countNodes(TreeNode root) {
        if (root == null) return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }


    //    https://leetcode.com/contest/weekly-contest-146/problems/minimum-cost-tree-from-leaf-values/
    public   int mctFromLeafValues(int[] A) {
        int res = 0, n = A.length;
        java.util.Stack<Integer> stack = new java.util.Stack<Integer>();
        stack.push(15);
        for (int a : A) {
            while (stack.peek() <= a) {
                int mid = stack.pop();
                res += mid * Math.min(stack.peek(), a);
            }
            stack.push(a);
        }
        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }

    //给一棵二叉树, 找到和为最小的子树, 返回其根节点。
//    https://www.jiuzhang.com/solutions/minimum-subtree/

    TreeNode subtree = null;
    int subtreeSum = Integer.MIN_VALUE;
    public  TreeNode findSubtree(TreeNode root) {
        findSum(root);
        return subtree;

    }

     int findSum(TreeNode root){

        if(root == null) return 0;

        int sum = findSum(root.left) + findSum(root.right) + root.val;
        if(subtreeSum<=sum){
            subtreeSum = sum;
            subtree = root; //replace the subtree with current node
        }
        return sum;
    }




    int p;

    public TreeNode str2tree(String s) {
        if (s == null || s.isEmpty()) return null;
        p = 0;
        s = "(" + s + ")";
        return build(s);
    }

    private TreeNode build(String s) {
        int start = p + 1;
        int end = start + 1;
        while (end < s.length() && Character.isDigit(s.charAt(end))) end++;
        int val = Integer.valueOf(s.substring(start, end));
        TreeNode root = new TreeNode(val);
        p = end;
        if (s.charAt(p) == '(') {
            root.left = build(s);
            if (s.charAt(p) == '(') {
                root.right = build(s);
            }
        }
        p++;
        return root;
    }


    //if give you a tree, build a map that a node knows who its parent is.
    //start with (map, root, null)
    void buildParentNode(Map<Integer, List<Integer>> map, TreeNode current, TreeNode parent){
        if(current == null) return;

        if(!map.containsKey(current.val)){
            map.put(current.val, new ArrayList<>());
        }

        if(parent!=null){
            List<Integer> n = map.get(current.val);
            n.add(parent.val);
            map.put(current.val, n);
            List<Integer> n2 = map.get(parent.val);
            n2.add(current.val);
            map.put(parent.val, n2);
        }

        buildParentNode(map, current.left, current);
        buildParentNode(map, current.right, current);
    }

    //if give you a tree, build a map that a node knows who its parent is.
    //start with (map, root, null)
    void buildParentNodePostOrder(Map<Integer, List<Integer>> map, TreeNode current){
        if(current == null) return;

        buildParentNodePostOrder(map, current.left);
        buildParentNodePostOrder(map, current.right);

        if(!map.containsKey(current.val)){
            map.put(current.val, new ArrayList<>());
        }
        if(current.left != null){
            List<Integer> n = map.get(current.val);
            n.add(current.left.val);
            map.put(current.val,n);

            List<Integer> n2 = map.get(current.left.val);
            n2.add(current.val);
            map.put(current.left.val, n2);
        }

        if(current.right != null){
            List<Integer> n = map.get(current.val);
            n.add(current.right.val);
            map.put(current.val,n);

            List<Integer> n2 = map.get(current.right.val);
            n2.add(current.val);
            map.put(current.right.val, n2);
        }
    }



    TreeNode halfNodedfs(TreeNode root){
        if(root == null ) return null;

        root.left = halfNodedfs(root.left);
        root.right= halfNodedfs(root.right);

        if(root.left != null && root.right!=null){
            return root;
        }

        if(root.left == null && root.right != null){
            TreeNode newNode = new TreeNode(root.right.val);
            return newNode;
        }
        if(root.left != null && root.right == null){
            TreeNode newNode = new TreeNode(root.left.val);
            return newNode;
        }
        return root;
    }


    Integer prev, ans;
    public int minDiffInBST(TreeNode root) {
        prev = null;
        ans = Integer.MAX_VALUE;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (prev != null)
            ans = Math.min(ans, node.val - prev);
        prev = node.val;
        dfs(node.right);
    }


}
