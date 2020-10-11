```java

// 547. Friend Circles
class Solution {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < M.length; i ++) {
            for (int j = 0; j < M[0].length; j ++){
                if (M[i][j] == 1){
                    uf.union(i,j);
                }
            }
        }
        return uf.count;
    }

    public class UnionFind{
        int[] parent;
        int count;
        public UnionFind(int n){
            parent = new int[n];
            count = n;
            for (int i = 0; i < n; i ++) {
                parent[i] = i;
            }
        }
        
        public int find(int p, int[] parent) {
            if (p == parent[p]) return p;
            parent[p] = find(parent[p], parent);
            return parent[p];
        }
        
        public void union(int p, int f) {
            int p1 = find(p, parent); int f1 = find(f, parent);
            if (p1 != f1) {
                parent[p1] = f1;
                count --;
            }
        }
    }
}

//128. Longest Consecutive Sequence
public class Solution {
        private int[] parent;
        public int longestConsecutive(int[] nums) {
            startUF(nums.length);
            Map<Integer,Integer> map = new HashMap<Integer,Integer>(); // <value,index>
             for(int i=0; i<nums.length; i++){
                if(map.containsKey(nums[i])){
                    continue;
                }
                map.put(nums[i],i);  
             }
        
            for(int i=0; i<nums.length; i++){
                if(map.containsKey(nums[i]+1) && find(map.get(nums[i]), parent) != find(map.get(nums[i]+1),parent)){
                    union(i,map.get(nums[i]+1));
                }
                if(map.containsKey(nums[i]-1) && find(map.get(nums[i]), parent) != find(map.get(nums[i]-1),parent)){
                    union(i,map.get(nums[i]-1));
                }
            }
            return maxUnion();
        }
      
        public void startUF(int n){
            parent = new int[n];
            for(int i=0; i<n; i++){
                parent[i] = i;
            }
        }
        
        private int find(int i, int[] parent){
           if (i == parent[i]) return i;
            parent[i] = find(parent[i], parent);
            return parent[i];
        }
        
        public void union(int p, int q){
          int i = find(p,parent);
          int j = find(q,parent);
          if(i != j){
             parent[i] = j; 
          }  
          
        }
        // returns the maxium size of union
        public int maxUnion(){ // O(n)
            int[] count = new int[parent.length];
            int max = 0;
            for(int i = 0; i < parent.length; i++) {
                count[find(i,parent)] ++;
                max = Math.max(max, count[find(i,parent)]);
            }
            return max;
        }
}




```
