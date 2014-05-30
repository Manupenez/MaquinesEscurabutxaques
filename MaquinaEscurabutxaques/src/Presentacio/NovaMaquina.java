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

import javax.swing.JLabel;

public class NovaMaquina extends JFrame {

	private JPanel contentPane;
	private ControladorMaquina controladorMaquina;
	private DefaultListModel modelTecnics;
	private DefaultListModel modelCarcassa;
	private DefaultListModel modelPlaca;
	private JList listTecnic;
	private JList listCarcassa;
	private JList listPlaca;
	private JButton btnAcceptar;

	/**
	 * Create the frame.
	 */
	public NovaMaquina() {
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

		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(57, 19, 61, 16);
		contentPane.add(lblPlaca);

		JLabel lblCarcassa = new JLabel("Carcassa");
		lblCarcassa.setBounds(182, 19, 61, 16);
		contentPane.add(lblCarcassa);

		JLabel lblTcnic = new JLabel("Tècnic");
		lblTcnic.setBounds(333, 19, 61, 16);
		contentPane.add(lblTcnic);

		btnAcceptar = new JButton("Acceptar");
		btnAcceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (listTecnic.isSelectionEmpty()
							|| listCarcassa.isSelectionEmpty()
							|| listPlaca.isSelectionEmpty()) {
						tirarError("Has de seleccionar un tècnic, una carcassa i una placa");
					} else {

						String str = ("Màquina Muntada amb ID: " + controladorMaquina
								.muntarMaquina(
										Integer.parseInt(String
												.valueOf(listTecnic
														.getSelectedValue())),
										Integer.parseInt(String
												.valueOf(listPlaca
														.getSelectedValue())),
										Integer.parseInt(String
												.valueOf(listCarcassa
														.getSelectedValue()))));
						JOptionPane.showMessageDialog(new JFrame(), str,
								"Màquina Creada", JOptionPane.PLAIN_MESSAGE);
					}
				} catch (Exception e1) {
					tirarError(e1.getMessage());
				}
				contentPane.updateUI();
				tornarEnrere();
			}
		});
		btnAcceptar.setBounds(320, 218, 124, 29);
		contentPane.add(btnAcceptar);
		btnAcceptar.setEnabled(false);

		omplirPantalla();
	}

	public void omplirPantalla() {
		try {
			LinkedList<Integer> tecnics;
			LinkedList<Integer> carcasses;
			LinkedList<Integer> plaques;
			carcasses = controladorMaquina.obtenirLListaCarcasses();
			plaques = controladorMaquina.obtenirLListaPlaques();
			tecnics = controladorMaquina.obtenirTecnics();

			if (carcasses.isEmpty() || plaques.isEmpty() || tecnics.isEmpty()) {
				if (carcasses.isEmpty()) {
					tirarError("No hi ha carcasses per escollir.");
				}
				if (plaques.isEmpty()) {
					tirarError("No hi ha plaques per escollir.");
				}
				if (tecnics.isEmpty()) {
					tirarError("No hi ha tècnics disponibles.");
				}
			} else {
				btnAcceptar.setEnabled(true);
				modelTecnics = new DefaultListModel();
				for (Integer tecnic : tecnics) {
					modelTecnics.addElement(tecnic);
				}
				modelCarcassa = new DefaultListModel();
				for (Integer carcassa : carcasses) {
					modelCarcassa.addElement(carcassa);
				}
				modelPlaca = new DefaultListModel();
				for (Integer placa : plaques) {
					modelPlaca.addElement(placa);
				}
				listCarcassa = new JList(modelCarcassa);
				listCarcassa.setBounds(159, 47, 117, 161);
				contentPane.add(listCarcassa);
				listCarcassa
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				listPlaca = new JList(modelPlaca);
				listPlaca.setBounds(19, 47, 117, 161);
				contentPane.add(listPlaca);
				listPlaca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				listTecnic = new JList(modelTecnics);
				listTecnic.setBounds(304, 47, 117, 161);
				contentPane.add(listTecnic);
				listTecnic
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				contentPane.updateUI();
			}
		} catch (Exception e1) {
			tirarError(e1.getMessage());
		}
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
