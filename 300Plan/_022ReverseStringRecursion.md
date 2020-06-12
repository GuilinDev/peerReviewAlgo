Python
```python
def reverse_print(s): 
    if len(s) < 2: 
        return s 
    else: 
        return reverse_print(s[1:]) + s[0] 
```
Leetcode 344 没有返回值
```
class Solution:
    def reverseString(self, s: List[str]) -> None:
        """
        Do not return anything, modify s in-place instead.
        """
        self.reverse(s, 0, len(s) - 1)
        
    def reverse(self, s: List[str], left: int, right: int) -> None:
        if left < right:
            s[left], s[right] = s[right], s[left]
            self.reverse(s, left + 1, right - 1)
```

Java Leetcode 344没有返回值
```java
class Solution {
    public void reverseString(char[] s) {
        reverse(s, 0, s.length -1);
    }
    private void reverse(char[] s, int left, int right) {
        if (left >= right) {
            return;
        }
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
        
        reverse(s, left + 1, right - 1);
    }
}
```
