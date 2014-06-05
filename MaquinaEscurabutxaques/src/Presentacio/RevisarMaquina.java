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

public class RevisarMaquina extends JFrame {

	private JPanel contentPane;
	private ControladorMaquina controladorMaquina;
	private DefaultListModel modelMaquines;
	private DefaultListModel modelTecnics;
	private JList listMaquines;
	private JButton btnTecnic;
	private JButton btnMquina;
	private JList listTecnics;

	/**
	 * Create the frame.
	 */
	public RevisarMaquina() {
		setTitle("Revisar M\u00E0quines");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 385, 335);
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
		btnCancellar.setBounds(208, 262, 117, 23);
		contentPane.add(btnCancellar);

		omplirPantalla();

		btnMquina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listMaquines.isSelectionEmpty()) {
					tirarError("Has de seleccionar quina màquina vols revisar");
					btnMquina.setEnabled(false);
				} else {
					try {
						btnTecnic.setEnabled(true);

						LinkedList<Integer> tecnics = controladorMaquina
								.obtenirTecnicsRevisar(Integer.parseInt(String
										.valueOf(listMaquines
												.getSelectedValue())));
						modelTecnics = new DefaultListModel();
						for (Integer tecnic : tecnics) {
							modelTecnics.addElement(tecnic);
						}
						listTecnics = new JList(modelTecnics);
						listTecnics
								.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						listTecnics.setBounds(208, 47, 117, 161);
						contentPane.add(listTecnics);
						contentPane.updateUI();
					} catch (Exception e1) {
						tirarError(e1.getMessage());
					}
				}
			}
		});

		btnTecnic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listTecnics.isSelectionEmpty()) {
					tirarError("Has de seleccionar un tècnic per que revisi la màquina");
				} else {
					try {
						Object[] options = { "Si", "No" };
						int n = JOptionPane.showOptionDialog(new JFrame(),
								"La màquina està correcte?",
								"Revisió de Màquina",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
						if (n == JOptionPane.YES_OPTION) {
							controladorMaquina.canviarEstatOK(Integer
									.parseInt(String.valueOf(listTecnics
											.getSelectedValue())), Integer
									.parseInt(String.valueOf(listMaquines
											.getSelectedValue())), true);
							JOptionPane.showMessageDialog(new JFrame(),
									"Maquina amb id: "+listMaquines.getSelectedValue()+" revisada per tècnic: "+listTecnics.getSelectedValue(),
									"Màquina Revisada", JOptionPane.PLAIN_MESSAGE);
						} else {
							if (n == JOptionPane.NO_OPTION) {
								controladorMaquina.canviarEstatOK(Integer
										.parseInt(String.valueOf(listTecnics
												.getSelectedValue())), Integer
										.parseInt(String.valueOf(listMaquines
												.getSelectedValue())), false);
								JOptionPane.showMessageDialog(new JFrame(),
										"Maquina amb id: "+listMaquines.getSelectedValue()+" revisada per tècnic: "+listTecnics.getSelectedValue(),
										"Màquina Revisada", JOptionPane.PLAIN_MESSAGE);
							}
						}
						tornarEnrere();
					} catch (Exception e1) {
						tirarError(e1.getMessage());
					}
				}
			}
		});

	}

	public void omplirPantalla() {
		LinkedList<Integer> maquines;
		try {
			maquines = controladorMaquina.obtenirMaquinesRevisar();
			if (maquines.isEmpty()) {
				tirarError("No hi han màquines per revisar");
			}
			modelMaquines = new DefaultListModel();
			for (Integer maquina : maquines) {
				modelMaquines.addElement(maquina);
			}
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
		listMaquines = new JList(modelMaquines);
		listMaquines.setBounds(19, 47, 117, 161);
		contentPane.add(listMaquines);
		listMaquines.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnTecnic = new JButton("Escollir T\u00E8cnic");
		btnTecnic.setEnabled(false);
		btnTecnic.setBounds(208, 228, 117, 23);
		contentPane.add(btnTecnic);

		JLabel lblMaquinesPerRevisar = new JLabel("M\u00E0quines per revisar:");
		lblMaquinesPerRevisar.setBounds(19, 22, 142, 14);
		contentPane.add(lblMaquinesPerRevisar);

		JLabel lblNewLabel = new JLabel("T\u00E8cnic per a revisar:");
		lblNewLabel.setBounds(208, 22, 117, 14);
		contentPane.add(lblNewLabel);

		btnMquina = new JButton("Escollir M\u00E0quina");
		btnMquina.setBounds(19, 228, 117, 23);
		contentPane.add(btnMquina);
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
