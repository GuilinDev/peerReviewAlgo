## Solution 1
> 暴力解法，统计每个number出现的次数，然后计算每个次数能否被总次数整除，O(n<sup>2</sup>)


## Solution 2
> 求出每个分组的次数，然后求出公约数，每个分组次数的公约数是否 >= 2，是的话可以被平分，复杂度O(nlogk)

```java
    class Solution {
        public boolean hasGroupsSizeX(int[] deck) {
            if (deck == null || deck.length < 2) {
                return false;
            }
            int[] values = new int[10000];
            for (int d : deck) {
                values[d]++;
            }
            int g = -1;
            for (int i = 0; i < 10000; i++) {
                if (values[i] > 0) { //有值的情况
                    if (g == -1) {
                        g = values[i];
                    } else {
                        g = gcd (g, values[i]);
                    }
                }
            }
            return g >= 2; //最大公约数>=2表明各个组以X为约数
        }
        private int gcd(int a, int b) {
            // 递归求最大公约数
            return a == 0 ? b : gcd(b % a, a);
        }
    }
```