```java

// Amazon Fresh Promotion
public class FindFruitCombs {
    private static int winPrize(String[][] codeList,String[] shoppingCart){
        //check zero length...
        if(codeList == null || codeList.length == 0)
            return 1;
        if(shoppingCart == null || shoppingCart.length == 0)
            return 0;
        int i=0,j=0;
        for(int k=0;k<shoppingCart.length;k++) {
            //when match success
            if(codeList[i][j].equals(shoppingCart[k]) || codeList[i][j].equals("anything")) {
                j++;
                if(j == codeList[i].length) {
                    i++;
                    j=0;
                }
                if(i == codeList.length)
                    return 1;
            }else {
                //when match failed, k and j both go back.
                k-=j;
                j=0;
            }
        }
        return 0;
    }
}
// Amazon Music Pairs
public class MusicPairs {
    private static int solve(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int n : nums) {
            n = n % 60;
            int key = 60 - n == 60 ? 0 : 60 - n;
            if(map.containsKey(key))
                res += map.get(key);
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        return res;
    }
}
//  Items in Containers
public class itemsInContainers {
   public static int[] numberOfItems(String s, int[] startindices, int[] endindices){
        int l = s.length(), n = startindices.length;
        int[] res = new int[n];
        int[] left = new int[l]; int[] right = new int[l]; int[] star = new int[l];
        int pre_left = -1, post_right = -1, count = 0;
        for(int i = 0; i < l; i++){
            if(s.charAt(i) == '|') pre_left = i;
            left[i] = pre_left;
        }
        for(int i = l - 1; i >= 0; i--){
            if(s.charAt(i) == '|') post_right = i;
            right[i] = post_right;
        }
        for(int i = 0; i < l; i++){
            if(s.charAt(i) == '*') count++;
            star[i] = count;
        }
        for(int i = 0; i < n; i++) {
            int start = startindices[i] - 1, end = endindices[i] - 1;
            int a = right[start], b = left[end];
            if(a >=b ) res[i] = 0;
            else res[i] = star[b] - star[a];
        }
        return res;
    }
}

//Largest Item Association
public class LargestItemAssociation {
 public static List<String> largestItemAssociation(List<PairString> pairs) {
        if (pairs.isEmpty())
            return null;
        PriorityQueue<Set<String>> maxHeap = new PriorityQueue<>(//
                (l1, l2) -> Integer.compare(l2.size(), l1.size()));//
        Collections.sort(pairs, (a, b) -> a.first.compareTo(b.first));
        for (int i = 0; i < pairs.size(); i++) {
            Set<String> list = new TreeSet<>(Arrays.asList(pairs.get(i).first, pairs.get(i).second));
            for (int j = i + 1; j < pairs.size(); j++) {
                merge(list, pairs.get(j));
            }
            maxHeap.add(list);
        }
        return maxHeap.poll().stream().collect(Collectors.toList());
    }

    private static void merge(Set<String> list, PairString pairString) {
        if (list.contains(pairString.first) && list.contains(pairString.second))
            return;
        if (list.contains(pairString.first)) {
            list.add(pairString.second);
        } else if (list.contains(pairString.second)) {
            list.add(pairString.first);
        }
    }

    public static void main(String[] args) {
        List<PairString> pairs = Arrays.asList( //
                new PairString("item3", "item2"), // -> item1, item3, item2
                new PairString("item3", "item4"), //
                new PairString("item2", "item1") //
        );

        System.out.println(largestItemAssociation(pairs));
    }
}

class PairString {
    String first;
    String second;

    public PairString(String first, String second) {
        this.first = first;
        this.second = second;
    }
}

// Turnstile
public class Turnstile {
    public static void main(String[] args) {
// int[] result = s.getTimes(4, new int[] { 0, 0, 1, 5 }, new int[] { 0, 1, 1, 0 });
        int[] result = getTimes(5, new int[] { 0, 1, 1, 3, 3 }, new int[] { 0, 1, 0, 0, 1 });
        System.out.println(Arrays.toString(result));
    }

    public static int[] getTimes(int numCustomers, int[] arrTime, int[] direction) {

        int[] result = new int[numCustomers];

        Map<Integer, List<int[]>> map = new HashMap<Integer, List<int[]>>();

        Queue<int[]> entryQ = new ArrayDeque<int[]>();
        Queue<int[]> exitQ = new ArrayDeque<int[]>();
        int i = 0;
        int turnstileDirection = -1; // default unused-1
        for (int a : arrTime) {
            if (!map.containsKey(a)) {
                map.put(a, new ArrayList<int[]>());
            }
            map.get(a).add(new int[] { i, direction[i] });
            i++;
        }

        i = 0; // used to determine timeStamp,
        while (numCustomers > 0) {

            if (map.containsKey(i)) {
                List<int[]> customers = map.get(i);

                for (int[] customer : customers) {
                    if (customer[1] == 1) {
                        exitQ.add(customer);
                    } else {
                        entryQ.add(customer);
                    }
                }
            }

            if (entryQ.isEmpty() && exitQ.isEmpty()) {
                turnstileDirection = -1;
                i++;
                continue;
            }

            int[] current;
            if (!entryQ.isEmpty() && !exitQ.isEmpty()) {

                switch (turnstileDirection) {
                    case -1: // unused
                        current = exitQ.poll();
                        result[current[0]] = i;
                        numCustomers--;
                        turnstileDirection = 1;
                        i++;
                        break;

                    case 1: // used for exit
                        current = exitQ.poll();
                        result[current[0]] = i;
                        numCustomers--;
                        turnstileDirection = 1;
                        i++;
                        break;

                    case 0: // used to entry
                        current = entryQ.poll();
                        result[current[0]] = i;
                        numCustomers--;
                        turnstileDirection = 0;
                        i++;
                        break;

                }
                continue;
            }

            if (!entryQ.isEmpty()) {
                current = entryQ.poll();
                result[current[0]] = i;
                numCustomers--;
                turnstileDirection = 0;
                i++;
                continue;
            }

            if (!exitQ.isEmpty()) {
                current = exitQ.poll();
                result[current[0]] = i;
                numCustomers--;
                turnstileDirection = 1;
                i++;
                continue;
            }
        }

        return result;
    }
}

//Five Star Sellers
public class Solution {
    public int fiveStarReviews(List<List<Integer>> productRatings, int ratingsThreshold){
        int num = 0;
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((l1, l2) -> diff(l2) - diff(l1)); // max-heap.
        for(List<Integer> rating : productRatings) pq.offer(rating); // initialize PriorityQueue.
        double curRating = 0;
        for(List<Integer> rating : productRatings) curRating += 100.0 * rating.get(0) / rating.get(1); // initialize curRating.
        while(curRating < ratingsThreshold * productRatings.size()) {
            num++;
            List<Integer> rating = pq.poll();
            List<Integer> newRating = Arrays.asList(rating.get(0)+1, rating.get(1)+1);
            curRating = curRating - 100.0 * rating.get(0) / rating.get(1) + 100.0 * newRating.get(0) / newRating.get(1); // keep updating the rating.
            pq.offer(newRating);
        }
        return num;
    }

    // the diff between the current product rating and the product added one more five-star rating.
    private int diff(List<Integer> p) {
        double currRating = 100.0 * p.get(0) / p.get(1);
        double newRating = 100.0 * (p.get(0)+1) / (p.get(1)+1);
        return (int)(newRating - currRating);
    }

    public static void main(String[] args) {
        List<List<Integer>> ratings = new ArrayList(){
            {
                add(Arrays.asList(4,4));
                add(Arrays.asList(1,2));
                add(Arrays.asList(3,6));
            }
        };
        int threshold = 77;
        Solution s = new Solution();
        System.out.println(s.fiveStarReviews(ratings, threshold));
    }
}
// Beta Testing  1335. Minimum Difficulty of a Job Schedule
class Solution {
 public int minDifficulty(int[] jobDifficulty, int D) { 
            int N = jobDifficulty.length;
        if(N < D) return -1;
        int[][] dp = new int[D][N];

        dp[0][0] = jobDifficulty[0];
        for(int i = 1; i < N; i ++){
            dp[0][i] = Math.max(jobDifficulty[i], dp[0][i - 1]);
        }

        for(int j = 1; j < D; ++j){
            for(int len = j; len < N; ++len){
                int max = jobDifficulty[len];
                dp[j][len] = Integer.MAX_VALUE;
                for(int schedule = len; schedule >= j; --schedule){
                    max = Math.max(max, jobDifficulty[schedule]);
                    dp[j][len] = Math.min(dp[j][len], dp[j - 1][schedule - 1] + max);
                }
            }
        }
        return dp[D - 1][N - 1];
    }
}

// Utilization Checks
public class UtilizationChecks {
private static final int LIMIT = 2 * 100_000_000;
public static int finalInstances(int instances, List<Integer> averageUtil) {
    for (int i = 0; i < averageUtil.size(); i++) {
        int util = averageUtil.get(i);
        if (util < 25) {
            if (instances > 1) {
                instances = instances / 2 + ((instances % 2 == 0) ? 0 : 1);
                i += 10;
            }
        } else if (util > 60) {
            int newInstances = 2 * instances;
            if (newInstances <= LIMIT) {
                instances = newInstances;
                i += 10;
            }
        }
    }
    return instances;
}
}

//Top K Frequently Mentioned Keywords
public class TopKFrenquencyWords {
    public static void main(String[] args) {
        int k1 = 2;
        String[] keywords1 = { "anacell", "cetracular", "betacellular" };
        String[] reviews1 = { "Anacell provides the best services in the city", "betacellular has awesome services",
                "Best services provided by anacell, everyone should use anacell", };
        int k2 = 2;
        String[] keywords2 = { "anacell", "betacellular", "cetracular", "deltacellular", "eurocell" };
        String[] reviews2 = { "I love anacell Best services; Best services provided by anacell",
                "betacellular has great services", "deltacellular provides much better services than betacellular",
                "cetracular is worse than anacell", "Betacellular is better than deltacellular.", };
        TopKFrenquencyWords tk = new TopKFrenquencyWords();
        System.out.println(tk.solve(k1, keywords1, reviews1));
        System.out.println(tk.solve(k2, keywords2, reviews2));

    }
    private  List<String> solve(int k, String[] keywords, String[] reviews) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>(Arrays.asList(keywords));
        Map<String, Integer> map = new HashMap<>();
        for(String r : reviews) {
            String[] strs = r.split("\\W");
            Set<String> added = new HashSet<>();
            for(String s : strs) {
                s = s.toLowerCase();
                if(set.contains(s) && !added.contains(s)) {
                    map.put(s, map.getOrDefault(s, 0) + 1);
                    added.add(s);
                }
            }
        }
        Queue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>((a, b)->
                a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue()); // why not a.val - b.val
        maxHeap.addAll(map.entrySet());
        while(!maxHeap.isEmpty() && k > 0) { 
            res.add(maxHeap.poll().getKey());
            k--;
        }
        return res;
    }
}

//Transaction Logs
public class FraudIds {
    public static void main(String[] args) {
        String[] input = new String[] { "345366 89921 45", "029323 38239 23", "38239 345366 15", "029323 38239 77",
                "345366 38239 23", "029323 345366 13", "38239 38239 23" };
        System.out.println(getFraudIds(input, 3));
    }
    public static List<String> getFraudIds(String[] input, int threshold) {
        List<String> res = new ArrayList<>();
        HashMap<String,Integer> map = new HashMap<>();
        for (String line : input){
            String[] parts = line.split(" ");
            if(parts[0].equals(parts[1])){
                String key = parts[0];
                map.put(key, map.getOrDefault(key, 0) + 1);
            } else {
                String key1 = parts[0];
                String key2 = parts[1];
                map.put(key1, map.getOrDefault(key1, 0) + 1);
                map.put(key2, map.getOrDefault(key2, 0) + 1);
            }
        }
        for (String key: map.keySet()){
            if(map.get(key) >= threshold){
                res.add(String.valueOf(key));
            }
        }
      //  Collections.sort(res, (o1,o2)-> Integer.valueOf(o1) - Integer.valueOf(o2));
        Collections.sort(res);
        return res;
    }
}

//Substrings of Size K with K-1 Distinct Chars
//Input: 
//  inputString = wawaglknagagwunagkwkwagl num = 4
//  Output:
//  [awag, naga, gagw, gkwk, wkwa]
public class FindDistinct {
    public static List<String> findSubstrings(String str, int k) {
        List<String> res = new ArrayList<>();
        Set<String> resSet = new HashSet<>();
        int left = 0; int right = 0;
        Map<Character, Integer> window = new HashMap<>();
        while (right < str.length()){
            char r = str.charAt(right);
            right ++;
            window.put(r,window.getOrDefault(r,0) + 1);
            while (right - left > k){
                char l = str.charAt(left);
                left++; // don't forget
                window.put(l, window.getOrDefault(l,0) - 1);
                if (window.get(l) == 0){
                    window.remove(l);
                }
            }
            if(right - left == k && window.size() == k - 1){
                resSet.add(str.substring(left,right));
            }
        }
        res.addAll(resSet);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findSubstrings("wawaglknagagwunagkwkwagl",4));
    }
}

// 200. Number of Islands
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int r = grid.length;
        int c = grid[0].length;
        int res = 0;
    //    boolean[][] visted = new boolean[r][c];
        for (int i = 0; i < r; i ++){
            for (int j = 0; j < c; j ++){
                if (grid[i][j] == '1'){
                    searchIsland(grid,i,j);
                    res ++;
                }
                
            }
        }
        return res;
    }
    
    
    public void searchIsland(char[][] grid, int row, int col) {
        if(!inArea(grid,row, col)) return;
        if(grid[row][col] != '1') return;
        grid[row][col] = '2';
        searchIsland(grid, row - 1, col);
        searchIsland(grid, row + 1, col);
        searchIsland(grid, row, col - 1);
        searchIsland(grid, row, col + 1);
    }
    
    public boolean inArea(char[][] grid, int row, int col){
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
    
}
//819. Most Common Word
// paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 //  banned = ["hit"] Output: "ball"  Explanation: 
 //  "hit" occurs 3 times, but it is a banned word.
//   "ball" occurs twice (and no other word does), 
//   so it is the most frequent non-banned word in the paragraph. 
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        String[] words = paragraph.toLowerCase().split("\\W+");// capital "W"
        Set<String> banSet = new HashSet<>(Arrays.asList(banned));
        HashMap<String, Integer> map = new HashMap<>();
        for (String word: words) {
            if (!banSet.contains(word)){
               map.put(word, map.getOrDefault(word, 0) + 1); 
            }
        }
        int max = 0;
        String res = "";
        for (String key: map.keySet()) {
            if(map.get(key) > max){
                max = map.get(key);// don't forget
                res = key;
            }
        }
        return res;
    }
}

// 973. K Closest Points to Origin
class Solution {
    public int[][] kClosest(int[][] points, int K) {
    PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1,p2) -> 
                        p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
    for (int i = 0; i < points.length; i ++){
        pq.offer(points[i]);
        if (pq.size() > K){
            pq.poll();
        }
    }
    int[][] res = new int[K][2];
                                                    
    for (int i = K - 1; i >= 0; i --){
        res[i] = pq.poll();
    }
     return res;                                               
    }
}
// Distance Between Nodes in BST
public class DistanceBST {
    public static void main(String[] args) {
         int[] nums = {2,1,3};
         int i = 1;
         int j = 3;
         System.out.println(findDistance(nums, i,j));
    }
    public static int findDistance(int[] is, int i, int j) {
        TreeNode root = null;
        for(int k=0;k< is.length;k++){
            root = buildBST(root, is[k]);
        }
        TreeNode lca = findLowestCommonAncestor(root, i, j);
        int distance = distanceFromRoot(lca,i)+ distanceFromRoot(lca,j);
        return distance;
    }

    private static int distanceFromRoot(TreeNode root, int val) {
        if (root.val == val)
            return 0;
        else if (root.val > val)
            return 1 + distanceFromRoot(root.left, val);
        return 1 + distanceFromRoot(root.right, val);
    }

    private static TreeNode findLowestCommonAncestor(TreeNode root, int i, int j) {
        if(root.val > i && root.val > j){
            return findLowestCommonAncestor(root.left, i, j);
        }else if(root.val < i && root.val < j){
            return findLowestCommonAncestor(root.right, i, j);
        }else{
            return root;
        }
    }

    private static TreeNode buildBST(TreeNode root , int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = buildBST(root.right, val);
        } else {
            root.left = buildBST(root.left, val);
        }
        return root;
    }
}

class TreeNode {
    TreeNode left;
    TreeNode right;
    int val;
    TreeNode(int val) {
        this.val = val;
    }
}

//701. Insert into a Binary Search Tree
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
}

// 235. Lowest Common Ancestor of a Binary Search Tree
class Solution {
TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    // base case
    if (root == null) return null;
    if (root == p || root == q) return root;
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
// 后续遍历
    if (left != null && right != null) {
        return root;
    }
    if (left == null && right == null) {
        return null;
    }
    return left == null ? right : left;
}
}

```
