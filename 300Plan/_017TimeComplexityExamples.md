#### Constant 
> O(1)
```python
def constant_algo(items):
    result = items[0] * items[0]
    print ()

constant_algo([4, 5, 6, 8])
```

#### Logarithmic
> O(logn)，二分查找，减治法
```python
def binary_search(arr, low, high, x): 
  
    if high >= low: 
  
        mid = low + (high - low) // 2
  
        if arr[mid] == x: 
            return mid 
        elif arr[mid] > x: 
            return binary_search(arr, low, mid - 1, x) 
        else: 
            return binary_search(arr, mid + 1, high, x) 
  
    else: 
        return -1

```

#### Linear
> O(n)，遍历一个数据结构，quick select的平均时间
```python
def linear_algo(items):
    for item in items:
        print(item)

linear_algo([4, 5, 6, 8])
```

#### Log Linear
> O(nlogn)，快排，归并的平均时间，SciPy Scikit Learn中的一些包
```python
## 快排
def partition(arr,low,high): 
    i = (low - 1)        
    pivot = arr[high]     
  
    for j in range(low , high): 
  
        # If current element is smaller than the pivot 
        if   arr[j] < pivot: 
          
            i = i + 1 
            arr[i],arr[j] = arr[j],arr[i] 
  
    arr[i+1],arr[high] = arr[high],arr[i+1] 
    return ( i+1 ) 
  
def quickSort(arr,low,high): 
    if low < high: 
        pi = partition(arr,low,high) 
  
        quickSort(arr, low, pi-1) 
        quickSort(arr, pi+1, high) 
 
```

#### Quadratic
> O(n^2)，通常两层循环，冒泡排序，插入选择排序等
```python
def quadratic_algo(items):
    for item in items:
        for item2 in items:
            print(item, ' ' ,item2)

quadratic_algo([4, 5, 6, 8])
```

#### Exponential
> O(2^n)，每个元素可选+可不选后的组合，很多DP题目优化前的情况
```python
# 树形结构的重复计算
def fibonacci(n):
    if n <= 1:
        return n
    return fibonacci(n-1) + fibonacci(n-2)
```

#### Factorial
> O(n!)，旅行商问题，阶乘级别，通常是组合问题
```python
# Heap found
def heap_permutation(data, n):
    if n == 1:
        print(data)
        return
    
    for i in range(n):
        heap_permutation(data, n - 1)
        if n % 2 == 0:
            data[i], data[n-1] = data[n-1], data[i]
        else:
            data[0], data[n-1] = data[n-1], data[0]
            
if __name__ == '__main__':
    data = [1, 2, 3]
    heap_permutation(data, len(data))
```
