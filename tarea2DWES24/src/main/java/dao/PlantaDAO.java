package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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
	public long insertar(Planta p) {
		try {
			ps = conex.prepareStatement("INSERT INTO plantas (codigo, nombrecomun, nombrecientifico) VALUES (?, ?, ?)");
			ps.setString(1, p.getCodigo());
			ps.setString(2, p.getNombrecomun());
			ps.setString(3, p.getNombrecientifico());
			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar en plantas: " + e.getMessage());
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
		List<Planta> listaPlantas = new ArrayList<>();
		String consulta = "SELECT * FROM plantas";

		try {

			if (this.conex == null || this.conex.isClosed()) {
				this.conex = ConexionBD.getConexion();
			}

			ps = conex.prepareStatement(consulta);
			rs = ps.executeQuery();

			while (rs.next()) {
				Planta planta = new Planta(rs.getString("codigo"), rs.getString("nombrecomun"),
						rs.getString("nombrecientifico"));
				listaPlantas.add(planta);
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener todas las plantas: " + e.getMessage());
			e.printStackTrace();
		}

		return listaPlantas;
	}

	@Override
	public boolean modificar(Planta p) {
		String consulta = "UPDATE plantas SET nombrecomun =?, nombrecientifico =? WHERE codigo =?";
		try {

			ps = conex.prepareStatement(consulta);
			ps.setString(1, p.getNombrecomun());
			ps.setString(2, p.getNombrecientifico());
			ps.setString(3, p.getCodigo());

			int resultadomodificacion = ps.executeUpdate();
			if (resultadomodificacion == 1)
				return true;
			else
				return false;

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean eliminar(Planta p) {

		String consulta = "DELETE FROM plantas WHERE codigo = ?";
		try {
			ps = conex.prepareStatement(consulta);
			ps.setString(1, p.getCodigo());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.println("Error al eliminar la planta: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	public boolean esunCodigo(String codigo) {
		String consulta = "SELECT * FROM plantas WHERE codigo = ?";
		ArrayList<String> entradaCodigo = new ArrayList<String>();
		try {
			ps = conex.prepareStatement(consulta);
			rs = ps.executeQuery();
			while (rs.next()) {
				entradaCodigo.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (entradaCodigo.contains(codigo.toUpperCase())) {
			return true;
		} else {
			return false;
		}

	}

	public boolean nombreComun(String codigo, String nombreNuevo) {
		String consulta = "UPDATE plantas SET nombrecomun = ? WHERE codigo = ?";
		try (Connection connection = ConexionBD.getConexion();
				PreparedStatement ps = connection.prepareStatement(consulta)) {
			ps.setString(1, nombreNuevo);
			ps.setString(2, codigo);
			int filas = ps.executeUpdate();
			return filas > 0;
		} catch (SQLException e) {
			System.out.println("Error al modificar el nombre común: " + e.getMessage());
			return false;
		}

	}

	public boolean nombreCientifico(String codigo, String nombreNuevo) {
		String consulta = "UPDATE plantas SET nombrecientifico = ? WHERE codigo = ?";
		try (Connection connection = ConexionBD.getConexion();
				PreparedStatement ps = connection.prepareStatement(consulta)) {
			ps.setString(1, nombreNuevo);
			ps.setString(2, codigo);
			int filas = ps.executeUpdate();
			return filas > 0;
		} catch (SQLException e) {
			System.out.println("Error al modificar el nombre cientifico: " + e.getMessage());
			return false;
		}
	}
}
