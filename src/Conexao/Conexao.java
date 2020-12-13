package Conexao;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Conexao {
	
	
	public static void main(String[] args) {
			Host h = new Host(900);
			new Thread(h).start();
			Cliente c = new Cliente("127.0.0.1",900);
			c.escrevePacoteMensagem("Host", "Opa");
			c.escrevePacoteMensagem("Host", "Tudo bom?");
			c.escrevePacoteMensagem("Host", "Quer tc?");

			h.escrevePacoteMensagem("Cliente", "E ai");

			long i = System.currentTimeMillis();
			while(System.currentTimeMillis()-i < 100){}
			c.fechaSocket();
			h.fechaSocket();
			
		}
	
}
