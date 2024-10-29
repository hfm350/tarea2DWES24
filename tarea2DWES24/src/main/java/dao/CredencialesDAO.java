package dao;

import entidades.Credenciales;

import java.util.ArrayList;
import java.util.List;

public interface CredencialesDAO {
	
	int insertar(Credenciales c);
	int modificar(Credenciales c);
	int eliminar(Credenciales c);

	Credenciales findById(int id);
	ArrayList<Credenciales> findByNombre(String nombre);
	List<Credenciales> findAll();
}
