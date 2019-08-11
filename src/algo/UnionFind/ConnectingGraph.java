package algo.UnionFind;


//union find : 利用一個數組來實現的數據結構


//lintcode 589
// given n nodes in graph labeled from 1 to n. There is no edges in the graph at the beginning. You need to support
// 1. connect(a,b), add edge to connect node a and node b.
// 2. query (a,b), check if two nodes are connected.
public class ConnectingGraph {
    int[] father = null;


    // given x nodes in graph
    ConnectingGraph(int x){
        father = new int[x+1];
        //init
        for(int i = 1; i<x+1 ;i++){
            father[i] = i;
        }
    }

    //given a and return its father node
    public int find(int a){
        if(father[a] == a){
            return a;
        }
        return father[a] = find(father[a]); // path compression for O(1)
    }


    //connect a and b
    public void connect(int a, int b){
        int father_a = find(a);
        int father_b = find(b);
        if(father_a != father_b){
            father[father_a] = father_b;
        }
    }

    // check if a and b share same father.
    public boolean query ( int a, int b){
        int father_a = find(a);
        int father_b = find(b);
        return father_a == father_b;
    }
}
