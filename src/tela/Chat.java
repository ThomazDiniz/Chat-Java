package tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Chat extends JFrame {

	private JPanel contentPane;
	private JTextField tfMensagem;
	private JButton btSair;
	private JButton btEnviar;
	private JTextArea taChat;
	
	public String getMensagem() {
		return tfMensagem.getText();
	}

	public void setMensagem(String mensagem) {
		this.tfMensagem.setText(mensagem);
	}

	public void setChat(String texto) {
		taChat.setText(texto);
	}

	public void appendChat(String mensagem) {
		this.taChat.append(mensagem);;
	}

	public Chat() {
		setTitle("NSM");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Chat.class.getResource("/imagens/icone.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btEnviar = new JButton("Enviar");
		btEnviar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btEnviar.setBounds(322, 373, 110, 40);
		contentPane.add(btEnviar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 422, 355);
		contentPane.add(scrollPane);
		
		taChat = new JTextArea();
		taChat.setEditable(false);
		scrollPane.setViewportView(taChat);
		
		tfMensagem = new JTextField();
		tfMensagem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfMensagem.setBounds(10, 373, 302, 74);
		contentPane.add(tfMensagem);
		tfMensagem.setColumns(10);
		
		btSair = new JButton("Sair");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btSair.setFont(new Font("Tahoma", Font.BOLD, 12));
		btSair.setBounds(322, 424, 110, 23);
		contentPane.add(btSair);
	}
	
	public void addMudaTelaSair(ActionListener a){
		btSair.addActionListener(a);
	}
	
	public void addEviarLister(ActionListener e){
		btEnviar.addActionListener(e);
	}
	
	public void addEnterListener(ActionListener e){
		tfMensagem.addActionListener(e);		
	}
	
}