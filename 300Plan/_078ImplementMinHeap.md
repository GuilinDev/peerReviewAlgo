自己实现heapify function

```python
import sys


class MinHeap:
    def __init__(self, maxSize):
        self.maxSize = maxSize
        self.currSize = 0
        self.Heap = [0] * (self.maxSize + 1)
        self.Heap[0] = -1 * sys.maxsize
        self.TOP = 1

    """
    查找当前节点的父节点，返回index
    """

    def getParentPos(self, pos):
        return pos // 2

    """
    查找当前节点的左子树位置，返回index
    """

    def getLeftChild(self, pos):
        return 2 * pos + 1

    """
      查找当前节点的右子树位置，返回index
      """

    def getRightChild(self, pos):
        return 2 * pos + 2

    """
    检查传入的节点是否是完全二叉树的叶子节点
    """

    def isLeafNode(self, pos):
        if pos >= (self.currSize // 2) and pos <= self.currSize:
            return True
        return False

    """
    Heapify时交换两个元素调整位置
    """

    def swap(self, pos1, pos2):
        self.Heap[pos1], self.Heap[pos2] = self.Heap[pos2], self.Heap[pos1]

    """
    自己实现heapify函数
    """

    def minHeapify(self, pos):
        if not self.isLeafNode(pos):
            if (self.Heap[pos] > self.Heap[self.isLeafNode(pos)]) or (
                    self.Heap[pos] > self.Heap[self.isRightNode(pos)]):
                # heapify左子树
                if self.Heap[self.getLeftChild(pos)] < self.Heap[self.getRightChild(pos)]:
                    self.swap(pos, self.getLeftChild(pos))
                    self.minHeapify(self.getLeftChild(pos))  # 递归
                # heapify右子树
                else:
                    self.swap(pos, self.getLeftChild(pos))
                    self.minHeapify(self.getLeftChild(pos))  # 递归

    """
    向堆中添加元素
    """

    def insert(self, element):
        if self.currSize >= self.maxSize:
            return
        self.currSize += 1
        self.Heap[self.currSize] = element

        current = self.currSize
        while self.Heap[current] < self.Heap[self.getParentPos(current)]:
            self.swap(current, self.getParentPos(current))
            current = self.getParentPos(current)

    """
    移除堆顶元素，返回该元素
    """

    def remove(self):
        popped = self.Heap[self.TOP]
        self.Heap[self.TOP] = self.Heap[self.currSize]
        self.currSize -= 1
        self.minHeapify(self.TOP)
        return popped

    """
    测试打印
    """
    def Print(self):
        for i in range (1, (self.currSize // 2) + 1):
            print("Parent: " + str(self.Heap[i]))
            print("Left Child: " + str(self.Heap[2 * i + 1]))
            print("Right Child: " + str(self.Heap[2 * i + 2]))


if __name__ == "__main__":
    print("Create MinHeap:")
    minHeap = MinHeap(10)
    minHeap.insert(6)
    minHeap.insert(3)
    minHeap.insert(1)
    minHeap.insert(100)
    minHeap.insert(60)

    minHeap.Print()
```
