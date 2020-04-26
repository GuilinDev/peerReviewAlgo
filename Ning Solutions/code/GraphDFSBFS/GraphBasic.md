```java
// 联通分量问题
public class GraphDFS {
    private Graph G;
    private boolean[] visited;
    private int count = 0;
    public GraphDFS(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v]){
             dfs(v);
             count++;
            }     
    }
    private void dfs(int v){
        visited[v] = true;
        for(int w: G.adj(v))
            if(!visited[w])
                dfs(w);
    }
    public int count(){ return cccount; }

}
// 单源路径问题
public class SingleSourcePath {
    private Graph G;
    private int s;
    private boolean[] visited;
    private int[] pre;

    public SingleSourcePath(Graph G, int s){ // S 是源
        G.validateVertex(s);
        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()]; // 通过数组记录前一个节点 类似UNIT FIND
        dfs(s, s);
    }

    private void dfs(int v, int parent){
        visited[v] = true;
        pre[v] = parent;
        for(int w: G.adj(v))
            if(!visited[w])
                dfs(w, v);
    }

    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(!isConnectedTo(t)) return res;
        int cur = t;
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }
    public static void main(String[] args){
        Graph g = new Graph("g.txt");
        SingleSourcePath sspath = new SingleSourcePath(g, 0);
        System.out.println("0 -> 6 : " + sspath.path(6));
        System.out.println("0 -> 5 : " + sspath.path(5));
    }
}
//环检测问题
public class CycleDetection {
    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;
    public CycleDetection(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                if(dfs(v, v)){
                    hasCycle = true;
                    break;
                }
    }
    // 从顶点 v 开始，判断图中是否有环
    private boolean dfs(int v, int parent){ // 要看上一个节点
        visited[v] = true;
        for(int w: G.adj(v))
            if(!visited[w]){ // 主要看是否回到之前访问过的点，并且这个点不能是父亲节点
                if(dfs(w, v)) return true;
            }
            else if(w != parent)
                return true;
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }
}

// 二分图的检测, 二分图: 所有顶点V可以分成不相交的两部分, 所有边的两个端点隶属于不同部分.
// 带环的奇数点的不是二分图 偶数点的可以是二分图. 比如正方形的对角线的两个图分为不同部分.
// 染色, 如何看二分图中的顶点属于那种颜色 只需要统计出染色的点就好.
public class BipartitionDetection {
    private Graph G;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];
        for(int i = 0; i < G.V(); i ++)
            colors[i] = -1;
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                if(!dfs(v, 0)){
                    isBipartite = false;
                    break;
                }
    }
    private boolean dfs(int v, int color){
        visited[v] = true;
        colors[v] = color;
        for(int w: G.adj(v))
            if(!visited[w]){
                if(!dfs(w, 1 - color)) return false; // 染另外一种颜色 用1 - color. 如果值是1,2 那就 3 - color
            }
            else if(colors[w] == colors[v])
                return false;
        return true;
    }
    public boolean isBipartite(){
        return isBipartite;
    }
}


```
