package tpgrafos.colecciones;
import java.util.Collection;
import java.util.List;

/**
 * Grafo. Los invariantes están dados por las siguientes
 * condiciones:
 * <ul>
 * <li>No pueden existir nodos {@code null} en el grafo.</li>
 * <li>Si existe una arista  que une los vértices V1 con V2, entonces V1 y V2
 * deben ser nodos que pertenecen al grafo.</li>
 * </ul>
 */
public interface Grafo {


    /**
     * Agrega un nuevo nodo (vértice) al grafo.
     *
     * @param nodo el nuevo nodo a agregar.
     * @return {@code true} sii el nodo pudo ser agregado.
     *         <hr>
     *         <i>Un nodo puede no ser agregado por que ya existe o por que la
     *         representación interna del grafo está llena.</i>
     */
    boolean agregarVertice(Vertice nodo);

    /**
     * Elimina un nodo (vértice) <b>existente</b> del grafo, y elimina todas las
     * aristas que van o llegan al mismo.
     *
     * @param nodo el nodo a eliminar.
     * @return {@code true} sii el nodo {@code nodo} existía en el grafo y fue
     *         correctamente eliminado.
     */
    boolean eliminarVertice(Vertice nodo);

    /**
     * Consulta la existencia de un nodo (vértice) en el grafo.
     *
     * @param nodo el nodo a consultar.
     * @return {@code true} sii {@code nodo} existe en el grafo.
     */
    boolean existe(Vertice nodo);

    /**
     * Retorna una coleccion con todos los vértices en el grafo.
     *
     * @return los vertices en el grafo en una colección
     *         <hr>
     *         <i>Modificar los vértices obtenidos puede tener efectos en el
     *         grafo.</i>
     */
    List<Vertice> vertices();

    /**
     * Retorna una coleccion con todas las aristas del grafo.
     *
     * @return las aristas (pares de vertices adyacentes) en el grafo en una colección.
     */
    Collection<Arista> aristas();

    /**
     * Conecta dos nodos <b>existentes</b>.
     *
     * @param vertice1 el vértice vertice1 donde sale la arista.
     * @param vertice2 el vértice vertice2 donde va la arista.
     * @return {@code true} sii la arista no existía en el grafo y fue agregada.
     */
    boolean agregarArista(Vertice vertice1, Vertice vertice2);

    /**
     * Elimina la arista que unia los vertices especificados.
     *
     * @param vertice1 el vértice vertice1 donde sale la arista.
     * @param vertice2 el vértice vertice2 donde va la arista.
     * @return {@code true} sii la arista a eliminar existía y fue eliminada.
     */
    boolean eliminarArista(Vertice vertice1, Vertice vertice2);

    /**
     * Obtiene los vértices adyacentes a un nodo <b>existente</b>.
     *
     * @param v el vértice sobre el cual obtener los adyacentes.
     * @return una Lista de  vértices adyancetes a {@code v}.
     */
    List<Vertice> obtenerAdyacentes(Vertice v);

    /**
     * Retorna cuantos vértices hay en el grafo.
     *
     * @return la cantidad de nodos (vértices) presentes en el grafo.
     */
    int cantidadVertices();

    /**
     * Retorna si el grafo no posee ningún vértice.
     *
     * @return {@code true} sii no existe ningún nodo en el grafo, este método es
     *         similar a ejecutar
     *
     *         <pre>
     *         cantidadVertices() == 0
     *         </pre>
     *
     *         .
     */
    boolean esVacio();

    /**
     * Retorna la cantidad de aristas en el grafo.
     *
     * @return cuantas aristas existen en el grafo.
     *         <hr>
     *         <i>Un valor {@code 0} no implica que el grafo esté vacío.</i>
     */
    int cantidadAristas();

    /**
     * Determina si hay un arco entre los vertices dados.
     * @param v1 Vertice de consulta si es un extremo de arco.
     * @param v2 Vertice de consulta si es un extremo de arco.
     * @return  si hay un arco entre los vertices dados.
     */
    boolean sonArco(Vertice v1, Vertice v2);


}
