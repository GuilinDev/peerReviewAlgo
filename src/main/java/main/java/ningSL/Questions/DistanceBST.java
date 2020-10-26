package main.java.ningSL.Questions;



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
