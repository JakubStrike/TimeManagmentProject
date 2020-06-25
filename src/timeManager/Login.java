package timeManager;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Login {

	protected JFrame frmLogin;
	
	private Database database = null;
	private AddActivity addActivity = null;
	private Register register = null;
	
	private JTextField textLogin;
	private JPasswordField passwordField;
	private JTextField txtLogin;
	private JTextField txtRegister;
	private JLabel lblErrorMessage;
	
	private String login;
	private String password;
	private boolean loginSuccessful = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		gui();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void gui() {
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login");
		frmLogin.setSize(554, 296);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(0, 0, 552, 362);
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/timeManager/clockify-logo.png")));
		lblNewLabel.setBounds(-10, 0, 239, 266);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(224, 31, 318, 207);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(255, 255, 255));
		panel_2.setBackground(new Color(128, 128, 128));
		panel_2.setBounds(1, 1, 316, 205);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("User Login");
		lblNewLabel_1.setBounds(120, 11, 97, 33);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Corbel", Font.BOLD, 16));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		
		textLogin = new JTextField();
		textLogin.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textLogin.setBounds(50, 41, 219, 27);
		panel_2.add(textLogin);
		textLogin.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField.setBounds(50, 83, 219, 27);
		panel_2.add(passwordField);
		
		txtLogin = new JTextField();
		txtLogin.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				txtLogin.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			}
		});
		txtLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				checkLogin();	
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				txtLogin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			}
		});
		txtLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtLogin.setForeground(new Color(255, 255, 255));
		txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogin.setText("Login");
		txtLogin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtLogin.setBackground(new Color(128, 128, 128));
		txtLogin.setEditable(false);
		txtLogin.setBounds(50, 121, 100, 27);
		panel_2.add(txtLogin);
		txtLogin.setColumns(10);
		
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
		txtRegister.setBackground(new Color(128, 128, 128));
		txtRegister.setEditable(false);
		txtRegister.setColumns(10);
		txtRegister.setBounds(169, 121, 100, 27);
		panel_2.add(txtRegister);
		
		lblErrorMessage = new JLabel("");
		lblErrorMessage.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(50, 153, 219, 14);
		panel_2.add(lblErrorMessage);
	}
	
	@SuppressWarnings("deprecation")
	private void checkLogin() {
		lblErrorMessage.setText("");
		login = textLogin.getText();
		password = passwordField.getText();
		
		database = new Database(login, password);
		loginSuccessful = database.getLoginCheck();
		
		if(loginSuccessful == true) {
			addActivity = new AddActivity(login, password);
			addActivity.frmAddActiviti.setVisible(true);
			frmLogin.setVisible(false);
		}else {
			lblErrorMessage.setText("Login or password are incorrect");
		}
	}
	
	private void registerUser() {
		register = new Register();
		register.frmRegister.setVisible(true);
		frmLogin.setVisible(false);	
	}
}
