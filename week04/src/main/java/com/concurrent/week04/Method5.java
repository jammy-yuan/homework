package com.concurrent.week04;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Method5 {
	
	public static void method5() throws InterruptedException {
		Object o = new Object();
		MyThread th1 = new MyThread(o);
		
		//获得对象o的锁
		synchronized (o) {
			//在同步块里启动子线程，以防子线程先跑o.notify()
			th1.start();
			//阻塞主线程，等待子线程唤醒
			o.wait();
		}
		System.out.println("主线程结束");
	}
	
	public static class MyThread extends Thread {
		private Object o;
		
		public MyThread(Object o) {
			this.o = o;
		}
		
		@Override
		public void run() {
			try {
				//取1~10000之间的一个随机数
				int mil = new Random().nextInt(10000);
				//让当前线程睡眠
				TimeUnit.MILLISECONDS.sleep(mil);
				System.out.println("线程" + Thread.currentThread().getName() + "睡眠了" + mil + "ms");
				synchronized (o) {
					o.notify();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
