package tp.nro1.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import tp.nro1.util.Pair;

/**
 * MultiSet implementation based on ArrayList.
 * All elements inserted into a bag must implement the Comparable interface.
 * @param <T> is the type of the elements of the bag.
 */
public class MultiSetArray<T extends Comparable<T>> implements MultiSet<T> {
    /**
     * Structure used to store the elements in the bag.
     */
    private ArrayList<Pair<T, Integer>> items;
    /**
     * number of bag members
     */
    private int cardinal;
    /**
     * number of different bag members
     */
    private int itemsSize;

    /**
     * Initializes an empty bag.
     */
    public MultiSetArray() {
        items = new ArrayList<Pair<T, Integer>>();
        cardinal = 0;
        itemsSize = 0;
    }

    /**
     * Creates a bag containing all items passed to it as a Collection{@code T}.
     * @param elems to be passed in to this bag.
     */
    public MultiSetArray(Collection<T> elems) {
        this.items = new ArrayList<Pair<T, Integer>>();
        this.itemsSize = 0;
        this.cardinal = 0;
        for (T elem: elems) {
            this.insert(elem);
        }
    }

    /**{@inheritDoc}*/
    public int cardinality() {
        return cardinal;
    }


    /**{@inheritDoc}*/
    public boolean isEmpty() {
        return itemsSize == 0;
    }


    /**{@inheritDoc}*/
    public boolean contains(T elem) {
        int inicio = 0;
        int fin = itemsSize - 1;
        int pivot = 0;
        while (inicio <= fin) {
            pivot = inicio + (fin - inicio) / 2;
            Pair<T, Integer> par = items.get(pivot);
            if (elem.compareTo(par.getFirst()) == 0) {
                return true;
            } else {
                if (elem.compareTo(par.getFirst()) < 0) {
                    fin = pivot - 1;
                } else {
                    inicio = pivot + 1;
                }
            }
        }
        return false;
    }
    /**{@inheritDoc}*/
    public boolean insert(T elem) {
        //Creamos un par nuevo donde contiene el elemento que queiro agreagar
        Pair<T, Integer> nuevoPar = new Pair<T, Integer>(elem, 1);
        //Si este valor se queda en -1 al finalizar en ciclo significa que el elemento debe ser agregado al final del arreglo
        int pos = -1;
        for (int i = 0; i < items.size(); i++) {
            //Guardo el valor de la comparacion
            Pair<T, Integer> pair = items.get(i);
            int cmp = pair.getFirst().compareTo(elem);
            if (cmp == 0) {
                //Como el elemtno ya esta en el arreglo lo busco y le sumo uno a la cantidad de veces que esta
                pair.setSecond(pair.getSecond() + 1);
                cardinal++;
                return true;
            } else if (cmp > 0) {
                //Aca guardo en pos la posicion donde debo hacer la insercion
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            //Agrego el elemento al final
            items.add(nuevoPar);
        } else {
            items.add(pos, nuevoPar);
        }
        itemsSize++;
        cardinal++;
        return true;
    }
    /**{@inheritDoc}*/
    public boolean remove(T elem) {
        if (items.size() == 0) {
            throw new IllegalStateException("El bag esta vacio.");
        } else {
        for (int i = 0; i < items.size(); i++) {
            Pair<T, Integer> nuevoPar = items.get(i);
            //Veo si el elemnto es igual al que
            if (nuevoPar.getFirst().equals(elem)) {
                /*Miro si la cantidad de veces que esta este lemento es mayor a 1 y
                le resto uno al second de mi par que me dice la cantidad de veces */
                //que esta repetido ese valor en mi bag
                if (nuevoPar.getSecond() > 1) {
                    nuevoPar.setSecond(nuevoPar.getSecond() - 1);
                    cardinal--;
                    return true;
                } else if (nuevoPar.getSecond() == 1) {
                    //Lo que hago remuevo ese lugar del arreglo ya que el elemnto no v a estar en mi bag
                    items.remove(i);
                    itemsSize--;
                    return true;
                }
            }
        }
        //Si sale del for retorno falso porque el elemto no esta en mi bag
        return false;
    }
    }
    /**{@inheritDoc}*/
    public MultiSet<T> union(MultiSet<T> otherBag) {
        if (otherBag instanceof MultiSet) {
            //Creo una copia de mi this
            MultiSet<T> nuevoBag = new MultiSetArray<>(this.getItems());
            Collection<T> itemsBag = otherBag.getItems();
            for (T i : itemsBag) {
                //Le inserto el i-esimo elento de mi colleccion de mi otro bag a la copia de
                //de mi this
                nuevoBag.insert(i);
            }
            return nuevoBag;
        }
        return null;
    }
    /**{@inheritDoc}*/
    public void clear() {
        cardinal = 0;
        itemsSize = 0;
    }
    /**{@inheritDoc}*/
    public T max() throws IllegalStateException {
        if (isEmpty() || itemsSize == 0) {
            throw new IllegalStateException("El bag esta vacio ");
        } else {
            Pair<T, Integer> nuevoPar = items.get(itemsSize - 1);
            return nuevoPar.getFirst();
        }
    }
    /**{@inheritDoc}*/
    @Override
    public int hashCode() {
        //Variable para guardar el codigo hash
        int codigo = 0;
        for (int i = 0; i < items.size(); i++) {
            Pair<T, Integer> par = items.get(i);
            T elemnto = par.getFirst();
            int cantidad = par.getSecond();
            //Calulo el valor del codigo hash teniendo en cuenta el valor nulo que seria 0
            //Si el elemnto es null entoces me devuelve 0 sino me obtiene el hashCode de ese elemnto
            int codigoElemtno = (elemnto == null) ? 0 : elemnto.hashCode();
            //Hago la sumatoria de cada codigo hash de cada elemtno de mi bag
            codigo = 31 * codigo + (codigoElemtno ^ cantidad);
        }
        return codigo;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean equals(Object other) {
        if (!(this instanceof MultiSet) || !(other instanceof MultiSet)) {
            return false;
        }
        MultiSetArray<T> Bag1 = new MultiSetArray<>(this.getItems());
        @SuppressWarnings("unchecked")
        MultiSetArray<T> Bag2 = new MultiSetArray<>(((MultiSet<T>) other).getItems());
        for (int i = 0; i < Bag1.itemsSize; i++) {
            Pair<T, Integer> pair1 = Bag1.items.get(i);
            Pair<T, Integer> pair2 = Bag2.items.get(i);
            if (!Objects.equals(pair1.getFirst(), pair2.getFirst()) || !Objects.equals(pair1.getSecond(), pair2.getSecond())) {
                return false;
            }
        }
        return true;
    }


    /**{@inheritDoc}*/
    @Override
    public String toString() {
        String  contenido = "[ ";
        for (Pair<T, Integer> pair : items) {
            contenido = contenido + "(" + pair.getFirst() + "," + pair.getSecond() + ") ";
        }
        contenido = contenido + "]";

        return contenido;
    }


    /**
     * repOk checks that:
     * items != null &amp;&amp; (all 0 &lt; i &lt; items.size():
     * items[i-1].first &lt; items[i].first) &amp;&amp;
     * (all 0 &lt; i &lt; items.size():
     * items[i].second &gt;0 ).
     * @return true iff items != null &amp;&amp; (all 0 &lt; i &lt; items.size():
     * items[i-1].first &lt; items[i].first) &amp;&amp;
     * (all 0 &lt; i &lt; items.size():
     * items[i].second &gt;0 ).
     */
    public boolean repOk() {
        if (items == null) {
            return false;
        }
        if (itemsSize == 0 || itemsSize == 1) {
            return itemsSize == 0 ?  true :  (items.get(0).getSecond() > 0);
        }

        for (int i = 1; i < items.size(); i++) {
            if (items.get(i - 1).getFirst().compareTo(items.get(i).getFirst()) >= 0 || items.get(i).getSecond() <= 0) {
                return false;
            }
        }
        return true;
    }

    /**{@inheritDoc}*/
    public Collection<T> getItems() {
        ArrayList<T> elements = new ArrayList<>();
        for (Pair<T, Integer> pair : items) {
            for (int i = 0; i < pair.getSecond(); i++) {
                elements.add(pair.getFirst());
            }
        }
        return elements;
    }
}
