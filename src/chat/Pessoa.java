package chat;


import java.util.ArrayList;

public class Pessoa {
	private String nome, ip; 
	private int porta;
	
	public void setPorta(int p){
		this.porta= p;
	}
	
	public Pessoa(String nome, String ip, int porta){
		this.nome = nome;
		this.ip = ip;
		this.porta = porta;
	}
	public Pessoa(String nome){
		this.nome=nome;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String falar(String mensagem /*, Conexao conexao */){
		return mensagem;
	}
}