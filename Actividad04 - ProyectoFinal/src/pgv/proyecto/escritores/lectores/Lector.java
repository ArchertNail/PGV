package pgv.proyecto.escritores.lectores;

import java.util.concurrent.Semaphore;

import javafx.application.Platform;

public class Lector extends Thread {

	private Semaphore barreraLector, barreraEscritor;
	private Zonas zonas;
	private Lector hiloLector;
	private LightSwitch l;

	public Lector(LightSwitch l, Semaphore barreraLector, Semaphore barreraEscritor, Zonas zonas) {
		this.barreraLector = barreraLector;
		this.barreraEscritor = barreraEscritor;
		this.zonas = zonas;
		this.hiloLector = this;
		this.l = l;
	}

	@Override
	public void run() {
		try {

			barreraLector.acquire();
			l.lock(barreraEscritor);
			barreraLector.release();

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					zonas.zonaEsperaProperty().remove(hiloLector.getName());
					zonas.zonaTareaProperty().add(hiloLector.getName());
				}
			});
			Thread.sleep(1000);
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					zonas.zonaTareaProperty().remove(hiloLector.getName());
				}
			});
			l.unlock(barreraEscritor);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// # first in locks 5 mutex.signal() 6 7 # critical section for readers
		// 8 9 mutex.wait() 10 readers -= 1 11 if readers == 0: 12
		// roomEmpty.signal() # last out unlocks 13 mutex.signal()
	}
}
