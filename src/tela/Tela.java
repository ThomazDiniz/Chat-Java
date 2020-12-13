package tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Tela extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfIP;
	private JTextField tfPorta;
	private JButton btConectar;
	private JButton btCriar;
	
	public String getNome() {
		return tfNome.getText();
	}

	public String getIP() {
		return tfIP.getText();
	}

	public int getPorta() {
		int s=0;
		while(s<=0){
			try{
				s = Integer.parseInt(tfPorta.getText());	
			}catch(Exception e){
				tfPorta.setText(""+5000);;
			}	
		}
		 return s;
	}

	public Tela() {
		setTitle("NSM");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tela.class.getResource("/imagens/icone.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 229);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfNome = new JTextField();
		tfNome.setText("Jo\u00E3o");
		tfNome.setBounds(157, 11, 250, 28);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome...........:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNome.setBounds(26, 9, 121, 28);
		contentPane.add(lblNome);
		
		JLabel lblIp = new JLabel("IP.................:");
		lblIp.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIp.setBounds(26, 50, 121, 28);
		contentPane.add(lblIp);
		
		tfIP = new JTextField();
		tfIP.setText("127.0.0.1");
		tfIP.setColumns(10);
		tfIP.setBounds(157, 52, 250, 28);
		contentPane.add(tfIP);
		
		btConectar = new JButton("Conectar");
		btConectar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btConectar.setBounds(259, 146, 148, 29);
		contentPane.add(btConectar);
		
		JLabel lblPorta = new JLabel("Porta............:");
		lblPorta.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPorta.setBounds(26, 93, 121, 28);
		contentPane.add(lblPorta);
		
		tfPorta = new JTextField();
		tfPorta.setText("5000");
		tfPorta.setColumns(10);
		tfPorta.setBounds(157, 95, 250, 28);
		contentPane.add(tfPorta);
		
		btCriar = new JButton("Criar");
		btCriar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btCriar.setBounds(26, 146, 148, 29);
		contentPane.add(btCriar);
		
		setVisible(true);
	}
	
	public void invisivel(boolean v){
		setVisible(v);
	}
	
	public void addMudaTelaConectar(ActionListener a){
		btConectar.addActionListener(a);		
	}
	public void addMudaTelaCria(ActionListener a){
		btCriar.addActionListener(a);
	}
}
