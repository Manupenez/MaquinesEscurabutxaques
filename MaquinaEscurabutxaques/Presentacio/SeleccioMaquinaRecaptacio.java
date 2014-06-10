package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import Aplicacio.ControladorMaquina;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
public class SeleccioMaquinaRecaptacio extends JFrame {

	private JPanel contentPane;
	private JList listIdMaquines;
	private DefaultListModel llistaMaquines;
	private ControladorMaquina controladorMaquina;


	/**
	 * Create the frame.
	 */
	public SeleccioMaquinaRecaptacio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		omplirPantalla();
		
		JLabel lblSeleccionaMaquina = new JLabel("Selecciona Maquina");
		lblSeleccionaMaquina.setBounds(27, 13, 126, 25);
		contentPane.add(lblSeleccionaMaquina);
		
		JButton btnAcceptar = new JButton("Veure Recaptacions");
		btnAcceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new InformacioRecaptacioMaquina(Integer.parseInt(listIdMaquines.getSelectedValue().toString()));
			}
		});
		btnAcceptar.setBounds(246, 47, 152, 35);
		contentPane.add(btnAcceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tornarEnrere();
			}
		});
		btnCancelar.setBounds(323, 215, 97, 25);
		contentPane.add(btnCancelar);
		
		JButton btnNewButton = new JButton("Inserir Recaptaci\u00F3");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new NovaRecaptacio();
			}
		});
		btnNewButton.setBounds(246, 121, 152, 35);
		contentPane.add(btnNewButton);
	}
	public void omplirPantalla(){
		try{
			LinkedList<Integer> maquines = controladorMaquina.obtenirMaquines();
			for(Integer maquina: maquines){
				llistaMaquines.addElement(maquina);
			}
			listIdMaquines = new JList(llistaMaquines);
			listIdMaquines.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listIdMaquines.setBounds(20, 47, 133, 193);
			contentPane.add(listIdMaquines);	
			
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
