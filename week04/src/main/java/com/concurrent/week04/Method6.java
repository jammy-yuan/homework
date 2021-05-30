package com.concurrent.week04;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Method6 {
	
	public static void method6() throws InterruptedException {
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(1);
		MyThread th1 = new MyThread(queue);
		th1.start();
		
		//主线程拿到子线程传递的值再继续走下去
		String take = queue.take();
		System.out.println(take);
		System.out.println("主线程结束");
	}

	public static class MyThread extends Thread {
		private ArrayBlockingQueue<String> queue;
		
		public MyThread(ArrayBlockingQueue<String> queue) {
			this.queue = queue;
		}
		
		@Override
		public void run() {
			try {
				//取1~10000之间的一个随机数
				int mil = new Random().nextInt(10000);
				//让当前线程睡眠
				TimeUnit.MILLISECONDS.sleep(mil);
				System.out.println("线程" + Thread.currentThread().getName() + "睡眠了" + mil + "ms");
				
				//子线程在队列中传递一个值
				queue.put("子线程睡眠了" + mil + "ms");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
}
