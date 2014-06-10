package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;

import Aplicacio.ControladorContracte;
import Domini.Contracte;

public class ModificarContracte extends JFrame {

	private JPanel contentPane;
	private DefaultListModel modelComerc;
	private JList list;
	private ControladorContracte controladorContracte;

	/**
	 * Create the frame.
	 */
	public ModificarContracte() {
		setTitle("Modificar Contracte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		try {
			controladorContracte = new ControladorContracte();
		} catch (Exception e1) {
			tirarError(e1.getMessage());
		}

		JButton btnCancellar = new JButton("Cancel·lar");
		btnCancellar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tornarEnrere();
			}
		});
		contentPane.setLayout(null);
		btnCancellar.setBounds(304, 228, 120, 23);
		contentPane.add(btnCancellar);

		JLabel lblTriaElComer = new JLabel(
				"Tria el comerç que vols modificar el contrcate");
		lblTriaElComer.setBounds(54, 26, 326, 14);
		contentPane.add(lblTriaElComer);

		JButton btnAcceptar = new JButton("Acceptar");
		btnAcceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			passarPantallaSeguent();
			}
		});
		btnAcceptar.setBounds(304, 194, 120, 23);
		contentPane.add(btnAcceptar);
		
		omplenarPantalla();
		
		contentPane.updateUI();
	}

	public void omplenarPantalla() {
		LinkedList<Integer> comercos;
		try {
			
			comercos = controladorContracte.comercAmbContracte();
			if (!comercos.isEmpty()) {
				modelComerc = new DefaultListModel();
				for (Integer comerc : comercos) {
					modelComerc.addElement(comerc);
				}
				list = new JList(modelComerc);
				list.setBounds(92, 51, 164, 173);
				contentPane.add(list);
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			}
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
	}
	
	public void passarPantallaSeguent(){
		if(list.isSelectionEmpty()){
			tirarError("Has de seleccionar un comerç");
		}else{
			Contracte contracte;
			try {				
				contracte = controladorContracte.aconseguirContracte(Integer.parseInt(String.valueOf(list.getSelectedValue())));
				ModificarContracte2 modificar = new ModificarContracte2(contracte);
				modificar.setVisible(true);
				this.dispose();
			} catch (Exception e) {
				this.tirarError(e.getMessage());
			}
			
		}
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



