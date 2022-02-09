```java
 //Design。设计job listing filter by city的feature，不难。考察用什么数据库，sql or nosql，怎么建index等等。
1: confirm system
2: list functional and non functional.

1: user can input keyword. of job.
2: keyword of city
3: login and save search history. 
4: type ahead and srawler

non functional.
HA: 99.99 54 minus/per year. 99.999 
scalablity: 数据上去了 性能不下降。 


------------------------------- 5 minus---------
Traffic est: 1m  10times storage/qps, average size 10 = 20bye*1m *10 = 200mb
/* x KB * y Million users = xy GB (Ex: 1 KB data (1000 bytes) * 1 million user 
(1,000,000) = 1,000,000,000 = 1 GB of storage)
x MB * y Million users = xy TB
Single Char  = 2 bytes.
Long or double = 8 bytes.
Image or photo
Average photos = 200 KB
Good photos = 2 MB
Videos
Average 2 MB in posts etc.,
Standard videos for streaming = 50 MB per minute of video*/
---------------2 minus------------------------------------------
graph

keyword, (jobid, jobid2) 
city +_ = (job id, )
city + job keyword = (job id)

jobid, keyword,, details, expired.postby, city.

solr, elastic search
lucence 

map reduce real time.
count min sketch 
geohash


//系统设计 实现简历的upload 和 retrieve。like dropbox. 

//设计一个ads系统，支持创建AD，并且能在用户搜索某个地区的job时候，返回一个AD。策略是使利润最大化。recomand system

// 设计一个提供广告的service 已知用户买了多少条广告每条广告多少钱，然后如何做到利益最大.
10 user, a 2,10, = 4 
// read heavy,--cache. write heavy-- queue. write back, write through,  write around
// 系统设计是设计一个click tracking system，需要根据click算每个sponsored client还剩多少budget以及每个月应该bill sponsored client多少钱.

```
