package hilos;

public class Cuenta_AZ implements Runnable {

	@Override
	public void run() {
		try{
			for (char c = 'A'; c <= 'Z'; c++) {
				System.out.println(c);
				Thread.sleep(100);
			}
		}catch(InterruptedException e){
			
		}
	}

}
