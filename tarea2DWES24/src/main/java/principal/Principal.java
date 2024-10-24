package principal;

import java.util.Scanner;

import entidades.Planta;

public class Principal {

	public static void main(String[] args) {
		Scanner in= new Scanner (System.in);
		System.out.println("Dame el codigo de la nueva planta");
		String codigo = in.nextLine().trim().toUpperCase();	
		System.out.println("Dame el nombre común de la nueva planta");
		String nombrecomun = in.nextLine();
		System.out.println("Dame el nombre cientifico de la nueva planta");
		String nombrecientifico = in.nextLine();
		
		
		Planta nueva = new Planta(codigo, nombrecomun, nombrecientifico);
		

		
	}

}
