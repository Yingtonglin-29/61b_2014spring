/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
  private List list;
  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    // Your solution here.
    this.list = new DList();
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return this.list.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   * @throws InvalidNodeException
   **/
  public void insert(Comparable c) throws InvalidNodeException {
    ListNode curr = this.list.front();
    // 1. if front node is sentinel, c should be new front
    if (! curr.isValidNode()) {
      this.list.insertFront(c);
      return;
    }
    // 2. if item of front node is the same as c
    else if (curr.item() == c) return;
    // 3. if item of front node is the bigger as c (c should be new front, smallest)
    else if (c.compareTo(curr.item()) < 0) {
      curr.insertBefore(c);
      return;
    }
    // 4. if item of front node is the smaller as c (c should be after front)
    else if (c.compareTo(curr.item()) > 0) {
      while (curr.next().isValidNode()) {  // while the next node is not sentinel, move forward to find the right position
        if (c.compareTo(curr.next().item()) == 0) return; 
        else if (c.compareTo(curr.next().item()) < 0) { 
          curr.insertAfter(c);
          return;
        } 
        else if (c.compareTo(curr.next().item()) > 0) {
          curr = curr.next();
        }
      }
      this.list.insertBack(c); // kicked out of while loop for reaching the sentinel, insert to the back of the list
    }
  }
  //  && c.compareTo(curr.next().item()) > 0

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   * @throws InvalidNodeException
   **/
  public void union(Set s) throws InvalidNodeException {
    // Your solution here.
    if (s.cardinality() == 0) return;
    ListNode curr = s.list.front();
    while (curr.isValidNode()) {
      this.insert((Comparable)curr.item());
      curr = (DListNode) curr.next();
    }
  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   * @throws InvalidNodeException
   **/
  public void intersect(Set s) throws InvalidNodeException {
    // Your solution here.
    if (cardinality() == 0) return;
    if (s.cardinality() == 0) {
      this.list = new DList();
      return;
    }
    ListNode sCurr = s.list.front();
    ListNode thisCurr = this.list.front();
    while (sCurr.isValidNode() && thisCurr.isValidNode()) {
      if (((Comparable)thisCurr.item()).compareTo(sCurr.item()) == 0) {
        sCurr = sCurr.next();
        thisCurr = thisCurr.next();
      }
      else if (((Comparable)thisCurr.item()).compareTo(sCurr.item()) > 0) {
        sCurr = sCurr.next();
      }
      else if (((Comparable)thisCurr.item()).compareTo(sCurr.item()) < 0) {
        ListNode next = thisCurr.next();
        thisCurr.remove();
        thisCurr = next;       
      }
    }
    if (thisCurr.isValidNode()) {
      while (thisCurr.isValidNode()) {
        ListNode next = thisCurr.next();
        thisCurr.remove();
        thisCurr = next;
      }
    }
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
    return list.toString();
  }

  public static void main(String[] argv) throws InvalidNodeException {
    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(4));
    s.insert(new Integer(5));
    s.insert(new Integer(6));
    s.insert(new Integer(7));
    s.insert(new Integer(5));
    System.out.println("Set s = " + s);

    // Set s2 = new Set();
    // s2.insert(new Integer(4));
    // s2.insert(new Integer(5));
    // s2.insert(new Integer(5));
    // System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    System.out.println("Set s3 = " + s3);

    // s.union(s2);
    // System.out.println("After s.union(s2), s = " + s);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.
  }
}
