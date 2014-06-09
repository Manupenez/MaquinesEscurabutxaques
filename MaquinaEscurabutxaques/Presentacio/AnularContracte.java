package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;

import Aplicacio.ControladorContracte;

public class AnularContracte extends JFrame {

	private JPanel contentPane;
	private JList listComercos;
	private ControladorContracte controladorContracte;
	private DefaultListModel modelComerc;
	private JButton btnAcceptar;

	/**
	 * Create the frame.
	 */
	public AnularContracte() {
		try {
			controladorContracte = new ControladorContracte();
		} catch (Exception e2) {
			tirarError(e2.getMessage());
		}
		setTitle("Anul·lar Contracte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnCancellar = new JButton("Cancel·lar");
		btnCancellar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tornarEnrere();
			}
		});

		contentPane.setLayout(null);
		btnCancellar.setBounds(300, 228, 124, 23);
		contentPane.add(btnCancellar);

		JLabel lblComerosAmbContracte = new JLabel("Comerços amb contracte");
		lblComerosAmbContracte.setBounds(29, 6, 185, 16);
		contentPane.add(lblComerosAmbContracte);

		btnAcceptar = new JButton("Acceptar");
		btnAcceptar.setEnabled(false);
		btnAcceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controladorContracte.baixaContracte(Integer.parseInt(String
							.valueOf(listComercos.getSelectedValue())));
					tornarEnrere();
				} catch (Exception e1) {
					tirarError(e1.getMessage());
				}
			}
		});
		btnAcceptar.setBounds(300, 187, 124, 29);
		contentPane.add(btnAcceptar);

		omplirPantalla();
	}

	public void tornarEnrere() {
		GestioContracte gestio = new GestioContracte();
		gestio.setVisible(true);
		this.dispose();
	}

	public void omplirPantalla() {
		LinkedList<Integer> comercos;
		try {
			comercos = controladorContracte.comercAmbContracte();
			if (!comercos.isEmpty()) {
				modelComerc = new DefaultListModel();
				for (Integer comerc : comercos) {
					modelComerc.addElement(comerc);
				}
				listComercos = new JList(modelComerc);
				listComercos
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				listComercos.setBounds(29, 32, 157, 175);
				contentPane.add(listComercos);
				btnAcceptar.setEnabled(true);
				contentPane.updateUI();
			} else {
				tirarError("No hi ha Comerços amb contracte");
			}
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
	}

	public void tirarError(String missatge) {
		JOptionPane.showMessageDialog(new JFrame(), missatge, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
}
