## 

```
// Java 
public int myAtoi(String str) { 
    int index = 0, sign = 1, total = 0; 
    //1. Empty string 
    if(str.length() == 0) return 0; 
    //2. Remove Spaces 
    while(str.charAt(index) == ' ' && index < str.length()) 
        index ++; 
    //3. Handle signs 
    if(str.charAt(index) == '+' || str.charAt(index) == '-'){ 
        sign = str.charAt(index) == '+' ? 1 : -1; 
        index ++; 
    } 
     
    //4. Convert number and avoid overflow 
    while(index < str.length()){ 
        int digit = str.charAt(index) - '0'; 
        if(digit < 0 || digit > 9) break; 
        //check if total will be overflow after 10 times and add digit 
        if(Integer.MAX_VALUE/10 < total ||             
        	Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit) 
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE; 
        total = 10 * total + digit; 
        index ++; 
    } 
    return total * sign; 
} 
```


```
# Python 
class Solution(object): 
    def myAtoi(self, s): 
        if len(s) == 0 : return 0 
        ls = list(s.strip()) 
         
        sign = -1 if ls[0] == '-' else 1 
        if ls[0] in ['-','+'] : del ls[0] 
        ret, i = 0, 0 
        while i < len(ls) and ls[i].isdigit() : 
            ret = ret*10 + ord(ls[i]) - ord('0') 
            i += 1 
        return max(-2**31, min(sign * ret,2**31-1)) 
```
```c++
//C/C++ 
int myAtoi(string str) { 
   int res = 0; 
   int sign = 1; 
   size_t index = 0; 
   if(str.find_first_not_of(' ') != string::npos)  
       index = str.find_first_not_of(' '); 
   if(str[index] == '+' || str[index] == '-') 
       sign = str[index] == '-' ? -1 : 1; 
     
    while(index < str.size() && isdigit(str[index])) { 
        res = res * 10 + (str[index++] - '0'); 
        if(res*sign > INT_MAX) return INT_MAX; 
        if(res*sign < INT_MIN) return INT_MIN;  
    } 
   return res*sign; 
} 
```
```c++
class Solution { 
public: 
    int myAtoi(string str) { 
        auto i = getStartIndexOfNonSpace(str); 
        if (i < 0) return 0; 
         
        auto sign = handleSign(str, i);        
        auto num = sign * convertStringToLong(str, i); 
        return convertLongToInt(num); 
    } 
     
private: 
    int getStartIndexOfNonSpace(const string &str) { 
        for (int i = 0; i < str.size(); ++i) { 
            if (str[i] != ' ') return i; 
        } 
         
        return -1; 
    } 
     
    int handleSign(const string &str, int &i) { 
        if (str[i] == '-') { 
            ++i; 
            return -1; 
        } 
         
        if (str[i] == '+') ++i; 
        return 1; 
    } 
     
    long convertStringToLong(const string &str, int i) { 
        long num = 0;         
        while(i < str.size()) { 
            if (num > INT_MAX || !isdigit(str[i])) break;             
            num = num * 10 + str[i] - '0'; 
            ++i; 
        } 
         
        return num; 
    } 
     
    int convertLongToInt(const long &num) { 
        if (num < INT_MIN) return INT_MIN; 
        if (num > INT_MAX) return INT_MAX;      
        return (int)num;         
    } 
}; 
```

```javascript
// JavaScript 
function myAtoi(str) { 
  let index = 0; 
  let sign = 1; 
  let total = 0; 
  // 1. Empty String 
  if (str.length === 0) return 0; 
  // 2. trim 
  while (str[index] === " " && index < str.length) { 
    index++; 
  } 
  // 3. get sign 
  if (str[index] === "+" || str[index] === "-") { 
    sign = str[index] === "+" ? 1 : -1; 
    index++; 
  } 
  // 4. covert 
  while (index < str.length) { 
    let digit = str[index].codePointAt(0) - "0".codePointAt(0); 
    if (digit < 0 || digit > 9) break; 
    total = total * 10 + digit; 
    index++; 
  } 
  if (sign * total > 2 ** 31 - 1) { 
    return 2 ** 31 - 1; 
  } else { 
  } 
  return Math.max(Math.min(sign * total, 2 ** 31 - 1), -(2 ** 31) 
```
