package algo.TrieTree;

import algo.twoPointer.Solutions;

import java.util.Arrays;
import java.util.UUID;

public class main {
    public static void main(String[] args) {
//        AddandSearch addandSearch = new AddandSearch();
//        addandSearch.addWord("bad");
//        addandSearch.addWord("dad");
//        addandSearch.addWord("mad");
//        System.out.println(addandSearch.search("pad"));
//        System.out.println(addandSearch.search("bad"));
//        System.out.println(addandSearch.search("b.d"));
//        System.out.println(addandSearch.search("b.."));

//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//        WordSearch wordSearch = new WordSearch();
//        System.out.println(wordSearch.exist(board, "SEEE"));
//        int[] dist = new int[100];
//        for (int i = 0; i < 10000; i++) {
//            String uuid = UUID.randomUUID().toString();
//            dist[Math.abs(("chi" + uuid).hashCode()) % 100]++;
//        }
//        System.out.println(Arrays.toString(dist));

        String[] test = {"abat","baba","atan","atal"};
        System.out.println(Arrays.toString(new WordSquares().wordSquares(test).toArray()));
    }
}
