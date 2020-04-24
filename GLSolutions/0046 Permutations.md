## Solution 1
> 熟悉回溯法的递归路径，这道题没有重复元素
```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        //Arrays.sort(nums);//因为没有重复元素，所以这道题不用排序
        backtracking(results, new ArrayList<Integer>(), nums);
        return results;
    }
    private void backtracking(List<List<Integer>> results, List<Integer> oneResult, int[] nums) {
        if (oneResult.size() == nums.length) {
            results.add(new ArrayList<>(oneResult));
        }
        for (int num : nums) {
            if (!oneResult.contains(num)) { //数字没有被用过
                oneResult.add(num);
                backtracking(results, oneResult, nums); // 继续下一层的递归
                oneResult.remove(oneResult.size() - 1); //结束当前元素num的所有子树的查找，并返回递归树的上一层
            }
        }
    }
}
```