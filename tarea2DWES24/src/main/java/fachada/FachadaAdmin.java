package fachada;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.IllegalFormatCodePointException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.sound.midi.VoiceStatus;

import control.Controlador;
import control.ServiciosCredenciales;
import control.ServiciosEjemplar;
import control.ServiciosMensaje;
import control.ServiciosPersona;
import control.ServiciosPlanta;
import entidades.Credenciales;
import entidades.Persona;
import entidades.Planta;
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
		int opcion = 0;

		do {
			System.out.println("\n\t\tMenú Administrador\n");
			System.out.println("\t\t1-  Gestión plantas");
			System.out.println("\t\t2-  Gestión ejemplares");
			System.out.println("\t\t3-  Gestión mensajes");
			System.out.println("\t\t4-  Gestión persona");
			System.out.println("\t\t9-  Cierre de Sesión");

			try {
				opcion = sc.nextInt();
				sc.nextLine();
				if (opcion != 1 && opcion != 2 && opcion != 3 && opcion != 4 && opcion != 9) {
					System.out.println("Opción invalida. Prueba otra vez. \n");
					continue;
				}
				switch (opcion) {
				case 1:
					gestionPlanta();
					break;
				case 2:
					gestionEjemplar();
					break;
				case 3:
					gestionMensaje();
					break;
				case 4:
					gestionPersona();
					break;
				case 9:
					FachadaInvitado.getPortal().mostrarMenuInvitado();
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("ERROR, porfavor ingrese un numero ENTERO");
				sc.next();
			}
		} while (opcion != 9);

	}

	private void gestionPlanta() {
		System.out.println("\n\t\tMenu-Gestión-Ejemplar\n");
		int opcion = 0;

		do {
			System.out.println("\t\t1-  Insertar PLANTA");
			System.out.println("\t\t2-  Modificar PLANTA-NombreComun");
			System.out.println("\t\t3-  Modificar PLANTA-NombreCientifico");
			System.out.println("\t\t9-  Volver al menú ADMIN");

			try {
				opcion = sc.nextInt();
				sc.nextLine();
				if (opcion != 1 && opcion != 2 && opcion != 3 && opcion != 9) {
					System.out.println("Opción invalida. Prueba otra vez. \n");
					continue;
				}
				switch (opcion) {
				case 1:
					insertarPlanta();
					break;
				case 2:
					modificarPlantaNombreComun();
					break;
				case 3:
					modificarPlantaNombreCientifico();
				case 9:
					menuAdmin();
					break;
				}
			} catch (InputMismatchException e) {
				System.err.println("ERROR, porfavor ingrese un numero ENTERO");
				sc.next();
			}
		} while (opcion != 9);
	}

	private void modificarPlantaNombreComun() {
		Planta p = null;
		boolean codigoCorrecto = false;
		boolean encontrado = false;
		String codigo;

		do {
			System.out.println("Dime el CODIGO para poder cambiar el nombre de esa PLANTA");
			codigo = sc.nextLine();
			codigoCorrecto = controlador.getServiciosPlanta().validarCodigo(codigo);
			if (codigoCorrecto == false) {
				System.out.println("CODIGO no valido");
			}
		} while (encontrado == false);
		encontrado = controlador.getServiciosPlanta().esunCodigo(codigo);
		if (encontrado == false) {
			System.out.println("CODIGO no existente");
		}
		System.out.print("Nombre Comun nuevo: ");
		String nombreNuevo;
		nombreNuevo = sc.nextLine().trim();
		try {
			boolean nuevo = controlador.getServiciosPlanta().nombreComun(codigo, nombreNuevo);
			if (nuevo == true) {
				System.out.println("Planta de codigo: " + codigo + ", ahora el nombre comun es:" + nombreNuevo);
			} else {
				System.out.println("ERROR en la actualización");
			}
		} catch (Exception ex) {
			System.out.println("ERROR en la actualización" + ex.getMessage());
		}

	}

	public void modificarPlantaNombreCientifico() {
		Planta p = null;
		boolean codigoCorrecto = false;
		boolean encontrado = false;
		String codigo;

		do {
			System.out.println("Dime el CODIGO para poder cambiar el nombre de esa PLANTA");
			codigo = sc.nextLine();
			codigoCorrecto = controlador.getServiciosPlanta().validarCodigo(codigo);
			if (codigoCorrecto == false) {
				System.out.println("CODIGO no valido");
			}
		} while (encontrado == false);
		encontrado = controlador.getServiciosPlanta().esunCodigo(codigo);
		if (encontrado == false) {
			System.out.println("CODIGO no existente");
		}
		System.out.print("Nombre Cientifico nuevo: ");
		String nombreNuevo;
		nombreNuevo = sc.nextLine().trim();
		try {
			boolean nuevo = controlador.getServiciosPlanta().nombreCientifico(codigo, nombreNuevo);
			if (nuevo == true) {
				System.out.println("Planta de codigo: " + codigo + ", ahora el nombre cientifico es:" + nombreNuevo);
			} else {
				System.out.println("ERROR en la actualización");
			}
		} catch (Exception ex) {
			System.out.println("ERROR en la actualización" + ex.getMessage());
		}
	}

	public void insertarPlanta() {
		Planta p = new Planta();
		boolean valido = false;
		while (!valido) {
			System.out.print("Dime el CODIGO de la planta: ");
			try {
				String codigoPlanta = sc.nextLine().trim().toUpperCase();
				valido = controlador.getServiciosPlanta().validarCodigo(codigoPlanta);
				if (!valido) {
					System.out.println("3-20 Caracteres");
					continue;
				}
				p.setCodigo(codigoPlanta);
			} catch (Exception e) {
				System.out.println("ERROR al insertar los datos " + e.getMessage());
				valido = false;
			}
		}

		System.out.print("Nombre común: ");
		String nombreComun = sc.nextLine().trim();
		p.setNombrecomun(nombreComun);

		System.out.print("Nombre científico: ");
		String nombreCientifico = sc.nextLine().trim();
		p.setNombrecientifico(nombreCientifico);

		valido = controlador.getServiciosPlanta().validarPlanta(p);
		if (!valido) {
			System.out.println("Los datos que has introducido no son correctos. Revisa los valores ingresados.");
			return;
		}

		try {
			controlador.getServiciosPlanta().insertar(p);
			System.out.println("Planta INSERTADA.");
		} catch (Exception e) {
			System.out.println("ERROR al insertar la planta" + e.getMessage());
		}
	}

	private void gestionEjemplar() {
		System.out.println("\n\t\tMenu-Gestión-Ejemplar\n");
	}

	private void gestionMensaje() {
		System.out.println("\n\t\tMenu-Gestión-Mensaje\n");
	}

	public void gestionPersona() {
		System.out.println("\n\t\tMenú-Gestión-Persona\n");
		Persona p = new Persona();
		Credenciales c = new Credenciales();
		String nombre = solicitarNombre();
		p.setNombre(nombre);
		String email;
		boolean emailValido = false;
		while (!emailValido) {
			email = solicitarEmail();
			if (controlador.getServiciosPersona().existeEmail(email)) {
				System.out.println("EMAIL ya registrado. Intente con otro.");
			} else {
				p.setEmail(email);
				emailValido = true;
			}
		}

		boolean usuarioValido = false;
		boolean correcto = false;
		String usuario;
		String contrasena;
		do {
			System.out.print("Usuario: ");
			usuario = sc.nextLine().trim();
			if (controlador.getServiciosCredenciales().existeUsuario(usuario)) {
				System.out.println("El usuario '" + usuario + "' ya está registrado. Elige otro nombre.");
			} else {
				usuarioValido = true;
				c.setUsuario(usuario);
			}
		} while (!usuarioValido);

		contrasena = solicitarContraseña();
		c.setPassword(contrasena);

		long idPersona = controlador.getServiciosPersona().insertar(p);

		if (idPersona == 0) {
			System.out.println("ERROR en el registro de la persona. No hay ID");
		}

		try {
			if (controlador.getServiciosCredenciales().insertar(usuario, contrasena, idPersona) > 0) {
				correcto = true;
				System.out.println("Registro exitoso. Usuario y credenciales creados correctamente.");
			} else {
				System.out.println("ERROR en el registro.");
			}
		} catch (Exception e) {
			System.err.println("Ocurrió un error durante el registro de las credenciales: " + e.getMessage());
		}
	}

	private String solicitarEmail() {
		String email;
		while (true) {
			System.out.print("Dime el EMAIL de la persona a quien quieres REGISTRAR: ");
			email = sc.nextLine().trim();

			if (emailValido(email)) {
				return email;
			} else {
				System.out.println("El EMAIL tiene que tener la primera letra mayúscula");
			}
		}
	}

	private String solicitarNombre() {
		String nombre;
		while (true) {
			System.out.print("Dime el NOMBRE de la persona a quien quieres REGISTRAR: ");
			nombre = sc.nextLine().trim();

			if (nombreValido(nombre)) {
				return nombre;
			} else {
				System.out.println("El NOMBRE tiene que tener la primera letra mayúscula");
			}
		}
	}

	private String solicitarContraseña() {
		String contraseña;
		while (true) {
			System.out.println("Dime la CONTRASEÑA que le quieres poner a tu USUARIO (4 digitos EJ: 0000): ");
			contraseña = sc.nextLine().trim();
			if (contraseñaValida(contraseña)) {
				return contraseña;
			} else {
				System.out.println("La CONTRASEÑA tiene que tener 4 digitos");
			}
		}

	}

	private boolean emailValido(String email) {
		return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com$"); // validacion de un EMAIL
	}

	private boolean contraseñaValida(String contraseña) {
		return contraseña.matches("\\d{4}"); // contraseña que solo tenga 4 digitos
	}

	private boolean nombreValido(String nombre) {
		return nombre.matches("[A-Z][a-z]*"); // solo la primera letra puede ser mayuscula
	}
}
