package algo.TreeTraverse;

public class Stack {

    public static TreeNode deepCopy(TreeNode tree){
        // use PreOrder traverse to do so.

        java.util.Stack<Integer> stack = new java.util.Stack<>();
        return null;
    }

    //root - left - right
    public static void PreOrder(TreeNode tree){

        java.util.Stack<TreeNode> stack = new java.util.Stack<>();
        stack.push(tree);

        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            System.out.println(cur.val);
            //反過來的順序 因為stack 的特性
            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left !=null){
                stack.push(cur.left);
            }
        }

    }
// left -- root -- right
    public static void InOrder(TreeNode tree){
        java.util.Stack<TreeNode> stack = new java.util.Stack<>();
        stack.push(tree);

        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            System.out.println(cur.val);
            //反過來的順序 因為stack 的特性
            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left !=null){
                stack.push(cur.left);
            }
        }
    }

    public static void PostOrder(TreeNode tree){

    }
}
