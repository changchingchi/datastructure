package algo.UnionFind;

import javafx.util.Pair;

import java.util.*;

public class Solutions {

    public int numIslands(char[][] grid) {
        //解法二: union find 2D -> 1D x*m+y = id

        int m = grid[0].length;
        int n = grid.length;

        if (n == 0 || m == 0) return 0;

        //init unionfind
        UnionFind unionFind = new UnionFind(n * m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //add into father[]
                if (grid[i][j] == '1') {
                    unionFind.setNumOfIslands();
                }
            }
        }

        //moving
        int[] direction_x = {0, 1, -1, 0};
        int[] direciton_y = {1, 0, 0, -1};


        //loop thru grid and check surrouding. If matches, connect them.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    for (int x = 0; x < 4; x++) {  //think about x<2 optimization.
                        int cur_x = i + direction_x[x];
                        int cur_y = j + direciton_y[x];

                        if (isBounded(cur_x, cur_y, grid) && grid[cur_x][cur_y] == '1') {
                            //use xm+y = id
                            unionFind.connect(i * m + j, cur_x * m + cur_y);
                        }
                    }
                }
            }
        }


        return unionFind.getNumOfIslands();
    }

    boolean isBounded(int x, int y, char[][] grid) {
        return (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length);
    }

    class UnionFind {
        private int[] father = null;
        private int numOfIslands = 0;

        UnionFind(int x) {
            father = new int[x];
            for (int i = 0; i < x; i++) {
                father[i] = i;
            }
        }

        //find father of a
        int find(int a) {
            if (father[a] == a) {
                return a;
            }

            return father[a] = find(father[a]);
        }

        // connect two islands
        void connect(int a, int b) {
            int father_a = find(a);
            int father_b = find(b);
            if (father_a != father_b) {
                //connect them in case they are not the same.
                father[father_a] = father_b;
                numOfIslands--;
            }

        }

        int getNumOfIslands() {
            return this.numOfIslands;
        }

        void setNumOfIslands() {
            numOfIslands++;
        }
    }

//    Given a n,m which means the row and column of the 2D matrix and an
//  array of pair A( size k). Originally, the 2D matrix is all 0 which means
// there is only sea in the matrix. The list pair has k operator and each operator
// has two integer A[i].x, A[i].y means that you can change the grid matrix[A[i].x][A[i].y]
// from sea to island. Return how many island are there in the matrix after each operator.
//
//    Example
//    Given n = 3, m = 3, array of pair A = [(0,0),(0,1),(2,2),(2,1)].
//
//            return [1,1,2,2].


    public List<Integer> numIslands2(int n, int m , Point[] operators){

        //思路 loop thru operator 每次更新counter
        List<Integer> result = new ArrayList<>();
        if(n == 0 || m == 0 || operators == null) return result;

        int[][] grid = new int[n][m];

        UnionFindII uf2 = new UnionFindII(n*m);

        int[] direction_x = {1,0,-1,0};
        int[] direction_y = {0,1,0,-1};

        for(int i=0;i<operators.length;i++){
            int x = operators[i].x;
            int y = operators[i].y;
            if(grid[x][y]==0){
                grid[x][y]=1;
                uf2.incrementCounter();
                for(int k=0;k<4;k++){
                    int neighbor_x = x + direction_x[k];
                    int neighbor_y = y + direction_y[k];
                    if(isBoundedII(neighbor_x,neighbor_y, n, m) && grid[neighbor_x][neighbor_y]==1){
                        uf2.connect(x*m+y,neighbor_x*m+neighbor_y);
                    }
                }
            }
            //skip if its 1 already.
            result.add(uf2.getCounter());
        }



        return result;
    }

    boolean isBoundedII(int x, int y, int n, int m){
        return (x>=0 && y>=0 && x<n && y<m);
    }

    class UnionFindII{
        int[] father ;
        int count;
        UnionFindII(int x){
            father = new int[x];
            for(int i=0;i<x;i++){
                father[i]=i;
            }
        }

        int find(int x){
            if(father[x] == x){
                return x;
            }
            return father[x] = find(father[x]);
        }

        void connect(int x, int y){
            int father_a = find(x);
            int father_b = find(y);
            if(father_a!=father_b){
                father[father_a] = father_b;
                count--;
            }
        }

        void incrementCounter(){
            count++;
        }

        int getCounter(){
            return count;
        }


    }

}

