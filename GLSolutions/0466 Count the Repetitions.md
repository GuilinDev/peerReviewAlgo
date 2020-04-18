实际题目意思
* 定义由 n 个连接的字符串 s 组成字符串 S，即 S = [s,n]。例如，["abc", 3]=“abcabcabc”。
* 另一方面，如果我们可以从 s2 中删除某些字符使其变为 s1，我们称字符串 s1 可以从字符串 s2 获得。
* 例如，“abc” 可以根据我们的定义从 “abdbec” 获得，但不能从 “acbbe” 获得。
* 现在给你两个非空字符串 s1 和 s2（每个最多 100 个字符长）和两个整数 0 ≤ N1 ≤ 10^6 和 1 ≤ N2 ≤ 10^6。
* 现在考虑字符串 S1 和 S2，其中 S1=[s1,n1] 和 S2=[s2,n2] 。
* 请你找出一个可以满足使[s2,M] 从 S1 获得的最大整数 M 。

## Solution 1
> 暴力解法
1. 首先对n1,n2进行最大公约数化简，例如 n1= 100 n2 = 10 那么最大公约数 divisor = 10 ==> 这样 n1= 10 n2= 1可以减少长度了。
2. 找到s1 和 s2的匹配规律，即为 m * s1 = n * s2 (m >= 1, n>=1),就是m个s1可以转换为n个s2
3. 由于n1,n2的长度是有限的，因此在2中存在两种可能 ，于是我们使用了一个标志位flag判断是否成功找到了m,n. 同时在寻找过程中使用数组index 记录i个s1匹配了k个s2
    3.1找到了这样的m,n;
        count = ( (n1 / m) * n + index.get(n1 % m) ) / n2
    3.2没找到这样的m,n
        count = index.get(n1) / n2;
        
count 就是最后匹配的结果。

```java
class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (n1 == 0 || n2 == 0) {
            return 0;
        }

        // 1. 找到n1，n2的最大公约数
        int divisor = getCommonDivisor(n1, n2);
        n1 /= divisor;
        n2 /= divisor;

        int len1 = s1.length();
        int len2 = s2.length();

        int i = 0, j = 0;

        boolean flag = false; // 标志位
        List<Integer> index = new ArrayList<>(); // 表示第i个位置匹配j
        index.add(0);

        // 2. 找到m * s1 = n * s2
        while (i < len1 * n1) {
            if (s1.charAt(i % len1) == s2.charAt(j % len2)) {
                j++;
            }
            i++;
            // m * s1 = n * s2
            if (i % len1 == 0) {
                index.add(j); // 改为每len1计数1次
                if (j % len2 == 0) {
                    i = i / len1;
                    j = j / len2;
                    flag = true;
                    break;
                }
            }
        }

        // 此时i * s1 = j * s2
        // 1. 判断n1 = k * i
        int count = 0;
        if (flag) {
            count += n1 / i * j;
            count += index.get(n1 % i) / len2;
            count /= n2;
        } else {
            // 没有找到m， n
            count = index.get(n1) / len2;
            count /= n2;
        }
        return count;
    }

    // 辗转相除法求最大公约数
    int getCommonDivisor(int m, int n) {
        if (m > n) {
            int temp = m;
            m = n;
            n = temp;
        }
        int a;
        // 辗转相除法的核心就是用较大的数n去除较小的数m，如果刚好能整除，则m与n的最大公约数为m，
        // 如果不能整除，则将m的值赋给n，余数r赋给m，再进行下一次的相除，以此循环，直到整除为止
        while ((a = n % m) != 0) {
            n = m;
            m = a;
        }
        return m;
    }
}
```

## Solution 2
> 剪枝
```java
class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if(s1.length() ==0 || s2.length()==0 || n1==0 || n2 ==0) return 0;
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();
        int count = 0;
        int index = 0;
        //存储在每个s1字符串中可以匹配出的s2字符串的索引
        int[] indexr = new int[s2Chars.length+1];
        //存储在每个s1字符串时匹配出的s2字符串的个数，可能是包含了前面一个s1循环节的部分
        int[] countr = new int[s2Chars.length+1];
        for(int i = 0;i < n1;i++){
            for(int j = 0; j < s1Chars.length; j++){
                if(s1Chars[j] == s2Chars[index]) {
                    if(index == s2Chars.length -1) {
                        count++;
                        index = 0;
                    }else{
                        index++;
                    }
                }
            }
            countr[i] = count;
            indexr[i] = index;
            //剪枝，跳出循环的判断
            //从计数的数组里面去找是否已经出现过该索引。
            //意味着已经出现重复的循环节了。就可以直接计算了。
            for (int k = 0; k < i && indexr[k] == index; k++) {
                int prev_count = countr[k];
                int pattern_count = ((n1 - 1 - k) / (i - k))*(countr[i] - countr[k]);
                int remain_count = countr[k + (n1 - 1 - k) % (i - k)] - countr[k];
                return (prev_count + pattern_count + remain_count) / n2;
            }
        }
        return countr[n1 - 1] / n2;
    }
}
```