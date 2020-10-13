```java

// 207. Course Schedule (https://www.jianshu.com/p/b59db381561a) like BFS
/* 拓扑排序问题
拓扑排序通常用来“排序”具有依赖关系的任务。
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
            adjacency.get(pre[1]).add(pre[0]); // pre[1] 是pre[0] 的前置
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
                if (indegrees[cur] == 0) { // 依次去除对PRE的关系
                    queue.offer(cur);
                }
            }
        }
        return numCourses == 0;
    }
}





```
