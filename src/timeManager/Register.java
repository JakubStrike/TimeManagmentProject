package timeManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Register {

	protected JFrame frmRegister;
	private Database database = null;
	private Login loginClass = null;
	
	private JTextField textLogin;
	private JPasswordField passwordField;
	private JTextField textEmail;
	private JTextField txtRegister;
	private JLabel lblErrorMessage;
	
	private String login;
	private String password;
	private String email;


	/**
	 * Create the application.
	 */
	public Register() {
		gui();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void gui() {
		frmRegister = new JFrame();
		frmRegister.setResizable(false);
		frmRegister.setTitle("Register");
		frmRegister.setSize(306, 203);
		frmRegister.setLocationRelativeTo(null);
		frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegister.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(0, 0, 374, 378);
		frmRegister.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 279, 153);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(128, 128, 128));
		panel_2.setBounds(1, 1, 277, 151);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login*:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 85, 26);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password*:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 48, 85, 17);
		panel_2.add(lblNewLabel_1);
		
		textLogin = new JTextField();
		textLogin.setBounds(105, 16, 144, 20);
		panel_2.add(textLogin);
		textLogin.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(105, 48, 144, 20);
		panel_2.add(passwordField);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(10, 76, 85, 17);
		panel_2.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBounds(105, 79, 144, 20);
		panel_2.add(textEmail);
		textEmail.setColumns(10);
		
		txtRegister = new JTextField();
		txtRegister.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				txtRegister.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			}
		});
		txtRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				registerUser();	
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				txtRegister.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			}
		});
		txtRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtRegister.setForeground(new Color(255, 255, 255));
		txtRegister.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegister.setText("Register");
		txtRegister.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtRegister.setEditable(false);
		txtRegister.setBackground(new Color(128, 128, 128));
		txtRegister.setBounds(71, 110, 114, 26);
		panel_2.add(txtRegister);
		txtRegister.setColumns(10);
		
		lblErrorMessage = new JLabel("");
		lblErrorMessage.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(5, 137, 270, 14);
		panel_2.add(lblErrorMessage);
	}
	
	@SuppressWarnings("deprecation")
	private void registerUser() {
		login = textLogin.getText();
		password = passwordField.getText();
		email = textEmail.getText();
		boolean loginValidation = true;
		boolean passwordValidation = true;
		loginValidation = registrationValidation(login);
		passwordValidation = registrationValidation(password);
		if(loginValidation == true && passwordValidation == true) {
		//check for same login
		boolean originalLogin = false;
		database = new Database(login, originalLogin);
		originalLogin = database.getOriginalLogin();
			if(originalLogin == true) {
				database = new Database(login, password, email);
				loginClass = new Login();
				loginClass.frmLogin.setVisible(true);
				frmRegister.setVisible(false);
			}else {
				lblErrorMessage.setText("This login already exist. Please choose diffrent one");
			}
		}	
	}
	
	private boolean registrationValidation(String account){
		try {
			if(account.length() < 3){
				lblErrorMessage.setText("Login and password need to have at least 3 characters!");
				return false;
			}
			} catch(Exception ex) {
				lblErrorMessage.setText("Login and password need to have at least 3 characters!");;
				System.out.println(ex);
				return false;
			}
		return true;
	}
	

}
