
命令式编程涉及多线程之间的状态共享，需要锁机制实现并发控制
函数式编程不会在多个线程之间共享状态，不需要用锁机制，可以更好并行处理，充分利用多核CPU并行处理能力
```
Scala是一门类Java的多范式语言，它整合了面向对象编程和函数式编程的最佳特性。具体来讲：
Scala运行于Java虚拟机（JVM）之上，并且兼容现有的Java程序，可以与Java类进行互操作，包括调用Java方法，创建Java对象，继承Java类和实现Java接口
Scala是一门纯粹的面向对象的语言。在Scala语言中，每个值都是对象，每个操作都是方法调用。对象的数据类型以及行为由类和特质描述。类抽象机制的扩展有两种途径，一种途径是子类继承，另一种途径是灵活的混入（mixin）机制，这两种途径能避免多重继承的种种问题
Scala也是一门函数式语言。在Scala语言中，每个函数都是一个值，并且和其他类型（如整数、字符串等）的值处于同一地位。Scala提供了轻量级的语法用以定义匿名函数，支持高阶函数，允许嵌套多层函数，并支持柯里化


```
## 变量基础
>scala安装过程可以在B站首页的实况里查看

运行：
```scala
//代码文件为/usr/local/scala/mycode/HelloWorld.scala
object HelloWorld {
  def main(args: Array[String]) {
  println("Hello, world!");
 }
}

```
```shell
$ scalac  HelloWorld.scala
$ scala -classpath . HelloWorld
```

```scala
val i = 123            //123就是整数字面量
val i = 3.14           //3.14就是浮点数字面量
val i = true            //true就是布尔型字面量
val i = 'A'              //'A'就是字符字面量
val i = “Hello”       //“Hello”就是字符串字面量
val I =()              // 空括号()是Unit类型唯一的字面量
//操作符优先级：算术运算符 > 关系运算符 > 逻辑运算符 > 赋值运算符

```
```shell
scala> val myStr = "Hello World!"
myStr: String = Hello World!


scala> val myStr2 : String = "Hello World!"
myStr2: String = Hello World!

#myStr是val变量，因此，一旦初始化以后，就不能再次赋值

scala> myStr = "Hello Scala!"
<console>:27: error: reassignment to val
          myStr = "Hello Scala!"
          
scala> val a = "Xiamen University"
a: String = Xiamen University
scala> var a = 50
a: Int = 50

```
注意：在REPL环境下，可以重复使用同一个变量名来定义变量，而且变量前的修饰符和其类型都可以不一致，REPL会以最新的一个定义为准

```shell
scala> println("hello");println("world")
hello
world


scala> val i = 34
i: Int = 34
scala> val f=56.5
f: Double = 56.5
scala> printf("I am %d years old and weight %.1f Kg.","Li Lie",i,f)
I am 34 years old and weight 56.5 Kg.

```

插值字符串
基本语法：s " …$变量名… "  或 f " …$变量名%格式化字符… "

```shell
scala> val i=10
i: Int = 10
scala> val f=3.5
f: Double = 3.5452
scala> val s="hello"
s: String = hello
scala> println(s"$s:i=$i,f=$f")    //s插值字符串
hello:i=10,f=3.5452
scala> println(f"$s:i=$i%-4d,f=$f%.1f")   //f插值字符串
hello:i=10  ,f=3.5
```

读写文件
```shell
scala> import java.io.PrintWriter
scala> val outputFile = new PrintWriter("test.txt")
scala> outputFile.println("Hello World")
scala> outputFile.print("Spark is good")
scala> outputFile.close()
```
![](imge/md-20240310103518.png)


## 控制结构
if大致与java相同
If(x>0) 1 else -1 相当于c或Java里的三元操作符：x>0? 1: -1

![](imge/md-20240310103701.png)

![](imge/md-20240310103749.png)
> for (变量 <- 表达式 if 条件表达式) 语句块

![](imge/md-20240310103826.png)
```shell
scala> val r=for (i <- Array(1,2,3,4,5) if i%2==0) yield { println(i); i}
2
4
r: Array[Int] = Array(2,4)
```
Scala仍使用try-catch结构来捕获异常
```scala
import java.io.FileReader 
import java.io.FileNotFoundException 
import java.io.IOException 
try { 
  val f = new FileReader("input.txt") 
    // 文件操作 
} catch { 
  case ex: FileNotFoundException => 
    // 文件不存在时的操作 
  case ex: IOException => 
   // 发生I/O错误时的操作
} finally { 
  file.close() // 确保关闭文件 
} 
```
Scala提供了一个Breaks类（位于包scala.util.control）。Breaks类有两个方法用于对循环结构进行控制，即breakable和break。
 将需要控制的语句块作为参数放在breakable后面，然后，其内部在某个条件满足时调用break方法，程序将跳出breakable方法。
```scala
//代码文件为/usr/local/scala/mycode/TestBreak.scala
import util.control.Breaks._ //导入Breaks类的所有方法
val array = Array(1,3,10,5,4)
breakable{
for(i<- array){
        if(i>5) break //跳出breakable，终止for循环，相当于Java中的break
        println(i)
    }
}
// 上面的for语句将输出1，3
 
for(i<- array){
    breakable{
        if(i>5) break 
        //跳出breakable，终止当次循环，相当于Java的continue		println(i)
    }
}// 上面的for语句将输出1，3，5，4

```


## 数据结构
```scala
val intValueArr = new Array[Int](3)  //声明一个长度为3的整型数组，每个数组元素初始化为0

intValueArr(0) = 12 //给第1个数组元素赋值为12
intValueArr(1) = 45  //给第2个数组元素赋值为45
intValueArr(2) = 33 //给第3个数组元素赋值为33

val myStrArr = new Array[String](3) //声明一个长度为3的字符串数组，每个数组元素初始化为null

 myStrArr(0) = "BigData"
 myStrArr(1) = "Hadoop"
 myStrArr(2) = "Spark"
 for (i <- 0 to 2) println(myStrArr(i))

 val intValueArr = Array(12,45,33)
 val myStrArr = Array("BigData","Hadoop","Spark")

```
```scala
val  myMatrix = Array.ofDim[Int](3,4)  //类型实际就是Array[Array[Int]]
val  myCube = Array.ofDim[String](3,2,4)  //类型实际是Array[Array[Array[String]]]


scala> val a = new Array[Array[Int]](2)
a: Array[Array[Int]] = Array(null, null)

scala> a(0) = new Array[Int](3)

scala> a(1) = new Array[Int](3)

scala> a
res4: Array[Array[Int]] = Array(Array(0, 0, 0), Array(0, 0, 0))
```
![](imge/md-20240310104656.png)

如果需要在方法里返回多个不同类型的对象，Scala可以通过返回一个元组来实现使用下划线“_”加上从1开始的索引值，来访问元组的元素

![](imge/md-20240310104738.png)在Iterable下的继承层次包括三个特质，分别是序列（Seq）、映射（Map）和 集合（Set），这三种容器最大的区别是其元素的索引方式，序列是按照从0开始的整数进行索引的，映射是按照键值进行索引的，而集合是没有索引的。

### 序列
序列（Sequence）: 元素可以按照特定的顺序访问的容器。序列中每个元素均带有一个从0开始计数的固定索引位置
序列容器的根是collection.Seq特质。其具有两个子特质 LinearSeq和IndexedSeq。LinearSeq序列具有高效的 head 和 tail 操作，而IndexedSeq序列具有高效的随机存储操作
实现了特质LinearSeq的常用序列有列表（List）和队列（Queue）。实现了特质IndexedSeq的常用序列有可变数组（ArrayBuffer）和向量（Vector）

不同于Java的java.util.List，scala的List一旦被定义，其值就不能改变，因此声明List时必须初始化
不能用new来建立List（原型：sealed abstract class List[+A] ）
补充相同类型：对于包括List在内的所有容器类型，如果没有显式指定元素类型，Scala会自动选择所有初始值的最近公共类型来作为元素的类型。因为Scala的所有对象都来自共同的根Any，因此，原则上容器内可以容纳任意不同类型的成员。例如：val x=List(1,3.4,"Spark")
var strList=List("BigData","Hadoop","Spark")


构造列表常用的方法是通过在已有列表前端增加元素，使用的操作符为::，例如：**：：是向右结合的（：结尾的操作符都是向右结合）**
val otherList="Apache"::strList
执行该语句后strList保持不变，而otherList将成为一个新的列表：
List("Apache","BigData","Hadoop","Spark")
Scala还定义了一个空列表对象Nil，借助Nil，可以将多个元素用操作符::串起来初始化一个列表
val intList = 1::2::3::Nil与val intList = List(1,2,3)等效
注意：除了head、tail操作是常数时间O(1)，其它按索引访问的操作都需要从头开始遍历，因此是线性时间复杂度O(N)。


Vetor可以实现所有访问操作都是常数时间。
+： 和 :+ 是Seq的方法，执行后vector本身没变

```
scala> val vec1=Vector(1,2)
vec1: scala.collection.immutable.Vector[Int] = Vector(1, 2, 3)
scala> val vec2 = 3 +: 4 +: vec1
vec2: scala.collection.immutable.Vector[Int] = Vector(3, 4, 1, 2)
scala> val vec3 = vec2 :+ 5
vec3: scala.collection.immutable.Vector[Int] = Vector(3, 4, 1, 2, 5)
scala> vec3(3)
res6: Int = 2
```

 Range类：一种特殊的、带索引的不可变数字等差序列。其包含的值为从给定起点按一定步长增长(减小)到指定终点的所有数值
 Range可以支持创建不同数据类型的数值序列，包括Int、Long、Float、Double、Char、BigInt和BigDecimal等
> scala> val r=new Range(1,5,1)
创建一个从1到5的数值序列，包含区间终点5，步长为1


### 集合
集合(set)：不重复元素的容器（collection）
列表（List）中的元素是按照插入的先后顺序来组织的，但是，“集合”中的元素并不会记录元素的插入顺序，而是以“哈希”方法对元素的值进行组织，所以，它允许你快速地找到某个元素
集合包括可变集和不可变集，分别位于scala.collection.mutable包和scala.collection.immutable包，缺省情况下创建的是不可变集
```scala
var mySet = Set("Hadoop","Spark")
mySet += "Scala" 

//不可变
import scala.collection.mutable.Set
val myMutableSet = Set("Database","BigData")
myMutableSet += "Cloud Computing" 
```

### 映射
Scala 的映射包含了可变的和不可变的两种版本，分别定义在包scala.collection.mutable 和scala.collection.immutable 里。默认情况下，Scala中使用不可变的映射。如果想使用可变映射，必须明确地导入scala.collection.mutable.Map

```scala
val university = Map("XMU" -> "Xiamen University", "THU" -> "Tsinghua University","PKU"->"Peking University")

println(university("XMU"))
val xmu = if (university.contains("XMU")) university("XMU") else 0
```
下面是可变映射
```
import scala.collection.mutable.Map
val university2 = Map("XMU" -> "Xiamen University", "THU" -> "Tsinghua University","PKU"->"Peking University")
university2("XMU") = "Ximan University" //更新已有元素的值
university2("FZU") = "Fuzhou University" //添加新元素

university2 + = ("TJU"->"Tianjin University") //添加一个新元素
university2 + = ("SDU"->"Shandong University","WHU"->"Wuhan University") //同时添加两个新元素
```

### 迭代器
```scala
val iter = Iterator("Hadoop","Spark","Scala")
while (iter.hasNext) {
    println(iter.next())
}
```
建议：除next和hasnext方法外，在对一个迭代器调用了某个方法后，不要再次使用该迭代器



