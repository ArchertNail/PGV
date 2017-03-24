package pgv.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {
	static final String HOST = "localhost";

    static final int Puerto=2000;

    public Cliente( ) {

    	try{

              Socket sCliente = new Socket( HOST , Puerto );

//            InputStream aux = sCliente.getInputStream();

// 		      DataInputStream flujo_entrada = new DataInputStream( aux );
              
              OutputStream aux = sCliente.getOutputStream();
     
              DataOutputStream flujo_salida = new DataOutputStream(aux);

              flujo_salida.writeUTF("killian");
              
              //System.out.println( flujo_entrada.readUTF() );

              sCliente.close();

        } catch( Exception e ) {

        	System.out.println( e.getMessage() );

        }

    }
}
