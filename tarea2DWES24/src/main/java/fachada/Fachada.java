package fachada;

import control.Controlador;
import control.ServiciosEjemplar;
import control.ServiciosMensaje;
import control.ServiciosPersona;
import control.ServiciosPlanta;
import utils.ConexionBD;

public class Fachada {

	private static Fachada portal;

	ConexionBD conex = (ConexionBD) ConexionBD.getConexion();
	Controlador controlador = Controlador.getServicios();

	ServiciosEjemplar ejServ = controlador.getServiciosEjemplar();
	ServiciosPlanta plantaServ = controlador.getServiciosPlanta();
	ServiciosMensaje mensajeServ = controlador.getServiciosMensaje();
	ServiciosPersona personaServ = controlador.getServiciosPersona();
	

	public static Fachada getPortal() {
			if (portal==null)
				portal=new Fachada();
			return portal;
			
	}	
		
}
