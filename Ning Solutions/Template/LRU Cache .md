```python
# Python  
class LRUCache(object):  
	def __init__(self, capacity):  
		self.dic = collections.OrderedDict()  
		self.remain = capacity 
	def get(self, key):  
		if key not in self.dic:  
			return -1  
		v = self.dic.pop(key)  
		self.dic[key] = v   # key as the newest one  
		return v  
	def put(self, key, value):  
		if key in self.dic:  
			self.dic.pop(key)  
		else:  
			if self.remain > 0:  
				self.remain -= 1  
			else:   # self.dic is full 
				self.dic.popitem(last=False)  
		self.dic[key] = value 
```
```c++
//C/C++ 
struct CacheNode { 
    int key, value; 
    CacheNode *pre, *next; 
       
    CacheNode(int key_ = 0, int value_ = 0)  
        : key(key_), value(value_), pre(NULL), next(NULL) {} 
}; 
class LRUCache { 
public: 
    LRUCache(int capacity)  
        : _capacity(capacity), _head(new CacheNode()), _tail(_head) {} 
     
    int get(int key) { 
        auto it = _cache.find(key); 
        if (it == _cache.end()) return -1; 
         
        moveToTail(it->second); 
        return it->second->value; 
    } 
     
    void put(int key, int value) { 
        auto it = _cache.find(key); 
         
        if (it != _cache.end()) { 
            it->second->value = value; 
            moveToTail(it->second); 
        } 
        else if ((int)_cache.size() < _capacity) { 
            auto node = new CacheNode(key, value); 
            addToTail(node);           
            _cache[key] = node; 
        } 
        else { 
            // Reuse existing node 
            _cache.erase(_head->next->key); 
            moveToTail(_head->next); 
            _tail->key = key; _tail->value = value; 
            _cache[key] = _tail; 
        } 
    } 
     
    ~LRUCache() { 
        auto pCurr = _head; 
        while (pCurr != NULL) { 
            auto next = pCurr->next; 
            delete pCurr; 
            pCurr = next; 
        } 
    } 
     
private: 
    const int _capacity; 
    CacheNode *const _head, *_tail; 
    unordered_map<int, CacheNode*> _cache; 
     
    void moveToTail(CacheNode *node) { 
        if (node == _tail) return; 
         
        node->pre->next = node->next; 
        node->next->pre = node->pre; 
         
        addToTail(node); 
    } 
     
    void addToTail(CacheNode *node) { 
        node->next = _tail->next; 
        _tail->next = node; 
        node->pre = _tail; 
        _tail = node;                    
    } 
}; 
```
```java
/** 
 * 使用 哈希表 + 双端链表 实现 
 */ 
class LinkedNode { 
  constructor(key = 0, val = 0) { 
    this.key = key 
    this.val = val 
    this.prev = null 
    this.next = null 
  } 
} 

class LinkedList { 
  constructor() { 
    this.head = new LinkedNode() 
    this.tail = new LinkedNode() 
    this.head.next = this.tail 
    this.tail.prev = this.head 
  } 

  insertFirst(node) { 
    node.next = this.head.next 
    node.prev = this.head 
    this.head.next.prev = node 
    this.head.next = node 
  } 

  remove(node) { 
    node.prev.next = node.next 
    node.next.prev = node.prev 
  } 

  removeLast() { 
    if (this.tail.prev === this.head) return null 
    let last = this.tail.prev 
    this.remove(last) 
    return last 
  } 
} 

/** 
 * @param {number} capacity 
 */ 
var LRUCache = function(capacity) { 
  this.capacity = capacity 
  this.keyNodeMap = new Map() 
  this.cacheLink = new LinkedList() 
}; 

/**  
 * @param {number} key 
 * @return {number} 
 */ 
LRUCache.prototype.get = function(key) { 
  if (!this.keyNodeMap.has(key)) return -1 
  let val = this.keyNodeMap.get(key).val 
  this.put(key, val) 
  return val 
}; 

/**  
 * @param {number} key  
 * @param {number} value 
 * @return {void} 
 */ 
LRUCache.prototype.put = function(key, value) { 
  let size = this.keyNodeMap.size 
  if (this.keyNodeMap.has(key)) { 
    this.cacheLink.remove(this.keyNodeMap.get(key));  
    --size  
  } 
  if (size >= this.capacity) { 
    this.keyNodeMap.delete(this.cacheLink.removeLast().key) 
  } 
  let node = new LinkedNode(key, value) 
  this.keyNodeMap.set(key, node) 
  this.cacheLink.insertFirst(node) 
}; 
```

```java
class LRUCache { 
    /** 
     * 缓存映射表 
     */ 
    private Map<Integer, DLinkNode> cache = new HashMap<>(); 
    /** 
     * 缓存大小 
     */ 
    private int size; 
    /** 
     * 缓存容量 
     */ 
    private int capacity; 
    /** 
     * 链表头部和尾部 
     */ 
    private DLinkNode head, tail; 
    public LRUCache(int capacity) { 
        //初始化缓存大小，容量和头尾节点 
        this.size = 0; 
        this.capacity = capacity; 
        head = new DLinkNode(); 
        tail = new DLinkNode(); 
        head.next = tail; 
        tail.prev = head; 
    } 
    /** 
     * 获取节点 
     * @param key 节点的键 
     * @return 返回节点的值 
     */ 
    public int get(int key) { 
        DLinkNode node = cache.get(key); 
        if (node == null) { 
            return -1; 
        } 
        //移动到链表头部 
         (node); 
        return node.value; 
    } 
    /** 
     * 添加节点 
     * @param key 节点的键 
     * @param value 节点的值 
     */ 
    public void put(int key, int value) { 
        DLinkNode node = cache.get(key); 
        if (node == null) { 
            DLinkNode newNode = new DLinkNode(key, value); 
            cache.put(key, newNode); 
            //添加到链表头部 
            addToHead(newNode); 
            ++size; 
            //如果缓存已满，需要清理尾部节点 
            if (size > capacity) { 
                DLinkNode tail = removeTail(); 
                cache.remove(tail.key); 
                --size; 
            } 
        } else { 
            node.value = value; 
            //移动到链表头部 
            moveToHead(node); 
        } 
    } 
    /** 
     * 删除尾结点 
     * 
     * @return 返回删除的节点 
     */ 
    private DLinkNode removeTail() { 
        DLinkNode node = tail.prev; 
        removeNode(node); 
        return node; 
    } 
    /** 
     * 删除节点 
     * @param node 需要删除的节点 
     */ 
    private void removeNode(DLinkNode node) { 
        node.next.prev = node.prev; 
        node.prev.next = node.next; 
    } 
    /** 
     * 把节点添加到链表头部 
     * 
     * @param node 要添加的节点 
     */ 
    private void addToHead(DLinkNode node) { 
        node.prev = head; 
        node.next = head.next; 
        head.next.prev = node; 
        head.next = node; 
    } 
    /** 
     * 把节点移动到头部 
     * @param node 需要移动的节点 
     */ 
    private void moveToHead(DLinkNode node) { 
        removeNode(node); 
        addToHead(node); 
    } 
    /** 
     * 链表节点类 
     */ 
    private static class DLinkNode { 
        Integer key; 
        Integer value; 
        DLinkNode prev; 
        DLinkNode next; 
        DLinkNode() { 
        } 
        DLinkNode(Integer key, Integer value) { 
            this.key = key; 
            this.value = value; 
        } 
    } 
} 
```

```javascript
// JavaScript 
class LRUCache { 
  constructor(capacity) { 
    this.capacity = capacity; 
    this.cache = new Map(); 
  } 
  get(key) { 
    if (!this.cache.has(key)) return -1; 
     
    let value = this.cache.get(key); 
    this.cache.delete(key); 
    this.cache.set(key, value); 
  } 
  put(key, value) { 
    if (this.cache.has(key)) { 
      this.cache.delete(key); 
    } else { 
      if (this.cache.size >= this.capacity) { 
        // Map 中新 set 的元素会放在后面 
        let firstKey = this.cache.keys().next(); 
        this.cache.delete(firstKey.value); 
      } 
    } 
    this.cache.set(key, value); 
  } 
} 
```
