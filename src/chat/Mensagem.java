package chat;

public class Mensagem {

	
	private Pessoa pessoa;
	private String texto;
	
	public String toString(){
		String resultado = pessoa.getNome() + ": "+texto;
		return resultado;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getMensagem() {
		return texto;
	}

	public void setMensagem(String mensagem) {
		texto = mensagem;
	}
	
	public Mensagem(Pessoa pessoa, String mensagem){
		this.pessoa = pessoa;
		this.texto = mensagem;
	}
}
