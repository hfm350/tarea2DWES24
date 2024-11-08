package control;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;

import dao.PlantaDAO;
import entidades.Planta;
import utils.ConexionBD;

public class ServiciosPlanta {
	private ConexionBD connex;
	private PlantaDAO plantaDAO;

	public ServiciosPlanta() {
		connex = ConexionBD.getInstance();
		plantaDAO = (PlantaDAO) connex.getPlantaDAO();
	}

	public boolean validarPlanta(Planta p) {
		boolean ret = false;
		if (p.getCodigo().isEmpty())
			return false;
		if (p.getCodigo().length() < 3 || p.getCodigo().length() > 20)
			return false;

		return true;
	}

	public boolean validarCodigo(String codigo) {
		if (codigo == null || codigo.isEmpty()) {
			return false;
		}
		if (!codigo.matches("^[a-zA-Z]+$")) {
			return false;
		}
		return true;
	}

	public boolean esunCodigo(String codigo) {
		return plantaDAO.esunCodigo(codigo);
	}
	
	public boolean nombreComun(String codigo, String nombreNuevo) {
		return plantaDAO.nombreComun(codigo, nombreNuevo);
	}
	
	public boolean nombreCientifico(String codigo, String nombreNuevo) {
		return plantaDAO.nombreCientifico(codigo, nombreNuevo);
	}

	public long insertar(Planta p) {

		return plantaDAO.insertar(p);
	}

	public boolean modificar(Planta p) {
		return plantaDAO.modificar(p);
	}

	public boolean eliminar(Planta p) {
		return plantaDAO.eliminar(p);
	}

	public Collection<Planta> busquedaDeTodos() {
		return plantaDAO.busquedaDeTodos();
	}

	public Planta buscarPorId(long id) {
		return plantaDAO.buscarPorID(id);
	}

}
