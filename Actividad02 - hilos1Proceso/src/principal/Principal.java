package principal;

import hilos.Cuenta20;
import hilos.Cuenta_AZ;

public class Principal {

	public static void main(String[] args) {
		
		Numero num = new Numero(5);
		
		//creamos los hilo, uno hereda de Thread y otro lo creamos tal cual, sin herencia. El hilo del Thread lo creamos facil, 
		//pero el otro tenemos que meterlo dentro de un objeto Thread para acceder a su funcion Run()
		
		Cuenta20 hilo1;
		Cuenta20 hilo3;
		Cuenta_AZ hiloRunable;
		Thread hilo2;
		
		hilo1 = new Cuenta20(num);
		hilo1.setName("hilo1");
		hilo3 = new Cuenta20(num);
		hilo3.setName("hilo3");
		hilo3.setPriority(Thread.MAX_PRIORITY);
		/*hiloRunable = new Cuenta_AZ();
		hilo2 = new Thread(hiloRunable);*/
		
		hilo1.start();
		hilo3.start();
	
		System.out.println("FIN");

	}

}
