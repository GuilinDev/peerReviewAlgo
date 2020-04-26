```java
// 785. Is Graph Bipartite?
/*
Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
对于图用的是二维矩阵，在这里 第零个点的边是【1，3】所以用【1，3】作为第一个元素。
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
0----1
| \  |
|  \ |
3----2
*/
public class Solution {
    private boolean[] visited;
    private int[] colors;
    private int[][] graph;
    
    public boolean isBipartite(int[][] graph) {
        this.graph = graph;
        int V = graph.length;
        visited = new boolean[V];
        colors = new int[V];
        for(int v = 0; v < V; v ++)
            if(!visited[v])
                if(!dfs(v, 0)) return false;
        return true;
    }

    private boolean dfs(int v, int color){
        visited[v] = true;
        colors[v] = color;
        for(int w: graph[v])
            if(!visited[w]){
                if(!dfs(w, 1 - color)) return false;
            }
            else if(colors[v] == colors[w])
                return false;
        return true;
    }
}
// 图的建模：二维变一维：比如在一个10* 13 中 (2,1)--> 2×13 +1 = 27
//一维变二维：x = V/C, y = V%C, 27/13 = 2, 27%13 = 1.
// 四联通： int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
//计算机中坐标系和以前学的数学坐标系不一样
// for(int d = 0; d < 4; d ++){
//   int nextx = x + dirs[d][0];
//   int nexty = y + dirs[d][1];
// 200，130，1020，733，1034，827
//图论建模的核心： 状态表达

// 1192. Critical Connections in a Network
class Solution {
    // Tarjan bridge-finding algorithm
   public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
	int[] disc = new int[n], low = new int[n];
	// use adjacency list instead of matrix will save some memory, adjmatrix will cause MLE
	List<Integer>[] graph = new ArrayList[n];
	List<List<Integer>> res = new ArrayList<>();
	Arrays.fill(disc, -1); // use disc to track if visited (disc[i] == -1)
	for (int i = 0; i < n; i++) {
		graph[i] = new ArrayList<>();
	}
	// build graph
	for (int i = 0; i < connections.size(); i++) {
		int from = connections.get(i).get(0), to = connections.get(i).get(1);
		graph[from].add(to);
		graph[to].add(from);
	}

	for (int i = 0; i < n; i++) {
		if (disc[i] == -1) {
			dfs(i, low, disc, graph, res, i);
		}
	}
	return res;
}

int time = 0; // time when discover each vertex

private void dfs(int u, int[] low, int[] disc, List<Integer>[] graph, List<List<Integer>> res, int pre) {
	disc[u] = low[u] = ++time; // discover u
	for (int j = 0; j < graph[u].size(); j++) {
		int v = graph[u].get(j);
		if (v == pre) {
			continue; // if parent vertex, ignore
		}
		if (disc[v] == -1) { // if not discovered
			dfs(v, low, disc, graph, res, u);
			low[u] = Math.min(low[u], low[v]);
			if (low[v] > disc[u]) {
				// u - v is critical, there is no path for v to reach back to u or previous vertices of u
				res.add(Arrays.asList(u, v));
			}
		} else { // if v discovered and is not parent of u, update low[u], cannot use low[v] because u is not subtree of v
			low[u] = Math.min(low[u], disc[v]);
		}
	}
}
}

```
