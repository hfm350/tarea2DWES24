package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.CredencialesDAO;
import dao.PersonaDAO;
import utils.ConexionBD;

public class ServiciosCredenciales {

    private ConexionBD conex;
    private CredencialesDAO credencialesDao;
    private PersonaDAO personaDao;

    public ServiciosCredenciales() {
        conex = ConexionBD.getInstance();
        credencialesDao = conex.getCredencialesDAO();
        personaDao = conex.getPersonaDAO();
       
    }

    public boolean autenticar(String usuario, String contraseña) {
        boolean autenticado = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        

        try {
            ps = conex.prepareStatement("SELECT password FROM usuarios WHERE nombre_usuario = ?");
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                String contraseñaBD = rs.getString("password");
                if (contraseña.equals(contraseñaBD)) {
                    autenticado = true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al autenticar usuario: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos de autenticación: " + e.getMessage());
            }
        }

        return autenticado;
    }
}

