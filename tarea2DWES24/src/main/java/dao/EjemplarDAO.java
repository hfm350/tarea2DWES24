package dao;

import entidades.Ejemplar;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EjemplarDAO implements OperacionesCRUD<Ejemplar> {

	Connection connex;

	public EjemplarDAO(Connection connex) {
		if (this.connex == null)
			this.connex = connex;
		
		
	}

	@Override
	public boolean insertarConID(Ejemplar elemento) {
		
		return false;
	}

	@Override
	public Ejemplar buscarPorID(long id) {
		
		return null;
	}

	@Override
	public Collection<Ejemplar> busquedaDeTodos() {
		
		return null;
	}

	@Override
	public boolean modificar(Ejemplar elemento) {
		
		return false;
	}

	@Override
	public boolean eliminar(Ejemplar elemento) {
		
		return false;
	}

}