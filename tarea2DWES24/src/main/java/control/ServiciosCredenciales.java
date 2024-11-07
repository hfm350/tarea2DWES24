package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.CredencialesDAO;
import dao.PersonaDAO;
import utils.ConexionBD;

public class ServiciosCredenciales {

	private Connection conex;
	private CredencialesDAO credencialesDao;
	private PersonaDAO personaDao;

	public ServiciosCredenciales() {
		this.conex = (Connection) ConexionBD.getConexion();
		credencialesDao = new CredencialesDAO(conex);
		personaDao = new PersonaDAO(conex);

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
}
