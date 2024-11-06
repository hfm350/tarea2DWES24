package dao;

import entidades.Ejemplar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EjemplarDAO implements OperacionesCRUD<Ejemplar> {

	Connection connex;
	private Connection conex;
	private PreparedStatement ps;
	private ResultSet rs;

	public EjemplarDAO(Connection connex) {
		if (this.connex == null)
			this.connex = connex;

	}

	@Override
	public long insertar(Ejemplar ej) {
		int resultado = 0;
		try {
			ps = conex.prepareStatement("INSERT INTO ejemplar(id, nombre, codigo_planta) VALUES (?, ?, ?)");
			ps.setLong(1, ej.getId());
			ps.setString(2, ej.getNombre());
			ps.setString(3, ej.getPlanta());

			resultado = ps.executeUpdate();
			System.out.println("Ejemplar insertado correctamente.");
		} catch (SQLException e) {
			System.out.println("Error al insertar el ejemplar: " + e.getMessage());
		}

		return resultado;
	}

	@Override
	public Ejemplar buscarPorID(long id) {
		Ejemplar ejemplar = null;
        try {
            ps = conex.prepareStatement("SELECT * FROM ejemplares WHERE id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ejemplar = new Ejemplar(rs.getLong("id"), 
                		rs.getString("nombre"), 
                		rs.getString("idPlanta"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar ejemplares: " + e.getMessage());
        }
        return ejemplar;
    }
	


	@Override
	public Collection<Ejemplar> busquedaDeTodos() {
	    HashSet<Ejemplar> ejemplares = new HashSet<>();
	    ResultSet rs = null;

	    try {
	        ps = conex.prepareStatement("SELECT * FROM ejemplares");
	        rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            Ejemplar ejemplar = new Ejemplar(
	                rs.getLong("id"),
	                rs.getString("nombre"),
	                rs.getString("idPlanta") 
	            );
	            ejemplares.add(ejemplar);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener todos los ejemplares: " + e.getMessage());
	    }
	    return ejemplares;
	}

	@Override
	public boolean modificar(Ejemplar ej) {
		boolean resultado = false;
        try {
            ps = conex.prepareStatement("UPDATE ejemplar SET nombre = ?, codigo_planta = ? WHERE id = ?");
            ps.setString(1, ej.getNombre());
            ps.setString(2, ej.getPlanta());
            ps.setLong(3, ej.getId());
            
            resultado = true;
            System.out.println("Ejemplar modificado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al modificar el ejemplar: " + e.getMessage());
        }
        
		return false;
	}

	@Override
	public boolean eliminar(Ejemplar ej) {
	    try {
	        ps = conex.prepareStatement("DELETE FROM ejemplares WHERE codigo = ?");
	        ps.setLong(1, ej.getId());

	        if (ps.executeUpdate() > 0) {  
	            System.out.println("Ejemplar eliminado correctamente.");
	            return true;
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al eliminar ejemplar: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return false;
	}

}