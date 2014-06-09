package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.JList;

import Aplicacio.ControladorReparador;

public class ModificacioReparacio extends JFrame {

	private JPanel contentPane;
	private ControladorReparador controladorReparador;
	private DefaultListModel llistaReparacions;
	private JList listReparacions;
	/**
	 * Create the frame.
	 */
	public ModificacioReparacio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReparacionsPendents = new JLabel("Reparacions pendents");
		lblReparacionsPendents.setBounds(12, 13, 131, 21);
		contentPane.add(lblReparacionsPendents);
		
		omplirPantalla();
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tornarEnrere();
			}
		});
		btnCancelar.setBounds(335, 215, 97, 25);
		contentPane.add(btnCancelar);
		
		JButton btnNewButton = new JButton("Continuar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				ModificarPecesReparacio nova = new ModificarPecesReparacio(Integer.parseInt(String.valueOf(listReparacions.getSelectedValue())));
				nova.setVisible(true);	
				dispose();
			}
		});
		btnNewButton.setBounds(222, 86, 142, 47);
		contentPane.add(btnNewButton);
	}
	public void omplirPantalla(){
		LinkedList<Integer> reparacions;
		try{
			reparacions = controladorReparador.obtenirMaquinesPerReparar();
			if(!reparacions.isEmpty()){
				llistaReparacions = new DefaultListModel();
				for(Integer reparacio: reparacions){
					llistaReparacions.addElement(reparacio);
				}
				listReparacions = new JList(llistaReparacions);
				listReparacions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				listReparacions.setBounds(10, 47, 133, 193);
				contentPane.add(listReparacions);
				
				}
			else{ tirarError("No hi han reparacions per fer");}
		}catch(Exception e){
			tirarError(e.getMessage());
		}
	}
	public void tornarEnrere(){
		PantallaPrincipal principal = new PantallaPrincipal();
		principal.setVisible(true);
		this.dispose();
	}
	public void tirarError(String missatge) {
		JOptionPane.showMessageDialog(new JFrame(), missatge, "Error",
				JOptionPane.ERROR_MESSAGE);
	}	
}
