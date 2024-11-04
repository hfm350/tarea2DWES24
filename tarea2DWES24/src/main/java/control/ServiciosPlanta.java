package control;

import dao.PlantaDAO;
import entidades.Planta;
import utils.ConexionBD;

public class ServiciosPlanta {
	private ConexionBD connex;
	private PlantaDAO plantaDAO;
	
	public ServiciosPlanta() {
		connex = ConexionBD.getInstance();
		plantaDAO=(PlantaDAO)connex.getConexion();
	}
	
	
	public boolean validarPlanta(Planta p) {
		boolean ret = false;
		if(p.getCodigo().isEmpty()) return false;
		if(p.getCodigo().length()<3 || p.getCodigo().length()>20) return false;
		
		return true;
	}
}
