package com.concurrent.week04;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Method3 {
	
	public static void method3() throws InterruptedException, ExecutionException {
		//Future模式
		CompletableFuture<Void> cf = CompletableFuture.runAsync(new MyRunnable());
		//线程回调，唤醒主线程
		cf.get();
		System.out.println("主线程结束");
	}
	
	public static void method4() throws InterruptedException {
		MyRunnable run1 = new MyRunnable();
		Thread th1 = new Thread(run1);
		th1.start();
		
		//主线程等待thread1结束再继续
		th1.join();
		System.out.println("主线程结束");
	}
}
