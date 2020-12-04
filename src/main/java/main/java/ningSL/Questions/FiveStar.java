package main.java.ningSL.Questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;


    public class FiveStar {
        public int fiveStarReviews(List<List<Integer>> productRatings, int ratingsThreshold){
            int num = 0;
            PriorityQueue<List<Integer>> pq = new PriorityQueue<>((l1, l2) -> diff(l2) - diff(l1)); // max-heap.
            for(List<Integer> rating : productRatings) pq.offer(rating); // initialize PriorityQueue.
            double curRating = 0;
            for(List<Integer> rating : productRatings)
                curRating += 100.0 * rating.get(0) / rating.get(1); // initialize curRating.
            while(curRating < ratingsThreshold * productRatings.size()) {
                num++;
                List<Integer> rating = pq.poll();
                List<Integer> newRating = Arrays.asList(rating.get(0)+1, rating.get(1)+1);
                curRating = curRating - 100.0 * rating.get(0) / rating.get(1) + 100.0 * newRating.get(0) / newRating.get(1); // keep updating the rating.
                pq.offer(newRating);
            }
            return num;
        }

        // the diff between the current product rating and the product added one more five-star rating.
        private int diff(List<Integer> p) {
            double currRating = 100.0 * p.get(0) / p.get(1);
            double newRating = 100.0 * (p.get(0)+1) / (p.get(1)+1);
            return (int)(newRating - currRating);
        }

        public static void main(String[] args) {
            List<List<Integer>> ratings = new ArrayList(){
                {
                    add(Arrays.asList(4,4));
                    add(Arrays.asList(1,2));
                    add(Arrays.asList(3,6));
                }
            };
            int threshold = 77;
            FiveStar s = new FiveStar();
            System.out.println(s.fiveStarReviews(ratings, threshold));
        }
    }



