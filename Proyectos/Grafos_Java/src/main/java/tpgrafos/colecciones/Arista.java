package tpgrafos.colecciones;

public class Arista {
  /**
   * Obtiene el primer vertice de una arista.
   * @return el primer vertice de una arista.
   */
  public Vertice getPrimero() {
    return primero;
  }

  /**
   * Obtiene el segundo vertice de una arista.
   * @return el segundo vertice de una arista.
   */
  public Vertice getSegundo() {
    return segundo;
  }

  /**
   * Primer vertice de una arista.
   */
  private final Vertice primero;
  /**
   * Segundo vertice de una arista
   */
  private final Vertice segundo;

  /**
   * Constructor de una arista.
   * @param primero Primer vertice de la arista.
   * @param segundo Segundo vertice de la arista.
   */
  public Arista(Vertice primero, Vertice segundo) {
    this.primero = primero;
    this.segundo = segundo;
  }

  /**
   * primero.hashCode(): Obtienes el valor de hash del primer vértice de la arista.
   * segundo.hashCode(): Obtienes el valor de hash del segundo vértice de la arista.
   * 31 * (primero.hashCode() + segundo.hashCode()): Multiplicas la suma de los valores de hash de ambos vértices por un número primo (en este caso, 31). 
   * La multiplicación por un número primo y la suma de los valores de hash de los vértices es una estrategia que usamos para combinar los valores de hash de múltiples componentes en un solo valor de hash para el objeto completo. 
   * Esta combinación busca minimizar colisiones (situaciones en las que dos objetos diferentes tienen el mismo valor de hash).
   * @return valor de hash para esta arista.
   */
  @Override
  public int hashCode() {
    // Implementa el hash considerando ambos vértices
    return 31 * (primero.hashCode() + segundo.hashCode());
  }


  /**
   * verifica la igualdad entre dos objetos de tipo Arista asegurándose de que conecten los mismos vértices, sin importar el orden en que están conectados. 
   * donde el orden de los vértices podría variar pero aún representar la misma conexión. 
   * @param obj a comparar con esta arista.
   * @return true si el objeto especificado es igual a esta arista.
   */
  @Override
  public boolean equals(Object obj) {
      //Verifica si el objeto actual es el mismo que el objeto comparado
      if (this == obj) {
        return true;
      }

      //Verifica si el objeto comparado es nulo o pertenece a una clase diferente
      if (obj == null || getClass() != obj.getClass()) {
        return false;
      }

      //Hacemos un casting del objeto comparado a la clase Arista
      Arista other = (Arista) obj;

      //Comparamos ambas combinaciones de vértices (primero y segundo) para la igualdad bidireccional
      return (primero.equals(other.primero) && segundo.equals(other.segundo)) ||
            (primero.equals(other.segundo) && segundo.equals(other.primero));
  }



  /**
   * proporciona una representación en forma de cadena de la arista,
   * incluyendo la información sobre los vértices que conecta. 
   * @return Representacion de la arista en forma de String.
   */
  @Override
  public String toString() {
     return "Arista{" +
           "primero=" + primero.getId() +
           ", segundo=" + segundo.getId() +
           '}';
  }


}
