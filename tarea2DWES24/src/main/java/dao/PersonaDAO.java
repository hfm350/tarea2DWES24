package dao;

import entidades.Persona;

import java.util.ArrayList;
import java.util.List;


public interface PersonaDAO {
	
	int insertar(Persona p);
	int modificar (Persona p);
	int eliminar (Persona p);
	
	Persona findById(int id);
	ArrayList<Persona> findByNombre(String nombre);
	List<Persona> findAll();
}
