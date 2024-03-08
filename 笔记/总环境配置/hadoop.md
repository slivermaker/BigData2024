**Ubuntu终端复制粘贴快捷键**: 在Ubuntu终端窗口中，复制粘贴的快捷键需要加上 shift，即粘贴是 ctrl+shift+v。

可为  用户增加管理员权限，方便部署，避免一些对新手来说比较棘手的权限问题：

```bash
sudo adduser hadoop sudo
```


## 更新apt

用 hadoop 用户登录后，我们先更新一下 apt，后续我们使用 apt 安装软件，如果没更新可能有一些软件安装不了。按 ctrl+alt+t 打开终端窗口，执行如下命令：

```bash
sudo apt-get update
```

若出现如下 "Hash校验和不符" 的提示，可通过更改软件源来解决。若没有该问题，则不需要更改。从软件源下载某些软件的过程中，可能由于网络方面的原因出现没法下载的情况，那么建议更改软件源。在学习Hadoop过程中，即使出现“Hash校验和不符”的提示，也不会影响Hadoop的安装。

![Ubuntu更新软件源时遇到Hash校验和不符的问题](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2014/08/install-hadoop-01-apt-hash.png)

首先点击左侧任务栏的【系统设置】（齿轮图标），选择【软件和更新】

![Ubuntu更新软件源](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2014/08/install-hadoop-02-apt-source.png)

点击 “下载自” 右侧的方框，选择【其他节点】

![Ubuntu更新软件源-选择服务器](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2014/08/install-hadoop-03-apt-change.png)

在列表中选中【mirrors.aliyun.com】，并点击右下角的【选择服务器】，会要求输入用户密码，输入即可。

![Ubuntu更新软件源-选择服务器](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2014/08/install-hadoop-04-apt-select.png)

接着点击关闭。

![Ubuntu更新软件源-关闭窗口](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2014/08/install-hadoop-05-apt-close.png)

此时会提示列表信息过时，点击【重新载入】，

![Ubuntu更新软件源-重新载入](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2014/08/install-hadoop-06-apt-reload.png)

最后耐心等待更新缓存即可。更新完成会自动关闭【软件和更新】这个窗口。如果还是提示错误，请选择其他服务器节点如 mirrors.163.com 再次进行尝试。更新成功后，再次执行 `sudo apt-get update` 就正常了。

后续需要更改一些配置文件，我比较喜欢用的是 vim（vi增强版，基本用法相同），建议安装一下（如果你实在还不会用 vi/vim 的，请将后面用到 vim 的地方改为 gedit，这样可以使用文本编辑器进行修改，并且每次文件更改完成后请关闭整个 gedit 程序，否则会占用终端）：

```bash
sudo apt-get install vim
```

安装软件时若需要确认，在提示处输入 y 即可。

![通过命令行安装软件](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2014/08/install-hadoop-07-apt-install.png)

vim的常用模式有分为命令模式，插入模式，可视模式，正常模式。本教程中，只需要用到正常模式和插入模式。二者间的切换即可以帮助你完成本指南的学习。

1.  正常模式  
    正常模式主要用来浏览文本内容。一开始打开vim都是正常模式。在任何模式下按下Esc键就可以返回正常模式
2.  插入编辑模式  
    插入编辑模式则用来向文本中添加内容的。在正常模式下，输入i键即可进入插入编辑模式
3.  退出vim  
    如果有利用vim修改任何的文本，一定要记得保存。Esc键退回到正常模式中，然后输入:wq即可保存文本并退出vim

## 安装SSH、配置SSH无密码登陆

集群、单节点模式都需要用到 SSH 登陆（类似于远程登陆，你可以登录某台 Linux 主机，并且在上面运行命令），Ubuntu 默认已安装了 SSH client，此外还需要安装 SSH server：

```bash
sudo apt-get install openssh-server
```

安装后，可以使用如下命令登陆本机：

```bash
ssh localhost
```

此时会有如下提示(SSH首次登陆提示)，输入 yes 。然后按提示输入密码 hadoop，这样就登陆到本机了。

![SSH首次登陆提示](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2014/08/install-hadoop-08-ssh-continue.png)

但这样登陆是需要每次输入密码的，我们需要配置成SSH无密码登陆比较方便。

首先退出刚才的 ssh，就回到了我们原先的终端窗口，然后利用 ssh-keygen 生成密钥，并将密钥加入到授权中：

```bash
exit                           # 退出刚才的 ssh localhost
cd ~/.ssh/                     # 若没有该目录，请先执行一次ssh localhost
ssh-keygen -t rsa              # 会有提示，都按回车就可以
cat ./id_rsa.pub >> ./authorized_keys  # 加入授权
```

**~的含义**: 在 Linux 系统中，~ 代表的是用户的主文件夹，即 "/home/用户名" 这个目录，如你的用户名为 hadoop，则 ~ 就代表 "/home/hadoop/"。 此外，命令中的 # 后面的文字是注释，只需要输入前面命令即可。

此时再用 `ssh localhost` 命令，无需输入密码就可以直接登陆了。

## 安装Java环境

手动安装，推荐采用本方式

Hadoop3.3.5需要JDK版本在1.8及以上。需要按照下面步骤来自己手动安装JDK1.8。  
我们已经把JDK1.8的安装包jdk-8u371-linux-x64.tar.gz放在了百度云盘，[可以点击这里到百度云盘下载JDK1.8安装包](https://pan.baidu.com/s/1gw94YHupw9_ZIFszUe6-uQ?pwd=ziyu)（提取码：ziyu）。请把压缩格式的文件jdk-8u371-linux-x64.tar.gz下载到本地电脑，假设保存在“/home/linziyu/Downloads/”目录下。

在Linux命令行界面中，执行如下Shell命令（注意：当前登录用户名是hadoop）：

```bash
cd /usr/lib
sudo mkdir jvm #创建/usr/lib/jvm目录用来存放JDK文件
cd ~ #进入hadoop用户的主目录
cd Downloads  #注意区分大小写字母，刚才已经通过FTP软件把JDK安装包jdk-8u371-linux-x64.tar.gz上传到该目录下
sudo tar -zxvf ./jdk-8u371-linux-x64.tar.gz -C /usr/lib/jvm  #把JDK文件解压到/usr/lib/jvm目录下
```

上面使用了解压缩命令tar，如果对Linux命令不熟悉，可以参考[常用的Linux命令用法](https://dblab.xmu.edu.cn/blog/1624-2/)。

JDK文件解压缩以后，可以执行如下命令到/usr/lib/jvm目录查看一下：

```bash
cd /usr/lib/jvm
ls
```

可以看到，在/usr/lib/jvm目录下有个jdk1.8.0\_371目录。  
下面继续执行如下命令，设置环境变量：

```bash
cd ~
vim ~/.bashrc
```

上面命令使用vim编辑器（[查看vim编辑器使用方法](https://dblab.xmu.edu.cn/blog/1607-2/)）打开了hadoop这个用户的环境变量配置文件，请在这个文件的开头位置，添加如下几行内容：

```
export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_371
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$PATH
```

保存.bashrc文件并退出vim编辑器。然后，继续执行如下命令让.bashrc文件的配置立即生效：

```bash
source ~/.bashrc
```

这时，可以使用如下命令查看是否安装成功：

```bash
java -version
```

如果能够在屏幕上返回如下信息，则说明安装成功：

```
hadoop@ubuntu:~$ java -version
java version "1.8.0_371"
Java(TM) SE Runtime Environment (build 1.8.0_371-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.162-b12, mixed mode)
```

至此，就成功安装了Java环境。下面就可以进入Hadoop的安装。

Hadoop安装文件，可以到Hadoop官下载hadoop-3.3.5.tar.gz。  
也可以直接[点击这里从百度云盘下载软件](https://pan.baidu.com/s/1gw94YHupw9_ZIFszUe6-uQ?pwd=ziyu)（提取码：ziyu），进入百度网盘后，进入“软件”目录，找到hadoop-3.3.5.tar.gz文件，下载到本地。  
我们选择将 Hadoop 安装至 /usr/local/ 中：

```bash
sudo tar -zxvf ~/下载/hadoop-3.3.5.tar.gz -C /usr/local   # 解压到/usr/local中
cd /usr/local/
sudo mv ./hadoop-3.3.5/ ./hadoop            # 将文件夹名改为hadoop
sudo chown -R hadoop ./hadoop       # 修改文件权限
```

Hadoop 解压后即可使用。输入如下命令来检查 Hadoop 是否可用，成功则会显示 Hadoop 版本信息：

```bash
cd /usr/local/hadoop
./bin/hadoop version
```

**相对路径与绝对路径**: 请务必注意命令中的相对路径与绝对路径，本文后续出现的 `./bin/...`，`./etc/...` 等包含 ./ 的路径，均为相对路径，以 /usr/local/hadoop 为当前目录。例如在 /usr/local/hadoop 目录中执行 `./bin/hadoop version` 等同于执行 `/usr/local/hadoop/bin/hadoop version`。可以将相对路径改成绝对路径来执行，但如果你是在主文件夹 ~ 中执行 `./bin/hadoop version`，执行的会是 `/home/hadoop/bin/hadoop version`，就不是我们所想要的了。

## Hadoop单机配置(非分布式)

Hadoop 默认模式为非分布式模式（本地模式），无需进行其他配置即可运行。非分布式即单 Java 进程，方便进行调试。

现在我们可以执行例子来感受下 Hadoop 的运行。Hadoop 附带了丰富的例子（运行 `./bin/hadoop jar ./share/hadoop/mapreduce/hadoop-mapreduce-examples-3.3.5.jar` 可以看到所有例子），包括 wordcount、terasort、join、grep 等。

在此我们选择运行 grep 例子，我们将 input 文件夹中的所有文件作为输入，筛选当中符合正则表达式 dfs\[a-z.\]+ 的单词并统计出现的次数，最后输出结果到 output 文件夹中。

```bash
cd /usr/local/hadoop
mkdir ./input
cp ./etc/hadoop/*.xml ./input   # 将配置文件作为输入文件
./bin/hadoop jar ./share/hadoop/mapreduce/hadoop-mapreduce-examples-3.3.5.jar grep ./input ./output 'dfs[a-z.]+'
cat ./output/*          # 查看运行结果
```

执行成功后如下所示，输出了作业的相关信息，输出的结果是符合正则的单词 dfsadmin 出现了1次

![Hadoop单机模式运行grep的输出结果](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2014/08/install-hadoop-13-grep-output.png)

**注意**，Hadoop 默认不会覆盖结果文件，因此再次运行上面实例会提示出错，需要先将 `./output` 删除。

```bash
rm -r ./output
```

## Hadoop伪分布式配置

Hadoop 可以在单节点上以伪分布式的方式运行，Hadoop 进程以分离的 Java 进程来运行，节点既作为 NameNode 也作为 DataNode，同时，读取的是 HDFS 中的文件。

Hadoop 的配置文件位于 /usr/local/hadoop/etc/hadoop/ 中，伪分布式需要修改2个配置文件 **core-site.xml** 和 **hdfs-site.xml** 。Hadoop的配置文件是 xml 格式，每个配置以声明 property 的 name 和 value 的方式来实现。

修改配置文件 **core-site.xml** (通过 gedit 编辑会比较方便: `gedit ./etc/hadoop/core-site.xml`)，将当中的

```xml
<configuration>
</configuration>
```

修改为下面配置：

```xml
<configuration>
    <property>
        <name>hadoop.tmp.dir</name>
        <value>file:/usr/local/hadoop/tmp</value>
        <description>Abase for other temporary directories.</description>
    </property>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:9000</value>
    </property>
</configuration>
```

同样的，修改配置文件 **hdfs-site.xml**：

```xml
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
    <property>
        <name>dfs.namenode.name.dir</name>
        <value>file:/usr/local/hadoop/tmp/dfs/name</value>
    </property>
    <property>
        <name>dfs.datanode.data.dir</name>
        <value>file:/usr/local/hadoop/tmp/dfs/data</value>
    </property>
</configuration>
```

_Hadoop配置文件说明_:

Hadoop 的运行方式是由配置文件决定的（运行 Hadoop 时会读取配置文件），因此如果需要从伪分布式模式切换回非分布式模式，需要删除 core-site.xml 中的配置项。

此外，伪分布式虽然只需要配置 fs.defaultFS 和 dfs.replication 就可以运行（官方教程如此），不过若没有配置 hadoop.tmp.dir 参数，则默认使用的临时目录为 /tmp/hadoo-hadoop，而这个目录在重启时有可能被系统清理掉，导致必须重新执行 format 才行。所以我们进行了设置，同时也指定 dfs.namenode.name.dir 和 dfs.datanode.data.dir，否则在接下来的步骤中可能会出错。

配置完成后，执行 NameNode 的格式化:

```bash
cd /usr/local/hadoop
./bin/hdfs namenode -format
```

成功的话，会看到 "successfully formatted" 的提示，具体返回信息类似如下：

```
2023-07-11 14:28:30,560 INFO namenode.NameNode: STARTUP_MSG: 
/************************************************************

STARTUP_MSG: Starting NameNode
STARTUP_MSG:   host = hadoop/127.0.1.1
STARTUP_MSG:   args = [-format]
STARTUP_MSG:  version = 3.3.5
*************************************************************/

......
2023-07-11 15:31:35,677 INFO common.Storage: Storage directory /usr/local/hadoop/tmp/dfs/name **has been successfully formatted**.
2023-07-11 15:31:35,700 INFO namenode.FSImageFormatProtobuf: Saving image file /usr/local/hadoop/tmp/dfs/name/current/fsimage.ckpt_0000000000000000000 using no compression
2023-07-11 15:31:35,770 INFO namenode.FSImageFormatProtobuf: Image file /usr/local/hadoop/tmp/dfs/name/current/fsimage.ckpt_0000000000000000000 of size 393 bytes saved in 0 seconds .
2023-07-11 15:31:35,810 INFO namenode.NNStorageRetentionManager: Going to retain 1 images with txid &gt;= 0
2023-07-11 15:31:35,816 INFO namenode.FSImage: FSImageSaver clean checkpoint: txid = 0 when meet shutdown.
2023-07-11 15:31:35,816 INFO namenode.NameNode: SHUTDOWN_MSG:  
/************************************************************
SHUTDOWN_MSG: Shutting down NameNode at hadoop/127.0.1.1
*************************************************************/
```

如果在这一步时提示 **Error: JAVA\_HOME is not set and could not be found.** 的错误，则说明之前设置 JAVA\_HOME 环境变量那边就没设置好，请按教程先设置好 JAVA\_HOME 变量，否则后面的过程都是进行不下去的。如果已经按照前面教程在.bashrc文件中设置了JAVA\_HOME，还是出现 **Error: JAVA\_HOME is not set and could not be found.** 的错误，那么，请到hadoop的安装目录修改配置文件“/usr/local/hadoop/etc/hadoop/hadoop-env.sh”，在里面找到“export JAVA\_HOME=${JAVA\_HOME}”这行，然后，把它修改成JAVA安装路径的具体地址，比如，“export JAVA\_HOME=/usr/lib/jvm/default-java”，然后，再次启动Hadoop。

接着开启 NameNode 和 DataNode 守护进程。

```bash
cd /usr/local/hadoop
./sbin/start-dfs.sh  #start-dfs.sh是个完整的可执行文件，中间没有空格
```

若出现如下SSH提示，输入yes即可。

![启动Hadoop时的SSH提示](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2014/08/install-hadoop-15-ssh-continue.png)

启动时可能会出现如下 WARN 提示：WARN util.NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable WARN 提示可以忽略，并不会影响正常使用。

_启动 Hadoop 时提示 Could not resolve hostname_:

如果启动 Hadoop 时遇到输出非常多“ssh: Could not resolve hostname xxx”的异常情况，如下图所示：

![启动Hadoop时的异常提示](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2014/08/install-hadoop-15-resolve-hostname.png)

这个并不是 ssh 的问题，可通过设置 Hadoop 环境变量来解决。首先按键盘的 **ctrl + c** 中断启动，然后在 ~/.bashrc 中，增加如下两行内容（设置过程与 JAVA\_HOME 变量一样，其中 HADOOP\_HOME 为 Hadoop 的安装目录）：

```shell
export HADOOP_HOME=/usr/local/hadoop
export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
```

保存后，务必执行 `source ~/.bashrc` 使变量设置生效，然后再次执行 `./sbin/start-dfs.sh` 启动 Hadoop。

启动完成后，可以通过命令 `jps` 来判断是否成功启动，若成功启动则会列出如下进程: "NameNode"、"DataNode" 和 "SecondaryNameNode"（如果 SecondaryNameNode 没有启动，请运行 sbin/stop-dfs.sh 关闭进程，然后再次尝试启动尝试）。如果没有 NameNode 或 DataNode ，那就是配置不成功，请仔细检查之前步骤，或通过查看启动日志排查原因。

![通过jps查看启动的Hadoop进程](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2014/08/install-hadoop-16-jps.png)

**Hadoop无法正常启动的解决方法**: 一般可以查看启动日志来排查原因，注意几点：

-   启动时会提示形如 "DBLab-XMU: starting namenode, logging to /usr/local/hadoop/logs/hadoop-hadoop-namenode-DBLab-XMU.out"，其中 DBLab-XMU 对应你的机器名，但其实启动日志信息是记录在 /usr/local/hadoop/logs/hadoop-hadoop-namenode-DBLab-XMU.log 中，所以应该查看这个后缀为 **.log** 的文件；
-   每一次的启动日志都是追加在日志文件之后，所以得拉到最后面看，对比下记录的时间就知道了。
-   一般出错的提示在最后面，通常是写着 Fatal、Error、Warning 或者 Java Exception 的地方。
-   可以在网上搜索一下出错信息，看能否找到一些相关的解决方法。

此外，**若是 DataNode 没有启动**，可尝试如下的方法（注意这会删除 HDFS 中原有的所有数据，如果原有的数据很重要请不要这样做）：

```bash
# 针对 DataNode 没法启动的解决方法
cd /usr/local/hadoop
./sbin/stop-dfs.sh   # 关闭
rm -r ./tmp     # 删除 tmp 文件，注意这会删除 HDFS 中原有的所有数据
./bin/hdfs namenode -format   # 重新格式化 NameNode
./sbin/start-dfs.sh  # 重启
```

成功启动后，可以访问 Web 界面 [http://localhost:9870](http://localhost:9870/) 查看 NameNode 和 Datanode 信息，还可以在线查看 HDFS 中的文件。

## 运行Hadoop伪分布式实例

上面的单机模式，grep 例子读取的是本地数据，伪分布式读取的则是 HDFS 上的数据。要使用 HDFS，首先需要在 HDFS 中创建用户目录：

```bash
./bin/hdfs dfs -mkdir -p /user/hadoop
```

_注意_: 教材《大数据技术原理与应用》的命令是以"./bin/hadoop dfs"开头的Shell命令方式，实际上有三种shell命令方式。

1.  hadoop fs
2.  hadoop dfs
3.  hdfs dfs

hadoop fs适用于任何不同的文件系统，比如本地文件系统和HDFS文件系统  
hadoop dfs只能适用于HDFS文件系统  
hdfs dfs跟hadoop dfs的命令作用一样，也只能适用于HDFS文件系统

接着将 ./etc/hadoop 中的 xml 文件作为输入文件复制到分布式文件系统中，即将 /usr/local/hadoop/etc/hadoop 复制到分布式文件系统中的 /user/hadoop/input 中。我们使用的是 hadoop 用户，并且已创建相应的用户目录 /user/hadoop ，因此在命令中就可以使用相对路径如 input，其对应的绝对路径就是 /user/hadoop/input:

```bash
./bin/hdfs dfs -mkdir input
./bin/hdfs dfs -put ./etc/hadoop/*.xml input
```

复制完成后，可以通过如下命令查看文件列表：

```bash
./bin/hdfs dfs -ls input
```

伪分布式运行 MapReduce 作业的方式跟单机模式相同，区别在于伪分布式读取的是HDFS中的文件（可以将单机步骤中创建的本地 input 文件夹，输出结果 output 文件夹都删掉来验证这一点）。

```bash
./bin/hadoop jar ./share/hadoop/mapreduce/hadoop-mapreduce-examples-3.3.5.jar grep input output 'dfs[a-z.]+'
```

查看运行结果的命令（查看的是位于 HDFS 中的输出结果）：

```bash
./bin/hdfs dfs -cat output/*
```

结果如下，注意到刚才我们已经更改了配置文件，所以运行结果不同。

![Hadoop伪分布式运行grep结果](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2014/08/install-hadoop-18-grep-output.png)

我们也可以将运行结果取回到本地：

```bash
rm -r ./output    # 先删除本地的 output 文件夹（如果存在）
./bin/hdfs dfs -get output ./output     # 将 HDFS 上的 output 文件夹拷贝到本机
cat ./output/*
```

Hadoop 运行程序时，输出目录不能存在，否则会提示错误 "org.apache.hadoop.mapred.FileAlreadyExistsException: Output directory hdfs://localhost:9000/user/hadoop/output already exists" ，因此若要再次执行，需要执行如下命令删除 output 文件夹:

```bash
./bin/hdfs dfs -rm -r output    # 删除 output 文件夹
```

_运行程序时，输出目录不能存在_: 运行 Hadoop 程序时，为了防止覆盖结果，程序指定的输出目录（如 output）不能存在，否则会提示错误，因此运行前需要先删除输出目录。在实际开发应用程序时，可考虑在程序中加上如下代码，能在每次运行时自动删除输出目录，避免繁琐的命令行操作：

```java
Configuration conf = new Configuration();
Job job = new Job(conf);
 
/* 删除输出目录 */
Path outputPath = new Path(args[1]);
outputPath.getFileSystem(conf).delete(outputPath, true);
```

若要关闭 Hadoop，则运行

```bash
./sbin/stop-dfs.sh
```

_注意_: 下次启动 hadoop 时，无需进行 NameNode 的初始化，只需要运行 `./sbin/start-dfs.sh` 就可以！

## 安装Hadoop集群

当Hadoop采用分布式模式部署和运行时，存储采用分布式文件系统HDFS，而且，HDFS的名称节点和数据节点位于不同机器上。这时，数据就可以分布到多个节点上，不同数据节点上的数据计算可以并行执行，这时的MapReduce分布式计算能力才能真正发挥作用。  
为了降低分布式模式部署难度，本教程简单使用两个节点（两台物理机器）来搭建集群环境，一台机器作为 Master节点，主机名为hadoop01，另一台机器作为 Slave 节点，主机名为hadoop02。由三个以上节点构成的集群，也可以采用类似的方法完成安装部署。  
Hadoop 集群的安装配置大致包括以下步骤：  
步骤1：选定一台机器作为 Master；  
步骤2：在Master节点上创建hadoop用户、安装SSH服务端、安装Java环境；  
步骤3：在Master节点上安装Hadoop，并完成配置；  
步骤4：在其他Slave节点上创建hadoop用户、安装SSH服务端、安装Java环境；  
步骤5：将Master节点上的“/usr/local/hadoop”目录复制到其他Slave节点上；  
步骤6：在Master节点上开启Hadoop；  
上述这些步骤中，关于如何创建hadoop用户、安装SSH服务端、安装Java环境、安装Hadoop等过程，已经在前面介绍伪分布式安装的时候做了详细介绍，请按照之前介绍的方法完成步骤1到步骤4，这里不再赘述。在完成步骤1到步骤4的操作以后，才可以继续进行下面的操作。

## 1.安装虚拟机

请参照教程“[在VMWare中安装Linux虚拟机](https://dblab.xmu.edu.cn/blog/4166/ "在VMWare中安装Linux虚拟机")”，安装好2个Linux虚拟机，主机名分别是hadoop01和hadoop02。由于hadoop02是Slave节点，不需要安装很多的软件，所以，配置可以比hadoop01低一，比如，对于hadoop02而言，内存只需要配置4GB，磁盘只需要配置20GB。  
安装好虚拟机hadoop02以后，首先创建hadoop用户，然后使用hadoop用户登录Linux系统，安装SSH服务端，并安装Java环境。

## 2\. 网络配置

```
由于集群中有两台机器需要设置，所以，在接下来的操作中，一定要注意区分Master节点和Slave节点。为了便于区分Master节点和Slave节点，可以修改各个节点的主机名，这样，在Linux系统中打开一个终端以后，在终端窗口的标题和命令行中都可以看到主机名，就比较容易区分当前是对哪台机器进行操作。在Ubuntu中，我们在 Master 节点（hadoop01）上执行如下命令修改主机名：
```

```bash
sudo vim /etc/hostname
```

执行上面命令后，就打开了“/etc/hostname”这个文件，这个文件里面记录了主机名.因此，打开这个文件以后，里面就只有“ubuntu”这一行内容，可以直接删除，并修改为“hadoop01”（注意是区分大小写的），然后，保存退出vim编辑器，这样就完成了主机名的修改，需要重启Linux系统才能看到主机名的变化。  
要注意观察主机名修改前后的变化。在修改主机名之前，如果用hadoop登录Linux系统，打开终端，进入Shell命令提示符状态，会显示如下内容：

```bash
hadoop@ ubuntu:~$
```

修改主机名并且重启Linux系统之后，用hadoop登录Linux系统，打开终端，进入Shell命令提示符状态，会显示如下内容：

```bash
hadoop@ hadoop01:~$
```

可以看出，这时就很容易辨认出当前是处于Master节点（hadoop01）上进行操作，不会和Slave节点（hadoop02）产生混淆。  
同理，请按照相同的方法，把虚拟机hadoop02中的主机名修改为“hadoop02”，并重启Linux系统。  
然后，使用ifconfig命令获取每台虚拟机的IP地址，具体命令如下：

图3-12给出ifconfig命令的执行效果，从中可以看到，hadoop01的IP地址是192.168.91.128（你的机器的IP地址可能和这个不同）。同理，可以查询到hadoop02的IP地址是192.168.91.129（你的机器的IP地址可能和这个不同）。  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE3-12-ifconfig%E5%91%BD%E4%BB%A4%E6%89%A7%E8%A1%8C%E6%95%88%E6%9E%9C.png)  
图3-12 ifconfig命令执行效果  
然后，在hadoop01中，执行如下命令打开并修改Master节点中的“/etc/hosts”文件：

```bash
sudo vim /etc/hosts
```

可以在hosts文件中增加如下两条IP和主机名映射关系：

```
192.168.91.128   hadoop01
192.168.91.129   hadoop02
```

修改后的效果如图3-13所示。  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE3-13-%E4%BF%AE%E6%94%B9IP%E5%92%8C%E4%B8%BB%E6%9C%BA%E5%90%8D%E6%98%A0%E5%B0%84%E5%85%B3%E7%B3%BB%E5%90%8E%E7%9A%84%E6%95%88%E6%9E%9C.png)  
图3-13 修改IP和主机名映射关系后的效果  
需要注意的是，一般hosts文件中只能有一个127.0.0.1，其对应主机名为localhost，如果有多余127.0.0.1映射，应删除，特别是不能存在“127.0.0.1 hadoop01”这样的映射记录。修改后需要重启Linux系统。  
上面完成了Master节点（hadoop01）的配置，接下来要继续完成对其他Slave节点的配置修改。本教程只有一个Slave节点，主机名为hadoop02。请参照上面的方法，把Slave节点上的“/etc/hostname”文件中的主机名修改为“hadoop02”，同时，修改“/etc/hosts”的内容，在hosts文件中增加如下两条IP和主机名映射关系：

```
192.168.91.128   hadoop01
192.168.91.129   hadoop02
```

修改完成以后，请重新启动Slave节点的Linux系统。  
这样就完成了Master节点和Slave节点的配置，然后，需要在各个节点上都执行如下命令，测试是否相互ping得通，如果ping不通，后面就无法顺利配置成功：

```bash
ping hadoop01 -c 3   # 只ping 3次就会停止，否则要按Ctrl+c中断ping命令
ping hadoop02 -c 3
```

例如，在Master节点上ping Slave1，如果ping通的话，会显示如图3-14所示的结果。  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE3-14-%E4%BD%BF%E7%94%A8ping%E5%91%BD%E4%BB%A4%E7%9A%84%E6%95%88%E6%9E%9C.png)  
图3-14 使用ping命令的效果

## 3\. SSH无密码登录节点

```
必须要让Master节点可以SSH无密码登录到各个Slave节点上。
```

首先需要在hadoop02上执行如下命令安装SSH服务端（如果此前已经安装就不用重复安装）：

```bash
sudo apt-get install openssh-server
```

然后，生成Master节点（hadoop01）的公匙，如果之前已经生成过公钥（在3.3.3节安装伪分布式模式的Hadoop时生成过一次公钥），必须要删除原来生成的公钥，重新生成一次，因为前面我们对主机名进行了修改。在Master节点执行如下命令：

```bash
cd ~/.ssh              # 如果没有该目录，先执行一次ssh localhost
rm ./id_rsa*           # 删除之前生成的公匙（如果已经存在）
ssh-keygen -t rsa       # 执行该命令后，遇到提示信息，一直按回车就可以
```

为了让Master节点能够无密码SSH登录本机，需要在Master节点上执行如下命令：

```bash
cat ./id_rsa.pub >> ./authorized_keys
```

完成后可以执行命令“ssh hadoop01”来验证一下，可能会遇到提示信息，只要输入yes即可，测试成功后，请执行“exit”命令返回原来的终端。  
接下来，在Master节点（hadoop01）将上公匙传输到Slave节点（hadoop02）：

```bash
scp ~/.ssh/id_rsa.pub hadoop@hadoop02:/home/hadoop/
```

上面的命令中，scp是secure copy的简写，用于在 Linux下进行远程拷贝文件，类似于cp命令，不过，cp只能在本机中拷贝。执行scp时会要求输入hadoop02上hadoop用户的密码，输入完成后会提示传输完毕，如图3-15所示。传输完成以后，在hadoop02上的“/home/hadoop”目录下就可以看到文件id\_rsa.pub了。  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE3-15-%E6%89%A7%E8%A1%8Cscp%E5%91%BD%E4%BB%A4%E7%9A%84%E6%95%88%E6%9E%9C.png)  
图3-15 执行scp命令的效果  
接着在Slave节点（hadoop02）上执行如下命令将SSH公匙加入授权：

```bash
mkdir ~/.ssh       # 如果不存在该文件夹需先创建，若已存在，则忽略本命令
cat ~/id_rsa.pub >> ~/.ssh/authorized_keys
rm ~/id_rsa.pub    # 用完以后就可以删掉
```

如果有其他Slave节点，也要执行将Master公匙传输到Slave节点以及在Slave节点上加入授权这两步操作。  
这样，在Master节点上就可以无密码SSH登录到各个Slave节点了，可在Master节点（hadoop01）上执行如下命令进行检验：

```bash
ssh hadoop02
```

执行该命令的效果如图3-16所示。  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE3-16-ssh%E5%91%BD%E4%BB%A4%E6%89%A7%E8%A1%8C%E6%95%88%E6%9E%9C.png)  
图3-16 ssh命令执行效果

## 4\. 配置PATH变量

```
在前面的伪分布式安装内容中，已经介绍过PATH变量的配置方法。可以按照同样的方法进行配置，这样就可以在任意目录中直接使用hadoop、hdfs等命令了。如果还没有配置PATH变量，那么需要在Master节点上进行配置。 首先执行命令“vim ~/.bashrc”，也就是使用vim编辑器打开“~/.bashrc”文件，然后，在该文件最上面的位置加入下面一行内容：
```

```
export PATH=$PATH:/usr/local/hadoop/bin:/usr/local/hadoop/sbin
```

保存后执行命令“source ~/.bashrc”，使配置生效。

## 5\. 配置集群/分布式环境

```
在配置集群/分布式模式时，需要修改“/usr/local/hadoop/etc/hadoop”目录下的配置文件，这里仅设置正常启动所必须的设置项，包括workers、core-site.xml、hdfs-site.xml、mapred-site.xml、yarn-site.xml共5个文件，更多设置项可查看官方说明。
```

## （1）修改文件workers

需要把所有数据节点的主机名写入该文件，每行一个，默认为 localhost（即把本机作为数据节点），所以，在伪分布式配置时，就采用了这种默认的配置，使得节点既作为名称节点也作为数据节点。在进行分布式配置时，可以保留localhost，让Master节点同时充当名称节点和数据节点，或者也可以删掉localhost这行，让Master节点仅作为名称节点使用。  
本教程让Master节点仅作为名称节点使用，因此将hadoop01中的workers文件中原来的localhost删除，只添加如下一行内容：

```
hadoop02
```

## （2）修改文件core-site.xml 

请把hadoop01中的core-site.xml文件修改为如下内容：

```
&lt;configuration&gt;
        &lt;property&gt;
                &lt;name&gt;fs.defaultFS&lt;/name&gt;
                &lt;value&gt;hdfs://hadoop01:9000&lt;/value&gt;
        &lt;/property&gt;
        &lt;property&gt;
                &lt;name&gt;hadoop.tmp.dir&lt;/name&gt;
                &lt;value&gt;file:/usr/local/hadoop/tmp&lt;/value&gt;
                &lt;description&gt;Abase for other temporary directories.&lt;/description&gt;
        &lt;/property&gt;
&lt;/configuration&gt;
```

各个配置项的含义可以参考前面伪分布式模式时的介绍，这里不再赘述。

## （3）修改文件hdfs-site.xml

对于Hadoop的分布式文件系统HDFS而言，一般都是采用冗余存储，冗余因子通常为3，也就是说，一份数据保存三份副本。但是，本教程只有一个Slave节点作为数据节点，即集群中只有一个数据节点，数据只能保存一份，所以 ，dfs.replication的值还是设置为 1。hadoop01中的hdfs-site.xml具体内容如下：

```
&lt;configuration&gt;
        &lt;property&gt;
                &lt;name&gt;dfs.namenode.secondary.http-address&lt;/name&gt;
                &lt;value&gt;hadoop01:50090&lt;/value&gt;
        &lt;/property&gt;
        &lt;property&gt;
                &lt;name&gt;dfs.replication&lt;/name&gt;
                &lt;value&gt;1&lt;/value&gt;
        &lt;/property&gt;
        &lt;property&gt;
                &lt;name&gt;dfs.namenode.name.dir&lt;/name&gt;
                &lt;value&gt;file:/usr/local/hadoop/tmp/dfs/name&lt;/value&gt;
        &lt;/property&gt;
        &lt;property&gt;
                &lt;name&gt;dfs.datanode.data.dir&lt;/name&gt;
                &lt;value&gt;file:/usr/local/hadoop/tmp/dfs/data&lt;/value&gt;
        &lt;/property&gt;
&lt;/configuration&gt;
```

## （4）修改文件mapred-site.xml

hadoop01中的“/usr/local/hadoop/etc/hadoop”目录下有一个mapred-site.xml，把mapred-site.xml文件配置成如下内容：

```
&lt;configuration&gt;
        &lt;property&gt;
                &lt;name&gt;mapreduce.framework.name&lt;/name&gt;
                &lt;value&gt;yarn&lt;/value&gt;
        &lt;/property&gt;
        &lt;property&gt;
                &lt;name&gt;mapreduce.jobhistory.address&lt;/name&gt;
                &lt;value&gt;hadoop01:10020&lt;/value&gt;
        &lt;/property&gt;
        &lt;property&gt;
                &lt;name&gt;mapreduce.jobhistory.webapp.address&lt;/name&gt;
                &lt;value&gt;hadoop01:19888&lt;/value&gt;
        &lt;/property&gt;
        &lt;property&gt;
&lt;name&gt;yarn.app.mapreduce.am.env&lt;/name&gt;
&lt;value&gt;HADOOP_MAPRED_HOME=/usr/local/hadoop&lt;/value&gt;
&lt;/property&gt;
&lt;property&gt;
&lt;name&gt;mapreduce.map.env&lt;/name&gt;
&lt;value&gt;HADOOP_MAPRED_HOME=/usr/local/hadoop&lt;/value&gt;
&lt;/property&gt;
&lt;property&gt;
&lt;name&gt;mapreduce.reduce.env&lt;/name&gt;
&lt;value&gt;HADOOP_MAPRED_HOME=/usr/local/hadoop&lt;/value&gt;
&lt;/property&gt; 
&lt;/configuration&gt;
```

## （5）修改文件 yarn-site.xml

请把hadoop01中的yarn-site.xml文件配置成如下内容：

```
&lt;configuration&gt;
        &lt;property&gt;
                &lt;name&gt;yarn.resourcemanager.hostname&lt;/name&gt;
                &lt;value&gt;hadoop01&lt;/value&gt;
        &lt;/property&gt;
        &lt;property&gt;
                &lt;name&gt;yarn.nodemanager.aux-services&lt;/name&gt;
                &lt;value&gt;mapreduce_shuffle&lt;/value&gt;
        &lt;/property&gt;
&lt;/configuration&gt;
```

上述5个文件全部配置完成以后，需要把Master节点上的“/usr/local/hadoop”文件夹复制到各个节点上。如果之前已经运行过伪分布式模式，建议在切换到集群模式之前首先删除之前在伪分布式模式下生成的临时文件。具体来说，需要首先在Master节点上执行如下命令：

```bash
cd /usr/local/hadoop
sudo rm -r ./tmp     # 删除 Hadoop 临时文件
sudo rm -r ./logs/*   # 删除日志文件
cd /usr/local
tar -zcf ~/hadoop.master.tar.gz ./hadoop   # 先压缩再复制
cd ~
scp ./hadoop.master.tar.gz hadoop02:/home/hadoop
```

然后在hadoop02节点上执行如下命令：

```bash
cd ~
sudo rm -r /usr/local/hadoop    # 删掉旧的（如果存在）
sudo tar -zxf ~/hadoop.master.tar.gz -C /usr/local
sudo chown -R hadoop /usr/local/hadoop
```

同样，如果有其他Slave节点，也要执行将hadoop.master.tar.gz传输到Slave节点以及在Slave节点解压文件的操作。  
首次启动Hadoop集群时，需要先在Master节点（hadoop01）执行名称节点的格式化（只需要执行这一次，后面再启动Hadoop时，不要再次格式化名称节点），命令如下：

```bash
cd /usr/local/hadoop
./bin/hdfs namenode -format
```

现在就可以启动Hadoop了，启动需要在Master节点（hadoop01）上进行，执行如下命令：

```bash
cd /usr/local/hadoop
./sbin/start-dfs.sh
./sbin/start-yarn.sh
./sbin/mr-jobhistory-daemon.sh start historyserver
```

通过命令jps可以查看各个节点所启动的进程。如果已经正确启动，则在Master节点上可以看到NameNode、ResourceManager、SecondaryNameNode和JobHistoryServer进程，如图3-17所示。  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE3-17-Master%E8%8A%82%E7%82%B9%E4%B8%8A%E5%90%AF%E5%8A%A8%E7%9A%84%E8%BF%9B%E7%A8%8B.png)  
图3-17 Master节点上启动的进程  
在Slave节点可以看到DataNode和NodeManager进程，如图3-18所示。  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE3-18-Slave%E8%8A%82%E7%82%B9%E4%B8%8A%E5%90%AF%E5%8A%A8%E7%9A%84%E8%BF%9B%E7%A8%8B.png)  
图3-18 Slave节点上启动的进程  
缺少任一进程都表示出错。另外还需要在Master节点上通过如下命令查看数据节点是否正常启动：

```bash
cd /usr/local/hadoop
./bin/hdfs dfsadmin -report
```

如果屏幕信息中的“Live datanodes”不为 0 ，则说明集群启动成功。由于本教程只有1个Slave节点充当数据节点，因此，数据节点启动成功以后，会显示如图3-19所示信息。  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE3-19-%E9%80%9A%E8%BF%87dfsadmin%E6%9F%A5%E7%9C%8B%E6%95%B0%E6%8D%AE%E8%8A%82%E7%82%B9%E7%9A%84%E7%8A%B6%E6%80%81.png)  
图3-19 通过dfsadmin查看数据节点的状态  
也可以在Linux系统的浏览器中输入地址“[http://hadoop01:9870/”，通过](http://hadoop01:9870/%E2%80%9D%EF%BC%8C%E9%80%9A%E8%BF%87) Web 页面看到查看名称节点和数据节点的状态（如图3-20所示）。如果不成功，可以通过启动日志排查原因。  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE3-20-Hadoop%E9%9B%86%E7%BE%A4%E7%9A%84Web%E7%AE%A1%E7%90%86%E9%A1%B5%E9%9D%A2.png)  
图3-20 Hadoop集群的Web管理页面  
这里再次强调，伪分布式模式和分布式模式切换时需要注意以下事项：  
从分布式切换到伪分布式时，不要忘记修改workers配置文件；  
在两者之间切换时，若遇到无法正常启动的情况，可以删除所涉及节点的临时文件夹，这样虽然之前的数据会被删掉，但能保证集群正确启动。所以，如果集群以前能启动，但后来启动不了，特别是数据节点无法启动，不妨试着删除所有节点（包括Slave节点）上的“/usr/local/hadoop/tmp”文件夹，再重新执行一次“hdfs namenode -format”，再次启动即可。

## 6\. 执行分布式实例

```
执行分布式实例过程与伪分布式模式一样，首先创建HDFS上的用户目录，可以在Master节点（hadoop01）上执行如下命令：
```

```bash
hdfs dfs -mkdir -p /user/hadoop #此前已经配置了PATH环境变量，所以不用路径全称
```

然后，在HDFS中创建一个input目录，并把“/usr/local/hadoop/etc/hadoop”目录中的配置文件作为输入文件复制到input目录中，命令如下：

```bash
hdfs dfs -mkdir input
hdfs dfs -put /usr/local/hadoop/etc/hadoop/*.xml input
```

接着就可以运行 MapReduce 作业了，命令如下：

```bash
hadoop jar /usr/local/hadoop/share/hadoop/mapreduce/hadoop-mapreduce-examples-3.3.5.jar grep input output 'dfs[a-z.]+'
```

运行时的输出信息与伪分布式类似，会显示MapReduce作业的进度，如图3-21所示。  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE3-21-%E8%BF%90%E8%A1%8CMapReduce%E4%BD%9C%E4%B8%9A%E6%97%B6%E7%9A%84%E8%BE%93%E5%87%BA%E4%BF%A1%E6%81%AF.png)  
图3-21 运行MapReduce作业时的输出信息  
执行过程可能会有点慢，但是，如果迟迟没有进度，比如5分钟都没看到进度变化，那么不妨重启Hadoop再次测试。若重启还不行，则很有可能是内存不足引起，建议增大虚拟机的内存，或者通过更改YARN的内存配置来解决。  
在执行过程中，可以在Linux系统中打开浏览器，在地址栏输入“[http://hadoop01:8088/cluster](http://hadoop01:8088/cluster)”， 通过Web界面查看任务进度，在Web界面点击 "Tracking UI" 这一列的“ApplicationMaster”链接（如图3-22所示），可以看到任务的运行信息，如图3-23所示。  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE3-22-%E9%80%9A%E8%BF%87Web%E9%A1%B5%E9%9D%A2%E6%9F%A5%E7%9C%8B%E9%9B%86%E7%BE%A4%E5%92%8CMapReduce%E4%BD%9C%E4%B8%9A%E7%9A%84%E4%BF%A1%E6%81%AF.png)  
图3-22 通过Web页面查看集群和MapReduce作业的信息  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE3-23-%E4%BD%9C%E4%B8%9A%E6%89%A7%E8%A1%8C%E6%83%85%E5%86%B5.png)  
图3-23 作业执行情况  
执行完毕后的输出结果如图3-24所示。  
![](https://dblab.xmu.edu.cn/blog/wp-content/uploads/2023/07/%E5%9B%BE3-24-MapReduce%E4%BD%9C%E4%B8%9A%E6%89%A7%E8%A1%8C%E5%AE%8C%E6%AF%95%E5%90%8E%E7%9A%84%E4%BF%A1%E6%81%AF.png)  
图3-24 MapReduce作业执行完毕后的信息  
最后，关闭Hadoop集群，需要在Master节点（hadoop01）执行如下命令：

```bash
stop-yarn.sh
stop-dfs.sh
mr-jobhistory-daemon.sh stop historyserver
```

至此，就顺利完成了Hadoop集群搭建。