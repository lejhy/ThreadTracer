package Tests;

public class Tests {

	public static void main(String[] args) {

		System.out.println("Creating Thread groups");
		ThreadGroup threadGroup1A = new ThreadGroup("Test ThreadGroup 1A");
		ThreadGroup threadGroup2A = new ThreadGroup(threadGroup1A, "Test ThreadGroup 2A");
		ThreadGroup threadGroup2B = new ThreadGroup(threadGroup1A, "Test ThreadGroup 2B");
		System.out.println("Done!");
		System.out.println("Creating Threads");
		Thread thread1A1 = new Thread(threadGroup1A, () -> loop());
		thread1A1.setName("Test Thread 1A1");

		Thread thread1A2 = new Thread(threadGroup1A, () -> loop());
		thread1A2.setName("Test Thread 1A2");

		Thread thread2B1 = new Thread(threadGroup2B, () -> loop());
		thread2B1.setName("Test Thread 2B1");

		Thread thread2B2 = new Thread(threadGroup2B, () -> loop());
		thread2B2.setName("Test Thread 2B2");

		Thread thread2B3 = new Thread(threadGroup2B, () -> loop());
		thread2B3.setName("Test Thread 2B3");
		System.out.println("Done!");

		System.out.println("Starting threads");
		thread1A1.start();
		thread1A2.start();
		thread2B1.start();
		thread2B2.start();
		thread2B3.start();
		System.out.println("Done!");
	}

	private static void loop(){
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				int x = 0;
			}
		}
	}
}
