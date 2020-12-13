package Conexao;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import tela.Chat;
import tela.Controle;
import tela.Tela;

public class Host implements Runnable,RecebeMensagem{
	private int porta;
	private ServerSocket serverSocket;
	private Socket socket;
	private DataInputStream dIn;
	private CaixaDeMensagens r;
	private Thread t;
	private boolean podeReceberMensagens = false;
	private boolean recebeu = false;
	private String nome;
	private String msg;
	private Chat chat;
	private Tela tela;
	
	public void setTela(Tela tela){
		this.tela=tela;
	}
	 
	public void setChat(Chat chat){
		this.chat= chat;
	}
	
	public void alteraChat(){
		chat.appendChat(nome+": "+msg +"\n");
		chat.setTitle("NSM - Conversa com: "+this.nome);
	}
	public void alteraChat(int i){
		chat.appendChat("\n------------------\nFim da conversa\n------------------ \n");
		chat.setTitle("NSM - Erro");
	}
	public Host(int porta){
		this.porta = porta;
	}


	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}

	public Socket getSocket() {
		return socket;
	}
	
	public void recebe(){
		while(true){
			try {
				byte titulo = dIn.readByte();
				switch(titulo){
					case 0:
						nome = dIn.readUTF();
						msg = dIn.readUTF();
						System.out.println(nome + ": " + msg);
						alteraChat();
						break;
					case 1:
						new Thread(this).start();
						alteraChat(54);
				}
			} catch (IOException e) {
				fechaSocket();
				System.out.println("IO Exception no código de receber mensagens do Host. Finalizando a thread...");				
				return;
			}
		}
	}

	
	public void escrevePacoteMensagem(String nome, String msg){
		try {
			DataOutputStream dOut= new DataOutputStream(socket.getOutputStream());
			dOut.writeByte(0);
			dOut.writeUTF(nome);
			dOut.writeUTF(msg);
			dOut.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void desconecta(){
		try {
			DataOutputStream dOut= new DataOutputStream(socket.getOutputStream());
			dOut.writeByte(1);
			dOut.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			System.out.println("Ninguem se conectou");
		}
	}
	
	
	
	
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}


	public void fechaSocket() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try{
			socket.close();	
		} catch (Exception e){
			System.out.println("Não foi possível fechar o socket, provavelmente ele não existe");
		}

		try{
			t.interrupt();
		} catch (Exception e){
			System.out.println("Não foi possível fechar o socket, provavelmente ele não existe");
		}
		
		
	}


	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(porta);
			socket = serverSocket.accept();
			serverSocket.close();
			dIn = new DataInputStream(socket.getInputStream());
			 r = new CaixaDeMensagens(this);
			 t = new Thread(r);
			 t.start();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void mudaTela(){
		tela.setVisible(Controle.visibilidade);
		chat.setVisible(!Controle.visibilidade);
		Controle.visibilidade = !Controle.visibilidade;
	}

}
