package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * App
 * 
 */
public class App {
	public static void main(String[] args) {

		//目标对象
		final WelcomeService target = new WelcomeServiceImpl() ;


		//类加载器
		ClassLoader loader = ClassLoader.getSystemClassLoader();

		//接口集合
		Class[] interfaces = {WelcomeService.class,WelcomeService2.class};

		//处理器
		InvocationHandler h = new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//System.out.println("hello world");
				long s = System.nanoTime();
				Object ret = method.invoke(target , args) ;
				long f = System.nanoTime();
				System.out.println(method.getName()+"耗时"+Long.toString(f-s));
				return ret;
			}
		} ;


		//创建代理对象
		Object o = Proxy.newProxyInstance(loader, interfaces, h);
		//访问代理对象的方法
		WelcomeService w1 = (WelcomeService) o;
		WelcomeService2 w2 = (WelcomeService2) o;
		w1.sayHello("李四");
		w2.sayhello2("张三");
	}
}
