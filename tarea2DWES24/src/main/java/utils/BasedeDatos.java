package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

import dao.EjemplarDAO;
import dao.PlantaDAO;
import daoImpl.PlantaDAOImpl;

public class BasedeDatos {
	private Connection con;

	private static BasedeDatos f;

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

	public EjemplarDAO getEjemplarDAO() {
		return new EjemplarDAOImpl(con);
	}

	public PlantaDAO getPlantaDAO() {
		return new PlantaDAOImpl(con);
	}

	public static BasedeDatos getCon() {
		if (f == null)
			f = new BasedeDatos();
		return f;
	}
}
