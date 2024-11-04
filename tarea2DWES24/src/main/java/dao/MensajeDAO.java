package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Mensaje;

public class MensajeDAO implements OperacionesCRUD<Mensaje> {

	Connection connex;

	public MensajeDAO(Connection connex) {
		if (this.connex == null)
			this.connex = connex;

	}

	@Override
	public int insertar(Mensaje elemento) {
		
		return 0;
	}

	@Override
	public Mensaje buscarPorID(long id) {
		
		return null;
	}

	@Override
	public Collection<Mensaje> busquedaDeTodos() {
		
		return null;
	}

	@Override
	public boolean modificar(Mensaje elemento) {

		return false;
	}

	@Override
	public boolean eliminar(Mensaje elemento) {

		return false;
	}

}