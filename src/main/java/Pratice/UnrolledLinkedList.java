package Pratice;
/*Given a LinkedList, every node contains a array. Every element of the array is char
		implement two functions
		1. get(int index) find the char at the index
		2. insert(char ch, int index) insert the char to the index
        3:删除一个数怎么处理，需要注意的地方也就是如果node空了就删掉吧。
		那就需要记录前一个node了，这样比较好删掉当前node。*/
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
		if (index < 0 || index >= totalLen)
			return ' ';

		Node cur = head;
		while (cur != null) {
			if (index >= cur.len)
				index -= cur.len;
			else {
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
			if (index >= cur.len) {
				index -= cur.len;
			} else {
				// node is full
				if (cur.len == 5) {
					Node newNode = new Node();
					newNode.chars[0] = cur.chars[4];
					newNode.len = 1;
					newNode.next = cur.next;
					cur.next = newNode;
					cur.len--;
				}

				// normal case
				cur.len++;
				for (int i = cur.len - 1; i > index; i--)
					cur.chars[i] = cur.chars[i-1];
				cur.chars[index] = c;
				break;
			}
			prev = cur;
			cur = cur.next;
		}

		// node is null
		if (cur == null) {
			Node newNode = new Node();
			newNode.chars[0] = c;
			newNode.len = 1;
			prev.next = newNode;
			// case 4: insert 1st element
			if (head == null)
				head = prev.next;
		}
		totalLen++;
	}

	public void delete(int index) {
		if (index < 0 || index >= totalLen)
			return;

		Node prev = new Node();
		prev.next = head;
		Node cur = head;
		while (cur != null) {
			if (index >= cur.len) {
				index -= cur.len;
			} else {
				if (cur.len == 1) {
					prev.next = cur.next;
				} else {
					for (int i = index; i < cur.len - 1; i++) {
						cur.chars[i] = cur.chars[i+1];
					}
					cur.len--;
				}
				break; // DO NOT forget this
			}
			prev = cur;
			cur = cur.next;
		}
		// corner case: only one element in the linked list
		head = prev.next;
		totalLen--;
	}

}
