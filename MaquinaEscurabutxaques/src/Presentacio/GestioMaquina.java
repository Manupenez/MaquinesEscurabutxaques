package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GestioMaquina extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;

	/**
	 * Create the frame.
	 */
	public GestioMaquina() {
		setTitle("Gestió Màquina");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnEnrere = new JButton("Enrere");
		btnEnrere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tornarEnrere();
			}
		});
		btnEnrere.setBounds(335, 228, 89, 23);
		contentPane.add(btnEnrere);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {
				"Nova Màquina", "Revisar Màquina", "Remuntar Màquina" }));
		comboBox.setSelectedIndex(0);
		comboBox.setToolTipText("");
		comboBox.setMaximumRowCount(3);
		comboBox.setBounds(97, 103, 238, 20);
		contentPane.add(comboBox);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canviarPantalla();
			}
		});
		btnOk.setBounds(172, 134, 89, 23);
		contentPane.add(btnOk);

		JLabel lblGestiContracte = new JLabel("Gestió Màquina");
		lblGestiContracte.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGestiContracte.setBounds(147, 32, 175, 14);
		contentPane.add(lblGestiContracte);
	}
	
	public void canviarPantalla(){
		if (comboBox.getSelectedIndex() == 0) {
			NovaMaquina nova = new NovaMaquina();
			nova.setVisible(true);	
			this.dispose();
		} else {
			if (comboBox.getSelectedIndex() == 1) {
				RevisarMaquina revisar = new RevisarMaquina();
				revisar.setVisible(true);
				this.dispose();
			} else {
				RemuntarMaquina remuntar= new RemuntarMaquina();
				remuntar.setVisible(true);
				this.dispose();
			}
		}
	}
	
	public void tornarEnrere(){
		PantallaPrincipal principal = new PantallaPrincipal();
		principal.setVisible(true);
		this.dispose();
	}

}
