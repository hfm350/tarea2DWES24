package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import dao.*;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ConexionBD {
	private static Connection con;

	private static ConexionBD connection = null;

	private ConexionBD() {
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

	public static Connection getConexion() {
		try {
			if (con == null || con.isClosed()) {
				Properties propiedades = new Properties();
				MysqlDataSource m = new MysqlDataSource();
				FileInputStream fis;
				fis = new FileInputStream("src/resources/db.properties");
				propiedades.load(fis);
				m.setUrl(propiedades.getProperty("url"));
				m.setPassword(propiedades.getProperty("password"));
				m.setUser(propiedades.getProperty("usuario"));
				fis.close();
				con = m.getConnection();
			}
			return con;
		} catch (FileNotFoundException e) {
			System.out.println("Error al acceder al fichero properties " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error al leer las propiedades del fichero properties" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception: " + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}
	
	public PlantaDAO getPlantaDAO() {
		return new PlantaDAO(con);
	}
	
	public EjemplarDAO getEjemplarDAO() {
		return new EjemplarDAO(con);
	}
	
	public PersonaDAO getPersonaDAO() {
		return new PersonaDAO(con);
	}
	
	public MensajeDAO getMensajeDAO() {
		return new MensajeDAO(con);
	}
	
	


	public static ConexionBD getInstance() {
		if (connection == null)
			connection = new ConexionBD();
		return connection;
	}

	public static void CerrarConexion() {
		try {
			con.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
