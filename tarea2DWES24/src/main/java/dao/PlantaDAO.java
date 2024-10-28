package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entidades.Planta;


public interface PlantaDAO {
	
	int insertar(Planta p);
	int modificar(Planta p);
	int eliminar(Planta p);
	Planta findById(int id);
	ArrayList<Planta> findByNombre(String nombre);
	List<Planta> findAll();
	
}
	
	

