package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;

import javax.swing.JList;

import Aplicacio.ControladorContracte;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class NouContracte extends JFrame {

	private JPanel contentPane;
	private ControladorContracte controladorContracte;
	private DefaultListModel modelComerc;
	private DefaultListModel modelMaquines;
	private JList listMaquines;
	private JList listComercos;
	private LinkedList<Integer> maquinesXContracte;
	private JButton btnAcceptar;
	private JButton btnAfegir;
	private JTextField tfInfo;

	/**
	 * Create the frame.
	 */
	public NouContracte() {
		setTitle("Nou Contracte");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			controladorContracte = new ControladorContracte();
		} catch (Exception e) {
			tirarError(e.getMessage());
		}
		maquinesXContracte = new LinkedList<Integer>();
		JButton btnCancellar = new JButton("Cancel\u00B7lar");
		btnCancellar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tornarEnrere();
			}
		});
		btnCancellar.setBounds(318, 249, 126, 23);
		contentPane.add(btnCancellar);

		btnAcceptar = new JButton("Acceptar");
		btnAcceptar.setEnabled(false);
		btnAcceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(listMaquines.isSelectionEmpty() || listComercos.isSelectionEmpty())){
					for (Object maquina : listMaquines.getSelectedValuesList()) {
						maquinesXContracte.add(Integer.parseInt(String
								.valueOf(maquina)));
					}
					try {
						if(tfInfo.getText()==null){
							tfInfo.setText(" ");
						}
						int retorn = controladorContracte.nouContracte(Integer.parseInt(String
								.valueOf(listComercos.getSelectedValue())), tfInfo
								.getText(), maquinesXContracte);
						JOptionPane.showMessageDialog(new JFrame(), "Creat contracte: "+retorn, "Contracte Creat",
								JOptionPane.PLAIN_MESSAGE);
						/*
						 * HEM DE FER QUE AL APRETAR ACCEPTAR S'OBRI UNA NOVA FINESTRA
						 * AQUESTA IDENTIFICARA DIRECTAMENT SI EL COMERÇ ÉS MAJORISTA 
						 * O MINORISTA MITJANÇANT EL SEU IDCOMERÇ.ET DEIXARÀ ESCOLLIR
						 * EL PERCENTATGE DE LA RECAPTAICIÓ PACTAT O EL PAGAMENT EFECTUAT
						 */
					} catch (Exception e1) {
						tirarError(e1.getMessage());
					}
					tornarEnrere();
				}else{
					tirarError("Has de seleccionar un comerç i mínim una màquina");
				}
			}
		});
		
		omplirPantalla();

		btnAcceptar.setBounds(318, 214, 126, 29);
		contentPane.add(btnAcceptar);

		JLabel lblComeros = new JLabel("Comerços");
		lblComeros.setBounds(185, 14, 101, 16);
		contentPane.add(lblComeros);

		JLabel lblMquines = new JLabel("Màquines");
		lblMquines.setBounds(19, 14, 61, 16);
		contentPane.add(lblMquines);

		JLabel lblInformaciDelContracte = new JLabel("Informació del contracte");
		lblInformaciDelContracte.setBounds(20, 197, 266, 16);
		contentPane.add(lblInformaciDelContracte);

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

	public void omplirPantalla() {
		LinkedList<Integer> comercos;
		LinkedList<Integer> maquines;
		try {
			maquines = controladorContracte.obtenirMaquinesLlestes("LLESTA");
			comercos = controladorContracte.comercSenseContracte();
			if (!comercos.isEmpty()) {
				modelComerc = new DefaultListModel();
				for (Integer comerc : comercos) {
					modelComerc.addElement(comerc);
				}
				listComercos = new JList(modelComerc);
				listComercos
						.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				listComercos.setBounds(185, 42, 149, 150);
				contentPane.add(listComercos);
				contentPane.updateUI();
				if (!maquines.isEmpty()) {
					modelMaquines = new DefaultListModel();
					for (Integer maquina : maquines) {
						modelMaquines.addElement(maquina);
					}
					listMaquines = new JList(modelMaquines);
					listMaquines.setBounds(19, 42, 148, 150);
					contentPane.add(listMaquines);
					tfInfo = new JTextField();
					tfInfo.setBounds(20, 213, 266, 59);
					contentPane.add(tfInfo);
					tfInfo.setColumns(10);
					btnAcceptar.setEnabled(true);
					contentPane.updateUI();
				} else {
					tirarError("No hi ha Maquines llestes per distribuïr");
				}
			} else {
				tirarError("No hi ha Comerços sense contracte");
			}

		} catch (Exception e) {
			tirarError(e.getMessage());
		}
	}
}
