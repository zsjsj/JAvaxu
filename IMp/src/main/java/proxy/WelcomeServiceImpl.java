package proxy;

/**
 * 接口实现类
 */
public class WelcomeServiceImpl implements WelcomeService ,WelcomeService2{
	public void sayHello(String msg) {
		System.out.println("hello : " + msg);
	}
	public void sayhello2(String msg) {
		System.out.println("nihao ："+msg);
	}
	public void ceshi(){

	}
}
