package Pratice;

import java.util.*;

public class MachQueryRecommendation {

    class Pair {
        int maxCount;
        List<String> words;
        public Pair() {
            maxCount = 0;
            words = new ArrayList<>();
        }
    }
    Map<String, Set<String>> users;
    Map<String, Map<String, Integer>> wordRelevance;
    Map<String, Pair> ans;

    public MachQueryRecommendation() {
        users = new HashMap<>();
        wordRelevance = new HashMap<>();
        ans = new HashMap<>();
    }

    public String search(String user, String word) {
        if (!users.containsKey(user))
            users.put(user, new HashSet<>());
        if (!wordRelevance.containsKey(word))
            wordRelevance.put(word, new HashMap<>());
        if (!ans.containsKey(word))
            ans.put(word, new Pair());

        Pair max = ans.get(word);

        // update maps
        for (String each : users.get(user)) {
            // update word -> each
            if (!wordRelevance.get(word).containsKey(each))
                wordRelevance.get(word).put(each, 1);
            else
                wordRelevance.get(word).put(each, wordRelevance.get(word).get(each) + 1);

            if (ans.get(word).maxCount == wordRelevance.get(word).get(each))
                ans.get(word).words.add(each);
            else if (ans.get(word).maxCount < wordRelevance.get(word).get(each)) {
                ans.put(word, new Pair());
                ans.get(word).maxCount = wordRelevance.get(word).get(each);
                ans.get(word).words.add(each);
            }

            // update each -> word
            if (!wordRelevance.get(each).containsKey(word))
                wordRelevance.get(each).put(word, 1);
            else
                wordRelevance.get(each).put(word, wordRelevance.get(each).get(word) + 1);

            if (ans.get(each).maxCount == wordRelevance.get(each).get(word))
                ans.get(each).words.add(word);
            else if (ans.get(each).maxCount < wordRelevance.get(each).get(word)) {
                ans.put(each, new Pair());
                ans.get(each).maxCount = wordRelevance.get(each).get(word);
                ans.get(each).words.add(word);
            }
        }
        users.get(user).add(word);  // do not forget this

        StringBuilder sb = new StringBuilder();
        sb.append(max.maxCount);
        for (String each : max.words) {
            sb.append(" " + each);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
       MachQueryRecommendation qr = new MachQueryRecommendation();
        Scanner sc = new Scanner(System.in);
        int numLines = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numLines; i++) {
            String[] parts = sc.nextLine().split(" ");
            System.out.println(qr.search(parts[0], parts[1]));
        }
    }
}

