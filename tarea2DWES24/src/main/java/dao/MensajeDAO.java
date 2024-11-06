package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Ejemplar;
import entidades.Mensaje;
import entidades.Persona;
import entidades.Planta;
import utils.ConexionBD;

public class MensajeDAO implements OperacionesCRUD<Mensaje> {

	Connection connex;
	private Connection conex;
	private PreparedStatement ps;
	private ResultSet rs;

	public MensajeDAO(Connection connex) {
		if (this.connex == null)
			this.connex = connex;

	}

	@Override
	public long insertar(Mensaje m) {
		try {
			ps = conex.prepareStatement(
					"INSERT INTO mensajes (id, fechahora, mensaje, idejemplar, idpersona) VALUES(?,?,?,?,?)");
			ps.setLong(1, m.getId());
			ps.setTimestamp(2, Timestamp.valueOf(m.getFechahora()));
			ps.setString(3, m.getMensaje());
			ps.setLong(4, m.getIdEjemplar());
			ps.setLong(5, m.getIdPersona());
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar un mensaje" + e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Mensaje buscarPorID(long id) {
	    Mensaje mensaje = null;
	    ResultSet rs = null;

	    try {
	        ps = conex.prepareStatement("SELECT * FROM mensajes WHERE id = ?");
	        ps.setLong(1, id);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            mensaje = new Mensaje(
	                rs.getLong("id"),
	                rs.getTimestamp("fechahora").toLocalDateTime(), 
	                rs.getString("mensaje"),
	                rs.getLong("idEjemplar"),
	                rs.getLong("idPersona")
	            );
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al buscar mensaje: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return mensaje;
	}

	@Override
	public Collection<Mensaje> busquedaDeTodos() {

		return null;
	}

	@Override
	public boolean modificar(Mensaje m) {
		try {
			ps = conex.prepareStatement(
					"UPDATE mensaje SET fechahora = ?, mensaje = ?, idEjemplar = ?, idPersona = ? WHERE id = ?");
			ps.setTimestamp(1, Timestamp.valueOf(m.getFechahora()));
			ps.setString(2, m.getMensaje());
			ps.setLong(3, m.getIdEjemplar());
			ps.setLong(4, m.getIdPersona());
			ps.setLong(5, m.getId());

			if (ps.executeUpdate() > 0) {
				System.out.println("Mensaje modificado correctamente.");
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error al modificar el mensaje: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean eliminar(Mensaje m) {
		try {
			ps = conex.prepareStatement("DELETE FROM mensaje WHERE id = ?");
			ps.setLong(1, m.getId());

			if (ps.executeUpdate() > 0) {
				System.out.println("Mensaje eliminado correctamente.");
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Error al eliminar el mensaje: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

}