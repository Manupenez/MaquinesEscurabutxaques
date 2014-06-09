package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;

public class ModificarPecesReparacio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarPecesReparacio frame = new ModificarPecesReparacio();
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
	public ModificarPecesReparacio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Plaques Lliures");
		lblNewLabel.setBounds(12, 13, 104, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Carcasses Lliures");
		lblNewLabel_1.setBounds(128, 13, 104, 23);
		contentPane.add(lblNewLabel_1);
		
		JList list = new JList();
		list.setBounds(0, 58, 116, 182);
		contentPane.add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(128, 58, 116, 182);
		contentPane.add(list_1);
	}

}
