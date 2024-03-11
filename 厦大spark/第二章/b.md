## **包**

Scala中包名和源文件路径不要求一致，它只代表了逻辑上的层级关系，非物理存储上。

**嵌套风格**

表示包层级关系时，Scala提供了另外一种嵌套的风格，嵌套风格声明包如下：

```scala
// 嵌套风格
package com {
​
  import com.shanghai.scala1.Inner1
​
  // 外层包中单例对象
  object Outer {
    var name = "aaron"
    def main(args: Array[String]): Unit = {
      println(Inner1.in)
    }
  }
​
  package shanghai {
    package scala1 {
​
      import com.beijing.scala2.Inner2
​
      // 内层包中定义单例对象
      object Inner1 {
        var in: String = "in"
        def main(args: Array[String]): Unit = {
          println(Outer.name)
          Outer.name = "Ramsey"
          println(Outer.name)
          Inner2.main()
        }
      }
    }
  }
}
​
package com {
  package beijing {
    package scala2 {
      object Inner2 {
        def main(): Unit = {
          println(Outer.name)
          Outer.name = "Wenger"
          println(Outer.name)
        }
      }
    }
  }
}
​
​
​
--------  Output  --------
aaron
Ramsey
Ramsey
Wenger
```

内层包可以直接访问外层包的属性和方法，访问时要加外层包的object名称；外层包访问内层包时则必须import。同一个文件内可以定义多个包，可以看到由于在程序中第一个嵌套包com.shanghai.scala1声明时，已经在外层包com中定义了单例对象Outer，因此第二个嵌套包com.beijing.scala2内层对象也可以直接调用Outer中的属性。

  

**包对象**

Scala中可以定义与包同名的包对象，定义在包对象中的成员，作为其对应包下所有 class 和 object 的共享变量，可以被直接访问。与上面访问外层包中object成员区别就在不用加Object名称，可以直接通过变量名访问。

```scala
package com {
​
  package shanghai {
    package scala1 {
​
      import com.beijing.scala2.Inner2
​
      // 内层包中定义单例对象
      object Inner1 {
        var in: String = "in"
        def main(args: Array[String]): Unit = {
          println(out)
          out = "outer"
          println(out)
          func(in)
          Inner2.main()
        }
      }
    }
  }
}
​
package com {
  package beijing {
    package scala2 {
      object Inner2 {
        def main(): Unit = {
          println(out)
          out = "outerrrrrrr"
          println(out)
        }
      }
    }
  }
}
​
package object com {
  var out: String = "out"
  def func(str: String): Unit = {
    println(str)
  }
}
​
​
--------  Output  --------
out
outer
in
outer
outerrrrrrr
```

包对象中定义的属性如果希望包中可以访问到，需要在同一层级（作用域）下，比如：要在上面程序中定义shanghai的包对象，那该包对象也得在com包下。

  

**导包**

Scala中导包可以局部导入，局部导入的包只在当前上下文有效。

Scala中通配符是下划线 \_ ，不同于Java的 \*。

Scala给类起名：import java.util.{ArrayList=>AL}

导入**相同包的**多个类：import java.util.{HashSet, ArrayList}

屏蔽类:import java.util.{ArrayList =>\_ , \_ }，将ArrayList屏蔽，导入其它所有。

Scala 中还有三个默认导入分别是：

**import java.lang.\_ import scala.\_ import scala.Predef.\_**

比如常用的println就在Predef.\_中。

  

# **类和对象**
## 简单的类

最简单的类的定义形式是：

```
class Counter{
     //这里定义类的字段和方法
}
```

然后，就可以使用new关键字来生成对象：

```
new Counter //或者new Counter()
```

## 给类增加字段和方法

下面我们给这个类增加字段和方法：

```
class Counter {
    private var value = 0
    def increment(): Unit = { value += 1}
    def current(): Int = {value}
}
```

在上面定义中，我们把value字段设置为private，这样它就成为私有字段，外界无法访问，只有在类内部可以访问该字段。如果字段前面什么修饰符都没有，就默认是public，外部可以访问该字段。对于类而言，我们并不需要声明为public，Scala文件中包含的多个类之间，都是彼此可见的。

对于方法的定义，是通过def实现的。上面的代码“def increment(): Unit = { value += 1}”中，increment()是方法，没有参数，冒号后面的Unit是表示返回值的类型，在Scala中不返回任何值，那么就用Unit表示，相当于Java中的void类型。方法的返回值，不需要靠return语句，方法里面的最后一个表达式的值就是方法的返回值，比如，上面current()方法里面只有一条语句“value”，那么，value的值就是该方法的返回值。

因为increment()方法只是对value的值进行了增加1的操作，并没有返回任何值，所以，返回值类型是Unit。Unit后面的等号和大括号后面，包含了该方法要执行的具体操作语句。如果大括号里面只有一行语句，那么也可以直接去掉大括号，写成下面的形式：

```
class Counter {
    private var value = 0
    def increment(): Unit = value += 1 //去掉了大括号
    def current(): Int = {value}  //作为对比，这里依然保留大括号
}
```

或者，还可以去掉返回值类型和等号，只保留大括号，如下：

```
class Counter {
    private var value = 0
    def increment() {value += 1} //去掉了返回值类型和等号，只保留大括号
    def current(): Int = {value} //作为对比，这里依然保留原来形式
}
```

## 创建对象

下面我们新建对象，并调用其中的方法：

```
val myCounter = new Counter
myCounter.increment() //或者也可以不用圆括号，写成myCounter.increment
println(myCounter.current)
```

从上面代码可以看出，Scala在调用无参方法时，是可以省略方法名后面的圆括号的。

## 编译和执行

现在，让我们把上面完整的代码拿到Linux系统中执行。请在登录Linux系统后，打开命令行终端（可以使用快捷组合键Ctr+Alt+T，快速打开命令行终端），进入到“/usr/local/scala/mycode”目录下，然后使用vim编辑器新建一个TestCounter.scala代码文件，如下：

```shell
cd /usr/local/scala/mycode
vim TestCounter.scala
```

在TestCounter.scala中输入以下代码：

```
class Counter {
    private var value = 0
    def increment(): Unit = { value += 1}
    def current(): Int = {value}
}
val myCounter = new Counter
myCounter.increment()
println(myCounter.current)
```

保存后退出vim编辑器。然后，使用scala命令执行这个代码文件：

```
scala TestCounter.scala
```

上面命令执行后，会在屏幕输出“1”。

这是在Linux的Shell命令中执行。实际上，我们也可以进入到Scala解释器下面去执行，首先启动Scala解释器，如下：

```
scala //在终端窗口中输入scala命令进入Scala解释器
```

这时就进入了scala命令提示符状态，可以在里面输入如下命令：

```
scala> :load /usr/local/scala/mycode/TestCounter.scala  //这是输入的命令
Loading /usr/local/scala/mycode/TestCounter.scala... //从这里开始是执行结果
defined class Counter
myCounter: Counter = Counter@17550481
1
```

完成上面操作以后，我们可以退出Scala解释器，回到Linux系统的Shell命令提示符状态，退出Scala解释器的命令如下：

```
scala> :quit
```


下面，我们尝试一下，看看是否可以使用scalac命令对这个TestCounter.scala文件进行编译，如下：

```
scalac TestCounter.scala
```


执行上述scalac命令后，你会发现，会出现一堆错误，无法编译。为什么呢？因为，当我们使用scalac命令对TestCounter.scala进行编译时，必须要求把声明（比如val myCounter = new Counter以及myCounter.increment()等）都封装在对象中，这也是JVM字节码的要求。但是，在TestCounter.scala中，这些声明都没有被封装在对象中，所以，无法编译。

不过，如果我们确实需要把TestCounter.scala编译为JVM字节码，那么，可以使用下面命令：

```
scalac -Xscript Upper1 TestCounter.scala  //编译
scala -classpath . Upper1 //执行
```

执行后会在屏幕上返回结果：1。在上面代码中，-Xscript后面跟着的名称Upper1是你自己定义的main类名称，你愿意起个名字叫Upper2，也是可以的。

好了，还记得我们在前面给大家介绍的HelloWorld程序吗？当时用的是一个包含了main()方法的大家比较熟悉的JVM应用程序，这种方式是本教程中推荐使用的方式，现在，我们采用这种方式重新编写上面的代码。

请在Linux系统的Shell命令提示符状态下，进入到“/usr/local/scala/mycode”目录下，然后使用vim编辑器新建一个TestCounterJVM.scala代码文件，如下：

```
cd /usr/local/scala/mycode
vim TestCounterJVM.scala
```

在TestCounterJVM.scala中输入以下代码：

```
class Counter {
    private var value = 0
    def increment(): Unit = { value += 1}
    def current(): Int = {value}
}
object MyCounter{
    def main(args:Array[String]){
        val myCounter = new Counter
        myCounter.increment()
        println(myCounter.current)
    }
}
```

保存后退出vim编辑器。然后，使用scalac命令编译这个代码文件，并用scala命令执行，如下：

```
scalac TestCounterJVM.scala
scala -classpath . MyCounter //MyCounter是包含main方法的对象名称，这里不能使用文件名称TestCounterJVM
```

上面命令执行后，会在屏幕输出“1”。

现在我们对之前的类定义继续改进一下，让方法中带有参数。我们可以修改一下TestCounterJVM.scala文件：

```
class Counter {
    private var value = 0
    def increment(step: Int): Unit = { value += step}
    def current(): Int = {value}
}
object MyCounter{
    def main(args:Array[String]){
        val myCounter = new Counter
        myCounter.increment(5) //这里设置步长为5，每次增加5
        println(myCounter.current)
    }
}
```

采用上面介绍的方法，编译执行这个文件，就可以得到执行结果是5。

## getter和setter方法

下面我们来看一下如何给类中的字段设置值以及读取值。我们知道，在Java中，这是通过getter和setter方法实现的。在Scala中，也提供了getter和setter方法的实现，但是并没有定义成getXxx和setXxx。

我们继续修改TestCounterJVM.scala文件：

```
class Counter {
    var value = 0 //注意这里没有private修饰符，从而让这个变量对外部可见
    def increment(step: Int): Unit = { value += step}
    def current(): Int = {value}
}
object MyCounter{
    def main(args:Array[String]){
        val myCounter = new Counter
        println(myCounter.value)  //不是用getXxx获取字段的值
        myCounter.value = 3 //不是用setXxx设置字段的值
        myCounter.increment(1) //这里设置步长为1，每次增加1
        println(myCounter.current)
    }
}
```

编译执行这个文件，就可以得到两行执行结果，第一行是0，第二行是4。  
但是，我们都知道，在Java中，是不提倡设置这种公有(public)字段的，一般都是把value字段设置为private，然后提供getter和setter方法来获取和设置字段的值。那么，到了Scala中该怎么做呢？  
我们先把value字段声明为private，看看会出现什么效果，继续修改TestCounterJVM.scala文件：

```
class Counter {
    private var value = 0  //增加了private修饰符，成为私有字段
    def increment(step: Int): Unit = { value += step}
    def current(): Int = {value}
}
object MyCounter{
    def main(args:Array[String]){
        val myCounter = new Counter
        println(myCounter.value)  //不是用getXxx获取字段的值
        myCounter.value = 3 //不是用setXxx设置字段的值
        myCounter.increment(1) //这里设置步长为1，每次增加1
        println(myCounter.current)
    }
}
```

现在我们去用scalac命令编译上面的代码，就会报错，会出现“error:variable value in class Counter cannot be accessed in Counter”这样的错误信息。因为，value字段前面用了修饰符private，已经成为私有字段，外部是无法访问的。  
那么，value变成私有字段以后，Scala又没有提供getter和setter方法，怎么可以访问value字段呢？解决方案是，在Scala中，可以通过定义类似getter和setter的方法，分别叫做value和value\_=，具体如下：

```
class Counter {
    private var privateValue = 0  //变成私有字段，并且修改字段名称
    def value = privateValue //定义一个方法，方法的名称就是原来我们想要的字段的名称
    def value_=(newValue: Int){
        if (newValue &gt; 0) privateValue = newValue //只有提供的新值是正数，才允许修改
    }
    def increment(step: Int): Unit = { value += step}
    def current(): Int = {value}
}
object MyCounter{
    def main(args:Array[String]){
        val myCounter = new Counter
        println(myCounter.value)  //打印value的初始值
        myCounter.value = 3 //为value设置新的值
        println(myCounter.value)  //打印value的新值 
        myCounter.increment(1) //这里设置步长为1，每次增加1
        println(myCounter.current)
    }
}
```

编译执行这个文件，就可以得到三行执行结果，第一行是0，第二行是3，第三行是4。

## 辅助构造器

Scala构造器包含1个主构造器和若干个（0个或多个）辅助构造器。  
我们首先认识一下辅助构造器。辅助构造器的名称为this，每个辅助构造器都必须调用一个此前已经定义的辅助构造器或主构造器。  
下面定义一个带有辅助构造器的类，我们对上面的Counter类定义进行修改：

```
class Counter {
    private var value = 0 //value用来存储计数器的起始值
    private var name = "" //表示计数器的名称
    private var mode = 1 //mode用来表示计数器类型（比如，1表示步数计数器，2表示时间计数器）
    def this(name: String){ //第一个辅助构造器
        this() //调用主构造器
        this.name = name
    }
    def this (name: String, mode: Int){ //第二个辅助构造器
        this(name) //调用前一个辅助构造器
        this.mode = mode
    }
    def increment(step: Int): Unit = { value += step}
    def current(): Int = {value}
    def info(): Unit = {printf("Name:%s and mode is %d\n",name,mode)}
}
object MyCounter{
    def main(args:Array[String]){
        val myCounter1 = new Counter  //主构造器
        val myCounter2 = new Counter("Runner") //第一个辅助构造器，计数器的名称设置为Runner，用来计算跑步步数
        val myCounter3 = new Counter("Timer",2) //第二个辅助构造器，计数器的名称设置为Timer，用来计算秒数
        myCounter1.info  //显示计数器信息
        myCounter1.increment(1)     //设置步长  
        printf("Current Value is: %d\n",myCounter1.current) //显示计数器当前值
        myCounter2.info  //显示计数器信息
        myCounter2.increment(2)     //设置步长  
        printf("Current Value is: %d\n",myCounter2.current) //显示计数器当前值
        myCounter3.info  //显示计数器信息
        myCounter3.increment(3)     //设置步长  
        printf("Current Value is: %d\n",myCounter3.current) //显示计数器当前值

    }
}
```

编译执行上述代码后，得到如下结果：

```
Name: and mode is 1
Current Value is: 1
Name:Runner and mode is 1
Current Value is: 2
Name:Timer and mode is 2
Current Value is: 3
```

## 主构造器

Scala的每个类都有主构造器。但是，Scala的主构造器和Java有着明显的不同，Scala的主构造器是整个类体，需要在类名称后面罗列出构造器所需的所有参数，这些参数被编译成字段，字段的值就是创建对象时传入的参数的值。  
对于上面给计数器设置name和mode的例子，刚才我们是使用辅助构造器来对name和mode的值进行设置，现在我们重新来一次，这次我们转而采用主构造器来设置name和mode的值。

```
class Counter(val name: String, val mode: Int) {
    private var value = 0 //value用来存储计数器的起始值    
    def increment(step: Int): Unit = { value += step}
    def current(): Int = {value}
    def info(): Unit = {printf("Name:%s and mode is %d\n",name,mode)}
}
object MyCounter{
    def main(args:Array[String]){       
        val myCounter = new Counter("Timer",2)
        myCounter.info  //显示计数器信息
        myCounter.increment(1)  //设置步长  
        printf("Current Value is: %d\n",myCounter.current) //显示计数器当前值       
    }
}
```

编译执行上述代码后，得到如下结果：

```
Name:Timer and mode is 2
Current Value is: 1
```

Scala一个文件可以定义多个class或object；

Scala中不提供访问控制修饰符public，什么也不加默认为public；

类中属性赋默认初始值可以用下划线；

@BeanProperty注解会自动创建符合 JavaBean 规范的getter、setter，

```scala
import scala.beans.BeanProperty
​
object Test03_Class {
  def main(args: Array[String]): Unit = {
    val student = new Student()
    println(student.getName())
    println(student.getAge)
    student.setName("aaron")
    println(s"姓名：${student.getName()}  年龄：${student.getAge}")
  }
}
​
class Student {
​
  private var name: String = _
  @BeanProperty
  var age: Int = 22
  var sex: String = "Male"
  def getName(): String = {
    name
  }
  def setName(name: String): Unit = {
    this.name = name
  }
}
​
​
--------  Output  --------
null
22
姓名：aaron  年龄：22
```

  

**封装**

封装的概念应该都不陌生，将数据封装起来通过指定方法去访问。Scala提供进一步的封装，“public”属性底层实际上都是private，访问时**本质上是调用了xxx(get) 和 xxx\_eq$(set) 方法**，但这里的方法名并不是getXXX和setXXX，由于一些Java框架会利用反射调用getXXX和setXXX，因此Scala也提供了上面提到过的@BeanProperty注解去生成某属性的这两个方法，但注意@BeanProperty不能加在private修饰的属性上，可以理解为由于“public”本身就是private，将变量修饰为private然后再提供getter、setter方法比较冗余，Scala不推荐这样做。

  

**访问权限**

-   Scala 中属性和方法的默认访问权限为 public，但 Scala 中无 public 关键字。
-   private 为私有权限，只在类的内部和伴生对象中可用。
-   protected 为受保护权限，Scala 中受保护权限比 Java 中更严格，同类、子类可以访问，同包无法访问。
-   private\[包名\]增加包访问权限，包名下的其他类也可以使用

```scala
class Person {
  private var id: String = "252257465"
  protected var name: String = "aaron"
  var sex: String = "female"
  private[chapter06] var age: Int = 22
​
  def printInfo: Unit = {
    println(s"Person: $id $name $sex $age")
  }
}
​
class Worker extends Person {
  override def printInfo: Unit = {
    name = "season"
    sex = "male"
    age = 20
    //    id = "21223412"  // error
    println(s"Worker: $name $sex $age")
​
  }
}
​
object Test_Access {
  def main(args: Array[String]): Unit = {
    val person = new Person
    println(person.age)
    println(person.sex)
    //    print(person.name)  // error
    //    print(person.id)  // error
    person.printInfo
    val worker = new Worker
    worker.printInfo
​
  }
}
​
​
--------  Output  --------
22
female
Person: 252257465 aaron female 22
Worker: season male 20
```

  

**构造器**

Scala中定义类可以理解为也是一个函数，此函数是这个类的**主构造器**（主构造方法），可以在类名后加括号和传入主构造器的参数，同其它函数一样，若没有参数括号可以省略。在类中定义名为this的带参函数即为**辅助构造器**（辅助构造方法），可以重载为多个，需要注意，辅助构造器不能直接构建对象，必须直接或者间接调用主构造器，且必须在首行。Java中与类名相同的方法在Scala的类中只是一个普通函数。

```scala
object Test05_Constructor {
  def main(args: Array[String]): Unit = {
    val student1 = new Student1()
    val student2 = new Student1("aaron")
    val student3 = new Student1("aaron",22)
  }
}
​
class Student1() {
  var name: String = _
  var age: Int = _
  println("主构造器被调用")
​
  def this(name: String) {
    this()
    println("辅助构造器1被调用")
    this.name = name
    println(s"name: $name, age: $age")
  }
​
  def this(name: String, age: Int) {
    this(name)
    println("辅助构造器2被调用")
    this.age = age
    println(s"name: $name, age: $age")
  }
}
​
​
--------  Output  --------
主构造器被调用
主构造器被调用
辅助构造器1被调用
name: aaron, age: 0
主构造器被调用
辅助构造器1被调用
name: aaron, age: 0
辅助构造器2被调用
name: aaron, age: 22
```

Scala 类的主构造器函数的形参包括三种类型

-   未用任何修饰符修饰，这个参数就是一个局部变量
-   var 修饰参数，作为类的成员属性使用，可以修改
-   val 修饰参数，作为类只读属性使用，不能修改

```
object Test_ConstructorParam {
  def main(args: Array[String]): Unit = {
    val student2 = new student2("aaron", 22)
    println(s"name: ${student2.name}, age: ${student2.age}")  // 成员变量可直接访问
​
    val student3 = new student3("ramsey", 28)
    student3.printInfo()
    // println(s"name: ${student3.name}, age: ${student3.age}")  // error 局部变量无法直接访问
  }
}
​
class student2 (var name: String, var age: Int)
​
class student3 (name: String, age: Int) {
  def printInfo(): Unit = {
    println(s"name: ${name}, age: ${age}")
  }
}
​
​
--------  Output  --------
name: aaron, age: 22
name: ramsey, age: 28
```

  

**继承**

先给出代码

```
object Test07_Inherit {
  def main(args: Array[String]): Unit = {
    val student = new Student("aaron", 22, "171717")
    student.printInfo()
  }
}
​
class Person {
  var name: String = _
  var age: Int = _
  println("1 父类主构造器被调用")
​
  def this(name: String, age: Int) {
    this()
    println("2 父类辅助构造器被调用")
    this.age = age
    this.name = name
  }
​
  def printInfo(): Unit = {
    println(s"name: $name, age: $age")
  }
}
class Student(_name: String, _age: Int) extends Person { //(name, age) {
  var stuNo: String = _
  println("3 子类主构造器被调用")
​
  def this(name: String, age: Int, stuNo: String) {
    this(name, age)
    println("4 子类辅助构造器被调用")
    this.stuNo = stuNo
  }
​
  override def printInfo(): Unit = {
    println(s"Student[name: ${name}, age: ${age}, stuNo: ${stuNo}]")
  }
}
​
​
--------  Output  --------
1 父类主构造器被调用
3 子类主构造器被调用
4 子类辅助构造器被调用
Student[name: null, age: 0, stuNo: 171717]
```

Student类继承Person类，Scala中继承要声明是继承的父类哪一个构造器，上面的extend Person实则是省略了Person后的括号，继承Person的主构造器，Student类主构造器传的参数是局部变量，因为会继承父类的属性name和age，没必要再声明为子类的属性；继承的调用顺序是父类构造器先于子类构造器，上面的程序会先调用继承的父类构造器Person()，再通过this(name, age)调用子类的主构造器，然后最后是子类辅助构造器；由于是调用的父类主构造器，并没有对继承的属性name和age赋值，只在子类构造器中修改了成员属性stuNo，因此得到了上面的输出结果。

如果将 extend Person 改为 extend Person ( \_name, \_age)，输出结果如下：

```
--------  Output  --------
1 父类主构造器被调用
2 父类辅助构造器被调用
3 子类主构造器被调用
4 子类辅助构造器被调用
Student[name: aaron, age: 22, stuNo: 171717]
```

继承的是父类的辅助构造器，因此会调用继承的父类构造器对name和age进行了赋值后再调用子类构造器，输出查看也都是三个属性赋值后的结果。

  

**多态**

Scala 中属性和方法都是动态绑定，而 Java 中只有方法为动态绑定。

先看一下Java中的多态：

```
class Person {
    int age = 20;
    public void printInfo() {
        System.out.println("Person");
    }
}
class Student extends Person {
    int age = 22;
    public void printInfo() {
        System.out.println("Student");
    }
}
​
class Test {
    public static void main(String args[]) {
        Person p = new Student();
        System.out.println(p.age);
        p.printInfo();
    }
}
​
​
--------  Output  --------
20
Student
```

p是Person类型的引用，当前语句是指向了new出来的一个Student对象，但它在后面仍然可以指向一个Person对象，编译时就会将p和Person绑定在一起，p的age属性是静态绑定的所以值为20。printInfo则为动态绑定，程序最终执行的是实际类型Student的printInfo。

Java的属性静态绑定是为了在编译期就能发现一些错误，Scala的理念中基本去除了所有静态的概念，前面文章中也提及到了Scala对变量进一步的封装，访问变量都要调用get/set方法，调用方法需要动态绑定，所以呈现出来的就是属性和方法都为动态绑定。

```
object Test_DynamicBind {
  def main(args: Array[String]): Unit = {
    val student: Person8 = new Student8
    println(student.name)
    student.hello
  }
}
​
class Person8 {
  val name: String = "person"
  def hello = {
    println("hello person")
  }
}
​
class Student8 extends Person8 {
  override val name: String = "student"
  override def hello: Unit = {
    println("hello student")
  }
}
​
​
--------  Output  --------
student
hello student
```

  

**抽象类**

-   抽象类：abstract class Person {...} 抽象类内也可以没有抽象属性抽象方法
-   抽象属性：val|var name:String 没有初始化的属性即为抽象属性
-   抽象方法：def printInfo():String 只有声明没有实现即为抽象方法

子类重写非抽象属性或方法需要加override，实现抽象属性或方法不需要加 override。

子类重写非抽象属性只支持val类型，var修饰可变变量直接赋值即可。

子类不实现所有抽象属性和方法仍需声明为抽象类。

抽象类还可以声明匿名子类，实现方法如下：

```
object Test_AnonymousClass {
  def main(args: Array[String]): Unit = {
    val person: Person = new Person {
      override var name: String = "aaron"
      override def hello(): Unit = println(s"hello $name")
    }
​
    println(person.name)
    person.hello()
  }
}
​
abstract class Person {
  var name: String
  def hello(): Unit
}
​
​
--------  Output  --------
aaron
hello aaron
```

  

**单例对象（伴生对象）**

伴生对象和伴生类相伴相生，Scala去除了static关键字，在伴生对象内保存一些“静态”的属性和方法，这些静态的内容可以在任何地方前面加上类名调用，伴生对象内也可以访问伴生类的全部成员（包括private）；这些静态内容拥有了所属的对象（伴生对象），更符合面向对象的思想。

伴生对象和伴生类名称必须相同，且放在同一个文件中

前面在测试时广泛使用了伴生对象，因为main函数也是只需要一份的，放到伴生对象中可以直接检测到并执行。

伴生对象提供了一个特殊的apply方法，apply方法中new了一个伴生类的实例化对象，调用apply方法时可以省略apply，写法比较简洁；apply方法可以重载。

```
object Test_Object {
  def main(args: Array[String]): Unit = {
​
//    val stu = new Student("aaron", 22)  // Student主构造器不用private修饰可以使用
//    stu.printerInfo()
​
    val stu1 = Student.newStudent("aaron", 22)
    stu1.printerInfo()
​
    val stu2 = Student("aaron", 22)
    stu2.printerInfo()
​
    val stu3 = Student("aaron")
    stu3.printerInfo()
  }
}
​
class Student private(val name: String, val age: Int) {
  def printerInfo(): Unit = {
    println(s"name: $name, age: $age, school: ${Student.school}")
  }
}
​
object Student {
  val school: String = "uuu"
  def newStudent(name: String, age: Int): Student = {
    new Student(name, age)
  }
​
  def apply(name: String, age: Int): Student = new Student(name, age)
​
  def apply(name: String): Student = new Student(name, 0)
}
```

伴生对象实现单例设计模式

```
object Test_Single {
  def main(args: Array[String]): Unit = {
    val stu1 = Student.getInstance()
    val stu2 = Student.getInstance()
​
    stu1.printerInfo()
    stu2.printerInfo()
​
    println(stu1)  // 输出引用
    println(stu2)
  }
}
​
class Student private(val name: String, val age: Int) {
  def printerInfo(): Unit = {
    println(s"name: $name, age: $age, school: ${Student.school}")
  }
}
​
​
object Student {
  val school: String = "uuu"
  private var student: Student = null
  def getInstance(): Student = {
    if (student == null) {
      student = new Student("aaron", 22)
    }
    student
  }
}
​
​
--------  Output  --------
name: aaron, age: 22, school: uuu
name: aaron, age: 22, school: uuu
chapter06.Student12@129a8472
chapter06.Student12@129a8472
```

  

## **特质 (Trait)**

Scala中用Trait代替了Java中的interface，可以有抽象和非抽象方法；抽象类只能单继承，特质可以多“实现”，称作特质的混入（mixin），整体类别有从属关系时用抽象类，附加某种方法时用特质，更加灵活。如果需要有参构造时只能用抽象类。

使用特质的例子：

```
object Test_Trait {
  def main(args: Array[String]): Unit = {
    val student1 = new Student
    student1.sayHello()  // 重写抽象类中的非抽象方法
    student1.dating()  //实现Young特质中的抽象方法
    student1.play()  // 引入Young特质的方法
    student1.study() // Student类自己的方法
  }
}
​
class Person {
  val name: String = "person"
  val age: Int = 22
  def sayHello(): Unit = {
    println("hello from: " + name)
  }
}
​
trait Young {
  val age: Int
  val name: String = "young"
​
  def play() = {
    println("young people is playing")
  }
  def dating(): Unit
}
​
class Student extends Person with Young {
  override val name: String = "stu"
  def dating(): Unit = println(s"student $name is dating")
  def study() = println(s"student $name is studying")
​
  override def sayHello(): Unit = {
    super.sayHello()
    println(s"hello from student $name")
  }
}
​
​
--------  Output  --------
hello from: stu
hello from student stu
student stu is dating
young people is playing
student stu is studying
```

上面的代码中声明Student时继承了父类Person并引入了特征Young，若只引入特征则写为 **class Student extends Young**；另外需要注意，name属性由于特质和抽象类中都定义为是一个非抽象属性，因此在Student类中需要重写，否则不知道应该用哪一个；age属性特质中定义为抽象属性，抽象类中定义为非抽象属性，因此可以直接使用。

特质混入的声明在一个特质后叠加 with 特质名即可，例：class Student extends Person with Young with... 混入多个特质同样要保证冲突的属性或方法需要重写，抽象的属性或方法要实现。

特质的 **动态混入** 是在实例化时引入新特质，同样需要对冲突和抽象的内容进行处理。例子如下：

```
object Test_Trait {
  def main(args: Array[String]): Unit = {
    val student1 = new Student with Talent {
      def singing(): Unit = println(s"student $name is singing")
      def dancing(): Unit = println(s"student $name is dancing")
    }
    student1.dating()
    student1.increase()
    student1.play()
    student1.increase()
    student1.study()
    student1.increase()
    student1.singing()
    student1.increase()
    student1.dancing()
    student1.increase()
  }
}
​
class Person {
  val name: String = "person"
  val age: Int = 22
  def sayHello(): Unit = {
    println("hello from: " + name)
  }
}
​
trait Young {
  val age: Int
  val name: String = "young"
  def play(): Unit = {
    println("young people is playing")
  }
  def dating(): Unit
}
​
trait Knowledge {
  var amount: Int = 0
  def increase(): Unit
}
​
trait Talent {
  def singing()
  def dancing()
}
​
class Student extends Person with Young with Knowledge {
​
  override val name: String = "stu"
  def dating(): Unit = println(s"student $name is dating")
  def study(): Unit = println(s"student $name is studying")
  
  override def sayHello(): Unit = {
    super.sayHello()
    println(s"hello from student $name")
  }
  
  def increase(): Unit = {
    amount += 1
    println(s"student ${name} knowledge increased: ${amount}")
  }
}
​
​
--------  Output  --------
student stu is dating
student stu knowledge increased: 1
young people is playing
student stu knowledge increased: 2
student stu is studying
student stu knowledge increased: 3
student stu is singing
student stu knowledge increased: 4
student stu is dancing
student stu knowledge increased: 5
```

特征叠加时，若有方法的冲突，我们在重写的该方法中使用super默认调用的是叠加的最后一个（最右边）特征的方法，下面钻石问题中有解释；上面例子中假设Person、Young、Knowledge都有printInfo方法，Student类override的printInfo方法中执行super.printInfo会执行Knowledge的printInfo方法。

钻石问题的特质叠加见下图：（下面例子中每个特质都有describe方法）

![](https://pic3.zhimg.com/v2-4220ae236772b15f34752e94d44cceb6_b.jpg)

![](https://pic3.zhimg.com/80/v2-4220ae236772b15f34752e94d44cceb6_1440w.webp)

-   案例中的 super，不是表示其父特质对象，而是表示上述叠加顺序中的下一个特质， 即，**MyClass** **中的** **super** **指代** **Color**，**Color** **中的** **super** **指代** **Category**，**Category** **中的** **super** **指代** **Ball**。
-   如果想要调用某个指定的混入特质中的方法，可以增加约束:super\[\]，例如super\[Category\].describe()。

  

## **扩展**

**自身类型**

Scala的自身类型也是一个比较独特的引入，它声明当前特质或类本身真正属于什么类型，实现依赖注入的功能；例子可以参考这篇 [博客](https://link.zhihu.com/?target=https%3A//blog.csdn.net/kypfos/article/details/79324088) 。

  

**类型检查和转换**

-   obj.isInstanceOf\[T\]:判断 obj 是不是 T 类型。
-   obj.asInstanceOf\[T\]:将 obj 强转成 T 类型。
-   classOf 获取对象的类名。

重温一个Java的知识点：

父类引用可以指向子类对象，该引用可以使用子类继承和重写的方法；

子类引用不可以指向父类对象，否则引用中的一些子类独有方法在指向对象中找不到具体实现。

  

**枚举类和应用类**

枚举类概念一定不陌生了，应用类就是继承App，不用写main方法可以直接执行，直接看代码：

```
object WorkDay extends Enumeration {
  val Key = Value(1, "dasd12eas213j1d")
  val Token = Value(2, "12345678909876543")
}
​
object TestApp extends App {
  println("APP START")
  println(WorkDay.Key)
  println(WorkDay.Token)
}
​
​
--------  Output  --------
APP START
dasd12eas213j1d
12345678909876543
```

  

本人也是Scala初学者，若有哪里理解的没那么到位，欢迎批评指正！
[参考来源](https://zhuanlan.zhihu.com/p/399419869)