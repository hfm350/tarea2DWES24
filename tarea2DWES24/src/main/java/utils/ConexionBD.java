package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import dao.*;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ConexionBD {
	private static Connection con;
	private static ConexionBD bd;

	private ConexionBD() {
		Properties prop = new Properties();
		MysqlDataSource m = new MysqlDataSource();

		try (InputStream fis = ConexionBD.class.getClassLoader().getResourceAsStream("db.properties")) {
			if (fis == null) {
				throw new IOException("No se pudo encontrar el archivo db.properties");
			}
			prop.load(fis);

			m.setUrl(prop.getProperty("url"));
			m.setUser(prop.getProperty("usuario"));
			m.setPassword(prop.getProperty("password"));

			con = m.getConnection();
		} catch (IOException e) {
			System.out.println("Error al leer las propiedades del archivo db.properties: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos: " + e.getMessage());
		}
	}

	public static Connection getConexion() {
		if (con == null || isClosed(con)) {
			bd = new ConexionBD();
		}
		return con;
	}

	public static ConexionBD getInstance() {
		if (bd == null) {
			bd = new ConexionBD();
		}
		return bd;
	}

	// Cerramos la conexión
	public static void cerrarConexion() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			System.err.println("Error al cerrar la conexión a la base de datos: " + e.getMessage());
		}
	}

	// Verificamos si la conexión se cerrada
	private static boolean isClosed(Connection con) {
		try {
			return con == null || con.isClosed();
		} catch (SQLException e) {
			System.err.println("Error al verificar si la conexión está cerrada: " + e.getMessage());
			return true;
		}
	}

	// Obtener DAO
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

	public CredencialesDAO getCredencialesDAO() {
		return new CredencialesDAO(con);
	}

	// Método para preparar consultas
	public PreparedStatement prepareStatement(String query) throws SQLException {
		return con.prepareStatement(query);
	}
}
