package algo.TrieTree;

class WordSearch {

    //思路：TrieTree延伸題
    //將word 建成 TrieTree  然後遍歷整個board + DFS上下左右

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //DFS each point;
                if (find(board, i, j, word, 0)) {
                    return true;
                }

            }
        }

        return false;
    }

    boolean find(char[][] board, int x, int y, String word, int index) {

        if (index == word.length()) {
            return true;
        }

        if (!isValid(board, x, y)) {
            return false;
        }

        if (board[x][y] != word.charAt(index)) {
            return false;
        }


        int[] direction_x = {1, 0, -1, 0};
        int[] direction_y = {0, 1, 0, -1};
        char temp = board[x][y];
        board[x][y] = 0;
        for (int k = 0; k < 4; k++) {
            int newX = x + direction_x[k];
            int newY = y + direction_y[k];
            if (find(board, newX, newY, word, index + 1)) {
                return true;
            }
        }
        board[x][y] = temp;
        return false;
    }


    boolean isValid(char[][] board, int x, int y) {
        return x < board.length && x >= 0 && y < board[0].length && y >= 0 && board[x][y] != 0;
    }
}


