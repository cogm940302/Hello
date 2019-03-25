package models;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;

public class NewAutomata {
	
	String edoAux = "";
	int iAux = 0;

	public void make() {
		//Variable que lee todo el archivo
//		String libroCompleto = leerArchivo();
		String libroCompleto = "0,1,2,3,4,5,6\r\n" +
				"+,-,a,.\r\n" +
				"0\r\n" +
				"4,6\r\n" +
				"0,+,1\r\n" +
				"0,-,1\r\n" +
				"0,+,5\r\n" +
				"0,-,5\r\n" +
				"1,a,2\r\n" +
				"2,a,2\r\n" +
				"2,.,3\r\n" +
				"3,a,4\r\n" +
				"4,a,4\r\n" +
				"5,a,5\r\n" +
				"5,.,6\r\n" +
				"6,a,6";
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
					libroCompleto +=  "\r\n" + validaSiEstaEnLaTabla + "," + "error";
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
		System.out.println(libroCompleto);
		armaAutomataHash(libroCompleto,"+aa.aa");
		//armaAutomata(libroCompleto.split("\\r\\n"), "+aa.aa", 0, "");
	}
	
	public void armaAutomataHash(String libro, String palabra) {
		Hashtable<String, String> diccionario = genHash(libro.split("\\r\\n"));
		if(diccionario.size() <= 0) {//Validamos que la tabla este bien llena
			System.out.println("Error en llenar la tabla");
			return;
		}
//		System.out.println(diccionario.toString()); 
		String edo = "";//esta es la variable para el edo, cuando inicias empieza vacio
		for(int i = 0 ; i < palabra.length() ; i++) {
			if(edo == null || edo.equals("")) {//si esta vacio significa que es el inicio aun cuando el primer elemento no sea parte del lenguaje
				String aBuscar = "0" + palabra.charAt(i);//concateno el edo 0 a la letra inicial
				System.out.println("transicion : " + aBuscar);
				edo = iteracion(diccionario, aBuscar);  //busco el nuevo estado
			}else if(edo.contains("\\t")) {//Si  vienen mas transiciones corto los edos posibles y ocupo uno. Guardo en una variable temporal por si tengo que regresar despues
				String corta[] = edo.split("\\\\t"); //Corto los posibles caminos
				iAux = i; //registro los datos
				edoAux = corta[1]; // registro el otro posible camino
				String aBuscar = corta[0] + palabra.charAt(i); //solo ocupo el primer camino
				System.out.println("transicion : " + aBuscar);
				edo = iteracion(diccionario, aBuscar);
			}else { //Este se ocupa cuando ya tiene un valor el edo, o sea no es edo 0
				String aBuscar = edo + palabra.charAt(i);
				System.out.println("transicion : " + aBuscar);
				edo = iteracion(diccionario, aBuscar);
			}
			if(i == palabra.length()-1 && !edo.equals("4") && !edo.equals("6") && !edoAux.equals("")) {// aqui tienes que cambiar el 4 y 6 por los posibles estados finales
				i = iAux; //si la cadena no esta en un edo final toma los valores temporales y valida el siguiente camino
				edo = edoAux;
				System.out.println("Siguiente ");
			}
		}
	}
	/**Esta funcion solo busca en el hash**/
	public String iteracion(Hashtable<String, String> diccionario, String aBuscar) {
		String result = diccionario.get(aBuscar);
		if(result != null)
			return result;
		else  //este else es por si no existe la transicion que manda regresa el edo que ya tenia
			return aBuscar.substring(0, 1);
	}
	
	/**Funcion que crea un tipo de dato HashTable
	 * Una hashtable funciona como una tabla
	 * donde almacenas valores pero se guardan con una llave
	 * la llave tiene que ser unica
	 * mi llave para esto son las transiciones y el valor es al edo que te lleva
	 * **/
	public Hashtable<String, String> genHash(String[] libro){
		//Creo el tipo de dato hash
		Hashtable<String, String> diccionario = new Hashtable<>();
		//linea toma el valor de cada linea de la tabla de transiciones
		String linea = null;
		//I empieza desde el 4 porque las lineas anteriores son otros valores
		for(int i = 4 ; i < libro.length ; i++) {
			linea  = libro[i]; 
			String[] dato = linea.split(","); //separo las transiciones
			String getValue = diccionario.get(dato[0]+dato[1]); //Hash te regresa un valor si ya esta, si no te regresa un null
			if(getValue == null)// si el valor es null agrego el nuevo dato
				diccionario.put(dato[0]+dato[1], dato[2]);
			else// si ya habia un valor antes concateno un nuevo valor
				diccionario.put(dato[0]+dato[1], getValue + "\\t" + dato[2]);
		}
		/**imprimimos el mapa**/
//		Iterator it = diccionario.keySet().iterator();
//		while(it.hasNext()){
//		  String key = (String)it.next();
//		  System.out.println(" " + key + " -> " + diccionario.get(key));
//		}
		
		/****/
		
		return diccionario;
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