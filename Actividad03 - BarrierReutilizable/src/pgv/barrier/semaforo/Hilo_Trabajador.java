package pgv.barrier.semaforo;

import java.util.concurrent.Semaphore;

public class Hilo_Trabajador extends Thread {

	private String name;
    private Semaphore mutex,barrierBajada, barrierSubida;
    private int n;
    private Contador contador;
    private final long MAX,MIN;
    
    public Hilo_Trabajador(String name,Semaphore mutex, Semaphore barrierBajada, Semaphore barrierSubida, int n, Contador contador) {
        this.name = name;
    	this.mutex = mutex;
        this.barrierBajada = barrierBajada;
        this.barrierSubida = barrierBajada;
        this.n = n;
        this.contador = contador;
        MAX = 4;
        MIN = 1;
    }
    @Override
    public void run() {
		
    	for (int i = 0; i < 5; i++) 
		try {
		System.out.println(this.name+"--> Trabajando abajo!");
		sleep((long) ((Math.random()*MAX)+MIN));
		
			mutex.acquire();
				contador.setNum(contador.getNum()+1);
				if (contador.getNum()==n) {
					barrierBajada.acquire();
					barrierSubida.release();
				}
			mutex.release();
			
			//Esperando a que llegue el último
			
			System.out.println(this.name+"--> preparado para subir");
			barrierSubida.acquire();
			
			System.out.println(this.name+"--> subiendo");
			barrierSubida.release();
			
			sleep((long) ((Math.random()*MAX)+MIN));
			System.out.println(this.name+"--> trabajo arriba  terminado!");
			
			mutex.acquire();
				contador.setNum(contador.getNum()-1);
				if (contador.getNum()==0) {
					barrierSubida.acquire();
					barrierBajada.release();
					}
			mutex.release();
			
			//Esperando a que llegue el último
			System.out.println(this.name+"--> preparado para bajar");
			barrierBajada.acquire();
			
			System.out.println(this.name+"--> Estoy bajando!");
			barrierBajada.release();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
         
}
