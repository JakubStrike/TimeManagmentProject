package timeManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ShowActivities {

	protected JFrame frmShowActivities;
	
	private Login loginScreen = null;
	private AddActivity addActivity = null;
	private Database database = null;
	
	private String login, password;
	
	private String[] activityNames = new String[100];
	private String[] activityDescriptions = new String[100];
	private String[] deadlines = new String[100];
	private int[] counters = {0, 1};
	private int activitiesCount;
	
	private JTextField txtLogoff, txtAddNewActivity;
	private JLabel lblActivityName, lblActivityDeadline, lblActivityName2, lblActivityDeadline2;
	private JTextArea textArea, textArea2;

	/**
	 * Create the application.
	 */
	public ShowActivities() {
		initialize();
	}
	
	public ShowActivities(String login, String password) {
		this.login = login;
		this.password = password;
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShowActivities = new JFrame();
		frmShowActivities.setResizable(false);
		frmShowActivities.setTitle("Show Activities");
		frmShowActivities.setSize(536, 345);
		frmShowActivities.setLocationRelativeTo(null);
		frmShowActivities.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShowActivities.getContentPane().setLayout(null);
		
		getAllUserActivity();		

		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(0, 0, 546, 342);
		frmShowActivities.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(105, 105, 105));
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(187, 11, 308, 143);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Activity Name:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(5, 5, 114, 14);
		panel_1.add(lblNewLabel_1);
		
		lblActivityName = new JLabel("");
		lblActivityName.setText(activityNames[counters[0]]);
		lblActivityName.setForeground(new Color(255, 255, 255));
		lblActivityName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblActivityName.setBounds(123, 5, 175, 14);
		panel_1.add(lblActivityName);
		
		JLabel lblNewLabel_3 = new JLabel("Activity Description:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(5, 47, 114, 14);
		panel_1.add(lblNewLabel_3);
		
		textArea = new JTextArea();
		textArea.setText(activityDescriptions[counters[0]]);
		textArea.setEditable(false);
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.setBounds(123, 45, 175, 90);
		panel_1.add(textArea);
		
		JLabel lblDeadline = new JLabel("Deadline:");
		lblDeadline.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDeadline.setForeground(Color.WHITE);
		lblDeadline.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDeadline.setBounds(5, 25, 114, 14);
		panel_1.add(lblDeadline);
		
		lblActivityDeadline = new JLabel("");
		lblActivityDeadline.setText(deadlines[counters[0]]);
		lblActivityDeadline.setForeground(Color.WHITE);
		lblActivityDeadline.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblActivityDeadline.setBounds(123, 25, 175, 14);
		panel_1.add(lblActivityDeadline);
		
		JLabel lblComplete = new JLabel("");
		lblComplete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeActivity(0);
			}
		});
		lblComplete.setIcon(new ImageIcon(ShowActivities.class.getResource("/timeManager/Done.png")));
		lblComplete.setBounds(40, 85, 50, 50);
		panel_1.add(lblComplete);
		
		JLabel lblNewLabel_2 = new JLabel("Activity Completed?");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(5, 69, 114, 14);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblUP = new JLabel("");
		lblUP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				moveUP();
			}
		});
		lblUP.setIcon(new ImageIcon(ShowActivities.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-fewer-details.png")));
		lblUP.setBounds(502, 11, 21, 21);
		panel.add(lblUP);
		
		JLabel lblDOWN = new JLabel("");
		lblDOWN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				moveDown();
			}
		});
		lblDOWN.setIcon(new ImageIcon(ShowActivities.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-more-details.png")));
		lblDOWN.setBounds(502, 275, 21, 21);
		panel.add(lblDOWN);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBackground(SystemColor.controlDkShadow);
		panel_2.setBounds(187, 155, 308, 143);
		panel.add(panel_2);
		
		JLabel label = new JLabel("Activity Name:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(5, 5, 114, 14);
		panel_2.add(label);
		
		lblActivityName2 = new JLabel("");
		lblActivityName2.setText(activityNames[counters[1]]);
		lblActivityName2.setForeground(Color.WHITE);
		lblActivityName2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblActivityName2.setBounds(123, 5, 175, 14);
		panel_2.add(lblActivityName2);
		
		JLabel label_2 = new JLabel("Activity Description:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setBounds(5, 47, 114, 14);
		panel_2.add(label_2);
		
		textArea2 = new JTextArea();
		textArea2.setText(activityDescriptions[counters[1]]);
		textArea2.setEditable(false);
		textArea2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea2.setBounds(123, 45, 175, 90);
		panel_2.add(textArea2);
		
		JLabel label_3 = new JLabel("Deadline:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setBounds(5, 25, 114, 14);
		panel_2.add(label_3);
		
		lblActivityDeadline2 = new JLabel("");
		lblActivityDeadline2.setText(deadlines[counters[1]]);
		lblActivityDeadline2.setForeground(Color.WHITE);
		lblActivityDeadline2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblActivityDeadline2.setBounds(123, 25, 175, 14);
		panel_2.add(lblActivityDeadline2);
		
		JLabel lblComplete2 = new JLabel("");
		lblComplete2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeActivity(1);
			}
		});
		lblComplete2.setIcon(new ImageIcon(ShowActivities.class.getResource("/timeManager/Done.png")));
		lblComplete2.setBounds(40, 85, 50, 50);
		panel_2.add(lblComplete2);
		
		JLabel label_6 = new JLabel("Activity Completed?");
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_6.setBounds(5, 69, 114, 14);
		panel_2.add(label_6);
		
		txtLogoff = new JTextField();
		txtLogoff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				logoff();	
			}
		});
		txtLogoff.setText("Sign out");
		txtLogoff.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogoff.setForeground(Color.WHITE);
		txtLogoff.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtLogoff.setEditable(false);
		txtLogoff.setColumns(10);
		txtLogoff.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		txtLogoff.setBackground(Color.GRAY);
		txtLogoff.setBounds(40, 11, 92, 25);
		panel.add(txtLogoff);
		
		txtAddNewActivity = new JTextField();
		txtAddNewActivity.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				txtAddNewActivity.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			}
		});
		txtAddNewActivity.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				callAddActivity();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				txtAddNewActivity.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			}
		});
		txtAddNewActivity.setText("Add new activity");
		txtAddNewActivity.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddNewActivity.setForeground(Color.WHITE);
		txtAddNewActivity.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtAddNewActivity.setEditable(false);
		txtAddNewActivity.setColumns(10);
		txtAddNewActivity.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtAddNewActivity.setBackground(Color.GRAY);
		txtAddNewActivity.setBounds(30, 270, 120, 25);
		panel.add(txtAddNewActivity);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(ShowActivities.class.getResource("/timeManager/graphs.png")));
		lblNewLabel_4.setBounds(10, 47, 167, 212);
		panel.add(lblNewLabel_4);
	}
	
	private void callAddActivity() {
		addActivity = new AddActivity(login, password);
		addActivity.frmAddActiviti.setVisible(true);
		frmShowActivities.setVisible(false);
	}
	
	private void logoff() {
		loginScreen = new Login();
		loginScreen.frmLogin.setVisible(true);
		frmShowActivities.setVisible(false);
	}
	
	/**
	 * sort from smallest date to biggest
	 */
	private void sortActivitiesInOrder() {
		String[] temp = new String[3];
		for(int i = 0; i < (activitiesCount - 1); i++) {
			for(int j = 0; j < (activitiesCount - 1); j++) {
				if(deadlines[j].compareTo(deadlines[j + 1]) > 0) {
					temp[0] = deadlines[j];
					temp[1] = activityNames[j];
					temp[2] = activityDescriptions[j];
					deadlines[j] = deadlines[j + 1];
					activityNames[j] = activityNames[j + 1];
					activityDescriptions[j] = activityDescriptions[j + 1];
					deadlines[j + 1] = temp[0];
					activityNames[j + 1] = temp[1];
					activityDescriptions[j + 1] = temp[2];		
				}				
			}
		}	
	}
	/**
	 * getting all activities for a user
	 */
	private void getAllUserActivity() {
		database = new Database(login, activityNames, activityDescriptions, deadlines);
		activityNames = database.getActivityNames();
		activityDescriptions = database.getActivityDescriptions();
		deadlines = database.getDeadlines();
		activitiesCount = database.getCounter();
		sortActivitiesInOrder();
	}
	
	
	private void removeActivity(int i){
		database = new Database(login, password, activityNames[counters[i]], activityDescriptions[counters[i]], deadlines[counters[i]]);
		activityNames = new String[100];
		activityDescriptions = new String[100];
		deadlines = new String[100];
		getAllUserActivity();
		setActivitiesTexts();
	}
	
	private void moveDown() {
		activitiesCount = database.getCounter();
		if(counters[1] < (activitiesCount - 1)) {
			counters[0]++;
			counters[1]++;
			setActivitiesTexts();
		}
	}
	
	private void moveUP() {
		if(counters[0] > 0) {
			counters[0]--;
			counters[1]--;
			setActivitiesTexts();
		}
	}
	
	private void setActivitiesTexts() {
		lblActivityName.setText(activityNames[counters[0]]);
		lblActivityName2.setText(activityNames[counters[1]]);
		textArea.setText(activityDescriptions[counters[0]]);
		textArea2.setText(activityDescriptions[counters[1]]);
		lblActivityDeadline.setText(deadlines[counters[0]]);
		lblActivityDeadline2.setText(deadlines[counters[1]]);
	}
	
}
