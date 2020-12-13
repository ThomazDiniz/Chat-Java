package tela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JFrame;

import Conexao.Cliente;
import Conexao.Host;

public class Controle{
	 Tela tela;
	 Chat chat;
	 Host host;
	 Cliente cliente;
	 boolean ehHost = false;
	 public static boolean visibilidade = false;
	 
	 public Controle() {
		// TODO Auto-generated constructor stub
		 tela = new Tela();
		 chat = new Chat();
		 
		 tela.addMudaTelaConectar(new MudaTelaListnerConectar());
		 tela.addMudaTelaCria(new MudaTelaListnerCriar());
		 chat.addMudaTelaSair(new MudaTelaListnerSair());
		 chat.addEviarLister(new enviarListener());
		 chat.addEnterListener(new enviarListener());
	}	
	 
	public void mudaTela(){
		tela.setVisible(visibilidade);
		chat.setVisible(!visibilidade);
		visibilidade = !visibilidade;
	}
	
	public class MudaTelaListnerConectar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			cliente = new Cliente(tela.getIP(), tela.getPorta()); 
			cliente.setChat(chat);
			cliente.setTela(tela);
			ehHost = false;
			mudaTela();
		}
	}
	public class MudaTelaListnerCriar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ehHost = true;
			host = new Host(tela.getPorta());
			host.setChat(chat);
			host.setTela(tela);
			new Thread(host).start();
			mudaTela();
		}
		
	}
	public class MudaTelaListnerSair implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (ehHost){
				host.desconecta();
				host.fechaSocket();
				
			}else{
				cliente.desconecta();
				cliente.fechaSocket();
			}
			 mudaTela();
		}
	}
	
	public class enviarListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if (ehHost){
				host.escrevePacoteMensagem(tela.getNome(), chat.getMensagem());
			} else{
				cliente.escrevePacoteMensagem(tela.getNome(), chat.getMensagem());
			}
			chat.appendChat(tela.getNome()+": "+chat.getMensagem() +"\n");
			chat.setMensagem("");
		}
	}
	
	public static void main(String[] args) {
		new Controle();
		
	}
}