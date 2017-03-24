package pgv.proyecto.escritores.lectores;

import java.util.concurrent.Semaphore;

import javafx.application.Platform;

public class Escritor extends Thread {

	private Semaphore barreraLector, barreraEscritor;
	private Zonas zonas;
	private Escritor hiloEscritor;
	private LightSwitch l;

	public Escritor(LightSwitch l, Semaphore barreraLector, Semaphore barreraEscritor, Zonas zonas) {
		this.barreraLector = barreraLector;
		this.barreraEscritor = barreraEscritor;
		this.l = l;
		this.zonas = zonas;
		this.hiloEscritor = this;
	}

	@Override
	public void run() {

		try {
			l.lock(barreraLector);
			barreraEscritor.acquire();
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					zonas.getZonaEspera().remove(hiloEscritor.getName());
					zonas.getZonaTarea().add(hiloEscritor.getName());
				}
			});
			Thread.sleep(2000);

			Platform.runLater(new Runnable() {
				@Override
				public void run() {

					zonas.getZonaTarea().remove(hiloEscritor.getName());
				}
			});
			barreraEscritor.release();
			l.unlock(barreraLector);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
