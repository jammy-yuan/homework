package com.concurrent.week04;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Method2 {
	
	public static void method2() {
		//设置信号量为1
		CountDownLatch latch = new CountDownLatch(1);
		CountDownLatchTask task = new CountDownLatchTask(latch);
		Thread th1 = new Thread(task);
		th1.start();
		try {
			//唤醒主线程
			latch.await();
			System.out.println("主线程结束");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static class CountDownLatchTask implements Runnable {
		
		private CountDownLatch latch;
		
		public CountDownLatchTask(CountDownLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {
			try {
				//取1~10000之间的一个随机数
				int mil = new Random().nextInt(10000);
				//让当前线程睡眠
				TimeUnit.MILLISECONDS.sleep(mil);
				System.out.println("线程" + Thread.currentThread().getName() + "睡眠了" + mil + "ms");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//信号量-1
			latch.countDown();
		}
		
	}

}
