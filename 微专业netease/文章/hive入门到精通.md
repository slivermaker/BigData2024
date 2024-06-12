___

___

**往期好文推荐：**  
🔶🔷[Hadoop深入浅出 ——三大组件HDFS、MapReduce、Yarn框架结构的深入解析式地详细学习【建议收藏！！！】](https://blog.csdn.net/dazuo_001/article/details/120005451?spm=1001.2014.3001.5502)

🔶🔷[Redis从青铜到王者，从环境搭建到熟练使用，看这一篇就够了，超全整理详细解析，赶紧收藏吧！！！](https://blog.csdn.net/dazuo_001/article/details/119831069?spm=1001.2014.3001.5502)

🔶🔷[硬核整理四万字，学会数据库只要一篇就够了，盘它！MySQL基本操作以及常用的内置函数汇总整理](https://blog.csdn.net/dazuo_001/article/details/119570368?spm=1001.2014.3001.5502)

🔶🔷[Redis主从复制 以及 集群搭建 详细步骤解析，赶快收藏练手吧！](https://blog.csdn.net/dazuo_001/article/details/119909812?spm=1001.2014.3001.5502)

🔶🔷[Hadoop集群HDFS、YARN高可用HA详细配置步骤说明，附Zookeeper搭建详细步骤【建议收藏！！！】](https://blog.csdn.net/dazuo_001/article/details/120015347?spm=1001.2014.3001.5502)

🔶🔷[SQL进阶-深入理解MySQL，JDBC连接MySQL实现增删改查，赶快收藏吧！](https://blog.csdn.net/dazuo_001/article/details/119673544?spm=1001.2014.3001.5502)

🔶🔷[初识鸿蒙OS，你好，HarmonyOS！](https://blog.csdn.net/dazuo_001/article/details/119293978?spm=1001.2014.3001.5502)

🔶🔷[【小白学Java】D25 》》》Java中的各种集合大汇总，学习整理](https://blog.csdn.net/dazuo_001/article/details/118659209?spm=1001.2014.3001.5502)

🔶🔷[hadoop入门简介](https://blog.csdn.net/dazuo_001/article/details/117195482)

___

> **💜🧡💛制作不易，各位大佬们给点鼓励！**  
> **🧡💛💚点赞👍 ➕ 收藏⭐ ➕ 关注✅**  
> **💛💚💙欢迎各位大佬指教，一键三连走起！**

》》》本篇文章主要是与大家分享，Hive的一些常见操作，分区，分桶，窗口函数等等，以及Hive的HQL的使用练习，如有错误，烦请大佬指教。希望大家能够喜欢！

___

## 

**目录**

        [🧡一、了解Hive](https://blog.csdn.net/dazuo_001/article/details/120143603#1)  
                [💜1、Hive的概念及架构](https://blog.csdn.net/dazuo_001/article/details/120143603#11)  
                [💜2、Hive与传统数据库比较](https://blog.csdn.net/dazuo_001/article/details/120143603#12)  
                [💜3、Hive的数据存储格式](https://blog.csdn.net/dazuo_001/article/details/120143603#13)  
                [💜4、Hive操作客户端](https://blog.csdn.net/dazuo_001/article/details/120143603#14)  
        [🧡二、Hive的基本语法](https://blog.csdn.net/dazuo_001/article/details/120143603#2)  
                [💜1、Hive建表语法](https://blog.csdn.net/dazuo_001/article/details/120143603#21)  
                [💜2、Hive加载数据](https://blog.csdn.net/dazuo_001/article/details/120143603#22)  
                [💜3、Hive 内部表（Managed tables）vs 外部表（External tables）](https://blog.csdn.net/dazuo_001/article/details/120143603#23)  
                [💜4、Hive 分区](https://blog.csdn.net/dazuo_001/article/details/120143603#24)  
                [💜5、Hive动态分区](https://blog.csdn.net/dazuo_001/article/details/120143603#25)  
                💜[6、Hive分桶](https://blog.csdn.net/dazuo_001/article/details/120143603#26)  
                [💜7、Hive连接JDBC](https://blog.csdn.net/dazuo_001/article/details/120143603#27)  
        [🧡三、Hive的数据类型](https://blog.csdn.net/dazuo_001/article/details/120143603#3)  
                [💜1、基本数据类型](https://blog.csdn.net/dazuo_001/article/details/120143603#31)  
                [💜2、日期类型](https://blog.csdn.net/dazuo_001/article/details/120143603#32)  
                [💜3、复杂数据类型](https://blog.csdn.net/dazuo_001/article/details/120143603#33)  
        [🧡四、Hive HQL使用语法](https://blog.csdn.net/dazuo_001/article/details/120143603#4)  
                [💜1、HQL语法-DDL](https://blog.csdn.net/dazuo_001/article/details/120143603#41)  
                [💜2、HQL语法-DML](https://blog.csdn.net/dazuo_001/article/details/120143603#42)  
        [🧡五、Hive HQL使用注意](https://blog.csdn.net/dazuo_001/article/details/120143603#5)  
        [🧡六、Hive 的函数使用](https://blog.csdn.net/dazuo_001/article/details/120143603#6)  
                [💜1、Hive-常用函数](https://blog.csdn.net/dazuo_001/article/details/120143603#61)  
                        [💚（1）关系运算](https://blog.csdn.net/dazuo_001/article/details/120143603#611)  
                        [💚（2）数值计算](https://blog.csdn.net/dazuo_001/article/details/120143603#612)  
                        [💚（3） 条件函数](https://blog.csdn.net/dazuo_001/article/details/120143603#613)  
                        [💚（4）日期函数](https://blog.csdn.net/dazuo_001/article/details/120143603#614)  
                        [💚（5) 字符串函数](https://blog.csdn.net/dazuo_001/article/details/120143603#615)  
                💜[2、Hive-高级函数](https://blog.csdn.net/dazuo_001/article/details/120143603#62)  
                        [💚（1）窗口函数（开窗函数）：用户分组中开窗](https://blog.csdn.net/dazuo_001/article/details/120143603#621)  
                        [💚（2）Hive 行转列](https://blog.csdn.net/dazuo_001/article/details/120143603#622)  
                        [💚（3）Hive 列转行](https://blog.csdn.net/dazuo_001/article/details/120143603#623)  
                        [💚（4）Hive自定义函数UserDefineFunction](https://blog.csdn.net/dazuo_001/article/details/120143603#624)  
                                [⭕ UDF：一进一出](https://blog.csdn.net/dazuo_001/article/details/120143603#6241)  
                                [⭕UDTF：一进多出](https://blog.csdn.net/dazuo_001/article/details/120143603#6242)  
                                [⭕UDAF：多进一出](https://blog.csdn.net/dazuo_001/article/details/120143603#6243)  
                [💜3、Hive 中的wordCount](https://blog.csdn.net/dazuo_001/article/details/120143603#63)  
        [🧡七、Hive 的Shell使用](https://blog.csdn.net/dazuo_001/article/details/120143603#7)

___

## 

一、了解Hive

### 

1、Hive的概念及架构

                          [点我返回目录](https://blog.csdn.net/dazuo_001/article/details/120143603#a)

        Hive 是建立在 [Hadoop](https://so.csdn.net/so/search?q=Hadoop&spm=1001.2101.3001.7020) 上的`数据仓库`基础构架。它提供了一系列的工具，可以用来进行数据提取转化加载（ETL ），这是一种可以存储、查询和分析存储在 Hadoop 中的大规模数据的机制。Hive 定义了简单的类 SQL 查询语言，称为 HQL ，它允许熟悉 SQL 的用户查询数据。同时，这个语言也允许熟悉 MapReduce 的开发者开发自定义的 mapper 和 reducer 来处理内建的 mapper 和 reducer 无法完成的复杂的分析工作。**Hive是SQL解析引擎**，它将SQL语句转译成Map/Reduce Job然后在Hadoop执行。**Hive的表其实就是HDFS的目录**，按表名把文件夹分开。如果是分区表，则分区值是子文件夹，可以直接在Map/Reduce Job里使用这些数据。**Hive相当于hadoop的客户端工具**，部署时不一定放在集群管理节点中，也可以放在某个节点上。

> `数据仓库`，英文名称为`Data Warehouse`，可简写为DW或DWH。数据仓库，是为企业所有级别的决策制定过程，提供所有类型数据支持的战略集合。它出于分析性报告和决策支持目的而创建。为需要业务智能的企业，提供指导业务流程改进、监视时间、成本、质量以及控制。

> Hive的版本介绍：  
> 0.13和.14版本，稳定版本，但是不支持更新删除操作。  
> 1.2.1和1.2.2 版本，稳定版本，为Hive2版本（是主流版本）  
> 1.2.1的程序只能连接hive1.2.1 的hiveserver2

### 

2、Hive与传统数据库比较

                          [点我返回目录](https://blog.csdn.net/dazuo_001/article/details/120143603#a)

| 查询语言 | HiveQL | SQL |
| --- | --- | --- |
| 数据存储位置 | HDFS | Raw Device or 本地FS |
| 数据格式 | 用户定义 | 系统决定 |
| 数据更新 | 不支持（1.x以后版本支持） | 支持 |
| 索引 | 新版本有，但弱 | 有 |
| 执行 | MapReduce | Executor |
| 执行延迟 | 高 | 低 |
| 可扩展性 | 高 | 低 |
| 数据规模 | 大 | 小 |

1.  **查询语言**。类 SQL 的查询语言 HQL。熟悉 SQL 开发的开发者可以很方便的使用 Hive 进行开发。
2.  **数据存储位置**。所有 Hive 的数据都是存储在 HDFS 中的。而数据库则可以将数据保存在块设备或者本地文件系统中。
3.  **数据格式**。Hive 中没有定义专门的数据格式。而在数据库中，所有数据都会按照一定的组织存储，因此，数据库加载数据的过程会比较耗时。
4.  **数据更新**。Hive 对数据的改写和添加比较弱化，0.14版本之后支持，需要启动配置项。而数据库中的数据通常是需要经常进行修改的。
5.  **索引**。Hive 在加载数据的过程中不会对数据进行任何处理。因此访问延迟较高。数据库可以有很高的效率，较低的延迟。由于数据的访问延迟较高，决定了 Hive 不适合在线数据查询。
6.  **执行计算**。Hive 中执行是通过 MapReduce 来实现的而数据库通常有自己的执行引擎。
7.  **数据规模**。由于 Hive 建立在集群上并可以利用 MapReduce 进行并行计算，因此可以支持很大规模的数据；对应的，数据库可以支持的数据规模较小。

### 

3、Hive的数据存储格式

                          [点我返回目录](https://blog.csdn.net/dazuo_001/article/details/120143603#a)

-   **Hive的数据存储基于Hadoop HDFS。**
    
-   **Hive没有专门的数据文件格式**，常见的有以下几种：**TEXTFILE**、SEQUENCEFILE、AVRO、**RCFILE**、**ORCFILE**、**PARQUET**。
    

___

**下面我们详细的看一下Hive的常见数据格式：**

___

-   **TextFile:**  
             **TEXTFILE 即正常的文本格式，是Hive默认文件存储格式**，因为大多数情况下源数据文件都是以text文件格式保存（便于查看验数和防止乱码）。此种格式的表文件在HDFS上是明文，可用hadoop fs -cat命令查看，从HDFS上get下来后也可以直接读取。  
             **TEXTFILE 存储文件默认每一行就是一条记录，可以指定任意的分隔符进行字段间的分割。但这个格式无压缩，需要的存储空间很大。** 虽然可以结合Gzip、Bzip2、Snappy等使用，使用这种方式，Hive不会对数据进行切分，从而无法对数据进行并行操作。**一般只有与其他系统由数据交互的接口表采用TEXTFILE 格式，其他事实表和维度表都不建议使用。**
    
-   **RCFile:**  
            **Record Columnar的缩写。是Hadoop中第一个列文件格式。** **能够很好的压缩和快速的查询性能。**通常**写操作比较慢**，比非列形式的文件格式需要更多的内存空间和计算量。 **RCFile是一种行列存储相结合的存储方式。** 首先，其将数据按行分块，保证同一个record在一个块上，避免读一个记录需要读取多个block。其次，块数据列式存储，有利于数据压缩和快速的列存取。
    
-   **ORCFile:**  
            Hive从0.11版本开始提供了ORC的文件格式，**ORC文件**不仅仅是**一种列式文件存储格式**，最重要的是**有着很高的压缩比**，并且**对于MapReduce来说是可切分（Split）的**。因此，**在Hive中使用ORC作为表的文件存储格式，不仅可以很大程度的节省HDFS存储资源，而且对数据的查询和处理性能有着非常大的提升**，因为ORC较其他文件格式压缩比高，查询任务的输入数据量减少，使用的Task也就减少了。**ORC能很大程度的节省存储和计算资源，但它在读写时候需要消耗额外的CPU资源来压缩和解压缩**，当然这部分的CPU消耗是非常少的。
    
-   **Parquet:**  
             通常我们使用关系数据库存储结构化数据，而**关系数据库中使用数据模型都是扁平式的**，遇到诸如List、Map和自定义Struct的时候就需要用户在应用层解析。但是在大数据环境下，通常数据的来源是服务端的`埋点数据`，很可能需要把程序中的某些对象内容作为输出的一部分，而每一个对象都可能是嵌套的，所以如果能够原生的支持这种数据，这样在查询的时候就不需要额外的解析便能获得想要的结果。`Parquet的灵感来自于2010年Google发表的Dremel论文，文中介绍了一种支持嵌套结构的存储格式，并且使用了列式存储的方式提升查询性能。`**Parquet仅仅是一种存储格式，它是语言、平台无关的，并且不需要和任何一种数据处理框架绑定。**这也是parquet相较于orc的仅有**优势**：**支持嵌套结构**。Parquet 没有太多其他可圈可点的地方,比如他**不支持update操作(数据写成后不可修改),不支持ACID等.**
    
-   **SEQUENCEFILE:**  
             **SequenceFile是Hadoop API 提供的一种二进制文件，它将数据以<key,value>的形式序列化到文件中。** 这种二进制文件内部使用Hadoop 的标准的Writable 接口实现序列化和反序列化。它与Hadoop API中的MapFile 是互相兼容的。Hive 中的SequenceFile 继承自Hadoop API 的SequenceFile，不过它的key为空，使用value 存放实际的值， 这样是为了避免MR 在运行map 阶段的排序过程。 **SequenceFile支持三种压缩选择：NONE, RECORD, BLOCK。 Record压缩率低，一般建议使用BLOCK压缩。 SequenceFile最重要的优点就是Hadoop原生支持较好，有API**，但除此之外平平无奇，实际生产中不会使用。
    
-   **AVRO:**  
             **Avro是一种用于支持数据密集型的二进制文件格式。**它的**文件格式更为紧凑**，若要读取大量数据时，Avro能够提供更好的序列化和反序列化性能。并且**Avro数据文件天生是带Schema定义的**，所以它不需要开发者在API 级别实现自己的Writable对象。**Avro提供的机制使动态语言可以方便地处理Avro数据**。最近多个Hadoop 子项目都支持Avro 数据格式，如Pig 、Hive、Flume、Sqoop和Hcatalog。
    

> 其中的**TextFile**、**RCFile**、**ORC**、**Parquet**为Hive**最常用的四大存储格式**  
> 它们的 `存储效率及执行速度比较如下：`  
>     ORCFile存储文件读操作效率最高，耗时比较（ORC<Parquet<RCFile<TextFile）  
>     ORCFile存储文件占用空间少，压缩效率高(ORC<Parquet<RCFile<TextFile)

### 

4、Hive操作客户端

                          [点我返回目录](https://blog.csdn.net/dazuo_001/article/details/120143603#a)

**常用的客户端有两个：CLI，JDBC/ODBC**

-   CLI，即Shell命令行
    
-   JDBC/ODBC 是 Hive 的Java，与使用传统数据库JDBC的方式类似。
    

1.  **Hive 将元数据存储在数据库中(metastore)，目前只支持 mysql、derby。** **Hive 中的元数据包括表的名字，表的列和分区及其属性，表的属性（是否为外部表等），表的数据所在目录等；由解释器、编译器、优化器完成 HQL 查询语句从词法分析、语法分析、编译、优化以及查询计划（plan）的生成。生成的查询计划存储在 HDFS 中，并在随后由 MapReduce 调用执行。**
    
2.  **Hive 的数据存储在 HDFS 中，大部分的查询由 MapReduce 完成（包含 \* 的查询，比如 select \* from table 不会生成 MapRedcue 任务）**
    

**Hive的metastore**

> > **metastore是hive元数据的集中存放地。**  
> > **metastore默认使用内嵌的derby数据库作为存储引擎  
> > Derby引擎的缺点：一次只能打开一个会话  
> > 使用MySQL作为外置存储引擎，可以多用户同时访问\`**
> 
> [元数据库详解见：查看mysql SDS表和TBLS表](https://blog.csdn.net/haozhugogo/article/details/73274832)  
> 连接地址：https://blog.csdn.net/haozhugogo/article/details/73274832

## 

二、Hive的基本语法

### 

1、Hive建表语法



```sql
CREATE [EXTERNAL] TABLE [IF NOT EXISTS] table_name
(
    -- 定义字段名，字段类型
    col_name data_type [COMMENT col_comment],
    ...
)
-- 给表加上注解
[COMMENT table_comment]
-- 分区
[PARTITIONED BY (col_name data_type [COMMENT col_comment], ...)]
-- 分桶
[CLUSTERED BY (col_name, col_name, ...)
-- 设置排序字段 升序、降序
[SORTED BY (col_name [ASC|DESC], ...)]
INTO num_buckets BUCKETS]
[
    -- 指定设置行、列分隔符
    [ROW FORMAT row_format]
    -- 指定Hive储存格式：textFile、rcFile、SequenceFile 默认为：textFile
    [STORED AS file_format]
    | STORED BY 'storage.handler.class.name' [WITH SERDEPROPERTIES (...)]
    -- (注意：此功能在0.6.0版本及以上可用)
]
-- 指定储存位置
[LOCATION hdfs_path]
-- 跟外部表配合使用，比如：映射HBase表，然后可以使用HQL对hbase数据进行查询，当然速度比较慢
[TBLPROPERTIES (property_name=property_value, ...)]
-- (注意：此功能在0.6.0版本及以上可用)
[AS select_statement]
-- (注意：此功能在0.5.0版本及以上可用)

```

#### 建表格式1：全部使用默认建表方式



```sql
CREATE TABLE students (
    id BIGINT,
    name STRING,
    age INT,
    gender STRING,
    clazz STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','; -- 必选，指定列分隔符

```

#### 建表格式2：指定location （这种方式也比较常用）


```sql
CREATE TABLE students2 (
    id BIGINT,
    name STRING,
    age INT,
    gender STRING,
    clazz STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION '/input1'; -- 指定Hive表的数据的存储位置，一般在数据已经上传到HDFS，想要直接使用，会指定Location
                    -- 通常Location会跟外部表一起使用，内部表一般使用默认的location

```

#### 建表格式3：指定存储格式


```sql
CREATE TABLE students3 (
    id BIGINT,
    name STRING,
    age INT,
    gender STRING,
    clazz STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS RCFILE; -- 指定储存格式为RCFILE，inputFormat: RCFileInputFormat, outputFormat: RCFileOutputFormat
                  -- 如果不指定，默认为TEXTFILE
                  -- 注意：除TEXTFILE以外，其他的存储格式的数据都不能直接加载，需要使用从表加载的方式

```

#### 建表格式4：create table xxxx as select\_statement(SQL语句) (这种方式比较常用)

**注意：**

1.  新建表不允许是`外部表`。
2.  select后面表需要是已经存在的表，建表同时会加载数据。
3.  会启动mapreduce任务去读取源表数据写入新表

```sql
create table students4 as select * from students2;
```

#### 建表格式5：create table xxxx like table\_name 只想建表，不需要加载数据

```sql
create table students5 like students;
```

### 

2、Hive加载数据


#### 1)、使用`hdfs dfs -put '本地数据' 'hive表对应的HDFS目录下'`

#### 2)、使用 load data inpath

        **从hdfs导入数据**，路径可以是目录，会将目录下所有文件导入，但是文件格式必须一致

```sql
-- 将HDFS上的/input1目录下面的数据移动至students表对应的HDFS目录下
-- 注意是移动！移动！移动！
LOAD DATA INPATH '/input1/students.txt' INTO TABLE students;

```

```sql
// 清空表 truncate table students;
```

**从本地文件系统导入**

```sql
-- 加上 LOCAL 关键字可以将Linux本地目录下的文件上传到Hive表对应的HDFS目录下，原文件不会被删除
LOAD DATA LOCAL INPATH '/usr/local/soft/data/students.txt' INTO TABLE students;

-- 使用 OVERWRITE 关键字进行覆盖加载
LOAD DATA LOCAL INPATH '/usr/local/soft/data/students.txt' OVERWRITE INTO TABLE students;

```

#### 3)、create table xxx as SQL语句，表对表加载

#### 4)、insert into table xxxx SQL语句 （没有as），表对表加载:

```sql
-- 将students表的数据插入到students2
-- 这是复制，不是移动，students表中的数据不会丢失
INSERT INTO TABLE students2
SELECT * FROM students;

-- 覆盖插入，把 INTO 换成 OVERWRITE
INSERT OVERWRITE TABLE students2
SELECT * FROM students;

```

> **注意：**  
> 1，如果建表语句没有指定存储路径，不管是外部表还是内部表，存储路径都是会默认在hive/warehouse/xx.db/表名的目录下。  
> 加载的数据如果在HDFS上会移动到该表的存储目录下。注意是移动，不是复制  
> 2，删除外部表，文件不会删除，对应目录也不会删除

### 

3、Hive 内部表（Managed tables）vs 外部表（External tables）


**外部表和普通表的区别**

1.  **外部表**的路径可以自定义，**内部表**的路径需要在 hive/warehouse/目录下
2.  删除表后，普通表数据文件和表信息都删除。外部表仅删除表信息

#### 1)、建表语句：

```sql
-- 创建内部表
CREATE TABLE students_internal (
    id BIGINT,
    name STRING,
    age INT,
    gender STRING,
    clazz STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION '/input2';

-- 创建外部表
CREATE EXTERNAL TABLE students_external (
    id BIGINT,
    name STRING,
    age INT,
    gender STRING,
    clazz STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION '/input3';

```

#### 2)、加载数据：

```sql
hive> dfs -put /usr/local/soft/data/students.txt /input2/; 
hive> dfs -put /usr/local/soft/data/students.txt /input3/;
```

#### 3)、删除表：

```sql
-- 删除内部表students_internal
hive> DROP TABLE students_internal;
-- 数据文件被移动到回收站
-- Moved: 'hdfs://master:9000/input2' to trash at: hdfs://master:9000/user/root/.Trash/Current
-- OK
-- Time taken: 0.474 seconds

-- 删除外部表students_external
hive> DROP TABLE students_external;
-- 数据文件不会被删除
-- OK
-- Time taken: 0.09 seconds

```

> 1、可以看出，删除内部表的时候，表中的数据（HDFS上的文件）会被同表的元数据一起删除；删除外部表的时候，只会删除表的元数据，而不会删除表中的数据（HDFS上的文件）  
> 2、一般在公司中，使用外部表多一点，因为数据可以需要被多个程序使用，避免误删，通常外部表会结合location一起使用  
> 3、外部表还可以将其他数据源中的数据 映射到 hive中，比如说：hbase，ElasticSearch…  
> 4、设计外部表的初衷就是 让 表的元数据 与 数据 解耦

### 

4、Hive 分区


        分区表实际上是在表的目录下在以分区命名，建子目录；作用：进行分区裁剪，避免全表扫描，减少MapReduce处理的数据量，提高效率  
        一般在公司的hive中，所有的表基本上都是分区表，通常按日期分区、地域分区；分区表在使用的时候记得加上分区字段；分区也不是越多越好，一般不超过3级，根据实际业务衡量

> 分区的概念和分区表：  
> 分区表指的是在创建表时指定分区空间，实际上就是在hdfs上表的目录下再创建子目录。  
> 在使用数据时如果指定了需要访问的分区名称，则只会读取相应的分区，避免全表扫描，提高查询效率。

#### 1)、建立分区表：

```sql
-- 创建外部分区表students_pt1，按照pt字段进行分区
CREATE EXTERNAL TABLE students_pt1 (
    id BIGINT,
    name STRING,
    age INT,
    gender STRING,
    clazz STRING
)
PARTITIONED BY (pt STRING) -- 按照pt字段进行分区
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','; -- 指定字段分隔符

```

#### 2)、增加一个分区：

```sql
alter table students_pt1 add partition(pt='20210904');
```

#### 3)、删除一个分区：

```sql
alter table students_pt drop partition(pt='20210904');
```

#### 4)、查看某个表的所有分区

```sql
-- 推荐方式：直接从元数据中获取分区信息
SHOW PARTITIONS students_pt;

-- 不推荐方式：使用 SELECT DISTINCT 查询分区字段
SELECT DISTINCT pt FROM students_pt;

```

#### 5)、往分区中插入数据：

```sql
-- 将students表的数据插入到students_pt分区pt='20210902'中
INSERT INTO TABLE students_pt PARTITION(pt='20210902')
SELECT * FROM students;

-- 使用LOAD DATA将本地目录下的数据加载到students_pt分区pt='20210902'中
LOAD DATA LOCAL INPATH '/usr/local/soft/data/students.txt' INTO TABLE students_pt PARTITION(pt='20210902');

```

#### 6)、查询某个分区的数据：

```sql
-- 不推荐：全表扫描，效率低
SELECT COUNT(*) FROM students_pt;

-- 推荐：使用 WHERE 条件进行分区裁剪，避免了全表扫描，效率高
SELECT COUNT(*) FROM students_pt WHERE pt='20210101';

-- 也可以在 WHERE 条件中使用非等值判断
SELECT COUNT(*) FROM students_pt WHERE pt<='20210112' AND pt>='20210110';

```

### 

5、Hive动态分区

> 有的时候我们原始表中的数据里面包含了 ‘‘日期字段 dt’’，我们需要根据dt中不同的日期，分为不同的分区，将原始表改造成分区表。
> 
> `hive默认不开启动态分区`
> 
> `动态分区`：**根据数据中某几列的不同的取值 划分 不同的分区**

```sql
-- 开启动态分区
hive> SET hive.exec.dynamic.partition=true;

-- 设置动态分区模式：
-- strict：需要配合静态分区一起使用
-- nostrict：允许全部使用动态分区
hive> SET hive.exec.dynamic.partition.mode=nostrict;

-- 表示支持的最大的分区数量为1000，可以根据业务需要进行调整
hive> SET hive.exec.max.dynamic.partitions.pernode=1000;

-- 示例：使用动态分区插入数据
-- strict模式示例
-- 需要在partition部分指定一个静态分区值（例如'dt=anhui'），然后剩余的为动态分区
INSERT INTO TABLE students_pt PARTITION(dt='anhui', pt)
SELECT ..., pt FROM students;

```

#### 1)、建立原始表并加载数据

```sql
-- 创建表students_dt，包含一个dt字段用于分区
CREATE TABLE students_dt (
    id BIGINT,
    name STRING,
    age INT,
    gender STRING,
    clazz STRING,
    dt STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','; -- 指定字段分隔符

```

#### 2)、建立分区表并加载数据

```sql
create table students_dt_p ( id bigint, name string, age int, gender string, clazz string ) PARTITIONED BY(dt string) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',';
```

#### 3)、使用动态分区插入数据

```sql
-- 将数据插入到分区表students_dt_p中，分区字段需要放在SELECT的最后
-- 如果有多个分区字段，同理。它是按位置匹配，不是按名字匹配
INSERT INTO TABLE students_dt_p PARTITION(dt)
SELECT id, name, age, gender, clazz, dt FROM students_dt;

-- 下面这条语句会使用age作为分区字段，而不会使用students_dt中的dt作为分区字段
INSERT INTO TABLE students_dt_p PARTITION(dt)
SELECT id, name, age, gender, dt, age FROM students_dt;

```

#### 4)、多级分区

```sql
-- 创建表students_year_month，包含year和month字段用于分区
CREATE TABLE students_year_month (
    id BIGINT,
    name STRING,
    age INT,
    gender STRING,
    clazz STRING,
    year STRING,
    month STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','; -- 指定字段分隔符

-- 创建分区表students_year_month_pt，按照year和month字段进行分区
CREATE TABLE students_year_month_pt (
    id BIGINT,
    name STRING,
    age INT,
    gender STRING,
    clazz STRING
)
PARTITIONED BY (year STRING, month STRING) -- 指定分区字段
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','; -- 指定字段分隔符

-- 将数据从students_year_month插入到students_year_month_pt分区表中
-- 分区字段需要放在SELECT的最后
INSERT INTO TABLE students_year_month_pt PARTITION(year, month)
SELECT id, name, age, gender, clazz, year, month FROM students_year_month;

```

> 有关分区好文分享：[上单讲分区：https://developer.aliyun.com/article/81775](https://developer.aliyun.com/article/81775)

### 

6、Hive分桶



        **分桶实际上是对文件（数据）的进一步切分**；**Hive默认关闭分桶**；**分桶的作用**：在往分桶表中插入数据的时候，会根据 clustered by 指定的字段 进行hash分组 对指定的buckets个数 进行取余，进而可以将数据分割成buckets个数个文件，以达到数据均匀分布，可以解决Map端的“数据倾斜”问题，方便我们取抽样数据，提高Map join效率；**分桶字段** 需要根据业务进行设定

#### 1)、开启分桶开关

```sql
hive> set hive.enforce.bucketing=true;
```

#### 2)、建立分桶表

```sql
create table students_buks ( id bigint, name string, age int, gender string, clazz string ) CLUSTERED BY (clazz) into 12 BUCKETS ROW FORMAT DELIMITED FIELDS TERMINATED BY ',';
```

#### 3)、往分桶表中插入数据

```sql
-- 直接使用LOAD DATA并不能将数据打散到分桶中
LOAD DATA LOCAL INPATH '/usr/local/soft/data/students.txt' INTO TABLE students_buks;

-- 需要使用下面这种方式插入数据，才能使分桶表真正发挥作用
INSERT INTO students_buks
SELECT * FROM students;

```

> Hive关于分桶好文分享， [Hive分桶表的使用场景以及优缺点分析:https://zhuanlan.zhihu.com/p/93728864](https://zhuanlan.zhihu.com/p/93728864)

### 

7、Hive连接JDBC



#### 1)、启动hiveserver2的服务

```shell
hive --service hiveserver2 &
```

#### 2)、 新建maven项目并添加两个依赖

```xml
<!-- 添加Hadoop Common依赖 -->
<dependency>
    <groupId>org.apache.hadoop</groupId>
    <artifactId>hadoop-common</artifactId>
    <version>2.7.6</version>
</dependency>

<!-- 添加Hive JDBC依赖 -->
<!-- 更多信息请参见：https://mvnrepository.com/artifact/org.apache.hive/hive-jdbc -->
<dependency>
    <groupId>org.apache.hive</groupId>
    <artifactId>hive-jdbc</artifactId>
    <version>1.2.1</version>
</dependency>

```

#### 3)、 编写JDBC代码

```java
import java.sql.*;

public class HiveJDBC {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 加载Hive JDBC驱动
        Class.forName("org.apache.hive.jdbc.HiveDriver");

        // 建立连接
        Connection conn = DriverManager.getConnection("jdbc:hive2://master:10000/test3");

        // 创建Statement对象
        Statement stat = conn.createStatement();

        // 执行查询语句，获取结果集
        ResultSet rs = stat.executeQuery("SELECT * FROM students LIMIT 10");

        // 处理结果集
        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int age = rs.getInt(3);
            String gender = rs.getString(4);
            String clazz = rs.getString(5);
            System.out.println(id + "," + name + "," + age + "," + gender + "," + clazz);
        }

        // 关闭资源
        rs.close();
        stat.close();
        conn.close();
    }
}

```

## 

三、Hive的数据类型

### 

1、基本数据类型


#### 数值型：

```bash
TINYINT — 微整型，只占用1个字节，只能存储0-255的整数。

SMALLINT — 小整型，占用2个字节，存储范围 -32768 到 32767。

INT — 整型，占用4个字节，存储范围 -2147483648 到 2147483647。

BIGINT — 长整型，占用8个字节，存储范围 -2^63 到 2^63-1。

```

#### 布尔型

```bash
BOOLEAN — TRUE/FALSE
```

#### 浮点型

```bash
FLOAT– 单精度浮点数。 DOUBLE– 双精度浮点数。
```

#### 字符串型

```bash
STRING– 不设定长度。
```

### 

2、日期类型

-   时间戳 timestamp
-   日期 date

```sql
create table testDate( ts timestamp ,dt date ) row format delimited fields terminated by ','; // 2021-01-14 14:24:57.200,2021-01-11
```

-   时间戳与时间字符串转换

```sql
-- from_unixtime函数：传入一个时间戳以及pattern，可以将时间戳转换成对应格式的字符串
SELECT from_unixtime(1630915221, 'yyyy年MM月dd日 HH时mm分ss秒'); 
-- 输出示例：'2021年09月07日 11时00分21秒'

-- unix_timestamp函数：传入一个时间字符串以及pattern，可以将字符串按照pattern转换成时间戳
SELECT unix_timestamp('2021年09月07日 11时00分21秒', 'yyyy年MM月dd日 HH时mm分ss秒'); 
-- 输出示例：1630915221

-- unix_timestamp函数：传入一个时间字符串（没有指定pattern），将字符串转换成时间戳
SELECT unix_timestamp('2021-01-14 14:24:57.200'); 
-- 输出示例：1610634297（不含毫秒）

```

### 

3、复杂数据类型



**主要有三种复杂数据类型：Structs，Maps，Arrays** ，[可以参考：https://blog.csdn.net/woshixuye/article/details/53317009](https://blog.csdn.net/woshixuye/article/details/53317009)

## 

四、Hive HQL使用语法



> 我们知道`SQL语言可以分为5大类`：  
> (1）DDL(Data Definition Language) 数据定义语言  
> 用来定义数据库对象：数据库，表，列等。  
> 关键字：create，drap,alter等  
> ( 2）DML(Data Manipulation Language) 数据操作语言  
> 用来对数据库中表的数据进行增删改。  
> 关键字：insert,delete,update等  
> ( 3）DQL(Data Query Language)数据查询语言  
> 用来查询数据库表的记录（数据）。  
> 关键字：select,where 等  
> ( 4）DCL(Data Control Language) 数据控制语言  
> 用来定义数据库的访问权限和安全级别，及创建用户。  
> 关键字：GRANT，REVOKE等  
> (5)TCL(Transaction Control Language) 事务控制语言  
> T CL经常被用于快速原型开发、脚本编程、GUI和测试等方面，  
> 关键字: commit、rollback等。

### 

1、HQL语法-DDL



```sql
创建数据库 create database xxxxx; 查看数据库 show databases； 删除数据库 drop database tmp; 强制删除数据库：drop database tmp cascade; 查看表：SHOW TABLES； 查看表的元信息： desc test_table; describe extended test_table; describe formatted test_table; 查看建表语句：show create table table_XXX 重命名表： alter table test_table rename to new_table; 修改列数据类型：alter table lv_test change column colxx string; 增加、删除分区： alter table test_table add partition (pt=xxxx) alter table test_table drop if exists partition(...);
```

### 

2、HQL语法-DML



```sql
where 用于过滤，分区裁剪，指定条件 join 用于两表关联，left outer join ，join，mapjoin（1.2版本后默认开启） group by 用于分组聚合，通常结合聚合函数一起使用 order by 用于全局排序，要尽量避免排序，是针对全局排序的，即对所有的reduce输出是有序的 sort by :当有多个reduce时，只能保证单个reduce输出有序，不能保证全局有序 cluster by = distribute by + sort by distinct 去重
```

> `order by、distribute by、sort by、cluster by详解`  
> [文章链接：Hive中order、sort、distribute、cluster by区别与联系 https://zhuanlan.zhihu.com/p/93747613](https://zhuanlan.zhihu.com/p/93747613)

## 

五、Hive HQL使用注意



1.  [count(\*)、count(1) 、count(‘字段名’) 的区别](https://blog.csdn.net/Jame_s/article/details/102926383)
    
2.  HQL 执行优先级：  
    `from、where、 group by 、having、order by、join、select 、limit`
    
3.  where 条件里不支持不等式子查询，实际上是支持 in、not in、exists、not exists
    
4.  hive中大小写不敏感
    
5.  在hive中，数据中如果有null字符串，加载到表中的时候会变成 null （不是字符串）  
    `如果需要判断 null，使用 某个字段名 is null 这样的方式来判断;或者使用 nvl() 函数，不能 直接 某个字段名 == null`
    
6.  使用explain查看SQL执行计划
    

## 

六、Hive 的函数使用

                          [点我返回目录](https://blog.csdn.net/dazuo_001/article/details/120143603#a)

### 

1、Hive-常用函数



#### 

（1）关系运算


```sql
-- 等值比较运算符
-- = : 等于
-- == : 等于（功能与=相同）
-- <=> : null安全等于（当比较的任一方为NULL时返回true）

-- 不等值比较运算符
-- != : 不等于
-- <> : 不等于（功能与!=相同）

-- 区间比较
SELECT * FROM default.students
WHERE id BETWEEN 1500100001 AND 1500100010;

-- 空值/非空值判断
-- is null : 判断是否为空
-- is not null : 判断是否不为空
-- nvl() : 用于替换NULL值，例如 nvl(col, 'default_value')
-- isnull() : 判断是否为空（与is null功能相同）

-- 模糊匹配
-- LIKE : 用于匹配简单的字符串模式，例如 'a%' 匹配以'a'开头的字符串
-- RLIKE : 用于匹配正则表达式模式，等价于 REGEXP
-- REGEXP : 用于匹配正则表达式模式，例如 'a.*' 匹配以'a'开头的任意长度的字符串
-- 等值比较
SELECT * FROM default.students
WHERE id = 1500100001;

-- 不等值比较
SELECT * FROM default.students
WHERE id != 1500100001;

-- 区间比较
SELECT * FROM default.students
WHERE id BETWEEN 1500100001 AND 1500100010;

-- 空值判断
SELECT * FROM default.students
WHERE name IS NULL;

-- 非空值判断
SELECT * FROM default.students
WHERE name IS NOT NULL;

-- 使用nvl函数
SELECT id, nvl(name, 'unknown') AS name FROM default.students;

-- 模糊匹配LIKE
SELECT * FROM default.students
WHERE name LIKE 'John%';

-- 正则匹配RLIKE或REGEXP
SELECT * FROM default.students
WHERE name RLIKE '^John.*';
SELECT * FROM default.students
WHERE name REGEXP '^John.*';

```

> [Hive中rlike,like,not like，regexp区别与使用详解](https://blog.csdn.net/qq_26442553/article/details/79452221)

#### 

（2）数值计算

                          [点我返回目录](https://blog.csdn.net/dazuo_001/article/details/120143603#a)

```sql
取整函数(四舍五入)：round 向上取整：ceil 向下取整：floor
```

#### 

（3） 条件函数


-   if： if(表达式,如果表达式成立的返回值,如果表达式不成立的返回值)

```sql
select if(1>0,1,0); select if(1>0,if(-1>0,-1,1),0);
```

-   COALESCE

```sql
-- COALESCE函数：从左往右依次匹配，直到遇到第一个非空值为止

-- 示例1：所有值从左到右依次匹配，返回第一个非空值 '1'
SELECT COALESCE(NULL, '1', '2'); 
-- 输出：'1'

-- 示例2：所有值从左到右依次匹配，返回第一个非空值 '1'
SELECT COALESCE('1', NULL, '2'); 
-- 输出：'1'

```

-   case when … then … else … end

```sql
-- 根据分数对学生进行评分
-- 注意条件的顺序，CASE语句从上到下依次匹配，匹配到第一个符合条件的分支后就会停止匹配

SELECT score,
    CASE
        WHEN score > 120 THEN '优秀'
        WHEN score > 100 THEN '良好'
        WHEN score > 90 THEN '及格'
        ELSE '不及格'
    END AS pingfen
FROM default.score
LIMIT 20;

```

#### 

（4）日期函数



```sql
-- 将Unix时间戳转换为指定格式的字符串
SELECT from_unixtime(1610611142, 'YYYY/MM/dd HH:mm:ss'); 
-- 输出示例：'2021/01/14 14:25:42'

-- 将当前Unix时间戳转换为指定格式的字符串
SELECT from_unixtime(unix_timestamp(), 'YYYY/MM/dd HH:mm:ss'); 
-- 输出示例：当前时间，格式为'YYYY/MM/dd HH:mm:ss'

-- 将字符串'2021年01月14日'转换为'2021-01-14'
SELECT from_unixtime(unix_timestamp('2021年01月14日', 'yyyy年MM月dd日'), 'yyyy-MM-dd'); 
-- 输出示例：'2021-01-14'

-- 将字符串"04牛2021数加16逼"转换为"2021/04/16"
SELECT from_unixtime(unix_timestamp('04牛2021数加16逼', 'MM牛yyyy数加dd逼'), 'yyyy/MM/dd'); 
-- 输出示例：'2021/04/16'

```

#### 

（5) 字符串函数



```sql
-- concat函数：连接字符串
SELECT concat('123', '456'); 
-- 输出：'123456'

-- 当concat函数遇到NULL时，结果为NULL
SELECT concat('123', '456', null); 
-- 输出：NULL

-- concat_ws函数：带分隔符的字符串连接，自动忽略NULL
SELECT concat_ws('#', 'a', 'b', 'c'); 
-- 输出：'a#b#c'

SELECT concat_ws('#', 'a', 'b', 'c', NULL); 
-- 输出：'a#b#c'

-- 使用concat_ws连接学生表的字段，并指定分隔符"|"
SELECT concat_ws('|', cast(id AS STRING), name, cast(age AS STRING), gender, clazz) 
FROM students 
LIMIT 10;

-- substring函数：截取字符串，从位置1开始计数
SELECT substring('abcdefg', 1); 
-- 输出：'abcdefg'

-- 将日期格式从'2021/01/14'转换为'2021-01-14'
SELECT concat_ws('-', substring('2021/01/14', 1, 4), substring('2021/01/14', 6, 2), substring('2021/01/14', 9, 2)); 
-- 输出：'2021-01-14'

-- split函数：拆分字符串为数组
SELECT split('abcde,fgh', ','); 
-- 输出：['abcde', 'fgh']

-- 获取拆分后的数组的第三个元素（位置从0开始计数）
SELECT split('a,b,c,d,e,f', ',')[2]; 
-- 输出：'c'

-- explode函数：将数组中的每个元素转换成一行
SELECT explode(split('abcde,fgh', ',')); 
-- 输出：'abcde'
-- 输出：'fgh'

-- 解析JSON格式的数据，获取指定路径的值
SELECT get_json_object('{"name":"zhangsan","age":18,"score":[{"course_name":"math","score":100},{"course_name":"english","score":60}]}', '$.score[0].score'); 
-- 输出：'100'

```

### 

2、Hive-高级函数


#### 

（1）窗口函数（开窗函数）：用户分组中开窗



        在sql中有一类函数叫做`聚合函数`,例如sum()、avg()、max()等等,这类函数可以将多行数据按照规则聚集为一行,一般来讲聚集后的行数是要少于聚集前的行数的.但是有时我们想要既显示聚集前的数据,又要显示聚集后的数据,这时我们便引入了窗口函数。（开创函数，我们一般用于分组中求 TopN问题）

> 好文分享，[Hive窗口函数](https://blog.csdn.net/qq_26937525/article/details/54925827)

样例演示：

```sql
数据： 111,69,class1,department1 112,80,class1,department1 113,74,class1,department1 114,94,class1,department1 115,93,class1,department1 121,74,class2,department1 122,86,class2,department1 123,78,class2,department1 124,70,class2,department1 211,93,class1,department2 212,83,class1,department2 213,94,class1,department2 214,94,class1,department2 215,82,class1,department2 216,74,class1,department2 221,99,class2,department2 222,78,class2,department2 223,74,class2,department2 224,80,class2,department2 225,85,class2,department2 建表： create table new_score( id int ,score int ,clazz string ,department string ) row format delimited fields terminated by ",";
```

##### row\_number()：无并列排名

```sql
使用格式： select xxxx, row_number() over(partition by 分组字段 order by 排序字段 desc) as rn from tb group by xxxx
```

##### dense\_rank()：有并列排名，并且依次递增

##### rank()：有并列排名，不依次递增

##### percent\_rank()：(rank的结果-1)/(分区内数据的个数-1)

##### cume\_dist()：计算某个窗口或分区中某个值的累积分布。

> 假定升序排序，则使用以下公式确定累积分布： 小于等于当前值x的行数 / 窗口或partition分区内的总行数。其中，x 等于 order by 子句中指定的列的当前行中的值。

##### NTILE(n)：对分区内数据再分成n组，然后打上组号

##### max()、min()、avg()、count()、sum()等函数：是基于每个partition分区内的数据做对应的计算

#### 窗口帧：用于从分区中选择指定的多条记录，供窗口函数处理

 

> Hive 提供了两种定义窗口帧的形式：`ROWS` 和 `RANGE`。两种类型都需要配置上界和下界。  
> 例如，`ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW` 表示选择分区起始记录到当前记录的所有行；  
> `SUM(close) RANGE BETWEEN 100 PRECEDING AND 200 FOLLOWING` 则通过 _字段差值_ 来进行选择。  
> 如当前行的 `close` 字段值是 `200`，那么这个窗口帧的定义就会选择分区中 `close` 字段值落在 `100` 至 `400` 区间的记录。  
> 以下是所有可能的窗口帧定义组合。如果没有定义窗口帧，则默认为 `RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW`。  
> 注意：窗口帧只能运用在max、min、avg、count、sum、FIRST\_VALUE、LAST\_VALUE这几个窗口函数上

**测试1：**

```sql
SELECT id ,score ,clazz ,SUM(score) OVER w as sum_w ,round(avg(score) OVER w,3) as avg_w ,count(score) OVER w as cnt_w FROM new_score WINDOW w AS (PARTITION BY clazz ORDER BY score rows between 2 PRECEDING and 2 FOLLOWING);
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/118668d9c4dc42d5a22e438b69500312.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5YyX5oWV6L6w,size_20,color_FFFFFF,t_70,g_se,x_16)

**测试2：**

```sql

```

![在这里插入图片描述](https://img-blog.csdnimg.cn/c221f0dbd0f5418082f16169b7fef12f.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5YyX5oWV6L6w,size_20,color_FFFFFF,t_70,g_se,x_16)

###### LAG(col,n)：往前第n行数据

###### LEAD(col,n)：往后第n行数据

###### FIRST\_VALUE：取分组内排序后，截止到当前行，第一个值

###### LAST\_VALUE：取分组内排序后，截止到当前行，最后一个值，对于并列的排名，取最后一个

**测试3：**

```sql
SELECT
    id,
    score,
    clazz,
    department,
    
    -- row_number()：在分区内为每一行分配唯一的递增编号
    row_number() OVER (PARTITION BY clazz ORDER BY score DESC) AS rn_rk,
    
    -- dense_rank()：在分区内为每一行分配排名，相同的值具有相同的排名，不跳过排名
    dense_rank() OVER (PARTITION BY clazz ORDER BY score DESC) AS dense_rk,
    
    -- rank()：在分区内为每一行分配排名，相同的值具有相同的排名，排名会跳过
    rank() OVER (PARTITION BY clazz ORDER BY score DESC) AS rk,
    
    -- percent_rank()：在分区内计算每一行的百分比排名（按百分比进行排名）
    percent_rank() OVER (PARTITION BY clazz ORDER BY score DESC) AS percent_rk,
    
    -- cume_dist()：计算分区内每一行的累计分布，四舍五入到小数点后三位
    ROUND(cume_dist() OVER (PARTITION BY clazz ORDER BY score DESC), 3) AS cume_rk,
    
    -- NTILE(3)：将分区内的行分配到3个桶中
    NTILE(3) OVER (PARTITION BY clazz ORDER BY score DESC) AS ntile_num,
    
    -- max()：在分区内计算指定范围内的最大值（包括前3行和后11行）
    MAX(score) OVER (PARTITION BY clazz ORDER BY score DESC RANGE BETWEEN 3 PRECEDING AND 11 FOLLOWING) AS max_p
    
FROM
    new_score;

```

![在这里插入图片描述](https://img-blog.csdnimg.cn/088ac08d5c0d4f6f8a8bf615d4049f41.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5YyX5oWV6L6w,size_20,color_FFFFFF,t_70,g_se,x_16)

#### 

（2）Hive 行转列

```sql
使用关键字： lateral view explode
```

样例演示：

```sql
CREATE TABLE testArray2 (
    name STRING,
    weight ARRAY<STRING>
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
COLLECTION ITEMS TERMINATED BY ',';
孙悟空    "150","170","180"
唐三藏    "150","180","190"

```

![在这里插入图片描述](https://img-blog.csdnimg.cn/a15b21214ef7445ca7e078e13af756b2.png)

```sql
select name,col1 from testarray2 lateral view explode(weight) t1 as col1;
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/7a78133b13a24b75a61a104712534530.png)

```sql
select key from (select explode(map('key1',1,'key2',2,'key3',3)) as (key,value)) t;
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/e02192d875e147b8a66f95101c4ea368.png)

```sql
select name,col1,col2 from testarray2 lateral view explode(map('key1',1,'key2',2,'key3',3)) t1 as col1,col2;
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/0adbc62b5a09402ea45bc1576ec18254.png)

```sql
select name,pos,col1 from testarray2 lateral view posexplode(weight) t1 as pos,col1;
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/a96ee387942b4942a63f404bcbeb4ed0.png)

#### 

（3）Hive 列转行



```sql
孙悟空    150
孙悟空    170
孙悟空    180
唐三藏    150
唐三藏    180
唐三藏    190

-- 创建表
CREATE TABLE testLieToLine (
    name STRING,
    col1 INT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t';

-- 加载数据
LOAD DATA LOCAL INPATH '/your/local/path/test_data.txt' INTO TABLE testLieToLine;



```

测试1：

```sql
select name,collect_list(col1) from testLieToLine group by name;
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/66124e78e07d4802bcbfc76a3f551b32.png)  
测试2：

```sql
select t1.name ,collect_list(t1.col1) from ( select name ,col1 from testarray2 lateral view explode(weight) t1 as col1 ) t1 group by t1.name;
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/30acf810c20d40b59547766c317bcfe1.png)

#### 

（4）Hive自定义函数UserDefineFunction


##### 

⭕ UDF：一进一出

-   创建maven项目，并加入依赖

```xml
<dependency>
    <groupId>org.apache.hive</groupId>
    <artifactId>hive-exec</artifactId>
    <version>1.2.1</version>
</dependency>

```

-   编写代码，继承org.apache.hadoop.hive.ql.exec.UDF，实现evaluate方法，在evaluate方法中实现自己的逻辑

```java
import org.apache.hadoop.hive.ql.exec.UDF;

public class HiveUDF extends UDF {
    // hadoop => #hadoop$
    public String evaluate(String col1) {
        // 给传进来的数据 左边加上 # 右边加上 $
        String result = "#" + col1 + "$";
        return result;
    }
}

```

-   打成jar包并上传至Linux虚拟机(小北路径：/usr/local/soft/jars/)
-   在hive shell中，使用 `add jar 路径`将jar包作为资源添加到hive环境中

```sql
add jar /usr/local/soft/jars/HiveUDF2-1.0.jar;
```

-   使用jar包资源注册一个临时函数，fxxx1是你的函数名，'MyUDF’是主类名

```sql
create temporary function fxxx1 as 'MyUDF';
```

-   使用函数名处理数据

```sql
select fxx1(name) as fxx_name from students limit 10; 
#施笑槐$ #吕金鹏$ #单乐蕊$ #葛德曜$ #宣谷芹$ #边昂雄$ #尚孤风$ #符半双$ #沈德昌$ #羿彦昌$
```

##### 

⭕UDTF：一进多出


```bash
样例数据： "key1:value1,key2:value2,key3:value3" key1 value1 key2 value2 key3 value3
```

###### 方法一：使用 explode+split

```sql
SELECT 
    SPLIT(t.col1, ":")[0],
    SPLIT(t.col1, ":")[1] 
FROM 
    (SELECT 
        EXPLODE(SPLIT("key1:value1,key2:value2,key3:value3", ",")) AS col1
    ) t;

```

###### 方法二：自定UDTF

```java
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import java.util.ArrayList;

public class HiveUDTF extends GenericUDTF {

    // 初始化方法，指定输出的列名及类型
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        ArrayList<String> fieldNames = new ArrayList<String>();
        ArrayList<ObjectInspector> fieldObjInspectors = new ArrayList<ObjectInspector>();

        // 添加输出列的名称和类型
        fieldNames.add("col1");
        fieldObjInspectors.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldNames.add("col2");
        fieldObjInspectors.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        // 返回结构化的对象检查器
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldObjInspectors);
    }

    // 处理输入数据的逻辑
    public void process(Object[] objects) throws HiveException {
        // objects 表示传入的N列
        String col = objects[0].toString(); // 获取传入的数据列

        // 按逗号分割每个键值对
        String[] splits = col.split(",");
        for (String str : splits) {
            String[] cols = str.split(":"); // 再按冒号分割键和值
            forward(cols); // 将分割后的键值对作为一行输出
        }
    }

    // 在UDTF结束时调用的方法
    public void close() throws HiveException {
        // 可选的关闭逻辑
    }
}

```

SQL:

```sql
select my_udtf("key1:value1,key2:value2,key3:value3");
```

###### 举例说明：

> 字段：id,col1,col2,col3,col4,col5,col6,col7,col8,col9,col10,col11,col12  
> 共13列
> 
> 数据：
> 
> a,1,2,3,4,5,6,7,8,9,10,11,12
> 
> b,11,12,13,14,15,16,17,18,19,20,21,22
> 
> c,21,22,23,24,25,26,27,28,29,30,31,32
> 
> 转成3列：id,hours,value
> 
> 例如：
> 
> a,1,2,3,4,5,6,7,8,9,10,11,12
> 
> a,0时,1
> 
> a,2时,2
> 
> a,4时,3
> 
> a,6时,4
> 
> …

```sql
CREATE TABLE udtfData (
    id STRING,
    col1 STRING,
    col2 STRING,
    col3 STRING,
    col4 STRING,
    col5 STRING,
    col6 STRING,
    col7 STRING,
    col8 STRING,
    col9 STRING,
    col10 STRING,
    col11 STRING,
    col12 STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ',';

```

java代码：

```java
import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import java.util.ArrayList;

public class HiveUDTF2 extends GenericUDTF {

    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        ArrayList<String> fieldNames = new ArrayList<String>();
        ArrayList<ObjectInspector> fieldObjInspectors = new ArrayList<ObjectInspector>();
        
        // 添加输出列的名称和类型
        fieldNames.add("col1");
        fieldObjInspectors.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        fieldNames.add("col2");
        fieldObjInspectors.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        // 返回结构化的对象检查器
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldObjInspectors);
    }

    public void process(Object[] objects) throws HiveException {
        int hours = 0;
        for (Object obj : objects) {
            hours++; // 计算处理的列数，用于生成时间标签
            String col = obj.toString(); // 获取传入的数据
            ArrayList<String> cols = new ArrayList<String>();
            cols.add(hours + "时"); // 添加时间标签
            cols.add(col); // 添加数据列
            forward(cols); // 将处理后的数据输出
        }
    }

    public void close() throws HiveException {
        // 可选的关闭逻辑
    }
}

```

添加jar资源:

```sql
add jar /usr/local/soft/HiveUDF2-1.0.jar;
```

注册udtf函数：

```sql
create temporary function my_udtf as 'MyUDTF';
```

SQL:

```sql
select id ,hours ,value from udtfData lateral view my_udtf(col1,col2,col3,col4,col5,col6,col7,col8,col9,col10,col11,col12) t as hours,value ;
```

##### 

⭕UDAF：多进一出



> 好文分享： [hive自定义函数学习](https://www.cnblogs.com/lrxvx/p/10974341.html)

### 

3、Hive 中的wordCount



```sql
-- 创建表
CREATE TABLE words (
    words STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '|';

-- 插入数据
INSERT INTO words VALUES 
('hello,java,hello,java,scala,python'),
('hbase,hadoop,hadoop,hdfs,hive,hive'),
('hbase,hadoop,hadoop,hdfs,hive,hive');

```

![在这里插入图片描述](https://img-blog.csdnimg.cn/ad3c0012969c4bd983411509a8cf03bf.png)

```sql
select word,count(*) from (select explode(split(words,',')) word from words) a group by a.word;
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/b60d4d11e6c5441b98a4aa48a8fc962d.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5YyX5oWV6L6w,size_20,color_FFFFFF,t_70,g_se,x_16)

## 

七、Hive 的Shell使用


### 第一种shell

```shell
hive -e "select * from test03.students limit 10"
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/82b14c9e788f4ee690ed8db9f51b6295.png?x-oss-process=image/watermark,type_ZHJvaWRzYW5zZmFsbGJhY2s,shadow_50,text_Q1NETiBA5YyX5oWV6L6w,size_20,color_FFFFFF,t_70,g_se,x_16)

### 第二种shell

```shell
hive -f hql文件路径 # 将HQL写在一个文件里，再使用 -f 参数指定该文件
```