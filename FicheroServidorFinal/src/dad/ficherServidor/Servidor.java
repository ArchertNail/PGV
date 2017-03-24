package dad.ficherServidor;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;

public class Servidor extends Thread{
	private static final int PUERTO = 8009;
	private static Socket cliente;
	static ArrayList<Socket> listaClientes;
	static boolean listo = false;
	
	private static byte[] ficheroBytes;
	private static byte[] listPaquetesByte;
	static DatagramPacket mensaje;

	private static int cantidadPaquetes = 4;
	private static int tamanioPaqueteByte;

	private static int maxClientes=3;
	private static int id;
	private static File archivo;
	
	
	private Servidor(Socket cliente, int i) throws IOException {
		this.cliente = cliente;
		id = i;

	}

	public void run() {
		
		//EL tamaño en bytes del archivo lo divides en paquetes -> 4 paquetes  = tamaño de cada paquete
		tamanioPaqueteByte  = ficheroBytes.length/cantidadPaquetes;
		
		//declaras un tamaño fijo que va a tener el array de bytes, este tamaño sera el de cada paquete de nuestro archivo
		listPaquetesByte = new byte[tamanioPaqueteByte];

		// Creo los flujos de entrada salida
		try {
			
			DataOutputStream flujo_salida = new DataOutputStream(cliente.getOutputStream());

			//Enviamos el numero de paquetes a enviar
			flujo_salida.writeInt(cantidadPaquetes);
			flujo_salida.writeInt(id);
			//Enviamos el tamanio de los paquetes
			flujo_salida.writeInt(tamanioPaqueteByte);
			flujo_salida.writeUTF(archivo.getName());
			
			int puerto = 1500;	
			
			//Este sleep nos permite aguantar el ultimo paquete u que llegen los datos
			try {
				sleep(500);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			//CONEXION UDP
			MulticastSocket multicastSocket = new MulticastSocket(puerto);

			InetAddress ipMulticast = InetAddress.getByName("225.0.0.0");
			multicastSocket.joinGroup(ipMulticast);

			int posFicheroByte = 0;
			for (int i = 0; i < cantidadPaquetes; i++) {
				
				for (int j = 0; j < listPaquetesByte.length; j++) {
					listPaquetesByte[j] = ficheroBytes[posFicheroByte];
					posFicheroByte++;
				}
				
				mensaje = new DatagramPacket(listPaquetesByte, listPaquetesByte.length, ipMulticast, puerto);
				multicastSocket.send(mensaje);
				
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] arg) throws IOException {
		
		archivo = new File("llave.png");
		
		listaClientes = new ArrayList<>();
		
		//cargamos en el array de bytes el archivo 
		ficheroBytes = Files.readAllBytes(archivo.toPath());
		
		//INICIAMOS CONEXION TCP
		ServerSocket socketServidor = new ServerSocket(PUERTO);
		System.out.println("Escuachando puerto --> " + PUERTO);

		
		// ESPERAMOS A QUE SE CONECTEN TODOS LOS CLIENTES
		for (int i=0; i<3;i++) {
			
			//Se queda escuchando hasta que inicie un cliente
			Socket cliente = socketServidor.accept();
			listaClientes.add(cliente);
			System.out.println("---> Cliente " + i + ": Conectado!! <---- ");
			
		}
		
		//EL tamaño en bytes del archivo lo divides en paquetes -> 4 paquetes  = tamaño de cada paquete
		tamanioPaqueteByte  = ficheroBytes.length/cantidadPaquetes;
		
		//declaras un tamaño fijo que va a tener el array de bytes, este tamaño sera el de cada paquete de nuestro archivo
		listPaquetesByte = new byte[tamanioPaqueteByte];

				for(int i=0; i<listaClientes.size(); i++){
				
					
					DataOutputStream flujo_salida = new DataOutputStream(listaClientes.get(i).getOutputStream());

					//Enviamos el numero de paquetes a enviar
					flujo_salida.writeInt(cantidadPaquetes);
					flujo_salida.writeInt(i);
					//Enviamos el tamanio de los paquetes
					flujo_salida.writeInt(tamanioPaqueteByte);
					flujo_salida.writeUTF(archivo.getName());
					
				}
				
			
				
					int puerto = 1500;	
					
					//Este sleep nos permite aguantar el ultimo paquete u que llegen los datos
					
					
					//CONEXION UDP
					MulticastSocket multicastSocket = new MulticastSocket(puerto);

					InetAddress ipMulticast = InetAddress.getByName("225.0.0.0");
					//multicastSocket.joinGroup(ipMulticast);

					int posFicheroByte = 0;
					for (int i = 0; i < cantidadPaquetes; i++) {
						
						for (int j = 0; j < listPaquetesByte.length; j++) {
							listPaquetesByte[j] = ficheroBytes[posFicheroByte];
							posFicheroByte++;
						}
						
						try {
							sleep(500);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						
						mensaje = new DatagramPacket(listPaquetesByte, listPaquetesByte.length, ipMulticast, puerto);
						multicastSocket.send(mensaje);
						
						
					}

		

		System.out.println("Se han conectad = " + listaClientes.size() + " Clientes");

	}
}
