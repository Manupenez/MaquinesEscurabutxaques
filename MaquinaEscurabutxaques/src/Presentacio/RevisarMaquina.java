package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import Aplicacio.ControladorMaquina;

public class RevisarMaquina extends JFrame {

	private JPanel contentPane;
	private ControladorMaquina controladorMaquina;
	private DefaultListModel modelMaquines;
	private JList listMaquines;

	/**
	 * Create the frame.
	 */
	public RevisarMaquina() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			controladorMaquina = new ControladorMaquina();
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
		btnCancellar.setBounds(320, 249, 124, 23);
		contentPane.add(btnCancellar);
		
		omplirPantalla();
	}

	public void omplirPantalla(){
		LinkedList<Integer> maquines;
	try{
		maquines = controladorMaquina.obtenirMaquinesRevisar();
		modelMaquines = new DefaultListModel();
		for(Integer maquina : maquines){
			modelMaquines.addElement(maquina);
		}
	}catch(Exception e){
		tirarError(e.getMessage());
	}
		listMaquines = new JList(modelMaquines);
		listMaquines.setBounds(19, 47, 117, 161);
		contentPane.add(listMaquines);
		listMaquines.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.updateUI();
	}

	public void tornarEnrere() {
		PantallaPrincipal principal = new PantallaPrincipal();
		principal.setVisible(true);
		this.dispose();
	}

	public void tirarError(String missatge) {
		JOptionPane.showMessageDialog(new JFrame(), missatge, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

}
