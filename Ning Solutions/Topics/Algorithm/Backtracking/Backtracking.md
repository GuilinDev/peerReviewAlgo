```java

// 46. Permutations 
// permutation(nums[0--n-1]) = 拿出一个数字 +  permutation（(nums[0--n-1]) - 拿出的数字）。
//Input: [1,2,3] Output:[[1,2,3],[1,3,2],[2,1,3],[2,3,1], [3,1,2],[3,2,1]]
class Solution {
    public  List<List<Integer>> permutationTest(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backTracking(res,temp,nums, used);
        return res;
    }

    public  void backTracking(List<List<Integer>> res, ArrayList<Integer> temp, int[] nums, boolean[] used){
        if (temp.size() == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i ++) {
            if (used[i]) continue; //【used[i]】更快速的查找到. 也可以temp.contains(nums[i])
            temp.add(nums[i]);
            used[i] = true;
            backTracking(res, temp,nums,used); // 每次要重新用到之前分支的数字,不要用到level
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }
}


// 47. Permutations II

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length ==0) return res;
        ArrayList<Integer> tempList = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backTracking(nums,res,tempList,used);
        return res; // don't forget return
    }
     
    public void backTracking(int[] nums,List<List<Integer>> res,ArrayList<Integer> tempList,
                            boolean[] used) {

        if (tempList.size() == nums.length) {
            res.add(new ArrayList<>(tempList));
           }
        for (int i = 0; i < nums.length; i ++) {
            if (used[i]) continue;
            if (i > 0 && nums[i - 1] == nums[i] && used[i - 1]) continue; //
            used[i] = true;
            tempList.add(nums[i]);
            backTracking(nums,res,tempList,used);
            used[i] = false; // need to be false 
            tempList.remove(tempList.size() - 1);           
        }     
    }
}

// 77. Combinations -- 有几个孩子是不固定的树形结构 但是每个分支有递减关系
//Input: n = 4, k = 2 Output:[ [2,4],  [3,4],  [2,3],  [1,2],  [1,3],  [1,4],]
class Solution {  // muke 递归图 
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n == 0 || k == 0 || k > n) return res;
        List<Integer> temp = new ArrayList<>();
        backTracking(k,n,1,res,temp);
        return res;
    }
    
    public void backTracking(int k, int n, int start, List<List<Integer>> res, List<Integer> temp) {
        if (temp.size() == k) {
            res.add(new ArrayList<>(temp));
        }
        for (int i = start; i <= n; i ++){
            temp.add(i);
            backTracking(k, n, i + 1, res, temp);
            temp.remove(temp.size() - 1);
        }
    }
}  
//78. Subsets
// Input: nums = [1,2,3] Output:[  [3],  [1],  [2],  [1,2,3],  [1,3], [2,3], [1,2],  []]

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new LinkedList<>();
        if (nums == null || nums.length == 0) return res;
        backTracking(res,nums,temp,0);
        return res;
    }
    
    public void backTracking(List<List<Integer>> res, int[] nums, List<Integer> temp, int level) {
        if (temp.size() <= nums.length) {
            res.add(new ArrayList<>(temp));
        }
        for (int i = level; i < nums.length; i ++) {
            temp.add(nums[i]);
            backTracking(res,nums,temp,i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}


// 90. Subsets II
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums); // have duplicate
        List<Integer> temp = new ArrayList<>();
        backTracking(res,nums,temp,0);
        return res;     
    }
    public void backTracking(List<List<Integer>> res, int[] nums, List<Integer> temp, int start) {
        res.add(new ArrayList(temp));
        for (int i = start; i < nums.length; i ++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // i > start. not i > 0; 
            temp.add(nums[i]);
            backTracking(res, nums, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}

// 39. Combination Sum
//Input: candidates = [2,3,6,7], target = 7 Output: [[2,2,3],[7]]
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length == 0) return res;        
        List<Integer> temp = new ArrayList<>();
        backTracking(res,temp, candidates, target, 0);
        return res;
    }
    public void backTracking(List<List<Integer>> res, List<Integer> temp, int[] candidates, int target, int level) {
        if (target < 0) {return;}
        if(target == 0){
            res.add(new ArrayList<>(temp));
            return; //要返回
        }
        for (int i = level; i < candidates.length;  i ++){ // 用LEVEL是因为 避免出现相同子集（不同顺序 但是内容一样）
                  temp.add(candidates[i]);
                  backTracking(res,temp,candidates, target - candidates[i], i); // 注意必须是I  因为元素可以再利用
                  temp.remove(temp.size() - 1); 
            }   
        
    }
}

// 40 Combination Sum II
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates); // duplicate problem need sort
        List<Integer> temp = new ArrayList<>();
        backTracking(temp, candidates, res, target, 0);
        return res;  
    }
    
    public void backTracking(List<Integer> temp, int[] candidates, List<List<Integer>> res, int target, int start) {
        if (target < 0) return;
        else if (target == 0) {
        res.add(new ArrayList<>(temp)); 
        return;
        }
        for (int i = start; i < candidates.length; i ++) {
                if (i > start && candidates[i] == candidates[i - 1]) continue; // need i > start
                temp.add(candidates[i]);
                backTracking(temp, candidates, res, target - candidates[i], i + 1); // need i + 1 元素虽然有重复 但是一个元素只能用一次
                temp.remove(temp.size() - 1);
            }
    }
}

//根据排列问题和组合问题画出的树来看，排列问题的树比较对称，而组合和子集问题的树越靠右节点越少。
//在代码中的体现就是，排列问题每次通过 contains 方法来排除在 track 中已经选择过的数字；
//而组合和子集问题通过传入一个 start 参数，来排除 start 索引之前的数字。






```
