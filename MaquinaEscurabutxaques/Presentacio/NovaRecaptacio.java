package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import Aplicacio.ControladorContracte;
import Aplicacio.ControladorRecaptacio;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NovaRecaptacio extends JFrame {

	private JPanel contentPane;
	private JTextField dinersMaquina;
	private ControladorRecaptacio controladorRecaptacio;
	private ControladorContracte controladorContracte;
	private int idMaquina;

	/**
	 * Create the frame.
	 */
	public NovaRecaptacio(int idmaquina) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		idMaquina = idmaquina;
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancelar.setBounds(323, 215, 97, 25);
		contentPane.add(btnCancelar);
		
		dinersMaquina = new JTextField();
		dinersMaquina.setText("");
		dinersMaquina.setBounds(139, 119, 158, 22);
		contentPane.add(dinersMaquina);
		dinersMaquina.setColumns(10);
		
		JButton btnAcceptar = new JButton("Acceptar");
		btnAcceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				if(!dinersMaquina.getText().equals("")){
					int idcontracte = controladorContracte.recuperarIdContracteMaquina(idMaquina);
					controladorRecaptacio.crearInformeRecaptacio(idMaquina, idcontracte, Integer.parseInt(dinersMaquina.getText()));
					
				}else{
					tirarError("Introdueixi un valor");
				}
				}catch(Exception e){
					tirarError(e.getMessage());
				}
			}
		});
		btnAcceptar.setBounds(175, 155, 97, 25);
		contentPane.add(btnAcceptar);
		
		JLabel lblIntrodueixiDinersRecaptats = new JLabel("Introdueixi diners recaptats en euros");
		lblIntrodueixiDinersRecaptats.setBounds(139, 55, 158, 51);
		contentPane.add(lblIntrodueixiDinersRecaptats);
	}
	
	public void tornarEnrere(){
		PantallaPrincipal principal = new PantallaPrincipal();
		principal.setVisible(true);
		dispose();
	}
	public void tirarError(String missatge) {
		JOptionPane.showMessageDialog(new JFrame(), missatge, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}
