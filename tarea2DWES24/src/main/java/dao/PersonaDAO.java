package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import entidades.Credenciales;
import entidades.Persona;
import entidades.Planta;

public class PersonaDAO implements OperacionesCRUD<Persona> {

	private Connection conex;
	private PreparedStatement ps;
	private ResultSet rs;

	public PersonaDAO(Connection conex) {
		if (this.conex == null)
			this.conex = conex;
	}

	@Override
	public long insertar(Persona persona) {
	    try {
	        String sql = "INSERT INTO personas (nombre, email) VALUES (?, ?)";
	        ps = conex.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  
	        ps.setString(1, persona.getNombre());
	        ps.setString(2, persona.getEmail());
	        int rowsAffected = ps.executeUpdate();
	        
	        if (rowsAffected > 0) {
	            ResultSet rs = ps.getGeneratedKeys(); 
	            if (rs.next()) {
	                return rs.getLong(1);  
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al insertar persona: " + e.getMessage());
	    }
	    return 0; // Si no se insertó correctamente
	}


	@Override
	public Persona buscarPorID(long id) {
		Persona persona = null;
		try {
			ps = conex.prepareStatement("SELECT * FROM personas WHERE id = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				persona = new Persona(rs.getLong("id"), rs.getString("nombre"), rs.getString("email"));
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Error al buscar persona: " + e.getMessage());
		}
		return persona;

	}
	
	public long obtenerIdPersonaAutenticada(String nombreUsuario) {
	    long idPersona = -1;
	    String query = "SELECT p.id FROM personas p JOIN credenciales c ON p.id = c.idPersona WHERE c.usuario = ?";
	    
	    try (PreparedStatement statement = conex.prepareStatement(query)) {
	        statement.setString(1, nombreUsuario);
	        
	        try (ResultSet resultado = statement.executeQuery()) {
	            if (resultado.next()) {
	                idPersona = resultado.getLong("id");
	            }
	        }
	    } catch (SQLException ex) {
	        System.err.println("Error al buscar el ID para el usuario: " + nombreUsuario + " - " + ex.getMessage());
	    }
	    
	    return idPersona;
	}

	

	@Override
	public Collection<Persona> busquedaDeTodos() {
		ArrayList<Persona> todas = new ArrayList<Persona>(); 
		try {
			ps = conex.prepareStatement("SELECT * FROM personas");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Persona p = new Persona(rs.getLong("id"), rs.getString("nombre"), rs.getString("email"));
				todas.add(p);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("Error al obtener todas las personas: " + e.getMessage());
		}
		return todas;

	}

	@Override
	public boolean modificar(Persona p) {
		try {
			ps = conex.prepareStatement("UPDATE personas SET nombre = ?, email = ? WHERE id = ?");
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getEmail());
			ps.setLong(3, p.getId());

			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error al modificar persona: " + e.getMessage());
		}

		return false;
	}

	@Override
	public boolean eliminar(Persona p) {
		boolean resultado = false;
		try {
			ps = conex.prepareStatement("DELETE FROM personas WHERE id = ?");
			ps.setLong(1, p.getId());

			if (ps.executeUpdate() > 0) {
				resultado = true;
			}
		} catch (SQLException e) {
			System.out.println("Error al eliminar persona: " + e.getMessage());

		}
		return resultado;
	}

}
