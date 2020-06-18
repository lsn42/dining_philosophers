![image](https://github.com/lsn42/dining_philosophers/blob/master/image/running.png)
# 哲学家进餐
## 一、课程设计目的
掌握基本的同步与互斥算法，掌握进程并发执行的原理，及其所引起的同步、互斥问题的方法。
## 二、课程设计内容
自己编写信号量和 wait、signal 操作的模拟程序，然后用它们解决不死锁的哲学家问题或者读者-写者问题。
## 三、要求及提示
本题目必须单人完成。
### 解决不死锁的哲学家问题，要求把哲学家们的活动过程用文字或图形可视化形式表示出来。 
提示：首先设置一个“PCB”数组或队列，其中一个字段表示“阻塞原因兼阻塞标志”，本实验中，该数组有 5 个元素表示 5 个哲学家即可。它们随机提出申请以及进行“思考”和“吃”的行为。再设一个“筷子”数组。还需要设置哪些数据结构以及需要哪些字段自己考虑。
# 运行截图
## 发生死锁
![image](https://github.com/lsn42/dining_philosophers/blob/master/image/deadlock.png)
## 不会发生死锁
![image](https://github.com/lsn42/dining_philosophers/blob/master/image/nodeadlock.png)