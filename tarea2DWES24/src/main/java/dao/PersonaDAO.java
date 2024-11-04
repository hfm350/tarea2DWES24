package dao;

import java.sql.Connection;
import java.util.Collection;

import entidades.Persona;

public class PersonaDAO implements OperacionesCRUD<Persona> {

	Connection conex;

	public PersonaDAO(Connection conex) {
		if (this.conex == null)
			this.conex = conex;
	}

	@Override
	public boolean insertar(Persona elemento) {

		return false;
	}

	@Override
	public Persona buscarPorID(long id) {

		return null;
	}

	@Override
	public Collection<Persona> busquedaDeTodos() {

		return null;
	}

	@Override
	public boolean modificar(Persona elemento) {

		return false;
	}

	@Override
	public boolean eliminar(Persona elemento) {

		return false;
	}

}
