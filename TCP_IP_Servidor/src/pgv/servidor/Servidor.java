package pgv.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	static final int Puerto = 2000;

    public Servidor( ) {

	    try {
	
	        ServerSocket skServidor = new ServerSocket(Puerto);
	
	        System.out.println("Escucho el puerto " + Puerto );
	
	        for ( int nCli = 0; nCli < 3; nCli++) {
	        	
	        	Socket sCliente = skServidor.accept(); 
	
			    //System.out.println("Sirvo al cliente " + nCli);
			    
			    InputStream aux = sCliente.getInputStream();
			
			    DataInputStream flujo_salida = new DataInputStream(aux);
			    
			    System.out.println(flujo_salida.readUTF() + " -> " + sCliente.getInetAddress());
			    
//			    OutputStream aux = sCliente.getOutputStream();
//			
//			    DataOutputStream flujo_salida= new DataOutputStream( aux );
			
//			    flujo_salida.writeUTF( "Hola cliente " + nCli );
			
			    sCliente.close();
	
	        }
	
	        skServidor.close();
	        System.out.println("Ya se han atendido los 3 clientes");
	
	    } catch( Exception e ) {
	
	        System.out.println( e.getMessage() );
	
	    }
    }
}
