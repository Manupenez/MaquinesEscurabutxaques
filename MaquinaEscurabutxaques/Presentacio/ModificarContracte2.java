package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Aplicacio.ControladorContracte;
import Domini.Contracte;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class ModificarContracte2 extends JFrame {

	private JPanel contentPane;
	private ControladorContracte controladorContracte;
	private JTextField textPagament;
	private JTextField textPercentatge;
	private JList listMaquinesAfegir;
	private JList listMaquinesTreure;
	private DefaultListModel modelMaquinesTreure;
	private DefaultListModel modelMaquinesAfegir;
	private JTextField textInformacio;
	private Contracte cont;

	/**
	 * Create the frame.
	 */

	public ModificarContracte2(Contracte contracte) {
		cont = contracte;
		setTitle("Modificar Contracte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCancellar = new JButton("Cancel·lar");
		btnCancellar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tornarEnrere();
			}
		});
		btnCancellar.setBounds(466, 292, 89, 23);
		contentPane.add(btnCancellar);

		JButton btnAcceptar = new JButton("Acceptar");
		btnAcceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				completarModificacio(cont);
			}
		});
		btnAcceptar.setBounds(466, 258, 89, 23);
		contentPane.add(btnAcceptar);

		try {
			controladorContracte = new ControladorContracte();
		} catch (Exception e) {
			tirarError(e.getMessage());
		}

		omplenarPantalla(contracte);

	}

	public void omplenarPantalla(Contracte contracte) {
		try {

			LinkedList<Integer> maquinesTreure = controladorContracte
					.maquinesDelContracte(contracte);
			modelMaquinesTreure = new DefaultListModel();
			for (Integer maquina : maquinesTreure) {
				modelMaquinesTreure.addElement(maquina);
			}

			LinkedList<Integer> maquinesAfegir = controladorContracte
					.obtenirMaquinesLlestes();
			modelMaquinesAfegir = new DefaultListModel();
			for (Integer maquina : maquinesAfegir) {
				modelMaquinesAfegir.addElement(maquina);
			}

			listMaquinesAfegir = new JList(modelMaquinesAfegir);
			listMaquinesAfegir.setBounds(20, 56, 127, 195);
			contentPane.add(listMaquinesAfegir);

			listMaquinesTreure = new JList(modelMaquinesTreure);
			listMaquinesTreure.setBounds(180, 56, 127, 195);
			contentPane.add(listMaquinesTreure);

			JLabel lblInformaci = new JLabel("Informació");
			lblInformaci.setBounds(357, 31, 68, 14);
			contentPane.add(lblInformaci);

			textInformacio = new JTextField(contracte.getInformacio());
			textInformacio.setBounds(357, 56, 198, 56);
			contentPane.add(textInformacio);

			JLabel lblAfegirMquines = new JLabel("Afegir Màquines:");
			lblAfegirMquines.setBounds(20, 30, 116, 16);
			contentPane.add(lblAfegirMquines);

			JLabel lblTreureMquines = new JLabel("Treure Màquines");
			lblTreureMquines.setBounds(180, 28, 127, 16);
			contentPane.add(lblTreureMquines);

			String tipus = contracte.getTipusComerc();
			if (tipus.equals("MAJORISTA")) {
				textPercentatge = new JTextField(String.valueOf(contracte
						.getPercentatge()));
				textPercentatge.setBounds(357, 216, 140, 20);
				contentPane.add(textPercentatge);
				textPercentatge.setColumns(10);

				JLabel lblPercentatge = new JLabel("Percentatge");
				lblPercentatge.setBounds(357, 191, 106, 14);
				contentPane.add(lblPercentatge);
			} else {
				JLabel lblPagament = new JLabel("Pagament mensual");
				lblPagament.setBounds(357, 123, 106, 14);
				contentPane.add(lblPagament);

				textPagament = new JTextField(String.valueOf(contracte
						.getPagament()));
				textPagament.setBounds(357, 148, 140, 20);
				contentPane.add(textPagament);
				textPagament.setColumns(10);
			}
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
	}

	public void completarModificacio(Contracte contracte) {
		try {
			double pagament = contracte.getPagament();
			double percentatge = contracte.getPercentatge();
			if (contracte.getTipusComerc().equals("MAJORISTA")) {
				String diners = textPercentatge.getText();
				if (diners.length() < 1) {
					tirarError("S'ha d'omplenar el percentetge");
				} else {
					percentatge = Double.parseDouble(diners);
					if (percentatge < 0 || percentatge > 100) {
						tirarError("El percentatge ha d'estar entre 0 i 100");
					} else {
						pagament = -1;
					}
				}
			} else {
				String diners = textPagament.getText();
				if (!(diners.length() > 0)) {
					tirarError("S'ha d'omplenar el pagament mensual");
				} else {
					pagament = Double.parseDouble(diners);
					if (pagament < 0) {
						tirarError("El pagament mensual ha de ser 0 o positiu.");
					} else {
						percentatge = -1;
					}
				}
			}
			LinkedList<Integer> maquinesNouContracte = controladorContracte
					.maquinesDelContracte(contracte);
			if (!listMaquinesTreure.isSelectionEmpty()) {
				for (Object maquina : listMaquinesTreure
						.getSelectedValuesList()) {
					maquinesNouContracte.remove(maquina);
				}
			}
			if (!listMaquinesAfegir.isSelectionEmpty()) {
				for (Object maquina : listMaquinesAfegir
						.getSelectedValuesList()) {
					maquinesNouContracte.add(Integer.parseInt(String
							.valueOf(maquina)));
				}
			}
			System.out.println("3");
			controladorContracte.modificarContracte(contracte,
					textInformacio.getText(), maquinesNouContracte,
					percentatge, pagament);
			tornarEnrere();
		} catch (Exception e) {
			tirarError(e.getMessage());
		}

	}

	public void tornarEnrere() {
		GestioContracte gestio = new GestioContracte();
		gestio.setVisible(true);
		this.dispose();
	}

	public void tirarError(String missatge) {
		JOptionPane.showMessageDialog(new JFrame(), missatge, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}
