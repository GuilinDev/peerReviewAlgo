package Pratice;

public class ReverseStringButHtml {
	// String s[] = str.toString().split("[^a-zA-Z]");
	 public String reverseHtml2(String html) {
		 if(html == null || html.length() < 2){
			 return html;
		 }
		 int len = html.length();
		 char[] htmlArr = html.toCharArray();
		 reverseChar(htmlArr,0,len -1);
		 int left = 0;
		 while(left < len){
			 if(htmlArr[left] != ';'){
				 left++;
			 }else {
				 int right = left+1;
				 if(right >= len -1){
					 break;
				 }
				 while(htmlArr[right] != '&'){
					 if(htmlArr[right] == ';'){
						 left = right;
					 }
					 right++;
				 }
				 reverseChar(htmlArr,left,right);
				 left = right+1;
			 }
		 }
		 return new String(htmlArr);
	 }
	 private  void reverseChar(char[] chars,int start,int end){
		 while(start < end) {
			 char temp = chars[start];
			 chars[start++] = chars[end];
			 chars[end--] = temp;
		 }
	 }
	 public static void main(String[] args) {
	   	ReverseStringButHtml sol = new ReverseStringButHtml();
	        
	          String s = "1234&eur;o;5677&&eu;567&";
	          String s1 = "There is all in all, two all";
	       // String s = "&euro4321";
	        System.out.println(sol.reverseHtml2(s));
	        System.out.println(sol.reverseHtml(s));
	        System.out.println(sol.allappend(s1, "all"));
	    }
	 
	 public  String  reverseHtml(String html) {
		 if(html == null || html.length()==0) {
			 return null;
		 }
		 int  len = html.length();
		 char[] chArr = html.toCharArray();
		 reverseChar(chArr,0,len-1);
		 int start = 0;
		 int  end = 0;
		 while(end<len){
			 if(chArr[end] == ';'){
				 start = end;
			 }else if(chArr[end] == '&'){
				 if(chArr[start] == ';')
					 reverseChar(chArr,start,end);
				  start = end+1;
			 } 
			 end++;
			 
		 }
		return new String(chArr); 
	 }

	 public String allappend(String input, String key) {
	 	StringBuilder sb = new StringBuilder();
	 	int kl = key.length();
	 	for (int i = 0; i < input.length(); i ++){
	 		if(i + kl >= input.length()) {
	 			sb.append(input.substring(i));
	 			break;
	 		}
	 		if(input.substring(i, i + kl).equals(key)){
	 			sb.append("<b>").append(key).append("</b>");
	 			i =  i + kl;
			} else {
	 			sb.append(input.charAt(i));
			}
	 	}
	 	return sb.toString();
	 }
}
