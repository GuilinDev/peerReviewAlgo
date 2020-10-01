## 

```
# Python 
def totalNQueens(self, n):  
	if n < 1: return []  
	self.count = 0  
	self.DFS(n, 0, 0, 0, 0)  
	return self.count 
def DFS(self, n, row, cols, pie, na):  
	# recursion terminator  
	if row >= n:  
		self.count += 1  
		return 
	bits = (~(cols | pie | na)) & ((1 << n) — 1)  # 得到当前所有的空位 
	while bits:  
		p = bits & —bits # 取到最低位的1 
		bits = bits & (bits — 1) # 表示在p位置上放入皇后 
		self.DFS(n, row + 1, cols | p, (pie | p) << 1, (na | p) >> 1)  
        # 不需要revert  cols, pie, na 的状态 
```

```
// Java 
class Solution { 
	private int size;  
	private int count; 
	private void solve(int row, int ld, int rd) {  
		if (row == size) {  
			count++;  
			return;  
		} 
		int pos = size & (~(row | ld | rd));  
		while (pos != 0) {  
			int p = pos & (-pos);  
			pos -= p; // pos &= pos - 1;  
			solve(row | p, (ld | p) << 1, (rd | p) >> 1);  
		}  
	}  
public int totalNQueens(int n) {  
	count = 0;  
	size = (1 << n) - 1;  
	solve(0, 0, 0);  
	return count;  
  }  
} 
```
```c++
//C/C++ 
class Solution { 
public: 
    int totalNQueens(int n) { 
        dfs(n, 0, 0, 0, 0); 
         
        return this->res; 
    } 
     
    void dfs(int n, int row, int col, int ld, int rd) { 
        if (row >= n) { res++; return; } 
         
        // 将所有能放置 Q 的位置由 0 变成 1，以便进行后续的位遍历 
        int bits = ~(col | ld | rd) & ((1 << n) - 1); 
        while (bits > 0) { 
            int pick = bits & -bits; // 注: x & -x 
            dfs(n, row + 1, col | pick, (ld | pick) << 1, (rd | pick) >> 1); 
            bits &= bits - 1; // 注: x & (x - 1) 
        } 
    } 

private: 
    int res = 0; 
}; 
```

```javascript
// Javascript 
var totalNQueens = function(n) { 
  let count = 0; 
  void (function dfs(row = 0, cols = 0, xy_diff = 0, xy_sum = 0) { 
    if (row >= n) { 
      count++; 
      return; 
    } 
    // 皇后可以放的地方 
    let bits = ~(cols | xy_diff | xy_sum) & ((1 << n) - 1); 
    while (bits) { 
      // 保留最低位的 1 
      let p = bits & -bits; 
      bits &= bits - 1; 
      dfs(row + 1, cols | p, (xy_diff | p) << 1, (xy_sum | p) >> 1); 
    } 
  })(); 
  return count; 
}; 
```
