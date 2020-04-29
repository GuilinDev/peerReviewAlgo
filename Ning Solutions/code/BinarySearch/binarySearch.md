```java
/*
int binarySearch(int[] nums, int target) {
    int left = 0, right = ...;

    while(...) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            ...
        } else if (nums[mid] < target) {
            left = ...
        } else if (nums[mid] > target) {
            right = ...
        }
    }
    return ...;
}

第一个，最基本的二分查找算法：
因为我们初始化 right = nums.length - 1 所以决定了我们的「搜索区间」是 [left, right]
所以决定了 while (left <= right) 同时也决定了 left = mid+1 和 right = mid-1
因为我们只需找到一个 target 的索引即可
所以当 nums[mid] == target 时可以立即返回


第二个，寻找左侧边界的二分查找：
因为我们初始化 right = nums.length 所以决定了我们的「搜索区间」是 [left, right)
所以决定了 while (left < right) 同时也决定了 left = mid + 1 和 right = mid
因为我们需找到 target 的最左侧索引
所以当 nums[mid] == target 时不要立即返回 而要收紧右侧边界以锁定左侧边界

第三个，寻找右侧边界的二分查找：
因为我们初始化 right = nums.length 所以决定了我们的「搜索区间」是 [left, right)
所以决定了 while (left < right) 同时也决定了 left = mid + 1 和 right = mid

因为我们需找到 target 的最右侧索引 所以当 nums[mid] == target 时不要立即返回
而要收紧左侧边界以锁定右侧边界 又因为收紧左侧边界时必须 left = mid + 1
所以最后无论返回 left 还是 right，必须减一

int binary_search(int[] nums, int target) {
    int left = 0, right = nums.length - 1; 
    while(left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid - 1; 
        } else if(nums[mid] == target) {
            // 直接返回
            return mid;
        }
    }
    // 直接返回
    return -1;
}

int left_bound(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid - 1;
        } else if (nums[mid] == target) {
            // 别返回，锁定左侧边界
            right = mid - 1;
        }
    }
    // 最后要检查 left 越界的情况
    if (left >= nums.length || nums[left] != target)
        return -1;
    return left;
}


int right_bound(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid - 1;
        } else if (nums[mid] == target) {
            // 别返回，锁定右侧边界
            left = mid + 1;
        }
    }
    // 最后要检查 right 越界的情况
    if (right < 0 || nums[right] != target)
        return -1;
    return right;
}

*/

// 69 Sqrt(x)
class Solution {
    public int mySqrt(int x) {
        if (x <= 1) return x;
        int left = 1; int right = x; int result = 0;
        while (left <= right) {
          int  mid = left + (right - left) / 2;
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid ) {
                result = mid;
                left = mid + 1;
            } else {
                
                right = mid - 1;
            }    
        }
         return result;
    }
}
// Newton method
class Solution {
    public int mySqrt(int x) {
        long r = x;
    while (r*r > x)
        r = (r + x/r) / 2;
    return (int) r;
    }
}

//367. Valid Perfect Square: 4*4 = 16
class Solution {
  public boolean isPerfectSquare(int num) {
    if (num < 2) {
      return true;
    }
    long left = 2, right = num / 2, x, guessSquared;
    while (left <= right) {
      x = left + (right - left) / 2;
      guessSquared = x * x;
      if (guessSquared == num) {
        return true;
      }
      if (guessSquared > num) {
        right = x - 1;
      } else {
        left = x + 1;
      }
    }
    return false;
  }
}

// Newton method
class Solution {
    public boolean isPerfectSquare(int num) {
        long r = num;
        while (r * r > num) {
            r = (r + num/r)/2;
        }
        if (r*r == num) return true;
        return false;  
    }
}
//33. Search in Rotated Sorted Array
/*题目要求 O(logN)O(logN) 的时间复杂度，基本可以断定本题是需要使用二分查找，怎么分是关键。
由于题目说数字了无重复，举个例子：
1 2 3 4 5 6 7 可以大致分为两类，
第一类 2 3 4 5 6 7 1 这种，也就是 nums[start] <= nums[mid]。此例子中就是 2 <= 5。
这种情况下，前半部分有序。因此如果 nums[start] <=target<nums[mid]，则在前半部分找，否则去后半部分找。
第二类 6 7 1 2 3 4 5 这种，也就是 nums[start] > nums[mid]。此例子中就是 6 > 2。
这种情况下，后半部分有序。因此如果 nums[mid] <target<=nums[end]，则在后半部分找，否则去前半部分找。*/

class Solution {
  public boolean isPerfectSquare(int num) {
    if (num < 2) {
      return true;
    }
    long left = 2, right = num / 2, x, guessSquared;
    while (left <= right) {
      x = left + (right - left) / 2;
      guessSquared = x * x;
      if (guessSquared == num) {
        return true;
      }
      if (guessSquared > num) {
        right = x - 1;
      } else {
        left = x + 1;
      }
    }
    return false;
  }
}

//74. Search a 2D Matrix
class Solution {
  public boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length;
    if (m == 0) return false;
    int n = matrix[0].length;
    // 二分查找
    int left = 0, right = m * n - 1;
    int pivotIdx, pivotElement;
    while (left <= right) {
      pivotIdx = (left + right) / 2;
      pivotElement = matrix[pivotIdx / n][pivotIdx % n];
      if (target == pivotElement) return true;
      else {
        if (target < pivotElement) right = pivotIdx - 1;
        else left = pivotIdx + 1;
      }
    }
    return false;
  }
}
//153 Find Minimum in Rotated Sorted Array 
class Solution {
  public int findMin(int[] nums) {
    // If the list has just one element then return that element.
    if (nums.length == 1) {
      return nums[0];
    }
    // initializing left and right pointers.
    int left = 0, right = nums.length - 1;
    // if the last element is greater than the first element then there is no rotation.
    // e.g. 1 < 2 < 3 < 4 < 5 < 7. Already sorted array.
    // Hence the smallest element is first element. A[0]
    if (nums[right] > nums[0]) {
      return nums[0];
    } 
    // Binary search way
    while (left <= right) {
      // Find the mid element
      int mid = left + (right - left) / 2;
      // if the mid element is greater than its next element then mid+1 element is the smallest
      // This point would be the point of change. From higher to lower value.
      if (nums[mid] > nums[mid + 1]) {
        return nums[mid + 1];
      }
      // if the mid element is lesser than its previous element then mid element is the smallest
      if (nums[mid - 1] > nums[mid]) {
        return nums[mid];
      }
      // if the mid elements value is greater than the 0th element this means
      // the least value is still somewhere to the right as we are still dealing with elements
      // greater than nums[0]
      if (nums[mid] > nums[0]) {
        left = mid + 1;
      } else {
        // if nums[0] is greater than the mid value then this means the smallest value is somewhere to
        // the left
        right = mid - 1;
      }
    }
    return -1;
  }
}


```
