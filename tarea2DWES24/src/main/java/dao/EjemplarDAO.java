package dao;


import entidades.Ejemplar;

import java.util.ArrayList;
import java.util.List;


public interface EjemplarDAO {
	
		int insertar(Ejemplar e);
		int modificar(Ejemplar e);
		int eliminar(Ejemplar e);
		
		Ejemplar findById(int id);
		ArrayList<Ejemplar> findByNombre(String nombre);
		List<Ejemplar> findAll();
	}