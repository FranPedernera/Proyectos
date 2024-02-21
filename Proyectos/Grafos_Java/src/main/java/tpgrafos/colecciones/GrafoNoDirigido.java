package tpgrafos.colecciones;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;


/**{@inheritDoc}*/
public class GrafoNoDirigido implements Grafo {

    private List<Vertice> vertices;
    private List<Arista> aristas;


    // Constructor de la clase GrafoNoDirigido

public GrafoNoDirigido() {
      // Inicializa la lista de vértices del grafo como una nueva instancia de ArrayList
    this.vertices = new ArrayList<>();
      // Inicializa la lista de aristas del grafo como una nueva instancia de ArrayList
      this.aristas = new ArrayList<>();
  }
  /**
   * Constructor de la clase GrafoNoDirigido que acepta listas predefinidas de vértices y aristas
   * @param vertices Listas de vertices del grafo a construir.
   * @param aristas coleccion de aristas del grafo a construir.
   */
  public GrafoNoDirigido(List<Vertice> vertices, Collection<Arista> aristas) {
     // Inicializa la lista de vértices del grafo con la lista proporcionada de vértices
    this.vertices = new ArrayList<>(vertices);
    // Inicializa la lista de aristas del grafo con la colección proporcionada de aristas
    this.aristas = new ArrayList<>(aristas);
  }

  /**{@inheritDoc}*/
  public boolean agregarVertice(Vertice nodo) {
    // 1: Verifica si el vértice proporcionado es nulo
    if (nodo == null) {
        throw new IllegalArgumentException("El vértice a agregar no puede ser nulo.");
    }

    // 2: Verifica si el vértice ya existe en el grafo
    if (!existe(nodo)) {
        // Agrega el vértice a la lista de vértices
        vertices.add(nodo);
        // Devuelve true para indicar que el vértice fue agregado exitosamente
        return true;
    }

    // Devuelve false indicando que el vértice no pudo ser agregado (ya existe en el grafo)
    return false;

  }
  /**
   * Consulta la existencia de un nodo (vértice) en el grafo.
   *
   * @param nodo el nodo a consultar.
   * @return {@code true} sii {@code nodo} existe en el grafo.
   */
  public boolean existe(Vertice nodo) {
    // Itera sobre la lista de vértices y verifica si el vértice dado está presente
    return vertices.contains(nodo);
  }


  /**{@inheritDoc}*/
  public List<Vertice> vertices() {
      // Devuelve una copia de la lista de vértices para evitar modificaciones no deseadas
      return new ArrayList<>(vertices);
  }


  /**{@inheritDoc}*/
  public Collection<Arista> aristas() {
    // Devuelve una copia de la lista de aristas para evitar modificaciones no deseadas
    return new ArrayList<>(aristas);
  }

  /**{@inheritDoc}*/
  public boolean agregarArista(Vertice vertice1, Vertice vertice2) {
        // Verifica si ambos vértices existen en el grafo
    if (existe(vertice1) && existe(vertice2)) {
        // Crea una nueva arista con los vértices dados
        Arista nuevaArista = new Arista(vertice1, vertice2);

        // Verifica si la arista ya existe en el grafo
        if (!aristas.contains(nuevaArista)) {
            // Agrega la arista a la lista de aristas
            aristas.add(nuevaArista);

            // Conecta los vértices adicionando cada uno como adyacente al otro
            vertice1.agregarAdyacente(vertice2);
            vertice2.agregarAdyacente(vertice1);
            // Devuelve true para indicar que la arista fue agregada correctamente
            return true;
        }
    }

    // Devuelve false indicando que la arista no pudo ser agregada
    return false;
  }
  /**{@inheritDoc}*/
  public boolean eliminarArista(Vertice vertice1, Vertice vertice2) {
        // Verifica si ambos vértices existen en el grafo
    if (existe(vertice1) && existe(vertice2)) {
        // Crea una instancia de la arista con los vértices dados
        Arista aristaAEliminar = new Arista(vertice1, vertice2);

        // Verifica si la arista existe en el grafo
        if (aristas.contains(aristaAEliminar)) {
            // Elimina la arista de la lista de aristas
            aristas.remove(aristaAEliminar);

            // Desconecta los vértices eliminando cada uno como adyacente del otro
            vertice1.eliminarAdyacente(vertice2);
            vertice2.eliminarAdyacente(vertice1);

            // Devuelve true para indicar que la arista fue eliminada correctamente
            return true;
        }
    }

    // Devuelve false indicando que la arista no pudo ser eliminada
    return false;
  }

  /**{@inheritDoc}*/
  public List<Vertice> obtenerAdyacentes(Vertice v) {
          // Verifica si el vértice existe en el grafo
      if (existe(v)) {
          // Utiliza el método getAdyacentes de la clase Vertice para obtener la lista de vértices adyacentes
          return new ArrayList<>(v.getAdyacentes());
      }

      // Si el vértice no existe, devuelve una lista vacía
      return new ArrayList<>();
  }

  /**{@inheritDoc}*/
  public int cantidadVertices() {
    // Utiliza el método size de la lista de vértices para obtener la cantidad de vértices
    return vertices.size();
  }

  /**{@inheritDoc}*/
  public boolean esVacio() {
    // Utiliza el método isEmpty de la lista de vértices para verificar si no hay vértices en el grafo
    return vertices.isEmpty();
  }

  /**{@inheritDoc}*/
  public int cantidadAristas() {
    // Utiliza el método size de la lista de aristas para obtener la cantidad de aristas en el grafo
    return aristas.size();

  }

  /**{@inheritDoc}*/
  public boolean sonArco(Vertice v1, Vertice v2) {
      // Verifica si ambos vértices existen en el grafo
      if (existe(v1) && existe(v2)) {
          // Crea instancias de aristas con los vértices dados en ambos sentidos
          Arista arista1 = new Arista(v1, v2);
          Arista arista2 = new Arista(v2, v1);

          // Verifica si al menos una de las aristas existe en el grafo
          return aristas.contains(arista1) || aristas.contains(arista2);
      }

      // Devuelve false si alguno de los vértices no existe en el grafo
      return false;
  }

  /**
   * TODO: Documentar.
   * @return Representacion de los vertices y arcos del grafo en forma de String.
   */
  @Override
  public String toString() {
      StringBuilder sb = new StringBuilder();    // Agrega información sobre los vértices
      sb.append("Vértices: ");
      for (Vertice vertice : vertices) {
          sb.append(vertice.getId()).append(", ");
      }
      // Elimina la coma y el espacio extra al final
      if (!vertices.isEmpty()) {
          sb.delete(sb.length() - 2, sb.length());
      }
      sb.append("\n");    // Agrega información sobre las aristas
      sb.append("Aristas: ");
      for (Arista arista : aristas) {
          sb.append(arista.toString()).append(", ");
      }
      // Elimina la coma y el espacio extra al final
      if (!aristas.isEmpty()) {
          sb.delete(sb.length() - 2, sb.length());
      }
      return sb.toString();
  }

      /**
   * Elimina un nodo (vértice) <b>existente</b> del grafo, y elimina todas las
   * aristas que van o llegan al mismo.
   *
   * @param nodo el nodo a eliminar.
   * @return {@code true} sii el nodo {@code nodo} existía en el grafo y fue
   *         correctamente eliminado.
   */
  /**{@inheritDoc}*/
  public boolean eliminarVertice(Vertice nodo) {
    // Verifica si el vértice existe en el grafo
    if (!existe(nodo)) {
     return false;
    }

    // Elimina todas las aristas que van o llegan al vértice dado
    for (Vertice adyacente : nodo.getAdyacentes()) {
         eliminarArista(nodo, adyacente);
    }

    // Elimina el vértice de la lista de vértices
    vertices.remove(nodo);

    return true;
    }



}
