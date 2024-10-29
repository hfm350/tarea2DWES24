package dao;

import entidades.Planta;

import java.util.ArrayList;
import java.util.List;


public interface PlantaDAO {
	
	
	
	int insertar(Planta p);
	int modificar(Planta p);
	int eliminar(Planta p);
	
	Planta findById(int id);
	ArrayList<Planta> findByNombre(String nombre);
	List<Planta> findAll();
	
}
	
	

