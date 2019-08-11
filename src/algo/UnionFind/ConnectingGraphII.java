package algo.UnionFind;


//lintcode
// given n nodes in graph labeled from 1 to n. There is no edges in the graph at the beginning. You need to support
// 1. connect(a,b), add edge to connect node a and node b.
// 2. query (a) : RETURN TEH NUMBER OF CONNECTED COMPONENT NODES WHICH INCLUDE NODE A.



//思路：延伸題：多新增一個tracking 的數組
public class ConnectingGraphII {

    int[] father = null;
    int[] size = null;


    // given x nodes in graph
    ConnectingGraphII(int x){
        father = new int[x+1];
        size = new int[x+1];

        //init
        for(int i = 1; i<x+1 ;i++){
            father[i] = i;
        }

        for(int i = 1;i<x+1;i++){
            size[i] = 1;
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
            //當每次要更新father時候，更新數組
            size[father_b] = size[father_b] + size[father_a]; // 把原father_a的size也加上去
        }
    }

    // RETURN TEH NUMBER OF CONNECTED COMPONENT NODES WHICH INCLUDE NODE A.
    public int query ( int a){
        int father_a = find(a);
        return size[father_a];
    }
}
