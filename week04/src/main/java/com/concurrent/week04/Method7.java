package com.concurrent.week04;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Method7 {
	
	public static void method7() throws Exception {
		//设置信号量为2，当有两个线程都调用了await()之后这两个线程才继续执行
		CyclicBarrier barrier = new CyclicBarrier(2);
		MyThread th1 = new MyThread(barrier);
		th1.start();
		
		//主线程阻塞，若子线程也阻塞，则达到CyclicBarrier设置的信号量，所有线程继续执行
		barrier.await();
		System.out.println("主线程结束");
	}
	
	public static class MyThread extends Thread {
		private CyclicBarrier barrier;
		
		public MyThread(CyclicBarrier barrier) {
			this.barrier = barrier;
		}
		
		@Override
		public void run() {
			try {
				//取1~10000之间的一个随机数
				int mil = new Random().nextInt(10000);
				//让当前线程睡眠
				TimeUnit.MILLISECONDS.sleep(mil);
				System.out.println("线程" + Thread.currentThread().getName() + "睡眠了" + mil + "ms");
				
				//子线程阻塞
				barrier.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
