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

// 207. Course Schedule (https://www.jianshu.com/p/b59db381561a) like BFS
/* 拓扑排序问题
根据依赖关系，构建邻接表、入度数组。
选取入度为 0 的数据，根据邻接表，减小依赖它的数据的入度。
找出入度变为 0 的数据，重复第 2 步。
直至所有数据的入度为 0，得到排序，如果还有数据的入度不为 0，说明图中存在环。*/
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses]; // 入度表
        List<List<Integer>> adjacency = new ArrayList<>(); //邻接表存储图     
        //BFS
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }
        // 给每门课添加入度，以及将入度信息和课程本身添加到邻接表中
        for (int[] pre : prerequisites) {
            indegrees[pre[0]]++; // 加的越多前置越多
            adjacency.get(pre[1]).add(pre[0]);
        }
        // 将入度为0（没有前置课程的）的课程索引初始化入队
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }
        // BFS拓扑排序
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            for (int cur : adjacency.get(pre)) { // 当前课程减去入度
                indegrees[cur]--;
                if (indegrees[cur] == 0) {
                    queue.offer(cur);
                }
            }
        }
        return numCourses == 0;
    }
}




```
