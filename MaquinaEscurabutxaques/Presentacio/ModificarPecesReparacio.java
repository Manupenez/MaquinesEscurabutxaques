package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModificarPecesReparacio extends JFrame {

	private JPanel contentPane;
	private JList listPlaques;
	private JList listCarcasses;
	/**
	 * Create the frame.
	 */
	public ModificarPecesReparacio(JList listReparacions) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Plaques Lliures");
		lblNewLabel.setBounds(12, 13, 104, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Carcasses Lliures");
		lblNewLabel_1.setBounds(128, 13, 104, 23);
		contentPane.add(lblNewLabel_1);
		
		listPlaques = new JList();
		listPlaques.setBounds(0, 58, 116, 182);
		contentPane.add(listPlaques);
		
		listCarcasses = new JList();
		listCarcasses.setBounds(128, 58, 116, 182);
		contentPane.add(listCarcasses);
		
		JButton btnFinalitzarReparaci = new JButton("Finalitzar Reparaci\u00F3");
		btnFinalitzarReparaci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnFinalitzarReparaci.setBounds(256, 55, 164, 51);
		contentPane.add(btnFinalitzarReparaci);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tornarEnrere();
			}
		});
		btnCancelar.setBounds(323, 215, 97, 25);
		contentPane.add(btnCancelar);
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
