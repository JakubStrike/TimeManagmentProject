package timeManager;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Database {
	private boolean originalName = false;
	private boolean loginSuccess = false;
	private boolean originalLogin = false;
	private String login;
	private String password;
	private int counter = 0;

	
	private String[] activityNames = new String[100];
	private String[] activityDescriptions = new String[100];
	private String[] deadlines = new String[100];


public Database(String activityName, String login, int nothing) {
	this.login = login;
	originalName(activityName);
}

public Database(String login, String password, String activityName, String activityDescription, String deadline) {
	removeActivity(activityName);
}

public Database(String login, String[] activityNames, String[] activityDescriptions, String[] deadlines) {
	this.login = login;
	this.activityNames = activityNames;
	this.activityDescriptions = activityDescriptions;
	this.deadlines = deadlines;
	allActivities();
}

public Database(String activityName, String activityDescription, Date date, String login, String password) {
	this.login = login;
	this.password = password;
	setValuesInDatabase(activityName, activityDescription, date);
}

public Database(String login, String password) {
	this.login = login;
	this.password = password;
	loginCheck();
}

public Database(String login, boolean originalLogin) {
	this.originalLogin = originalLogin;
	this.login = login;
	checkLogin();
}

public Database(String login, String password,String email) {
	this.login = login;
	this.password = password;
	setUserAccount(email);
}

private void removeActivity(String activityName) {
	try {			
		Class.forName("com.mysql.jdbc.Driver"); 
		Connection con=DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2333529", "sql2333529", "jR8%pU1*");;
				String query = "delete from timeManagerProject where activityName = ?";		
				PreparedStatement preparedStmt = con.prepareStatement(query);
			    preparedStmt.setString (1, activityName);
			    preparedStmt.execute();
		con.close();
		}catch(Exception e) {
			System.out.println(e);
		}	
}

private void allActivities(){
	
	try {
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2333529", "sql2333529", "jR8%pU1*");
		Statement stmt=con.createStatement();
		String sql = "Select * from timeManagerProject Where login='" + login + "'";
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next()) {
			if((activityNames[counter] = rs.getString("activityName")) == null) {
				continue;
			}else {
				activityNames[counter] = rs.getString("activityName");
				activityDescriptions[counter] = rs.getString("activityDescription");
				deadlines[counter] = rs.getString("deadline");
				counter++;
			}	
		}
		con.close();
	}catch(Exception e) {
		System.out.println(e);
	}	
}

public int getCounter() {
	return counter;
}

public String[] getActivityNames() {
	return activityNames;
}

public String[] getActivityDescriptions() {
	return activityDescriptions;
}

public String[] getDeadlines() {
	return deadlines;
}


private void setValuesInDatabase(String activityName, String activityDescription, Date date) {
	try {
	SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
	String deadline = dateFormat.format(date);
		
	Class.forName("com.mysql.jdbc.Driver"); 
	Connection con=DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2333529", "sql2333529", "jR8%pU1*");
			String query = " insert into timeManagerProject (activityName, activityDescription, deadline, login, password)" + " values (?, ?, ?, ?, ?)";		
			PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.setString (1, activityName);
		    preparedStmt.setString (2, activityDescription);
		    preparedStmt.setString (3, deadline);
		    preparedStmt.setString (4, this.login);
		    preparedStmt.setString (5, this.password);
		    preparedStmt.execute();
	con.close();
	}catch(Exception e) {
		System.out.println(e);
	}
}


private void setUserAccount(String email) {
	try {
		Class.forName("com.mysql.jdbc.Driver"); 
		Connection con=DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2333529", "sql2333529", "jR8%pU1*");
				String query = " insert into timeManagerProject (login, password, email)" + " values (?, ?, ?)";		
				PreparedStatement preparedStmt = con.prepareStatement(query);
			    preparedStmt.setString (1, login);
			    preparedStmt.setString (2, password);
			    preparedStmt.setString (3, email);			    
			    preparedStmt.execute();
		con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
}

private void checkLogin() {
	try {
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2333529", "sql2333529", "jR8%pU1*");
		Statement stmt=con.createStatement();
		String sql = "Select * from timeManagerProject Where login='" + login + "'";
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()) {
			con.close();
			originalLogin = false;
		}
		else {
			con.close();
			originalLogin = true;
		}
	}catch(Exception e) {
		System.out.println(e);
	}
}

public boolean getOriginalLogin() {
	return originalLogin;
}

private void originalName(String activityName) {
	try {
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2333529", "sql2333529", "jR8%pU1*");
		Statement stmt=con.createStatement();
		String sql = "Select * from timeManagerProject Where activityName='" + activityName + "' and login='" + login + "'";
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()) {
			con.close();
			originalName = false;
		}
		else {
			con.close();
			originalName = true;
		}
	}catch(Exception e) {
		System.out.println(e);
	}
}


public boolean getOriginalName() {
	return originalName;
}
	
private void loginCheck() {
	try {
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2333529", "sql2333529", "jR8%pU1*");
		Statement stmt=con.createStatement();
		String sql = "Select * from timeManagerProject Where login='" + login + "' and password='" + password + "'";
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()) {
			con.close();
			loginSuccess = true;
		}
		else {
			con.close();
			loginSuccess = false;
		}
	}catch(Exception e) {
		System.out.println(e);
	}
}

public boolean getLoginCheck() {
	return loginSuccess;
}
	
public String getLogin() {
	return login;
}

}
