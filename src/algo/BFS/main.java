package algo.BFS;

import jiuzhang.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args){
//        String[]{] testset ={{"1","1","1","1","0"},{"1","1","0","1","0"},{"1","1","0","0","0"},{"0","0","0","0","0"}};
//        System.out.println(new Solutions().numIslands(testset));

 int[][] edges = new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}};
 int[][] edgesfalse = new int[][] {{0, 1}, {1, 2}, {2, 3}, {1, 3},{4,4}};
                System.out.println(new Solutions().validTree(5, edgesfalse));

     new Solutions().findCheapestPrice2(3, new int[][]{{0,1,100},{1,2,100},{0,2,500}}, 0, 2,1);
//        new Solutions().ladderLength("hit",
//                "cog",
//                Arrays.asList(new String[]{"hot","dot","dog","lot","log", "cog"}));

        new Solutions().shortestAlternatingPaths(3, new int[][]{{0,1}},new int[][]{{1,2}});
//        new Solutions().canFinish(3, new int[][]{{2,1}, {1,0},{1,2}});
        new Solutions().shortestDistance(new int[][]{{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}});
    }}
