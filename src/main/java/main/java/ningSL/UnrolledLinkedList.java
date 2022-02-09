package main.java.ningSL;

class UnrolledLinkedList {
	class Node {
		char[] chars = new char[5];
		Node next;
		int len;
	}

	Node head;
	int totalLen;

	public UnrolledLinkedList(Node head, int totalLen) {
		this.head = head;
		this.totalLen = totalLen;
	}
	public char get(int index) {
		if (index < 0 || index > totalLen) return ' ';
		Node cur = head;
		while (cur != null){
			if(index >= cur.len){
				index -= cur.len;
			} else {
				return cur.chars[index];
			}
			cur = cur.next;
		}
		return ' ';
	}

	public void insert(int index, char c) {
		if (index < 0 || index > totalLen)
			return;
		Node prev = new Node();
		prev.next = head;
		Node cur = head;
		while (cur != null) {
			if(index >= cur.len){
				index -=cur.len;
			} else {
				if(cur.len == 5){
					Node newNode = new Node();
					newNode.chars[0] = cur.chars[4];
					newNode.len = 1;
					newNode.next = cur.next;
					cur.next = newNode;
					cur.len --;
				}
				cur.len ++;
				for (int i = cur.len - 1; i > index; i --){
					cur.chars[i] = cur.chars[i-1];
					cur.chars[index] = c;

				}
			}
			prev = prev.next;
			cur = cur.next;
		}

	}

}