```java
//左移 << 0011 -> 0110 右移 0110 -> 0011
//  &	与	两个位都为 1 时，结果才为 1
//  I	或	两个位都是 0 时，结果才为 0
//  ^	异或	两个位相同时为 0，相异为 1 1^1=0  1^0=1                
//  ~	取反	0 变 1，1 变 0

// 1: 判断奇偶数
// 只要根据最未位是 0 还是 1 来决定，为 0 就是偶数，为 1 就是奇数。 因此可以用 if ((a & 1) == 0) 
//代替 if (a % 2 == 0) 来判断 a 是不是偶数

// 2 交换两位数
//a ^= b; 第一步 a ^= b 即 a = (a ^ b)；
//b ^= a; 第二步 b ^= a 即 b= b ^ ( a ^ b)，由于异或运算满足交换律，b ^ ( a ^ b) = b ^ b ^ a。
//由于一个数和自己异或的结果为 0 并且任何数与 0 异或都会不变的，所以此时 b 被赋上了 a 的值；
//a ^= b; 第三步 a ^= b 就是 a = a ^ b，由于前面二步可知 a = ( a ^ b)，b=a，
//所以 a = a ^ b 即 a = ( a ^ b ) ^ a。故 a 会被赋上 b 的值。

// 3 乘除
// a=a*4; a=a<<2; b=b/4; 　b=b>>2;  a=a*9 => a*8+a*1 => a=(a<<3) + a;  a=a*7 = a=(a<<3)- a

// 4 变换正负号 变换符号只需要取反操作后加 1 即可 ： ~15 +1 = -15

//5  x & (x - 1) ，可以将最右边的 1 设置为 0。（这个技巧可以用来检测 2的幂，
// 或者检测一个整数二进制中 1 的个数，又或者别人问你一个数变成另一个数其中改变了多少个bit位，统统都是它）


// 136. Single Number
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num: nums) {
            ans = ans^num;
        }
        return ans;
    }
}

// 137 Single Number II
class Solution {
 public int singleNumber(int[] nums) {
        int x1 = 0, x2 = 0, mask = 0;
        for (int i : nums) {
            x2 ^= x1 & i;
            x1 ^= i;
            mask = ~(x1 & x2);
            x2 &= mask;
            x1 &= mask;
        }
        return x1;  // Since p = 1, in binary form p = '01', then p1 = 1, so we should return x1. 
                    // If p = 2, in binary form p = '10', then p2 = 1, and we should return x2.
                    // Or alternatively we can simply return (x1 | x2).
    }
}

// 461. Hamming Distance 
// 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
//1.两个数字异或之后可以得到 不同二进制位的数字
//2.计算该数字中1的个数，即是汉明距离
//计算1的个数时，有几种方法，1：不断和左移的1 进行 与，判断该位是否为1.
// 2: n&(n-1)就是把n最右边的bit 1 位去掉，看能去掉几次，就有几个1位。
class Solution {
    public int hammingDistance(int x, int y) {
        int a = x ^ y;
        int ans = 0;
        while (a != 0) {
            ans++;
            a = a & (a - 1);
        }
        return ans;
        
    }
}

// 371. Sum of Two Integers
class Solution {
    public int getSum(int a, int b) {
        while(b != 0){
            int temp = a ^ b;
            b = (a & b) << 1;
            a = temp;
        }
        return a;
    }
}

// 169. Majority Element
class Solution {
// Sorting
public int majorityElement1(int[] nums) {
    Arrays.sort(nums);
    return nums[nums.length/2];
}

// Hashtable 
public int majorityElement2(int[] nums) {
    Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
    //Hashtable<Integer, Integer> myMap = new Hashtable<Integer, Integer>();
    int ret=0;
    for (int num: nums) {
        if (!myMap.containsKey(num))
            myMap.put(num, 1);
        else
            myMap.put(num, myMap.get(num)+1);
        if (myMap.get(num)>nums.length/2) {
            ret = num;
            break;
        }
    }
    return ret;
}

// Moore voting algorithm
public int majorityElement3(int[] nums) {
    int count=0, ret = 0;
    for (int num: nums) {
        if (count==0)
            ret = num;
        if (num!=ret)
            count--;
        else
            count++;
    }
    return ret;
}

// Bit manipulation 
public int majorityElement(int[] nums) {
    int[] bit = new int[32];
    for (int num: nums)
        for (int i=0; i<32; i++) 
            if ((num>>(31-i) & 1) == 1)
                bit[i]++;
    int ret=0;
    for (int i=0; i<32; i++) {
        bit[i]=bit[i]>nums.length/2?1:0;
        ret += bit[i]*(1<<(31-i));
    }
    return ret;
}
}

// 231. Power of Two
class Solution {
    public boolean isPowerOfTwo(int n) {
         return (n > 0) && (n&(n-1)) == 0;
        
    }
}

//191. Number of 1 Bits
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int sum = 0;
    while (n != 0) {
        sum++;
        n &= (n - 1);
    }
    return sum;
    }
}

// 268 Missing Number
// 1  1+2+3+...+n = (1+n) * n / 2 再减去数组中所有数字之和就是答案
class Solution {
    public int missingNumber(int[] nums) {
        int length=nums.length;
        int result=(length+1)*length/2;
        for(int e:nums)
            result-=e;
        return result;
    }
}
// 位运算
class Solution {
    public int missingNumber(int[] nums) {
        int res = 0;
        for(int i = 0; i < nums.length; i++)
            res ^= nums[i] ^ i;
        return res ^ nums.length;
    }
}

```
