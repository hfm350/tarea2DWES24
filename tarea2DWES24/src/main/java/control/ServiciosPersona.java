package control;

import java.util.Collection;

import dao.PersonaDAO;
import entidades.Persona;
import utils.ConexionBD;

public class ServiciosPersona {
	private ConexionBD connex;
	private PersonaDAO personaDAO;

	public ServiciosPersona() {
		connex = ConexionBD.getInstance();
		personaDAO = (PersonaDAO) connex.getPersonaDAO();
	}

	public long insertar(Persona p) {

		return personaDAO.insertar(p);
	}
	
	public boolean modificar(Persona p) {
		return personaDAO.modificar(p);
	}
	
	public boolean eliminar(Persona p) {
		return personaDAO.eliminar(p);
	}
	
	public Collection<Persona> busquedaDeTodos(){
		return personaDAO.busquedaDeTodos();
	}
	
	public Persona buscarPorId(long id) {
		return personaDAO.buscarPorID(id);
	}
}