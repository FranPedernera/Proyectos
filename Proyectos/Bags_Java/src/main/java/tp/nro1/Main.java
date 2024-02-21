package tp.nro1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import tp.nro1.collection.MultiSet;
import tp.nro1.collection.MultiSetArray;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
  /**
   * Create MultiSet and use the concrete operations.
   *
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    Integer[] a1 = {4, 5, 6, 4, 9, 1};
    MultiSet<Integer> b1 = new MultiSetArray<Integer>(Arrays.asList(a1));
    System.out.println("bag b1 = " + b1.toString());
    b1.remove(4);
    System.out.println("after remove(4) bag b1 = " + b1.toString());
    Integer[] a2 = {6};
    MultiSetArray<Integer> b2 = new MultiSetArray<Integer>(Arrays.asList(a2));
    b2.insert(0);
    System.out.println("bag b2 = " + b2.toString());
    MultiSet<Integer> unionBab = b1.union(b2);
    System.out.println("union b1 and b2 = " + unionBab.toString());
    System.out.println("el maximo es " + b1.max());
    System.out.println("la igualdad del bag1 y bag 2 es: " + b1.equals(b2));
    Integer[] a3 = {4, 5, 6, 9, 1};
    MultiSet<Integer> b3 = new MultiSetArray<Integer>(Arrays.asList(a3));
    System.out.println("la igualdad del bag 1 y bag 3 es: " + b1.equals(b3));

    String archivo = "/home/fran/Escritorio/trabajo-practico-1-trimboli-pedernera/src/texto.txt";
    //Creo el bag donde voy  a ir guardando las palbras del archivo
    MultiSet<String> palabrasTexto = new MultiSetArray<>();

    try {
      BufferedReader reader = new BufferedReader(new FileReader(archivo));
      String linea;
    
      while ((linea = reader.readLine()) != null) {
        // Dividir la línea en palabras usando el espacio como delimitador
        String[] palabras = linea.split(" ");
    
        for (String palabra : palabras) {
          // Eliminar caracteres no alfabéticos de la palabra
          palabra = palabra.replaceAll("[^a-zA-Z]", "");
    
          // Agregar la palabra al 'palabrasTexto' si no está vacía
          if (!palabra.isEmpty()) {
            palabrasTexto.insert(palabra);
          }
        }
      }
    } catch (IOException e) {
      // Manejar errores de lectura de archivo
    }
    
    Scanner scanner = new Scanner(System.in);
    System.out.println("ingrese la palabra que quiere saber si esta en el texto");
    String valorIngresado = leerValor(scanner);
    System.out.println(palabrasTexto.contains(valorIngresado));
  }

  public static String leerValor(Scanner scanner) {
    if (scanner.hasNext()) {
      return  scanner.next();
    }
    return null;
  }


  /* Result Expected:
      bag b1 = {1, 4, 4, 5, 6, 9}
      after remove(4) bag b1 = {1, 4, 5, 6, 9}
      bag b2 = {0, 6}
      union b1 and b2 = {0, 1, 4, 5, 6, 6, 9}
  */
  }


