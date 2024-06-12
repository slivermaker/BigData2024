

## 启动与停止MySQL服务：

启动

sudo /etc/init.d/mysqlstart

或者

sudo service mysqlstart

停止

sudo /etc/init.d/mysqlstop

或者

sudo service mysqlstop

重启

sudo service mysqlrestart

查看状态

sudo/etc/init.d/mysqlstatus

## 验证是否安装成功与连接
mysql-V
连接
指定socket文件和用户名、密码
mysql-S/tmp/mysql.sock -uroot -p
指定IP和端口
mysql-h127.0.0.1 -P3306 -uroot -p
连接进去之后
数据库状态
status;
展示当前连接showprocesslist;


详细教程：  https://www.w3school.com.cn/sql/index.asp

## 练习

```sql
#查看当前有哪些数据库
show databases;
#使用名为test的数据库
use test;
#创建一张学生表
create table stu(
id int(10), name varchar(20),
age int(10),
primary key(id));
#每一张表都需要包含一个主键，主键唯一标识一条记录，唯一的字段，不可重复不能为空，通过`primary key`关键字来定义。
#查看创建好的表
show create table stu;
#新加一个字段
alter table stu add column gender varchar(20);
#修改一个字段
alter table stu modify column gender varchar(40);
#删除一个字段
alter table stu drop column gender;
#删除表
drop table stu;
#查看当前数据库中的表
show tables;
#向表中插入数据
insert into stu(id,name,age) values(1,'pw',28);
#插入全部字段时可以只写表名
insert into stu values(2,'nss',29);
1
#查看刚才添加的数据,"*"代表查询全部字段
select * fromstu;
#如果只想查询两个字段，则只写要查询的字段名
select name,agefromstu;
#也可以根据某个条件进行查询，比如只查询id为1的记录
select nameagefromstuwhereid=1;
#更新语句
updatestu setage=29 whereid=1;
#删除
deletefromstuwhereid=1
```


新手入门SQL，强烈推荐MICK的《SQL基础教程》。这本书逻辑清晰，直白易懂，介绍了SQL所有的基础语句。掌握了这本书中的内容，就可以利用SQL进行简单的数据分析了。

![](https://pic4.zhimg.com/v2-3fb06b31fd8606e9ff99c242404706bf_b.jpg)

《SQL基础教程》

在这篇文章里我对《SQL基础教程》中的几乎所有的SQL基础语句进行了总结和摘抄，面试前可以把这些SQL语句集中记忆一遍。下一篇文章我会教大家如何利用这篇文章中提到的SQL基础语句进行数据分析的实操。欢迎大家关注我的专栏~

___

**首先，什么是SQL？**

SQL是Structured Query Language的缩写，意思是结构化查询语言，是一种在数据库管理系统（Relational Database Management System, RDBMS）中查询数据，或通过RDBMS对数据库中的数据进行更改的语言。

常见的RDBMS有：

-   Oracle Database：甲骨文公司的RDBMS
-   SQL Server ：微软公司的RDBMS
-   DB2：IBM 公司的RDBMS
-   PostgreSQL：开源的RDBMS
-   MySQL ：开源的RDBMS

不同RDBMS的SQL语言略有不同，由于MySQL是开源的，免费容易获取，国内很多公司用的都是MySQL，所以本篇文章汇总的是MySQL的SQL语言。

使用SQL在RDBMS中查询数据的过程是这样的：

![](https://pic3.zhimg.com/v2-af08aabdda37bcdd640ce936283f6a9e_b.jpg)

图片来自《SQL基础教程》

用户在客户端通过SQL语言，将需要的数据和对数据进行的操作的请求发送给RDBMS，RDBMS 根据该语句的内容返回所请求的数据，或者对存储在数据库中的数据进行更新。

根据对RDBMS 赋予的指令种类的不同，SQL 语句可以分为以下三类。

**●DDL（Data Definition Language，数据定义语言）**

用来创建或者删除存储数据用的数据库以及数据库中的表等对象。DDL 包含以下几种指令。

CREATE： 创建数据库和表等对象

DROP： 删除数据库和表等对象

ALTER： 修改数据库和表等对象的结构

**●DML（Data Manipulation Language，数据操纵语言）**

用来查询或者变更表中的记录。DML 包含以下几种指令。

SELECT：查询表中的数据

INSERT：向表中插入新数据

UPDATE：更新表中的数据

DELETE：删除表中的数据

**●DCL（Data Control Language，数据控制语言）**

用来确认或者取消对数据库中的数据进行的变更。除此之外，还可以对RDBMS 的用户是否有权限操作数据库中的对象（数据库表等）进行设定。DCL 包含以下几种指令。

COMMIT： 确认对数据库中的数据进行的变更

ROLLBACK： 取消对数据库中的数据进行的变更

GRANT： 赋予用户操作权限

REVOKE： 取消用户的操作权限

下面就让我们具体看看这三类语句分别包括哪些基础语句吧！

## **Ⅰ. DDL（Data Definition Language，数据定义语言）**

**1、 创建数据库(CREATE)**

**2、创建表(CREATE)**

```sql
CREATE TABLE Product (product_id CHAR(4) NOT NULL, product_name VARCHAR(100) NOT NULL, product_type VARCHAR(32) NOT NULL, sale_price INTEGER , purchase_price INTEGER , regist_date DATE , PRIMARY KEY (product_id));
```

每一列的数据类型（后述）是必须要指定的，数据类型包括：

-   INTEGER 整数型
-   NUMERIC ( 全体位数, 小数位数)
-   CHAR 定长字符串
-   VARCHAR 可变长字符串
-   DATE 日期型

**3、 删除表(DROP)**

**4、表定义的更新(ALTER)**

-   在表中增加一列(ADD COLUMN)

```sql
ALTER TABLE Product ADD COLUMN product_name_pinyin VARCHAR(100);
```

-   在表中删除一列(DROP COLUMN)

```sql
ALTER TABLE Product DROP COLUMN product_name_pinyin;
```

-   变更表名(RENAME)

```sql
RENAME TABLE Poduct to Product;
```

## **Ⅱ. DML（Data Manipulation Language，数据操纵语言）**

**1、向表中插入数据(INSERT)**

-   包含列清单

```sql
INSERT INTO Product (product_id, product_name, product_type, sale_price, purchase_price, regist_date) VALUES ('0001', 'T恤衫', '衣服', 1000, 500, '2009-09-20');
```

-   省略列清单

```sql
START TRANSACTION; 
INSERT INTO Product VALUES ('0001', 'T恤衫', '衣服', 1000, 500, '2009-09-20');
INSERT INTO Product VALUES ('0002', '打孔器', '办公用品', 500, 320, '2009-09-11');
INSERT INTO Product VALUES ('0003', '运动T恤', '衣服', 4000, 2800, NULL);
INSERT INTO Product VALUES ('0004', '菜刀', '厨房用具', 3000, 2800, '2009-09-20'); 
INSERT INTO Product VALUES ('0005', '高压锅', '厨房用具', 6800, 5000, '2009-01-15'); 
INSERT INTO Product VALUES ('0006', '叉子', '厨房用具', 500, NULL, '2009-09-20'); 
INSERT INTO Product VALUES ('0007', '擦菜板', '厨房用具', 880, 790, '2008-04-28'); 
INSERT INTO Product VALUES ('0008', '圆珠笔', '办公用品', 100, NULL,'2009-11-11'); 
COMMIT;
```

-   从其他表中复制数据

```text
INSERT INTO ProductCopy (product_id, product_name, product_type，sale_price, purchase_price, regist_date) SELECT product_id, product_name, product_type, sale_price, purchase_price, regist_date FROM Product;
```

-   INSERT 语句中的SELECT 语句，也可以使用WHERE 子句或者GROUP BY 子句等。

```text
INSERT INTO ProductType (product_type, sum_sale_price, sum_purchase_price) SELECT product_type, SUM(sale_price), SUM(purchase_price) FROM Product GROUP BY product_type;
```

**2、从表中查询出需要的列(SELECT)**

```text
SELECT product_id, product_name, purchase_price FROM Product;
```

-   查询出所有的列

-   为列设定别名(AS)

```text
SELECT product_id AS id, product_name AS name, purchase_price AS “价格” FROM Product;
```

-   将查询出的一列指定为常数

```text
SELECT ‘2009-02-24’ AS date, product_id, product_name FROM Product;
```

-   从查询结果中删除重复行(DISTINCT)

```text
SELECT DISTINCT product_type FROM Product;
```

**3、指定查询的条件(WHERE)**

```text
SELECT product_name, product_type FROM Product; WHERE product_type = '衣服';
```

**4、算数运算符和比较运算符**

-   算数运算符

加 +

减 -

乘 \*

除 /

注意：所有包含NULL 的计算，结果肯定是NULL。

```text
SELECT product_name, sale_price, sale_price * 2 AS "sale_price_x2" FROM Product;
```

-   比较运算符

等于 =

不等于 <>

大于 >

大于等于 >=

小于 <

小于等于 <=

```text
SELECT product_name, product_type, regist_date FROM Product WHERE regist_date < '2009-09-27';
```

-   将算数运算符和比较运算符结合使用：

```text
SELECT product_name, sale_price, purchase_price FROM Product WHERE sale_price - purchase_price >= 500;
```

注意：不能对NULL使用比较运算符，正确的方法是：

```text
SELECT product_name, purchase_price FROM Product WHERE purchase_price IS NULL; SELECT product_name, purchase_price FROM Product WHERE purchase_price IS NOT NULL;
```

**5、逻辑运算符(NOT、AND、OR)**

-   NOT

```text
SELECT product_name, product_type, sale_price FROM Product WHERE NOT sale_price >= 1000;
```

（也就是sale\_price<1000）

-   AND

AND运算符在其两侧的查询条件都成立时整个查询条件才成立，其意思相当于“并且”。

```text
SELECT product_name, purchase_price FROM Product WHERE product_type = '厨房用具' AND sale_price >= 3000;
```

-   OR

运算符在其两侧的查询条件有一个成立时整个查询条件都成立，其意思相当于“或者”。

```text
SELECT product_name, purchase_price FROM Product WHERE product_type = '厨房用具' OR sale_price >= 3000;
```

**6、对表进行聚合查询**

常用的五个聚合函数：

-   COUNT： 计算表中的记录数（行数）
-   SUM： 计算表中数值列中数据的合计值
-   AVG： 计算表中数值列中数据的平均值
-   MAX： 求出表中任意列中数据的最大值
-   MIN： 求出表中任意列中数据的最小值

-   计算全部数据的行数（包含NULL）

```text
SELECT COUNT(*) FROM Product;
```

-   计算某一列的行数（不包含NULL）

```text
SELECT COUNT(purchase_price) FROM Product;
```

-   计算删除重复数据后的行数

```text
SELECT COUNT(DISTINCT product_type) FROM Product;
```

（所有的聚合函数都可以使用DISTINCT）

-   SUM/AVG函数只能对数值类型的列使用，而MAX/MIN函数原则上可以适用于任何数据类型的列

```text
SELECT MAX(regist_date), MIN(regist_date) FROM Product;
```

**7、对表进行分组(GROUP BY)**

```text
SELECT product_type, COUNT(*) FROM Product GROUP BY product_type;
```

-   GROUP BY和WHERE并用时SELECT语句的执行顺序：

FROM → WHERE → GROUP BY → SELECT

```text
SELECT purchase_price, COUNT(*) FROM Product WHERE product_type = '衣服' GROUP BY purchase_price;
```

-   为聚合结果指定条件(HAVING)

```text
SELECT product_type, COUNT(*) FROM Product GROUP BY product_type HAVING COUNT(*) = 2;
```

**8、对查询结果进行排序(ORDER BY)**

-   **子句的书写顺序**

**SELECT → FROM → WHERE → GROUP BY → HAVING → ORDER BY**

**子句的执行顺序：**

**FROM → WHERE → GROUP BY → HAVING → SELECT → ORDER BY**

```text
SELECT product_id, product_name, sale_price, purchase_price FROM Product ORDER BY sale_price;
```

-   升序(ASC)或降序(DESC)

```text
SELECT product_id, product_name, sale_price, purchase_price FROM Product ORDER BY sale_price DESC;
```

注意：默认升序

**9、数据的删除(DELETE)**

-   清空表

-   指定删除对象（搜索型DELETE）

```text
DELETE FROM Product WHERE sale_price >= 4000;
```

**10、数据的更新(UPDATE)**

-   更新整列

```text
UPDATE Product SET regist_date = '2009-10-10';
```

-   指定条件的更新（搜索型UPDATE）

```text
UPDATE Product SET sale_price = sale_price * 10 WHERE product_type = '厨房用具';
```

-   多列更新

```text
UPDATE Product SET sale_price = sale_price * 10, purchase_price = purchase_price / 2 WHERE product_type = '厨房用具';
```

**11、视图**

-   创建视图(CREATE VIEW)

```text
CREATE VIEW ProductSum (product_type, cnt_product) AS SELECT product_type, COUNT(*) FROM Product GROUP BY product_type;
```

注意：定义视图时不能使用ORDER BY子句

-   使用视图

```text
SELECT product_type, cnt_product FROM ProductSum;
```

-   删除视图(DROP VIEW)

**12、子查询（一次性视图）**

```text
-- 在FROM子句中直接书写定义视图的SELECT语句 SELECT product_type, cnt_product FROM ( SELECT product_type, COUNT(*) AS cnt_product FROM Product GROUP BY product_type ) AS ProductSum;
```

-   标量子查询

在WHERE子句中使用标量子查询

```text
SELECT product_id, product_name, sale_price FROM Product WHERE sale_price > (SELECT AVG(sale_price) FROM Product);
```

注意：能够使用常数或者列名的地方，无论是SELECT 子句、GROUP BY 子句、HAVING 子句，还是ORDER BY 子句，几乎所有的地方都可以使用标量子查询。

-   关联子查询

```text
SELECT product_type, product_name, sale_price FROM Product AS P1 WHERE sale_price > (SELECT AVG(sale_price) FROM Product AS P2 WHERE P1.product_type = P2.product_type GROUP BY product_type);
```

这里起到关键作用的就是在子查询中添加的WHERE 子句的条件。该条件的意思就是，在同一商品种类中对各商品的销售单价和平均单价进行比较。

**13、函数**

函数大致可以分为以下几种。

-   算术函数（用来进行数值计算的函数）
-   字符串函数（用来进行字符串操作的函数）
-   日期函数（用来进行日期操作的函数）
-   转换函数（用来转换数据类型和值的函数）
-   聚合函数（用来进行数据聚合的函数）

-   算数函数

ABS (数值) —— 绝对值

MOD (被除数, 除数) —— 求余

ROUND (对象数值, 保留小数的位数) —— 四舍五入

-   字符串函数

CONCAT (字符串1, 字符串2, 字符串3) —— 拼接

LENGTH (字符串) —— 字符串长度

LOWER (字符串) —— 小写

UPPER (字符串) —— 大写

REPLACE (对象字符串，替换前的字符串，替换后的字符串) —— 替换

SUBSTRING（对象字符串 FROM 截取的起始位置 FOR 截取的字符数）—— 截取

-   日期函数

CURRENT\_DATE —— 当前日期

CURRENT\_TIME —— 当前时间

CURRENT\_TIMESTAMP —— 当前的日期和时间

EXTRACT (日期元素 FROM 日期)

-   转换函数

CAST（转换前的值 AS 想要转换的数据类型）—— 类型转换

COALESCE (数据1，数据2，数据3……) —— 将NULL转换为其他值

**14、谓词**

-   LIKE谓词

前方一致查询：

```text
SELECT * FROM SampleLike WHERE strcol LIKE 'ddd%';
```

也可用\_（下划线）代替%，但\_只能代表一个字符

```text
SELECT * FROM SampleLike WHERE strcol LIKE 'abc_';
```

中间一致查询：

```text
SELECT * FROM SampleLike WHERE strcol LIKE '%ddd%';
```

后方一致查询：

```text
SELECT * FROM SampleLike WHERE strcol LIKE '%ddd';
```

-   BETWEEN谓词

```text
SELECT product_name, sale_price FROM Product WHERE sale_price BETWEEN 100 AND 1000;
```

BETWEEN 的特点就是结果中会包含100 和1000 这两个临界值。

-   IS NULL和IS NOT NULL谓词

为了选取出某些值为NULL 的列的数据，不能使用=，而只能使用特定的谓词IS NULL

```text
SELECT product_name, purchase_price FROM Product WHERE purchase_price IS NULL;
```

-   IN谓词

```text
SELECT product_name, purchase_price FROM Product WHERE purchase_price IN (320, 500, 5000);
```

也可以用NOT IN

```text
SELECT product_name, purchase_price FROM Product WHERE purchase_price NOT IN (320, 500, 5000);
```

注意：在使用IN 和NOT IN 时是无法选取出NULL 数据的。

使用子查询作为IN谓词的参数：

```text
SELECT product_name, sale_price FROM Product WHERE product_id IN (SELECT product_id FROM ShopProduct WHERE shop_id = '000C');
```

-   EXIST谓词

```text
SELECT product_name, sale_price FROM Product AS P WHERE EXISTS (SELECT * FROM ShopProduct AS SP WHERE SP.shop_id = '000C' AND SP.product_id = P.product_id);
```

也可以用NOT EXIST

**15、CASE表达式**

```text
SELECT product_name, CASE WHEN product_type = '衣服' THEN CONCAT('A:', product_type) WHEN product_type = '办公用品' THEN CONCAT('B:', product_type) WHEN product_type = '厨房用具' THEN CONCAT('C:',product_type) ELSE NULL END AS abc_product_type FROM Product;
```

**16、表的加减法**

-   表的加法(UNION)

```text
SELECT product_id, product_name FROM Product UNION SELECT product_id, product_name FROM Product2;
```

通过UNION 进行并集运算时可以使用任何形式的SELECT 语句，WHERE、GROUP BY、HAVING 等子句都可以使用，但是ORDER BY 只能在最后使用一次。

注意：UNION会删去两个表中的重复记录。如果想保留重复记录，可以在UNION后面加ALL

-   选取表中的公共部分(INTERSECT)

MySQL不支持INTERSECT

-   表的减法(EXCEPT)

MySQL不支持EXCEPT

**17、以列为单位对表进行联结(JOIN)**

-   内联结(INNER JOIN)

```text
SELECT SP.shop_id, SP.shop_name, SP.product_id, P.product_name, P.sale_price FROM ShopProduct AS SP INNER JOIN Product AS P ON SP.product_id = P.product_id;
```

像这样使用联结运算将满足相同规则的表联结起来时，WHERE、GROUP BY、HAVING、ORDER BY 等工具都可以正常使用.

-   外联结(OUTER JOIN)

```text
SELECT SP.shop_id, SP.shop_name, SP.product_id, P.product_name, P.sale_price FROM ShopProduct AS SP LEFT OUTER JOIN Product AS P ① ON SP.product_id = P.product_id;
```

-   三张以上的表的联结

```text
SELECT SP.shop_id, SP.shop_name, SP.product_id, P.product_name, P.sale_price, IP.inventory_quantity FROM ShopProduct AS SP INNER JOIN Product AS P ① ON SP.product_id = P.product_id INNER JOIN InventoryProduct AS IP ② ON SP.product_id = IP.product_id WHERE IP.inventory_id = 'P001';
```

## **Ⅲ. DCL（Data Control Language，数据控制语言）**

**1、创建事务(START TRANSACTION) - 提交处理(COMMIT)**

```text
START TRANSACTION; -- 将运动T恤的销售单价降低1000日元 UPDATE Product SET sale_price = sale_price - 1000 WHERE product_name = '运动T恤'; -- 将T恤衫的销售单价上浮1000日元 UPDATE Product SET sale_price = sale_price + 1000 WHERE product_name = 'T恤衫'; COMMIT;
```

**2、取消处理(ROLLBACK)**

```text
START TRANSACTION; -- 将运动T恤的销售单价降低1000日元 UPDATE Product SET sale_price = sale_price - 1000 WHERE product_name = '运动T恤'; -- 将T恤衫的销售单价上浮1000日元 UPDATE Product SET sale_price = sale_price + 1000 WHERE product_name = 'T恤衫'; ROLLBACK;
```

## 管理权限
![](imge/md-20240515150142.png)

查看权限
show grant (for ...)
![](imge/md-20240515152757.png)

## 添加新用户建议 GRANT 命令

**一、grant 普通数据用户，查询、插入、更新、删除 数据库中所有表数据的权利。**

```
grant select on testdb.* to common_user@'%'
grant insert on testdb.* to common_user@'%'
grant update on testdb.* to common_user@'%'
grant delete on testdb.* to common_user@'%'
```

或者，用一条 MySQL 命令来替代：

```
grant select, insert, update, delete on testdb.* to common_user@'%'
```

**二、grant 数据库开发人员，创建表、索引、视图、存储过程、函数。。。等权限。**

grant 创建、修改、删除 MySQL 数据表结构权限。

```
grant create on testdb.* to developer@'192.168.0.%';
grant alter on testdb.* to developer@'192.168.0.%';
grant drop on testdb.* to developer@'192.168.0.%';
```

grant 操作 MySQL 外键权限。

```
grant references on testdb.* to developer@'192.168.0.%';
```

grant 操作 MySQL 临时表权限。

```
grant create temporary tables on testdb.* to developer@'192.168.0.%';
```

grant 操作 MySQL 索引权限。

```
grant index on testdb.* to developer@'192.168.0.%';
```

grant 操作 MySQL 视图、查看视图源代码 权限。

```
grant create view on testdb.* to developer@'192.168.0.%';
grant show view on testdb.* to developer@'192.168.0.%';
```

grant 操作 MySQL 存储过程、函数 权限。

```
grant create routine on testdb.* to developer@'192.168.0.%'; -- now, can show procedure status
grant alter routine on testdb.* to developer@'192.168.0.%'; -- now, you can drop a procedure
grant execute on testdb.* to developer@'192.168.0.%';
```

**三、grant 普通 DBA 管理某个 MySQL 数据库的权限。**

```
grant all privileges on testdb to dba@'localhost'
```

其中，关键字 **privileges** 可以省略。

**四、grant 高级 DBA 管理 MySQL 中所有数据库的权限。**

```
grant all on *.* to dba@'localhost'
```

**五、MySQL grant 权限，分别可以作用在多个层次上。**

1\. grant 作用在整个 MySQL 服务器上：

```
grant select on *.* to dba@localhost; -- dba 可以查询 MySQL 中所有数据库中的表。
grant all on *.* to dba@localhost; -- dba 可以管理 MySQL 中的所有数据库
```

2\. grant 作用在单个数据库上：

```
grant select on testdb.* to dba@localhost; -- dba 可以查询 testdb 中的表。
```

3\. grant 作用在单个数据表上：

```
grant select, insert, update, delete on testdb.orders to dba@localhost;
```

这里在给一个用户授权多张表时，可以多次执行以上语句。例如：

```
grant select(user_id,username) on smp.users to mo_user@'%' identified by '123345';
grant select on smp.mo_sms to mo_user@'%' identified by '123345';
```

4\. grant 作用在表中的列上：

```
grant select(id, se, rank) on testdb.apache_log to dba@localhost;
```

5\. grant 作用在存储过程、函数上：

```
grant execute on procedure testdb.pr_add to 'dba'@'localhost'
grant execute on function testdb.fn_add to 'dba'@'localhost'
```

**六、查看 MySQL 用户权限**

查看当前用户（自己）权限：

```
show grants;
```

查看其他 MySQL 用户权限：

```
show grants for dba@localhost;
```

**七、撤销已经赋予给 MySQL 用户权限的权限。**

revoke 跟 grant 的语法差不多，只需要把关键字 to 换成 from 即可：

```
grant all on *.* to dba@localhost;
revoke all on *.* from dba@localhost;
```

**八、MySQL grant、revoke 用户权限注意事项**

1\. grant, revoke 用户权限后，该用户只有重新连接 MySQL 数据库，权限才能生效。

2\. 如果想让授权的用户，也可以将这些权限 grant 给其他用户，需要选项 **grant option**

```
grant select on testdb.* to dba@localhost with grant option;
```

这个特性一般用不到。实际中，数据库权限最好由 DBA 来统一管理。

注意：创建完成后需要执行 **FLUSH PRIVILEGES** 语句。


## 数据库权限相关的表
比如
![](imge/md-20240515155102.png)
![](imge/md-20240515155121.png)


### 触发器与存储过程

![](imge/md-20240515200936.png)
![](imge/md-20240515201238.png)
![](imge/md-20240515201357.png)
![](imge/md-20240515201731.png)
![](imge/md-20240515201845.png)
![](imge/md-20240515202006.png)
![](imge/md-20240515202131.png)
![](imge/md-20240515202431.png)
![](imge/md-20240515202616.png)


# 理论知识点
**一、数据库概述**  
保存数据 的容器  
SELECT `sid` AS ‘学生编号’ FROM yu;  
[数组](https://so.csdn.net/so/search?q=%E6%95%B0%E7%BB%84&spm=1001.2101.3001.7020)  
[集合](https://so.csdn.net/so/search?q=%E9%9B%86%E5%90%88&spm=1001.2101.3001.7020)  
文件  
…  
能否存储大量文件？  
查询速度如何？是否方便？  
共享是否方便？  
安全性如何？  
数据库的好处  
•实现数据持久化  
•使用完整的管理系统统一管理，易于查询  
**数据库的概念**  
DB  
数据库（database）：存储数据的“仓库”。它保存了一系列有组织的数据。  
DBMS  
数据库管理系统（Database Management System）。数据库是通过DBMS创建和操作的容器，常见的数据库管理系统：MySQL、Oracle、DB2、SqlServer等  
SQL  
结构化查询语言（Structure Query Language）：专门用来与数据库通信的语言。  
**二、SQL语言概述**  
SQL的优点：  
1、不是某个特定数据库供应商专有的语言，几乎所有DBMS都支持SQL  
2、简单易学  
3、虽然简单，但实际上是一种强有力的语言，灵活使用其语言元素，可以进行非常复杂和高级的数据库操作。  
**三、数据库的特点**  
将数据放到表中，表再放到库中  
一个数据库中可以有多个表，每个表都有一个的名字，用来标识自己。表名具有唯一性。  
表具有一些特性，这些特性定义了数据在表中如何存储，类似java中 “类”的设计。  
表由列组成，我们也称为字段。所有表都是由一个或多个列组成的，每一列类似java 中的”属性”  
表中的数据是按行存储的，每一行类似于java中的“对象”。  
**四、SQL语言分类**  
1、DML（Data Manipulation Language):数据操纵语句，用于添加、删除、修改、查询数据库记录，并检查数据完整性  
2、DDL（Data Definition Language):数据定义语句，用于库和表的创建、修改、删除。  
3、DCL（Data Control Language):数据控制语句，用于定义用户的访问权限和安全级别。  
DML  
DML用于查询与修改数据记录，包括如下SQL语句：  
INSERT：添加数据到数据库中  
UPDATE：修改数据库中的数据  
DELETE：删除数据库中的数据  
SELECT：选择（查询）数据  
SELECT是SQL语言的基础，最为重要。  
DDL  
DDL用于定义数据库的结构，比如创建、修改或删除数据库对象，包括如下SQL语句：  
CREATE TABLE：创建数据库表  
ALTER TABLE：更改表结构、添加、删除、修改列长度  
DROP TABLE：删除表  
CREATE INDEX：在表上建立索引  
DROP INDEX：删除索引  
DCL  
DCL用来控制数据库的访问，包括如下SQL语句：  
GRANT：授予访问权限  
REVOKE：撤销访问权限  
COMMIT：提交事务处理  
ROLLBACK：事务处理回退  
SAVEPOINT：设置保存点  
LOCK：对数据库的特定部分进行锁定  
**五、mysql数据库的安装**  
图解MySQL程序结构

MySQL产品的特点  
MySQL数据库隶属于MySQL AB公司，总部位于瑞典，后被oracle收购。  
– 成本低：开放源代码，一般可以免费试用  
– 性能高：执行很快  
– 简单：很容易安装和使用  
配置文件常用配置分析（重点）  
安装目录下的my.ini文件  
\[mysqld\] 服务端配置  
port=3306 端口3306  
basedir 是安装目录  
datadir 数据文件目录  
character-set-server 服务端字符编码  
default-storage-engine 默认引擎  
sql-mode 语法模式  
max\_connections 最大连接数  
启动和停止MySQL服务  
方式一：通过计算机管理方式  
右击计算机—管理—服务—启动或停止MySQL服务  
方式二：通过命令行方式（右键管理员运行，否则没有权限）  
启动：net start 服务名  
停止：net stop 服务名  
MySQL服务端的登录和退出  
通过mysql自带客户端

通过window是命令行登录  
mysql –h 主机名 –u用户名 –p 密码  
mysql -h localhost -u root -p  
输入密码：  
退出  
exit  
六、MySql数据库的使用  
规范  
不区分大小写  
每句话用;或\\g结尾  
各子句一般分行写  
关键字不能缩写也不能分行  
用缩进提高语句的可读性  
注释  
单行注释 # 或 –  
多行注释 /\* 注释文本 \*/

示例

1.  进入 mysql, 在命令行中输入: mysql –u root –p
2.  查看 mysql 中有哪些个数据库: show databases;
3.  使用一个数据库: use 数据库名称;
4.  新建一个数据库: create database 数据库名
5.  查看指定的数据库中有哪些数据表: show tables;  
    6.查看mysql版本  
    登录后select version();  
    或退出重新登录  
    未登录 mysql --version或mysql –V

1.SHOW DATABASES;  
2.USE employee;  
3.SHOW TABLES ;  
4.DESC dept;  
**七、图形化界面客户端的使用**

导入sql文件  
四张表的介绍

**八、基本 SELECT 语句**  
SELECT \*|{\[DISTINCT\] column|expression \[alias\],…}  
FROM table;  
• SELECT 标识选择哪些列。  
• FROM 标识从哪个表中选择。  
SELECT \*  
FROM departments;  
选择特定的列  
SELECT department\_id, location\_id  
FROM departments;

注意  
• SQL 语言大小写不敏感。  
• SQL 可以写在一行或者多行  
• 关键字不能被缩写也不能分行  
• 各子句一般要分行写。  
• 使用缩进提高语句的可读性  
列的别名  
• 重命名一个列。  
• 便于计算。  
• 紧跟列名，也可以在列名和别名之间加入关键字‘AS’，别名使用双引号，以便在别名中包含空格或特殊的字符并区分大小写。  
SELECT last\_name AS name, commission\_pct comm  
FROM employees;  
SELECT last\_name “Name”, salary\*12 “Annual Salary”  
FROM employees;

字符串  
• 字符串可以是 SELECT 列表中的一个字符,数字,日期。  
• 日期和字符只能在单引号中出现。?  
显示表结构  
使用 DESCRIBE 命令，表示表结构  
DESC\[RIBE\] tablename  
DESCRIBE employees  
**九、过滤和排序数据**  
过滤  
使用WHERE 子句，将不满足条件的行过滤掉  
SELECT  
\*|{\[DISTINCT\] column|expression \[alias\],…}  
FROM table  
\[WHERE condition(s)\];  
WHERE 子句紧随 FROM 子句

返回在 90号部门工作的所有员工的信息  
SELECT employee\_id, last\_name, job\_id, department\_id  
FROM employees  
WHERE department\_id = 90 ;  
比较运算  
赋值使用 := 符号

SELECT last\_name, salary  
FROM employees  
WHERE salary <= 3000;  
其它比较运算（重点听一下LIKE）

BETWEEN  
使用 BETWEEN 运算来显示在一个区间内的值  
SELECT last\_name, salary  
FROM employees  
WHERE salary BETWEEN 2500 AND 3500;  
IN  
使用 IN运算显示列表中的值。  
SELECT employee\_id, last\_name, salary, manager\_id  
FROM employees  
WHERE manager\_id IN (100, 101, 201);  
LIKE  
•使用 LIKE 运算选择类似的值  
•选择条件可以包含字符或数字:  
% 代表零个或多个字符(任意个字符)。  
\_ 代表一个字符。  
SELECT first\_name  
FROM employees  
WHERE first\_name LIKE ‘S%’;

‘%’和‘-’可以同时使用  
SELECT last\_name  
FROM employees  
WHERE last\_name LIKE ‘\_o%’; //最后的名字字母为o  
NULL  
使用 IS (NOT) NULL 判断空值  
SELECT last\_name, manager\_id  
FROM employees  
WHERE manager\_id IS NULL;  
逻辑运算

AND  
AND 要求并的关系为真

SELECT employee\_id, last\_name, job\_id, salary  
FROM employees  
WHERE salary >=10000  
AND job\_id LIKE ‘%MAN%’;  
OR  
OR 要求或关系为真。  
SELECT employee\_id, last\_name, job\_id, salary  
FROM employees  
WHERE salary >= 10000  
OR job\_id LIKE ‘%MAN%’;  
NOT  
SELECT last\_name, job\_id  
FROM employees  
WHERE job\_id  
NOT IN (‘IT\_PROG’, ‘ST\_CLERK’, ‘SA\_REP’);  
ORDER BY子句  
使用 ORDER BY 子句排序  
ASC（ascend）: 升序  
DESC（descend）: 降序  
ORDER BY 子句在SELECT语句的结尾。  
SELECT last\_name, job\_id, department\_id, hire\_date  
FROM employees  
ORDER BY hire\_date ;  
降序排序  
SELECT last\_name, job\_id, department\_id, hire\_date  
FROM employees  
ORDER BY hire\_date DESC ;  
按别名排序  
SELECT employee\_id, last\_name, salary\*12 annsal  
FROM employees  
ORDER BY annsal;  
多个列排序  
按照ORDER BY 列表的顺序排序  
SELECT last\_name, department\_id, salary  
FROM employees  
ORDER BY department\_id, salary DESC;  
可以使用不在SELECT 列表中的列排序  
**十、分组函数**  
什么是分组函数  
分组函数作用于一组数据，并对一组数据返回一个值。  
组函数类型

• AVG()  
• COUNT()  
• MAX()  
• MIN()  
• SUM()  
组函数语法  
SELECT \[column,\] group\_function(column), …  
FROM table  
\[WHERE condition\]  
\[GROUP BY column\]  
\[ORDER BY column\];  
AVG（平均值）和 SUM （合计）函数  
可以对数值型数据使用AVG 和 SUM 函数。  
SELECT AVG(salary), MAX(salary),  
MIN(salary), SUM(salary)  
FROM employees  
WHERE job\_id LIKE ‘%REP%’;  
MIN（最小值）和 MAX（最大值）函数  
可以对任意数据类型的数据使用 MIN 和 MAX 函数。  
SELECT MIN(hire\_date), MAX(hire\_date)  
FROM employees;  
COUNT（计数）函数  
COUNT(_) 返回表中记录总数,适用于任意数据类型。  
SELECT COUNT(_)  
FROM  
employees  
WHERE department\_id = 50;

COUNT(expr) 返回expr不为空的记录总数  
SELECT COUNT(commission\_pct)  
FROM employees  
WHERE department\_id = 50;  
分组数据  
分组数据: GROUP BY 子句语法  
可以使用GROUP BY子句将表中的数据分成若干组  
SELECT column, group\_function(column)  
FROM table  
\[WHERE condition\]  
\[GROUP BY group\_by\_expression\]  
\[ORDER BY column\];  
GROUP BY 子句 (?)  
在SELECT 列表中所有未包含在组函数中的列都应该包含在 GROUP BY 子句中  
SELECT department\_id, AVG(salary)  
FROM employees  
GROUP BY department\_id ;  
包含在 GROUP BY 子句中的列不必包含在SELECT 列表中  
SELECT AVG(salary)  
FROM employees  
GROUP BY department\_id ;  
使用多个列分组  
SELECT department\_id dept\_id, job\_id, SUM(salary)  
FROM employees  
GROUP BY department\_id, job\_id ;  
非法使用组函数  
• 不能在 WHERE 子句中使用组函数。  
• 可以在 HAVING 子句中使用组函数  
SELECT department\_id, AVG(salary)  
FROM employees  
WHERE AVG(salary) > 8000  
GROUP BY department\_id;

WHERE 子句中不能使用组函数  
过滤分组  
使用 HAVING 过滤分组:

1.  行已经被分组。
2.  使用了组函数。
3.  满足HAVING 子句中条件的分组将被显示。  
    SELECT column, group\_function  
    FROM table  
    \[WHERE condition\]  
    \[GROUP BY group\_by\_expression\]  
    \[HAVING group\_condition\]  
    \[ORDER BY column\];

HAVING 子句  
SELECT department\_id, MAX(salary)  
FROM employees  
GROUP BY department\_id  
HAVING MAX(salary)>10000 ;  
**十一、多表查询**

笛卡尔集

select name,boyName from beauty,boys; 这条语句对吗？  
为了避免笛卡尔集， 可以在 WHERE 加入有 效的连接条件

Mysql 连接  
使用连接在多个表中查询数据  
SELECT table1.column, table2.column  
FROM table1, table2  
WHERE table1.column1 = table2.column2;  
在 WHERE 子句中写入连接条件。  
在表中有相同列时，在列名之前加上表名前缀  
等值连接

SELECT beauty.id,NAME,boyname FROM beauty ,boys  
WHERE beauty.`boyfriend_id`\=boys.id;  
区分重复的列名  
• 使用表名前缀在多个表中区分相同的列。  
• 在不同表中具有相同列名的列可以用表的别名加以区分。  
• 如果使用了表别名，则在select语句中需要使用表别名代替表名  
• 表别名最多支持32个字符长度，但建议越少越好  
表的别名  
• 使用别名可以简化查询。  
• 使用表名前缀可以提高执行效率。  
SELECT bt.id,NAME,boyname  
FROM beauty bt,boys b;  
WHERE bt.`boyfriend_id`\=b.id ;  
Join连接  
• 分类：  
– 内连接 \[inner\] join on  
– 外连接  
• 左外连接 left \[outer\] join on  
• 右外连接 right \[outer\] join on  
ON 子句  
SELECT bt.id,NAME,boyname  
FROM beauty bt  
Inner join boys b  
On bt.`boyfriend_id`\=b.id ;  
连接多个表  
连接 n个表,至少需要 n-1个连接条件。  
例如：连接三个表，至少需要两个连接条件。  
练习：查询出公司员工的 last\_name, department\_name, city  
使用 ON 子句创建多表连接（重点）  
SELECT employee\_id, city, department\_name  
FROM employees e  
JOIN departments d  
ON d.department\_id = e.department\_id  
JOIN locations l  
ON d.location\_id = l.location\_id;  
John连接总结

**十二、字符函数**

大小写控制函数  
这类函数改变字符的大小写。  
LOWER(‘SQL Course’)  
sql course

UPPER(‘SQL Course’)  
SQL COURSE

字符控制函数  
CONCAT(‘Hello’, ‘World’)  
HelloWorld

SUBSTR(‘HelloWorld’,1,5)  
Hello

LENGTH(‘HelloWorld’)  
10

INSTR(‘HelloWorld’, ‘W’)  
6

LPAD(salary,10,’\*’)  
\*\*\*\*\*24000

RPAD(salary, 10, ‘\*’)  
24000\*\*\*\*\*

TRIM(‘H’ FROM ‘HelloWorld’)  
elloWorld

REPLACE(‘abcd’,‘b’,‘m’)  
amcd  
**十三、数字函数**  
ROUND: 四舍五入  
ROUND(45.926, 2) 45.93  
TRUNCATE: 截断  
TRUNC(45.926, 2) 45.92  
MOD: 求余  
MOD(1600, 300) 100  
**十四、日期函数**  
now：获取当前日期  
str\_to\_date: 将日期格式的字符转换成指定格式的日期  
STR\_TO\_DATE(‘9-13-1999’,’%m-%d-%Y’) 1999-09-13  
date\_format:将日期转换成字符  
str\_to\_date: 将日期格式的字符转换成指定格式的日期 2018年06月06日

**十五、条件表达式**  
• 在 SQL 语句中使用IF-THEN-ELSE 逻辑  
• 使用方法: – CASE 表达式  
CASE 表达式  
在需要使用 IF-THEN-ELSE 逻辑时  
CASE expr WHEN comparison\_expr1 THEN return\_expr1  
\[WHEN comparison\_expr2 THEN return\_expr2  
WHEN comparison\_exprn THEN return\_exprn  
ELSE else\_expr\]  
END  
练习：查询部门号为 10, 20, 30 的员工信息, 若部门号为 10, 则打印其工资的 1.1 倍, 20 号部门, 则打印其工资的 1.2 倍, 30 号部门打印其工资的 1.3 倍数  
下面是使用case表达式的一个例子：  
SELECT department\_id,salary,  
CASE department\_id  
WHEN 10 THEN salary_1.2  
WHEN 20 THEN salary_1.3  
WHEN 30 THEN salary\*1.5  
ELSE salary  
END 工资2  
FROM employees  
**十六、数据操纵语言**  
DML(Data Manipulation Language –数据操纵语言) 可以在下列条件下执行: – 向表中插入数据  
– 修改现存数据  
– 删除现存数据  
事务是由完成若干项工作的DML语句组成的  
**十七、插入数据**  
INSERT 语句语法  
使用 INSERT 语句向表中插入数据。  
INSERT INTO table \[(column \[, column…\])\]  
VALUES (value \[, value…\]);  
使用这种语法一次只能向表中插入一条数据  
插入数据  
为每一列添加一个新值。  
• 按列的默认顺序列出各个列的值。  
• 在 INSERT 子句中随意列出列名和他们的值。  
• 字符和日期型数据应包含在单引号中。  
INSERT INTO departments(department\_id, department\_name,  
manager\_id, location\_id)  
VALUES (70, ‘Public Relations’, 100, 1700);

INSERT INTO  
employees(employee\_id,last\_name,email,hire\_date,job\_id)  
VALUES (300,’Tom’,’tom@126.com’,to\_date(‘2012-3-  
21’,’yyyy-mm-dd’),’SA\_RAP’);

向表中插入空值  
隐式方式: 在列名表中省略该列的值  
INSERT INTO departments (department\_id, department\_name )  
VALUES (30, ‘Purchasing’);

显示方式: 在VALUES 子句中指定空值。  
INSERT INTO departments  
VALUES (100, ‘Finance’, NULL, NULL);  
插入指定的值  
NOW()函数：记录当前系统的日期和时间  
INSERT INTO employees (employee\_id, first\_name, last\_name, email, phone\_number,hire\_date, job\_id, salary, commission\_pct, manager\_id,department\_id)  
VALUES (113, ‘Louis’, ‘Popp’, ‘LPOPP’, ‘515.124.4567’, NOW(), ‘AC\_ACCOUNT’, 6900, NULL, 205, 100);

从其它表中拷贝数据  
在 INSERT 语句中加入子查询  
INSERT INTO emp2  
SELECT \*  
FROM employees  
WHERE department\_id = 90;

INSERT INTO sales\_reps(id, name, salary, commission\_pct)  
SELECT employee\_id, last\_name, salary, commission\_pct  
FROM employees  
WHERE job\_id LIKE ‘%REP%’;

不必书写 VALUES 子句。  
子查询中的值列表应与 INSERT 子句中的列名对应  
**十八、更新数据**  
UPDATE 语句语法  
• 使用 UPDATE 语句更新数据  
UPDATE table  
SET column = value \[, column = value, …\]  
\[WHERE condition\];

• 可以一次更新多条数据。  
• 如果需要回滚数据，需要保证在DML前，进行设置：SET AUTOCOMMIT = FALSE;  
• 使用 WHERE 子句指定需要更新的数据  
UPDATE employees  
SET department\_id = 70  
WHERE employee\_id = 113;  
• 如果省略 WHERE 子句，则表中的所有数据都将被更新  
UPDATE copy\_emp  
SET department\_id = 110;

更新中的数据完整性错误  
UPDATE employees  
SET department\_id = 55  
WHERE department\_id = 110;

**十九、删除数据**  
DELETE 语句  
使用 DELETE 语句从表中删除数据。  
DELETE FROM table  
\[WHERE condition\];  
删除数据  
使用 WHERE 子句删除指定的记录  
DELETE FROM departments  
WHERE department\_name = ‘Finance’;  
如果省略 WHERE 子句，则表中的全部数据将被删除  
DELETE FROM copy\_emp;  
删除中的数据完整性错误  
DELETE FROM departments  
WHERE department\_id = 60;

**二十、子查询**  
概念  
出现在其他语句内部的select语句，称为子查询或内查询,内部嵌套其他select语句的查询，称为外查询或主查询  
示例：  
select first\_name from employees where  
department\_id in(  
select department\_id from departments  
where location\_id=1700  
)  
注意事项  
• 子查询要包含在括号内。  
• 将子查询放在比较条件的右侧。  
• 单行操作符对应单行子查询，多行操作符对应多行子查询  
单行子查询  
• 只返回一行。  
• 使用单行比较操作符。

子查询语法  
SELECT select\_list  
FROM table  
WHERE expr operator  
(SELECT select\_list  
FROM table);

• 子查询 (内查询) 在主查询之前一次执行完成。  
• 子查询的结果被主查询(外查询)使用 。  
使用子查询解决问题  
谁的工资比 Abel 高?

执行单行子查询  
题目：返回job\_id与141号员工相同，salary比143号员工多的员工姓名，job\_id 和工资  
在子查询中使用组函数  
题目：返回公司工资最少的员工的last\_name,job\_id和salary  
子查询中的 HAVING 子句  
• 首先执行子查询。  
• 向主查询中的HAVING 子句返回结果。  
题目：查询最低工资大于50号部门最低工资的部门id和其最低工资  
非法使用子查询

子查询中的空值问题  
SELECT last\_name, job\_id  
FROM employees  
WHERE job\_id =  
(SELECT job\_id  
FROM employees  
WHERE last\_name = ‘Haas’);  
子查询不返回任何行  
多行子查询  
• 返回多行。  
• 使用多行比较操作符

使用in操作符  
题目：返回location\_id是1400或1700的部门中的所有员工姓名  
在多行子查询中使用 ANY 操作符  
题目：返回其它部门中比job\_id为‘IT\_PROG’部门任一工资低的员工的员  
工号、姓名、job\_id 以及salary

题目：返回其它部门中比job\_id为‘IT\_PROG’部门所有工资都低的员工  
的员工号、姓名、job\_id 以及salary

子查询中的空值问题

**二十一、创建数据库**  
创建一个保存员工信息的数据库  
– create database employees;  
相关其他命令  
– show databases;查看当前所有数据库  
– use employees;“使用”一个数据库，使其作为当前数据库  
命名规则  
• 数据库名不得超过30个字符，变量名限制为29个 • 必须只能包含 A–Z, a–z, 0–9, \_共63个字符  
• 不能在对象名的字符间留空格  
• 必须不能和用户定义的其他对象重名  
• 必须保证你的字段没有和保留字、数据库系统或常用方法冲突  
• 保持字段名和类型的一致性,在命名字段并为其指定数据类型的时候一定要保证一致性。假如数据类型在一个表里是整数,那在另一个表里可就别变成字符型了  
CREATE TABLE 语句  
• 必须具备:  
– CREATE TABLE权限  
– 存储空间

• 必须指定:  
– 表名  
– 列名, 数据类型, 尺寸  
语法

确认

常用数据类型

创建表  
CREATE TABLE emp (  
#int类型，自增  
emp\_id INT AUTO\_INCREMENT, #最多保存20个中英文字符  
emp\_name CHAR (20),  
#总位数不超过15位  
salary DOUBLE,  
#日期类型  
birthday DATE,  
#主键  
PRIMARY KEY (emp\_id)  
) ;  
使用子查询创建表  
• 使用 AS subquery 选项，将创建表和插入数据结合起来(?)

• 指定的列和子查询中的列要一一对应  
• 通过列名和默认值定义列  
复制现有的表：  
create table emp1 as select \* from employees;  
create table emp2 as select \* from employees where 1=2;  
–创建的emp2是空表。  
使用子查询创建表举例

**二十二、ALTER TABLE 语句**  
使用 ALTER TABLE 语句可以实现：  
–向已有的表中添加列  
修改现有表中的列  
–删除现有表中的列  
–重命名现有表中的列  
追加一个新列  
ALTER TABLE dept80  
ADD job\_id varchar(15);  
修改一个列  
• 可以修改列的数据类型, 尺寸和默认值  
• 对默认值的修改只影响今后对表的修改  
ALTER TABLE dept80  
MODIFY (last\_name VARCHAR(30));

ALTER TABLE dept80  
MODIFY (salary double(9,2) default 1000);

删除一个列  
使用 DROP COLUMN 子句删除不再需要的列.  
ALTER TABLE dept80  
DROP COLUMN job\_id;  
重命名一个列  
使用 CHANGE old\_column new\_column dataType子句重命名列  
ALTER TABLE dept80  
CHANGE department\_name dept\_name varchar(15);  
二十三、删除表  
• 数据和结构都被删除  
• 所有正在运行的相关事务被提交  
• 所有相关索引被删除  
• DROP TABLE 语句不能回滚  
DROP TABLE dept80;

清空表  
• TRUNCATE TABLE 语句:  
– 删除表中所有的数据  
– 释放表的存储空间  
• TRUNCATE语句不能回滚  
• 可以使用 DELETE 语句删除数据,可以回滚  
二十四、改变对象的名称  
• 执行RENAME语句改变表, 视图的名称  
• 必须是对象的拥有者  
ALTER table dept  
RENAME TO detail\_dept;  
二十五、常见的数据类型  
整型

小数

位类型

char和varchar类型  
字符串类型 最多字符数 描述及存储需求  
说明：用来保存MySQL中较短的字符串。

binary和varbinary类型  
说明：类似于char和varchar，不同的是它们包含二进制字符串而不包含非二进制字符串。  
Enum类型  
说明:又称为枚举类型哦，要求插入的值必须属于列表中指定的值之一。  
如果列表成员为1~255，则需要1个字节存储  
如果列表成员为255~65535，则需要2个字节存储  
最多需要65535个成员  
Set类型  
说明：和Enum类型类似，里面可以保存0~64个成员。和Enum类型最大的区别是：SET类型一次可以选取多个成员，而Enum只能选一个根据成员个数不同，存储所占的字节也不同

日期类型

datetime和timestamp的区别  
1、Timestamp支持的时间范围较小，取值范围：19700101080001——2038年的某个时间Datetime的取值范围：1000-1-1 ——9999—12-31  
2、timestamp和实际时区有关，更能反映实际的日期，而datetime则只能反映出插入时的当地时区  
3、timestamp的属性受Mysql版本和SQLMode的影响很大  
**二十六、约束**  
什么是约束  
• 为了保证数据的一致性和完整性，SQL规范以约束的方式对表数据进行额外的条件限制。  
• 约束是表级的强制规定  
• 可以在创建表时规定约束（通过 CREATETABLE 语句），或者在表创建之后也可以（通  
过 ALTER TABLE 语句）  
有以下六种约束:  
– NOT NULL 非空约束，规定某个字段不能为空  
– UNIQUE 唯一约束，规定某个字段在整个表中是唯一的  
– PRIMARY KEY 主键(非空且唯一) – FOREIGN KEY 外键  
– CHECK 检查约束  
– DEFAULT 默认值  
具体细节可以参阅W3Cschool手册  
注意： MySQL不支持check约束，但可以使用check约束，而没有任何效果；  
• 根据约束数据列的限制，约束可分为：  
– 单列约束：每个约束只约束一列  
– 多列约束：每个约束可约束多列数据  
• 根据约束的作用范围，约束可分为：  
– 列级约束只能作用在一个列上，跟在列的定义后面  
– 表级约束可以作用在多个列上，不与列一起，而是单独定义  
NOT NULL 约束  
非空约束用于确保当前列的值不为空值，非空约束只能出现在表对象的列上。  
Null类型特征：  
所有的类型的值都可以是null，包括int、float等数据类型  
空字符串””不等于null，0也不等于null  
创建 not null 约束：  
CREATE TABLE emp(  
id INT(10) NOT NULL,  
NAME VARCHAR(20) NOT NULL DEFAULT ‘abc’,  
sex CHAR NULL  
);  
增加 not null 约束  
ALTER TABLE emp  
MODIFY sex VARCHAR(30) NOT NULL;  
取消 not null 约束：  
ALTER TABLE emp  
MODIFY sex VARCHAR(30) NULL;

取消 not null 约束，增加默认值：  
ALTER TABLE emp  
MODIFY NAME VARCHAR(15) DEFAULT ‘abc’ NULL;  
UNIQUE 约束  
• 同一个表可以有多个唯一约束，多个列组合的约束。在创建唯一约束的时候，如果不给唯一约束名称，就默认和列名相同。  
• MySQL会给唯一约束的列上默认创建一个唯一索引  
• 唯一约束，允许出现多个空值：NULL。  
CREATE TABLE USER(  
id INT NOT NULL,  
NAME VARCHAR(25),  
PASSWORD VARCHAR(16),  
#使用表级约束语法  
CONSTRAINT uk\_name\_pwd UNIQUE(NAME,PASSWORD)  
);  
表示用户名和密码组合不能重复  
添加唯一约束  
ALTER TABLE USER  
ADD UNIQUE(NAME,PASSWORD);  
ALTER TABLE USER  
ADD CONSTRAINT uk\_name\_pwd UNIQUE(NAME,PASSWORD);  
ALTER TABLE USER  
MODIFY NAME VARCHAR(20) UNIQUE;  
删除约束  
ALTER TABLE USER  
DROP INDEX uk\_name\_pwd;  
PRIMARY KEY 约束  
• 主键约束相当于唯一约束+非空约束的组合，主键约束列不允许重复，也不允许出现空值  
• 如果是多列组合的主键约束，那么这些列都不允许为空值，并且组合的值不允许重复。  
• 每个表最多只允许一个主键，建立主键约束可以在列级别创建，也可以在表级别上创建。  
• MySQL的主键名总是PRIMARY，当创建主键约束时，系统默认会在所在的列和列组合上建立对应的唯一索引。  
CREATE TABLE emp4(  
id INT AUTO\_INCREMENT PRIMARY KEY,  
NAME VARCHAR(20)  
);

CREATE TABLE emp5(  
id INT NOT NULL AUTO\_INCREMENT,  
NAME VARCHAR(20),  
pwd VARCHAR(15),  
CONSTRAINT emp5\_id\_pk PRIMARY KEY(id)  
);

CREATE TABLE emp6(  
id INT NOT NULL,  
NAME VARCHAR(20),  
pwd VARCHAR(15),  
CONSTRAINT emp7\_pk PRIMARY KEY(NAME,pwd)  
);  
删除主键约束  
ALTER TABLE emp5  
DROP PRIMARY KEY;  
添加主键约束  
ALTER TABLE emp5  
ADD PRIMARY KEY(NAME,pwd);  
修改主键约束  
ALTER TABLE emp5  
MODIFY id INT PRIMARY KEY;  
FOREIGN KEY 约束  
• 外键约束是保证一个或两个表之间的参照完整性，外键是构建于一个表的两个字段或是两个表的两个字段之间的参照关系。  
• 从表的外键值必须在主表中能找到或者为空。当主表的记录被从表参照时，主表的记录将不允许删除，如果要删除数据，需要先删除从表中依赖该记录的数据，然后才可以删除主表的数据。  
• 还有一种就是级联删除子表数据。  
• 注意：外键约束的参照列，在主表中引用的只能是主键或唯一键约束的列  
• 同一个表可以有多个外键约束  
创建外键约束:  
CREATE TABLE dept(  
dept\_id INT AUTO\_INCREMENT PRIMARY KEY,  
dept\_name VARCHAR(20)  
);

CREATE TABLE emp(  
emp\_id INT AUTO\_INCREMENT PRIMARY KEY,  
last\_name VARCHAR(15),  
dept\_id INT,  
);  
创建多列外键组合，必须使用表级约束:  
CREATE TABLE classes(  
id INT,  
NAME VARCHAR(20),  
number INT,  
PRIMARY KEY(NAME,number)  
);

CREATE TABLE student(  
id INT AUTO\_INCREMENT PRIMARY KEY,  
classes\_name VARCHAR(20),  
classes\_number INT,  
FOREIGN KEY(classes\_name,classes\_number)  
REFERENCES classes(NAME,number)  
);

• 删除外键约束：  
ALTER TABLE emp  
DROP FOREIGN KEY emp\_dept\_id\_fk;  
• 增加外键约束：  
ALTER TABLE emp  
ADD \[CONSTRAINT emp\_dept\_id\_fk\] FOREIGN KEY(dept\_id)  
REFERENCES dept(dept\_id);  
FOREIGN KEY 约束的关键字  
– FOREIGN KEY: 在表级指定子表中的列  
– REFERENCES: 标示在父表中的列  
–ON DELETE CASCADE(级联删除): 当父表中的列被删除时，子表中相对应的列也被删除  
–ON DELETE SET NULL(级联置空): 子表中相应的列置空

CREATE TABLE student(  
id INT AUTO\_INCREMENT PRIMARY KEY,  
NAME VARCHAR(20),  
classes\_name VARCHAR(20),  
classes\_number INT,  
/_表级别联合外键_/  
FOREIGN KEY(classes\_name, classes\_number)  
REFERENCES classes(NAME, number) ON DELETE CASCADE);

CHECK 约束  
• MySQL可以使用check约束，但check约束对数据验证没有任何作用,添加数据时，没有任何错误或警告

CREATE TABLE temp(  
id INT AUTO\_INCREMENT,  
NAME VARCHAR(20),  
age INT CHECK(age > 20),  
PRIMARY KEY(id)  
);  
**二十七、MySQL中使用limit实现分页**  
• 背景  
–查询返回的记录太多了，查看起来很不方便，怎么样能够实现分页查询呢？  
• 分页原理  
–所谓分页显示，就是将数据库中的结果集，一段一段显示出来需要的条件

MySQL中使用limit实现分页  
• 怎么分段，当前在第几段（每页有几条，当前在第几页）  
–前10条记录：SELECT \* FROM table LIMIT 0,10;  
–第11至20条记录：SELECT \* FROM table LIMIT 10,10;  
–第21至30条记录： SELECT \* FROM table LIMIT 20,10;  
• 公式：  
（当前页数-1）\*每页条数，每页条数  
SELECT \* FROM table LIMIT(PageNo - 1)\*PageSize,PageSize; • 注意：  
–limit子句必须放在整个查询语句的最后！  
**二十八、事务**  
事务的概念  
事务：事务由单独单元的一个或多个SQL语句组成，在这个单元中，每个MySQL语句是相互依赖的。而整个单独单元作为一个不可分割的整体，如果单元中某条SQL语句一旦执行失败或产生错误，整个单元将会回滚。所有受到影响的数据将返回到事物开始以前的状态；如果单元中的所有SQL语句均执行成功，则事物被顺利执行  
MySQL 中的存储引擎\[了解\]  
1、概念：在mysql中的数据用各种不同的技术存储在文件（或内存）中。  
2、通过show engines；来查看mysql支持的存储引擎。  
3、 在mysql中用的最多的存储引擎有：innodb，myisam ,memory 等。其中innodb支持事务，而myisam、memory等不支持事务  
**事务的特点（重点）**  
事务的ACID(acid)属性  
–1. 原子性（Atomicity）  
原子性是指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生。  
–2. 一致性（Consistency）  
事务必须使数据库从一个一致性状态变换到另外一个一致性状态。  
–3. 隔离性（Isolation）  
事务的隔离性是指一个事务的执行不能被其他事务干扰，即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。  
–4. 持久性（Durability）  
持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来的其他操作和数据库故障不应该对其有任何影响  
**事务的使用**  
以第一个 DML 语句的执行作为开始  
以下面的其中之一作为结束:  
–COMMIT 或 ROLLBACK 语句  
–DDL 或 DCL 语句（自动提交）  
–用户会话正常结束  
–系统异常终了  
**数据库的隔离级别**  
对于同时运行的多个事务, 当这些事务访问数据库中相同的数据时, 如果没有采取必要的隔离机制, 就会导致各种并发问题:  
–脏读: 对于两个事务 T1, T2, T1 读取了已经被 T2 更新但还没有被提交的字段. 之后, 若 T2 回滚, T1读取的内容就是临时且无效的.  
–不可重复读: 对于两个事务T1, T2, T1 读取了一个字段, 然后 T2 更新了该字段. 之后, T1再次读取同一个字段, 值就不同了.  
–幻读: 对于两个事务T1, T2, T1 从一个表中读取了一个字段, 然后 T2 在该表中插 入了一些新的行. 之后, 如果 T1 再次读取同一个表, 就会多出几行.  
数据库事务的隔离性: 数据库系统必须具有隔离并发运行各个事务的能力, 使它们不会相互影响, 避免各种并发问题.  
一个事务与其他事务隔离的程度称为隔离级别. 数据库规定了多种事务隔离级别, 不同隔离级别对应不同的干扰程度, 隔离级别越高, 数据一致性就越好, 但并发性越弱  
数据库提供的 4 种事务隔离级别

Oracle 支持的 2 种事务隔离级别：READ COMMITED, SERIALIZABLE。 Oracle 默认的事务隔离级别为: READ COMMITED  
Mysql 支持 4 种事务隔离级别. Mysql 默认的事务隔离级别为: REPEATABLE READ

在 MySql 中设置隔离级别  
每启动一个 mysql 程序, 就会获得一个单独的数据库连接. 每个数据库连接都有一个全局变量 @@tx\_isolation, 表示当前的事务隔离级别.  
查看当前的隔离级别: SELECT @@tx\_isolation;  
设置当前 mySQL 连接的隔离级别:  
–set transaction isolation level read committed;  
设置数据库系统的全局的隔离级别:  
–set global transaction isolation level read committed;  
**二十九、视图**  
• 概念  
视图：MySQL从5.0.1版本开始提供视图功能。一种虚拟存在的表，行和列的数据来自定义视图的查询中使用的表，并且是在使用视图时动态生成的，只保存了sql逻辑，不保存查询结果  
• 应用场景：  
– 多个地方用到同样的查询结果  
– 该查询结果使用的sql语句较复杂  
• 示例：  
CREATE VIEW my\_v1  
AS  
SELECT studentname,majorname  
FROM student s  
INNER JOIN major m  
ON s.majorid=m.majorid  
WHERE s.majorid=1;  
常见题目  
1.查询邮箱中包含a字符的员工名、部门名和工种信息  
2.查询各部门的平均工资  
3.查询平均工资最低的部门信息  
4.查询平均工资最低的部门名和工资  
**视图的好处**  
• 重用sql语句  
• 简化复杂的sql操作，不必知道它的查询细节  
• 保护数据，提高安全性  
创建或者修改视图  
• 创建视图的语法：  
create \[or replace\] view view\_name  
As select\_statement  
\[with|cascaded|local|check option\]  
• 修改视图的语法：  
alter view view\_name  
As select\_statement  
\[with|cascaded|local|check option\]  
视图的可更新性和视图中查询的定义有关系，以下类型的视图是不能更新的。  
• 包含以下关键字的sql语句：分组函数、distinct、group by、having、union或者union all  
• 常量视图  
• Select中包含子查询  
• join  
• from一个不能更新的视图  
• where子句的子查询引用了from子句中的表  
删除视图  
• 删除视图的语法：  
用户可以一次删除一个或者多个视图，前提是必须有该视图的drop权限。  
drop view \[if exists\] view\_name,view\_name …\[restrict|cascade\]  
查看视图  
• 查看视图的语法：  
show tables;  
如果需要查询某个视图的定义，可以使用show create view命令进行查看：  
show create view view\_name \\G  
**三十、存储过程和函数**  
什么是存储过程和函数  
存储过程和函数：  
事先经过编译并存储在数据库中的一段sql语句的集合。  
使用好处：  
1、简化应用开发人员的很多工作  
2、减少数据在数据库和应用服务器之间的传输  
3、提高了数据处理的效率  
创建存储过程或函数  
• 创建存储过程：  
create procedure 存储过程名 (\[proc\_parameter\[,…\]\])  
\[characteristic…\]routine\_body  
• 创建函数：  
create function 函数名(\[func\_parameter\[,…\]\])  
returns type  
\[characteristic…\]routine\_body  
语法解释  
• proc\_parameter: \[in|out|inout\] param\_name type  
• Func\_paramter:param\_name type  
• Type:  
任何有效的mysql数据类型  
• Characteristic:  
language sql(默认，且推荐)  
|\[not\] deterministic  
|{contains sql|no sql|reads sql data|modifies sql data}  
|sql security{definer|invoker}  
|comment ‘string’  
• Rountine\_body:  
有效的sql 过程语句  
调用存储过程或函数  
• 调用存储过程：  
call 存储过程名(参数列表)  
• 调用函数：  
Select 函数名(参数列表)  
案例  
• 查询员工名为king的所有记录  
• 根据输入的员工名，查询部门名  
• 根据指定的员工编号，返回工资  
• 根据指定的员工编号，返回工资和部门号  
• 将输入的a和b都翻倍并返回  
修改存储过程或函数  
• 修改存储过程：  
alter procedure 存储过程名 \[charactristic…\]  
• 修改函数：  
alter function 函数名 \[charactristic…\]  
characteristic:  
{contains sql|no sql|reads sql data|modifies sql data}  
|sql security{definer|invoker}  
|comment ‘string’  
删除存储过程或函数  
说明：一次只能删除一个存储过程或者函数，并且要求有该过程或函数的alter routine 权限  
删除存储过程：  
drop procedure \[if exists\] 存储过程名  
删除函数：  
drop function \[if exists\] 函数名  
查看存储过程或函数  
1.查看存储过程或函数的状态：  
show {procedure|function} status like 存储过程或函数名  
2.查看存储过程或函数的定义：  
show create {procedure|function} 存储过程或函数名  
3.通过查看information\_schema.routines了解存储过程和函数的信息（了解）  
select \* from rountines where rounine\_name =存储过程名|函数名  
**三十一、数据库设计**  
为什么需要设计数据库  
良好的数据库设计  
\-节省数据的存储空间  
\-能够保证数据的完整性  
\-方便进行数据库应用系统的开发  
软件项目开发周期中数据库设计

**设计数据库的步骤**  
收集信息  
与该系统有关人员进行交流、座谈，充分了解用户需求，理解数据库需要完成的任务  
标识实体 （Entity）  
标识数据库要管理的关键对象或实体，实体一般是名词  
标识每个实体的属性（Attribute）  
标识实体之间的关系（Relationship）  
**E-R图**

转化E-R图为数据库模型图  
将各实体转换为对应的表，将各属性转换为各表对应的列  
标识每个表的主键列  
在表之间建立主外键，体现实体

**为什么需要数据规范化**  
不合规范的表设计  
信息重复  
更新异常  
插入异常  
无法正确表示信息  
删除异常  
丢失有效信息  
第一范式 (1st NF)  
第一范式的目标是确保每列的原子性  
如果每列都是不可再分的最小数据单元（也称为最小的原子单元），则满足第一范式（1NF）

第二范式 (2nd NF)  
第二范式要求每个表只描述一件事情

第三范式 (3nd NF)  
如果一个关系满足2NF，并且除了主键以外的其他列都不传递依赖于主键列，则满足第三范式（3NF）