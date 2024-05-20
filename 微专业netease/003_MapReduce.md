## 1.5 MapReduce编程
----------------------------------
###  一、演示Demo：WordCount程序
	位置：
	$HADOOP_HOME/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.3.jar
	命令：hadoop jar hadoop-mapreduce-examples-2.7.3.jar wordcount /input/data.txt /output/0225/wc

### 二、开发自己的WordCount程序
	1、（画图）非常重要：分析WordCount数据处理的过程（Map阶段+Reduce阶段）
![](imge/md-20240520105218.png)

	2、开发自己的Java程序，实现WordCount
		需要的jar包：
			$HADOOP_HOME/share/hadoop/common/*.jar
			$HADOOP_HOME/share/hadoop/common/lib/*.jar
			$HADOOP_HOME/share/hadoop/mapreduce/*.jar
			$HADOOP_HOME/share/hadoop/mapreduce/lib/*.jar
	
	3、举例：求每个部门的工资总额（类似SQL）
		SQL：select deptno,sum(sal)
			 from emp
			 group by deptno;
![](imge/md-20240520105427.png)
![](imge/md-20240520110019.png)

### 三、MapReduce的高级特性
	1、序列化：接口Writable（类似Java的序列化）
		功能：实现自定义的数据类型
		（1）举例：创建一个Employee类，封装员工数据，作为Map输出的value（v2，k2使用员工号）
		
	2、排序：默认的排序规则：数字---升序
							  字符串---按照字典顺序
							  对象：按照员工的薪水
			 按照K2进行排序
			 自定义排序排序规则：数字、字符串、对象（WritableComparable）
	3、分区：Partition（非常重要）
		默认：MapReduce只有一个分区（一个分区是一个输出文件）
		根据Map的输出建立分区<k2,v2>
		举例：根据员工的部门号建立分区
![](imge/md-20240520105842.png)

	4、Combiner合并，是一种特殊的Reduce；是MapReduce的一种优化的方式
		（1）有些情况不能使用Combiner：求平均值
		（2）不管有没有Combiner不能改变最后运行结果
		（3）不管有没有Combiner，都不应该改变原有的处理逻辑。（案例：倒排索引）
![](imge/md-20240520105320.png)
	5、MapReduce核心：Shuffle洗牌
![](imge/md-20240520105341.png)
### 四、编程案例
	1、数据去重：SQL的distinct语句
		相同的k2，他的v2会被同一个reduce处理
		举例：select distinct job from emp;
	
	2、多表查询
		（1）补充：SQL中的多表查询
				（*）什么是笛卡尔积？
				（*）连接条件：至少有N-1
				根据连接条件的不同，多表查询的类型
				（*）等值连接
				（*）不等值连接
				（*）外连接：左、右、全（在Flink DataSet API实现批处理）
				（*）自连接：只需要一张表
		（2）等值连接：查询员工信息，显示：员工号、姓名，薪水，部门名称
![](imge/md-20240520105711.png)

			SQL:select e.ename,d.dname
				from emp e,dept d
				where e.deptno=d.deptno;
						
			MapReduce：分析等值连接数据处理的流程
		
		
		（3）自连接：通过表的别名，将同一张表视为多张表
![](imge/md-20240520105734.png)

			查询员工信息，显示：老板名称、员工姓名
			SQL：
				select b.ename,e.ename
				from emp e,emp b
				where e.mgr=b.empno;
			
			MapReduce：
			
	3、倒排索引
![](imge/md-20240520105508.png)		

		准备测试数据
		  vi data01.txt
				I love Beijing and love Shanghai
		  		  
		  vi data02.txt
				I love China
		  
		  vi data03.txt
				Beijing is the capital of China
	

### **Hadoop MapReduce Shuffle**

Shuffle 过程中，提供数据的一端，被称作 Map 端，Map 端每个生成数据的任务称为 Mapper，对应的，接收数据的一端，被称作 Reduce 端，Reduce 端每个拉取数据的任务称为 Reducer，Shuffle 过程本质上都是将 Map 端获得的数据使用分区器进行划分，并将数据发送给对应的 Reducer 的过程，下面是官网提供的shuffle流程图：

![](https://pic1.zhimg.com/v2-b4e6ad4e243b93298bac53593becfb50_b.jpg)

1、map端的shuffle流程：

1）根据输入文件，形成文件**切片**规划(split),运行MapTask；

MapTask的并行度决定Map阶段的任务处理并发度，进而影响到整个Job的处理速度。一个job的Map阶段并行度由客户端在提交job时的切片书决定，每一个split切片分配一个MapTask处理。

2）**分区Partition**

在将map()函数处理后得到的（key,value）对写入到缓冲区之前，需要先进行分区操作，这样就能把map任务处理的结果发送给指定的reducer去执行，从而达到负载均衡，避免数据倾斜。MapReduce提供默认的分区类（HashPartitioner），我们也可以自定义分区，让其继承Partitioner类，并重写getPartition()方法，让其针对不同情况返回不同数值即可。并在最后通过job设置指定分区类和reducer任务数量即可。

```java
public class HashPartitioner<K, V> extends Partitioner<K, V> { 
/** Use {@link Object#hashCode()} to partition. */ 
	public int getPartition(K key, V value, int numReduceTasks) { 
		return (key.hashCode() & Integer.MAX_VALUE) % numReduceTasks; 
	}
 }
```

3)**写入环形内存缓冲区**

因为频繁的磁盘I/O操作会严重的降低效率，因此“中间结果”不会立马写入磁盘，而是优先存储到map节点的“环形内存缓冲区”，并做一些预排序以提高效率，当写入的数据量达到预先设置的阙值后便会执行一次I/O操作将数据写入到磁盘。每个map任务都会分配一个环形内存缓冲区，用于存储map任务输出的键值对（默认大小100MB，mapreduce.task.io.sort.mb调整）以及对应的partition，被缓冲的（key,value）对已经被序列化（为了写入磁盘）。

**4)执行溢写出spill**

一旦缓冲区内容达到阈值（mapreduce.map.io.sort.spill.percent,默认0.80，或者80%），就会会锁定这80%的内存，并在每个分区中对其中的键值对按键进行sort排序，具体是将数据按照partition和key两个关键字进行排序，排序结果为缓冲区内的数据按照partition为单位聚集在一起，同一个partition内的数据按照key有序。排序完成后会创建一个溢出写文件（临时文件），然后开启一个后台线程把这部分数据以一个临时文件的方式溢出写（spill）到本地磁盘中（如果客户端自定义了Combiner（相当于map阶段的reduce），则会在分区排序后到溢写出前自动调用combiner，将相同的key的value相加，这样的好处就是减少溢写到磁盘的数据量。这个过程叫“合并”，此过程不是默认流程，用户在需要时定义即可）。剩余的20%的内存在此期间可以继续写入map输出的键值对。溢出写过程按轮询方式将缓冲区中的内容写到mapreduce.cluster.local.dir属性指定的目录中。

**5)归并merge**

当一个map task处理的数据很大，以至于超过缓冲区内存时，就会生成多个spill文件。此时就需要对同一个map任务产生的多个spill文件进行归并生成最终的一个已分区且已排序的大文件。配置属性mapreduce.task.io.sort.factor控制着一次最多能合并多少流，默认值是10。这个过程包括排序和合并（可选），归并得到的文件内键值对有可能拥有相同的key，这个过程如果client设置过Combiner，也会合并相同的key值的键值对.

溢出写文件归并完毕后，Map将删除所有的临时溢出写文件，并告知NodeManager任务已完成，只要其中一个MapTask完成，ReduceTask就开始复制它的输出（Copy阶段分区输出文件通过http的方式提供给reducer）。

2、reduce端的shuffle流程：

**1）复制copy，拉取数据**

Reduce进程启动一些数据copy线程，通过HTTP方式请求MapTask所在的NodeManager以获取输出文件。 NodeManager需要为分区文件运行reduce任务。并且reduce任务需要集群上若干个map任务的map输出作为其特殊的分区文件。而每个map任务的完成时间可能不同，因此只要有一个任务完成，reduce任务就开始复制其输出。

reduce任务有少量复制线程，因此能够并行取得map输出。默认线程数为5，但这个默认值可以通过mapreduce.reduce.shuffle.parallelcopies属性进行设置。

**2）merge阶段，合并拉取来的小文件**

Copy过来的数据会先放入内存缓冲区中，如果内存缓冲区中能放得下这次数据的话就直接把数据写到内存中，即内存到内存merge。Reduce要向每个Map去拖取数据，在内存中每个Map对应一块数据，当内存缓存区中存储的Map数据占用空间达到一定程度的时候，开始启动内存中merge，把内存中的数据merge输出到磁盘上一个文件中，即**内存到磁盘merge**。与map端的溢写类似，在将buffer中多个map输出合并写入磁盘之前，如果设置了Combiner，则会化简压缩合并的map输出。Reduce的内存缓冲区可通过mapred.job.shuffle.input.buffer.percent配置，默认是JVM的heap size的70%。内存到磁盘merge的启动门限可以通过mapred.job.shuffle.merge.percent配置，默认是66%。

当属于该reducer的map输出全部拷贝完成，则会在reducer上生成多个文件（如果拖取的所有map数据总量都没有内存缓冲区，则数据就只存在于内存中），这时开始执行合并操作，即**磁盘到磁盘merge**，Map的输出数据已经是有序的，Merge进行一次合并排序，所谓Reduce端的sort过程就是这个合并的过程，采取的排序方法跟map阶段不同，因为每个map端传过来的数据是排好序的，因此众多排好序的map输出文件在reduce端进行合并时采用的是归并排序，针对键进行归并排序。一般Reduce是一边copy一边sort，即copy和sort两个阶段是重叠而不是完全分开的。最终Reduce shuffle过程会输出一个整体有序的数据块。

**3）reduce计算**

当一个reduce任务完成全部的复制和排序后，就会针对已根据键排好序的Key构造对应的Value迭代器。这时就要用到分组，默认的根据键分组，自定义的可是使用 job.setGroupingComparatorClass()方法设置分组函数类。对于默认分组来说，只要这个比较器比较的两个Key相同，它们就属于同一组，它们的 Value就会放在一个Value迭代器，而这个迭代器的Key使用属于同一个组的所有Key的第一个Key。

在reduce阶段，reduce()方法的输入是所有的Key和它的Value迭代器。此阶段的输出直接写到输出文件系统，一般为HDFS。如果采用HDFS，由于NodeManager也运行数据节点，所以第一个块副本将被写到本地磁盘。

**总结：**

**map端的Shuffle简述:**

1)input, 根据split输入数据，运行map任务;

2)patition, 每个map task都有一个内存缓冲区，存储着map的输出结果;

3)spill, 当缓冲区快满的时候需要将缓冲区的数据以临时文件的方式存放到磁盘;

4)merge, 当整个map task结束后再对磁盘中这个map task产生的所有临时文件做合并，生成最终的正式输出文件，然后等待reduce task来拉数据。

**reduce 端的Shuffle简述:**

reduce task在执行之前的工作就是不断地拉取当前job里每个map task的最终结果，然后对从不同地方拉取过来的数据不断地做merge，也最终形成一个文件作为reduce task的输入文件。

1) Copy过程，拉取数据。

2)Merge阶段，合并拉取来的小文件

3)Reducer计算

4)Output输出计算结果

参考文章：[MapReduce的shuffle过程详解（分片、分区、合并、归并。。。）\_ASN\_forever的博客-CSDN博客](https://link.zhihu.com/?target=https%3A//blog.csdn.net/ASN_forever/article/details/81233547)