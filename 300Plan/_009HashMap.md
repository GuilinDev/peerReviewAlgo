### HashMap
键值存储，每个键值对叫一个entry，得到值需要知道键，之所以叫HashMap是因为该数据结构使用了Hashing的技术，将一个较大的字符串转换成一个较小的字符串，以此方便对数据进行索引和快速查找

需要注意的点：
* HashMap的默认长度和loading factor
* 哈希冲突
* Hashs数组+链表结构解决冲突问题O(n) vs 平衡树结构解决同key查找问题O(logn)
* 高并发下死锁问题
