/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp.nro1.util;

/**
 * Define our own Pair class which contains two Comparable objects.
 * @param <F> is the type of the first elements of the pair.
 * @param <S> is the type of the second elements of the pair.
 */
public class Pair<F extends Comparable<F>, S extends Comparable<S>>
        implements Comparable<Pair<F, S>> {

  /**
   * first object in the Pair.
   */
  private F first;
  /**
   * second object in the Pair.
   */
  private S second;

  /**
   * Constructor for a Pair.
   *
   * @param first  the first object in the Pair
   * @param second the second object in the pair
   */
  public Pair(F first, S second) {
    this.first = first;
    this.second = second;
  }

  /**
   * Checks the two objects for equality by delegating to their respective
   * {@link Object#equals(Object)} methods.
   *
   * @param o the {@link Pair} to which this one is to be checked for equality
   * @return true if the underlying objects of the Pair are both considered
   * equal
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Pair)) {
      return false;
    }
    final Pair<?, ?> pair = (Pair<?, ?>) o;
    if (this == pair) {
      return true;
    }

    if (this.getFirst() == null) {
      if (pair.getFirst() != null) {
        return false;
      }
    } else if (!this.getFirst().equals(pair.getFirst())) {
      return false;
    }
    if (this.getSecond() == null) {
      if (pair.getSecond() != null) {
        return false;
      }
    } else if (!this.getSecond().equals(pair.getSecond())) {
      return false;
    }
    return true;
  }

  /**
   * Compute a hash code using the hash codes from the inner objects.
   *
   * @return a hashcode of the Pair
   */
  @Override
  public int hashCode() {
    return (getFirst() == null ? 0 : getFirst().hashCode()) ^ (getSecond() == null ? 0 : getSecond().hashCode());
  }

  /**
   * Convenience method for creating an appropriately typed pair.
   * &lt;A&gt; Type for the first object in the Pair
   * &lt;B&gt; Type for the second object in the pair
   * @param a the first object in the Pair
   * @param b the second object in the pair
   * @return a Pair contains a and b
   */
  public static <A extends Comparable<A>, B extends Comparable<B>> Pair<A, B> create(A a, B b) {
    return new Pair<A, B>(a, b);
  }

  /**
   * Overriding compareTo() method
   * @param that the object to be compared.
   * @return inner F compareTo.
   */
  @Override
  public int compareTo(Pair<F, S> that) {
    int cmp = this.getFirst().compareTo(that.getFirst());
    if (cmp == 0) {
      cmp = this.getSecond().compareTo(that.getSecond());
    }
    return cmp;
  }

  /**
   * Pair representation as {@code String}.
   * @return Pair representation as {@code String}.
   */
  @Override
  public String toString() {
    return "(" + getFirst().toString() + "," + getSecond().toString() + ")";
  }

  /**
   * First  the first object in the Pair.
   * @return First  the first object in the Pair.
   */
  public F getFirst() {
    return first;
  }

  /**
   * Second  the second object in the Pair.
   * @return Second  the second object in the Pair.
   */
  public S getSecond() {
    return second;
  }
  public void setFirs(F first ){
    this.first = first;
  }

  public void setSecond (S second){
    this.second = second;
  }



}
