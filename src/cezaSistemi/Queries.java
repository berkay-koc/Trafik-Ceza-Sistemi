package cezaSistemi;

import java.sql.*;

import javax.swing.table.DefaultTableModel;

public class Queries {

	final static String url = "jdbc:postgresql://localhost/TCS";
	final static String user = "postgres";
	final static String password = "1234";
	static String kimlikno;
	static Connection conn;
	
	public static void main(String[] args) throws SQLException {
		Queries sqlconnect = new Queries();
		
		try{
			conn = DriverManager.getConnection(url,user,password);
			if(conn != null){
				System.out.println("Connected!");
			}else{
				System.out.println("Failed!");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		LOGIN l = new LOGIN();
		l.login();
		
		conn.close();
	}
	
	
	public static void login_control(String tckn) throws SQLException {
		try{
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		String query = "SELECT login("+tckn+")";
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(query);
		r.next();
		
		if(r.getLong(1)==1){
			POLICE p = new POLICE();
			p.police();
		}else if(r.getLong(1)==2){
			CITIZEN c = new CITIZEN();
			c.citizen();
		}else{
			ERROR.error("Sistemde bulunmamaktasýnýz!");
		}
		
		s.close();
		conn.close();
	}
	
	public static void cezaSorgula_Citizen() throws SQLException {
		try{
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		String query = "SELECT * FROM ceza_sor("+kimlikno+")";
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(query);
		DefaultTableModel model = (DefaultTableModel)CITIZEN.cezaSorTable.getModel();
		
		while(r.next()) {
			model.addRow();
		}
		
		s.close();
		conn.close();
	}
	
}
