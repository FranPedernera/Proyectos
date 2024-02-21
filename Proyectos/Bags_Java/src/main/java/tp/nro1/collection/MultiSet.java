package tp.nro1.collection;
import java.util.Collection;

/**
 * Multiset or Bag is a collection of comparable objects.
 * It allows for multiple instances for each of its elements.
 * @param <T> is the type of the elements of the bag.
 */
public interface MultiSet<T extends Comparable<T>> {
  /**
   * Is this bag empty?
   * @return true if this bag is empty; false otherwise
   */
  boolean isEmpty();


  /**
   * Returns the number of items in the bag.
   * @return the number of items in the bag
   */
  int cardinality();

  /**
   * Returns true if this bag contains the specified element.
   * @param elem element whose presence in this bag is to be tested.
   * @return true if elem is this bag member; false otherwise.
   */
  boolean contains(T elem);

  /**
   * Adds the specified element to this bag.
   * @param elem element to be added to this bag.
   * @return true if this bag changed as a result of the call.
   */
  boolean insert(T elem);

  /**
   * Removes one occurrence of the specified element from this bag if it is present.
   * @param elem element to be removed from this bag, if present.
   * @return true if this bag contained the specified element.
   * @throws IllegalStateException if the bag is empty.
   */
  boolean remove(T elem);

  /**
   * The union of two bags returns the elements that appears in this bag and otherBag the
   * sum of the number of times it appears in each bag.
   * @param otherBag bag containing elements to be added to this bag
   * @return the elements that appears in this bag and otherBag the
   * sum of the number of times it appears in each bag.
   */
  MultiSet<T> union(MultiSet<T> otherBag);


  /**
   * Removes all the elements from this bag.
   */
  void clear();

  /**
   * Returns maximum element in bag. This can't be {@code null} or {@code empty}.
   * @return maximum element in bag.
   * @throws IllegalStateException when This is {@code null} or {@code empty}
   */
  T max() throws IllegalStateException;

  /**
   * Bag representation as {@code String}, elements are separate from each other with ", "
   * and begin and end delimiters are "{}".
   * To convert each element to String: {@code String.valueOf(Object)}.
   * @return Bag representation as {@code String}.
   */
  String toString();

  /**
   * Returns the hash code value for this bag.
   * The hash code of a bag is defined to be the sum of the hash codes of the elements
   * the amount of times it appears in the bag,
   * where the hash code of a null element is defined to be zero.
   * This ensures that s1.equals(s2) implies that s1.hashCode()==s2.hashCode()
   * for any two bags s1 and s2, as required by the general contract of Object.hashCode().
   * @return the hash code value for this bag.
   */
  int hashCode();

  /**
   * Compares the specified object with this bag for equality.
   * Returns true if the specified object is also a bag,
   * the two bags have the same size, and every member of the specified bag
   * is contained in this bag the exact same number of times.
   * This definition ensures that the equals method works properly
   * across different implementations of the set interface.
   * @param e object to compare.
   * @return true if the specified object is equal to this.
   */
  boolean equals(Object e);

  /**
   * Retrieve the bag items as a Collection.
   * @return the bag items as a Collection.
   */
  Collection<T> getItems();
}
