/*	Lock occurs when multiple processes try to
 * access the same resource at the same time
 *  
 * 	One process loses out and must wait for the 
 * other to finish
 * 	
 * 	A deadlock occurs when the waiting process is still 
 * holding on to another resource that the first needs 
 * before it can finish.
 * 
 *  The example below demonstrate the way to create deadlock,
 * the 2 threads try to access the same resource which is in the
 * the synchronous process of other thread.
 * 
 *  The 2 threads have to wait other to access the needed resources.
 *  --> Deadlock occurs
 */
package com.vinh.deadlock2;

import java.util.concurrent.TimeUnit;

public class Demo {
	public static boolean flag = false;
	public static int i = 0;

	public static void main(String[] args) throws InterruptedException {
		final String resource1 = "r1";
		final String resource2 = "r2";

		/*
		 * thread number 1, when thread 1 is done, system will print "thread1 done" and
		 * change the value of FLAG to TRUE, if thread 1 is not finished FLAG value is
		 * still FALSE
		 */
		Thread thread1 = new Thread() {
			public void run() {
				synchronized (resource1) {
					System.out.println("Thread 1 run");
					try {
						Thread.sleep(100);
					} catch (Exception e) {
					}
					synchronized (resource2) {
					}
					System.out.println("thread1 done");
					flag = true;
				}
			}
		};

		/*
		 * thread number 1, when thread 1 is done, system will print "thread2 done"
		 */
		Thread thread2 = new Thread() {
			public void run() {
				synchronized (resource2) {
					System.out.println("Thread 2 run");
					try {
						Thread.sleep(100);
					} catch (Exception e) {
					}
					synchronized (resource1) {
					}
					System.out.println("thread2 done");
				}
			}
		};

		thread1.start();
		thread2.start();
		/*
		 * check if the thread 1 is finished, if thread 1 is not finished, system will
		 * print "running..."
		 * 
		 * Function of this loop is to demonstrate the deadlock situation, because the 2
		 * thread number 1 and 2 cannot finish --> deadlock occurs, system will keep
		 * printing the String "running..."
		 */
		while (!flag) {
			System.out.println("running..." + i);
			i++;
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
			}
		}

	} // end of main

} // end of class
