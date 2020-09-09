package main.java.ningSL;

public class UnionFind {
    public static void main(String[] args) {
        UnionFind un = new UnionFind(10);
        un.union(1,2);
        un.union(2,3);
        un.union(1,3);
        un.union(4,3);
        un.union(5,6);
        un.union(7,8);
        un.union(9,0);
       System.out.println(un.find(4));
        System.out.println(count);

    }

        public static int count = 0;
        private int[] parent;
        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            parent[rootP] = rootQ;
            count--;
        }

}
