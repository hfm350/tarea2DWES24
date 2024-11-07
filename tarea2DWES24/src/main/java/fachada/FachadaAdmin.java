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

public class FachadaAdmin {
	private static FachadaAdmin portal;
	
	Connection conex = ConexionBD.getConexion();
	Controlador controlador = Controlador.getServicios();

	ServiciosEjemplar ejServ = controlador.getServiciosEjemplar();
	ServiciosPlanta servPlanta = controlador.getServiciosPlanta();
	ServiciosMensaje servMensaje = controlador.getServiciosMensaje();
	ServiciosPersona servPersona = controlador.getServiciosPersona();
	ServiciosCredenciales servCredenciales = controlador.getServiciosCredenciales();
	
	public static FachadaAdmin getPortal() {
		if (portal == null) {
			portal = new FachadaAdmin();
		}
		return portal;
	}

	Scanner sc = new Scanner(System.in);
	
	public void menuAdmin() {
		System.out.println("Hola");
	}
}
