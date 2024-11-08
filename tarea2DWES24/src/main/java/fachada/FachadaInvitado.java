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

	Connection conex = (Connection) ConexionBD.getConexion();

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

	FachadaAdmin portalAdmin = FachadaAdmin.getPortal();

	FachadaPersonal portalPersonal = FachadaPersonal.getPortal();

	Scanner sc = new Scanner(System.in);

	public void menuInvitado() {
		System.out.println("\t\tBienvenido al menú de gestión de un vivero de Plantas");
		System.out.println("\nPULSE: ");
		System.out.println("\t\t1 - Ver Plantas.");
		System.out.println("\t\t2 - Iniciar sesión.");
		System.out.println("\t\t9 - Cerrar aplicación.");
	}

	public void mostrarMenuInvitado() {
		boolean AbiertaSesion = false;
		int opcion = 0;
		do {
			this.menuInvitado();
			try {
				System.out.print("Selecciona una opción: ");
				opcion = sc.nextInt();
				sc.nextLine();
				if (opcion != 1 && opcion != 2 && opcion != 9) {
					System.out.println("Opción invalida. Prueba otra vez. \n");
					continue;
				}
				switch (opcion) {
				case 1:
					List<Planta> plantas = (List<Planta>) servPlanta.busquedaDeTodos();
					if (plantas.isEmpty()) {
						System.out.println("No hay plantas almacenadas.");
					} else {
						System.out.println("Lista de plantas:");
						for (int i = 0; i < plantas.size(); i++) {
							System.out.println((i + 1) + " ª " + plantas.get(i));
						}
					}
					break;
				case 2:
					System.out.print("Introduce tu usuario: ");
					String usuario = sc.nextLine().toUpperCase().trim();
					System.out.print("Introduce tu contraseña: ");
					String contraseña = sc.nextLine().toLowerCase().trim();

					// verifica si las credenciales son correctas.
					if (servCredenciales.autenticar(usuario, contraseña)) {
						// si las credenciales coinciden con las del usuario
						AbiertaSesion = true;
						System.out.print("\n");
						if (usuario.equals("ADMIN") && contraseña.equals("admin")) {
							System.out.println("\t\tHola ADMIN, bienvenido dispones de todo el control del VIVERO");
							portalAdmin.menuAdmin();
						} else {
							portalPersonal.menuPersonal();
						}

					} else {
						System.out.println("Usuario o contraseña incorrectos.");
					}
					break;

				case 9:
					System.out.println("Cerrando el menú de invitado.");
					break;
				}

			} catch (InputMismatchException e) {
				// si mete un numero que no es entero salta este error
				System.out.println("ERROR, porfavor ingrese un numero ENTERO\n");
				sc.next();
			}

		} while (!AbiertaSesion && opcion != 9);
		// el bucle se repite hasta que se la inicie sesión o el usuario quiera salir
		// del PROGRAMA.
	}

}
