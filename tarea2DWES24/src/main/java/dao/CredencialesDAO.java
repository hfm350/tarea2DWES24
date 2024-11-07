package dao;

import entidades.Credenciales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CredencialesDAO implements OperacionesCRUD<Credenciales> {

	Connection connex;

	public CredencialesDAO(Connection connex) {
		if (this.connex == null)
			this.connex = connex;
	}

	@Override
	public long insertar(Credenciales c) {

		return 0;
	}

	@Override
	public Credenciales buscarPorID(long id) {

		return null;
	}

	@Override
	public Collection<Credenciales> busquedaDeTodos() {

		return null;
	}

	@Override
	public boolean modificar(Credenciales c) {

		return false;
	}

	@Override
	public boolean eliminar(Credenciales c) {

		return false;
	}




}
