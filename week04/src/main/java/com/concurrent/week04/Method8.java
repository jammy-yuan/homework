package com.concurrent.week04;

public class Method8 {
	
	public static void method8() throws Exception {
		MyRunnable run = new MyRunnable();
		Thread th1 = new Thread(run);
		th1.start();
		
		//主线程不断轮询查看子线程是否结束
		while(th1.isAlive()) {
			System.out.println("子线程未结束");
		}
		System.out.println("子线程结束");
		System.out.println("主线程结束");
	}

}
