package algo.recursionanddp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chchi on 8/30/17.
 */

//if we can walk diagonally, then we add matrix[i-1][j-1]
//if now there is a certain obstacles on the matrix, then we set the particular point to 0 in base cases;

public class solution {
    //from(0,0) to des(X,Y) in N x M matrix, numbers of possible paths.
    public static int twoDStairCasewalk_DP(int n, int m, int X, int Y) {

        if (X > m - 1 || Y > n - 1 || X < 0 || Y < 0) return 0;
        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++)
            matrix[i][0] = 1;
        for (int j = 0; j < m; j++)
            matrix[0][j] = 1;
        for (int i = 1; i <= X; i++) {
            for (int j = 1; j <= Y; j++) {
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
            }
        }
        return matrix[X][Y];
    }

    public static int twoDStairCasewalk_recursion(int n, int m, int X, int Y) {

        if (X > m - 1 || Y > n - 1 || X < 0 || Y < 0) return 0;
        if (X == 0 || Y == 0) return 1;

        return twoDStairCasewalk_recursion(n, m, X - 1, Y) + twoDStairCasewalk_recursion(n, m, X, Y - 1);
    }

    //p344.
    // Robot in a grid
    public static ArrayList<Point> getPath(boolean[][] maze) {
        if (maze == null || maze.length == 0) return null;
        ArrayList<Point> path = new ArrayList<>();
        if (getPath(maze, maze.length - 1, maze[0].length - 1, path)) {
            return path;
        }
        return null;
    }

    private static boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path) {
        if (col < 0 || row < 0 || !maze[row][col]) {
            return false;
        }

        if (row == 0 && col == 0) {
            path.add(new Point(row, col));
            return true;
        }
        if (getPath(maze, row, col - 1, path) || getPath(maze, row - 1, col, path)) {
            path.add(new Point(row, col));
            return true;
        }
        return false;
    }

    static class Point {
        int x;
        int y;

        Point(int r, int c) {
            x = r;
            y = c;
        }
    }


    public static void DFS(String digits, List<String> result, StringBuffer s, int start,
                           HashMap<Integer, String> map) {
        if (start == digits.length()) {
            result.add(s.toString());
            return;
        }

        String tmp = map.get(digits.charAt(start) - '0');
        for (int i = 0; i < tmp.length(); i++) {
            s.append(tmp.charAt(i));
            DFS(digits, result, s, start + 1, map);
            s.deleteCharAt(s.length() - 1);
        }

    }

    public static List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0)
            return new ArrayList<String>();
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(0, "");
        map.put(1, "");
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        List<String> result = new ArrayList<String>();
        StringBuffer s = new StringBuffer();
        DFS(digits, result, s, 0, map);
        return result;
    }



    public static List<Integer> lexicalOrder(int n) {
        int cur = 1;
        List<Integer> result = new ArrayList<>();
        for(int i=1;i<=9;i++){
            helper(i, result, n);
        }

        return result;
    }
    //查看以cur開頭的有沒有超過n
    static void helper(int cur, List<Integer> result, int n){
        if(cur>n) return;
        result.add(cur);
        for(int i=0;i<=9;i++){
            if(cur*10+i>n) return; //prunning
            helper(cur*10+i, result,n);
//            Arrays.binarySearch({1,2,3},2);
        }
    }



}
