package algo.TrieTree;

import java.util.ArrayList;
import java.util.List;

public class WordSquares {
    class TrieNode{
        TrieNode[] children;
        List<String> startWith;
        TrieNode(){
            children = new TrieNode[26];
            startWith = new ArrayList<>();
        }
    }

    class TrieTree{
        TrieNode root ;
        TrieTree(String[] words){
            root = new TrieNode();
            //insert them into trietree
            for(String word:words){
                insert(word);
            }
        }

        void insert(String word){
            TrieNode current = root;
            for(char c:word.toCharArray()){
                if(current.children[c-'a']==null){
                    current.children[c-'a'] = new TrieNode();
                }
                current = current.children[c-'a'];
                current.startWith.add(word);// add current word into node so that it has the prefix record.
            }
        }

        List<String> findPrefix(String prefix){
            //return the prefix records on node.
            //移動到prefix的最後然後取當前node 的 startwith 所有prefix.
            TrieNode current = root;
            for(char c:prefix.toCharArray()){
                if(current.children[c-'a']==null){
                    return new ArrayList<>(); //若不存在的話回傳空殼
                }
                current = current.children[c-'a'];
            }
            return new ArrayList<>(current.startWith);
        }
    }

    //main
    List<List<String>> wordSquares(String[] words){
        List<List<String>> result = new ArrayList<>();
        if(words==null || words.length == 0){
            return result;
        }

        TrieTree trieTree = new TrieTree(words);
        List<String> list = new ArrayList<>();
        for(String word:words){
            //DFS
         list.add(word);
         helper(trieTree,result,list);
         list.remove(list.size()-1);
        }
        return result;
    }

    private void helper(TrieTree trieTree, List<List<String>> result, List<String> list) {
        if(list.size() == list.get(0).length()){ // 當發現當前list 已經跟 list.get(0)一樣長度時 表示square了 所以退出
            result.add(new ArrayList<>(list));
            return;
        }
        int index = list.size();
        StringBuilder prefix = new StringBuilder();
        for(String s:list){
            prefix.append(s.charAt(index)); //造成row kth / colume kth
            //b a l l
            //a _ _ _
        }
        List<String> startWith = trieTree.findPrefix(prefix.toString()); // 找出所有以a為prefix的
        for(String word:startWith){
            //再進行一次局部DFS
            list.add(word);
            helper(trieTree, result, list);
            list.remove(list.size()-1);
        }

    }
}
