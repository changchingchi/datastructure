package algo.BFS;

import java.util.*;

public class Solutions {

    /**
     * Question
     * <p>
     * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
     * <p>
     * Notice
     * <p>
     * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
     * <p>
     * Example
     * <p>
     * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
     * <p>
     * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
     */
    boolean validTree(int n, int[][] edges) {
        //樹的條件為 n 個node 有n-1個邊
        if (n == 0) return false;
//        if (n-1 != edges.length) return false;
        //BFS 需要有連接表 queue hash 三要素

        // 1. create adjacent list
        Map<Integer, Set<Integer>> al = initializeGraph(n, edges);

        // 開始BFS
        Queue<Integer> queue = new LinkedList<>();
        // 需要一個set儲存當前node以便最後可以知道聯通性
        Set<Integer> set = new HashSet<>();
        queue.offer(0);
        set.add(0);
        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // 取得所有鄰接點

            for (int i : al.get(curr)) {
                if (set.contains(i)) {
                    continue; //set 裡已經有了則跳過
                }
                queue.offer(i);
                set.add(i);
            }
        }
        //set 裡包含得點 必須相等 因為BFS驗證其連通性
        return set.size() == n;
    }

    private Map<Integer, Set<Integer>> initializeGraph(int n, int[][] edges) {
        //需要找出每個node對應到的邊
        Map<Integer, Set<Integer>> result = new HashMap<>();

        for (int i = 0; i < n; i++) {
            result.put(i, new HashSet<>());
        }

        for (int i = 0; i < n; i++) {
            int a = edges[i][0];
            int b = edges[i][1];

            result.get(a).add(b); // put b as a's neighbor
            result.get(b).add(a);//put a as b's neighbor
        }
        return result;
    }


    //Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
    //Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

    //判斷有沒有環形 即可知道是不是valid tree. 連接所有edge 並求最後聯通塊是否為1
    boolean validTreeUnionFind(int n, int[][] edges) {

        if (n - 1 != edges.length) {
            return false;
        }

        unionFind u = new unionFind(n);

        for (int i = 0; i < edges.length; i++) {
            u.connect(edges[i][0], edges[i][1]);
        }
        return u.count == 1;
    }

    class unionFind {

        int[] father;
        int count;

        unionFind(int x) {
            father = new int[x];
            for (int i = 0; i < x; i++) {
                father[i] = i;
            }
            count = x;
        }

        int find(int a) {
            if (father[a] == a) {
                return a;
            }

            return father[a] = find(father[a]); //compression to make find O(1) operation.
        }

        void connect(int a, int b) {
            int father_a = find(a);
            int father_b = find(b);

            if (father_a != father_b) {
                father[father_a] = father_b;
                count--;
            }

        }
    }


    public int numIslands(String[][] grid) {
        //灌水法 BFS
        //1. 對於每個為1的點 遍歷上下左右 完成計算後標記為0
        //2. BFS

        if (grid.length < 1 || grid[0].length < 1 || grid == null) return 0;

        int islands = 0;
        for (int i = 0; i < grid.length; i++) //traverse rows
            for (int j = 0; j < grid[0].length; j++) { //column.
                if (grid[i][j].equals("1")) {
                    BFS(grid, i, j); // pass in coordinate
                    islands++;
                }
            }
        return islands;
    }

    private void BFS(String[][] grid, int r, int c) {
        //BFS : queue
        Queue<coordinate> q = new LinkedList<>();
        q.offer(new coordinate(r, c));
        grid[r][c] = "0";
        while (!q.isEmpty()) {
            //拿出當前位置
            coordinate current = q.poll();
            //查找上下左右是否有相鄰的1 , 確保每次都在圖的界內
            int[] x = {1, 0, -1, 0}; //上下左右的模板，以便我們放入for loop
            int[] y = {0, 1, 0, -1};
            for (int k = 0; k < 4; k++) {
                coordinate neighbor = new coordinate(current.x + x[k], current.y + y[k]);
                //確認是否界內
                if (!isInbound(grid, neighbor)) {
                    continue; //跳過越界的
                }
                //如果鄰居也是1則加入q 且設為0
                if (grid[neighbor.x][neighbor.y].equals("1")) {
                    grid[neighbor.x][neighbor.y] = "0";
                    q.offer(neighbor);
                }
            }
        }
    }

    private void BFS2(String[][] grid, int r, int c) {

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        grid[r][c] = "0";
        int[] dir_x = {1, 0, -1, 0};
        int[] dir_y = {0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newX = cur[0] + dir_x[i];
                int newY = cur[1] + dir_y[i];

                if (isBounded(grid, newX, newY)) {
                    if (grid[newX][newY].equals("1")) {
                        queue.add(new int[]{newX, newY});
                        grid[newX][newY] = "0";
                    }
                }
            }
        }

    }

    private boolean isBounded(String[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }

    private boolean isInbound(String[][] grid, coordinate coor) {
        return coor.x >= 0 && coor.y >= 0 && coor.y <= grid[0].length - 1 && coor.x <= grid.length - 1;
    }

    private class coordinate {
        int x;
        int y;

        coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public Node cloneGraph(Node node) {
        if (node == null) return null;

        //step 1
        List<Node> oldNodes = getOldNodes(node);

        //step 2 oldNodes:newNodes mapping information
        Map<Node, Node> map = new HashMap<>();
        for (Node n : oldNodes) {
            map.put(n, new Node(n.val, null));
        }

        //step 3 clone edges from old to new

        for (Node n : oldNodes) {
            Node newNode = map.get(n);
            for (Node neighbor : n.neighbors) {
                //deep copy neighbor as well
                Node newNeighbor = map.get(neighbor);
                newNode.neighbors.add(newNeighbor);
            }
        }

        return map.get(node); //return map old->new
    }

    private List<Node> getOldNodes(Node node) {
        List<Node> result = new ArrayList<>();

        if (node == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        HashSet<Node> set = new HashSet<>();

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            set.add(cur);
            result.add(cur);

            for (Node neighbor : cur.neighbors) {
                if (!set.contains(neighbor)) {
                    //visit only once.
                    queue.add(neighbor);
                }
            }
        }
        return result;

    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
        }

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }






    /*Given a undirected graph, a node and a target, return the nearest node to given node which value of it is target, return NULL if you can't find.

    There is a mapping store the nodes' values in the given parameters.

    Notice
    It's guaranteed there is only one available solution

    Have you met this question in a real interview?
    Yes
            Example
       2------3  5
        \     |  |
        \    |  |
        \   |  |
        \  |  |
          1 --4
    Give a node 1, target is 50

    there a hash named values which is [3,4,10,50,50], represent:
    Value of node 1 is 3
    Value of node 2 is 4
    Value of node 3 is 10
    Value of node 4 is 50
    Value of node 5 is 50

    Return node 4
    */

    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }

    public UndirectedGraphNode searchNode(ArrayList<UndirectedGraphNode> graph,
                                          Map<UndirectedGraphNode, Integer> values,
                                          UndirectedGraphNode node,
                                          int target) {


        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        int[] visited = new int[graph.size() + 1]; // so that I can use same index.
        queue.add(node);
        visited[node.label] = 1;
        while (!queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();


            if (values.get(cur) == target) {
                return cur;
            }

            for (UndirectedGraphNode neighbor : cur.neighbors) {
                if (visited[node.label] != 1) {
                    queue.add(neighbor);
                    visited[node.label] = 1;
                }
            }
        }
        return null;

    }
//    Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
//
//            Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
//
//    After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.


    //    Input:
//    accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
//    Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
    public List<List<String>> accountsMerge2(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        // step 1: build graph that connects all emails have relationships
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                graph.putIfAbsent(account.get(i), new HashSet<>());
                emailToName.put(account.get(i), name);
                if (i != 1) {
                    graph.get(account.get(i)).add(account.get(i - 1));
                    graph.get(account.get(i - 1)).add(account.get(i));
                }
            }
        }

        // step 2: BFS traversal to traverse all nodes in every single component and generate each result list individually
        List<List<String>> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String email : graph.keySet()) {
            if (!visited.contains(email)) {
                visited.add(email);
                List<String> newList = bfs(graph, visited, email);
                Collections.sort(newList);
                newList.add(0, emailToName.get(newList.get(0)));
                result.add(newList);
            }
        }
        return result;
    }

    public List<String> bfs(Map<String, Set<String>> graph, Set<String> visited, String startPoint) {
        List<String> newList = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(startPoint);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curEmail = queue.poll();
                newList.add(curEmail);
                Set<String> neighbors = graph.get(curEmail);
                for (String neighbor : neighbors) {
                    // WARING: DO NOT FORGET to check whether current email has been visited before
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return newList;
    }
//    给一个二维网格，每一个格子都有一个值，2 代表墙，1 代表僵尸，0 代表人类(数字 0, 1, 2)。
//    僵尸每天可以将上下左右的人类感染成僵尸，但不能穿墙。将所有人类感染为僵尸需要多久，如果不能感染所有人则返回 -1。

    public int zombie(int[][] grid) {
        //把所有的殭屍都放到queue然後當前殭屍走一遍 day++

        int people = 0;
        Queue<points> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    people++;
                } else if (grid[i][j] == 0) {
                    queue.add(new points(i, j));
                }
            }
        }

        int[][] dir = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int days = 1;
        while (!queue.isEmpty()) {
            int zombies = queue.size();
            points cur = queue.poll();
            days--;
            for (int i = 0; i < zombies; i++) {
                for (int j = 0; j < 4; j++) {
                    int newX = cur.x + dir[j][0];
                    int newY = cur.y + dir[j][1];
                    if (isPeople(newX, newY, grid)) {
                        people--;
                        if (people == 0) {
                            return days;
                        }
                        grid[newX][newY] = 1;
                        queue.add(new points(newX, newY));
                    }
                }
            }
        }
        return -1;
    }

    private boolean isPeople(int newX, int newY, int[][] grid) {
        return newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] == 0;
    }

    class points {
        int x;
        int y;

        points(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<Pair>> map = new HashMap<>();
        for (int[] i : flights) {

            List<Pair> t = map.getOrDefault(i[0], new ArrayList<>());
            t.add(new Pair(i[1], i[2]));

            map.put(i[0], t);
        } //記錄對於每個起點 到哪個點以及其cost

        //0, (1,100) , (2,500)
        //1, (2,100)
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(src, 0)); // src->src cost = 0 走到src需要多少錢

        int steps = 0;
        int ans = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int size = queue.size(); //scan all neighbors
            for (int i = 0; i < size; i++) {
                Pair p = queue.poll();
                int cur = p.getKey();
                int cost = p.getValue();
                if (cur == dst) {
                    //
                    ans = Math.min(ans, cost);
                }

                if (map.containsKey(cur)) {
                    for (Pair k : map.get(cur)) {
                        if (cost + k.getValue() > ans) continue; //加上新的超過了本來的了

                        queue.add(new Pair(k.getKey(), cost + k.getValue()));
                    }
                }

            }

            if (steps > K) break;
            steps++;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    class Pair {
        int key;
        int value;

        Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        int getKey() {
            return this.key;
        }

        int getValue() {
            return this.value;
        }
    }


    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        //create graph graph[from][to] = cost
        int[][] graph = new int[flights.length][flights[0].length];
        for (int[] i : graph) {
            Arrays.fill(i, -1); // mark -1 if not a vaild route.
        }

        //from 0 to i[1] and its cost.
        for (int[] i : flights) {
            int from = i[0];
            int to = i[1];
            int cost = i[2];
            graph[from][to] = cost;
        }
        //from to cost
        // 0  100 500
        // -1  0  100
        // -1  -1  0

        //visit
        Set<Integer> visited = new HashSet<>();  //--> we dont need this cause we need to repetively update cost from different node
        //bfs
        Queue<int[]> queue = new LinkedList<>();
        visited.add(src);
        queue.add(new int[]{src, src, 0}); //from 0 to next dst total cost
        int min = Integer.MAX_VALUE;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int from = cur[0];
                int to = cur[1];
                int cost = cur[2];

                if (to == dst) {
                    min = Math.min(cost, min);
                }


                //這個算法好像不是很好 因為還是遍歷from->n 但並不是所有的路徑都存在 hashmap可能更好

                for (int j = to; j < n; j++) { //starting from "from". "from" 之前的已經找過了 e.g 從0開始之後到了1 我們就找1->2 而已
                    //from this point (to) to and loop to check for next available route.
                    if (graph[to][j] != -1) {
                        int c = graph[to][j];
                        if (cost + c > min) continue;

                        queue.add(new int[]{to, j, cost + c});
//                        visited.add(j);
                    }
                }
            }

            if (step > K) break;
            step++;

        }

        return min == Integer.MAX_VALUE ? -1 : min;

    }


//    Given 2 strings s and t, determine if you can convert s into t. The rules are:
//
//    You can change 1 letter at a time.
//    Once you changed a letter you have to change all occurrences of that letter.
//
//    Input: s = "abca", t = "dced"
//    Output: true
//    Explanation: abca ('a' to 'd') -> dbcd ('c' to 'e') -> dbed ('b' to 'c') -> dced


    //wordLadder
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> dict = new HashSet<>();
        for (String word : wordList) {    //将wordList中的单词加入dict
            dict.add(word);
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }

        if (!wordList.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        queue.add(beginWord);

        int count = 1;

        while (!queue.isEmpty()) {
            //each level is depending on how many neighbors.
            count++;
            for (int j = 0; j < queue.size(); j++) {
                String cur = queue.poll();

                for (String next : findNeighbor(cur, dict)) {
                    if (visited.contains(next)) continue;

                    if (next.equals(cur))
                        return count;

                    visited.add(next);
                    queue.add(next);
                }
            }
        }
        return 0;
    }


    private ArrayList<String> findNeighbor(String word, Set<String> dict) {
        ArrayList<String> nextWords = new ArrayList<String>();
        for (char c = 'a'; c <= 'z'; c++) {                    //枚举替换字母
            for (int i = 0; i < word.length(); i++) {        //枚举替换位置
                if (c == word.charAt(i)) {
                    continue;
                }
                String nextWord = replace(word, i, c);
                if (dict.contains(nextWord)) {                //如果dict中包含新单词，存入nextWords
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;                                    //构造当前单词的全部下一步方案
    }

    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }


//    1129. Shortest Path with Alternating Colors

//    Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
//    Output: [0,1,-1]

    //    Input: n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
//    Output: [0,1,2]
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {

        int[] res = new int[n];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[0] = 0;

        //build neighbor table
        int[][] table = buildGraph(n, red_edges, blue_edges);
        //visit array
        Set<String> visited = new HashSet<>(); //why String? we wanto also record color = value+color

        int count = 0;
        //bfs
        Queue<int[]> queue = new LinkedList<>(); //use int[] to mark curren node, and its color.
        queue.add(new int[]{0, 1});
        queue.add(new int[]{0,-1}); // it can be red or blue.

        while(!queue.isEmpty()){
            int size = queue.size();
            count++;
            for(int i=0;i<size; i++){
                int[] cur = queue.poll(); //take the current one.
                int node = cur[0];
                int color = cur[1];
                int nextColor = -color;

                //we scan thru all the neighbors for given node. (just loop thru ~0->n)
                for(int j=0;j<n;j++){
                    if(visited.contains(j+""+nextColor)) continue;

                    if(table[node][j] == nextColor || table[node][j] == 0){  //its oppositie color or it can be both. (both is fine cause we can also filter at next level)
                        visited.add(j+""+nextColor);
                        queue.add(new int[]{j, nextColor});
                        res[j] = Math.min(res[j], count);  //find min among neighbors.

                    }
                }

            }
        }

        for(int i=0;i<res.length;i++){
            if(res[i] == Integer.MAX_VALUE) res[i] = -1;
        }

        return res;
    }


    //create a from-to graph : red = 1 blue= -1 , both = 0;
    int[][] buildGraph(int n, int[][] red_edge, int[][] blue_edge){
        int[][] res = new int[n][n];
        for(int[] i: res){
            Arrays.fill(i,-n);
        }
        for(int[] i: red_edge){
            int from = i[0];
            int to = i[1];
            res[from][to] = 1; //make red edge as 1
        }

        for(int[] i: blue_edge){
            int from = i[0];
            int to = i[1];

            if(res[from][to] ==1){
                res[from][to] = 0; //both edge are available
            }else{
                res[from][to] = -1; //why -1? so that we can flip directly during bfs
            }
        }
        return res;
    }
//BFS
// 因為要交替走 假設我們現在是紅色出發 (我們怎麼知道是紅色？) --> 要標記一下
// 我們要找到紅色的點的藍色鄰居

//BFS 一般過程
// neighbor table
// visited
//queue


    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        HashMap<DirectedGraphNode, Integer> map = new HashMap();
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (map.containsKey(neighbor)) {
                    map.put(neighbor, map.get(neighbor) + 1);  //計算入度 有多少點指向他
                } else {
                    map.put(neighbor, 1);
                }
            }
        }

        Queue<DirectedGraphNode> q = new LinkedList<DirectedGraphNode>();
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) { // 找到起點
                q.offer(node);
                result.add(node);
            }
        }
        while (!q.isEmpty()) {
            DirectedGraphNode node = q.poll();
            for (DirectedGraphNode n : node.neighbors) {
                map.put(n, map.get(n) - 1); //對於當前的鄰居 減少一個入肚 因為已經有當前節點指向他
                if (map.get(n) == 0) {
                    result.add(n);
                    q.offer(n);
                }
            }
        }
        return result;
    }

    class DirectedGraphNode {
        int label;
        ArrayList<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

//    现在你总共有 n 门课需要选，记为 0 到 n - 1.
//    一些课程在修之前需要先修另外的一些课程，比如要学习课程 0 你需要先学习课程 1 ，表示为[0,1]
//    给定n门课以及他们的先决条件，判断是否可能完成所有课程？

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        //( 課程, indegree)
        HashMap<Integer, Integer> inDegree = new HashMap<>();

        //見鄰居表
        HashMap<Integer, List<Integer>> neighbor = new HashMap<>();

        for (int[] pre : prerequisites) {

            if (!neighbor.containsKey(pre[1])) {
                neighbor.put(pre[1], new ArrayList<>());
            }

            List<Integer> n = neighbor.get(pre[1]);
            n.add(pre[0]);
            neighbor.put(pre[1], n);

            //update indegree table.
            if (inDegree.containsKey(pre[0])) {
                inDegree.put(pre[0], inDegree.get(pre[0]) + 1);
            } else {
                inDegree.put(pre[0], 1);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> ordering = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (!inDegree.containsKey(i)) {
                //find indegree == 0, entry point. 起點
                System.out.println(i);
                queue.add(i);
                ordering.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();
            System.out.println(course);
            List<Integer> nbr = neighbor.get(course);
            if(nbr!=null){
                for (int n : nbr) {
                    inDegree.put(n, inDegree.get(n) - 1); //入度-1
                    if (inDegree.get(n) == 0) {
                        //入度 = 0 已解鎖 沒有其他先修課程
                        ordering.add(n);
                        queue.add(n);
                    }
                }
            }

        }
        // System.out.println((ordering));
        return numCourses <= ordering.size();
    }


    //You want to build a house on an empty land which reaches all buildings in the shortest amount of distance.
    // You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
    //
    //Each 0 marks an empty land which you can pass by freely.
    //Each 1 marks a building which you cannot pass through.
    //Each 2 marks an obstacle which you cannot pass through.


    int min = Integer.MAX_VALUE;
    public int shortestDistance(int[][] grid) {
        //find all 1's first
        int buildings = 0;
        for(int[] i: grid){
            for(int j:i){
                if(j==1) buildings++;
            }
        }

        for(int i =0 ;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j] == 0){
                    //start bfs only from 0
                    bfs(grid, i, j, buildings);
                }
            }
        }

        return min==Integer.MAX_VALUE ? -1 : min;
    }

    void bfs(int[][] grid, int x, int y, int buildings){
        int[][] dir = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int steps=0;
        int total = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            // System.out.println("")

            for(int i=0;i<size;i++){
                int[] cur = queue.poll();
                System.out.println("from: "+cur[0]+":"+cur[1]);
                visited[cur[0]][cur[1]] = true;
                for(int j=0;j<4;j++){
                    int newX = cur[0]+dir[j][0];
                    int newY = cur[1]+dir[j][1];
                    if(isValid(grid, newX, newY) && !visited[newX][newY]){
                        System.out.println(newX+":"+newY);

                        if(grid[newX][newY] == 1){
                            buildings--;
                            System.out.println("building left: "+buildings);
                            total+=steps;
                            if(buildings == 0){
                                min = Math.min(min, total);
                            }
                        }else{
                            queue.add(new int[]{newX, newY});
                        }
                        visited[newX][newY] = true;


                    }
                }

            }
            steps++;
        }
    }

    boolean isValid(int[][] grid, int x, int y){
        return x>=0 && x<grid.length && y>=0 && y< grid[0].length && grid[x][y]!=2;
    }
}
