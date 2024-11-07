package utils;

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

    private static ConexionBD connection = null;

    private ConexionBD() {
        Properties prop = new Properties();
        MysqlDataSource m = new MysqlDataSource();
        try {
            
            InputStream fis = getClass().getClassLoader().getResourceAsStream("db.properties");
            if (fis == null) {
                System.err.println("No se pudo encontrar el archivo db.properties");
                return;
            }
            prop.load(fis);
            m.setUrl(prop.getProperty("url"));
            m.setPassword(prop.getProperty("password"));
            m.setUser(prop.getProperty("usuario"));
            con = m.getConnection();
        } catch (IOException e) {
            System.out.println("Error al leer las propiedades del archivo db.properties: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public static Connection getConexion() {
        try {
            if (con == null || con.isClosed()) {
                Properties propiedades = new Properties();
                MysqlDataSource m = new MysqlDataSource();
                
                InputStream fis = ConexionBD.class.getClassLoader().getResourceAsStream("db.properties");
                if (fis == null) {
                    System.err.println("No se pudo encontrar el archivo db.properties");
                    return null;
                }
                propiedades.load(fis);

                m.setUrl(propiedades.getProperty("url"));
                m.setPassword(propiedades.getProperty("password"));
                m.setUser(propiedades.getProperty("usuario"));
                con = m.getConnection();
            }
            return con;
        } catch (IOException e) {
            System.out.println("Error al leer las propiedades del archivo db.properties: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return con;
    }

    public static ConexionBD getInstance() {
        if (connection == null) {
            connection = new ConexionBD();
        }
        return connection;
    }

    public static void CerrarConexion() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PreparedStatement prepareStatement(String string) {
        return null;
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

    public CredencialesDAO getCredencialesDAO() {
        return new CredencialesDAO(con);
    }
}
