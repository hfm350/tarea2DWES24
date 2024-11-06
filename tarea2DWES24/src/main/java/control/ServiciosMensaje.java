package control;

import java.util.Collection;

import dao.MensajeDAO;
import entidades.Mensaje;
import utils.ConexionBD;

public class ServiciosMensaje {
	private ConexionBD connex;
	private MensajeDAO mensajeDAO;

	public ServiciosMensaje() {
		connex = ConexionBD.getInstance();
		mensajeDAO = (MensajeDAO) connex.getMensajeDAO();
	}

	public long insertar(Mensaje m) {

		return mensajeDAO.insertar(m);
	}
	
	public boolean modificar(Mensaje p) {
		return mensajeDAO.modificar(p);
	}
	
	public boolean eliminar(Mensaje p) {
		return mensajeDAO.eliminar(p);
	}
	
	public Collection<Mensaje> busquedaDeTodos(){
		return mensajeDAO.busquedaDeTodos();
	}
	
	public Mensaje buscarPorId(long id) {
		return mensajeDAO.buscarPorID(id);
	}
}
