package next.xadmin.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import next.xadmin.login.bean.LoginBean;

public class LoginDao {
	private String dburl="jdbc:mysql://localhost:8085/userdb";
	private static String dbUname="root";
	private static String dbPassword="rootpasswordgiven";
	private static String dbDriver="com.mysql.cj.jdbc.Driver";
	private static String dbUrl;
	public static void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection con=null;
		try {
			con=DriverManager.getConnection(dbUrl,dbUname,dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static boolean validate(LoginBean loginBean) {
	loadDriver(dbDriver);
	Connection con=getConnection();
	boolean status=false;
	String sql="select * from login where username=? and password=?";
		
	PreparedStatement ps;
	try {
		ps=con.prepareStatement(sql);
		ps.setString(1,loginBean.getUsername());
		ps.setString(2,loginBean.getPassword());
		ResultSet rs=ps.executeQuery();
		status=rs.next();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		return status;
	}

}
