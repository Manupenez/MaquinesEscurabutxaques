package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import Aplicacio.ControladorMaquina;
import Aplicacio.ControladorReparador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class ModificarPecesReparacio extends JFrame {

	private JPanel contentPane;
	private ControladorReparador controladorReparador;
	private ControladorMaquina controladorMaquina;
	private DefaultListModel llistaPlaques;
	private DefaultListModel llistaCarcasses;
	private JList listPlaques;
	private JList listCarcasses;
	private int idRep;
	/**
	 * Create the frame.
	 */
	public ModificarPecesReparacio(int idReparacio) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		this.idRep=idReparacio;
		
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
				
				try {
					controladorReparador.canviarEstatFinalitzat(idRep,Integer.parseInt(String.valueOf(listCarcasses.getSelectedValue())) , Integer.parseInt(String.valueOf(listPlaques.getSelectedValue())));
				}catch (Exception e) {
					tirarError(e.getMessage());
				}
				
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
	public void omplirPantalla(){
		LinkedList<Integer> plaques;
		LinkedList<Integer> carcasses;
		try{
			plaques = controladorMaquina.obtenirLListaPlaques();
			carcasses = controladorMaquina.obtenirLListaCarcasses();
			llistaPlaques = new DefaultListModel();
			llistaCarcasses =  new DefaultListModel();
			for(Integer placa: plaques){
				llistaPlaques.addElement(placa);
			}
			for(Integer carcasssa: carcasses){
				llistaPlaques.addElement(carcasssa);
			}
			listPlaques = new JList(llistaPlaques);
			listPlaques.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listPlaques.setBounds(10, 47, 133, 193);
			contentPane.add(listPlaques);	
			
			listCarcasses = new JList(llistaCarcasses);
			listCarcasses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listCarcasses.setBounds(10, 47, 133, 193);
			contentPane.add(listCarcasses);	
			
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
