package dao;

import java.sql.Connection;

import java.util.Collection;

import entidades.Planta;

public class PlantaDAO implements OperacionesCRUD<Planta> {

	Connection conex;

	public PlantaDAO(Connection conex) {
		if (this.conex == null)
			this.conex = conex;
	}

	@Override
	public boolean insertarConID(Planta elemento) {

		return false;
	}

	@Override
	public Planta buscarPorID(long id) {

		return null;
	}

	@Override
	public Collection<Planta> busquedaDeTodos() {

		return null;
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