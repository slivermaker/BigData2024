### 选择版本

进入MySQL官方的Community Server选择历史版本：[https://downloads.mysql.com/archives/community/](https://downloads.mysql.com/archives/community/)

![image](https://img2023.cnblogs.com/blog/2765564/202306/2765564-20230608154331165-1354979301.png)

### 下载tar包

可以使用wget命令链接下载地址，也可以使用腾讯云提供的文件上传，将tar包放在Ubuntu的一个目录中

```
wget https://downloads.mysql.com/archives/get/p/23/file/mysql-server_5.7.36-1ubuntu18.04_amd64.deb-bundle.tar
```

在目录下解压tar包

```
tar xvf ./mysql-server_5.7.36-1ubuntu18.04_amd64.deb-bundle.tar
```

解压后目录

![image](https://img2023.cnblogs.com/blog/2765564/202306/2765564-20230608154345299-1778236391.png)

### 安装

安装依赖lib包

```
sudo apt-get install ./libmysql*
sudo apt-get install libtinfo5
```

安装客户端和服务端，按提示可能要先安装community版本

```
sudo apt-get install ./mysql-community-client_5.7.36-1ubuntu18.04_amd64.deb
sudo apt-get install ./mysql-client_5.7.36-1ubuntu18.04_amd64.deb
sudo apt-get install ./mysql-community-server_5.7.36-1ubuntu18.04_amd64.deb
sudo apt-get install ./mysql-server_5.7.36-1ubuntu18.04_amd64.deb 
```

过程中会提示设置MySQL的密码，用户名默认root

### 启动MySQL

#### 检查状态

一般安装成功就自动启动，输入命令检查启动状态

```
systemctl status mysql.service
```

![image](https://img2023.cnblogs.com/blog/2765564/202306/2765564-20230608154358523-530136479.png)

绿色的active表示运行中

#### 登录MySQL

```
mysql -u root -p
```

输入密码后，如下界面就是进入MySQL命令行

![image](https://img2023.cnblogs.com/blog/2765564/202306/2765564-20230608154405268-1956815903.png)

输入 `exit` 退出

安装成功，可以选择删除安装包

```
sudo <span class="hljs-built_in">rm</span> -rf /安装包所在目录（不能是根目录）
```