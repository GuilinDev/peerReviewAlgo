如果当前节点的下标为i，那和它左右子树的关系的下标为2 * i + 1和2 * i+ 2
```python
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


def list_to_binarytree(nums):
    return levelOrder(nums, None, 0, len(nums))


def levelOrder(nums, root, index, n):
    if index < n:
        temp = TreeNode(nums[index])
        root = temp

        root.left = levelOrder(nums, root.left, 2 * index + 1, n)
        root.right = levelOrder(nums, root.right, 2 * index + 2, n)

    return root


def printInOder(root):
    if root:
        printInOder(root.left)
        print(root.val, end=" ")
        printInOder(root.right)


if __name__ == '__main__':
    nums = [3, 9, 20, None, None, 15, 7]
    root = list_to_binarytree(nums)
    printInOder(root)

```
中序打印结果:
```text
None 9 None 3 15 20 7
```
