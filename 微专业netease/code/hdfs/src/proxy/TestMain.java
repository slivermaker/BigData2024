package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestMain {

	public static void main(String[] args) {
		// �������������Ķ���
		MyService obj = new MyServiceImpl();
//		obj.method1();
//		obj.method2();
		
		//�ڲ��޸�Դ�������£���дmethod2��ʵ��
		//����obj����Ĵ������
		/*
		 * Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
		 * ��һ������ classload���������
		 * �ڶ������� Class<?>[] interfaces����������ʵ�ֵĽӿ�
		 * ���������� InvocationHandler h����δ���ͻ��˵ĵ���
		 */
		MyService proxy = (MyService)Proxy.newProxyInstance(TestMain.class.getClassLoader(), 
				               obj.getClass().getInterfaces(), 
				               new InvocationHandler() {
								
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// ��δ���ͻ��˵ĵ���
				if(method.getName().equals("method2")) {
					//��д�߼�
					System.out.println("In Proxy Method2");
					return null;
				}else {
					//��������������ֱ�ӵ��������Ķ������
					return method.invoke(obj, args);
				}
			}
		});
		
		//ͨ���������ȥ����
		proxy.method1();
		proxy.method2();
	}
}















