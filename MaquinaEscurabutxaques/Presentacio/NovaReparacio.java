package Presentacio;

import java.awt.BorderLayout;
import java.awt.Component;
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

import Aplicacio.ControladorContracte;
import Aplicacio.ControladorMaquina;
import Aplicacio.ControladorReparador;

import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

public class NovaReparacio extends JFrame {

	private JPanel contentPane;
	private JList listClients;
	private JList listMaquines;
	private ControladorReparador controladorReparador;
	private ControladorMaquina controladorMaquina;
	private ControladorContracte controladorContracte;
	private DefaultListModel modelClients;
	private DefaultListModel modelMaquines;
	private JButton btnClient;
	private JButton btnMaquina;

	/**
	 * Create the frame.
	 */
	public NovaReparacio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		try {
			controladorReparador = new ControladorReparador();
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
		contentPane.setLayout(null);
		btnCancellar.setBounds(230, 261, 124, 23);
		contentPane.add(btnCancellar);

		omplirPantalla();
	}

	private void omplirPantalla() {
		LinkedList<Integer> comercos;

		try {
			comercos = controladorContracte.comercAmbContracte();
			modelClients = new DefaultListModel();
			for (Integer client : comercos) {
				modelClients.addElement(client);
			}
			listClients = new JList(modelClients);
			listClients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listClients.setBounds(25, 54, 146, 165);
			contentPane.add(listClients);
		} catch (Exception e) {
			tirarError("No hi ha cap comerç amb contracte");
		}

		JLabel lblEscollirClient = new JLabel("Escollir Client:");
		lblEscollirClient.setBounds(25, 26, 91, 16);
		contentPane.add(lblEscollirClient);

		JLabel lblEscollirMquina = new JLabel("Escollir Màquina: ");
		lblEscollirMquina.setBounds(208, 26, 124, 16);
		contentPane.add(lblEscollirMquina);

		btnMaquina = new JButton("Acceptar");
		btnMaquina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listMaquines.isSelectionEmpty()) {
					tirarError("Has de seleccionar les màquines que s'han de reparar");
				} else {
					try {
						for (Object maquina : listMaquines
								.getSelectedValuesList()) {
							String retorn = controladorReparador.inserirReparacio(
									Integer.parseInt(String.valueOf(maquina)),
									Integer.parseInt(String.valueOf(listClients
											.getSelectedValue())));
							JOptionPane
									.showMessageDialog(
											new JFrame(),
											retorn
													+ " és l'encarregat de dur a terme la reparació",
											"Reparació Creada",
											JOptionPane.PLAIN_MESSAGE);
						}

						tornarEnrere();
					} catch (Exception e1) {
						tirarError(e1.getMessage());
					}
				}
			}
		});
		btnMaquina.setEnabled(false);
		btnMaquina.setBounds(230, 231, 124, 29);
		contentPane.add(btnMaquina);

		btnClient = new JButton("Acceptar");
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listClients.isSelectionEmpty()) {
					tirarError("Has de seleccionar un Client");
				} else {
					btnClient.setEnabled(false);
					btnMaquina.setEnabled(true);
					try {
						LinkedList<Integer> maquines = controladorReparador.obtenirMaquinesComerc(Integer
								.parseInt(String.valueOf(listClients
										.getSelectedValue())));
						modelMaquines = new DefaultListModel();
						for (Integer maquina : maquines) {
							modelMaquines.addElement(Integer.parseInt(String
									.valueOf(maquina)));
						}
						listMaquines = new JList(modelMaquines);
						listMaquines.setBounds(208, 54, 146, 165);
						contentPane.add(listMaquines);
						contentPane.updateUI();
					} catch (Exception e1) {
						tirarError(e1.getMessage());
					}
				}
			}
		});
		btnClient.setBounds(54, 231, 117, 29);
		contentPane.add(btnClient);

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
