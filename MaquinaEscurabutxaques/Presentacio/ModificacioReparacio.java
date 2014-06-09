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
	private JList list;
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
				ModificarReparacioPeces nova = new ModificarReparacioPeces();
				nova.setVisible(true);	
				dispose();
				/*for (Object reparacio: list.getSelectedValuesList()){
					controladorReparador.canviarEstatFinalitzat(Integer.parseInt(String.valueOf(reparacio)), idCarcassa, idPlaca);
				}*/
			}
		});
		btnNewButton.setBounds(222, 86, 142, 47);
		contentPane.add(btnNewButton);
			
		omplirPantalla();
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
				list = new JList(llistaReparacions);
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				list.setBounds(10, 47, 133, 193);
				contentPane.add(list);
				
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
