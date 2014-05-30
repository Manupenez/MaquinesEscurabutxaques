package Presentacio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;

import Aplicacio.ControladorMaquina;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

public class PantallaInicial extends JFrame {

	private JPanel contentPane;
	private JButton btnMuntarMaquina;
	private ControladorMaquina controladorMaquina;
	private JLabel lblErrors;
	private int tecnic = 0;
	private int carcassa = 0;
	private int placa = 0;
	private JLabel lblInfo;
	private JButton btnValidarMaquina;
	private JButton btnRevisarMaquina;
	private JButton btnEscollirTecnic;
	private JButton btnValidarRev;
	private JList listTecnic;
	private JList listCarcassa_Maquina;
	private JList listPlaca;
	private JLabel lblSeleccionarCarcassa_Maquina;
	private JLabel lblSeleccionarTecnic;
	private JLabel lblSeleccionarPlaca;
	private DefaultListModel modelTecnics;
	private DefaultListModel modelCarcassa;
	private DefaultListModel modelPlaca;
	private DefaultListModel modelMaquines;
	private JButton btnNoValidarRevisio;
	private JButton btnRemuntar;
	private JButton btnFiRemuntar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaInicial frame = new PantallaInicial();
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
	public PantallaInicial() {

		setTitle("Maquina EscuraButxaques");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		lblErrors = new JLabel("");
		lblErrors.setBounds(28, 378, 708, 51);

		try {
			this.controladorMaquina = new ControladorMaquina();
		} catch (Exception s) {
			lblErrors.setText(s.getMessage());
		}

		btnMuntarMaquina = new JButton("Muntar Màquina");
		btnMuntarMaquina.setBounds(15, 20, 151, 23);
		btnMuntarMaquina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblErrors.setText("");
				btnRevisarMaquina.setEnabled(false);
				btnRemuntar.setEnabled(false);
				btnMuntarMaquina.setEnabled(false);
				btnValidarMaquina.setEnabled(true);
				lblInfo.setText("");

				lblSeleccionarTecnic.setText("Seleccionar Tecnic: ");
				lblSeleccionarCarcassa_Maquina
						.setText("Seleccionar Carcassa: ");
				lblSeleccionarPlaca.setText("Seleccionar Placa: ");

				try {
					LinkedList<Integer> tecnics;
					LinkedList<Integer> carcasses;
					LinkedList<Integer> plaques;
					carcasses = controladorMaquina.obtenirLListaCarcasses();
					plaques = controladorMaquina.obtenirLListaPlaques();
					tecnics = controladorMaquina.obtenirTecnics();

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
					listCarcassa_Maquina = new JList(modelCarcassa);
					listCarcassa_Maquina.setBounds(375, 91, 146, 168);
					contentPane.add(listCarcassa_Maquina);

					listPlaca = new JList(modelPlaca);
					listPlaca.setBounds(566, 91, 146, 168);
					contentPane.add(listPlaca);

					listTecnic = new JList(modelTecnics);
					listTecnic.setBounds(191, 91, 146, 168);
					contentPane.add(listTecnic);
					contentPane.updateUI();

				} catch (Exception e1) {
					lblErrors.setText(e1.getMessage());
				}

			}
		});

		lblInfo = new JLabel("");
		lblInfo.setBounds(28, 335, 708, 64);

		btnValidarMaquina = new JButton("Validar Màquina");
		btnValidarMaquina.setBounds(15, 49, 151, 23);
		btnValidarMaquina.setEnabled(false);
		btnValidarMaquina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (listTecnic.isSelectionEmpty()
							|| listCarcassa_Maquina.isSelectionEmpty()
							|| listPlaca.isSelectionEmpty()) {
						lblErrors
								.setText("Has de seleccionar un tècnic, una carcassa i una placa");
					} else {
						tecnic = Integer.parseInt(String.valueOf(listTecnic
								.getSelectedValue()));
						placa = Integer.parseInt(String.valueOf(listPlaca
								.getSelectedValue()));
						carcassa = Integer.parseInt(String
								.valueOf(listCarcassa_Maquina
										.getSelectedValue()));

						lblInfo.setText("Màquina Muntada amb ID: "
								+ controladorMaquina.muntarMaquina(tecnic,
										placa, carcassa));

						tecnic = 0;
						placa = 0;
						carcassa = 0;
						btnValidarMaquina.setEnabled(false);
						btnMuntarMaquina.setEnabled(true);
						btnRevisarMaquina.setEnabled(true);
						btnRemuntar.setEnabled(true);

						contentPane.remove(listCarcassa_Maquina);
						contentPane.remove(listPlaca);
						lblSeleccionarPlaca.setText("");
						lblSeleccionarCarcassa_Maquina.setText("");
						lblSeleccionarTecnic.setText("");
						lblErrors.setText("");
						contentPane.remove(listTecnic);
					}
				} catch (Exception e1) {
					lblErrors.setText(e1.getMessage());
				}
				contentPane.updateUI();
			}
		});
		contentPane.setLayout(null);

		contentPane.add(btnMuntarMaquina);
		contentPane.add(btnValidarMaquina);
		contentPane.add(lblErrors);
		contentPane.add(lblInfo);

		lblSeleccionarTecnic = new JLabel("");
		lblSeleccionarTecnic.setBounds(190, 64, 147, 16);
		contentPane.add(lblSeleccionarTecnic);

		lblSeleccionarCarcassa_Maquina = new JLabel("");
		lblSeleccionarCarcassa_Maquina.setBounds(375, 63, 146, 16);
		contentPane.add(lblSeleccionarCarcassa_Maquina);

		lblSeleccionarPlaca = new JLabel("");
		lblSeleccionarPlaca.setBounds(566, 63, 146, 16);
		contentPane.add(lblSeleccionarPlaca);

		btnRevisarMaquina = new JButton("Revisar Màquina");
		btnRevisarMaquina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LinkedList<Integer> maquines;
				try {
					maquines = controladorMaquina.obtenirMaquinesRevisar();
					if (!maquines.isEmpty()) {

						btnMuntarMaquina.setEnabled(false);
						btnRevisarMaquina.setEnabled(false);
						btnEscollirTecnic.setEnabled(true);
						btnRemuntar.setEnabled(false);
						lblInfo.setText("");
						lblErrors.setText("");
						lblSeleccionarCarcassa_Maquina
								.setText("Seleccionar Màquina: ");
						modelMaquines = new DefaultListModel();
						for (Integer maquina : maquines) {
							modelMaquines.addElement(maquina);
						}
						listCarcassa_Maquina = new JList(modelMaquines);
						listCarcassa_Maquina.setBounds(375, 91, 146, 168);
						contentPane.add(listCarcassa_Maquina);

						contentPane.updateUI();
					} else {
						lblErrors.setText("No hi han màquines per a revisar");
					}
				} catch (Exception e2) {
					lblErrors.setText(e2.getMessage());
				}
			}
		});
		btnRevisarMaquina.setBounds(15, 94, 151, 29);
		contentPane.add(btnRevisarMaquina);

		btnEscollirTecnic = new JButton("Escollir Tècnic");
		btnEscollirTecnic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblInfo.setText("");
				LinkedList<Integer> tecnics;
				try {

					if (listCarcassa_Maquina.isSelectionEmpty()) {
						lblErrors.setText("Selecciona una màquina");
					} else {
						tecnics = controladorMaquina
								.obtenirTecnicsRevisar(Integer.parseInt(String
										.valueOf(listCarcassa_Maquina
												.getSelectedValue())));

						modelTecnics = new DefaultListModel();
						for (Integer tecnic : tecnics) {
							modelTecnics.addElement(tecnic);
						}
						lblSeleccionarTecnic.setText("Seleccionar Tècnic: ");
						listCarcassa_Maquina.setEnabled(false);
						btnEscollirTecnic.setEnabled(false);
						btnRemuntar.setEnabled(false);
						btnValidarRev.setEnabled(true);
						btnNoValidarRevisio.setEnabled(true);
						listTecnic = new JList(modelTecnics);
						listTecnic.setBounds(191, 91, 146, 168);
						contentPane.add(listTecnic);
						lblErrors.setText("");
					}
					contentPane.updateUI();
				} catch (Exception e3) {
					lblErrors.setText(e3.getMessage());
				}
			}
		});
		btnEscollirTecnic.setEnabled(false);
		btnEscollirTecnic.setBounds(15, 125, 151, 29);
		contentPane.add(btnEscollirTecnic);

		btnValidarRev = new JButton("OK");
		btnValidarRev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (listTecnic.isSelectionEmpty()) {
						lblErrors.setText("Selecciona un tècnic");
					} else {
						btnValidarRev.setEnabled(false);
						btnNoValidarRevisio.setEnabled(false);
						btnRemuntar.setEnabled(true);
						btnMuntarMaquina.setEnabled(true);
						btnRevisarMaquina.setEnabled(true);
						lblErrors.setText("");
						controladorMaquina.canviarEstatOK(Integer
								.parseInt(String.valueOf(listTecnic
										.getSelectedValue())), Integer
								.parseInt(String.valueOf(listCarcassa_Maquina
										.getSelectedValue())), true);

						contentPane.remove(listCarcassa_Maquina);
						contentPane.remove(listTecnic);

						lblSeleccionarTecnic.setText("");
						lblSeleccionarCarcassa_Maquina.setText("");
					}
					contentPane.updateUI();
				} catch (Exception e3) {
					lblErrors.setText(e3.getMessage());
				}
			}
		});
		btnValidarRev.setEnabled(false);
		btnValidarRev.setBounds(15, 158, 80, 29);
		contentPane.add(btnValidarRev);

		btnNoValidarRevisio = new JButton("NO OK");
		btnNoValidarRevisio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (listTecnic.isSelectionEmpty()) {
						lblErrors.setText("Selecciona un tècnic");
					} else {
						btnValidarRev.setEnabled(false);
						btnNoValidarRevisio.setEnabled(false);
						btnRemuntar.setEnabled(true);
						btnRevisarMaquina.setEnabled(true);
						btnMuntarMaquina.setEnabled(true);
						controladorMaquina.canviarEstatOK(Integer
								.parseInt(String.valueOf(listTecnic
										.getSelectedValue())), Integer
								.parseInt(String.valueOf(listCarcassa_Maquina
										.getSelectedValue())), false);
						contentPane.remove(listCarcassa_Maquina);
						contentPane.remove(listTecnic);
						lblInfo.setText("");
						lblSeleccionarTecnic.setText("");
						lblSeleccionarCarcassa_Maquina.setText("");
						lblErrors.setText("");
					}
				} catch (Exception e1) {
					lblErrors.setText(e1.getMessage());
				}
				contentPane.updateUI();
			}
		});
		btnNoValidarRevisio.setEnabled(false);
		btnNoValidarRevisio.setBounds(86, 158, 80, 29);
		contentPane.add(btnNoValidarRevisio);

		btnRemuntar = new JButton("Tornar a Muntar");
		btnRemuntar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LinkedList<Integer> maquines;
				try {
					maquines = controladorMaquina.obtenirMaquinesReMuntar();
					if (!maquines.isEmpty()) {
						btnMuntarMaquina.setEnabled(false);
						btnRevisarMaquina.setEnabled(false);
						btnRemuntar.setEnabled(false);
						btnFiRemuntar.setEnabled(true);
						lblErrors.setText("");
						lblInfo.setText("");
						modelMaquines = new DefaultListModel();
						for (Integer maquina : maquines) {
							modelMaquines.addElement(maquina);
						}

						lblSeleccionarCarcassa_Maquina
								.setText("Seleccionar Màquina: ");

						listCarcassa_Maquina = new JList(modelMaquines);
						listCarcassa_Maquina.setBounds(375, 91, 146, 168);
						contentPane.add(listCarcassa_Maquina);
						contentPane.updateUI();
					} else {
						lblErrors.setText("No hi han màquines per re-muntar");
					}
				} catch (Exception e3) {
					lblErrors.setText(e3.getMessage());

				}
			}
		});
		btnRemuntar.setBounds(15, 205, 151, 29);
		contentPane.add(btnRemuntar);

		btnFiRemuntar = new JButton("Re-Muntar");
		btnFiRemuntar.setEnabled(false);
		btnFiRemuntar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (listCarcassa_Maquina.isSelectionEmpty()) {
						lblErrors.setText("Selecciona una màquina");
					} else {
						btnFiRemuntar.setEnabled(false);
						btnRemuntar.setEnabled(true);
						btnRevisarMaquina.setEnabled(true);
						btnMuntarMaquina.setEnabled(true);
						controladorMaquina.tornarMuntar(Integer.parseInt(String
								.valueOf(listCarcassa_Maquina
										.getSelectedValue())));
						contentPane.remove(listCarcassa_Maquina);
						lblInfo.setText("");
						lblSeleccionarCarcassa_Maquina.setText("");
						lblErrors.setText("");
					}
				} catch (Exception e1) {
					lblErrors.setText(e1.getMessage());
				}

				contentPane.updateUI();
			}
		});
		btnFiRemuntar.setBounds(15, 234, 151, 29);
		contentPane.add(btnFiRemuntar);

	}
}
