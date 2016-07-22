package client;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class ManagerLogin {
	private JFrame ManagerFrame;
	private JTextField txt_ManagerID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerLogin window = new ManagerLogin();
					window.ManagerFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public ManagerLogin() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ManagerFrame = new JFrame();
		ManagerFrame.setType(Type.UTILITY);
		ManagerFrame.setTitle("Manager Login");
		ManagerFrame.setBounds(100, 100, 257, 163);
		ManagerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ManagerFrame.getContentPane().setLayout(null);
		
		JButton btn_ManagerID = new JButton("Log In");
		btn_ManagerID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				String temp = txt_ManagerID.getText().toString().substring(0,3).toUpperCase();
				
				if(!temp.equals("MTL") && !temp.equals("DDO") && !temp.equals("LVL"))
				{
					JOptionPane.showMessageDialog(null, "Incorrect Manager ID, ID should start with DDO/LVL/MTL",temp, JOptionPane.ERROR_MESSAGE, null);
				}
				
				else
				{
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								ManagerClient window = new ManagerClient(txt_ManagerID.getText().toString());
								window.frame.setVisible(true);
							} catch (Exception e) {
					
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		btn_ManagerID.setBounds(96, 60, 101, 22);
		ManagerFrame.getContentPane().add(btn_ManagerID);
		
		JLabel lblNewLabel = new JLabel("Manager ID");
		lblNewLabel.setBounds(12, 18, 84, 36);
		ManagerFrame.getContentPane().add(lblNewLabel);
		
		txt_ManagerID = new JTextField();
		txt_ManagerID.setBounds(96, 25, 101, 22);
		ManagerFrame.getContentPane().add(txt_ManagerID);
		txt_ManagerID.setColumns(10);
	}
}