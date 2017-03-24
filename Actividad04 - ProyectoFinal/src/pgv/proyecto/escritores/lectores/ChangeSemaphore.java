package pgv.proyecto.escritores.lectores;

import java.util.concurrent.Semaphore;

public class ChangeSemaphore {
	
	public static void lock(Semaphore barrera){
		 try {
			barrera.acquire(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void unlock(Semaphore barrera){
		barrera.release();
	}
}
