package pgv.proyecto.escritores.lectores;

import java.util.concurrent.Semaphore;

public class LightSwitch {

	private Semaphore mutex;
	private int contador;
	
	public LightSwitch() {
		contador =0;
		this.mutex = new Semaphore(1);
		
	}
	
	public void lock(Semaphore semaforo){
		try {
			mutex.acquire();
			contador++;
			if(contador==1){
				semaforo.acquire();
			}
			mutex.release();
		
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void unlock(Semaphore semaforo){
		try {
			mutex.acquire();
			contador--;
			if(contador==0){
				semaforo.release();
			}
			mutex.release();
		
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
