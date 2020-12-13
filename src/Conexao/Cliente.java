package Conexao;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import tela.Chat;
import tela.Controle;
import tela.Tela;

public class Cliente implements RecebeMensagem{
	private String ip;
	private int porta;
	private Socket socket;
	private CaixaDeMensagens r;
	private DataInputStream dIn;
	private Thread t;
	private boolean podeReceberMensagens = false;
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
	public void alteraChat(int i){
		chat.appendChat("\n------------------\nFim da conversa\n------------------ \n");
		chat.setTitle("NSM - Erro");
	}
	
	public void alteraChat(){
		chat.appendChat(nome+": "+msg+"\n");
		chat.setTitle("NSM - Conversa com: "+this.nome);
	}
	public Cliente(String ip, int porta){
		this.ip = ip;
		this.porta = porta;
		r = new CaixaDeMensagens(this);
		try {
			socket = new Socket(ip, porta);
			dIn = new DataInputStream(socket.getInputStream());
			System.out.println("Clientes estão sendo atendidos");
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		t = new Thread(r);
		t.start();
		podeReceberMensagens = true;
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
	
	
	public void recebe(){
		while(true){
			
			if (!podeReceberMensagens){
				System.out.println("Finalizando Conexão.");
				return;
			}
			
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
						alteraChat(54);
						break;
				}
			} catch (IOException e) {
				fechaSocket();
				
				System.out.println("IO Exception no código de receber mensagens do cliente. Finalizando a thread...");
				return;
			} catch(NullPointerException b){
				System.out.println("Não foi possivel conectar");
				new JOptionPane().showMessageDialog(null, "Não foi possivel conectar");
				mudaTela();
				return;
			}
		}
	}
	
	public Socket getSocket(){
		return this.socket;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}
	
	public void desconecta(){
		try {
			DataOutputStream dOut= new DataOutputStream(socket.getOutputStream());
			dOut.writeByte(1);
			dOut.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("ja fechou");
		} catch (Exception e){
			System.out.println("socket não instanciado");
		}
	}
	
	public void fechaSocket() {
		try {
			socket.close();
			t.interrupt();
			podeReceberMensagens = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mudaTela(){
		tela.setVisible(Controle.visibilidade);
		chat.setVisible(!Controle.visibilidade);
		Controle.visibilidade = !Controle.visibilidade;
	}
}
