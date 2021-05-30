package com.concurrent.week04;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Method1 {
	
	public static void method1() {
        
        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        Callable<Integer> call1 = new MyCallable();
        // 异步执行 下面方法
        FutureTask<Integer> ft = new FutureTask<Integer>(call1);
		new Thread(ft).start();
        int result;
		try {
			result = ft.get(); //这是得到的返回值
			
			// 确保  拿到result 并输出
	        System.out.println("异步计算结果为："+result);
	        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
	        // 然后退出main线程
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private static int sum() {
        return fibo(36);
    }
    
    private static int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
    
    public static class MyCallable implements Callable<Integer> {

		@Override
		public Integer call() throws Exception {
			return Integer.valueOf(sum());
		}
    }

}
