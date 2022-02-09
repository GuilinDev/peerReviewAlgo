package Pratice;

import java.util.BitSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// A* is an informed search algorithm, or a best-first search,
// At each iteration of its main loop, A* needs to determine which of its paths to extend. It does so based on the
// cost of the path and an estimate of the cost required to extend the path all the way to the goal.
// Specifically, A* selects the path that minimizes
//
//{\displaystyle f(n)=g(n)+h(n)}f(n)=g(n)+h(n)
//where n is the next node on the path, g(n) is the cost of the path from the start node to n, and h(n)
// is a heuristic function that estimates the cost of the cheapest path from n to the goal.
// A* terminates when the path it chooses to extend is a path from start to goal or if there are no paths
// eligible to be extended. The heuristic function is problem-specific. If the heuristic function is admissible,
// meaning that it never overestimates the actual cost to get to the goal,
// A* is guaranteed to return a least-cost path from start to goal.
public class Knight {
    int[] dx = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
    int[] dy = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    HashSet<String> visited = new HashSet<>();
    public int minKnightMoves(int x, int y) {
        // no need to work with negatives, sames # steps in any quadrant
        int k = Math.abs(x);
        int s = Math.abs(y);
        // special case dips negative, return early
        if (k == 1 && s == 1) return 2;


        Queue<int[]> queue = new LinkedList();
        queue.add(new int[]{0, 0});
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i < size; i++) {
                int[] pos = queue.remove();
                int pX = pos[0];
                int pY = pos[1];
                String pr = pX + "," + pY;
                if (pX == x && pY == y) return steps;
                if (visited.contains(pr)) continue;
                // need put in the outside of logic
                visited.add(pr);
                for (int d = 0; d < 8; d ++) {
                    queue.add(new int[]{pX+ dx[d], pY + dy[d]});
                }
            }
            steps++;
        }
        return steps;
    }

    public static void main(String[] args) {
      //  270, -21
        Knight kg = new Knight();
        System.out.println(kg.minKnightMoves(270,-21));
        String a = "abc";
        String b = "abc";
        System.out.println(a.hashCode() +",,,"+b.hashCode());
        BitSet bs = new BitSet();
        int k = -100;
        int s = -20;
        System.out.println(k +"," + s);

        long guid = Long.MAX_VALUE - 2;
        int l =   Long.hashCode(guid);
        System.out.println(l);

    }
}
