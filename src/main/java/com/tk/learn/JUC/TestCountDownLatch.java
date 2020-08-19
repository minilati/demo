package com.tk.learn.JUC;

import jdk.nashorn.internal.ir.CallNode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * CountDownLatch ：闭锁，在完成某些运算是，只有其他所有线程的运算全部完成，当前运算才继续执行
 */
public class TestCountDownLatch {

	public static void main(String[] args) {
		final CountDownLatch latch = new CountDownLatch(10);
		LatchDemo ld = new LatchDemo(latch);

		long start = System.currentTimeMillis();

		for (int i = 0; i < 10; i++) {
			new Thread(ld).start();
		}

		try {
			latch.await();
			System.out.println("success!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();

		System.out.println("耗费时间为：" + (end - start));
	}

}

class LatchDemo implements Runnable {

	private CountDownLatch latch;

	private int num =0;

	public LatchDemo(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {


		try {
			for (int i = 0; i < 10; i++) {
				Thread.sleep(100);
				//System.out.println(Thread.currentThread().getName()+":"+ num++);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			latch.countDown();

		}

	}

}