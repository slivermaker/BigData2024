本文涉及的产品

RDS MySQL Serverless 基础系列，0.5-2RCU 50GB

RDS MySQL Serverless 高可用系列，价值2615元额度，1个月

**简介：** 数据库数据库:DataBase ( DB)，是存储和管理数据的仓库。数据库管理系统:DataBase Management System (DBMS)，操纵和管理数据库的大型软件。SQL: Structured Query Language，操作关系型数据库的编程语言，定义了一套操作关系型数据库统一标准。

### 数据库

数据库:DataBase ( **DB**)，是存储和管理数据的仓库。

数据库管理系统:DataBase Management System (**DBMS**)，操纵和管理数据库的大型软件。

SQL: Structured Query Language，操作[关系型数据库](https://so.csdn.net/so/search?q=%E5%85%B3%E7%B3%BB%E5%9E%8B%E6%95%B0%E6%8D%AE%E5%BA%93&spm=1001.2101.3001.7020)的编程语言，定义了一套操作关系型数据库统一标准。

![image.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_c63e884df17b4ec89631cab31f58c087.png "image.png")

### 数据库设计

##### MySQL概述

![image.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_d7edffb166aa40d9851268e899a97b48.png "image.png")

![image.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_b88f752adc3744adb37ea917328f46b8.png "image.png")

##### 数据库设计-DDL

![9c9daafc93bc46ee8e4b9b2bd26499b1.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_846fe0e7f5254895903b5d1bdd13ced3.png "9c9daafc93bc46ee8e4b9b2bd26499b1.png")

![c8542f07e67043c6a933a6088b6264ac.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_eaa26279f75248ce91a4d12a6dbddbe2.png "c8542f07e67043c6a933a6088b6264ac.png")

![image.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_54977716965f4996a689e949e9e8cf4c.png "image.png")

### 数据库操作

##### 数据库操作-DML

![8bf82a43b8b843d6ae9b5dd82384c0e4.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_dd0f23cbc6394b6080596541801b8eaa.png "8bf82a43b8b843d6ae9b5dd82384c0e4.png")

![feb4e2e468c54d44ad5fdace91f25fa2.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_9b888c84465249908a3953f991b8b8fd.png "feb4e2e468c54d44ad5fdace91f25fa2.png")

![4ea667a141f6485994a3b12c28fffc73.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_a7f48e2840f54205a11d076a9465104d.png "4ea667a141f6485994a3b12c28fffc73.png")

![image.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_25f8a1f0ca734d249f470b99bf4f58b7.png "image.png")

##### 数据库操作-DQL

![43618975b81c4d51bd1712dc7229bf0e.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_1d74b0a974804bcf9d4f0736814af4cc.png "43618975b81c4d51bd1712dc7229bf0e.png")

![596e0ab8272c4318857e3c43d9f4f08e.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_7159e73c52ea428789869de007746fe8.png "596e0ab8272c4318857e3c43d9f4f08e.png")

![c74c0bfe8b1241e7a4cdacaa63e04eed.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_d865d8abf412401f8e725b5d775c9029.png "c74c0bfe8b1241e7a4cdacaa63e04eed.png")

![96db7be4dfca41839781ecd29b58ca6e.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_5e89b9c3be0149f1a93c0252b4b27739.png "96db7be4dfca41839781ecd29b58ca6e.png")

![96db7be4dfca41839781ecd29b58ca6e.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_585ce67b50f342079861caea9688100b.png "96db7be4dfca41839781ecd29b58ca6e.png")

![74e8c49d2ca54b70ba8d1d1b6a04b074.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_4de0ea04210f48b2bd32a398f709d0c8.png "74e8c49d2ca54b70ba8d1d1b6a04b074.png")

![9b851adfad7f4657b3fdef183fedc705.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_6dd1af270dbf465ab30c35d826eb420a.png "9b851adfad7f4657b3fdef183fedc705.png")

![d53b229d453a41cba6c239f81a0fd896.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_2df3257fd2bf4d829309af24f65088b3.png "d53b229d453a41cba6c239f81a0fd896.png")

##### 多表设计

> 1.一对多
> 
> 在多的一方添加外键，关联另外一方的主键。
> 
> 2.一对一
> 
> 任意一方，添加外键，关联另外一方的主键。
> 
> 3．多对多
> 
> 通过中间表来维护，中间表的两个外键，分别关联另外两张表的主键。

![44f287ace5404e0095a576819ff46f95.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_3bff57fda2f64263ba59f3e52fd4abad.png "44f287ace5404e0095a576819ff46f95.png")

![image.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_a8849dd0e34448898c48166d10abe0b8.png "image.png")

##### 多表查询

![e602dc58792f4ebeb61533b4083861d8.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_a4c6dac19dc640a0851b9aae804f2c97.png "e602dc58792f4ebeb61533b4083861d8.png")

内连接

隐式内连接: select 字段列表 from 表1,表2 where 条件…;

显式内连接: select 字段列表 from 表1 \[ inner \] join 表2 on 连接条件…;

外连接

左外连接: select 字段列表 from 表1 left \[ outer \] join 表2 on 连接条件…;

右外连接: selec t字段列表 from 表1 right \[ outer \] join 表2 on 连接条件…;

子查询

介绍:SQL语句中嵌套select语句，称为嵌套查询，又称子查询。

形式: select \* from t1 where column1 = ( select column1 from t2 …);

子查询外部的语句可以是insert / update / delete / select的任何一个，最常见的是select

**相关查询的练习**

```
<span><span></span><span>-- ============================= 内连接 =============================
</span></span><span><span></span><span>-- A. 查询员工的姓名 , 及所属的部门名称 (隐式内连接实现)
</span></span><span><span></span><span>SELECT tb_emp.`name`as 姓名,tb_dept.`name` AS 部门 FROM tb_emp,tb_dept WHERE tb_emp.dept_id=tb_dept.id
</span></span><span><span></span><span>
</span></span><span><span></span><span>-- B. 查询员工的姓名 , 及所属的部门名称 (显式内连接实现)
</span></span><span><span></span><span>SELECT tb_emp.`name`as 姓名,tb_dept.`name` AS 部门 FROM
</span></span><span><span></span><span>tb_emp INNER JOIN tb_dept on tb_emp.dept_id=tb_dept.id
</span></span><span><span></span><span>
</span></span><span><span></span><span>-- =============================== 外连接 ============================
</span></span><span><span></span><span>-- A. 查询员工表 所有 员工的姓名, 和对应的部门名称 (左外连接)
</span></span><span><span></span><span>SELECT tb_emp.`name` AS 姓名,tb_dept.`name` AS 部门 FROM 
</span></span><span><span></span><span>tb_emp LEFT JOIN tb_dept ON tb_emp.dept_id=tb_dept.id
</span></span><span><span></span><span>
</span></span><span><span></span><span>-- B. 查询部门表 所有 部门的名称, 和对应的员工名称 (右外连接)
</span></span><span><span></span><span>SELECT tb_emp.`name` AS 姓名,tb_dept.`name` AS 部门 FROM 
</span></span><span><span></span><span>tb_emp RIGHT JOIN tb_dept ON tb_emp.dept_id=tb_dept.id
</span></span><span><span></span><span>
</span></span><span><span></span><span>-- ========================= 子查询 ================================
</span></span><span><span></span><span>-- 标量子查询
</span></span><span><span></span><span>-- A. 查询 "教研部" 的所有员工信息
</span></span><span><span></span><span>SELECT * FROM tb_emp WHERE tb_emp.dept_id=(
</span></span><span><span></span><span>SELECT tb_dept.id FROM tb_dept WHERE tb_dept.`name`='教研部' )
</span></span><span><span></span><span>
</span></span><span><span></span><span>-- B. 查询在 "方东白" 入职之后的员工信息
</span></span><span><span></span><span>SELECT * FROM tb_emp WHERE entrydate&gt;(SELECT entrydate FROM
</span></span><span><span></span><span>tb_emp WHERE `name`='方东白'
</span></span><span><span></span><span>)
</span></span><span><span></span><span>
</span></span><span><span></span><span>-- 列子查询
</span></span><span><span></span><span>-- A. 查询 "教研部" 和 "咨询部" 的所有员工信息
</span></span><span><span></span><span>SELECT * FROM tb_emp WHERE dept_id in (SELECT id FROM tb_dept WHERE `name`in ('教研部','咨询部'))
</span></span><span><span></span><span>
</span></span><span><span></span><span>-- 行子查询
</span></span><span><span></span><span>-- A. 查询与 "韦一笑" 的入职日期 及 职位都相同的员工信息 ;
</span></span><span><span></span><span>SELECT * FROM tb_emp WHERE (entrydate=(SELECT entrydate FROM tb_emp WHERE `name`='韦一笑') and job =(SELECT job FROM tb_emp WHERE  `name`='韦一笑'))
</span></span><span><span></span><span>
</span></span><span><span></span><span>-- 表子查询
</span></span><span><span></span><span>-- A. 查询入职日期是 "2006-01-01" 之后的员工信息 , 及其部门信息
</span></span><span><span></span><span>SELECT tb_emp.*,tb_dept.* FROM tb_emp LEFT JOIN tb_dept ON tb_emp.dept_id=tb_dept.id
</span></span><span><span></span><span> WHERE tb_emp.entrydate&gt;'2006-01-01' 
</span></span><span><span></span><span>
</span></span><span><span></span><span>-- 1. 查询价格低于 10元 的菜品的名称 、价格 及其 菜品的分类名称 .
</span></span><span><span></span><span>SELECT dish.`name`,dish.price ,category.`name` as 分类 FROM dish,category WHERE dish.price&lt;10 AND category.id=dish.category_id
</span></span><span><span></span><span>
</span></span><span><span></span><span>-- 2. 查询所有价格在 10元(含)到50元(含)之间 且 状态为'起售'的菜品名称、价格 及其 菜品的分类名称 (即使菜品没有分类 , 也需要将菜品查询出来).
</span></span><span><span></span><span>SELECT dish.`name`,dish.price,category.`name` FROM dish LEFT JOIN category ON category.id=dish.category_id
</span></span><span><span></span><span>WHERE price BETWEEN 10 and 50  and dish.`status`=1 
</span></span><span><span></span><span>
</span></span><span><span></span><span>-- 3. 查询每个分类下最贵的菜品, 展示出分类的名称、最贵的菜品的价格 .
</span></span><span><span></span><span>SELECT category.`name`,MAX(dish.price) AS 最贵的 FROM dish,category WHERE dish.category_id=category.id GROUP BY category.`name`
</span></span><span><span></span><span>
</span></span><span><span></span><span>-- 4. 查询各个分类下 状态为 '起售' , 并且 该分类下菜品总数量大于等于3 的 分类名称 .
</span></span><span><span></span><span>SELECT category.`name`,COUNT(*)FROM dish,category WHERE category.`status`=1 AND dish.category_id=category.id  
</span></span><span><span></span><span>GROUP BY category.`name` HAVING count(*)&gt;=3
</span></span><span><span></span><span>
</span></span><span><span></span><span>-- 5. 查询出 "商务套餐A" 中包含了哪些菜品 （展示 出套餐名称、价格, 包含的菜品名称、价格、份数）.
</span></span><span><span></span><span>SELECT dish.`name`,dish.price ,setmeal_dish.copies,setmeal.`name` ,setmeal.price FROM dish ,setmeal,setmeal_dish WHERE 
</span></span><span><span></span><span> setmeal_dish.dish_id=dish.id and setmeal_dish.setmeal_id=setmeal.id AND setmeal.`name`='商务套餐A' 
</span></span><span><span></span><span>
</span></span><span><span></span><span>-- 6. 查询出低于菜品平均价格的菜品信息 (展示出菜品名称、菜品价格).
</span></span><span><span></span><span>SELECT dish.`name`,dish.price FROM dish WHERE dish.price &lt; (SELECT AVG(price) FROM dish)
</span></span><span><span></span><span>
</span></span>
```

### 据库优化

##### 事务

> **介绍&操作**
> 
> 事务是一组操作的集合，它是一个不可分割的工作单位。事务会把所有的操作作为一个整体一起向系统提交或撤销操作请求，即这些操作要么同时成功，要么同时失败。默认MySQL的事务是自动提交的，也就是说，当执行一条DML语句，MySQL会立即隐式的提交事务。
> 
> 开启事务: start transaction; / begin ;
> 
> 提交事务:commit;
> 
> 回滚事务:rollback;

**四大特性**

![f92e7ded90c84fd18537def64420f187.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_289100d26b3a488fba7b265f18d629e9.png "f92e7ded90c84fd18537def64420f187.png")

##### 索引

**索引(index)** 是帮助数据库高效获取数据的数据结构。

![4f7444db17324b6a9e0a03e7d289e3aa.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_7adabda1e89b4729a837427c1d204ee5.png "4f7444db17324b6a9e0a03e7d289e3aa.png")

![70099584f97c4930bd8a4f9a58bbfdc0.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_6b6f46f2fa594374b1a90a32748c3b4b.png "70099584f97c4930bd8a4f9a58bbfdc0.png")

![0832283d11604fefa42da3c02e7a5e2f.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_bf216c4ebb7a4fb9a08dc6330fdf071a.png "0832283d11604fefa42da3c02e7a5e2f.png")

![964c5de6e8914d798501f98e6b872a46.png](https://ucc.alicdn.com/pic/developer-ecology/bhvol6g5lbllu_102abf5e1da14e30b471a77daf8503e3.png "964c5de6e8914d798501f98e6b872a46.png")

**语法**

创建索引

create \[ unique \] index索引名on表名(字段名,… );

查看索引

show index from表名;

删除索引

drop index索引名 on表名;

**主键字段，在建表时，会自动创建主键索引。  
添加唯一约束时，数据库实际上会添加唯一索引**

相关实践学习

基于CentOS快速搭建LAMP环境

本教程介绍如何搭建LAMP环境，其中LAMP分别代表Linux、Apache、MySQL和PHP。

全面了解阿里云能为你做什么

阿里云在全球各地部署高效节能的绿色数据中心，利用清洁计算为万物互联的新世界提供源源不断的能源动力，目前开服的区域包括中国（华北、华东、华南、香港）、新加坡、美国（美东、美西）、欧洲、中东、澳大利亚、日本。目前阿里云的产品涵盖弹性计算、数据库、存储与CDN、分析与搜索、云通信、网络、管理与监控、应用服务、互联网中间件、移动服务、视频服务等。通过本课程，来了解阿里云能够为你的业务带来哪些帮助 &nbsp; &nbsp; 相关的阿里云产品：云服务器ECS 云服务器 ECS（Elastic Compute Service）是一种弹性可伸缩的计算服务，助您降低 IT 成本，提升运维效率，使您更专注于核心业务创新。产品详情: https://www.aliyun.com/product/ecs

[![](https://ucc.alicdn.com/pic/developer-ecology/b141c992a2ba42f191ae0df507a13b41.jpg)](https://developer.aliyun.com/topic/ecs/huanshou)