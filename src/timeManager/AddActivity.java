package timeManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AddActivity {

	protected JFrame frmAddActiviti;
	
	private Login loginScreen = null;
	private Database database = null;
	private ShowActivities showActivities = null;
	
	private JTextField textActivityName;
	private JTextField txtLogoff;
	private JLabel lblNewLabel_1;
	private JLabel lblActivityDescription;
	private JTextField txtAddActivity;
	private JTextField txtShowActivitis;
	private JTextArea textArea;
	
	private JDateChooser dateChooser;
	private String login = "";
	private String password = "";
	private String deadline = "";
	private Date date;
	private JLabel lblErrorMessage;
	private JLabel lblErrorMessage2;
	private JLabel lblErrorMessage3;

	
	/**
	 * Create the application.
	 */
	public AddActivity() {
		gui();
	}
	
	public AddActivity(String login, String password) {
		this.login = login;
		this.password = password;
		gui();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void gui() {
		frmAddActiviti = new JFrame();
		frmAddActiviti.setTitle("Add Activity");
		frmAddActiviti.setSize(413, 284);
		frmAddActiviti.setResizable(false);
		frmAddActiviti.setLocationRelativeTo(null);
		frmAddActiviti.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddActiviti.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(0, 0, 434, 261);
		frmAddActiviti.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome, " + login);
		lblNewLabel.setBorder(new LineBorder(new Color(255, 255, 255), 3, true));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(26, 11, 224, 25);
		panel.add(lblNewLabel);
		
		textActivityName = new JTextField();
		textActivityName.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textActivityName.setBounds(26, 64, 224, 20);
		panel.add(textActivityName);
		textActivityName.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.setBounds(26, 112, 224, 57);
		panel.add(textArea);
		
		txtLogoff = new JTextField();
		txtLogoff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				logoff();
			}
		});
		txtLogoff.setEditable(false);
		txtLogoff.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtLogoff.setForeground(new Color(255, 255, 255));
		txtLogoff.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogoff.setText("Sign out");
		txtLogoff.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		txtLogoff.setBackground(new Color(128, 128, 128));
		txtLogoff.setBounds(273, 11, 92, 25);
		panel.add(txtLogoff);
		txtLogoff.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Activity Name*");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(26, 47, 104, 14);
		panel.add(lblNewLabel_1);
		
		lblActivityDescription = new JLabel("Activity Description");
		lblActivityDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblActivityDescription.setForeground(Color.WHITE);
		lblActivityDescription.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblActivityDescription.setBounds(26, 95, 138, 14);
		panel.add(lblActivityDescription);
		
		dateChooser = new JDateChooser();
		dateChooser.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		dateChooser.setBounds(260, 64, 119, 20);
		panel.add(dateChooser);
		
		
		txtAddActivity = new JTextField();
		txtAddActivity.setEditable(false);
		txtAddActivity.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				txtAddActivity.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			}
		});
		txtAddActivity.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				txtAddActivity.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				add();
			}
		});
		txtAddActivity.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtAddActivity.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddActivity.setText("Add activity");
		txtAddActivity.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtAddActivity.setForeground(new Color(255, 255, 255));
		txtAddActivity.setBackground(new Color(128, 128, 128));
		txtAddActivity.setBounds(26, 192, 104, 25);
		panel.add(txtAddActivity);
		txtAddActivity.setColumns(10);
		
		JLabel lblDeadline = new JLabel("Deadline*");
		lblDeadline.setHorizontalAlignment(SwingConstants.LEFT);
		lblDeadline.setForeground(Color.WHITE);
		lblDeadline.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDeadline.setBounds(260, 47, 104, 14);
		panel.add(lblDeadline);
		
		txtShowActivitis = new JTextField();
		txtShowActivitis.setEditable(false);
		txtShowActivitis.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				txtShowActivitis.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			}
		});
		txtShowActivitis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				txtShowActivitis.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				show();
			}
		});
		txtShowActivitis.setText("Show activities");
		txtShowActivitis.setHorizontalAlignment(SwingConstants.CENTER);
		txtShowActivitis.setForeground(Color.WHITE);
		txtShowActivitis.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtShowActivitis.setColumns(10);
		txtShowActivitis.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtShowActivitis.setBackground(Color.GRAY);
		txtShowActivitis.setBounds(146, 192, 104, 25);
		panel.add(txtShowActivitis);
		
		lblErrorMessage = new JLabel("");
		lblErrorMessage.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblErrorMessage.setForeground(new Color(128, 0, 0));
		lblErrorMessage.setBounds(26, 72, 398, 36);
		panel.add(lblErrorMessage);
		
		lblErrorMessage2 = new JLabel("");
		lblErrorMessage2.setForeground(new Color(128, 0, 0));
		lblErrorMessage2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblErrorMessage2.setBounds(26, 161, 398, 36);
		panel.add(lblErrorMessage2);
		
		lblErrorMessage3 = new JLabel("");
		lblErrorMessage3.setForeground(Color.GREEN);
		lblErrorMessage3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblErrorMessage3.setBounds(26, 208, 398, 36);
		panel.add(lblErrorMessage3);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showActivities = new ShowActivities(login, password);
				showActivities.frmShowActivities.setVisible(true);
				frmAddActiviti.setVisible(false);
			}
		});
		btnNewButton.setBounds(273, 146, 89, 23);
		panel.add(btnNewButton);
		
	}
	
	private void logoff() {
		loginScreen = new Login();
		loginScreen.frmLogin.setVisible(true);
		frmAddActiviti.setVisible(false);	
	}
	
	private void add(){
		String activityName = "";
		String activityDescription = "";
		boolean activityNameCorrect = true;
		boolean descriptionCorrect = true;
		boolean dateAccepted = true;
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
		
		lblErrorMessage3.setText("");
		
		activityName = textActivityName.getText();
		activityDescription = textArea.getText();
		
		try {
			if(!(Character.isUpperCase(activityName.charAt(0)) && activityName.length() > 1)){
				activityNameCorrect = false;
				lblErrorMessage.setText("Activity name need to start with capital letter and have at least 2 characters");
			}else {
				lblErrorMessage.setText("");
			}
			} catch(Exception ex) {
				activityNameCorrect = false;
				lblErrorMessage.setText("Activity name need to start with capital letter and have at least 2 characters");
				ex.printStackTrace();
			}
		
		try {
			int counterWords = 0;
			for(int i = 0; i<activityDescription.length(); i++) {
				if(activityDescription.charAt(i) == ' ') {
					counterWords++;
				}
			}
			if(counterWords > 20) {
				descriptionCorrect = false;
				lblErrorMessage2.setText("you cant have more than 20 words in description");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}		 
		
		if(activityNameCorrect == true) {	 		 
			 //gettign today's date
			 date = new Date();
			 String todayDate = dateFormat.format(date);
			 
			 try {
			 //checking if chosen date is earlier than today's one
				 deadline = dateFormat.format(dateChooser.getDate());
			 if(todayDate.compareTo(deadline) > 0) {
				 lblErrorMessage.setText("You cant choose date earlier than today's date");
				 dateAccepted = false;
			 }
			 }catch(Exception ex) {
				 dateAccepted = false;
				 lblErrorMessage.setText("You need to pick the date");
				 ex.printStackTrace();
			 }
		}
		 
		if(activityNameCorrect == true && dateAccepted == true && descriptionCorrect == true) {
			boolean originalActivityName = false;
			database = new Database(activityName, login , 0);
			originalActivityName = database.getOriginalName();
			if(originalActivityName == true) {
				try {
					date = dateFormat.parse(deadline);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				database = new Database(activityName, activityDescription, date, login, password);	
				lblErrorMessage3.setText("Activity added!");
			}		
			else {
				lblErrorMessage3.setText("");
				lblErrorMessage2.setText("This activity name already exist");
			}
		}
	}
	
	private void show() {
		showActivities = new ShowActivities(login, password);
		showActivities.frmShowActivities.setVisible(true);
		frmAddActiviti.setVisible(false);
	}
}
