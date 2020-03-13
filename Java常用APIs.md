### 
```java
    /**
    Linked List
    **/
    LinkedList<E> linkedList = new LinkedList<>();
    linkedList.add(int index, E e);
    linkedList.add(E e);
    linkedList.addAll(int index, Collection c);//从特定位置开始，将特定集合中的所有元素插入链表中
    linkedList.addAll(Collection c);
    linkedList.addFirst(E e);
    linkedList.addLast(E e);
    linkedList.clear();
    linkedList.clone();// shallow copy
    linkedList.contains(Object o);
    linkedList.offer(E e);
    linkedList.offerFirst(E e);
    linkedList.offerLast(E e);
    linkedList.peek();
    linkedList.peekFirst();
    linkedList.peekLast();
    linkedList.poll();
    linkedList.pollFirst();
    linkedList..pollLast();
    linkedList.pop();
    linkedList.remove();//remove the head
    linkedList.remove(int index);
    linkedList.remove(Object o);
    linkedList.removeFirst();
    linkedList.removeFirstOccurence(Object o);
    linkedList.removeLast();
    linkedList.removeLastOccurence(Object o);
    linkedList.size();
    linkedList.toArray();

    /**
    ArrayList和Array相互转换
    **/
    E[] arr = new E[]{};
    List list = Arrays.asList(arr);

    /**
    Stack
    **/
    Stack<E> stack = new Stack<>();
    stack.push(e);
    stack.pop();
    stack.peek();
    stack.empty();
    stack.search(e); //returns the position of the element from the top of the stack, or returns -1

    /**
    Queue
    **/
    Queue<E> queue = new LinkedList<>();
    Queue<E> queue = new LinkedBlockingQueue<>();
    Queue<E> queue = new ConcurrentLinkedDeque<>();
    Queue<E> queue = new ArrayDeque<>();    
    Comparator<String> comparator = new StringLengthComparator();
    PriorityQueue<String> queue = new PriorityQueue<String>(10, comparator);
    queue.add(e);     
    queue.remove();    
    queue.element();
    // vs
    queue.offer(e);
    queue.poll();
    queue.peek();
```

#### Lamda的comparator
```java
    //List.sort() since Java 8，无需再用Collections.sort()
	listDevs.sort(new Comparator<Developer>() {
		@Override
		public int compare(Developer o1, Developer o2) {
			return o2.getAge() - o1.getAge();
		}
	});	

    //lambda
	listDevs.sort((Developer o1, Developer o2)->o1.getSalary().compareTo(o2.getSalary()));
	
	//lambda
	listDevs.sort((o1, o2)->o1.getSalary().compareTo(o2.getSalary()));
```

#### 范型
> E - Element (在集合中使用，因为集合中存放的是元素)
> 
> T - Type（Java 类）
> 
> K - Key（键）
> 
> V - Value（值）
> 
> N - Number（数值类型）
> 
> ？ -  表示不确定的java类型
> 
> S、U、V  - 2nd、3rd、4th types