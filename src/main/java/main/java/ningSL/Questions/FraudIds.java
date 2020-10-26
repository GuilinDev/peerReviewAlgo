package main.java.ningSL.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FraudIds {
    public static void main(String[] args) {
        String[] input = new String[] { "345366 89921 45", "029323 38239 23", "38239 345366 15", "029323 38239 77",
                "345366 38239 23", "029323 345366 13", "38239 38239 23" };
        System.out.println(getFraudIds(input, 3));
    }
    public static List<String> getFraudIds(String[] input, int threshold) {
        List<String> res = new ArrayList<>();
        HashMap<String,Integer> map = new HashMap<>();
        for (String line : input){
            String[] parts = line.split(" ");
            if(parts[0].equals(parts[1])){
                String key = parts[0];
                map.put(key, map.getOrDefault(key, 0) + 1);
            } else {
                String key1 = parts[0];
                String key2 = parts[1];
                map.put(key1, map.getOrDefault(key1, 0) + 1);
                map.put(key2, map.getOrDefault(key2, 0) + 1);
            }
        }
        for (String key: map.keySet()){
            if(map.get(key) >= threshold){
                res.add(String.valueOf(key));
            }
        }
      //  Collections.sort(res, (o1,o2)-> Integer.valueOf(o1) - Integer.valueOf(o2));
        Collections.sort(res);
        return res;

    }
}
