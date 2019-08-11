package algo.TreeTraverse;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class main {

    public static void main(String[] args) {

         /* Constructed binary tree is
            10
          /   \
        8      2
              /  \
             3   5
  */
        TreeNode tree = new TreeNode(10);
        tree.left = new TreeNode(8);
        TreeNode two = new TreeNode(2);
        tree.right = two;
        tree.right.left = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        tree.right.right = five;

        System.out.println(new Solutions().countNodes(tree));
        new Solutions().isValidBST(tree);

        Recursive.lowestCommonAncestor(tree, tree.right.left, five );


//        Recursive.increasingBST(tree);
        new Solutions().sortedArrayToBST(new int[]{-10,-3,0,5,9});

        // =============Recursive=========
        TreeNode newTree = Recursive.deepCopy(tree);

        Recursive.InOrder(tree);
        System.out.println("======");

        TreeNode tree2 = new TreeNode(1);
        tree2.right = new TreeNode(2);
        tree2.right.left = new TreeNode(3);

        Recursive.PreOrder(tree2);
        System.out.println("======");

        Recursive.PostOrder(tree);
        System.out.println("======");


        //=================Stack======

        TreeNode newTree2 = Stack.deepCopy(tree);

        Stack.PreOrder(tree);
        System.out.println("======");

        Stack.InOrder(tree);
        System.out.println("======");

        Stack.PostOrder(tree);


        TreeNode tree4 = new TreeNode(1);
        tree4.left = new TreeNode(3);
        tree4.right = new TreeNode(2);

        TreeNode tree5 = new TreeNode(1);
        tree5.left = new TreeNode(3);
        tree5.right = new TreeNode(2);

        new Solutions().mergeTrees(tree4, tree5);

//
        System.out.println(new Solutions().serialize2(tree));
        System.out.println(new Solutions().serialize(tree));
        System.out.println(new Solutions().mctFromLeafValues(new int[]{6,2,4}));
//        deSerialize(serialize(tree));

//        System.out.println(serialize(deSerialize("10,8,2,null,null,3,5,null,null,null,null")));

        new Solutions().str2tree("4(2(3)(1))(6(5))");
//        new Recursive().largestBSTSubtree(deserialize("10,5,15,1,8,null,7"));
        new Solutions().buildParentNodePostOrder(new HashMap<>(),deserialize("3,5,1,6,2,0,8,null,null,7,4"));

        System.out.println(serialize(new Solutions().halfNodedfs(deserialize("3,5,1,null,2,0,8,null,null,7,4"))));

        new Solutions().minDiffInBST(deserialize("1,null,7,2,null"));
    }

    static String serialize(TreeNode root){
        StringBuilder sb = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i <size; i++){
                TreeNode cur = queue.poll();
                if(cur == null) {
                    sb.append("null,");
                }else{
                    sb.append(cur.val+",");
                    queue.add(cur.left);
                    queue.add(cur.right);

                }

            }
        }

        return sb.toString();
    }

    static TreeNode deserialize(String tree){

        String[] nodes = tree.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(nodes[0]));
        queue.add(root);

            for(int i=1;i<nodes.length;i+=2) {
                TreeNode cur = queue.poll();

                if (!nodes[i].equals("null")) {
                    TreeNode left = new TreeNode(Integer.valueOf(nodes[i]));
                    cur.left = left;
                    queue.add(cur.left);
                }

                if (!nodes[i + 1].equals("null")) {
                    TreeNode right = new TreeNode(Integer.valueOf(nodes[i + 1]));
                    cur.right = right;
                    queue.add(cur.right);
                }
            }


        return root;
    }

}
