package Main;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.UIManager;

import GUI.DangNhap;

public class Start {
	private static Connection connectDatabase() throws SQLException {
		Connection con = null;
        String url = "jdbc:sqlserver://localhost:1434;databaseName=HeThongQuanLyKhachSan;trustServerCertificate=true;encrypt=true";
        String user = "sa";
        String password = "sapassword";
        con = DriverManager.getConnection(url, user, password);
        return con;
	}
	public static void main(String[] args) throws SQLException {
		try {
			 UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	         UIManager.put("nimbusBase", new Color(38, 38, 42));

       } catch (Exception e) {
           e.printStackTrace();
       }
		new DangNhap(connectDatabase());
	}
}
