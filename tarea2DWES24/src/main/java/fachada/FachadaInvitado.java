package fachada;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import control.Controlador;
import control.ServiciosCredenciales;
import control.ServiciosEjemplar;
import control.ServiciosMensaje;
import control.ServiciosPersona;
import control.ServiciosPlanta;
import entidades.Planta;
import utils.ConexionBD;

public class FachadaInvitado {

	private static FachadaInvitado portal;

	
	Connection conex = ConexionBD.getConexion();
	Controlador controlador = Controlador.getServicios();

	ServiciosEjemplar ejServ = controlador.getServiciosEjemplar();
	ServiciosPlanta servPlanta = controlador.getServiciosPlanta();
	ServiciosMensaje servMensaje = controlador.getServiciosMensaje();
	ServiciosPersona servPersona = controlador.getServiciosPersona();
	ServiciosCredenciales servCredenciales = controlador.getServiciosCredenciales();

	public static FachadaInvitado getPortal() {
		if (portal == null) {
			portal = new FachadaInvitado();
		}
		return portal;
	}

	Scanner sc = new Scanner(System.in);

	public void menuInvitado() {
		System.out.println("Bienvenido al menu de gestión de un vivero");
		System.out.println("\nPULSE");
		System.out.println("1 -  Ver Plantas.");
		System.out.println("2 -  Iniciar sesión.");
		System.out.println("9 -  Cerrar aplicación.");
	}

	public void mostrarMenuInvitado() {
		boolean AbiertaSesion = false;
		int opcion = 0;

		do {
			this.menuInvitado();
			try {
				opcion = sc.nextInt();
				sc.nextLine();

				if (opcion < 0 || opcion > 2) {
					System.err.println("Opción fuera de rango. Inténtelo de nuevo.");
					continue;
				}

				switch (opcion) {
				case 1:
					List<Planta> plantas = (List<Planta>) servPlanta.busquedaDeTodos();
					if (plantas.isEmpty()) {
						System.out.println("No hay plantas almacenadas");
					} else {
						System.out.println("Lista de plantas:");
						for (int i = 0; i < plantas.size(); i++) {
							System.out.println((i + 1) + "ª " + plantas.get(i));
						}
					}
					break;

				case 2:
					System.out.print("Introduce tu usuario: ");
					String usuario = sc.nextLine();
					System.out.print("Introduce tu contraseña: ");
					String contraseña = sc.nextLine();

					if (servCredenciales.autenticar(usuario, contraseña)) {
						AbiertaSesion = true;
						System.out.println("Sesión iniciada correctamente.");
					} else {
						System.err.println("Usuario o contraseña incorrectos.");
					}
					break;

				case 9:
					System.out.println("Cerrando el menú de invitado.");
					break;
				}

			} catch (InputMismatchException e) {
				System.err.println("Entrada inválida. Por favor, ingrese un número entero.");
				sc.next();
			}
		} while (!AbiertaSesion && opcion != 9);

		sc.close();
	}
}
