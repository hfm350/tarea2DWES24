package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import dao.CredencialesDAO;
import dao.PersonaDAO;
import entidades.Credenciales;
import entidades.Persona;
import utils.ConexionBD;

public class ServiciosCredenciales {

	private Connection conex;
	private CredencialesDAO credencialesDAO;
	private PersonaDAO personaDAO;

	public ServiciosCredenciales() {
		this.conex = (Connection) ConexionBD.getConexion();
		credencialesDAO = new CredencialesDAO(conex);
		personaDAO = new PersonaDAO(conex);

	}
	
	public int insertar(String usuario, String contrasena, Long idPersona) {
		return credencialesDAO.insertar(usuario, contrasena, idPersona);
	}


	public boolean autenticar(String usuario, String contraseña) {
		boolean autenticado = false;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = conex.prepareStatement("SELECT password FROM credenciales WHERE usuario = ?");
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
		}

		return autenticado;

	}

	public boolean existeUsuario(String usuario) {

		String sqlString = "SELECT COUNT(*) FROM CREDENCIALES WHERE usuario = ?";

		try {
			PreparedStatement pStatement = conex.prepareStatement(sqlString);
			pStatement.setString(1, usuario);
			ResultSet rs = pStatement.executeQuery();

			if (rs.next() && rs.getInt(1) > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}
	
	
	
}
