package dao;

import java.util.Collection;

import entidades.Persona;

public interface OperacionesCRUD<T> {

	public long insertar(T elemento);

	public T buscarPorID(long id);

	public Collection<T> busquedaDeTodos();

	public boolean modificar(T elemento);

	public boolean eliminar(T elemento);

	
	
}
