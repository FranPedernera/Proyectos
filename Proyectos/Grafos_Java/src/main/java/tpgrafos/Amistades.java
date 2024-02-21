package tpgrafos;

import tpgrafos.colecciones.Arista;
import tpgrafos.colecciones.Grafo;
import tpgrafos.colecciones.GrafoNoDirigido;
import tpgrafos.colecciones.Vertice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Dada la información de una comunidad específica de una red social, es decir personas y amistades que las relacionan,
 * calcula el número de amistades esenciales.
 * La entrada del problema es un archivo que contiene las siguientes líneas:
 *  <ul>
 *  <li>
 *  La primera lı́nea contiene dos enteros separados por un espacio {@code U} y {@code A},
 *  que representan La cantidad de usuarios (({@code >= 1} y {@code <= 100000})) y amistades (({@code >= 1} y {@code <= 150000})).
 *  </li>
 *  <li>
 *  Las siguientes {@code A} líneas contienen información acerca de las amistades directas, {@code a}, {@code b} enteros positivos separados por un espacio.
 *  </li>
 *  </ul>
 */
public class Amistades {  /**
 * Numero de parametros recibidos por CLI.
 */
private static final int NUMPARAMS = 1;  /**
 * Numero de usuarios de la red que pertenecen a la comunidad (U),
 * {@code 1} &lt; {@code U} && {@code U} &le; {@code 100000}
 */
private static int numeroUsuarios;
    /**
     * Numero de amistades (A).
     * {@code 1} &le; {@code A} && {@code A} &le; {@code 150000}
     */
    private static int numeroAmistades;  /**
     * Ejecuta la aplicación que descubre amistades esenciales, según la información pasada como argumento, .
     *  @param args Los argumentos del programa.
     *  @throws IOException en caso de problemas al acceder a un archivo.
     */
    public static void main(String[] args) throws IOException {
        if ((args.length != NUMPARAMS) || args[0].trim().isEmpty()) {
            throw new IllegalArgumentException("args debe contener el nombre de un archivo existente");
        }
        File archivoEntrada = Paths.get(args[0].trim()).toFile();
        if (!archivoEntrada.exists()) {
            throw new IllegalArgumentException("El archivo" + args[0] + "no es un archivo");
        }
        if (!archivoEntrada.isFile()) {
            throw new IllegalArgumentException("El archivo" + args[0] + "no tiene permisos de lectura");
        }
        String nombreArchivo = archivoEntrada.getAbsolutePath();
        Grafo grafo = parsearEntrada(nombreArchivo);
        System.out.println("Archivo de entrada: " + args[0] + " procesado.");
        System.out.println("Calculando amistades esenciales ...");
        System.out.println("Cantidad de amistades esenciales = " + amistadEscencial(grafo));

    }
    /**
     * Dada la información de una comunidad específica, determina la cantidad de amistades esenciales.
     * Una amistad es esencial si, cuando falta, quedan usuarios, que antes pertenecían a la comunidad, fuera de la misma.
     * Utiliza BFS para verificar la esencialidad de cada amistad.
     * @param grafo Representación de una comunidad de amigos específica.
     * @return La cantidad de amistades esenciales.
     */
    public static int amistadEscencial(Grafo grafo) {
        int amistadesEsenciales = 0; // Itera a través de todas las aristas en el grafo
        for (Arista arista : grafo.aristas()) {
            // Eliminar temporalmente el borde actual del grafo
            grafo.eliminarArista(arista.getPrimero(), arista.getSegundo());            // Compruebe si la eliminación desconecta a los usuarios de la comunidad
            if (!esComunidadConectada(grafo)) {
                amistadesEsenciales++;
            }
            // Vuelva a agregar la rista  al grafo para la siguiente iteración
            grafo.agregarArista(arista.getPrimero(), arista.getSegundo());
        }
        return amistadesEsenciales;
    }    /**
     * Verifica si la comunidad sigue estando conectada después de eliminar una amistad.
     * Utiliza una búsqueda en amplitud (BFS) para esta verificación.
     * @param grafo Representación de una comunidad de amigos específica.
     * @return true si la comunidad sigue conectada, false si se ha desconectado.
     */
    private static boolean esComunidadConectada(Grafo grafo) {
        Set<Vertice> visitados = new HashSet<>();

        if (grafo.cantidadVertices() == 0) {
            // Si no hay vértices en el grafo, se considera como una comunidad conectada
            return true;
        }

        // Iterar sobre todos los vértices del grafo para manejar grafos no conexos
        for (Vertice vertice : grafo.vertices()) {
            if (!visitados.contains(vertice)) {
                if (!dfs(grafo, vertice, visitados)) {
                    // Si dfs retorna false, significa que no todos los vértices son alcanzables desde el vértice actual
                    return false;
                }
            }
        }

        // Si se llega a este punto, significa que todos los vértices son alcanzables desde algún vértice
        return true;
    }

    private static boolean dfs(Grafo grafo, Vertice inicio, Set<Vertice> visitados) {
        Stack<Vertice> pila = new Stack<>();
        pila.push(inicio);
        visitados.add(inicio);

        while (!pila.isEmpty()) {
            Vertice actual = pila.pop();

            for (Vertice adyacente : grafo.obtenerAdyacentes(actual)) {
                if (!visitados.contains(adyacente)) {
                    visitados.add(adyacente);
                    pila.push(adyacente);
                }
            }
        }

        // Verificar si todos los vértices son alcanzables desde el vértice de inicio
        return visitados.size() == grafo.cantidadVertices();
    }
    
    


    /**
     * Parsea el archivo de entrada y asigna los valores correspondientes a los campos estáticos,
     * `numeroUsuarios` y `numeroAmistades` y construye un grafo con los datos del archivo.
     * @param archivoEntrada nombre del archivo de entrada.
     * @return Grafo obtenido de los datos del archivo de entrada.
     * @throws IOException problemas con el archivo de entrada.
     */

    private static Grafo parsearEntrada(String archivoEntrada) throws IOException {
    BufferedReader lector = new BufferedReader(new FileReader(archivoEntrada));
    String linea = lector.readLine();    // Dividir la primera línea en dos partes: número de usuarios y número de amistades
    String[] partes = linea.split(" ");
    int numeroUsuarios = Integer.parseInt(partes[0]);
    int numeroAmistades = Integer.parseInt(partes[1]);    // Crear un nuevo GrafoNoDirigido
    GrafoNoDirigido grafo = new GrafoNoDirigido();    // Crear un conjunto para llevar un seguimiento de los vértices ya agregados al grafo
    Set<Vertice> verticesAgregados = new HashSet<>();
    // Leer las siguientes líneas con información sobre amistades
    for (int i = 0; i < numeroAmistades; i++) {
        linea = lector.readLine();
        partes = linea.split(" ");        // Obtener los identificadores de los vértices de la amistad
        Integer idVertice1 = Integer.parseInt(partes[0]);
        Integer idVertice2 = Integer.parseInt(partes[1]);        // Obtener o crear vértices
        Vertice vertice1 = obtenerVerticePorId(verticesAgregados, grafo, idVertice1);
        Vertice vertice2 = obtenerVerticePorId(verticesAgregados, grafo, idVertice2); // Agregar arista al grafo
        grafo.agregarArista(vertice1, vertice2);
    }    // Asignar valores a los campos estáticos
    Amistades.numeroUsuarios = numeroUsuarios;
    Amistades.numeroAmistades = numeroAmistades;    // Cerrar el lector
    lector.close();
    return grafo;
}

/**
 * Método auxiliar para obtener un vértice por su ID o crear uno nuevo si no existe.
 * @param verticesAgregados Conjunto que lleva un seguimiento de los vértices ya agregados al grafo.
 * @param grafo Grafo al que se están agregando vértices.
 * @param id ID del vértice a buscar o crear.
 * @return El vértice encontrado o uno nuevo creado.
 */
private static Vertice obtenerVerticePorId(Set<Vertice> verticesAgregados, Grafo grafo, int id) {
    for (Vertice vertice : verticesAgregados) {
        if (vertice.getId() == id) {
            return vertice;
        }
    }
    // Si no se encuentra el vértice, crear uno nuevo y agregarlo a la lista
    Vertice nuevoVertice = new Vertice(id);
    verticesAgregados.add(nuevoVertice);
    grafo.agregarVertice(nuevoVertice);
    return nuevoVertice;
}
}