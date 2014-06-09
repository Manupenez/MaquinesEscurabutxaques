package Presentacio;

import GestioReparacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaPrincipal extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
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
	public PantallaPrincipal() {
		setTitle("M�quina Escurabutxaques");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canviarPantalla();
			}
		});
		btnOk.setBounds(166, 142, 89, 23);
		contentPane.add(btnOk);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Gesti� m�quina", "Gesti� reparacions", "Gesti� contrcate",
				"Gesti� recaptacions" }));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(106, 78, 218, 20);
		contentPane.add(comboBox);
	}

	public void canviarPantalla() {
		if (comboBox.getSelectedIndex() == 0) {
			GestioMaquina maquina = new GestioMaquina();
			maquina.setVisible(true);
			this.dispose();
		} else {
			if (comboBox.getSelectedIndex() == 2) {
				GestioContracte contracte = new GestioContracte();
				contracte.setVisible(true);
				this.dispose();
			} else {
				if(comboBox.getSelectedIndex() == 1){
					GestioReparacio reparacio = new GestioReparacio();
					reparacio.setVisible(true);
					this.dispose();
			}
		}

	}
}
