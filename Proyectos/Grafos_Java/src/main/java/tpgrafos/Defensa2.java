package tpgrafos;

import tpgrafos.colecciones.Arista;
import tpgrafos.colecciones.Grafo;
import tpgrafos.colecciones.GrafoNoDirigido;
import tpgrafos.colecciones.Vertice;

import java.util.ArrayList;
import java.util.List;

public class Defensa2 {
  public static void main(String [] args) {
    int size = 4;
    List<Vertice> vertex = new ArrayList<>();
    List<Arista> edge = new ArrayList<>();
    for(int i = 1; i < size; i++) {
      Vertice v = new Vertice(i);
      for (Vertice vertice : vertex) {
        edge.add(new Arista(vertice, v));
      }
      vertex.add(v);
    }
    Grafo g = new GrafoNoDirigido(vertex, edge);
    Vertice last = new Vertice(size);
    g.agregarVertice(last);
    g.agregarArista(vertex.get(0), last);
    System.out.print("\n Calculando amistades esenciales....");
    System.out.print("\n Cantidad de amistades esenciales = " + Amistades.amistadEscencial(g) + "\n");



  }
}
