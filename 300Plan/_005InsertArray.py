def insert(lst, i, e):
    """
    lst：一个数组或向量，Python 就用 list 表达吧
    i：待插入元素的位置
    e：待插入元素
    """
    return lst[:i] + [e] + lst[i:]


list1 = [1, 2, 3, 4, 5, 6, 7]
print(*list1)
list2 = insert(list1, 2, 321)
print(*list2)
