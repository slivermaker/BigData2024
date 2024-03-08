**update-alternatives --config java**:
来表示展示java版本的优先级，然后挑选序号可以切换

**卸载jdk**
> dpkg --list | grep -i jdk
> apt-get purge jdk*
> apt-get purge icedtea-* jdk-*

**切换java环境**
```bash

# 设置 Java 8
export JAVA_8_HOME=/usr/lib/jvm/jdk1.8.0_172
export PATH=$JAVA_8_HOME/bin:$PATH

# 设置 Java 11
export JAVA_11_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export PATH=$JAVA_11_HOME/bin:$PATH

# 切换到 Java 8
switch_to_java8() {
  export JAVA_HOME=$JAVA_8_HOME
}

# 切换到 Java 11
switch_to_java11() {
  export JAVA_HOME=$JAVA_11_HOME
}

# 默认使用 Java 8
switch_to_java8

```

