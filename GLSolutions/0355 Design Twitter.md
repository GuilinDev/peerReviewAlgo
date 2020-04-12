## Solution 1
> 哈希表 + 链表

```java
class Twitter {

    private static int timestamp = 0;
    /**
    新添加 Tweet 和 User 两个类，之所以要把 Tweet 和 User 类放到 Twitter 类里面，是因为 Tweet 类必须要用到一个全局时间戳 timestamp，而 User 类又需要用到Tweet 类记录用户发送的推文，所以它们都作为内部类。
    */
    private static class Tweet {
        private int id;
        private int time;
        private Tweet next;

        // 传入推文内容(这里是id)和发文时间做初始化
        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
            this.next = null;
        }
    }
    // 根据面向对象的设计原则，「关注」「取关」和「发文」应该是 User 的行为，况且关注列表和推文列表也存储在 User 类中，所以我们也应该给 User 添加 follow，unfollow 和 post 这几个方法
    private static class User {
        private int id; // 自己的id
        public Set<Integer> followed; // 自己关注的所有人，不能有重复
        // 用一个链表来记录用户发表的推文，这里先创建链表头节点
        public Tweet head;

        public User(int userId) {
            followed = new HashSet<>();
            this.id = userId;
            this.head = null;
            // 需要关注自己
            follow(id);
        }

        public void follow(int userId) {
            followed.add(userId);
        }

        public void unfollow(int userId) {
            // 不可以取关自己
            if (userId != this.id) {
                followed.remove(userId);
            }
        }

        public void post(int tweetId) {
            Tweet twt = new Tweet(tweetId, timestamp);
            timestamp++;
            // 将新建的推文插入到链表头，越靠前的推文time值越大
            twt.next = head;
            head = twt;
        }
    }

    // 我们需要一个映射将userId和User对象对应起来
    private HashMap<Integer, User> userMap = new HashMap<>();

    /** Initialize your data structure here. */
    public Twitter() {

    }
    
    /** Compose a new tweet. */
    /** user 发表一条 tweet 动态 */
    public void postTweet(int userId, int tweetId) {
        // 若 userId 不存在，则新建
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }
        User u = userMap.get(userId);
        u.post(tweetId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    /** 返回该 user 关注的人（包括他自己）最近的动态 id，
    最多 10 条，而且这些动态必须按从新到旧的时间线顺序排列。*/
    // 把用户的tweet存到一个链表中，合并有序的n个链表的算法题
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        if (!userMap.containsKey(userId)) {
            return result;
        }

        // 获取关注的列表用户ID
        Set<Integer> users = userMap.get(userId).followed;

        // 创建一个pq，根据time的属性从大到小（由新到旧）排序，容量为users的大小
        PriorityQueue<Tweet> pq = new PriorityQueue<>(users.size(), (a, b) -> (b.time - a.time));

        // 先将所有链表头结点插入pq
        for (int id : users) {
            Tweet twt = userMap.get(id).head;
            if (twt == null) {
                continue;
            }
            pq.offer(twt);
        }

        while (!pq.isEmpty()) {
            // 最多返回10条
            if (result.size() == 10) {
                break;
            }
            // 弹出time值最大的(最近发表的)
            Tweet twt = pq.poll();
            result.add(twt.id);
            //将下一篇Tweet插入进行排序
            if (twt.next != null) {
                pq.offer(twt.next);
            }
        }
        return result;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    /** follower 关注 followee */
    public void follow(int followerId, int followeeId) {
        // 若 follower 不存在，则新建
        if (!userMap.containsKey(followerId)) {
            User u = new User(followerId);
            userMap.put(followerId, u);
        }
        // 若 followee 不存在，则新建
        if (!userMap.containsKey(followeeId)) {
            User u = new User(followeeId);
            userMap.put(followeeId, u);
        }

        userMap.get(followerId).follow(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    /** follower 取关 followee，如果 Id 不存在则什么都不做 */
    public void unfollow(int followerId, int followeeId) {
        if (userMap.containsKey(followerId)) {
            User flwer = userMap.get(followerId);
            flwer.unfollow(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
```