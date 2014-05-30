package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ModificarContracte extends JFrame {

	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public ModificarContracte() {
		setTitle("Modificar Contracte");
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
		btnCancellar.setBounds(304, 228, 120, 23);
		contentPane.add(btnCancellar);
	}
	
	public void tornarEnrere(){
		GestioContracte gestio = new GestioContracte();
		gestio.setVisible(true);
		this.dispose();
	}
	

}

