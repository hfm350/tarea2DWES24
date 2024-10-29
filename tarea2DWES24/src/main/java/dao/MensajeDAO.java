package dao;

import java.util.ArrayList;
import java.util.List;

import entidades.Mensaje;

public interface MensajeDAO {

	int insertar(Mensaje c);

	int modificar(Mensaje c);

	int eliminar(Mensaje c);

	Mensaje findById(int id);

	ArrayList<Mensaje> findByNombre(String nombre);

	List<Mensaje> findAll();
}