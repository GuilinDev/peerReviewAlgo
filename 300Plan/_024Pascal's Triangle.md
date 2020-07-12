递归-帕斯卡三角
```python
class Solution:
    def generate(self, numRows):
        # base case
        if numRows==0:
            return []
        elif numRows==1:
            return [[1]]
        elif numRows==2:
            return [[1],[1,1]]
        else:
            genNow = self.generate(numRows-1)
            li = genNow[-1]
            # 从上一行li构建三角形的新一行new_li
            new_li = [li[i] + li[i + 1] for i in range(len(li) - 1)]
            new_li.insert(0,1) # new_li第一个数字为1
            new_li.append(1) # new_li最后一个数字为1
            genNow.append(new_li)
            return genNow
```
