package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import Aplicacio.ControladorRecaptacio;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JTextField;
//classe nova
public class InformacioRecaptacioMaquina extends JFrame {

	private JPanel contentPane;
	private ControladorRecaptacio controladorRecaptacio;
	private JList datesRecaptacio; 
	private DefaultListModel llistaDates;
	private JTextField textField;
	private JButton btnNewButton;
	private int idMaquina;

	/**
	 * Create the frame.
	 */
	public InformacioRecaptacioMaquina(int idmaquina) {
		
		this.idMaquina = idMaquina;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tornarEnrere();
			}
		});
		btnCancelar.setBounds(323, 215, 97, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(12, 13, 65, 25);
		contentPane.add(lblData);
		
		JLabel lblRecaptaci = new JLabel("Recaptaci\u00F3:");
		lblRecaptaci.setBounds(181, 49, 97, 34);
		contentPane.add(lblRecaptaci);
		
		datesRecaptacio = new JList();
		datesRecaptacio.setBounds(10, 35, 143, 205);
		contentPane.add(datesRecaptacio);
		
		textField = new JTextField();
		textField.setBounds(262, 55, 143, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!datesRecaptacio.isSelectionEmpty()){
					textField.setText("");
					try {
						textField.setText(Double.toString(controladorRecaptacio.recuperarRecaptacio((Date)(datesRecaptacio.getSelectedValue()),idMaquina)));
					} catch (Exception e) {
						tirarError(e.getMessage());
					}
				}
			}
		});
		btnNewButton.setBounds(262, 110, 120, 34);
		contentPane.add(btnNewButton);
		
		omplirPantalla();		
	}
	public void omplirPantalla(){
		try{
		LinkedList<Date> dates = controladorRecaptacio.getDatesRecaptacioMaquina(idMaquina);		
			for(Date data: dates){
				llistaDates.addElement(data);
			}
			datesRecaptacio = new JList(llistaDates);
			datesRecaptacio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			datesRecaptacio.setBounds(10, 47, 133, 193);
			contentPane.add(datesRecaptacio);	
			
		}catch(Exception e){
			tirarError(e.getMessage());
		}
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
