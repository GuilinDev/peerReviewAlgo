LC 933 Number of Recent Calls

二分法找到t - 3000的ceiling，list.size() - index就是比t-3000大的元素个数
```python
class RecentCounter:

    def __init__(self):
        self.ls = []

    def ping(self, t: int) -> int:
        self.ls.append(t)
        return len(self.ls) - bisect.bisect_left(self.ls, t - 3000)
        


# Your RecentCounter object will be instantiated and called as such:
# obj = RecentCounter()
# param_1 = obj.ping(t)
```

Heap
```java
class RecentCounter {
    PriorityQueue<Integer> pq;
    public RecentCounter() {
        pq = new PriorityQueue<>(); // minHeap
    }
    
    public int ping(int t) {
        int lowerBound = t - 3000;
        pq.offer(t);
        while (!pq.isEmpty() && pq.peek() < lowerBound) {
            pq.poll();
        }
        return pq.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
```

TreeSet
```java
class RecentCounter {
    TreeSet<Integer> ts;

    public RecentCounter() {
        ts = new TreeSet<>();
    }
    
    public int ping(int t) {
        ts.add(t);
        return ts.tailSet(t - 3000).size(); // 返回比t-3000大于等于的所有元素
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
```
