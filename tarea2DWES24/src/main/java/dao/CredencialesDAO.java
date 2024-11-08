package dao;

import entidades.Credenciales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CredencialesDAO  {

	Connection connex;
	PreparedStatement ps;

	public CredencialesDAO(Connection connex) {
		if (this.connex == null)
			this.connex = connex;
	}

	
	public int insertar(String usuario, String contraseña, Long idPersona) {
		int filas = 0;
		String insertarCredenciales = "INSERT INTO credenciales (usuario, password, idPersona) VALUES (?, ?, ?)";
		try (PreparedStatement ps = connex.prepareStatement(insertarCredenciales,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, usuario);
			ps.setString(2, contraseña);
			ps.setLong(3, idPersona);
			filas = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar las credenciales.");
		}
		return filas;

	}

	public boolean eliminar(Long idPersona) {

		return false;
	}


}
