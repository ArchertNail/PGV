package udp_emisor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class EmisorUDP {

	public static void main(String[] args) {
		// Comprueba los argumento
		
		String[] argumento ={"10.2.2.2","MEH"};
		if (argumento.length != 2) {

			System.err.println("Uso: java EmisorUDP maquina mensaje");

		}

		else
			try {

				// Crea el socket

				DatagramSocket sSocket = new DatagramSocket();

				// Construye la dirección del socket del receptor

				InetAddress maquina = InetAddress.getByName(argumento[0]);

				int Puerto = 1500;

				// Crea el mensaje

				byte[] cadena = argumento[1].getBytes();

				DatagramPacket mensaje = new DatagramPacket(cadena, argumento[1].length(), maquina, Puerto);

				// Envía el mensaje

				System.out.println("Ready Steady?");
				System.in.read();
				sSocket.send(mensaje);
				System.out.println("ENVIADO!");
				// Cierra el socket

				sSocket.close();

			} catch (UnknownHostException e) {

				System.err.println("Desconocido: " + e.getMessage());

			} catch (SocketException e) {

				System.err.println("Socket: " + e.getMessage());

			} catch (IOException e) {

				System.err.println("E/S: " + e.getMessage());

			}

	}

}
