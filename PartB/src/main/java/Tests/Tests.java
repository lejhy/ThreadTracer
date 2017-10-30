package Tests;

public class Tests {

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			ThreadGroup group = new ThreadGroup(String.valueOf(i+65));
			for (int j = 0; j < 2; j++) {
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {while(true){try{Thread.sleep(10000);} catch (Exception e) {}}}
			
				});
				thread.setName(String.valueOf(i+65) + String.valueOf(j+65));
			}
		}
		
	}
}
