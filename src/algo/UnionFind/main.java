package algo.UnionFind;

import com.sun.tools.javac.util.Assert;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class main {
    public static void main(String[] args){

//        ConnectingGraph connectingGraph = new ConnectingGraph(5);
//        System.out.println(connectingGraph.query(1,2 ));
//        connectingGraph.connect(1, 2);
//        System.out.println(connectingGraph.query(1,3 ));

//        ConnectingGraphII connectingGraphii = new ConnectingGraphII(5);
//        System.out.println(connectingGraphii.query(1 ));
//        connectingGraphii.connect(1, 2);
//        System.out.println(connectingGraphii.query(1));
//        connectingGraphii.connect(2, 4);
//        System.out.println(connectingGraphii.query(1));
//        connectingGraphii.connect(1, 4);
//        System.out.println(connectingGraphii.query(1));
//
//        ConnectingGraphIII connectingGraphiii = new ConnectingGraphIII(5);
//        System.out.println(connectingGraphiii.query()); // return 5
//        connectingGraphiii.connect(1, 2);
//        System.out.println(connectingGraphiii.query()); // return 4
//        connectingGraphiii.connect(2, 4);
//        System.out.println(connectingGraphiii.query()); // return 4
//        connectingGraphiii.connect(1, 4);
//        System.out.println(connectingGraphiii.query());

//        char[][] test = new char[][]{   {'1','1','1','1','0'},
//                                        {'1','1','0','1','0'},
//                                        {'1','1','0','0','0'},
//                                        {'0','0','0','1','0'}};
//        System.out.println(new Solutions().numIslands(test));

        Point[] points = new Point[4];
        points[0] = new Point(0,0);
        points[1] = new Point(0,1);
        points[2] = new Point(2,2);
        points[3] = new Point(2,1);
        Stack<Integer> s = new Stack<>();
        Queue<Integer> q = new LinkedList<>();
        System.out.println(new Solutions().numIslands2(3, 3, points));


    }
}
