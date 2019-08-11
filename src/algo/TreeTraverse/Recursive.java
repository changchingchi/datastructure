package algo.TreeTraverse;

import java.util.List;

public class Recursive {
    public static TreeNode deepCopy(TreeNode treeNode) {
        //deep copy returns a new Tree (IN ORDER)
        if (treeNode == null) return null;

        TreeNode newTree = new TreeNode(treeNode.val);

        newTree.left = deepCopy(treeNode.left);
        newTree.right = deepCopy(treeNode.right);
        return newTree;
    }

    // left - root - right
    public static void InOrder(TreeNode tree) {
        if (tree == null) return;
        PreOrder(tree.left);
        System.out.println(tree.val);
        PreOrder(tree.right);
    }

    //  root - left - right
    public static void PreOrder(TreeNode tree) {
        if (tree == null) return;
        System.out.println(tree.val);
        InOrder(tree.left);
        InOrder(tree.right);

    }

    // left - right - root
    public static void PostOrder(TreeNode tree) {
        if (tree == null) return;
        PostOrder(tree.left);
        PostOrder(tree.right);
        System.out.println(tree.val);
    }

//    Given a binary search tree, rearrange the tree in in-order so that the leftmost node
//    in the tree is now the root of the tree, and every node has no left child and only 1 right child.

    TreeNode head; //用來定位
    TreeNode prev;  //用來追蹤
    public TreeNode increasingBST(TreeNode root) {
        // in order 遞歸模版

        //step 1
        if(root == null) return null;
        increasingBST(root.left); // will reach all the way

        //step 3
        if(head == null){
            //init
            head = root; //用來記錄最左邊的
        }

        if(prev!=null){
            //有點像linkedlist的味道
            root.left = null;
            prev.right = root;
        }

        prev = root; //更新

        //step 2
        increasingBST(root.right);
        return head;
    }

    static void helper(TreeNode root, List<Integer> result) {
        boolean end = false;
        if (root == null) {
            end = true;
            return;
        }

        helper(root.left, result);
        if (end) {
            result.add(root.val);
            System.out.println(result);
            end = false;
        }
        helper(root.right, result);
    }

//               10
//             /   \
//            8     2
//                /  \
//                3   5
//    利用递归，先遍历两个子树，来查找是否其中含有目标节点p或者q。假如两节点分别位于root的左右两侧，则root为LCA.
//    否则，left和right哪个非空，则哪一个为LCA, 这一侧含有p和q两个目标节点。

    //    https://www.youtube.com/watch?v=13m9ZCB8gjw
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        //pre order 的便利方法 現看node -> left -> right
        if (root == p || root == q) return root;  // found one side; 有一邊可以找到
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left!=null && right !=null) return root; //found
        if(left==null && right==null) return null; // not found at all;

        return left ==null? right : left; // 過程中返回不是null的那邊

    }


    public int closestValue(TreeNode root, double target) {
        TreeNode kid = root.val < target ? root.right : root.left;
        if(kid == null) {
            return root.val;
        }
        int k = closestValue(kid, target);
        return Math.abs(root.val - target) < Math.abs(k - target) ? root.val : k;
    }
// recursion


    public boolean isValidBST(TreeNode root) {
        return isValid(root,  Long.MAX_VALUE, Long.MIN_VALUE);
    }

    boolean isValid(TreeNode root, long upper, long lower){
        if(root == null) return true;

        if(root.val >= upper || root.val<=lower) return false;
        boolean left = isValid(root.left, root.val, lower);
        boolean right= isValid(root.right, upper, root.val);

        return left & right;
    }

    public int largestBSTSubtree(TreeNode root) {
        int[] res = bst(root);
        return res[2];
    }

    // return array for each node:
    //     [0] --> min
    //     [1] --> max
    //     [2] --> largest BST in its subtree(inclusive)

    int[] bst(TreeNode node){
        if(node == null) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};

        int[] left = bst(node.left);
        int[] right = bst(node.right);

        //backtracking
        if(node.val>left[1] && node.val<right[0]){
            //valid BST
            //we need to pass in the max and min in this subtree.
            return new int[]{Math.min(left[0], node.val), Math.max(right[1],node.val), left[2]+right[2]+1};
        }else{

            //若不是bst 那我們返回左右子樹裡面最大的個數 因為如果左或右有曾經是bst那我們也要記錄下來 這樣最後才知道哪個subtree最大
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left[2],right[2])};
        }
    }
}
