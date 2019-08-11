package algo.UnionFind;


//lintcode
// given n nodes in graph labeled from 1 to n. There is no edges in the graph at the beginning. You need to support
// 1. connect(a,b), add edge to connect node a and node b.
// 2. query (a) : RETURN TEH NUMBER OF CONNECTED COMPONENT IN THE GRAPH.


//思路：延伸題：多新增一個tracking 的數組
public class ConnectingGraphIII {

    int[] father = null;
    int totalcount = 0;

    // given x nodes in graph
    ConnectingGraphIII(int x) {
        father = new int[x + 1];
        totalcount = x; //initial state, total number of graph =  x nodes.
        //init
        for (int i = 1; i < x + 1; i++) {
            father[i] = i;
        }
    }

    //given a and return its father node
    public int find(int a) {
        if (father[a] == a) {
            return a;
        }
        return father[a] = find(father[a]); // path compression for O(1)
    }


    //connect a and b
    public void connect(int a, int b) {
        int father_a = find(a);
        int father_b = find(b);
        if (father_a != father_b) {
            father[father_a] = father_b;
            //當每次要更新father時候，更新數組
            totalcount--;
        }
    }

    // RETURN TEH NUMBER OF CONNECTED COMPONENTS.
    public int query() {
        return totalcount;
    }
}
