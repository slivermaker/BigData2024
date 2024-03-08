在Linux操作系统中安装Scala的过程如下。  
首先要指定Scala的安装目录，比如，这里我们选择安装在“/usr/local/”目录下，这里假设当前用户登录名是hadoop。  
然后，把刚才下载的scala-2.11.8.tgz文件解压缩到“/usr/local/”目录下，修改文件夹名称，并为hadoop用户赋予权限，具体如下:

```sudo tar -zxf ~/下载/scala-2.11.8.tgz -C /usr/local   # 解压到/usr/local中
cd /usr/local/
sudo mv ./scala-2.11.8/ ./scala         # 将文件夹名改为scala
sudo chown -R hadoop ./scala        # 修改文件权限，用hadoop用户拥有对scala目录的权限
```

Shell 命令[](javascript:void(0); "复制代码")[](javascript:void(0); "查看纯文本代码")[](javascript:void(0); "返回代码高亮")

接着需要把scala命令添加到path环境变量中。这里我们在 ~/.bashrc 中进行设置。可以采用vim编辑器打开.bashrc文件：

```
vim ~/.bashrc
```



打开vim编辑器以后，需要键盘敲击输入一个字母i，进入编辑状态，然后才能修改内容。  
然后，在.bashrc文件的最开头位置，修改path环境变量设置，把scala命令所在的目录“/usr/local/scala/bin”增加到path中，具体如下：

```
export PATH=$PATH:/usr/local/scala/bin
```

注意，上面的PATH和等号之间，不要加入任何空格，否则会出错。  
修改后，保存退出（方法是：首先，按键盘Esc键，退出vim的编辑状态，然后，敲击键盘输入“:wq”三个英文字母，然后回车，即可保存退出）。

接着还需要让该环境变量生效，执行如下代码：

```
source ~/.bashrc    # 使变量设置生效

```

设置好后我们来检验一下是否设置正确，可以输入scala命令：

```
scala
```



输入scala命令以后，屏幕上显示scala和Java版本信息，并进入“scala>”提示符状态，就可以开始使用Scala解释器了，你就可以输入scala语句来调试程序代码了。

# 三、使用Scala解释器

按照上述步骤完成安装以后，如果是Windows系统，可以打开Windows系统的命令提示符界面（也就是运行命令行程序cmd.exe），然后，在界面中输入“scala”命令，就可以运行Scala解释器。如果是Linux系统，可以在命令提示符终端中，输入“scala”命令，就可以运行Scala解释器。  
注意：由于前面的安装过程中，安装程序已经自动设置了path变量，所以，这不需要给出scala命令的路径全称，实际上，scala命令位于scala安装目录的bin目录下。  
运行Scala解释器以后，就可以测试了，你可以输入一条语句，解释器会立即执行语句并返回结果，这就是我们所说的REPL（Read-Eval-Print Loop，交互式解释器），为我们提供了交互式执行环境，表达式计算完成就会输出结果，而不必等到整个程序运行完毕，因此可即时查看中间结果，并对程序进行修改，这样可以在很大程度上提升开发效率。  
在命令提示符界面中输入“scala”命令后，会进入scala命令行提示符状态（即“scala>”），可以在后面输入命令：

```
scala>   //可以在命令提示符后面输入命令
```

比如，下面在命令提示符后面输入一个表达式“8 \* 2 + 5”，然后回车，就会立即得到结果：

```
scala> 8*2+5
res0: Int = 21
```

最后，可以使用命令“:quit”退出Scala解释器，如下所示：

```
scala>:quit
```



或者，也可以直接使用“Ctrl+D”组合键，退出Scala解释器。  
到此，我们就顺利完成了Scala环境的安装，为我们后面学习Scala编程奠定了基础。

# 四、第一个Scala程序：Hello World

Scala融合了面向对象编程思想，所以，这里我们采用一个包含了main()方法的大家比较熟悉的JVM应用程序，这里以Hello World程序为例进行说明。  
请登录Linux系统，打开命令行终端（可以使用Ctr+Alt+T组合键来打开终端）。现在请在Scala安装目录/usr/local/scala下面新建一个mycode文件夹，用于存放自己的练习代码文件(后面我们都会把练习代码文件放在/usr/local/scala/mycode这个目录下)，创建目录的命令如下:

```
cd /usr/local/scala
mkdir mycode
```


使用下面命令到达mycode目录，并新建一个test.scala文件：

```cd /usr/local/scala/mycode
vim test.scala
```



在test.scala文件中输入以下代码：

```
object HelloWorld {
    def main(args: Array[String]){
        println("Hello, World!")
    }
}
```

关于上面代码，需要重点说明两点：  
（1）在上面代码中，定义了程序的入口main()方法。可以看出，关于main()方法的定义，Java和Scala是不同的，在Java中是用静态方法（public static void main(String\[\] args)），而Scala中则必须使用对象方法，本例中，也就是HelloWorld对象中的main()方法。  
（2）对象的命名HelloWorld可以不用和文件名称一致，这里对象名称是HelloWorld，而文件名称却是test.scala。这点和Java是不同的，按照Java的命名要求，这里的文件名称就必须起名为HelloWorld.scala，但是，在Scala中是没有这个一致性要求的。  
（3）Scala是大小写敏感的，所以，不要输入错误，比如把小写开头的object输成大写开头的Object。文件名Test.scala和test.scala也是两个不同的文件。

下面我们用scalac命令编译test.scala代码文件，并用scala命令执行，如下：

```
scalac test.scala //编译的时候使用的是Scala文件名称
scala -classpath . HelloWorld  //执行的时候使用的是HelloWorld对象名称
```

注意，上面命令中一定要加入"-classpath ."，否则会出现“No such file or class on classpath: HelloWorld”。  
上述命令执行后，会在屏幕上打印出“Hello, World!”。