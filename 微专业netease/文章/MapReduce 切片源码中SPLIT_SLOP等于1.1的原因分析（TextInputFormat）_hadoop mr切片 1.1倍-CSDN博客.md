![](https://csdnimg.cn/release/blogv2/dist/pc/img/original.png)

[AdamShyly](https://blog.csdn.net/Adam_captain "AdamShyly") ![](https://csdnimg.cn/release/blogv2/dist/pc/img/newUpTime2.png) 已于 2022-04-11 23:57:18 修改

之所以是1.1倍的splitSize，原因应该是考虑到文件大小为32.1M这种情况，由于切片大小默认等于块大小，所以此时数据的块存储跟切片存储大小一致，都被分为32M和0.1M。而此时由于有两个切片则不得不开启两个MapTask。而MR不擅长处理小文件的运算也是因为此时启动MapTask的时间耗费比计算时间还长（此时不如用python处理）。而如果将32.1M只当成一个切片来处理的话，虽然数据块是位于两个节点服务器中，而MapTask只在其中一个服务器中，但只在两个服务器中传递这0.1M的时间成本相比于另外启动一个MapTask的时间而言应该会更加短。这些都是出于时间成本的考虑。

还有另一种35.3M`32M * 1.1 = 35.2M`的情况，此时由于大于1.1倍的splitSize，则需要切片，但需要注意每一切片的大小依然是块大小32M，所以此时会被切割成32M和3.3M。  
![在这里插入图片描述](https://img-blog.csdnimg.cn/3e4dbc665399447c851e9dbc56f70b8c.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAQWRhbVNoeWx5,size_20,color_FFFFFF,t_70,g_se,x_16)