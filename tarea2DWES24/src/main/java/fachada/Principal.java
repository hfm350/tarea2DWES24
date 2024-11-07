package fachada;

import java.sql.Connection;

import utils.ConexionBD;

public class Principal {

	public static void main(String[] args) {
		
		
		FachadaInvitado portal = FachadaInvitado.getPortal();
		
		Connection connection = ConexionBD.getConexion();

		
	}

}