package main.java.ningSL;

import java.util.HashMap;
import java.util.Map;

public class Find2 {
    public static void main(String[] args) {
        int[] nums = new int[]{100,4,200,1,3,2};
        Find2 f = new Find2();
        System.out.println(f.longestConsecutive(nums));
    }

    public int longestConsecutive(int[] nums) {
        UF uf = new UF(nums.length);
        Map<Integer,Integer> map = new HashMap<Integer,Integer>(); // <value,index>
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                continue;
            }
            map.put(nums[i],i);
            if(map.containsKey(nums[i]+1)){
                uf.union(i,map.get(nums[i]+1));
            }
            if(map.containsKey(nums[i]-1)){
                uf.union(i,map.get(nums[i]-1));
            }
        }
        return uf.maxUnion();
    }
}

class UF{
    private int[] list;
    public UF(int n){
        list = new int[n];
        for(int i=0; i<n; i++){
            list[i] = i;
        }
    }

    private int root(int i, int[] list){
        if (i == list[i]) return i;
        list[i] = root(list[i], list);
        return list[i];
    }

    public void union(int p, int q){
        int i = root(p,list);
        int j = root(q,list);
        if(i != j){
            list[i] = j;
        }
    }

    // returns the maxium size of union
    public int maxUnion(){ // O(n)
        int[] count = new int[list.length];
        int max = 0;
        for(int i=0; i<list.length; i++){
            count[root(i,list)] ++;
            max = Math.max(max, count[list[i]]);
        }
        return max;
    }
}

