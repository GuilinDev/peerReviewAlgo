package Pratice;

import java.util.*;

class TreeNode{
    int val;
    TreeNode left, right;
    public TreeNode(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
public class BT_to_Array {
    public int[] compressDenseTree(TreeNode root){
        int height = getHeight(root);
        if (height == 0){
            return new int[0];
        }
        //dense tree的情况下,默认null node位置放0。(假设原来的tree里面没有0)
        int len = (int) Math.pow(2, height);
        int[] heap = new int[len];
        //BFS一下就压缩好了
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Queue<Integer> idxQueue = new LinkedList<>();
        //这里如果是1开头,那么就是(2i, 2i+1),如果是0开头,就是(2i+1,2i+2),其实1,2,3一下就看出来了。
        // parent = i / 2;
        idxQueue.offer(1);

        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            Integer curI = idxQueue.poll();
            heap[curI] = cur.val;
            if (cur.left != null){
                queue.offer(cur.left);
                idxQueue.offer(2*curI);
            }
            if (cur.right != null){
                queue.offer(cur.right);
                idxQueue.offer(2*curI+1);
            }
        }

        return heap;
    }
    public Map<Integer, Integer> compressSparseTree(TreeNode root){
        //前提假设是sparse tree,用map来记录,key是index,value是root的value
        Map<Integer, Integer> record = new HashMap<>();
        if (root == null) {
            return record;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> idxQueue = new LinkedList<>();
        queue.offer(root);
        idxQueue.offer(1);

        while (!queue.isEmpty()){
            TreeNode cur = queue.poll();
            int idx = idxQueue.poll();
            record.put(idx, cur.val);
            if (cur.left != null) {
                queue.offer(cur.left);
                idxQueue.offer(2*idx);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                idxQueue.offer(2*idx+1);
            }
        }
        return record;
    }
    public int getHeight(TreeNode root){
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right)+1;
    }

    public static void main(String[] args) {
        BT_to_Array test = new BT_to_Array();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        int[] res = test.compressDenseTree(t1);
        System.out.println(Arrays.toString(res));
        Map<Integer, Integer> resMap = test.compressSparseTree(t1);
        Iterator<Integer> ite = resMap.keySet().iterator();
        while (ite.hasNext()){
            int num = ite.next();
            System.out.println("root index is "+num + " root value is "+resMap.get(num));
        }
    }
}