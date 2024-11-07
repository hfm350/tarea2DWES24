package fachada;

import java.sql.Connection;

import utils.ConexionBD;

public class Principal {

	public static void main(String[] args) {
	    Connection connection = (Connection) ConexionBD.getConexion();
	    
	        FachadaInvitado portal = FachadaInvitado.getPortal();
	        portal.mostrarMenuInvitado();
	    }
	

}