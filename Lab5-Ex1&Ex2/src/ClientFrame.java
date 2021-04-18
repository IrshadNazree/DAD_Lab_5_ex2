import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class ClientFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame window = new ClientFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 12));
		textField.setBounds(112, 36, 96, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Arial", Font.PLAIN, 12));
		textField_1.setBounds(112, 65, 96, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Item Name : ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(36, 39, 66, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Item Price : ");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(36, 68, 66, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Item ID : ");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(36, 169, 66, 13);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Item Name : ");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(36, 192, 66, 13);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Item Price : ");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(36, 215, 66, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel labelID = new JLabel("");
		labelID.setFont(new Font("Arial", Font.PLAIN, 12));
		labelID.setBounds(118, 169, 90, 13);
		frame.getContentPane().add(labelID);
		
		JLabel labelName = new JLabel("");
		labelName.setFont(new Font("Arial", Font.PLAIN, 12));
		labelName.setBounds(118, 192, 90, 13);
		frame.getContentPane().add(labelName);
		
		JLabel labelPrice = new JLabel("");
		labelPrice.setFont(new Font("Arial", Font.PLAIN, 12));
		labelPrice.setBounds(118, 215, 90, 13);
		frame.getContentPane().add(labelPrice);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemProduct itemP = new ItemProduct();
				
				String name = textField.getText();
				float price = Float.parseFloat(textField_1.getText());
				this.client(name, price);
				
				
			}

			private void client(String name, float price) {
					ItemProduct itemP = new ItemProduct();
					itemP.setName(name);
					itemP.setPrice(price);
					
					try {
						int portNo = 4228;
						InetAddress serverAddress = InetAddress.getLocalHost();
						Socket sc = new Socket(serverAddress, portNo);

						// Open stream to send object
						ObjectOutputStream oos = new ObjectOutputStream(sc.getOutputStream());

						// Send request to server
						System.out.println("Send object to server: " + itemP);
						oos.writeObject(itemP);
						oos.flush();
						
						// Open stream to receive object
						ObjectInputStream ois = new ObjectInputStream(sc.getInputStream());
						
						// Get object from stream and display details
						itemP = (ItemProduct) ois.readObject();
						labelID.setText(String.valueOf(itemP.getItemProductID()));
						labelName.setText(itemP.getName());
						labelPrice.setText("RM " + String.valueOf(itemP.getPrice()));
//						System.out.println ("Id for " + itemP.getName() + " is " + itemP.getItemProductID());
						
						// Close all closeable objects
						oos.close();
						ois.close();
						sc.close();
						
					} catch (IOException | ClassNotFoundException e){
						// TODO: handle exception
						e.printStackTrace();
					}
					
				}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(118, 94, 85, 21);
		frame.getContentPane().add(btnNewButton);
	}
	
		
	}
