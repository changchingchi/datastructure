package algo.TrieTree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TrieTree {

    private TrieNode root;

    public TrieTree() {
        root = new TrieNode(); // 虛節點
    }

    public void insert(String s) {
        TrieNode cur = root;
        HashMap<Character, TrieNode> curChildrenTable = cur.children;
        char[] word = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char curChar = word[i];
            //兩種狀況
            //1. 沒有過：加入之後更新
            if (!curChildrenTable.containsKey(curChar)) {
                TrieNode newNode = new TrieNode(curChar);
                curChildrenTable.put(curChar, newNode);
                cur = newNode;
            } else {
                //2. 已經有了，直接更新
                cur = curChildrenTable.get(curChar);
            }
            curChildrenTable = cur.children;

            //when last one, tag it.
            if (i == s.length() - 1) {
                cur.hasWord = true;
            }

        }
    }

    //check if word s is inside Trietree
    public boolean search(String s) {
        if (searchWordNodePos(s) == null) {
            return false;
        } else if (searchWordNodePos(s).hasWord) {
            return true;
        } else {
            return false;
        }
    }


    public boolean startsWith(String prefix) {
        return searchWordNodePos(prefix) != null;
    }


    public TrieNode searchWordNodePos(String s) {
        HashMap<Character, TrieNode> children = root.children;
        TrieNode cur = null;
        char[] charArray = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            char c = charArray[i];
            if (children.containsKey(c)) {
                cur = children.get(c); //往下走
                children = cur.children; //往下走
            } else {
                return null; // 不存在
            }
        }
        return cur;
    }

}

/// here we only check if we have this word in the tree. what if we want to return a list of typeahead words?


// 1. we can save at each node of what are the children's words, in other words, root parent will have a huge
// list of all children words. 空間換時間 at the time of visiting parent root, we know what are returned.
//e.g. assuming i have a trie tree for amazon, apple, adidas, appreciate
//when i type a, a should already have a list of all possible list which is a->[amazon, apple, adidas]
//if i type in ap --> ap node should have [apple, appreciate] returned.

//we can limit possible words on each node to prevent huge size.

class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<>();
    boolean hasWord; // to check if last node. (complete word)
    //if we wanr to return list of possible children
    Set<String> allword = new HashSet<>();

    char c;

    TrieNode(char c) {
        this.c = c;
    }

    TrieNode() {

    }
}