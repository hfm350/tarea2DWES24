package dao;

import entidades.Credenciales;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CredencialesDAO implements OperacionesCRUD<Credenciales> {

	Connection connex;

	public CredencialesDAO() {
		if (this.connex == null)
			this.connex = connex;
	}

	@Override
	public boolean insertar(Credenciales elemento) {

		return false;
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
	public boolean modificar(Credenciales elemento) {

		return false;
	}

	@Override
	public boolean eliminar(Credenciales elemento) {

		return false;
	}

}
