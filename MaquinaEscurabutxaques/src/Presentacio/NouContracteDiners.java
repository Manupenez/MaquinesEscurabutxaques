package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import Aplicacio.ControladorContracte;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NouContracteDiners extends JFrame {

	private JPanel contentPane;
	private JTextField textPercentatge;
	private JTextField textPagamentMensual;
	private ControladorContracte controladorContracte;
	private String tipusComerc;
	private int idComerc;
	private String info;
	private LinkedList<Integer> maquines;

	/**
	 * Create the frame.
	 */
	public NouContracteDiners(int idComerc, String info,
			LinkedList<Integer> maquines) {
		setTitle("Pagament del contracte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.idComerc = idComerc;
		this.info = info;
		this.maquines = maquines;
		try {
			controladorContracte = new ControladorContracte();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			tirarError(e.getMessage());
		}

		try {
			tipusComerc = controladorContracte.mirarTipusComerc(idComerc);
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
		if (tipusComerc == "MINORISTA") {
			JLabel lblPercentatge = new JLabel("Percentatge");
			lblPercentatge.setBounds(47, 83, 88, 14);
			contentPane.add(lblPercentatge);

			textPercentatge = new JTextField();
			textPercentatge.setBounds(181, 133, 117, 20);
			contentPane.add(textPercentatge);
			textPercentatge.setColumns(10);

			JButton btnOk1 = new JButton("OK");
			btnOk1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String diners = textPercentatge.getSelectedText();
					if (diners.length() < 1) {
						tirarError("S'ha d'omplenar el percentetge");
					} else {
						try {
							double percentatge = Double.parseDouble(diners);
							if (percentatge < 0 || percentatge > 100) {
								tirarError("El percentatge ha d'estar entre 0 i 100");
							} else {
								nouContracteFinalitzat(percentatge, -1);
							}
						} catch (Exception e) {
							tirarError("El percentatge ha de ser un número - "
									+ e.getMessage());
						}

					}
				}
			});
			btnOk1.setBounds(335, 79, 89, 23);
			contentPane.add(btnOk1);
		} else {
			JLabel lblPagamentMensual = new JLabel("Pagament mensual");
			lblPagamentMensual.setBounds(47, 136, 112, 14);
			contentPane.add(lblPagamentMensual);

			textPagamentMensual = new JTextField();
			textPagamentMensual.setBounds(181, 80, 117, 20);
			contentPane.add(textPagamentMensual);
			textPagamentMensual.setColumns(10);

			JButton btnOk2 = new JButton("OK");
			btnOk2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String diners = textPagamentMensual.getSelectedText();
					if (diners.length() < 1) {
						tirarError("S'ha d'omplenar el pagament mensual");
					} else {
						try {
							double pagament = Double.parseDouble(diners);
							if (pagament < 0) {
								tirarError("El pagament mensual ha de ser 0 o positiu.");
							} else {
								nouContracteFinalitzat(-1, pagament);
							}
						} catch (Exception e2) {
							tirarError("El pagament mensual ha de ser un número - "
									+ e2.getMessage());
						}

					}
				}
			});
			btnOk2.setBounds(335, 132, 89, 23);
			contentPane.add(btnOk2);
		}

		JButton btnCancellar = new JButton("Cancel·lar");
		btnCancellar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tornarEnrere();
			}
		});
		btnCancellar.setBounds(335, 228, 89, 23);
		contentPane.add(btnCancellar);
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

	public void nouContracteFinalitzat(double percentatge, double pagament) {
		try {
			int retorn = controladorContracte.nouContracte(idComerc, info,
					maquines, percentatge, pagament);
			JOptionPane.showMessageDialog(new JFrame(), "Creat contracte: "
					+ retorn, "Contracte Creat", JOptionPane.PLAIN_MESSAGE);
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
		tornarEnrere();
	}
}
