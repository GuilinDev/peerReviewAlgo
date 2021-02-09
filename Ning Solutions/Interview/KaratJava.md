:::python
Domain
1. Leetcode 811
We are given a list cpdomains of count-paired domains. We would like a list of count-paired domains, 
(in the same format as the input, and in any order), that explicitly counts the number of visits to each subdomain.

Example 1:
Input: 
["9001 discuss.leetcode.com"]
Output: 
["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]

Example 2:
Input: 
["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
Output: 
["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
/**
过一遍substring用hashmap记一下frequency，然后再遍历即可。
*/


class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < cpdomains.length; i ++) {
            String cur =  cpdomains[i];
            String[] doms = cur.split("\\s+");
            int value  = Integer.valueOf(doms[0]);
            String[] subs = doms[1].split("\\.");
            String pre = "";
            for(int j = subs.length - 1; j >= 0; j --) {
               String combine = subs[j] + pre;
               map.put(combine, map.getOrDefault(combine, 0) + value);
               pre = "." + combine;
            }
        }
        ArrayList<String> res = new ArrayList<>();
        for(String key : map.keySet()){
            StringBuilder sb = new StringBuilder();
            sb.append(map.get(key)).append(" ").append(key);
            res.add(sb.toString());
        }
        return res;
    }
}


2. Longest Common Continuous Subarray
输入：

[
  ["3234.html", "xys.html", "7hsaa.html"], // user1
  ["3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"] // user2
]
输出两个user的最长连续且相同的访问记录。

["xys.html", "7hsaa.html"]
/**
和LCS做法类似，如果当前两个string相等就把当前格子变成[i - 1][j - 1] + 1。不相等就保留0。
    // 定义：s1[0..i-1] 和 s2[0..j-1] 的 lcs 长度为 dp[i][j]
    // 目标：s1[0..m-1] 和 s2[0..n-1] 的 lcs 长度，即 dp[m][n]
    // base case: dp[0][..] = dp[..][0] = 0

*/
int longestCommonSubsequence(String s1, String s2) {
    int m = s1.length(), n = s2.length();
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            // 现在 i 和 j 从 1 开始，所以要减一
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                // s1[i-1] 和 s2[j-1] 必然在 lcs 中
                dp[i][j] = 1 + dp[i - 1][j - 1];
            } else {
                // s1[i-1] 和 s2[j-1] 至少有一个不在 lcs 中
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
    }

    return dp[m][n];
}

3. Ads Conversion Rate
// The people who buy ads on our network don't have enough data about how ads are working for
//their business. They've asked us to find out which ads produce the most purchases on their website.

// Our client provided us with a list of user IDs of customers who bought something on a landing page
//after clicking one of their ads:

// # Each user completed 1 purchase.
// completed_purchase_user_ids = [
//   "3123122444","234111110", "8321125440", "99911063"]

// And our ops team provided us with some raw log data from our ad server showing every time a
//user clicked on one of our ads:
// ad_clicks = [
//  #"IP_Address,Time,Ad_Text",
//  "122.121.0.1,2016-11-03 11:41:19,Buy wool coats for your pets",
//  "96.3.199.11,2016-10-15 20:18:31,2017 Pet Mittens",
//  "122.121.0.250,2016-11-01 06:13:13,The Best Hollywood Coats",
//  "82.1.106.8,2016-11-12 23:05:14,Buy wool coats for your pets",
//  "92.130.6.144,2017-01-01 03:18:55,Buy wool coats for your pets",
//  "92.130.6.145,2017-01-01 03:18:55,2017 Pet Mittens",
//]
       
//The client also sent over the IP addresses of all their users.
       
//all_user_ips = [
//  #"User_ID,IP_Address",
//   "2339985511,122.121.0.155",
//  "234111110,122.121.0.1",
//  "3123122444,92.130.6.145",
//  "39471289472,2001:0db8:ac10:fe01:0000:0000:0000:0000",
//  "8321125440,82.1.106.8",
//  "99911063,92.130.6.144"
//]
       
// Write a function to parse this data, determine how many times each ad was clicked,
//then return the ad text, that ad's number of clicks, and how many of those ad clicks
//were from users who made a purchase.


// Expected output:
// Bought Clicked Ad Text
// 1 of 2  2017 Pet Mittens  
// 0 of 1  The Best Hollywood Coats
// 3 of 3  Buy wool coats for your pets
/**
没什么技术含量。愣实现就行了。
*/
function adsConversionRate(completedPurchaseUserIds, adClicks, allUserIps) {
  const userIds = new Set(completedPurchaseUserIds);
  const conversion = new Map();
  const ipToUserId = new Map();
  for (const userIp of allUserIps) {
    const [userId, ip] = userIp.split(',');
    ipToUserId.set(ip, userId);
  }
  for (const click of adClicks) {
    const [ip,, adText] = click.split(',');
    if (conversion.has(adText)) {
      conversion.get(adText)[1]++;
      if (userIds.has(ipToUserId.get(ip))) {
        conversion.get(adText)[0]++;
      }
    } else {
      const bought = userIds.has(ipToUserId.get(ip)) ? 1 : 0;
      conversion.set(adText, [bought, 1]);
    }
  }
  for (const [adText, ratio] of conversion) {
    console.log(`${ratio[0]} of ${ratio[1]}  ${adText}`);
  }
}
选课
1. 两名学生的课程overlap
You are a developer for a university. Your current project is to develop a system for students to find 
courses they share with friends. The university has a system for querying courses students are enrolled
 in, returned as a list of (ID, course) pairs.
Write a function that takes in a list of (student ID number, course name) pairs and returns, 
for every pair of students, a list of all courses they share.

Sample Input:

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

Sample Output (pseudocode, in any order):

find_pairs(student_course_pairs_1) =>
{
  [58, 17]: ["Software Design", "Linear Algebra"]
  [58, 94]: ["Economics"]
  [58, 25]: ["Economics"]
  [94, 25]: ["Economics"]
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
function courseOverlaps(studentCoursePairs) {
  if (!studentCoursePairs || studentCoursePairs.length === 0) {
    return [];
  }
  const courses = new Map();
  for (const [studentId, course] of studentCoursePairs) {
    if (courses.has(studentId)) {
      courses.get(studentId).push(course);
    } else {
      courses.set(studentId, [course]);
    }
  }
  const studentIds = [...courses.keys()];
  for (let i = 0; i < studentIds.length; i++) {
    for (let j = i + 1; j < studentIds.length; j++) {
      const overlaps = [];
      for (const course1 of courses.get(studentIds[i])) {
        for (const course2 of courses.get(studentIds[j])) {
          if (course1 === course2) {
            overlaps.push(course1);
          }
        }
      }
      console.log(`[${studentIds[i]},${studentIds[j]}]: ${overlaps}`);
    }
  }
}
2. 输出中间课程
以下是当时面试结束后留在editor里的所有内容。没有整理。题目是第三题，大家大概参考一下吧。。第三题写的是不对的当时没时间debug了。

/*
Students may decide to take different "tracks" or sequences of courses in the Computer Science curriculum. 
There may be more than one track that includes the same course, but each student follows a single linear 
track from a "root" node to a "leaf" node. In the graph below, their path always moves left to right.
Write a function that takes a list of (source, destination) pairs, and returns the name of all of the
 courses that the students could be taking when they are halfway through their track of courses.

Sample input:
all_courses = [
    ["Logic", "COBOL"],
    ["Data Structures", "Algorithms"],
    ["Creative Writing", "Data Structures"],
    ["Algorithms", "COBOL"],
    ["Intro to Computer Science", "Data Structures"],
    ["Logic", "Compilers"],
    ["Data Structures", "Logic"],
    ["Creative Writing", "System Administration"],
    ["Databases", "System Administration"],
    ["Creative Writing", "Databases"],
    ["Intro to Computer Science", "Graphics"],
]

Sample output (in any order):
          ["Data Structures", "Creative Writing", "Databases", "Intro to Computer Science"]

All paths through the curriculum (midpoint *highlighted*):

*Intro to C.S.* -> Graphics
Intro to C.S. -> *Data Structures* -> Algorithms -> COBOL
Intro to C.S. -> *Data Structures* -> Logic -> COBOL
Intro to C.S. -> *Data Structures* -> Logic -> Compiler
Creative Writing -> *Databases* -> System Administration
*Creative Writing* -> System Administration
Creative Writing -> *Data Structures* -> Algorithms -> COBOL
Creative Writing -> *Data Structures* -> Logic -> COBOL
Creative Writing -> *Data Structures* -> Logic -> Compilers

Visual representation:

                    ____________
                    |          |
                    | Graphics |
               ---->|__________|
               |                          ______________
____________   |                          |            |
|          |   |    ______________     -->| Algorithms |--\     _____________
| Intro to |   |    |            |    /   |____________|   \    |           |
| C.S.     |---+    | Data       |   /                      >-->| COBOL     |
|__________|    \   | Structures |--+     ______________   /    |___________|
                 >->|____________|   \    |            |  /
____________    /                     \-->| Logic      |-+      _____________
|          |   /    ______________        |____________|  \     |           |
| Creative |  /     |            |                         \--->| Compilers |
| Writing  |-+----->| Databases  |                              |___________|
|__________|  \     |____________|-\     _________________________
               \                    \    |                       |
                \--------------------+-->| System Administration |
                                         |_______________________|

Complexity analysis variables:

n: number of pairs in the input

*/

'use strict';

const prereqs_courses1 = [
  ['Data Structures', 'Algorithms'],
  ['Foundations of Computer Science', 'Operating Systems'],
  ['Computer Networks', 'Computer Architecture'],
  ['Algorithms', 'Foundations of Computer Science'],
  ['Computer Architecture', 'Data Structures'],
  ['Software Design', 'Computer Networks'],
];

const prereqs_courses2 = [
  ['Data Structures', 'Algorithms'],
  ['Algorithms', 'Foundations of Computer Science'],
  ['Foundations of Computer Science', 'Logic'],
];

const prereqs_courses3 = [['Data Structures', 'Algorithms']];

const allCourses = [
  ['Logic', 'COBOL'],
  ['Data Structures', 'Algorithms'],
  ['Creative Writing', 'Data Structures'],
  ['Algorithms', 'COBOL'],
  ['Intro to Computer Science', 'Data Structures'],
  ['Logic', 'Compilers'],
  ['Data Structures', 'Logic'],
  ['Creative Writing', 'System Administration'],
  ['Databases', 'System Administration'],
  ['Creative Writing', 'Databases'],
  ['Intro to Computer Science', 'Graphics'],
];

function findAllMidway(prereqs) {
  const graph = formGraph(prereqs);
  const paths = [];
  const backtracking = (path, curr) => {
    if (graph.get(curr).length === 0) {
      paths.push([...path]);
      return;
    }
    for (const next of graph.get(curr)) {
      backtracking(path, next);
    }
    path.pop(curr);
  };
  const firstCourses = findAllFirstCourses(prereqs);
  for (const course of firstCourses) {
    backtracking([], course);
  }
  const result = new Set();
  for (const path of paths) {
    if (path.length % 2 === 0) {
      result.add(path[path.length / 2 - 1]);
    } else {
      result.add(path[Math.floor(path.length / 2)]);
    }
  }
  return result;
}

function findAllFirstCourses(prereqs) {
  const result = [];
  const courses = new Set();
  const coursesHavePrereq = new Set();
  for (const prereq of prereqs) {
    courses.add(prereq[0]);
    courses.add(prereq[1]);
    coursesHavePrereq.add(prereq[1]);
  }
  for (const course of courses) {
    if (!coursesHavePrereq.has(course)) {
      result.push(course);
    }
  }
  return result;
}

function formGraph(prereqs) {
  const result = new Map();
  for (const prereq of prereqs) {
    if (result.has(prereq[0])) {
      result.get(prereq[0]).add(prereq[1]);
    } else {
      result.set(prereq[0], new Set([prereq[1]]));
    }
  }
  for (const prereq of prereqs) {
    if (!result.has(prereq[1])) {
      result.set(prereq[1], new Set());
    }
  }
  return result;
}

console.log(findAllMidway(allCourses));

function findMidway(prereqs) {
  const result = [];
  const coursesHavePrereq = new Set();
  for (const prereq of prereqs) {
    coursesHavePrereq.add(prereq[1]);
  }
  let curr;
  for (const prereq of prereqs) {
    if (!coursesHavePrereq.has(prereq[0])) {
      curr = prereq[0];
      break;
    }
  }
  const courses = [...coursesHavePrereq, curr];
  while (result.length !== courses.length) {
    result.push(curr);
    for (const prereq of prereqs) {
      if (curr === prereq[0]) {
        curr = prereq[1];
        break;
      }
    }
  }
  if (result.length % 2 === 0) {
    return result[result.length / 2 - 1];
  }
  return result[Math.floor(result.length / 2)];
}

const studentCoursePairs1 = [
  ['58', 'Linear Algebra'],
  ['94', 'Art History'],
  ['94', 'Operating Systems'],
  ['17', 'Software Design'],
  ['58', 'Mechanics'],
  ['58', 'Economics'],
  ['17', 'Linear Algebra'],
  ['17', 'Political Science'],
  ['94', 'Economics'],
  ['25', 'Economics'],
  ['58', 'Software Design'],
];

const studentCoursePairs2 = [
  ['42', 'Software Design'],
  ['0', 'Advanced Mechanics'],
  ['9', 'Art History'],
];

function findAllOverlaps(studentCoursePairs) {
  const map = new Map();
  for (const [studentId, course] of studentCoursePairs) {
    if (map.has(studentId)) {
      map.get(studentId).push(course);
    } else {
      map.set(studentId, [course]);
    }
  }
  const studentIds = [...map.keys()];
  const result = new Map();
  for (let i = 0; i < studentIds.length; i++) {
    for (let j = i + 1; j < studentIds.length; j++) {
      const key = `${studentIds[i]},${studentIds[j]}`;
      const overlaps = [];
      for (const course1 of map.get(studentIds[i])) {
        for (const course2 of map.get(studentIds[j])) {
          if (course1 === course2) {
            overlaps.push(course1);
          }
        }
      }
      result.set(key, overlaps);
    }
  }
  return result;
}

// console.log(findAllOverlaps(studentCoursePairs1));
// console.log(findAllOverlaps(studentCoursePairs2));
矩阵题
1. 找一个矩形
there is an image filled with 0s and 1s. There is at most one rectangle in this image filled with 0s, 
find the rectangle. Output could be the coordinates of top-left and bottom-right elements of the rectangle, 
or top-left element, width and height.
function findOneRectangle(board) {
  if (!board || board.length === 0 || board[0].length === 0) {
    return [];
  }
  const result = [];
  for (let i = 0; i < board.length; i++) {
    for (let j = 0; j < board[0].length; j++) {
      if (board[i][j] === 0) {
        result.push([i, j]);
        let height = 1, width = 1;
        while (i + height < board.length && board[i + height][j] === 0) {
          height++;
        }
        while (j + width < board[0].length && board[i][j + width] === 0) {
          width++;
        }
        result.push([i + height - 1, j + width - 1]);
        break;
      }
      if (result.length !== 0) {
        break;
      }
    }
  }
  return result;
}
2. 找多个矩形
for the same image, it is filled with 0s and 1s. It may have multiple rectangles filled with 0s. 
The rectangles are separated by 1s. Find all the rectangles.
function findMultipleRectangle(board) {
  if (!board || board.length === 0 || board[0].length === 0) {
    return [];    
  }
  const result = [];
  for (let i = 0; i < board.length; i++) {
    for (let j = 0; j < board[0].length; j++) {
      if (board[i][j] === 0) {
        const rectangle = [[i, j]];
        board[i][j] = 1;
        let height = 1, width = 1;
        while (i + height < board.length && board[i + height][j] === 0) {
          height++;
        }
        while (j + width < board[0].length && board[i][j + width] === 0) {
          width++;
        }
        for (let h = 0; h < height; h++) {
          for (let w = 0; w < width; w++) {
            board[i + h][j + w] = 1;
          }
        }
        rectangle.push([i + height - 1, j + width - 1]);
        result.push(rectangle);
      }
    }
  }
  return result;
}
3. 找多个形状
the image has random shapes filled with 0s, separated by 1s. Find all the shapes.
 Each shape is represented by coordinates of all the elements inside.
function findMultipleShapes(board) {
  if (!board || board.length === 0 || board[0].length === 0) {
    return [];    
  }
  const result = [];
  const floodFillDFS = (x, y, shape) => {
    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] === 1) {
      return;
    }
    board[x][y] = 1;
    shape.push([x, y]);
    floodFillDFS(x + 1, y, shape);
    floodFillDFS(x - 1, y, shape);
    floodFillDFS(x, y - 1, shape);
    floodFillDFS(x, y + 1, shape);
  };
  for (let i = 0; i < board.length; i++) {
    for (let j = 0; j < board[0].length; j++) {
      if (board[i][j] === 0) {
        shape = [];
        floodFillDFS(i, j, shape);
        result.push(shape);
      }
    }
  }
  return result;
}
reflow字符串
1. word wrap
给一个word list 和最大的长度，要求把这些word用 - 串联起来，但不能超过最大的长度。

function wordWrap(words, maxLen) {
  if (!words || words.length === 0) {
    return [];
  }
  const result = [];
  let i = 0;
  while (i < words.length) {
    let remain = maxLen;
    let count = 0;
    while (i < words.length) {
      if (remain - words[i].length < 0) {
        break;
      }
      count++;
      remain -= words[i++].length + 1;
    }
    result.push(words.slice(i - count, i).join('-'));
  }
  return result;
}
2. word processor
We are building a word processor and we would like to implement a "reflow" functionality that also applies full 
justification to the text.
Given an array containing lines of text and a new maximum width, re-flow the text to fit the new width. 
Each line should have the exact specified width. If any line is too short, insert '-' (as stand-ins for spaces) 
between words as equally as possible until it fits.
Note: we are using '-' instead of spaces between words to make testing and visual verification of the results easier.

lines = [ "The day began as still as the",
          "night abruptly lighted with",
          "brilliant flame" ]

reflowAndJustify(lines, 24) ... "reflow lines and justify to length 24" =>

        [ "The--day--began-as-still",
          "as--the--night--abruptly",
          "lighted--with--brilliant",
          "flame" ] // <--- a single word on a line is not padded with spaces
function reflowAndJustify(lines, maxLen) {
  if (!lines || lines.length === 0) {
    return [];
  }
  const words = lines.join(' ').split(' ');
  const result = [];
  let i = 0;
  while (i < words.length) {
    // split words into lines first
    let remain = maxLen;
    let count = 0;
    while (i < words.length) {
      if (remain - words[i].length < 0) {
        break;
      }
      count++;
      remain -= words[i++].length + 1;
    }
    const line = words.slice(i - count, i);

    // after splitting into lines, calculate the required dashes between each word
    const n = line.reduce((n, word) => n + word.length, 0);
    let reflowed = ''; // line result with padded dashes
    const baseDash = '-'.repeat(parseInt((maxLen - n) / (line.length - 1)));
    let extra = (maxLen - n) % (line.length - 1); // some dashes at the beginning has one extra dash
    for (let j = 0; j < line.length; j++) {
      if (j === line.length - 1) {
        reflowed += line[j];
      } else {
        reflowed +=
          extra-- <= 0 ? line[j] + baseDash : line[j] + baseDash + '-';
      }
    }
    result.push(reflowed);
  }
  return result;
}
计算器
1. 基本加减计算器
给输入为string，例如"2+3-999"，之包含+-操作，返回计算结果。
function basicCalculator(expression) {
  if (!expression || expression.length === 0) {
    return 0;
  }
  let result = 0;
  let sign = 1;
  for (let i = 0; i < expression.length; i++) {
    if (isNumeric(expression[i])) {
      let num = expression[i];
      while (i + 1 < expression.length && isNumeric(expression[i + 1])) {
        num += expression[++i];
      }
      num = parseInt(num);
      result += num * sign;
    } else if (expression[i] === '+') {
      sign = 1;
    } else if (expression[i] === '-') {
      sign = -1;
    }
  }
  return result;
}

function isNumeric(c) {
  return c >= '0' && c <= '9';
}
2. 加减计算器带括号 - Leetcode 224
加上parenthesis， 例如"2+((8+2)+(3-999))"，返回计算结果。

/*
只是比第一问多了一个stack以及两个else if语句判断括号。
*/
function basicCalculator(expression) {
  if (!expression || expression.length === 0) {
    return 0;
  }
  let result = 0;
  let sign = 1;
  let stack = [];
  for (let i = 0; i < expression.length; i++) {
    if (isNumeric(expression[i])) {
      let num = expression[i];
      while (i + 1 < expression.length && isNumeric(expression[i + 1])) {
        num += expression[++i];
      }
      num = parseInt(num);
      result += num * sign;
    } else if (expression[i] === '+') {
      sign = 1;
    } else if (expression[i] === '-') {
      sign = -1;
    } else if (expression[i] === '(') {
      stack.push(result);
      stack.push(sign);
      result = 0;
      sign = 1;
    } else if (expression[i] === ')') {
      result = result * stack.pop() + stack.pop();
    }
  }
  return result;
}

function isNumeric(c) {
  return c >= '0' && c <= '9';
}
3. 带变量计算器
不光有数字和operator，还有一些变量，这些变量有些可以表示为一个数值，需要从给定的map里去get这个变量的value。然后有的变量不能转为数字，
所以结果要包含这些不可变成数字的单词以及其他数字部分通过计算器得到的结果。
这题就写个思路吧，估计不会问太深。Leetcode770是原题，discussion区域都是喷这道题的。

矩阵合法
1. 矩阵合法
给一个N*N的矩阵，判定是否是有效的矩阵。有效矩阵的定义是每一行或者每一列的数字都必须正好是1到N的数。输出一个bool。

function isValidMatrix(matrix) {
  if (!matrix || matrix.length === 0 || matrix[0].length === 0) {
    return false;
  }
  const n = matrix.length;
  for (let i = 0; i < n; i++) {
    const rowSet = new Set();
    const colSet = new Set();
    let rowMin = Number.POSITIVE_INFINITY, rowMax = Number.NEGATIVE_INFINITY;
    let colMin = rowMin, colMax = rowMax;
    for (let j = 0; j < n; j++) {
      if (!rowSet.has(matrix[i][j])) {
        rowSet.add(matrix[i][j]);
        rowMin = Math.min(rowMin, matrix[i][j]);
        rowMax = Math.max(rowMax, matrix[i][j]);
      } else {
        return false;
      }
      if (!colSet.has(matrix[j][i])) {
        colSet.add(matrix[j][i]);
        colMin = Math.min(colMin, matrix[j][i]);
        colMax = Math.max(colMax, matrix[j][i]);
      } else {
        return false;
      }
    }
    if (rowMin !== 1 || colMin !== 1 || rowMax !== n || colMax !== n) {
      return false;
    }
  }
  return true;
}
2. nonogram
"""
A nonogram is a logic puzzle, similar to a crossword, in which the player is given a blank grid and has to color
 it according to some instructions. Specifically, each cell can be either black or white, 
 which we will represent as 0 for black and 1 for white.

+------------+
| 1  1  1  1 |
| 0  1  1  1 |
| 0  1  0  0 |
| 1  1  0  1 |
| 0  0  1  1 |
+------------+

For each row and column, the instructions give the lengths of contiguous runs of black (0) cells. 
For example, the instructions for one row of [ 2, 1 ] indicate that there must be a run of two black cells, 
followed later by another run of one black cell, and the rest of the row filled with white cells.

These are valid solutions: [ 1, 0, 0, 1, 0 ] and [ 0, 0, 1, 1, 0 ] and also [ 0, 0, 1, 0, 1 ]
This is not valid: [ 1, 0, 1, 0, 0 ] since the runs are not in the correct order.
This is not valid: [ 1, 0, 0, 0, 1 ] since the two runs of 0s are not separated by 1s.

Your job is to write a function to validate a possible solution against a set of instructions. 
Given a 2D matrix representing a player's solution; and instructions for each row along with additional 
instructions for each column; return True or False according to whether both sets of instructions match.

Example instructions #1

matrix1 = [[1,1,1,1],
           [0,1,1,1],
           [0,1,0,0],
           [1,1,0,1],
           [0,0,1,1]]
rows1_1    =  [], [1], [1,2], [1], [2]
columns1_1 =  [2,1], [1], [2], [1]
validateNonogram(matrix1, rows1_1, columns1_1) => True

Example solution matrix:
matrix1 ->
                                   row
                +------------+     instructions
                | 1  1  1  1 | <-- []
                | 0  1  1  1 | <-- [1]
                | 0  1  0  0 | <-- [1,2]
                | 1  1  0  1 | <-- [1]
                | 0  0  1  1 | <-- [2]
                +------------+
                  ^  ^  ^  ^
                  |  |  |  |
  column       [2,1] | [2] |
  instructions      [1]   [1]


Example instructions #2

(same matrix as above)
rows1_2    =  [], [], [1], [1], [1,1]
columns1_2 =  [2], [1], [2], [1]
validateNonogram(matrix1, rows1_2, columns1_2) => False

The second and third rows and the first column do not match their respective instructions.

Example instructions #3

matrix2 = [
[ 1, 1 ],
[ 0, 0 ],
[ 0, 0 ],
[ 1, 0 ]
]
rows2_1    = [], [2], [2], [1]
columns2_1 = [1, 1], [3]
validateNonogram(matrix2, rows2_1, columns2_1) => False

The black cells in the first column are not separated by white cells.

n: number of rows in the matrix
m: number of columns in the matrix
"""

matrix1 = [
    [1,1,1,1], # []
    [0,1,1,1], # [1] -> a single run of _1_ zero (i.e.: "0")
    [0,1,0,0], # [1, 2] -> first a run of _1_ zero, then a run of _2_ zeroes
    [1,1,0,1], # [1]
    [0,0,1,1], # [2]
]

# True
rows1_1 = [[],[1],[1,2],[1],[2]]
columns1_1 = [[2,1],[1],[2],[1]]
# False
rows1_2 = [[],[],[1],[1],[1,1]]
columns1_2 = [[2],[1],[2],[1]]

matrix2 = [
    [1,1],
    [0,0],
    [0,0],
    [1,0]
]
# False
rows2_1 = [[],[2],[2],[1]]
columns2_1 = [[1,1],[3]]
function isValidNonogram(matrix, rows, cols) {
  if (!matrix || !rows || !cols) {
    return false;
  }
  const n = matrix.length;
  const m = matrix[0].length;
  if (n === 0 || n !== rows.length || m !== cols.length) {
    return false;
  }
  return (
    isNonogramRowsValid(matrix, rows, n, m) &&
    isNonogramColsValid(matrix, cols, n, m)
  );
}

function isNonogramRowsValid(matrix, rows, n, m) {
  for (let i = 0; i < n; i++) {
    let rowIndex = 0;
    for (let j = 0; j < m; j++) {
      if (matrix[i][j] === 0) {
        if (rows[i].length === 0) {
          return false;
        }
        for (let k = 0; k < rows[rowIndex]; k++) {
          if (j + k >= m || matrix[i][j + k] !== 0) {
            return false;
          }
        }
        j += rows[i][rowIndex++];
      }
    }
    if (rowIndex !== rows[i].length) {
      return false;
    }
  }
  return true;
}

function isNonogramColsValid(matrix, cols, n, m) {
  for (let i = 0; i < m; i++) {
    let colIndex = 0;
    for (let j = 0; j < n; j++) {
      if (matrix[j][i] === 0) {
        if (cols[i].length === 0) {
          return false;
        }
        for (let k = 0; k < cols[colIndex]; k++) {
          if (j + k >= n || matrix[j + k][i] !== 0) {
            return false;
          }
        }
        j += cols[i][colIndex++];
      }
    }
    if (colIndex !== cols[i].length) {
      return false;
    }
  }
  return true;
}
祖先
1. 0个或1个parent的节点
输入是int[][] input, input[0]是input[1] 的parent，比如 {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}}会形成上面的图
第一问是只有0个parents和只有1个parent的节点

  1    2    3
/  \  /      \
4    5        6
                \
                  7
function findNodesWithZeroOrOneParent(edges) {
  if (!edges || edges.length === 0) {
    return [];
  }
  const result = [];
  const map = new Map();
  for (const [parent, child] of edges) {
    if (map.has(child)) {
      map.get(child).add(parent);
    } else {
      map.set(child, new Set([parent]));
    }
  }
  for (const [child, parentSet] of map) {
    if (parentSet.length === 0 || parentSet.length === 1) {
      result.push(child);
    }
  }
  return result;
}
2. 两个节点是否有公共祖先
function hasCommonAncestor(edges, x, y) {
  if (!edges || edges.length === 0) {
    return false;
  }
  const directParents = new Map();
  for (const [parent, child] of edges) {
    if (directParents.has(child)) {
      directParents.get(child).add(parent);
    } else {
      directParents.set(child, new Set([parent]));
    }
  }
  const findAllParents = (e) => {
    const result = new Set();
    const stack = [];
    stack.push(e);
    while (stack.length !== 0) {
      const curr = stack.pop();
      const parents = directParents.get(curr);
      if (!parents) {
        continue;
      }
      for (const parent of parents) {
        if (result.has(parent)) {
          continue;
        }
        result.add(parent);
        stack.push(parent);
      }
    }
    return result;
  };
  const parentsOfX = findAllParents(x);
  const parentsOfY = findAllParents(y);
  for (const parentOfX of parentsOfX) {
    if (parentsOfY.has(parentOfX)) {
      return true;
    }
  }
  return false;
}
3. 最远祖先
function Queue() {
  this.firstStack = [];
  this.secondStack = []; 
  this.length = 0;
}

Queue.prototype.push = function(x) {
  this.firstStack.push(x);
  this.length++;
};

Queue.prototype.pop = function() {
  if (this.secondStack.length === 0) {
    while (this.firstStack.length !== 0) {
      this.secondStack.push(this.firstStack.pop());
    }
  }
  this.length--;
  return this.secondStack.pop();
};

function earliestAncestor(parentChildPairs, x) {
  const directParents = new Map();
  for (const [parent, child] of parentChildPairs) {
    if (directParents.has(child)) {
      directParents.get(child).add(parent);
    } else {
      directParents.set(child, new Set([parent]));
    }
  }
  let currlayer = new Queue();
  let prevlayer;
  const visited = new Set();
  currlayer.push(x);
  while (currlayer.length !== 0) {
    prevlayer = new Queue();
    let size = currlayer.length;
    while (size--) {
      const curr = currlayer.pop();
      prevlayer.push(curr);
      const parents = directParents.get(curr);
      if (!parents) {
        continue;
      }
      for (const parent of parents) {
        if (visited.has(parent)) {
          continue;
        }    
        currlayer.push(parent);
        visited.add(parent);
      }
    }
  }
  return prevlayer.pop();
}
门禁刷卡
1. 找进出记录不符的人
Given a list of people who enter and exit, find the people who entered without
their badge and who exited without their badge.

// badge_records = [
//   ["Martha",   "exit"],
//   ["Paul",     "enter"],
//   ["Martha",   "enter"],
//   ["Martha",   "exit"],
//   ["Jennifer", "enter"],
//   ["Paul",     "enter"],
//   ["Curtis",   "enter"],
//   ["Paul",     "exit"],
//   ["Martha",   "enter"],
//   ["Martha",   "exit"],
//   ["Jennifer", "exit"],
// ]

// Expected output: ["Paul", "Curtis"], ["Martha"]
function invalidBadgeRecords(records) {
  if (!records || records.length === 0) {
    return [];
  }
  const result = [[], []];
  // 0 for exited, 1 for entered
  const state = new Map();
  const invalidEnter = new Set();
  const invalidExit = new Set();
  for (const [name, action] of records) {
    !state.has(name) && state.set(name, 0);
    if (action === 'enter') {
      if (state.get(name) === 0) {
        state.set(name, 1);
      } else {
        invalidEnter.add(name);
      }
    } else {
      if (state.get(name) === 1) {
        state.set(name, 0);
      } else {
        invalidExit.add(name);
      }
    }
  }
  for (const [name, s] of state) {
    if (s === 1) {
      invalidEnter.add(name);
    }
  }
  for (const name of invalidEnter) {
    result[0].push(name);
  }
  for (const name of invalidExit) {
    result[1].push(name);
  }
  return result;
}
2. 一小时内access多次
给 list of [name, time], time is string format: '1300' //下午一点
return: list of names and the times where their swipe badges within one hour. 
if there are multiple intervals that satisfy the condition, return any one of them.
name1: time1, time2, time3...
name2: time1, time2, time3, time4, time5...
example:
input: [['James', '1300'], ['Martha', '1600'], ['Martha', '1620'], ['Martha', '1530']] 
output: {
'Martha': ['1600', '1620', '1530']
}
function frequentAccess(records) {
  if (!records || records.length === 0) {
    return [];
  }
  const result = [];
  const times = new Map();
  for (const [name, timestamp] of records) {
    if (times.has(name)) {
      times.get(name).push(timestamp);
    } else {
      times.set(name, [timestamp]);
    }
  }
  for (const [name, timestamps] of times) {
    timestamps.sort(timeDifference);
    let i = 0;
    let timewindow = [timestamps[i]];
    for (let j = 1; j < timestamps.length; j++) {
      if (timeDifference(timestamps[i], timestamps[j]) < 60) {
        timewindow.push(timestamps[j]);
      } else {
        timewindow = [timestamps[j]];
        i = j;
      }
    }
    if (timewindow.length >= 3) {
      result.push([name, timewindow]);
    }
  }
  return result;
}

function timeDifference(a, b) {
  const aHour = Math.floor(a / 100);
  const bHour = Math.floor(b / 100);
  const aMinute = a % 100;
  const bMinute = b % 100;
  return aHour * 60 + aMinute - (bHour * 60 + bMinute);
}
开会
1. 是否有空余时间
第一题：类似meeting rooms，输入是一个int[][] meetings, int start, int end, 每个数都是时间，13：00 =》 1300， 9：30 =》 18930， 
看新的meeting 能不能安排到meetings
ex: {[1300, 1500], [930, 1200],[830, 845]}, 新的meeting[820, 830], return true; [1450, 1500] return false;

function canSchedule(meetings, start, end) {
  for (const meeting of meetings) {
    if (
      (start >= meeting[0] && start < meeting[1]) ||
      (end > meeting[0] && end <= meeting[1]) ||
      (start < meeting[0] && end > meeting[1])
    ) {
      return false;
    }
  }
  return true;
}
2. 返回空闲时间段
类似merge interval，唯一的区别是输出，输出空闲的时间段，merge完后，再把两两个之间的空的输出就好，注意要加上0 - 第一个的start time

function spareTime(meetings) {
  if (!meetings || meetings.length === 0) {
    return [];
  }
  meetings = mergeMeetings(meetings);
  const result = [];
  let start = 0;
  for (let i = 0; i < meetings.length; i++) {
    result.push([start, meetings[i][0]]);
    start = meetings[i][1];
  }
  return result;
}

function mergeMeetings(meetings) {
  const result = [];
  meetings.sort((a, b) => a[0] - b[0]);
  let [start, end] = meetings[0];
  for (const meeting of meetings) {
    if (start < meeting[1]) {
      end = Math.max(end, meeting[1]);
    } else {
      result.push(start, end);
      start = meeting[0];
      end = meeting[0];
    }
  }
  return result;
}
Sparse Vector
1. 设计sparse vector
sparseVector v = new sparseVector(100); //size constructor; size is 100.
    v.set(0, 1.0);
    v.set(3, 2.0);
    v.set(80,-4.5);

    System.out.println(v.get(80)); //should print -4.5
    System.out.println(v.get(50)); //should print 0.0

    try {
       System.out.println(v.get(101)); //error -- index out of range
       throw new IllegalStateException("We should not get here, an exception should have been thrown");
    } catch (IndexOutOfBoundsException t) {
       // success
    }
    System.out.println(v.toString()); //should print something like [1.0, 0.0, 0.0, 2.0, 0.0, ...]  
class IndexOutOfRangeError extends Error {
  constructor(message) {
    super(message);
  }
}

function Node(val, next, index) {
  this.val = val;
  this.next = next;
  this.index = index;
}

function SparseVector(n) {
  this.length = n;
  this.head = null;
}

SparseVector.prototype.set = function SparseVectorSet(index, val) {
  if (index >= this.length) {
    throw new IndexOutOfRangeError(
      `Index out of range: ${index} of ${this.length}`
    );
  }
  let curr = this.head;
  if (!curr) {
    const node = new Node(val, null, index);
    this.head = node;
    return;
  }
  let prev = new Node();
  prev.next = curr;
  while (curr && curr.index < index) {
    prev = curr;
    curr = curr.next;
  }
  if (curr) {
    if (curr.index === index) {
      curr.val = val;
    } else {
      const node = new Node(val, curr, index);
      prev.next = node;
    }
  } else {
    prev.next = new Node(val, null, index);
  }
};

SparseVector.prototype.get = function SparseVectorGet(index) {
  if (index >= this.length) {
    throw new IndexOutOfRangeError(
      `Index out of range: ${index} of ${this.length}`
    );
  }
  let curr = this.head;
  while (curr && curr.index !== index) {
    curr = curr.next;
  }
  return curr ? curr.val : 0;
};

SparseVector.prototype.toString = function SparseVectorToString() {
  const result = [];
  let curr = this.head;
  for (let i = 0; i < this.length; i++) {
    if (!curr || i < curr.index) {
      result.push(0);
    } else if (i === curr.index) {
      result.push(curr.val);
    } else {
      curr = curr.next;
      i--;
    }
  }
  return '[' + result.toString() + ']';
};
2. 实现add，dot和cos
Add these operations to your library: Addition, dot product, and cosine. Formulae for each are provided below; 
we’re more interested in you writing the code than whether you’ve memorized the formula. 
For each operation, your code should throw an error if the two input vectors are not equal length.
Sample input/output:
//Note: This is pseudocode. Your actual syntax will vary by language.
v1 = new vector(5)
v1[0] = 4.0
v1[1] = 5.0
. from: 1point3acres.com/bbs 
v2 = new vector(5)
v2[1] = 2.0
v2[3] = 3.0. From 1point 3acres bbs

v3 = new vector(2)
print v1.add(v2) //should print [4.0, 7.0, 0.0, 3.0, 0.0]
print v1.add(v3) //error -- vector lengths don’t match

print v1.dot(v2) //should print 10
print v1.dot(v3) //error -- vector lengths don’t match

print v1.cos(v2) //should print 0.433
print v1.cos(v3) //error -- vector lengths don’t match


Formulae:
Addition
a.add(b) = [a[0]+b[0], a[1]+b[1], a[2]+b[2], ...]
Dot product
a.dot(b) = a[0]*b[0] + a[1]*b[1] + a[2]*b[2] + ...

Cosine
a.cos(b) = a.dot(b) / (norm(a) * norm(b))
//norm(a) = sqrt(a[0]^2 + a[1]^2 + a[2]^2 + ...). 
SparseVector.prototype.add = function SparseVectorAdd(v2) {
  if (this.length !== v2.length) {
    throw new Error('length mismatch');
  }
  const result = [];
  for (let i = 0; i < this.length; i++) {
    result.push(this.get(i) + v2.get(i));
  }
  return result;
};

SparseVector.prototype.dot = function SparseVectorDot(v2) {
  if (this.length !== v2.length) {
    throw new Error('length mismatch');
  }
  let result = 0;
  for (let i = 0; i < this.length; i++) {
    result += this.get(i) * v2.get(i);
  }
  return result;
};

SparseVector.prototype.norm = function SparseVectorNorm() {
  let sum = 0;
  for (let i = 0; i < this.length; i++) {
    const val = this.get(i);
    sum += val * val;
  }
  return Math.sqrt(sum);
};

SparseVector.prototype.cos = function SparseVectorCos(v2) {
  return this.dot(b) / (this.norm() * v2.norm());
};
找宝藏
1. Find legal moves
第一问就是给一个i和j，找出身边四个方向里为0的所有格子。

2. 找能去的所有0区域
给一个二维matrix，-1代表墙，0代表路。问给定一个起点坐标为0，是否能到达所有的0。

function findLegalMoves(matrix, i, j) {
  if (!matrix || matrix.length === 0) {
    return false;
  }
  const visited = Array.from({ length: matrix.length }, () =>
    Array.from({ length: matrix[0].length }, () => false)
  );
  const floodFillDFS = (x, y) => {
    if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] === -1 || visited[x][y]) {
      return;
    } 
    visited[x][y] = true;
    floodFillDFS(x - 1, y);
    floodFillDFS(x + 1, y);
    floodFillDFS(x, y - 1);
    floodFillDFS(x, y + 1);
  };
  floodFillDFS(i, j);
  for (let i = 0; i < matrix.length; i++) {
    for (let j = 0; j < matrix[0].length; j++) {
      if (!visited[i][j] && matrix[i][j] === 0) {
        return false;
      }
    }
  }
  return true;
}
3. 最短路径找treasure
board3 中1代表钻石，给出起点和终点，问有没有一条不走回头路的路线，能从起点走到终点，并拿走所有的钻石，给出所有的最短路径。

board3 = [
    [  1,  0,  0, 0, 0 ],
    [  0, -1, -1, 0, 0 ],
    [  0, -1,  0, 1, 0 ],
    [ -1,  0,  0, 0, 0 ],
    [  0,  1, -1, 0, 0 ],
    [  0,  0,  0, 0, 0 ],
]


treasure(board3, (5, 0), (0, 4)) -> None

treasure(board3, (5, 2), (2, 0)) ->
  [(5, 2), (5, 1), (4, 1), (3, 1), (3, 2), (2, 2), (2, 3), (1, 3), (0, 3), (0, 2), (0, 1), (0, 0), (1, 0), (2, 0)]
  Or
  [(5, 2), (5, 1), (4, 1), (3, 1), (3, 2), (3, 3), (2, 3), (1, 3), (0, 3), (0, 2), (0, 1), (0, 0), (1, 0), (2, 0)]

treasure(board3, (0, 0), (4, 1)) ->
  [(0, 0), (0, 1), (0, 2), (0, 3), (1, 3), (2, 3), (2, 2), (3, 2), (3, 1), (4, 1)]
  Or
  [(0, 0), (0, 1), (0, 2), (0, 3), (1, 3), (2, 3), (3, 3), (3, 2), (3, 1), (4, 1)]

function findAllTreasures(board, start, end) {
  if (!board) {
    return [];
  }
  let numTreasures = 0;
  for (let i = 0; i < board.length; i++) {
    for (let j = 0; j < board[0].length; j++) {
      if (board[i][j] === 1) {
        numTreasures++;
      }
    }
  }
  const paths = [];
  const dfs = (x, y, path, remainTreasure) => {
    if (
      x < 0 ||
      x >= board.length ||
      y < 0 ||
      y >= board[0].length ||
      board[x][y] === -1 ||
      board[x][y] === 2
    ) {
      return;
    }
    path.push([x, y]);
    const temp = board[x][y];
    if (temp === 1) {
      remainTreasure--;
    }
    if (x === end[0] && y === end[1] && remainTreasure === 0) {
      paths.push([...path]);
      path.pop();
      board[x][y] = temp;
      return;
    }
    board[x][y] = 2;
    dfs(x + 1, y, path, remainTreasure);
    dfs(x - 1, y, path, remainTreasure);
    dfs(x, y + 1, path, remainTreasure);
    dfs(x, y - 1, path, remainTreasure);
    board[x][y] = temp;
    path.pop();
  };
  dfs(start[0], start[1], [], numTreasures);
  if (paths.length === 0) {
    return [];
  }
  let minPaths = paths[0].length;
  for (let i = 0; i < paths.length; i++) {
    minPaths = Math.min(minPaths, paths[i].length);
  }
  return paths.filter((path) => path.length === minPaths);
}
```
