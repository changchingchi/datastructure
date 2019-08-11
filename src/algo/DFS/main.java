package algo.DFS;

import algo.TreeTraverse.TreeNode;

import java.util.*;

public class main {
    public static void main(String[] args){
        int[] testset = {1,1,2};
//        System.out.println(new Solutions().subsets(testset));
//        String s = "aaab";
//        System.out.println(new Solutions().partition(s));

//        TreeNode tree = new TreeNode(1);
//        tree.right = new TreeNode(2);
//        tree.right.left = new TreeNode(2);
//
//        System.out.println(new Solutions().combinationSum(new int[]{2,3,6,7},7));
//        System.out.println(new Solutions().combinationSum2(new int[]{1,1,2,2,3,3},6));
//        System.out.println(new Solutions().findMode(tree));
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("pen");
        list.add("applepen");
        list.add("pine");
        list.add("pineapple");
        list.add("cats");
        list.add("dog");
        list.add("sand");
        list.add("and");
        list.add("cat");

        System.out.println(new Solutions().wordBreak("catsanddog", list));
//
//        HashMap<String, Integer> map = new HashMap<>();
////        map.put("abc",1);
////        map.put("ab",1);
////        map.put("cd",1);
//
//        map.put("a",2);
//        map.put("b",2);
//        System.out.println(new Solutions().wordBreakFB("abcd", map));

//        System.out.println(new Solutions().permute3(new int[]{1,2,3}));
//        System.out.println(new Solutions().diffWaysToCompute("2*3-4*5"));
//        Graph g = new Graph(4);
//        g.addEdge(0,1);
//        g.addEdge(0,2);
//        g.addEdge(0,3);
//        g.addEdge(2,0);
//        g.addEdge(2,1);
//        g.addEdge(1,3);
//System.out.println(new Solutions().reverseString("word"));


           /* Constructed binary tree is
            10
          /   \
        8      2
              /  \
              3   5
        */
/*
        TreeNode tree = new TreeNode(10);
        tree.left = new TreeNode(8);
        TreeNode two = new TreeNode(2);
        tree.right = two;
        tree.right.left = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        tree.right.right = five;

//        new Solutions().pathSumII(tree,15 );
//
//
//
//        new Solutions().canPartition(new int[]{1, 5, 11, 5});
//new Solutions().getFactors(8);
//        new Solutions().subsets(new int[]{1,2,3});
//        new Solutions().solveNQueens(4);

        List<String> set = new ArrayList<>();
        set.add("hot");
        set.add("dot");
        set.add("dog");
        set.add("lot");
        set.add("log");
        set.add("cog");
        new Solutions().findLadders("hit", "cog", set);
*/


        new Solutions().construct(new int[][]{  {1,1,1,1,0,0,0,0},
                                                {1,1,1,1,0,0,0,0},
                                                {1,1,1,1,1,1,1,1},
                                                {1,1,1,1,1,1,1,1},
                                                {1,1,1,1,0,0,0,0},
                                                {1,1,1,1,0,0,0,0},
                                                {1,1,1,1,0,0,0,0},
                                                {1,1,1,1,0,0,0,0}
                                                } );

        new Solutions().combinationSum4(new int[]{1,2,3}, 4 );
//        {0,1,0},
//        {0,1,0},
//        {1,1,0}}
        System.out.println(new Solutions().findMaze(new int[][]{{0,1,0},{0,1,0},{1,1,0}}, 0,0, 2,2));

    }

}
