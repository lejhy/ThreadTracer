package Tests;

public class Tests {

	public static void main(String[] args) {


		ThreadGroup threadGroup1A = new ThreadGroup("Test ThreadGroup 1A");
		ThreadGroup threadGroup2A = new ThreadGroup(threadGroup1A,"Test ThreadGroup 2A");
		ThreadGroup threadGroup2B = new ThreadGroup(threadGroup1A,"Test ThreadGroup 2B");

		Thread thread1A1 = new Thread(threadGroup1A,() -> {while(true){try{Thread.sleep(10000);} catch (Exception e) {int x =0;}}});
		thread1A1.setName("Test Thread 1A1");

		Thread thread1A2 = new Thread(threadGroup1A,() -> {while(true){try{Thread.sleep(10000);} catch (Exception e) {int x =0;}}});
		thread1A2.setName("Test Thread 1A2");

		Thread thread2B1 = new Thread(threadGroup2B,() -> {while(true){try{Thread.sleep(10000);} catch (Exception e) {int x =0;}}});
		thread2B1.setName("Test Thread 2B1");

		Thread thread2B2 = new Thread(threadGroup2B,() -> {while(true){try{Thread.sleep(10000);} catch (Exception e) {int x =0;}}});
		thread2B2.setName("Test Thread 2B2");

		Thread thread2B3 = new Thread(threadGroup2B,() -> {while(true){try{Thread.sleep(10000);} catch (Exception e) {int x =0;}}});
		thread2B3.setName("Test Thread 2B3");

		thread1A1.start();
		thread1A2.start();
		thread2B1.start();
		thread2B2.start();
		thread2B3.start();

		try {
			thread1A1.join();
			thread1A2.join();
			thread2B1.join();
			thread2B2.join();
			thread2B3.join();
		} catch (Exception e){}
	}
}
