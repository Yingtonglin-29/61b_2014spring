/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  private List[] table;
  private double loadFactor = 0.75;
  private int numberOfBucket;
  private int numberOfEntry;
  

  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    numberOfBucket = (int) (sizeEstimate/loadFactor);
    table = new SList[numberOfBucket];
    for (int i = 0; i < numberOfBucket; i ++) {
      table[i] = new SList();
    }
    numberOfEntry = 0;
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    numberOfBucket = 100;
    table = new SList[numberOfBucket];
    for (int i = 0; i < numberOfBucket; i ++) {
      table[i] = new SList();
    }
    numberOfEntry = 0;
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  // int compFunction(int code) {
  //   // Replace the following line with your solution.
  //   return 88;
  // }
  int compFunction(int code) {
    // Replace the following line with your solution.
    code = (233333 * code + 23333333) % 16908799;
    if (code < 0) {
      code = (code + 16908799)  % 16908799;
    }
    return code % numberOfBucket;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return numberOfEntry;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return numberOfEntry == 0 ? true : false;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    Entry obj = new Entry();
    obj.key = key;
    obj.value = value;
    int objBucketIndex = compFunction(key.hashCode());
    table[objBucketIndex].insertBack(obj);
    numberOfEntry ++;
    return obj;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    int objBucketIndex = compFunction(key.hashCode());
    List bucketToFind = table[objBucketIndex];
    if (bucketToFind.length() == 0) {
      return null;
    }
    ListNode curr = bucketToFind.front();
    while (curr.isValidNode() == false) {
      Entry currEntry;
      try {
        currEntry = (Entry) curr.item();
        if (currEntry.key() == key) {
          return currEntry;
        } else {
          curr = curr.next();
        }
      } catch (InvalidNodeException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    int objBucketIndex = compFunction(key.hashCode());
    List bucketToFind = table[objBucketIndex];
    if (bucketToFind.length() == 0) {
      return null;
    }
    ListNode curr = bucketToFind.front();
    while (curr.isValidNode() == false) {
      Entry currEntry;
      try {
        currEntry = (Entry) curr.item();
        if (currEntry.key() == key) {
          curr.remove();
          return currEntry;
        } else {
          curr = curr.next();
        }
      } catch (InvalidNodeException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    table = new SList[numberOfBucket];
    for (int i = 0; i < numberOfBucket; i ++) {
      table[i] = new SList();
    }
    numberOfEntry = 0;
  }

  public String[] makeStrings() {
    String[] res = new String[numberOfBucket];
    String s = "";
    int index = 0;
    for (int i = 0; i < numberOfBucket; i ++) {
      if (i % 10 == 0) {
        res[index] = s;
        index ++;
        s = "";
      }
      s += "[" + Integer.toString(table[i].length()) + "]";
    }
    return res;
  }

}
