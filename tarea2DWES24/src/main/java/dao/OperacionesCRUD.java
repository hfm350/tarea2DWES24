package dao;

import java.util.Collection;

public interface OperacionesCRUD<T> {

	public int insertar( T elemento);

	public T buscarPorID(long id);

	public Collection<T> busquedaDeTodos();

	public boolean modificar(T elemento);

	public boolean eliminar(T elemento);

}
