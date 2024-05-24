package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestMain {

	public static void main(String[] args) {
		// 创建对象（真正的对象）
		MyService obj = new MyServiceImpl();
//		obj.method1();
//		obj.method2();
		
		//在不修改源码的情况下，重写method2的实现
		//创建obj对象的代理对象
		/*
		 * Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
		 * 第一个参数 classload：类加载器
		 * 第二个参数 Class<?>[] interfaces：真正对象实现的接口
		 * 第三个参数 InvocationHandler h：如何处理客户端的调用
		 */
		MyService proxy = (MyService)Proxy.newProxyInstance(TestMain.class.getClassLoader(), 
				               obj.getClass().getInterfaces(), 
				               new InvocationHandler() {
								
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// 如何处理客户端的调用
				if(method.getName().equals("method2")) {
					//重写逻辑
					System.out.println("In Proxy Method2");
					return null;
				}else {
					//对于其他方法，直接调用真正的对象完成
					return method.invoke(obj, args);
				}
			}
		});
		
		//通过代理对象去调用
		proxy.method1();
		proxy.method2();
	}
}















