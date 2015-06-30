/**
 *  General purpose object queue
 *
 * @author     Danny Ayers
 * @created    20 March 2001 
 
 * Queue has five significant methods : 
 *
 *   add - adds object to back of queue 
 *   get - pulls object off the front of queue 
 *   peek - looks at object at front of queue 
 *   clear - empties queue 
 *   resize - resizes
 *      the queue container array, preserving contents.
 */

public class Queue {

  private Object[] array;
  private int currentsize;
  private int front;
  private int back;
  private int min_size = 5;
  private int max_size = 1000;

  private final static int DEFAULT_SIZE = 10;

  public Queue() {
    this(DEFAULT_SIZE);
  }

  public Queue(int size) {
    array = new Object[size];
    clear();
  }

  public boolean setMaxSize(int max) {
    if (max < currentsize) {
      return false;
    }
    if (max < array.length) {
      resize(max);
    }
    max_size = max;
    return true;
  }

  public boolean setMinSize(int min) {
    if (currentsize > min_size) {
      return false;
    }
    min_size = min;
    return true;
  }

  public synchronized Object get() {
    if (isEmpty()) {
      System.out.print("empty");
      return null;
    }
    currentsize--;
    Object frontitem = array[front];
    array[front] = null;
    front = increment(front);
    return frontitem;
  }

  public synchronized boolean isFull() {
    return currentsize == array.length;
  }

  public synchronized int getCapacity() {
    return array.length;
  }

  public synchronized int getSize() {
    return currentsize;
  }


  public synchronized boolean isEmpty() {
    return currentsize == 0;
  }

  public synchronized boolean add(Object item) {

    if (isFull()) {
      int newsize = array.length * 3 / 2;
      if (newsize > max_size) {
        return false;
      }
      System.out.print("full");
      resize(array.length * 3 / 2);
    }
    back = increment(back);
    array[back] = item;
    currentsize++;
    return true;
  }

  public synchronized Object peek() {
    if (isEmpty()) {
      return null;
    }
    return array[front];
  }

  public synchronized void clear() {
    currentsize = 0;
    front = 0;
    back = -1;
  }

  public synchronized boolean resize(int newsize) {
    if (newsize < min_size) {
      return false;
    }
    if (newsize <= currentsize) {
      return false;
    }
    Object[] newarray = new Object[newsize];
    int j = 0;
    int i = front;

    if (front == back) {
      System.out.println("*******************");
    }

    if (currentsize != 0) {
      for (i = front; i != back; i = increment(i)) {
        try {
          newarray[j++] = array[i];
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println(j + " " + i + " " + array.length + " " + newarray.length + "  newsize = " + newsize + "  current = " + currentsize);
          System.exit(1);
        }
      }
    }
    newarray[j] = array[i];
    //  System.out.println("\nold size " + array.length);
    // show(array);
    array = newarray;
    front = 0;
    back = currentsize - 1;
    //  System.out.println("new size" + newsize);
    //  show(array);
    return true;
  }



  public String toString() {
    StringBuffer output = new StringBuffer();
    output.append("Capacity = " + getCapacity() + "\n");
    output.append("Current size = " + getSize() + "\n");
    Object ob;
    for (int i = 0; i < array.length; i++) {
      ob = array[i];
      if (ob != null) {
        output.append(" " + ob);
      }
    }
    return output.toString();
  }

  private int increment(int i) {
    // synchronized
    return ++i == array.length ? 0 : i;
  }

}
