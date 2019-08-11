package algo.DFS;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    int node;
    Map<Integer, List<Integer>> neighbor = new HashMap<>();

    public Graph(int node) {
        this.node = node;
        init();
    }

    private void init() {

    }

    //connect two nodes
    public void addEdge(int node1, int node2) {
        if (neighbor.containsKey(node1)) {
            neighbor.get(node1).add(node2);
        } else {
            ArrayList<Integer> n = new ArrayList<>();
            n.add(node2);
            neighbor.put(node1,n);
        }
    }

    void printAllPath(int start, int des) {
        boolean[] visited = new boolean[node];
        List<Integer> path = new ArrayList<>();
        path.add(start);
        helper(start, des, visited, path, new StringBuilder());
    }

    private void helper(int start, int des, boolean[] visited, List<Integer> path, StringBuilder sb) {
        visited[start] = true;

        if (start == des) {
            System.out.println(path);
            visited[start] = false;
            return;
        }
//        System.out.println("asa");

        //對於當前的node的每一個neightbor我們都一直往下查詢
        for (int i : neighbor.get(start)) {
            if (!visited[i]) {
                path.add(i);
                helper(i, des, visited, path, sb);
                path.remove(path.size() - 1);

//                System.out.println("as");
            }
        }

        visited[start] = false;
    }


}
