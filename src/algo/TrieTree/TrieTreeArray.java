package algo.TrieTree;

public class TrieTreeArray {

    private TrieNodeArray root;

    TrieTreeArray() {
        root = new TrieNodeArray();
    }

    void insert(String s) {
        TrieNodeArray current = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (current.children[c - 'a'] == null) {
                //不存在 所以加入
                current.children[c - 'a'] = new TrieNodeArray();
            }
            current = current.children[c - 'a']; //往下移動
        }
        current.hasword = true;
    }


    boolean search(String s) {
        if (searchNodePos(s) == null) {
            return false;
        } else {
            return searchNodePos(s).hasword;
        }
    }

    boolean startWith(String s) {
        return searchNodePos(s) != null;
    }

    TrieNodeArray searchNodePos(String s) {
        //從頂開始
        TrieNodeArray current = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (current.children[c - 'a'] != null) {
                current = current.children[c - 'a'];
            } else {
                return null;
            }

        }
        return current;
    }
}

class TrieNodeArray {
    TrieNodeArray[] children;
    boolean hasword;

    TrieNodeArray() {
        children = new TrieNodeArray[26];
        for (int i = 0; i < 26; i++) {
            children[i] = null;
            hasword = false;
        }
    }
}