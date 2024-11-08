package control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import dao.PersonaDAO;
import entidades.Persona;
import utils.ConexionBD;

public class ServiciosPersona {
	private ConexionBD connex;
	private PersonaDAO personaDAO;

	public ServiciosPersona() {
		connex = ConexionBD.getInstance();
		personaDAO = (PersonaDAO) connex.getPersonaDAO();
	}

	public boolean autenticar(String persona, String contraseña) {
		boolean autenticado = false;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			ps = connex.prepareStatement("SELECT password FROM credenciales WHERE usuario = ?");
			ps.setString(1, persona);

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

	

	public long insertar(Persona p) {

		return personaDAO.insertar(p);
	}

	public boolean modificar(Persona p) {
		return personaDAO.modificar(p);
	}

	public boolean eliminar(Persona p) {
		return personaDAO.eliminar(p);
	}

	public Collection<Persona> busquedaDeTodos() {
		return personaDAO.busquedaDeTodos();
	}

	public Persona buscarPorId(long id) {
		return personaDAO.buscarPorID(id);
	}
	
	public boolean existeEmail(String email) {
		try {
			String query = "SELECT COUNT(*) FROM personas WHERE email = ?";
			PreparedStatement ps = connex.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Persona buscarPorNombre(String nombre) {
	    Persona persona = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        String sql = "SELECT * FROM personas WHERE nombre = ?";
	        ps = connex.prepareStatement(sql);
	        ps.setString(1, nombre);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            persona = new Persona(rs.getLong("id"), rs.getString("nombre"), rs.getString("email"));
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al buscar persona: " + e.getMessage());
	        e.printStackTrace();
	    } 
	    

	    return persona;
	}

}