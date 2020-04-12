## Solution 1
> 直接解法就是把array2放到array1中然后再sort，这里可以从m+n-1的位置开始放最大者
```java
class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
        // 从m + n - 1开始
        while (m > 0 && n > 0) {
            if (A[m - 1] >= B[n - 1]) {
                A[m + n - 1] = A[m - 1];
                m--;
            } else {
                A[m + n - 1] = B[n - 1];
                n--;
            }
        }

        // 剩下的数都比已经遍历过的数小
        // 如果 m 不为 0，则 A 没遍历完，都已经在 A 中不用再管
        // 如果 n 不为 0，则 B 没遍历完，直接全移到 A 中相同的位置
        while (n > 0) {
            A[n - 1] = B[n - 1];
            n--;
        }
    }
}
```