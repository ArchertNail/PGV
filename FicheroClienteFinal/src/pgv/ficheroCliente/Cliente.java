package pgv.ficheroCliente;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class Cliente {
	private static final String HOST = "localhost";
    private static final int Puerto = 8009;
    int paquetes;
	private int tamanioPaquete;
	private FileOutputStream fileOutput;
	private BufferedOutputStream bufferOutput;
	private int id;
	
	
	public Cliente() throws IOException{
		Socket sCliente = new Socket(HOST, Puerto);
        
        DataInputStream entradaTCP = new DataInputStream(sCliente.getInputStream());
        
        //Realizamos la comunicaciones TCP entre servidor y cliente
        paquetes=entradaTCP.readInt();
        System.out.println("Has recibido: "+paquetes+ " paquetes");
        
        id = entradaTCP.readInt();
        System.out.println(id);
        tamanioPaquete=entradaTCP.readInt();
        System.out.println(tamanioPaquete);
        String file = entradaTCP.readUTF();
       
        System.out.println(file);
        File archivo = new File(id + " - "+ file);

        //CREAR CONEXION UDP
        MulticastSocket multicastSocket = new MulticastSocket(1500);
        multicastSocket.joinGroup(InetAddress.getByName("225.0.0.0"));
        
        fileOutput = new FileOutputStream(archivo);
        bufferOutput = new BufferedOutputStream(fileOutput);
        
        //Reywnar el archivo
        for (int i = 0; i < paquetes; i++) {
        	
        	byte[] paquete = new byte[tamanioPaquete];
            DatagramPacket datoRecibido = new DatagramPacket(paquete,paquete.length);

            multicastSocket.receive(datoRecibido);
            
            bufferOutput.write(paquete, 0, paquete.length);
			
            bufferOutput.flush();
           
			System.out.println("Paquete " + i + " Recibido!!");
		} 
        
        fileOutput.close();
        bufferOutput.close();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
        new Cliente();
    }
}
