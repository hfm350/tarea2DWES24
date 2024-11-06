package control;

import java.util.Collection;

import dao.EjemplarDAO;
import entidades.Ejemplar;
import utils.ConexionBD;

public class ServiciosEjemplar {
	private ConexionBD connex;
	private EjemplarDAO ejemplarDAO;

	public ServiciosEjemplar() {
		connex = ConexionBD.getInstance();
		ejemplarDAO = (EjemplarDAO) connex.getEjemplarDAO();
	}

	public long insertar(Ejemplar e) {

		return ejemplarDAO.insertar(e);
	}
	
	public boolean modificar(Ejemplar e) {
		return ejemplarDAO.modificar(e);
	}
	
	public boolean eliminar(Ejemplar e) {
		return ejemplarDAO.eliminar(e);
	}
	
	public Collection<Ejemplar> busquedaDeTodos(){
		return ejemplarDAO.busquedaDeTodos();
	}
	
	public Ejemplar buscarPorId(long id) {
		return ejemplarDAO.buscarPorID(id);
	}
}
