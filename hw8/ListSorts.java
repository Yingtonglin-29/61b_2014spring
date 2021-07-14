/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 1000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   * @throws QueueEmptyException
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) throws QueueEmptyException {
    // Replace the following line with your solution.
    LinkedQueue res = new LinkedQueue();
    while (!q.isEmpty()) {
      LinkedQueue newQueue = new LinkedQueue();
      newQueue.enqueue(q.dequeue());
      res.enqueue(newQueue);
    }
    return res;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   * @throws QueueEmptyException
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) throws QueueEmptyException {
    // Replace the following line with your solution.
    LinkedQueue res = new LinkedQueue();
    if (q1.isEmpty() && q2.isEmpty()) return q1;
    else if (q1.isEmpty()|| q2.isEmpty()) {
      while (!q2.isEmpty() || !q1.isEmpty()) {
          res.enqueue(q1.isEmpty() ? q2.dequeue() : q1.dequeue());
      }
    } else {
      while (!q1.isEmpty() && !q2.isEmpty()) {
        if (((Comparable) q1.front()).compareTo((Comparable) q2.front()) > 0) {
          res.enqueue(q2.dequeue());
        } else {
          res.enqueue(q1.dequeue());
        }
      }
      res.append(q1.isEmpty() ? q2 : q1);
    }
    return res;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   * @throws QueueEmptyException
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) throws QueueEmptyException {
    // Your solution here.
    while (!qIn.isEmpty()) {
      Object item = qIn.dequeue();
      if (((Comparable) item).compareTo(pivot) > 0) {
        qLarge.enqueue(item);
      } else if (((Comparable) item).compareTo(pivot) > 0) {
        qSmall.enqueue(item);
      } else {
        qEquals.enqueue(item);
      }
    }
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   * @throws QueueEmptyException
   **/
  public static void mergeSort(LinkedQueue q) throws QueueEmptyException {
    // Your solution here.
    if (q.isEmpty() || q.size() == 1) return;
    LinkedQueue QueueOfQueues = makeQueueOfQueues(q);
    while (QueueOfQueues.size() > 1) {
      LinkedQueue q1 = (LinkedQueue) QueueOfQueues.dequeue();
      LinkedQueue q2 = (LinkedQueue) QueueOfQueues.dequeue();
      LinkedQueue newQueue = mergeSortedQueues(q1, q2);
      QueueOfQueues.enqueue(newQueue);
    }
    q.append((LinkedQueue) QueueOfQueues.dequeue());
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   * @throws QueueEmptyException
   **/
  public static void quickSort(LinkedQueue q) throws QueueEmptyException {
    // Your solution here.
    if (q.isEmpty() || q.size() == 1) return;
    Comparable pivot = (Comparable) q.nth(q.size()/2);
    LinkedQueue qSmall = new LinkedQueue();
    LinkedQueue qLarge = new LinkedQueue();
    LinkedQueue qEquals = new LinkedQueue();
    partition(q, pivot, qSmall, qEquals, qLarge);
    if (qSmall.size() > 1) {
      quickSort(qSmall);
    }
    if (qLarge.size() > 1) {
      quickSort(qLarge);
    }
    q.append(qSmall);
    q.append(qEquals);
    q.append(qLarge);
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   * @throws QueueEmptyException
   **/
  public static void main(String [] args) throws QueueEmptyException {

    LinkedQueue q = makeRandom(10);
    // System.out.println(q.toString());
    // mergeSort(q);
    // System.out.println(q.toString());

    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    /* Remove these comments for Part III.
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    */
  }

}
