file://<HOME>/code/%E7%AC%94%E8%AE%B0/%E6%80%BB%E7%8E%AF%E5%A2%83%E9%85%8D%E7%BD%AE/test.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:
Scala version: 3.3.1
Classpath:
<HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.1/scala3-library_3-3.3.1.jar [exists ], <HOME>/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.10/scala-library-2.13.10.jar [exists ]
Options:



action parameters:
offset: 99
uri: file://<HOME>/code/%E7%AC%94%E8%AE%B0/%E6%80%BB%E7%8E%AF%E5%A2%83%E9%85%8D%E7%BD%AE/test.java
text:
```scala
import java.io.*;
import java.util.*;


public class test{
    public static void main(String[] arg@@s) {
        Map<Integer,Integer>map=new HashMap<>();
        // for(var i=0;i<10;i++)
        //     map.compute(i*i, (k,v)->v==null?1:v+1);

        // for(var t:map.entrySet()){
        //     System.out.println(t.getValue());
        // }
    }
}
```



#### Error stacktrace:

```
scala.collection.Iterator$$anon$19.next(Iterator.scala:973)
	scala.collection.Iterator$$anon$19.next(Iterator.scala:971)
	scala.collection.mutable.MutationTracker$CheckedIterator.next(MutationTracker.scala:76)
	scala.collection.IterableOps.head(Iterable.scala:222)
	scala.collection.IterableOps.head$(Iterable.scala:222)
	scala.collection.AbstractIterable.head(Iterable.scala:933)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:168)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.HoverProvider$.hover(HoverProvider.scala:34)
	scala.meta.internal.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:352)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator