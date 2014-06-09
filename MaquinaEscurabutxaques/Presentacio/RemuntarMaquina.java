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
import javax.swing.border.EmptyBorder;

import Aplicacio.ControladorMaquina;

import javax.swing.JLabel;

public class RemuntarMaquina extends JFrame {

	private JPanel contentPane;
	private JButton btnAcceptar;
	private ControladorMaquina controladorMaquina;
	private DefaultListModel modelMaquines;
	private JList listMaquines;

	/**
	 * Create the frame.
	 */
	public RemuntarMaquina() {
		try {
			controladorMaquina = new ControladorMaquina();
		} catch (Exception e1) {
			tirarError(e1.getMessage());
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCancellar = new JButton("Cancel·lar");
		btnCancellar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tornarEnrere();
			}
		});

		contentPane.setLayout(null);
		btnCancellar.setBounds(300, 228, 124, 23);
		contentPane.add(btnCancellar);

		btnAcceptar = new JButton("Acceptar");
		btnAcceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (listMaquines.isSelectionEmpty()) {
						tirarError("Has de seleccionar quina màquina vols Remuntar");
					} else {
						controladorMaquina.tornarMuntar(Integer.parseInt(String
								.valueOf(listMaquines.getSelectedValue())));
						String missatge = "Màquines remuntades: \n";
						for (Object maquina : listMaquines
								.getSelectedValuesList()) {
							missatge = missatge + String.valueOf(maquina)
									+ "\n";
						}
						JOptionPane.showMessageDialog(new JFrame(), missatge,
								"Màquina Remuntada", JOptionPane.PLAIN_MESSAGE);
						tornarEnrere();
					}
				} catch (Exception e) {
					tirarError(e.getMessage());
				}
			}
		});
		btnAcceptar.setBounds(300, 194, 124, 23);
		contentPane.add(btnAcceptar);

		JLabel lblMaquinesPerRemuntar = new JLabel("Maquines per Remuntar");
		lblMaquinesPerRemuntar.setBounds(27, 27, 141, 14);
		contentPane.add(lblMaquinesPerRemuntar);

		omplirPantalla();
	}

	public void omplirPantalla() {
		LinkedList<Integer> maquines;
		try {
			maquines = controladorMaquina.obtenirMaquinesReMuntar();
			if (maquines.isEmpty()) {
				tirarError("No hi ha màquines per a remuntar");
				btnAcceptar.setEnabled(false);
			}
			modelMaquines = new DefaultListModel();
			for (Integer maquina : maquines) {
				modelMaquines.addElement(maquina);
			}
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
		listMaquines = new JList(modelMaquines);
		listMaquines.setBounds(27, 59, 141, 191);
		contentPane.add(listMaquines);
		contentPane.updateUI();
	}

	public void tirarError(String missatge) {
		JOptionPane.showMessageDialog(new JFrame(), missatge, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	public void tornarEnrere() {
		PantallaPrincipal principal = new PantallaPrincipal();
		principal.setVisible(true);
		this.dispose();
	}
}
