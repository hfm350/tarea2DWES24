package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import entidades.Planta;
import utils.ConexionBD;

public class PlantaDAO implements OperacionesCRUD<Planta> {

	private Connection conex;
	private PreparedStatement ps;
	private ResultSet rs;

	public PlantaDAO(Connection conex) {
		this.conex = conex;
	}

	@Override
	public int insertar(Planta elemento) {
		try {
			ps = conex.prepareStatement("insert into plantas (codigo, nombrecomun, nombrecientifico) values (?,?,?)");
			ps.setString(1, elemento.getCodigo());
			ps.setString(2, elemento.getNombrecomun());
			ps.setString(3, elemento.getNombrecientifico());
		} catch (SQLException e) {
			System.out.println("Error al insertar en plantas" + e.getMessage());
		}
		return 0;
	}

	@Override
	public Planta buscarPorID(long id) {
		String consulta = "SELECT * FROM planta WHERE codigo = ?";
		Planta planta = null;
		try {
			PreparedStatement pstmt = conex.prepareStatement(consulta);
			pstmt.setLong(1, id);
			ResultSet result = pstmt.executeQuery();

			while (result.next()) {
				String codigo = result.getString("codigo");
				String nombreComun = result.getString("nombrecomun");
				String nombreCientifico = result.getString("nombrecientifico");
				planta = new Planta(codigo, nombreComun, nombreCientifico);
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception: " + e.getMessage());
			e.printStackTrace();
		}
		return planta;
	}

	@Override
	public Collection<Planta> busquedaDeTodos() {
		HashSet<Planta> todas = new HashSet<>(); 
	    String consulta = "SELECT * FROM plantas"; 
	    try {
	        if (this.conex == null || this.conex.isClosed()) {
	            this.conex = ConexionBD.getConexion();
	        }
	        PreparedStatement ps = conex.prepareStatement(consulta);
	        ResultSet resultado = ps.executeQuery();
	        while (resultado.next()) {
	            Planta planta = new Planta(                
	                resultado.getString("codigo"),           
	                resultado.getString("nombrecomun"),      
	                resultado.getString("nombrecientifico"));
	            todas.add(planta); 
	        }
	        conex.close();
	    } catch (SQLException e) {
	        System.out.println("Error al obtener todas las plantas: " + e.getMessage());
	        e.printStackTrace();
	    
	    }
	    
	    return todas;
		
	}

	@Override
	public boolean modificar(Planta elemento) {

		return false;
	}

	@Override
	public boolean eliminar(Planta elemento) {

		return false;
	}
}