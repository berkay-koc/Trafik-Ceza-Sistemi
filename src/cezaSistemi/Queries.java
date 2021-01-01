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
			ERROR.error("Sistemde bulunmamaktas�n�z!");
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
			Object[] buffer = new Object[] {r.getLong(1), r.getString(2), r.getString(3), r.getString(4), r.getInt(5), r.getDate(6), r.getString(7)};
			model.addRow(buffer);
		}
		//r.getLong(1), r.getString(2), r.getString(3), r.getString(4), r.getInt(4), r.getDate(5), r.getString(6)
		s.close();
		conn.close();
	}
	
	public static void cezaSorgula_Police(String kimlikno) throws SQLException {
		try{
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		String query = "SELECT * FROM ceza_sor("+kimlikno+")";
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(query);
		DefaultTableModel model = (DefaultTableModel)POLICE.yenilencezaTable.getModel();
		
		while(r.next()) {
			Object[] buffer = new Object[] {r.getLong(1), r.getString(2), r.getString(3), r.getString(4), r.getInt(5), r.getDate(6), r.getString(7)};
			model.addRow(buffer);
		}
		s.close();
		conn.close();
	}
	
	public static void borcSorgula_Citizen() throws SQLException {
		try{
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		String query = "SELECT * FROM borc_sor("+kimlikno+")";
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(query);
		r.next();
		String text = r.getString(1) + " TL";
		CITIZEN.borcSorText.setText(text);
		
		s.close();
		conn.close();
	}
	
	public static void borcSorgula_Police() throws SQLException {
		try{
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		kimlikno = POLICE.tcknSorguField.getText();
		String query = "SELECT * FROM borc_sor("+kimlikno+")";
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(query);
		r.next();
		String text = r.getString(1) + " TL";
		POLICE.borcSorField.setText(text);
		
		s.close();
		conn.close();
	}
	
	public static void cezaTablosuInsert() throws SQLException{
		try{
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String query = "SELECT * FROM ceza";
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(query);
		DefaultTableModel model = (DefaultTableModel)POLICE.cezaTipTable.getModel();
		while(r.next()) {
			Object[] buffer = new Object[] {r.getInt(1), r.getString(2)};
			model.addRow(buffer);
		}
		
		
		s.close();
		conn.close();
		
	}
	
	public static void yeniCezaEkle() throws SQLException{
		try{
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
		String isim = POLICE.isimField.getText();
		String soyisim = POLICE.soyisimField.getText();
		String tckn = POLICE.tcknField.getText();
		String plaka = POLICE.plakaField.getText();
		String aracTipi = POLICE.aractipiBox.getSelectedItem().toString();
		String cezaTipi = POLICE.cezatipiBox.getSelectedItem().toString();
		
		System.out.println(isim + soyisim + tckn + plaka + aracTipi);
		
		long milli = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(milli);
		
		String query1 = "SELECT p_id FROM plaka WHERE plakano = '"+plaka+"'";
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(query1);
		r.next();
		Integer plaka_id = r.getInt(1);
		
		String query2 = "SELECT cezano FROM cezalar WHERE cezaismi = '"+cezaTipi+"'";
		s = conn.createStatement();
		r = s.executeQuery(query2);
		r.next();
		Integer ceza = r.getInt(1);
		
		String query3 = "SELECT tabanucret FROM cezalar WHERE cezaismi = '"+cezaTipi+"'";
		s = conn.createStatement();
		r = s.executeQuery(query3);
		r.next();
		Integer cezaUcreti = r.getInt(1);
		
		String query4 = "INSERT INTO yenilencezalar VALUES("+tckn+", nextval('ceza_id'), "+kimlikno+", "+plaka_id+", "+ceza+","+cezaUcreti+",'"+date+"')";
		s = conn.createStatement();
		if(!s.execute(query4)) {
			ERROR.error("Ekleme ba�ar�l�! \nTrigger �al��t�!");
		}
		else {
			ERROR.error("Ekleme ba�ar�s�z! \\nTrigger �al��mad�!");
			}
		s.close();
		}
		catch(Exception e){
			ERROR.error("Yanl�� parametre!");
		}
		conn.close();
		
	}
	public static void insertCezalarToComboBox() throws SQLException{
		try{
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		String query = "SELECT cezaismi FROM cezalar";
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(query);
		
		while(r.next()) {
			POLICE.cezatipiBox.addItem(r.getString(1).toString());
		}
		
		s.close();
		conn.close();
	}
	public static void insertBorclarToComboBox_Police() throws SQLException{
		try{
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		String query = "SELECT cezano FROM yenilencezalar WHERE surucutckn = "+ kimlikno;
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(query);
		
		while(r.next()) {
			POLICE.cezaOdeBox.addItem(r.getString(1).toString());
		}
		
		s.close();
		conn.close();
	}
	public static void insertBorclarToComboBox_Citizen() throws SQLException{
		try{
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		String query = "SELECT cezano FROM yenilencezalar WHERE surucutckn = "+ kimlikno;
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(query);
		
		while(r.next()) {
			CITIZEN.cezaOdeBox.addItem(r.getString(1).toString());
		}
		
		s.close();
		conn.close();
	}
	
	public static void borcOde_Police() throws SQLException {
		try{
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		String query = "DELETE FROM yenilencezalar WHERE cezano = "+POLICE.cezaOdeBox.getSelectedItem().toString();
		Statement s = conn.createStatement();
		s.execute(query);
		POLICE.cezaOdeBox.removeAllItems();
		insertBorclarToComboBox_Police();
		
		s.close();
		conn.close();
	}
	public static void borcOde_Citizen() throws SQLException {
		try{
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		String query = "DELETE FROM yenilencezalar WHERE cezano = "+CITIZEN.cezaOdeBox.getSelectedItem().toString();
		Statement s = conn.createStatement();
		s.execute(query);
		CITIZEN.cezaOdeBox.removeAllItems();
		insertBorclarToComboBox_Citizen();
		
		s.close();
		conn.close();
	}
	
	public static void atamaYap() throws SQLException{
		try{
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		String query = "UPDATE polisler SET sube = '"+POLICE.textField_1.getText()+"' WHERE tckn = "+ POLICE.textField.getText();
		Statement s = conn.createStatement();
		s.execute(query);
		
		ERROR.error("Atama ba�ar�l�!");
		
		s.close();
		conn.close();
	}
	
}

