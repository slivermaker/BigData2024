

【版权声明】版权所有，请勿转载！  
【相关文章推荐】[《大数据软件安装和基础编程实践指南》，详细指导VMWare、Ubuntu、Hadoop、HDFS、HBase、Hive、MapReduce、Spark、Flink的安装和基础编程](https://dblab.xmu.edu.cn/blog/4189/)  
作者：厦门大学计算机系林子雨副教授  
E-mail: ziyulin@xmu.edu.cn  
Apache Spark 是一个新兴的大数据处理通用引擎，提供了分布式的内存抽象。Spark 最大的特点就是快，可比 Hadoop MapReduce 的处理速度快 100 倍。本指南将介绍 Spark 的安装与基本使用。

首先需要下载Spark安装文件。访问[Spark官方下载地址](https://archive.apache.org/dist/spark/spark-3.4.0/)，  
下载spark-3.4.0-bin-without-hadoop.tgz文件。也可以直接[点击这里从百度云盘下载软件](https://pan.baidu.com/s/1gw94YHupw9_ZIFszUe6-uQ?pwd=ziyu)（提取码：ziyu）。进入百度网盘后，进入“软件”目录，找到spark-3.4.0-bin-without-hadoop.tgz文件，下载到本地。  
本教程的具体运行环境如下：

-   Hadoop 3.3.5
-   Java JDK 1.8
-   Spark 3.4.0

需要注意的是，本教程内容中Spark采用Local模式进行安装，也就是在单机上运行Spark，因此，在安装Hadoop时，需要按照伪分布式模式进行安装。在单台机器上按照“Hadoop（伪分布式）+Spark（Local模式）”这种方式进行Hadoop和Spark组合环境的搭建，可以较好满足入门级Spark学习的需求

### 安装Hadoop（伪分布式）

Spark的安装过程较为简单，在已安装好 Hadoop 的前提下，经过简单配置即可使用。  
如果仍没有安装Hadoop3.3.5（伪分布式），请访问[Hadoop3.3.5安装教程\_单机/伪分布式配置\_Hadoop3.3.5/Ubuntu22.04(20.04/18.04/16.04)](https://dblab.xmu.edu.cn/blog/4193/),依照教程学习安装即可。

### 安装JAVA JDK

安装Hadoop3.3.5的过程就已经要求安装JAVA JDK1.8了。如果没有，请参考[Hadoop3.3.5安装教程\_单机/伪分布式配置\_Hadoop3.3.5/Ubuntu22.04(20.04/18.04/16.04)](https://dblab.xmu.edu.cn/blog/4193/)进行安装配置。

### 安装Spark（Local模式）

```bash
sudo tar -zxf ~/下载/spark-3.4.0-bin-without-hadoop.tgz -C /usr/local/
cd /usr/local
sudo mv ./spark-3.4.0-bin-without-hadoop/ ./spark
sudo chown -R hadoop:hadoop ./spark          # 此处的 hadoop 为你的用户名
```

安装后，还需要修改Spark的配置文件spark-env.sh

```bash
cd /usr/local/spark
cp ./conf/spark-env.sh.template ./conf/spark-env.sh
```

编辑spark-env.sh文件(vim ./conf/spark-env.sh)，在第一行添加以下配置信息:

```
export SPARK_DIST_CLASSPATH=$(/usr/local/hadoop/bin/hadoop classpath)
```

配置完成后就可以直接使用，不需要像Hadoop运行启动命令。  
通过运行Spark自带的示例，验证Spark是否安装成功。

```bash
cd /usr/local/spark
bin/run-example SparkPi
```

执行时会输出非常多的运行信息，输出结果不容易找到，可以通过 grep 命令进行过滤（命令中的 2>&1 可以将所有的信息都输出到 stdout 中，否则由于输出日志的性质，还是会输出到屏幕中）:

```bash
cd /usr/local/spark
bin/run-example SparkPi 2>&1 | grep "Pi is"
```

这里涉及到Linux Shell中管道的知识，详情可以参考[Linux Shell中的管道命令](https://dblab.xmu.edu.cn/blog/824-2/)  
过滤后的运行结果如下图示，可以得到π 的 5 位小数近似值：  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2016/01/spark-quick-start-guide-02-sparkpi.png)

## 二、使用 Spark Shell 编写代码

学习Spark程序开发，建议首先通过spark-shell交互式学习，加深Spark程序开发的理解。  
Spark shell 提供了简单的方式来学习 API，也提供了交互的方式来分析数据。

### 启动Spark Shell

```bash
cd /usr/local/spark
bin/spark-shell
```

启动spark-shell后，会自动创建名为sc的SparkContext对象和名为spark的SparkSession对象,如图：  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE9-3-Spark-Shell%E6%A8%A1%E5%BC%8F.png)

### 加载文本文件

spark创建sc，可以加载本地文件和HDFS文件创建RDD。这里用Spark自带的本地文件README.md文件测试。

```scala
val textFile = sc.textFile("file:///usr/local/spark/README.md")
```

加载HDFS文件和本地文件都是使用textFile，区别是添加前缀(hdfs://和file:///)进行标识。

### 简单RDD操作

```scala
//获取RDD文件textFile的第一行内容
textFile.first()
//获取RDD文件textFile所有项的计数
textFile.count()
//抽取含有“Spark”的行，返回一个新的RDD
val lineWithSpark = textFile.filter(line => line.contains("Spark"))
//统计新的RDD的行数
lineWithSpark.count()
```

可以通过组合RDD操作进行组合，可以实现简易MapReduce操作

```scala
//找出文本中每行的最多单词数
textFile.map(line => line.split(" ").size).reduce((a, b) => if (a > b) a else b)
```

更多RDD的操作，请访问Spark官方文档[RDD操作](http://spark.apache.org/docs/latest/programming-guide.html#transformations)

### 退出Spark Shell

输入exit，即可退出spark shell

## 三、独立应用程序编程

接着我们通过一个简单的应用程序 SimpleApp 来演示如何通过 Spark API 编写一个独立应用程序。使用 Scala 编写的程序需要使用 sbt （或者Maven）进行编译打包，相应地，Java 程序使用 Maven 编译打包，而 Python 程序通过 spark-submit 直接提交。

### （一）使用sbt对Scala独立应用程序进行编译打包

#### 1\. 安装sbt

使用Scala语言编写的Spark程序，需要使用sbt进行编译打包。Spark中没有自带sbt，需要单独安装。可以到“[http://www.scala-sbt.org](http://www.scala-sbt.org/)”下载sbt安装文件sbt-1.9.0.tgz。

假设下载后的安装包sbt-1.9.0.tgz保存在了“~/Downloads”目录下。  
这里我们把sbt安装到“/usr/local/sbt”目录下，请使用hadoop用户登录Linux系统，新建一个终端，在终端中执行如下命令：

```bash
sudo mkdir /usr/local/sbt             　　　# 创建安装目录
cd ~/Downloads 
sudo tar -zxvf ./sbt-1.9.0.tgz -C /usr/local 
cd /usr/local/sbt
sudo chown -R hadoop /usr/local/sbt  　　 # 此处的hadoop为系统当前用户名
cp ./bin/sbt-launch.jar ./  #把bin目录下的sbt-launch.jar复制到sbt安装目录下
```

接着在安装目录中使用下面命令创建一个Shell脚本文件，用于启动sbt：

```bash
vim /usr/local/sbt/sbt
```

该脚本文件中的代码如下：

```
#!/bin/bash
SBT_OPTS="-Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256M"
java $SBT_OPTS -jar `dirname $0`/sbt-launch.jar "$@"
```

保存后，还需要为该Shell脚本文件增加可执行权限：

```bash
chmod u+x /usr/local/sbt/sbt
```

然后，可以使用如下命令查看sbt版本信息：

```bash
cd /usr/local/sbt
./sbt sbtVersion
Java HotSpot(TM) 64-Bit Server VM warning: ignoring option MaxPermSize=256M; support was removed in 8.0
[warn] No sbt.version set in project/build.properties, base directory: /usr/local/sbt
[info] Set current project to sbt (in build file:/usr/local/sbt/)
[info] 1.9.0
```

![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE9-4-%E6%9F%A5%E7%9C%8Bsbt%E7%89%88%E6%9C%AC%E4%BF%A1%E6%81%AF.png)  
如果能够返回上述信息，就说明安装成功了。但是，这个过程会很漫长，甚至很坎坷，会花很长时间。  
但是，只要安装成功，第2次开始运行“./sbt sbtVersion”和编译打包命令，速度就比较快了。  
之所以第一次运行sbt编译打包命令速度很慢，是因为这个过程需要到国外网站下载很多的依赖包，而国外网站下载速度很慢，所以，需要耗费很长时间。为了加快速度，可以尝试更改为国内的仓库地址，具体方法可以参考博客：[安装最新版sbt工具方法和体会](https://dblab.xmu.edu.cn/blog/2546-2/)。

#### 2\. Scala应用程序代码

在终端中执行如下命令创建一个文件夹 sparkapp 作为应用程序根目录：

```bash
cd ~           # 进入用户主文件夹
mkdir ./sparkapp        # 创建应用程序根目录
mkdir -p ./sparkapp/src/main/scala     # 创建所需的文件夹结构
```

在 ./sparkapp/src/main/scala 下建立一个名为 SimpleApp.scala 的文件（`vim ./sparkapp/src/main/scala/SimpleApp.scala`），添加代码如下：

```scala
/* SimpleApp.scala */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
 
object SimpleApp {
        def main(args: Array[String]) {
            val logFile = "file:///usr/local/spark/README.md" // Should be some file on your system
            val conf = new SparkConf().setAppName("Simple Application")
            val sc = new SparkContext(conf)
            val logData = sc.textFile(logFile, 2).cache()
            val numAs = logData.filter(line => line.contains("a")).count()
            val numBs = logData.filter(line => line.contains("b")).count()
            println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
        }
    }
```

该程序计算 /usr/local/spark/README 文件中包含 "a" 的行数 和包含 "b" 的行数。代码第8行的 /usr/local/spark 为 Spark 的安装目录，如果不是该目录请自行修改。不同于 Spark shell，独立应用程序需要通过 `val sc = new SparkContext(conf)` 初始化 SparkContext，SparkContext 的参数 SparkConf 包含了应用程序的信息。

该程序依赖 Spark API，因此我们需要通过 sbt 进行编译打包。 在~/sparkapp这个目录中新建文件simple.sbt，命令如下：

```bash
cd ~/sparkapp
vim simple.sbt
```

在simple.sbt中添加如下内容，声明该独立应用程序的信息以及与 Spark 的依赖关系：

```
name := "Simple Project"
version := "1.0"
scalaVersion := "2.12.17"
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.4.0"
```

文件 simple.sbt 需要指明 Spark 和 Scala 的版本。在上面的配置信息中，scalaVersion用来指定scala的版本，sparkcore用来指定spark的版本，这两个版本信息都可以在之前的启动 Spark shell 的过程中，从屏幕的显示信息中找到。下面就是笔者在启动过程当中，看到的相关版本信息（备注：屏幕显示信息会很长，需要往回滚动屏幕仔细寻找信息）。

![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE9-3-Spark-Shell%E6%A8%A1%E5%BC%8F.png)

#### 3\. 使用 sbt 打包 Scala 程序

为保证 sbt 能正常运行，先执行如下命令检查整个应用程序的文件结构：

```bash
cd ~/sparkapp
find .
```

文件结构应如下图所示：  
![SimpleApp的文件结构](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2016/01/spark-quick-start-guide-07-directory-structure.png)

接着，我们就可以通过如下代码将整个应用程序打包成 JAR（首次运行同样需要下载依赖包 ）：

```bash
/usr/local/sbt/sbt package
```

打包成功的话，会输出如下图内容：  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE9-5-sbt%E6%89%93%E5%8C%85%E6%89%A7%E8%A1%8C%E7%BB%93%E6%9E%9C.png)

生成的 jar 包的位置为 ~/sparkapp/target/scala-2.12/simple-project\_2.12-1.0.jar。

#### 4\. 通过 spark-submit 运行程序

最后，我们就可以将生成的 jar 包通过 spark-submit 提交到 Spark 中运行了，命令如下：

```bash
/usr/local/spark/bin/spark-submit --class "SimpleApp" ~/sparkapp/target/scala-2.12/simple-project_2.12-1.0.jar
# 上面命令执行后会输出太多信息，可以不使用上面命令，而使用下面命令查看想要的结果
/usr/local/spark/bin/spark-submit --class "SimpleApp" ~/sparkapp/target/scala-2.12/simple-project_2.12-1.0.jar 2>&1 | grep "Lines with a:"
```

最终得到的结果如下：

```
Lines with a: 62, Lines with b: 31
```

自此，就完成了你的第一个 Spark 应用程序了。

### （二）使用Maven对Java独立应用程序进行编译打包

#### 1\. 安装Maven

Ubuntu中没有自带安装maven，需要手动安装maven。可以访问[maven官方下载](https://maven.apache.org/download.cgi#Files)自己下载。这里直接给出[apache-maven-3.9.2-bin.zip的下载地址](https://downloads.apache.org/maven/maven-3/3.9.2/binaries/apache-maven-3.9.2-bin.zip),直接点击下载即可。或者也可以[点击这里](https://pan.baidu.com/s/1gw94YHupw9_ZIFszUe6-uQ?pwd=ziyu "点击这里")从百度网盘下载文件（提取码是ziyu，进入百度网盘后，在“软件”目录下找到Maven安装文件）。  
这里选择安装在/usr/local/maven目录中：

```bash
sudo unzip ~/Downloads/apache-maven-3.9.2-bin.zip -d /usr/local
cd /usr/local
sudo mv apache-maven-3.9.2/ ./maven
sudo chown -R hadoop ./maven
```

#### 2\. Java应用程序代码

在终端执行如下命令创建一个文件夹sparkapp2作为应用程序根目录

```bash
cd ~ #进入用户主文件夹
mkdir -p ./sparkapp2/src/main/java
```

在 ./sparkapp2/src/main/java 下建立一个名为 SimpleApp.java 的文件（vim ./sparkapp2/src/main/java/SimpleApp.java），添加代码如下：

```java
/*** SimpleApp.java ***/
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.SparkConf;
 
public class SimpleApp {
    public static void main(String[] args) {
        String logFile = "file:///usr/local/spark/README.md"; // Should be some file on your system
        SparkConf conf=new SparkConf().setMaster("local").setAppName("SimpleApp");
        JavaSparkContext sc=new JavaSparkContext(conf);
        JavaRDD<String> logData = sc.textFile(logFile).cache(); 
        long numAs = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) { return s.contains("a"); }
        }).count(); 
        long numBs = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) { return s.contains("b"); }
        }).count(); 
        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
    }
}
```

该程序依赖Spark Java API,因此我们需要通过Maven进行编译打包。在./sparkapp2目录中新建文件pom.xml，命令如下：

```bash
cd ~/sparkapp2
vim pom.xml
```

在pom.xml文件中添加内容如下，声明该独立应用程序的信息以及与Spark的依赖关系：

```

<!-- <project>
    <groupId>cn.edu.xmu</groupId>
    <artifactId>simple-project</artifactId>
    <modelVersion>4.0.0</modelVersion>
    <name>Simple Project</name>
    <packaging>jar</packaging>
    <version>1.0</version>
    <repositories>
        <repository>
            <id>jboss</id>
            <name>JBoss Repository</name>
            <url>http://repository.jboss.com/maven2/</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency> <!-- Spark dependency -->
            <groupId>org.apache.spark</groupId>
            <artifactId>spark-core_2.12</artifactId>
            <version>3.4.0</version>
        </dependency>
    </dependencies>
</project> -->
<project>
    <groupId>cn.edu.xmu</groupId>
    <artifactId>simple-project</artifactId>
    <modelVersion>4.0.0</modelVersion>
    <name>Simple Project</name>
    <packaging>jar</packaging>
    <version>1.0</version>
    <repositories>
        <repository>
            <!-- <id>jboss</id>
            <name>JBoss Repository</name>
            <url>http://repository.jboss.com/maven2/</url> -->
            <id>alimaven</id>

 	        <name>aliyun maven</name>

 	        <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency> <!-- Spark dependency -->
            <groupId>org.apache.spark</groupId>
            <artifactId>spark-core_2.12</artifactId>
            <version>3.5.1</version>
        </dependency>
    </dependencies>
</project>
```

#### 3\. 使用Maven打Java程序

为了保证maven能够正常运行，先执行如下命令检查整个应用程序的文件结构:

```bash
cd ~/sparkapp2
find .
```

文件结构如下图：  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2016/07/3-1.png)  
接着，我们可以通过如下代码将这整个应用程序打包成Jar(注意：电脑需要保持连接网络的状态，而且首次运行同样下载依赖包，同样消耗几分钟的时间):

```bash
cd ~/sparkapp2
/usr/local/maven/bin/mvn package
```

如出现下信息，说明生成Jar包成功：

```
[INFO]----------------------------------------
[INFO] BUILD SUCCESS
[INFO]----------------------------------------
[INFO] Total time: 3.177 s
[INFO] Finished at: 2023-07-13T19:30:03-07:00
[INFO]----------------------------------------
```

如果运行Maven编译打包过程很慢，是因为需要到国外网站下载很多的依赖包，国外网站速度很慢，因此，要花费很长时间。为了加快速度，可以更改为国内的仓库地址，具体可以参考博客：[将Maven源改为国内阿里云仓库](https://dblab.xmu.edu.cn/blog/2758/)。

#### 4\. 通过spark-submit 运行程序

最后，可以通过将生成的jar包通过spark-submit提交到Spark中运行，如下命令：

```bash
/usr/local/spark/bin/spark-submit --class "SimpleApp" ~/sparkapp2/target/simple-project-1.0.jar
# 上面命令执行后会输出太多信息，可以不使用上面命令，而使用下面命令查看想要的结果
/usr/local/spark/bin/spark-submit --class "SimpleApp" ~/sparkapp2/target/simple-project-1.0.jar 2>&1 | grep "Lines with a"
```

最后得到的结果如下:

```
Lines with a: 72, Lines with b: 39
```

### （三）使用Maven对Scala独立应用程序进行编译打包

#### 1\. 安装Maven

（备注：如果已经安装Maven，就不用重复安装了）  
Ubuntu中没有自带安装maven，需要手动安装maven。可以访问[maven官方下载](https://maven.apache.org/download.cgi#Files)自己下载。这里直接给出[apache-maven-3.9.2-bin.zip的下载地址](https://downloads.apache.org/maven/maven-3/3.9.2/binaries/apache-maven-3.9.2-bin.zip),直接点击下载即可。或者也可以[点击这里](https://pan.baidu.com/s/1gw94YHupw9_ZIFszUe6-uQ?pwd=ziyu "点击这里")从百度网盘下载文件（提取码是ziyu，进入百度网盘后，在“软件”目录下找到Maven安装文件）。  
这里选择安装在/usr/local/maven目录中：

```bash
sudo unzip ~/Downloads/apache-maven-3.9.2-bin.zip -d /usr/local
cd /usr/local
sudo mv apache-maven-3.9.2/ ./maven
sudo chown -R hadoop ./maven
```

#### 2\. Scala应用程序代码

在终端中执行如下命令创建一个文件夹 sparkapp3作为应用程序根目录：

```bash
cd ~           # 进入用户主文件夹
mkdir ./sparkapp3        # 创建应用程序根目录
mkdir -p ./sparkapp3/src/main/scala     # 创建所需的文件夹结构
```

在 ./sparkapp3/src/main/scala 下建立一个名为 SimpleApp.scala 的文件（`vim ./sparkapp3/src/main/scala/SimpleApp.scala`），添加代码如下：

```scala
/* SimpleApp.scala */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
 
object SimpleApp {
        def main(args: Array[String]) {
            val logFile = "file:///usr/local/spark/README.md" // Should be some file on your system
            val conf = new SparkConf().setAppName("Simple Application")
            val sc = new SparkContext(conf)
            val logData = sc.textFile(logFile, 2).cache()
            val numAs = logData.filter(line => line.contains("a")).count()
            val numBs = logData.filter(line => line.contains("b")).count()
            println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
        }
    }
```

该程序计算 /usr/local/spark/README 文件中包含 "a" 的行数 和包含 "b" 的行数。代码第8行的 /usr/local/spark 为 Spark 的安装目录，如果不是该目录请自行修改。不同于 Spark shell，独立应用程序需要通过 `val sc = new SparkContext(conf)` 初始化 SparkContext，SparkContext 的参数 SparkConf 包含了应用程序的信息。

#### 3.使用Maven进行编译打包

该程序依赖Spark Java API,因此我们需要通过Maven进行编译打包。在./sparkapp3目录中新建文件pom.xml，命令如下：

```bash
cd ~/sparkapp3
vim pom.xml
```

然后，在pom.xml文件中添加如下内容，用来声明该独立应用程序的信息以及与Spark的依赖关系：

```
<!-- 
<project>
    <groupId>cn.edu.xmu</groupId>
    <artifactId>simple-project</artifactId>
    <modelVersion>4.0.0</modelVersion>
    <name>Simple Project</name>
    <packaging>jar</packaging>
    <version>1.0</version>
    <repositories>
        <repository>
            <id>jboss</id>
            <name>JBoss Repository</name>
            <url>http://repository.jboss.com/maven2/</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency> <!-- Spark dependency -->
            <groupId>org.apache.spark</groupId>
            <artifactId>spark-core_2.12</artifactId>
            <version>3.4.0</version>
        </dependency>
    </dependencies>

  <build>
    <sourceDirectory>src/main/scala</sourceDirectory>
    <plugins>
      <plugin>
        <groupId>org.scala-tools</groupId>
        <artifactId>maven-scala-plugin</artifactId>
        <executions>
          <execution>
            <goals>
              <goal>compile</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <scalaVersion>2.12.17</scalaVersion>
          <args>
            <arg>-target:jvm-1.8</arg>
          </args>
        </configuration>
    </plugin>
    </plugins>
</build>
</project> -->
<project>
    <groupId>cn.edu.xmu</groupId>
    <artifactId>simple-project</artifactId>
    <modelVersion>4.0.0</modelVersion>
    <name>Simple Project</name>
    <packaging>jar</packaging>
    <version>1.0</version>
    <repositories>
        <repository>
            <!-- <id>jboss</id>
            <name>JBoss Repository</name>
            <url>http://repository.jboss.com/maven2/</url> -->
           <id>alimaven</id>

 	        <name>aliyun maven</name>

 	        <url>http://maven.aliyun.com/nexus/content/groups/public/</url>

        </repository>
    </repositories>
    <dependencies>
        <dependency> <!-- Spark dependency -->
            <groupId>org.apache.spark</groupId>
            <artifactId>spark-core_2.12</artifactId>
            <version>3.5.1</version>
        </dependency>
    </dependencies>

  <build>
    <sourceDirectory>src/main/scala</sourceDirectory>
    <plugins>
      <plugin>
        <groupId>org.scala-tools</groupId>
        <artifactId>maven-scala-plugin</artifactId>
        <executions>
          <execution>
            <goals>
              <goal>compile</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <scalaVersion>2.12.19</scalaVersion>
          <args>
            <arg>-target:jvm-1.8</arg>
          </args>
        </configuration>
    </plugin>
    </plugins>
</build>
</project>

```

为了保证Maven能够正常运行，先执行如下命令检查整个应用程序的文件结构：

```bash
cd  ~/sparkapp3
find  .
```

文件结构应该是类似如下的内容：

```
.
./pom.xml
./src
./src/main
./src/main/scala
./src/main/scala/SimpleApp.scala
```

接下来，我们可以通过如下代码将整个应用程序打包成JAR包（注意：计算机需要保持连接网络的状态，而且首次运行打包命令时，Maven会自动下载依赖包，需要消耗几分钟的时间）：

```bash
cd  ~/sparkapp3    #一定把这个目录设置为当前目录
/usr/local/maven/bin/mvn  package
```

如果屏幕返回如下信息，则说明生成JAR包成功：

```
[INFO] Building jar: /home/linziyu/sparkapp3/target/simple-project-1.0.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 01:08 min
[INFO] Finished at: 2023-07-13T09:21:21+08:00
[INFO] Final Memory: 31M/254M
[INFO] ------------------------------------------------------------------------
```

生成的应用程序JAR包的位置为“~/sparkapp3/target/simple-project-1.0.jar”。  
如果运行Maven编译打包过程很慢，是因为需要到国外网站下载很多的依赖包，国外网站速度很慢，因此，要花费很长时间。为了加快速度，可以更改为国内的仓库地址，具体可以参考博客：[将Maven源改为国内阿里云仓库](https://dblab.xmu.edu.cn/blog/2758/)。

#### 4.通过 spark-submit 运行程序

最后，我们就可以将生成的 jar 包通过 spark-submit 提交到 Spark 中运行了，命令如下：

```bash
/usr/local/spark/bin/spark-submit --class "SimpleApp" ~/sparkapp3/target/simple-project-1.0.jar
#上面命令执行后会输出太多信息，可以不使用上面命令，而使用下面命令查看想要的结果
/usr/local/spark/bin/spark-submit --class "SimpleApp" ~/sparkapp3/target/simple-project-1.0.jar 2>&1 | grep "Lines with a:"
```

最终得到的结果如下：

```
Lines with a: 72, Lines with b: 39
```

到此，就顺利完成 Spark 应用程序的Maven编译打包运行了。