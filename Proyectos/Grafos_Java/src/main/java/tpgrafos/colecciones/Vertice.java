package tpgrafos.colecciones;

  import java.util.ArrayList;
  import java.util.List;

/**
 *  Representa un nodo en un grafo.
 */
public class Vertice {
  /**
   * Obtiene el identificador de vertice.
   * @return el identificador del vertice.
   */
  public Integer getId() {
    return id;
  }

  /**
   * identificador del vertice.
   */
  private final Integer id;

  /**
   * Obtiene la lista de vertices adyacentes a este.
   * @return lista de vertices adyacentes a este.
   */
  public List<Vertice> getAdyacentes() {
    return adyacentes;
  }

  /**
   * lista de vertices adyacentes a this.
   */
  private List<Vertice> adyacentes;

  /**
   * CONSTRUCTOR
   * Construye un vertice con el identificador pasado como parametro.
   * @param id identificador de vertice.
   */
  public Vertice(Integer id) {
    this.id = id;
    adyacentes = null;
  }

  /**
   * Conecta este vertice al vertice especificado.
   * @param v Vertice a unir con this.
   * @return {@code true} sii la conexión con el vértice especificado fue agregada.
   */
  public boolean agregarAdyacente(Vertice v) {
    if (adyacentes == null) {
      adyacentes = new ArrayList<Vertice>();
    }
    if (!adyacentes.contains(v)) {
      adyacentes.add(v);
      return true;
    }
    return false;
  }

  /**
   * desconecta este vertice del vertice especificado.
   * @param v Vertice a desconectar.
   * @return {@code true} sii la conexión con el vertice especificado fue eliminada.
   */
  public boolean eliminarAdyacente(Vertice v) {
    if (adyacentes != null) {
      return adyacentes.remove(v);
    }
    return false;
  }

  /**
   * TODO: Documentar.
   * @return valor de hash para este vertice.
   */
  @Override
  public int hashCode() {
    return id.hashCode();
  }

  /**
   * TODO: Documentar.
   * @param obj a comparar con este vertice.
   * @return true si el objeto especificado es igual a este vértice.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true; //verifica si el objeto que se está comparando (obj) es el mismo objeto (this)
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false; //verifica si el objeto que se está comparando (obj) es nulo o si pertenece a una clase diferente
    }
    Vertice other = (Vertice) obj;
    return id.equals(other.id);
  }

  /**
   * Genera una representación en forma de cadena del vértice.
   * Incluye el identificador y, si hay vértices adyacentes, también los muestra.
   * @return Representacion del vértice en forma de String.
   */
  @Override
  public String toString() {
      // Creamos un StringBuilder para construir la cadena de representación
      StringBuilder result = new StringBuilder();

      // Agregamos el identificador del vértice
      result.append("Vertice{id=").append(getId());
      //System.out.println(result);

      // Verificamos si la lista de adyacentes no es nula y no está vacía
      if (adyacentes != null && !adyacentes.isEmpty()) {
          // Agregamos la información de los vértices adyacentes
          result.append(", adyacentes=").append(getAdyacentes());
      }

      // Cerramos la representación en forma de cadena
      result.append('}');
      String resultado = result.toString();
      //System.out.println(result);
    // Convertimos el StringBuilder a String y lo retornamos
      return resultado;
  }

}
