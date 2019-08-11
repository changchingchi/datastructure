package algo.TrieTree;
//
//Design a data structure that supports the following two operations:
//
//        void addWord(word)
//        bool search(word)
//        search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
//
//        For example:
//
//        addWord("bad")
//        addWord("dad")
//        addWord("mad")
//        search("pad") -> false
//        search("bad") -> true
//        search(".ad") -> true
//        search("b..") -> true


//表準trietree
public class AddandSearch {

    TrieNode root;

    AddandSearch() {
        root = new TrieNode();
    }

    void addWord(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (current.children[c - 'a'] == null) {
                //not exist so add it.
                current.children[c - 'a'] = new TrieNode(c);
            }
            current = current.children[c - 'a'];

        }
        current.hasWord = true;
    }

    public boolean search(String word) {
        return find(word, 0, root);
    }

    //need to add searching criteria here.
    //思路 因為要支援 "."收尋，相當於求所有組合 ==> DFS : 遞歸三要素
    boolean find(String word, int index, TrieNode current) {

        //遞歸的出口
        if (index == word.length()) { // 為什麼不是 length -1? 因為虛root
            return current.hasWord; //已經到終點 判斷是否hasword;
        }

        char c = word.charAt(index);
        if (c == '.') {
            for (int i = 0; i < 26; i++) {
                if (current.children[i] != null) { // 查看當前node的所有小孩 DFS
                    if (find(word, index + 1, current.children[i])) {
                        return true;
                    }
                }
            }
            return false;
        } else if (current.children[c - 'a'] != null) {
            //regular alphabetic case.
            return find(word, index + 1, current.children[c - 'a']);
        }
        return false;

    }
//
//    boolean find2(String word, int start, TrieNode root){
//        if(start==word.length()) return root.hasWord;
//
//        char c = word.charAt(start);
//        if(c=='.'){
//            // '.' mean all possible, so we will need to check all char
//            for(int i=0;i<26; i++){
//                if(root.children[i]!=null){
//                    if(find2(word, start+1, root.children[i])) return true;
//
//                }
//            }
//        }else{
//            if(root.children[c-'a']!=null){
//                //continue to next child.
//                return find2(word,start+1, root.children[c-'a']);
//            }
//        }
//
//        return false;
//    }

    class TrieNode {
        TrieNode[] children;
        boolean hasWord;
        char c;

        TrieNode(char c) {
            children = new TrieNode[26];
            hasWord = false;
            this.c = c;
        }

        TrieNode() {
            children = new TrieNode[26];
            hasWord = false;
        }
    }
}


