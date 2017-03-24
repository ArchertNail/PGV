package hilos;

import principal.Numero;

public class Cuenta20 extends Thread {

	Numero num;
	public Cuenta20(Numero num){
		this.num = num;
	}
	//es una manera de crear un hilo
	@Override
	public void run() {
		int i=1;	
		
		try{
			while(i++<=20){
				num.setN(num.getN()+1);
				System.out.println(num.getN()+" -> " + getName());
				sleep(100);
			}
		}catch(InterruptedException e){
			
		}
	
	}
}
