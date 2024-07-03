【oiv】offline image viwer     
\--------------------------------------------------------------  
1.用于查看Hadoop fsimage   
2.语法  
    $> hdfs oiv -i inputfile -o outputfile -P process  
3.inputfile: 要查看的fsimage文件  
   outputfile: 用于保存格式化之后的文件  
   process: 使用什么进程解码，XML|Web|...


【oev】  
\----------------------------------------------------------------  
1.用于查看Hadoop 的 edit 文件   
2.语法  
    $> hdfs oev -i inputfile -o outputfile -P process  
3.inputfile: 要查看的edit文件  
   outputfile: 用于保存格式化之后的文件  
   process: 使用什么进程解码，XML|Web|...


【通过URL访问hdfs】  
\----------------------------------------------------------------

```java
public class TsHadoopFileSystemURL {

static

    {

        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());

    }

public static void main(String[] args) {

        tsHdfsFileSystem1();        

    }

public static void tsHdfsFileSystem1()

    {        

try {

String spec = "hdfs://s100:8020/user/ubuntu/data/a.txt";

URL url = new URL(spec);

InputStream is = url.openStream();

ByteArrayOutputStream baos = new ByteArrayOutputStream();

            IOUtils.copyBytes(is, baos, 1024);

            IOUtils.closeStream(is);

            System.out.println(new String(baos.toByteArray()));

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

public static void tsHdfsFileSystem()

    {

try {

String spec = "hdfs://s100:8020/user/ubuntu/data/a.txt";

URL url = new URL(spec);

InputStream is = url.openStream();

ByteArrayOutputStream baos = new ByteArrayOutputStream();

byte [] buf = new byte[1024];

int len = 0;

while((len = is.read(buf)) != -1)

            {

                baos.write(buf,0,len);

            }

byte [] bs = baos.toByteArray();

            System.out.println(new String(bs));

            baos.close();

            is.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }    

}
```


【FileSyatem-- Hadoop的文件系统常用API】  
\-----------------------------------------------------------------------------------------------

1.获取Hadoop文件系统 getHDFS

```java
public FileSystem getFileSystem()

    {

try {

long start = System.currentTimeMillis();

Configuration conf = new Configuration();

FileSystem fs = FileSystem.get(conf);

            System.out.println("get hdfs successful" + "耗时：" + (System.currentTimeMillis() - start));

return fs;

        } catch (Exception e) {

            e.printStackTrace();

        }

return null;

    }
```


2.GetFsInfo获取HDFS文件系统信息

```java
@Test

public void tsGetFsInfo()

    {

try {

FileSystem fs = getFileSystem();

Path f = new Path(filePath);

Configuration conf = fs.getConf();

            System.out.println("conf-->" + conf.toString());

            System.out.println("conf.hadoop.tmp.dir-->" + conf.get("hadoop.tmp.dir"));

            System.out.println("scheme--> " + fs.getScheme());

            System.out.println("BlockSize--> " + fs. getFileStatus(f).getBlockSize());

            System.out.println("getLen--> " + fs. getFileStatus(f).getLen());

FileStatus fileStatus = fs.getFileStatus(f);

            BlockLocation [] bls = fs.getFileBlockLocations(fileStatus , 0, fileStatus.getLen());

for(BlockLocation bl : bls)

            {

                System.out.println(bl.getHosts());

                System.out.println(bl.getTopologyPaths());

            }

        } catch (Exception e) {

            e.printStackTrace();

        }    

    }
```


    2.Read From DFS 从HDFS中读取文件到本地  


```java
@Test

public void tsGet()

    {    

try {

FileSystem fs = getFileSystem();

Path f = new Path(filePath);

FSDataInputStream fsin = fs.open(f);

            IOUtils.copyBytes(fsin, System.out, 1024, true);                        

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
```

3.Write to DFS 从本地写入文件到HDFS  
     

```java
@Test

public void tsPut()

    {

try {

FileSystem fs = getFileSystem();

Path p = new Path("hdfs://s100:8020/user/ubuntu/data2/1.jpg");

FSDataOutputStream fsout =  fs.create(p);

            java.io.FileInputStream fis = new java.io.FileInputStream("D:\\1.jpg");

            IOUtils.copyBytes(fis, fsout, 1024,true);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
```

              4.Seek 游标

```java
@Test

public void tsSeek()

    {

try {

FileSystem fs = getFileSystem();

Path p = new Path("hdfs://s100:8020/user/ubuntu/data2/1.jpg");

FSDataInputStream fsin =  fs.open(p);

            java.io.FileOutputStream fos = new java.io.FileOutputStream("D:\\1-1.jpg");

            IOUtils.copyBytes(fsin, fos, 1024, false);

            fsin.seek(0);

            fos = new java.io.FileOutputStream("D:\\1-2.jpg");

            IOUtils.copyBytes(fsin, fos, 1024, true);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
```


        5.Append 追加  


```java
@Test

public void teAppend()

    {

try {

FileSystem fs = getFileSystem();

Path p = new Path("hdfs://s100:8020/user/ubuntu/data/a.txt");

FSDataOutputStream fsout = fs.append(p);

            fsout.writeChars("how are you ~");

            fsout.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }    
```


    6.set Configuration 手动设定配置文件的值  


```java
@Test

public void tsSetConf()

    {

try {

Configuration conf = new Configuration();

            conf.set("dfs.replication", "10");    

            conf.set("dfs.blocksize", "100k");    

FileSystem fs = FileSystem.get(conf);

Path p = new Path("hdfs://s100:8020/user/ubuntu/data2/1.jpg");        

FSDataOutputStream fsout = fs.create(p);

FileInputStream fis = new FileInputStream("D:\\1.jpg");

            IOUtils.copyBytes(fis, fsout, 1024, true);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
```

              7.listStatus 查看文件结构

```java
@Test

public void tsFileListStatus()

    {

try {

Configuration conf = new Configuration();

FileSystem fs = FileSystem.get(conf);

Path p = new Path("hdfs://s100:8020/user/ubuntu");        

            FileStatus [] fsStatus = fs.listStatus(p);

for (FileStatus f : fsStatus) {

                System.out.println(f.getPath() + " :is File = " + f.isFile());

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
```

         8.getmerge 合并下载  
        $> hadoop fs -getmerge -nl  /user/ubuntu/data2/1.txt  /user/ubuntu/data/a.txt ~/downloads/merge.txt  
        9.globStatus  
        a.PathFileter 应用正则表达式对文件进行检索过滤

```java
@Test

public void tsPathFileter()

    {

try {

Configuration conf = new Configuration();

FileSystem fs = FileSystem.get(conf);

Path p = new Path("hdfs://s100:8020/user/ubuntu/*");    

String regex = "^.*/ubuntu/data$";

MyPathFilter filter = new MyPathFilter(regex);

            FileStatus [] fsStatus = fs.globStatus(p,filter);

for (FileStatus f : fsStatus) {

                System.out.println(f.getPath());

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
```


    10.delete  


```java
@Test

public void tsDelete()

    {

try {

Configuration conf = new Configuration();

FileSystem fs = FileSystem.get(conf);

Path p = new Path("hdfs://s100:8020/user/ubuntu/data2");

boolean b = fs.delete(p, true);

            System.out.println("delete == " + b);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
```

11.FileUtil 文件工具类  
Path \[\] FileUtil.stat2Paths(FileStatus \[\] fsts)    //返回Path数组