package Pratice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Edge{
    Node2 node; //表示这个edge的尾巴指向哪里。
    int cost;
    public Edge(Node2 n, int cost) {
        this.node = n;
        this.cost = cost;
    }
}
// like 129. Sum Root to Leaf Numbers

class Node2 {
    List<Edge> edges; //表示从这个头出发的所有edge
    public Node2(){
        this.edges = new ArrayList<>();
    }
}

public class Root_to_Leaf_Min_Cost{
    int minCost = Integer.MAX_VALUE;
    //返回最短路径上面的所有Edge
    public List<Edge> getMinPath(Node2 root){
        List<Edge> res = new ArrayList<>();
        List<Edge> temp = new ArrayList<>();
        dfs(res, temp, root, 0);
        return res;
    }
    //就是普通的DFS
    public void dfs(List<Edge> res, List<Edge> temp, Node2 root, int curCost){
        if (root == null){
            return;
        }
        if (root.edges.size() == 0){
            if (curCost < minCost){
                minCost = curCost;
                res.clear();
                res.addAll(temp);
                return;
            }
        }
        for (Edge e : root.edges){
            Node2 next = e.node;
            temp.add(e);
            dfs(res, temp, next, curCost+e.cost);
            temp.remove(temp.size()-1);
        }
    }
    //这个只返回个最小cost
    public int getMinCost(Node2 root){
        if (root == null) {
            return 0;
        }
        helper(root, 0);
        return minCost;
    }
    public void helper(Node2 root, int curCost){
        if (root.edges.size() == 0){
            minCost = Math.min(minCost, curCost);
            return;
        }
        for (Edge e : root.edges){
            Node2 next = e.node;
            helper(next, curCost + e.cost);
        }
    }
    public static void main(String[] args) {
        Root_to_Leaf_Min_Cost test = new Root_to_Leaf_Min_Cost();
        /*
         *       n1
         *   e1 /  \ e3
         *     n2   n3
         * e2 /
         *   n4
         *
         * */
        Node2 n1 = new Node2();
        Node2 n2 = new Node2();
        Node2 n3 = new Node2();
        Node2 n4 = new Node2();
        Edge e1 = new Edge(n2,1);
        Edge e2 = new Edge(n4,2);
        Edge e3 = new Edge(n3,5);
        n1.edges.add(e1);
        n1.edges.add(e3);
        n2.edges.add(e2);
        int res = test.getMinCost(n1);
        System.out.println(res);
        System.out.println(test.getMinPathInGraph(n1).size());
       // System.out.println("3 = "+res);
    }

    int miniCost = Integer.MAX_VALUE;
    Map<Node2, Integer> dist = new HashMap<>();
    public List<Edge> getMinPathInGraph(Node2 root){
        List<Edge> res = new ArrayList<>();
        List<Edge> temp = new ArrayList<>();
        dfsInGraph(res, temp, root, 0);
        return res;
    }
    public void dfsInGraph(List<Edge> res, List<Edge> temp, Node2 node, int curCost){
        if (node == null) return;
        if (dist.containsKey(node)) return;
        dist.put(node, curCost);
        if (node.edges.size() == 0){
            if (curCost < miniCost){
                miniCost = curCost;
                res.clear();
                res.addAll(temp);
            }
            return;
        }

        for (Edge e : node.edges){
            Node2 next = e.node;
            temp.add(e);
            dfsInGraph(res, temp, next, curCost + e.cost);
            temp.remove(temp.size()-1);
        }
    }
}