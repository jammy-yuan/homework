package com.concurrent.week04;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyRunnable implements Runnable {
	
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
	}

}
