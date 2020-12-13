package Conexao;

public class CaixaDeMensagens implements Runnable{
	private RecebeMensagem recebe;
	
	public CaixaDeMensagens(RecebeMensagem r){
		this.recebe = r;
	}
	@Override
	public void run() {
		recebe.recebe();
	}

}
