package fachada;

import java.sql.Connection;
import java.util.Scanner;

import control.Controlador;
import control.ServiciosCredenciales;
import control.ServiciosEjemplar;
import control.ServiciosMensaje;
import control.ServiciosPersona;
import control.ServiciosPlanta;
import utils.ConexionBD;

public class FachadaPersonal {
private static FachadaPersonal portal;
	
	Connection conex = ConexionBD.getConexion();
	Controlador controlador = Controlador.getServicios();

	ServiciosEjemplar ejServ = controlador.getServiciosEjemplar();
	ServiciosPlanta servPlanta = controlador.getServiciosPlanta();
	ServiciosMensaje servMensaje = controlador.getServiciosMensaje();
	ServiciosPersona servPersona = controlador.getServiciosPersona();
	ServiciosCredenciales servCredenciales = controlador.getServiciosCredenciales();
	
	public static FachadaPersonal getPortal() {
		if (portal == null) {
			portal = new FachadaPersonal();
		}
		return portal;
		
	}

	Scanner sc = new Scanner(System.in);
	
	public void menuPersonal() {
		System.out.println("Hola");
	}

	
}
