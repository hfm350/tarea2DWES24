package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public long insertar(Persona p) {
		try {
			ps = conex.prepareStatement("INSERT INTO personas (id,nombre,email) VALUES(?,?,?)");
			ps.setLong(1, p.getId());
			ps.setString(2, p.getNombre());
			ps.setString(3, p.getEmail());
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al registrar una persona " + e.getMessage());
			e.getStackTrace();
		}
		return 0;
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

	@Override
	public Collection<Persona> busquedaDeTodos() {
		HashSet<Persona> personas = new HashSet<>();
        try {
            ps = conex.prepareStatement("SELECT * FROM personas");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Persona persona = new Persona(rs.getLong("id"), rs.getString("nombre"), rs.getString("email"));
                personas.add(persona);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener todas las personas: " + e.getMessage());
        }
        return personas;
		
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
