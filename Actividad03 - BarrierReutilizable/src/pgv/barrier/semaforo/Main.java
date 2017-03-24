package pgv.barrier.semaforo;

import java.util.concurrent.Semaphore;

public class Main {

	
	public static void main(String[] args) {
		
	

		Semaphore mutex = new Semaphore(1);
		Semaphore barrierSubida = new Semaphore(0);
		Semaphore barrierBajada = new Semaphore(1);
		
		final int N =4;
		Contador contador = new Contador(0);
		
		Hilo_Trabajador htrabajador1 = new Hilo_Trabajador("Trabajador1",mutex,barrierBajada,barrierSubida,N,contador);
		Hilo_Trabajador htrabajador2 = new Hilo_Trabajador("Trabajador2",mutex,barrierBajada,barrierSubida,N,contador);
		Hilo_Trabajador htrabajador3 = new Hilo_Trabajador("Trabajador3",mutex,barrierBajada,barrierSubida,N,contador);
		Hilo_Trabajador htrabajador4 = new Hilo_Trabajador("Trabajador4",mutex,barrierBajada,barrierSubida,N,contador);
		// Se crean cuatro hilos

		htrabajador1.start();
		htrabajador2.start();
		htrabajador3.start();
		htrabajador4.start();
	}
}
