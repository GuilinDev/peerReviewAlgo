package main.java.ningSL;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    public static void main(String[] args) {
        LRUCache lru = new LRUCache(2);
        lru.put(1,1);
        System.out.println(lru.cap);

    }
   static class Node {
        int key, value;
        Node pre, next;
        Node(int key, int value) {
            this.key = key;
            this.value= value;
        }
    }

   static class DoubleList{
        private Node head, tail; // initial in the begin, new in the constructed method.
        private int size;
        DoubleList(){
            head = new Node(0,0);
            tail = new Node(0,0);
            head.next = tail;
            tail.pre = head;
            size = 0;
        }

        public void addFirst(Node x) {
            x.next = head.next;
            x.pre = head;
            head.next.pre = x;
            head.next = x;
            size ++;
        }



        public void remove(Node x) {
            x.pre.next = x.next;
            x.next.pre = x.pre;
            size --; //
        }

        public Node removeLast(){
            if (tail.pre == head){
                return null;
            }
            Node last = tail.pre;
            remove(last);
            return last;
        }

        public int getSize(){
            return size;
        }

    }


    static class LRUCache {
        private Map<Integer, Node> map;
        private  DoubleList cache;
        private int cap;

        public LRUCache(int capacity) {
            this.cap = capacity;
            map = new HashMap<>();
            cache = new DoubleList();

        }

        public int get(int key) {
            if (!map.containsKey(key))
                return -1;
            int val = map.get(key).value;
            put(key,val);
            return val;
        }

        public void put(int key, int value) {
            Node node = new Node(key,value);
            if (map.containsKey(key)){
                cache.remove(map.get(key));
                cache.addFirst(node);
                map.put(key,node);
            } else {
                if (cap == cache.getSize()){
                    Node last = cache.removeLast();
                    map.remove(last.key);
                }
                cache.addFirst(node);
                map.put(key,node);
            }
        }

    }

}
