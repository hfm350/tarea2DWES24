package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;



public class BasedeDatos {
	private static Connection con;

	private static BasedeDatos connection = null;

	private BasedeDatos() {
		Properties prop = new Properties();
		MysqlDataSource m = new MysqlDataSource();
		FileInputStream fis;

		try {
			fis = new FileInputStream("src/main/resources/db.properties");
			prop.load(fis);
			m.setUrl(prop.getProperty("url"));
			m.setPassword(prop.getProperty("password"));
			m.setUser(prop.getProperty("usuario"));
			con = m.getConnection();
		} catch (FileNotFoundException e) {
			System.out.println("Error al acceder al fichero properties " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al leer las propiedades del fichero properties" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos");
		}
	}

	

	public static Connection getCon() {
		return con;
	}



	public static void setCon(Connection con) {
		BasedeDatos.con = con;
	}



	public static BasedeDatos getInstance() {
		if (connection == null)
			connection = new BasedeDatos();
		return connection;
	}
	
	public static void CerrarConexion() {
		try {
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
