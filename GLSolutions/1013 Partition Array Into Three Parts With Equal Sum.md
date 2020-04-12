## Solution 1
> 直接的做法
```java
class Solution {
    public boolean canThreePartsEqualSum(int[] A) {
        if (A == null || A.length < 3) {
            return false;
        }
        int len = A.length;
        int sum = 0;
        for (int a : A) {
            sum += a;
        }
        if (sum % 3 != 0) {
            return false;
        }

        int index1 = 0;
        int index2 = 0;
        boolean isFirst = false;
        boolean isSecond = false;
        int part = sum / 3;
        int temp = 0;
        for (int i = 0; i < len; i++) {
            temp += A[i];
            if (temp == part) {
                temp = 0;
                index1 = i;
                isFirst = true;
                break;
            }
        }
        for (int i = index1 + 1; i < len; i++) {
            temp += A[i];
            if (temp == part) {
                temp = 0;
                index2 = i;
                isSecond = true;
                break;
            }
        }

        return isFirst && isSecond && (index2 < len - 1); //len - 1 表示最后一部分需要有值
    }
}
```