package automata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class NewAutomata {

	public void make() {
		//Variable que lee todo el archivo
		String libroCompleto = leerArchivo();
//		String libroCompleto = "0,1,2,3,4,5,6\r\n" +
//				"+,-,a,.\r\n" +
//				"0\r\n" +
//				"4,6\r\n" +
//				"0,+,1\r\n" +
//				"0,-,1\r\n" +
//				"0,+,5\r\n" +
//				"0,-,5\r\n" +
//				"1,a,2\r\n" +
//				"2,a,2\r\n" +
//				"2,.,3\r\n" +
//				"3,a,4\r\n" +
//				"4,a,4\r\n" +
//				"5,a,5\r\n" +
//				"5,.,6\r\n" +
//				"6,a,6";
		//Linea Por Linea Del archivo
		String[] lineaPorLinea = libroCompleto.split("\\r\\n");
//		for(String linea : lineaPorLinea) {
//			System.out.println("Linea : "  + linea);
//		}
		//Valida que el archivo sea correcto
		if(lineaPorLinea == null || lineaPorLinea.length <= 0) {
			System.out.println("El archivo es incorrecto");
			return;
		}
		//Toma la linea 1 y la convierte en los estados del automata
		String[] estados = lineaPorLinea[0].split(",");
		//Toma la linea 2 y la convierte en el alfabeto del automata
		String[] alfabeto = lineaPorLinea[1].split(",");
		//Valida si se encuentra en el archivo, si no agrega la transicion con un estado de error
		for(int i = 0 ; i < estados.length ; i++) {
			for(int j = 0; j < alfabeto.length ; j++) {
				String validaSiEstaEnLaTabla = estados[i] + "," + alfabeto[j];
				if(!libroCompleto.contains(validaSiEstaEnLaTabla)) {
					libroCompleto += validaSiEstaEnLaTabla + "," + "error" + "\r\n";
				}
			}
		}
		
		System.out.println("***********************************************");
		System.out.println("***********************************************");
		System.out.println("***********************************************");
		System.out.println("***********************************************");
		System.out.println("***********************************************");
		System.out.println("***********************************************");
		//Imprime
		//System.out.println(libroCompleto);
		armaAutomata(libroCompleto.split("\\r\\n"), "+aa.aa", 0, "");
	}
	
	public void armaAutomata(String[] libroCompleto, String palabraAValidar, int lugarEnLaPalabra, String edoTemporal) {
		for(int i = lugarEnLaPalabra ; i < palabraAValidar.length() ; i++) {
			String transicion = "";
			if(edoTemporal.equals("")) {
				transicion = "0," + palabraAValidar.charAt(i);
			}else {
				transicion = edoTemporal + "," + palabraAValidar.charAt(i);
			}
			for(int j = 4; j < libroCompleto.length ; j++) {
				if(!transicion.equals("") && libroCompleto[j].contains(transicion)) {
					System.out.println("transicion: " + transicion);
					edoTemporal = libroCompleto[j].split(",")[2];
					int lugarDeI = i + 1;
					if(lugarDeI == palabraAValidar.length()) {
						edoTemporal = "";
					}
					armaAutomata(libroCompleto, palabraAValidar,lugarDeI ,edoTemporal);
				}
			}
			
		}
		System.out.println("Ya termine la transicion\n");
	}
	
	public String leerArchivo() {
		String cadena;
		FileReader f = null;
		BufferedReader b = null;
		String libroCompleto = "";
		try {
			f = new FileReader("C:\\Users\\Admon\\Desktop\\automata.txt");
		    b = new BufferedReader(f);
		    while((cadena = b.readLine())!=null) {
		    	//Se agrega retorno de carro y nueva linea porque el alfabeto podria tener otro caracter y seria un conflicto
		        libroCompleto += cadena + "\r\n";
//		        System.out.println(cadena);
		    }
		} catch (FileNotFoundException e) {
			System.out.println("No encontre el archivo");
		} catch (IOException e) {
			System.out.println("No pude leer el archivo");
		}finally {
			if(b != null) {
			      try {
					b.close();
				} catch (IOException e) {
					System.out.println("Una excepcion rara");
				}
			}
		}

	      return libroCompleto;
	}
	
	public static void main(String[] args) {
		new NewAutomata().make();
	}
}
