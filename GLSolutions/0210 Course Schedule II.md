## Solution 1
> 拓扑排序，BFS
```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return new int[0];
        }
        int[] inDegrees = new int[numCourses];
        // 1. 建立入度表
        for (int[] prerequisite : prerequisites) {
            inDegrees[prerequisite[0]]++;
        }

        // 2. 入度为0的节点队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) { // 没有prerequirements的课程，在prerequisites没有出现在第一位
                queue.offer(i);
            }
        }

        int count = 0; // 记录可以学完的课程数量
        int[] results = new int[numCourses]; // 可以学完的课程
        // 3. 当队列不为空，节点出队，标记学完课程数量的变量加 1，并记录该课程
        while (!queue.isEmpty()) {
            int current = queue.poll();
            results[count] = current;
            count++;
            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] == current) { // 完成一门前置课
                    // 4. 将课程的入度减 1
                    inDegrees[prerequisite[0]]--;
                    // 5. 若邻居课程入度为 0，加入队列, 前置课已经学完
                    if (inDegrees[prerequisite[0]] == 0) { 
                        queue.offer(prerequisite[0]);
                    }
                }
            }
        }
        if (count == numCourses) {
            return results;
        }
        return new int[0];
    }
}
```

## Solution 2
> 邻接矩阵， DFS
```java
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[0];
        // HashSet 作为邻接矩阵，提高查找速度
        HashSet<Integer>[] graph = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new HashSet<>();
        }
        for (int[] p : prerequisites) {
            graph[p[1]].add(p[0]);
        }
        int[] mark = new int[numCourses]; // 标记数组
        Stack<Integer> stack = new Stack<>(); // 结果栈
        for (int i = 0; i < numCourses; i++) {
            if(!isCycle(graph, mark, i, stack)) return new int[0];
        }
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = stack.pop();
        }
        return res;
    }

    private boolean isCycle(HashSet<Integer>[] graph, int[] mark, int i, Stack<Integer> stack) {
        if (mark[i] == -1) return true;
        if (mark[i] == 1) return false;

        mark[i] = 1;
        for (int neighbor : graph[i]) {
            if (!isCycle(graph, mark, neighbor, stack)) return false;
        }
        mark[i] = -1;
        stack.push(i);
        return true;
    }
}
```