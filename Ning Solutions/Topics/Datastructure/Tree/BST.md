```java
//701. Insert into a Binary Search Tree
//You are given the root node of a binary search tree (BST) 
//and a value to insert into the tree. Return the root node 
//of the BST after the insertion. 
//It is guaranteed that the new value does not exist in the original BST.
class Solution {
    TreeNode insertIntoBST(TreeNode root, int val) {
    // 找到空位置插入新节点
    if (root == null) return new TreeNode(val);
    // if (root.val == val)
    //     BST 中一般不会插入已存在元素
    if (root.val < val) 
        root.right = insertIntoBST(root.right, val);
    if (root.val > val) 
        root.left = insertIntoBST(root.left, val);
    return root;
}
}





```
