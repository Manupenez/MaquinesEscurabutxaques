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
//classe nova
public class SeleccióMàquinaRecaptació extends JFrame {

	private JPanel contentPane;
	private JList listIdMaquines;
	private DefaultListModel llistaMaquines;
	private ControladorMaquina controladorMaquina;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccióMàquinaRecaptació frame = new SeleccióMàquinaRecaptació();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SeleccióMàquinaRecaptació() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listIdMaquines = new JList();
		listIdMaquines.setBounds(27, 51, 126, 163);
		contentPane.add(listIdMaquines);
		
		omplirPantalla();
		
		JLabel lblSeleccionaMaquina = new JLabel("Selecciona Maquina");
		lblSeleccionaMaquina.setBounds(27, 13, 126, 25);
		contentPane.add(lblSeleccionaMaquina);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new InformacioRecaptacioMaquina(Integer.parseInt(listIdMaquines.getSelectedValue().toString()));
			}
		});
		btnContinuar.setBounds(249, 87, 105, 35);
		contentPane.add(btnContinuar);
		
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
		LinkedList<Integer> maquines = controladorMaquina.obtenirMaquines();
		try{
			for(Integer maquina: maquines){
				llistaMaquines.addElement(maquina);
			}
			listIdMaquines = new JList(llistaMaquines);
			listIdMaquines.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listIdMaquines.setBounds(10, 47, 133, 193);
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
