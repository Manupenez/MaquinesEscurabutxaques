package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Aplicacio.ControladorContracte;
import Domini.Contracte;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class ModificarContracte2 extends JFrame {

	private JPanel contentPane;
	private ControladorContracte controladorContracte;
	private JTextField textPagament;
	private JTextField textPercentatge;
	private JList listMaquinesAfegir;
	private JList listMaquinesTreure; 
	private DefaultListModel modelMaquinesTreure;
	private DefaultListModel modelMaquinesAfegir;
	/**
	 * Create the frame.
	 */
	
	public ModificarContracte2(Contracte contracte) {
		setTitle("Modificar Contracte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCancellar = new JButton("Cancel·lar");
		btnCancellar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tornarEnrere();
			}
		});
		btnCancellar.setBounds(466, 292, 89, 23);
		contentPane.add(btnCancellar);

		JButton btnAcceptar = new JButton("Acceptar");
		btnAcceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				completarModificacio();
			}
		});
		btnAcceptar.setBounds(466, 258, 89, 23);
		contentPane.add(btnAcceptar);
		
		try {
			controladorContracte = new ControladorContracte();
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
		
		omplenarPantalla(contracte);

	}

	public void omplenarPantalla(Contracte contracte) {
		try{
		JTextPane txtpnSeleccionaLesMaquines = new JTextPane();
		txtpnSeleccionaLesMaquines.setEditable(false);
		txtpnSeleccionaLesMaquines
				.setText("Selecciona les maquines per afegir al contracte");
		txtpnSeleccionaLesMaquines.setBounds(20, 11, 127, 34);
		contentPane.add(txtpnSeleccionaLesMaquines);

		JTextPane txtpnSeleccionaLesMaquines_1 = new JTextPane();
		txtpnSeleccionaLesMaquines_1
				.setText("Selecciona les maquines que vulguis treure del contracte\r\n");
		txtpnSeleccionaLesMaquines_1.setEditable(false);
		txtpnSeleccionaLesMaquines_1.setBounds(180, 11, 155, 34);
		contentPane.add(txtpnSeleccionaLesMaquines_1);
		
		LinkedList<Integer> maquinesTreure = controladorContracte.maquinesDelContracte(contracte);
		modelMaquinesTreure = new DefaultListModel();
		for (Integer maquina : maquinesTreure) {
			modelMaquinesTreure.addElement(maquina);
		}
		
		LinkedList<Integer> maquinesAfegir = controladorContracte.obtenirMaquines("LLESTA");
		modelMaquinesAfegir = new DefaultListModel();
		for (Integer maquina : maquinesAfegir) {
			modelMaquinesAfegir.addElement(maquina);
		}
				
		listMaquinesAfegir = new JList(modelMaquinesAfegir);
		listMaquinesAfegir.setBounds(20, 56, 116, 195);
		contentPane.add(listMaquinesAfegir);
		
		listMaquinesTreure = new JList(modelMaquinesTreure);
		listMaquinesTreure.setBounds(180, 56, 127, 195);
		contentPane.add(listMaquinesTreure);
		
		JLabel lblInformaci = new JLabel("Informació");
		lblInformaci.setBounds(357, 31, 68, 14);
		contentPane.add(lblInformaci);
		
		JTextPane textInformacio = new JTextPane();
		textInformacio.setBounds(357, 56, 198, 56);
		contentPane.add(textInformacio);

		String tipus = contracte.getTipusComerc();
		if (tipus.equals("MAJORISTA")) {
			textPercentatge = new JTextField();
			textPercentatge.setBounds(357, 216, 140, 20);
			contentPane.add(textPercentatge);
			textPercentatge.setColumns(10);

			JLabel lblPercentatge = new JLabel("Percentatge");
			lblPercentatge.setBounds(357, 191, 106, 14);
			contentPane.add(lblPercentatge);
		} else {
			JLabel lblPagament = new JLabel("Pagament mensual");
			lblPagament.setBounds(357, 123, 106, 14);
			contentPane.add(lblPagament);

			textPagament = new JTextField();
			textPagament.setBounds(357, 148, 140, 20);
			contentPane.add(textPagament);
			textPagament.setColumns(10);
		}}catch(Exception e){
			tirarError(e.getMessage());
		}
	}

	public void completarModificacio() {

	}

	public void tornarEnrere() {
		GestioContracte gestio = new GestioContracte();
		gestio.setVisible(true);
		this.dispose();
	}

	public void tirarError(String missatge) {
		JOptionPane.showMessageDialog(new JFrame(), missatge, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}
