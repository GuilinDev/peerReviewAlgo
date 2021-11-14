# Wrap Lines
### PT.1 Connecting words with '-' as blank spaces, no exceeds maxLength

Input: String[] words, int maxLength.

Output: List lines.

```text
    ["1p3acres", "is", "a", "good", "place", "to", "communicate"], 12 => {"1p3acres-is", "a-good-place", "for", "communicate"}
```
O(n) time, O(n) space
```java
public class Test {
    public static List<String> wrapLines1(String[] words, int maxLength) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int p = 0;
        while (p < words.length) {
            if (sb.length() == 0)
                // assume all words length no exceed to maxLength
                sb.append(words[p++]);

            else if (sb.length() + 1 + words[p].length() <= maxLength) {
                sb.append('-');
                sb.append(words[p++]);
            } else {
                ans.add(sb.toString());
                sb.setLength(0);
            }
        }
        if (sb.length() != 0) ans.add(sb.toString());
        return ans;
    }

    public static void main(String[] args) {
        String[] words = {"1p3acres", "is", "a", "good", "place", "to", "communicate"};
        System.out.println(wrapLines1(words, 12));
    }
}

```
### PT.2 Require every line to be "balanced".

Input: String[] lines, ["the way it moves like me", "another sentence example",...], int maxLength.

Output: List lines.
```text
e.g. ["123 45 67 8901234 5678", "12345 8 9 0 1 23"], 10 => {"123--45-67", "8901234", "5678-12345", "8-9-0-1-23"}
["123 45 67 8901234 5678", "12345 8 9 0 1 23"], 15 => {"123----45----67", "8901234----5678", "12345---8--9--0", "23"}
```

O(n^2) worst time, O(n) space
```java
public class Test {
    public static List<String> wrapLines2(String[] lines, int maxLength){
        List<String> unbalanced = new ArrayList<>();
        List<String> words = new ArrayList<>();
        for(String line : lines){
            String[] word_collection = line.split(" ", -1);
            Collections.addAll(words, word_collection);
        }
        StringBuilder sb = new StringBuilder();
        int p = 0;
        while(p < words.size()){
            if(sb.length() == 0)
                // assume all words length no exceed to maxLength
                sb.append(words.get(p++));

            else if(sb.length() + 1 + words.get(p).length() <= maxLength){
                sb.append('-');
                sb.append(words.get(p++));
            }
            else{
                unbalanced.add(sb.toString());
                sb.setLength(0);
            }
        }
        if(sb.length() != 0) unbalanced.add(sb.toString());
        //now we have un-balanced result, then balance it
        List<String> balanced = new ArrayList<>();
        for(String line : unbalanced){
            StringBuilder cur_line = new StringBuilder(line);
            int num_needed = maxLength - cur_line.length();
            if(!cur_line.toString().contains("-")){
                balanced.add(cur_line.toString());
                continue;
            };
            while(num_needed > 0){
                int i = 0;
                while(i < cur_line.length() - 1){
                    if(cur_line.charAt(i) == '-' && cur_line.charAt(i + 1) != '-'){
                        cur_line.insert(i + 1, '-');
                        num_needed--;
                        i++;
                        if(num_needed == 0) break;
                    }
                    i++;
                }
            }
            balanced.add(cur_line.toString());
        }
        return balanced;
    }


    public static void main(String[] args) {
        String[] words = {"123 45 67 8901234 5678", "12345 8 9 0 1 23"};
        System.out.println(wrapLines2(words, 10));
    }
}
```

### PT.3 Assuming only one "-" between words, but define "score": sum of difference square between each line length and the length of longest line (like variance), how to wrap can minimize the score. How long for each line is not limited. (dp)

# Friend Cycle

例子
```text
Sample Input:

employees = [
  "1, Bill, Engineer",
  "2, Joe, HR",
  "3, Sally, Engineer",
  "4, Richard, Business",
  "6, Tom, Engineer"
]

friendships = [
  "1, 2",
  "1, 3",
  "3, 4"
]

```
### PT.1 Given employees and friendships, find all adjacencies that denote the friendship, A friendship is bi-directional/mutual so if 1 is friends with 2, 2 is also friends with 1.

```text
Sample Output:
Output:
1: 2, 3
2: 1
3: 1, 4
4: 3
6: None

```
O(n) time? (worst case n^2 relationship between n employees, takes n^2), O(n) space
```java
public class Test {
    public static List<String> friendCycle1(String[] employees, String[] friendships){
        // assume each pair in friendship only contains two elements
        List<String> ans = new ArrayList<>();
        Map<String,List<String>> friend_list = new HashMap<>();
        for(int i = 0; i < employees.length; i++){
            String[] split_res = employees[i].split(",");

            friend_list.put(split_res[0], new ArrayList<String>());
        }
        for(String pair : friendships){
            //chris is friend with martin, martin is friend with chris
            String[] sep = pair.split(",");
            String chris = sep[0];
            String[] meaningless_split = sep[1].split(" ");
            String martin = meaningless_split[1];
            friend_list.get(chris).add(martin);
            friend_list.get(martin).add(chris);
        }
        // iterate friend list, if list is empty, too bad you get no friends ;(
        for(String everyone : friend_list.keySet()){
            StringBuilder ones_friends = new StringBuilder();
            ones_friends.append(everyone);
            ones_friends.append(": ");
            if(friend_list.get(everyone).size() != 0)
                ones_friends.append(friend_list.get(everyone));
            else ones_friends.append("None");
            ans.add(ones_friends.toString());
        }
        return ans;
    }



    public static void main(String[] args) {
        String[] employees  = {
                "1, Bill, Engineer",
                "2, Joe, HR",
                "3, Sally, Engineer",
                "4, Richard, Business",
                "6, Tom, Engineer"
        };
        String[] friendships  = {
                "1, 2",
                "1, 3",
                "3, 4"
        };
        System.out.println(friendCycle1(employees, friendships));
    }
}
```
### PT.2 Now for each department count the number of employees that have a friend in another department
```text
Sample Output:
Output:
"Engineer: 2 of 3"
"HR: 1 of 1"
"Business: 1 of 1"
```

O(n^2) time complexity, O(n) space complexity.
```java
public class Test {
    public static List<String> friendCycle2(String[] employees, String[] friendships){
        List<String> ans = new ArrayList<>();
        Map<String,List<String>> friend_list = new HashMap<>();
        for(int i = 0; i < employees.length; i++){
            String[] split_res = employees[i].split(",");

            friend_list.put(split_res[0], new ArrayList<String>());
        }
        for(String pair : friendships){
            //chris is friend with martin, martin is friend with chris
            String[] sep = pair.split(",");
            String chris = sep[0];
            String[] meaningless_split = sep[1].split(" ");
            String martin = meaningless_split[1];
            friend_list.get(chris).add(martin);
            friend_list.get(martin).add(chris);
        }
        // now we have each employee -> friends (list of strings) mapping
        Map<String,String> employee_department = new HashMap<>();
        Map<String,Integer> departments_num = new HashMap<>();
        for(String employee : employees){
            String[] split_res = employee.split(",");
            String depart = split_res[2];
            String cur_depart = depart.substring(1, depart.length());
            employee_department.put(split_res[0], cur_depart);
            departments_num.put(cur_depart, departments_num.getOrDefault(cur_depart, 0) + 1);

        }
        // friend_list : employees -> all friends
        // employee_department : employees -> own department
        // departments_num : department -> nums of employees

        //iterate all departments, store department name and total number of employees in a list of String
        Map<String, List<Integer>> info = new HashMap<>();// department name -> [total num, out of dep employees num]
        for(String dep : departments_num.keySet()){
            List<Integer> val = new ArrayList<>();
            val.add(departments_num.get(dep));
            info.put(dep, val);
        }
        for(String individual : friend_list.keySet()){
            String own_dep = employee_department.get(individual);
            for(String friend : friend_list.get(individual)){
                String fri_dep = employee_department.get(friend);
                if(fri_dep != own_dep){
                    int update = departments_num.get(own_dep) - 1;
                    departments_num.put(own_dep, update);
                    break;
                }
            }
        }
        for(String dep : info.keySet()){
            int hasOther = info.get(dep).get(0) - departments_num.get(dep);
            info.get(dep).add(hasOther);
        }
        for(String dep : info.keySet()){
            StringBuilder sb = new StringBuilder();
            sb.append(dep);
            sb.append(": ");
            sb.append(info.get(dep).get(1));
            sb.append(" of ");
            sb.append(info.get(dep).get(0));
            ans.add(sb.toString());
        }


        return ans;
    }

    public static void main(String[] args) {
        String[] employees  = {
                "1, Bill, Engineer",
                "2, Joe, HR",
                "3, Sally, Engineer",
                "4, Richard, Business",
                "6, Tom, Engineer"
        };
        String[] friendships  = {
                "1, 2",
                "1, 3",
                "3, 4"
        };
        System.out.println(friendCycle2(employees, friendships));
    }
}

```
### PT.3 Output if all the employees are in a same friend cycle.
```text
Interviewer modified friendship so that 6 has a friend.
friendships2 = [
  "1, 2",
  "1, 3",
  "3, 4",
  "6, 1"
]

```

# Common Ancestor / Parent Children
### Pt. 1 Suppose we have some input data describing a graph of relationships between parents and children over multiple generations. The data is formatted as a list of (parent, child) pairs, where each individual is assigned a unique integer identifier.

For example, in this diagram, 3 is a child of 1 and 2, and 5 is a child of 4

```text
parentChildPairs = [  (1, 3), (2, 3), (3, 6), (5, 6),
                   (5, 7), (4, 5), (4, 8), (8, 10)  ] 

```

Write a function that takes this data as input and returns two collections: one containing all individuals with zero known parents, and one containing all individuals with exactly one known parent.

sample output:
```text
findNodesWithZeroAndOneParents(parentChildPairs) =>
                                  [ [1, 2, 4],    // Individuals with zero parents
                                  [5, 7, 8, 10] // Individuals with exactly one parent ]

```

O(n) time, O(n) space
```java
public class Test {
    public static List<List<Integer>> commonAncestor1(int[][] pairs){
        // assume non-empty input 2-d array, and each pair contains 2 elements with parent-child order
        List<Integer> zero_ancestor = new ArrayList<>(), one_ancestor = new ArrayList<>();
        Map<Integer,Integer> numOfAncestors = new HashMap<>();
        for(int[] pair : pairs){
            numOfAncestors.put(pair[1], numOfAncestors.getOrDefault(pair[1], 0) + 1);
            numOfAncestors.put(pair[0], numOfAncestors.getOrDefault(pair[0], 0));
        }
        for(int node : numOfAncestors.keySet()){
            if(numOfAncestors.get(node) == 0) zero_ancestor.add(node);
            if(numOfAncestors.get(node) == 1) one_ancestor.add(node);
        }
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(zero_ancestor);
        ans.add(one_ancestor);
        return ans;
    }

    public static void main(String[] args) {
        int[][] pairs = {{1, 2, 4}, {5, 7, 8, 10}};
        System.out.println(commonAncestor1(pairs));
    }
}
```

### Pt.2 Write a function that takes the graph, as well as two of the individuals in our dataset, as its inputs and returns true if and only if they share at least one ancestor.
Sample input and output: （input as same as last part）
```text
hasCommonAncestor(parentChildPairs, 3, 8) => false
hasCommonAncestor(parentChildPairs, 5, 8) => true
hasCommonAncestor(parentChildPairs, 6, 8) => true
hasCommonAncestor(parentChildPairs, 1, 3) => false

```

O(n) time, O(n) space
```java
public class Test {
    public static boolean commonAncestor2(int[][] pairs, int node1, int node2){
        Set<Integer> p1 = new HashSet<>(), p2 = new HashSet<>();
        help_commonAncestor2(p1, node1, pairs);
        help_commonAncestor2(p2, node2, pairs);
        for(int parent : p1){
            if(p2.contains(parent)) return true;
        }
        return false;
    }
    public static void help_commonAncestor2(Set<Integer> parents, int node, int[][] pairs){
        for(int[] pair : pairs){
            if(pair[1] == node){
                parents.add(pair[0]);
                help_commonAncestor2(parents, pair[0], pairs);
            }
        }
    }


    public static void main(String[] args) {
        int[][] pairs = {{1, 2, 4}, {5, 7, 8, 10}};
        System.out.println(commonAncestor2(pairs, 1, 7));
    }
}
```

## Pt.3 For example, in this diagram, 3 is a child of 1 and 2, and 5 is a child of 4

Write a function that, for a given individual in our dataset, returns their earliest known ancestor -- the one at the farthest distance from the input individual. . check 1point3acres for more.

If there is more than one ancestor tied for "earliest", return any one of them. If the input individual has no parents, the function should return null (or -1). 

Sample input and output:
```text
parent_child_pairs = [ (1, 3), (2, 3), (3, 6), (5, 6), (5, 7), (4, 5), (4, 8), (8, 10), (11, 2) ]

findEarliestAncestor(parentChildPairs, 8) => 4
findEarliestAncestor(parentChildPairs, 7) => 4
findEarliestAncestor(parentChildPairs, 6) => 11
findEarliestAncestor(parentChildPairs, 1) => null or -1 

```

# Calculator
### Pt.1 Calculator without parenthesis, only +, -, non-negative ints

直接算，O(n) time, O(1) space
```java
public class Test {
    public static int basicCalculator1(String expression) {
        int num = 0, sum = 0, sign = 1; // 1 for +, -1 for -
        char[] chars = expression.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char cur = chars[i];
            if (Character.isDigit(cur)) num = num * 10 + Character.getNumericValue(cur);
            else if (cur == '+' || cur == '-') {
                sum += sign * num;
                num = 0;
                sign = (cur == '+') ? 1 : -1;
            }
        }
        if (num != 0) sum += sign * num;
        return sum;
    }
    
    public static void main(String[] args) {
        System.out.println(basicCalculator1("1+3-5"));
    }
}
```

### Pt.2 Calculator with parenthesis (LeetCode 224)

Stack, O(n) time, O(n) worst case space
```java
public class Test {
    public static int basicCalculator2(String expression) {
        char[] chars = expression.toCharArray();
        int num = 0, sum = 0, sign = 1;
        Stack<Integer> former_res = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            char cur = chars[i];
            if (Character.isDigit(cur)) {
                num = num * 10 + Character.getNumericValue(cur);
            } else if (cur == '+' || cur == '-') {
                sum += num * sign;
                num = 0;
                sign = (cur == '+') ? 1 : -1;
            } else if (cur == '(') {
                former_res.push(sum);
                former_res.push(sign);
                sum = 0;
                num = 0;
                sign = 1;
            } else if (cur == ')') {
                sum += num * sign;
                sum = sum * former_res.pop() + former_res.pop();
                num = 0;
                // 3 + (  - 9 )  + 5
                // - 6
            }
        }
        if (num != 0) sum += sign * num;
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(basicCalculator2("3 + (  - 9 )  + 5"));
    }
}
```

### Pt.3 (LeetCode 770 with output type is String)
构建一个 Poly 多项式类，实现这个多项式类的一些数学操作。

算法

单独实现每个方法都很直接，这里先列一下要实现的方法：

Poly:add(this, that) 返回 this + that 的结果。

Poly:sub(this, that) 返回 this - that 的结果。

Poly:mul(this, that) 返回 this * that 的结果。

Poly:evaluate(this, evalmap) 返回将所有的自由变量替换成 evalmap 指定常数之后的结果。

Poly:toList(this) 返回正确的多项式输出格式。

Solution::combine(left, right, symbol) 返回对 左边（left) 和 右边(left) 进行 symobl 操作之后的结果。

Solution::make(expr) 创造一个新的 Poly 实例来表示常数或 expr 指定的变量。

Solution::parse(expr) 将 expr 解析成一个 Poly 实例。

```java
class Solution {
    public List<String> basicCalculatorIV(String expression, String[] evalVars, int[] evalInts) {
        Map<String, Integer> evalMap = new HashMap();
        for (int i = 0; i < evalVars.length; ++i)
            evalMap.put(evalVars[i], evalInts[i]);

        return parse(expression).evaluate(evalMap).toList();
    }

    public Poly make(String expr) {
        Poly ans = new Poly();
        List<String> list = new ArrayList();
        if (Character.isDigit(expr.charAt(0))) {
            ans.update(list, Integer.valueOf(expr));
        } else {
            list.add(expr);
            ans.update(list, 1);
        }
        return ans;
    }

    public Poly combine(Poly left, Poly right, char symbol) {
        if (symbol == '+') return left.add(right);
        if (symbol == '-') return left.sub(right);
        if (symbol == '*') return left.mul(right);
        throw null;
    }

    public Poly parse(String expr) {
        List<Poly> bucket = new ArrayList();
        List<Character> symbols = new ArrayList();
        int i = 0;
        while (i < expr.length()) {
            if (expr.charAt(i) == '(') {
                int bal = 0, j = i;
                for (; j < expr.length(); ++j) {
                    if (expr.charAt(j) == '(') bal++;
                    if (expr.charAt(j) == ')') bal--;
                    if (bal == 0) break;
                }
                bucket.add(parse(expr.substring(i+1, j)));
                i = j;
            } else if (Character.isLetterOrDigit(expr.charAt(i))) {
                int j = i;
                search : {
                    for (; j < expr.length(); ++j)
                        if (expr.charAt(j) == ' ') {
                            bucket.add(make(expr.substring(i, j)));
                            break search;
                        }
                    bucket.add(make(expr.substring(i)));
                }
                i = j;
            } else if (expr.charAt(i) != ' ') {
                symbols.add(expr.charAt(i));
            }
            i++;
        }

        for (int j = symbols.size() - 1; j >= 0; --j)
            if (symbols.get(j) == '*')
                bucket.set(j, combine(bucket.get(j), bucket.remove(j+1), symbols.remove(j)));

        if (bucket.isEmpty()) return new Poly();
        Poly ans = bucket.get(0);
        for (int j = 0; j < symbols.size(); ++j)
            ans = combine(ans, bucket.get(j+1), symbols.get(j));

        return ans;
    }
}

class Poly {
    HashMap<List<String>, Integer> count;
    Poly() {count = new HashMap();}

    void update(List<String> key, int val) {
        this.count.put(key, this.count.getOrDefault(key, 0) + val);
    }

    Poly add(Poly that) {
        Poly ans = new Poly();
        for (List<String> k: this.count.keySet())
            ans.update(k, this.count.get(k));
        for (List<String> k: that.count.keySet())
            ans.update(k, that.count.get(k));
        return ans;
    }

    Poly sub(Poly that) {
        Poly ans = new Poly();
        for (List<String> k: this.count.keySet())
            ans.update(k, this.count.get(k));
        for (List<String> k: that.count.keySet())
            ans.update(k, -that.count.get(k));
        return ans;
    }

    Poly mul(Poly that) {
        Poly ans = new Poly();
        for (List<String> k1: this.count.keySet())
            for (List<String> k2: that.count.keySet()) {
                List<String> kNew = new ArrayList();
                for (String x: k1) kNew.add(x);
                for (String x: k2) kNew.add(x);
                Collections.sort(kNew);
                ans.update(kNew, this.count.get(k1) * that.count.get(k2));
            }
        return ans;
    }

    Poly evaluate(Map<String, Integer> evalMap) {
        Poly ans = new Poly();
        for (List<String> k: this.count.keySet()) {
            int c = this.count.get(k);
            List<String> free = new ArrayList();
            for (String token: k) {
                if (evalMap.containsKey(token))
                    c *= evalMap.get(token);
                else
                    free.add(token);
            }
            ans.update(free, c);
        }
        return ans;
    }

    int compareList(List<String> A, List<String> B) {
        int i = 0;
        for (String x: A) {
            String y = B.get(i++);
            if (x.compareTo(y) != 0) return x.compareTo(y);
        }
        return 0;
    }
    List<String> toList() {
        List<String> ans = new ArrayList();
        List<List<String>> keys = new ArrayList(this.count.keySet());
        Collections.sort(keys, (a, b) ->
            a.size() != b.size() ? b.size() - a.size() : compareList(a, b));

        for (List<String> key: keys) {
            int v = this.count.get(key);
            if (v == 0) continue;
            StringBuilder word = new StringBuilder();
            word.append("" + v);
            for (String token: key) {
                word.append('*');
                word.append(token);
            }
            ans.add(word.toString());
        }
        return ans;
    }
}
```

# Domain Visits

### Pt.1 (LeetCode 811)

O(n) time, O(n) space
```java
public class Test {
    public static List<String> domainVisits1(String[] cpdomains){
        List<String> ans = new ArrayList<>();
        Map<String,Integer> hm = new HashMap<>();
        for(String pair : cpdomains){
            int clicks = 0, i = 0;
            while(i < pair.length()){
                char cur = pair.charAt(i++);
                if(Character.isDigit(cur)){
                    clicks = clicks * 10 + Character.getNumericValue(cur);
                }
                if(cur == ' ') break;
            }
            String cur_domain = pair.substring(i, pair.length());
            hm.put(cur_domain, hm.getOrDefault(cur_domain, 0) + clicks);
            while(i < pair.length()){
                char cur = pair.charAt(i++);
                if(cur == '.'){
                    cur_domain = pair.substring(i, pair.length());
                    hm.put(cur_domain, hm.getOrDefault(cur_domain, 0) + clicks);
                }
            }
        }
        for(String domain : hm.keySet()){
            StringBuilder sb = new StringBuilder();
            sb.append(hm.get(domain) + " " + domain);
            ans.add(sb.toString());
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] domains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        System.out.println(domainVisits1(domains));
    }
}
```

### Pt.2 Longest Continuous Common History: Given visiting history of each user, find the longest continuous common history between two users. (LeetCode 718, dp)

Sample input:
```text
[
 ["3234.html", "xys.html", "7hsaa.html"], // user1
 ["3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"] // user2
], user1 and user2
```

Sample output:
```text
["xys.html", "7hsaa.html"]

```

O(min(N,M) * log(min(N, M)) * (M + N)) time, O(n) space
```java
public class Test {
    public static List<String> domainVisits2(String[] visit1, String[] visit2){
        int lo = 0, hi = Math.min(visit1.length, visit2.length) + 1;
        while(lo < hi){
            int mid = (hi + lo) / 2;
            if(help_domainVisits2(visit1, visit2, mid).size() != 0) lo = mid + 1;
            else hi = mid;
        }
        return help_domainVisits2(visit1,visit2,lo - 1);
    }
    public static List<String> help_domainVisits2(String[] v1, String[] v2, int len){
        List<String> ans = new ArrayList<>();
        Set<String> hs = new HashSet<>();
        for(int i = 0; i + len <= v1.length; i++){
            hs.add(Arrays.toString(Arrays.copyOfRange(v1, i, i + len)));
        }
        for(int j = 0; j + len <= v2.length; j++){
            if(hs.contains(Arrays.toString(Arrays.copyOfRange(v2, j, j + len)))){
                Collections.addAll(ans, Arrays.copyOfRange(v2, j, j+ len));
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        String[] visit1 = {"3234.html", "xys.html", "7hsaa.html"};
        String[] visit2 = {"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"};
        System.out.println(domainVisits2(visit1, visit2));
    }
}
```

### Pt.3 (Map)The people who buy ads on our network don't have enough data about how ads are working for their business. They've asked us to find out which ads produce the most purchases on their website. Our client provided us with a list of user IDs of customers who bought something on a landing page after clicking one of their ads:

```text
Each user completed 1 purchase. completed_purchase_user_ids = [ "3123122444","234111110", "8321125440", "99911063"]
```

And our ops team provided us with some raw log data from our ad server showing every time a user clicked on one of our ads:

```text
ad_clicks = [
#"IP_Address,Time,Ad_Text",
"122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
"96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
"122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
"82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
"92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
"92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens",
]

```

Write a function to parse this data, determine how many times each ad was clicked, then return the ad text, that ad's number of clicks, and how many of those ad clicks were from users who made a purchase.

Expected output:
```text
Bought Clicked Ad Text
1 of 2  2017 Pet Mittens
0 of 1  The Best Hollywood Coats
3 of 3  Buy wool coats for your pets

```

# Course Schedule
### Pt.1 You are a developer for a university. Your current project is to develop a system for students to find courses they share with friends. The university has a system for querying courses students are enrolled in, returned as a list of (ID, course) pairs. Write a function that takes in a list of (student ID number, course name) pairs and returns, for every pair of students, a list of all courses they share.

Sample Input:
```text
student_course_pairs_1 = [
  ["58", "Software Design"],
  ["58", "Linear Algebra"],
  ["94", "Art History"],
  ["94", "Operating Systems"],
  ["17", "Software Design"],
  ["58", "Mechanics"],
  ["58", "Economics"],
  ["17", "Linear Algebra"],
  ["17", "Political Science"],
  ["94", "Economics"],
  ["25", "Economics"],
]

```

Sample Output (pseudocode, in any order):
```text
find_pairs(student_course_pairs_1) =>
{
  [58, 17]: ["Software Design", "Linear Algebra"]-baidu 1point3acres
  [58, 94]: ["Economics"]
  [58, 25]: ["Economics"]
  [94, 25]: ["Economics"]-baidu 1point3acres
  [17, 94]: []
  [17, 25]: []
}
Additional test cases:

Sample Input:

student_course_pairs_2 = [
  ["42", "Software Design"],
  ["0", "Advanced Mechanics"],
  ["9", "Art History"],
]

Sample output:

find_pairs(student_course_pairs_2) =>
{
  [0, 42]: []
  [0, 9]: []
  [9, 42]: []
}

```

Assume n students taking m same courses in the worst case, first I have to iterate m * n courses and write into hashmap, then going over between n^2 pairs, each pair to check m courses, then to wrap the answer same as example given, going over n^2 pairs again. Did not handle the duplicate bug though, like [58,25] also exists when [25, 58] exists. In total, O(m * n + n ^2) time, O(n^2 + m * n) space I assume.

```java
public static List<String> courseSchedule1(String[][] pairs) {
    List<String> ans = new ArrayList<>();
    HashMap<String, HashSet<String>> student_courses = new HashMap<>();
    for (String[] pair : pairs) {
        String id = pair[0], cur_course = pair[1];
        if (student_courses.containsKey(id)) student_courses.get(id).add(cur_course);
        else {
            HashSet<String> course_list = new HashSet<>();
            course_list.add(cur_course);
            student_courses.put(id, course_list);
        }
    }
    for (String s1 : student_courses.keySet()) {
        HashSet<String> course_s1 = student_courses.get(s1);
        for (String s2 : student_courses.keySet()) {
            if (s1 == s2) continue;
            StringBuilder cur_pair = new StringBuilder();
            cur_pair.append("[" + s1 + ", " + s2 + "]: [");
            for (String course : student_courses.get(s2)) {
                if (course_s1.contains(course)) {
                    cur_pair.append(course + " ");
                }
            }
            cur_pair.append("]");
            ans.add(cur_pair.toString());
            ans.add("\n");
        }
    }
    return ans;
}
```

### Pt.2 Given that a is pre-requisite of b, b is pre-requisite of c, what is the mid course? notice that there is only one order of all courses: a->b->c, therefore mid course is b.

### Students may decide to take different "tracks" or sequences of courses in the Computer Science curriculum. There may be more than one track that includes the same course, but each student follows a single linear track from a "root" node to a "leaf" node. In the graph below, their path always moves from left to right.

Write a function that takes a list of (source, destination) pairs, and returns the name of all of the courses that the students could be taking when they are halfway through their track of courses.

Sample input:
```text
all_courses = [
    ["Logic","COBOL"],
    ["Data Structures","Algorithms"],
    ["Creative Writing","Data Structures"],
    ["Algorithms","COBOL"],
    ["Intro to Computer Science","Data Structures"],
    ["Logic","Compilers"],
    ["Data Structures","Logic"],
    ["Creative Writing","System Administration"],
    ["Databases","System Administration"],
    ["Creative Writing","Databases"]
]

```

Sample_ouput(in any order):
```text
["Creative Writing","Databases","Data Structures"]

```

# Badge Access
### Pt.1 We are working on a security system for a badged-access room in our company's building. Given an ordered list of employees who used their badge to enter or exit the room, write a function that returns two collection

1. All employees who didn't use their badge while exiting the room – they recorded an enter without a matching exix

2. All employees who didn't use their badge while entering the room  – they recorded an exit without a matching enter

```text
badge_records = [
  ["Martha",   "exit"],
  ["Paul",     "enter"],. 1point3acres.com/bbs
  ["Martha",   "enter"],
  ["Martha",   "exit"],
  ["Jennifer", "enter"],. more info on 1point3acres.com
  ["Paul",     "enter"],. From 1point 3acres bbs
  ["Curtis",   "enter"],
  ["Paul",     "exit"],
  ["Martha",   "enter"],
  ["Martha",   "exit"],
  ["Jennifer", "exit"],
]
find_mismatched_entries(badge_records)
Expected output: ["Paul", "Curtis"], ["Martha"]

```

O(n) time, O(n) space
```java

```

### Pt.2 We want to find employees who badged into our secured room unusually often. We have an unordered list of names and access times over a single day. Access times are given as three or four-digit numbers using 24-hour time, such as "800" or "2250"

Write a function that finds anyone who badged into the room 3 or more times in a 1-hour period, and returns each time that they badged in during that period. (If there are multiple 1-hour periods where this was true, just return the first one.

```text
badge_records =
     ["Paul", 1355],
     ["Jennifer", 1910]
     ["John", 830]
     ["Paul", 1315]
     ["John", 835]
     ["Paul", 1405]
     ["Paul", 1630]
     ["John", 855],
    
     ["John", 915]
     ["John", 930]
     ["Jennifer", 1335]
     ["Jennifer", 730]
     ["John", 1630]
     ]

Expected output:
John: 830 835 855 915 9
Paul: 1315 1355 1405

```

# Meeting Room / Merge Interval
### Pt.1 Similar to Meeting Rooms(LeetCode 252, *try 253!)
```text
Input: int[][] meetings, int start, int end
(e.g 13:00 => 1300, 9:30 => 930)

Output: boolean, whether the new time could be scheduled as new meetings

Sample:

{[1300,1500],[930,1200],[830,845]},new meeting [820,830], return true, [1450,1500] return false

```

O(n) time, O(1) space.
```java
public static boolean meetingRooms1(int[][] meetings, int start, int end){
    for(int[] meeting : meetings){
        if((meeting[0] <= start && meeting[1] > start) || (meeting[0] < end && meeting[1] >= end)) return false;
    }
    return true;
}

```

### Pt.2 Similar to Merge Intervals(LeetCode 56), but the output is different, now you are required to output idle time after time intervals merged, notice also output 0 - first start time.

O(nlogn) time dominated by .sort, O(n) space. 

```java
public static List<List<Integer>> meetingRooms2(int[][] intervals){
    List<List<Integer>> ans = new ArrayList<>();
    Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
    LinkedList<int[]> cur = new LinkedList<>();
    for(int[] pair : intervals){
        if(cur.isEmpty() || cur.getLast()[1] < pair[0]) cur.add(pair);
        else{
            cur.getLast()[1] = Math.max(cur.getLast()[1], pair[1]);
        }
    }
    List<Integer> Start_interval = new ArrayList<>();
    Start_interval.add(0);
    Start_interval.add(cur.get(0)[0]);
    ans.add(Start_interval);
    for(int i = 0; i < cur.size() - 1; i++){
        List<Integer> cur_interval = new ArrayList<>();
        cur_interval.add(cur.get(i)[1]);
        cur_interval.add(cur.get(i + 1)[0]);
        ans.add(cur_interval);
    }
    return ans;
}

```

# Find Rectangles
````text
Imagine we have an image. We'll represent this image as a simple 2D array where every pixel is a 1 or a 0.

There are N shapes made up of 0s in the image. They are not necessarily rectangles -- they are odd shapes ("islands"). Find them.

image1 = [
  [1, 0, 1, 1, 1, 1, 1],
  [1, 0, 0, 1, 0, 1, 1],
  [0, 1, 1, 0, 0, 0, 1],
  [1, 0, 1, 1, 0, 1, 1],
  [1, 0, 1, 0, 1, 1, 1],
  [1, 0, 0, 0, 0, 1, 1],
  [1, 1, 1, 0, 0, 1, 1],
  [0, 1, 0, 1, 1, 1, 0],
]

Every single pixel in each shape. For reference, these are (in [row,column] format):

findShapes(image1) =>
  [
    [[0,1],[1,1],[1,2]],
    [[1,4],[2,3],[2,4],[2,5],[3,4]],
    [[3,1],[4,1],[4,3],[5,1],[5,2],[5,3],[5,4],[6,3],[6,4]],
    [[7,6]],
  ]


Other test cases:

image2 = [
  [0],
]

findShapes(image2) =>
  [
    [[0,0]],
  ]

image3 = [
  [1],
]

findShapes(image3) => []

n: number of rows in the input image
m: number of columns in the input image

````

### find coodinates
```java
public class Main {

    static class Coordinates{
        public Coordinates(int x, int y){
            this.x = x;
            this.y = y;
        }
        int x;
        int y;
    }
    
    public static void main(String[] args){
        int[][] mat = {
            {1, 0, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1}
        };
    
        for(List<Coordinates> coords:findIndex(mat)){
            System.out.println(coords.get(0).x + "," + coords.get(0).y + " " + coords.get(1).x + "," + coords.get(1).y);
        }
    }
    
    private static List<List<Coordinates>> findIndex(int[][] mat) {
        List<List<Coordinates>> answer = new ArrayList<>();
        List<Coordinates> start = new ArrayList<>();
        List<Coordinates> end = new ArrayList<>();
        if (mat[0][0] == 0){
            start.add(new Coordinates(0,0));
        } 
        
        for (int i = 1; i < mat.length; i++) {
                if (mat[i][0] == 0 && mat[i-1][0] != 0) {  
                    start.add(new Coordinates(i,0));
                }
        }
        
        for (int j = 1; j < mat[0].length; j++) {
                if (mat[0][j] == 0 && mat[0][j-1] != 0) {  
                    start.add(new Coordinates(0,j));
                }
            }
        
        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[0].length; j++) {
                if (mat[i][j] == 0 && mat[i][j-1] != 0 && mat[i-1][j] != 0) {  
                    start.add(new Coordinates(i,j));
                }
            }
        }
        
        if (mat[mat.length-1][mat[0].length-1] == 0){
            end.add(new Coordinates(mat.length-1,mat[0].length-1));
        } 
        for (int i = mat.length-2; i >=0 ; i--) {
                if (mat[i][mat[0].length-1] == 0 && mat[i+1][mat[0].length-1] != 0) {  
                    end.add(new Coordinates(i,mat[0].length-1));
                }
        }
        for (int j = mat[0].length-2; j >= 0; j--) {
                if (mat[mat.length-1][j] == 0 && mat[mat.length-1][j+1] != 0) {  
                    end.add(new Coordinates(mat.length-1,j));
                }
            }
        
        for (int i = mat.length-1; i >=0 ; i--) {
            for (int j = mat[0].length-2; j >= 0; j--) {
                if (mat[i][j] == 0 && mat[i][j+1] != 0 && mat[i+1][j] != 0) {  
                    end.add(new Coordinates(i,j));
                }
            }
        }
        for(int i=0; i< start.size(); i++){
            for(int j=0; j< end.size(); j++){
                if(end.get(j).x >= start.get(i).x && end.get(j).y >= start.get(i).y){
                boolean oneFound = false;
                for(int k=start.get(i).x; k<=end.get(j).x; k++){
                   for(int l=start.get(i).y; l<=end.get(j).y; l++){
                        if(mat[k][l] != 0){
                            oneFound = true;
                        }
                    } 
                }
                if(!oneFound){
                    List<Coordinates> list = new ArrayList<>();
                    list.add(start.get(i));
                    list.add(end.get(j));
                    answer.add(list);
                }
            }
            }
        }
        return answer;
    }
}
```

### find all the rectangles
```java
public class Main {

  public List<List<List<Integer>>> findRectangles(int[][] input) {
    List<List<List<Integer>>> ans = new ArrayList<>();
    if (input == null || input.length == 0 || input[0].length == 0) {
      return ans;
    }

    for (int row = 0; row <= input.length - 1; row++) {
      for (int col = 0; col <= input[0].length - 1; col++) {
        if (input[row][col] == 1) {
          findRectanglesStartingWith(input, row, col, ans);
        }
      }
    }
    return ans;
  }

  public void findRectanglesStartingWith(int[][] input, int row, int col, List<List<List<Integer>>> ans) {
    int maxRow = input.length - 1;
    int maxCol = input[0].length - 1;
    mainLoop:
    for (int i = row; i <= maxRow; i++) {
      for (int j = col; j <= maxCol; j++) {
        if (input[i][j] == 1) {
          //this is a possible end of the rectangle. add it to the ans
           
            List<List<Integer>> subAns = new ArrayList<>();
            
            List<Integer> startRectangle = new ArrayList<>();
            startRectangle.add(row);
            startRectangle.add(col);
            subAns.add(startRectangle);
            
            List<Integer> endRectangle = new ArrayList<>();
            endRectangle.add(i);
            endRectangle.add(j);
            subAns.add(endRectangle);
          
            ans.add(subAns);
        } else {
          maxCol = j - 1;
          if (maxCol == col - 1) {
            break mainLoop;
          }
          break;
        }
      }
    }
  }

  public static void main(String[] args) {
    Main sol = new Main();
    int[][] input = new int[][]{{1, 1, 1}, {1, 0, 1}};
    List<List<List<Integer>>> rectangles = sol.findRectangles(input);
    System.out.println(rectangles);
  }
}
```

# Single problem 1 - Task by level (Similar: FindLeaves, LeetCode 366)
```text
input = {
{"cook", "eat"},   // do "cook" before "eat"
{"study", "eat"},
{"sleep", "study"}}

output (steps of a workflow):
{{"sleep", "cook"},.
{"study"},
{"eat"}}

```