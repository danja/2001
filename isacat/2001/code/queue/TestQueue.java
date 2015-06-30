/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    20 March 2001 
 *
 *      Main thread pumps incrementing numbers onto the the
 *      queue, then gets them off again, checking they're still in sequence.
 *      Every time a number goes on queue, + is displayed, every time a number
 *      is removed - displayed. Producer threads push null objects onto queue,
 *      consumers remove null objects.
 */
class TestQueue {

  Queue queue;
  int outcount = 0;
  int incount = 0;
  int producers = 0;
  int consumers = 0;

  public TestQueue() {
    this(10, 30, 30);
  }

  public TestQueue(int size, int in, int out) {
    queue = new Queue(size);
    queue.setMaxSize(Integer.MAX_VALUE);
    in();
    for (int i = 0; i < in; i++) {
      (new Producer()).start();
      producers++;
    }
    for (int i = 0; i < out; i++) {
      (new Consumer()).start();
      consumers++;
    }
    for (int i = 0; i < Integer.MAX_VALUE / 2; i++) {
      in();
      out();

    }
    System.out.println("producers : " + producers + "  consumers : " + consumers);
    System.out.println("Test OK!");
  }


  public void in() {
    for (int j = 0; j < (int) (Math.random() * 10000 + 1); j++) {
      System.out.print("+");
      queue.add(new Integer(incount));
      incount++;
    }
  }

  public void out() {

    for (int j = 0; j < (int) (Math.random() * 10000 + 1); j++) {
      Object ob = queue.get();

      if (ob != null) {
        System.out.print("-");
        if (((Integer) ob).intValue() != outcount) {
          System.out.println("\n\nReceived value : " + ob + "   should be : " + outcount);
          System.out.println("Target was : " + Integer.MAX_VALUE / 2);
          System.out.println("Producers : " + producers + "  Consumers : " + consumers);
          System.out.println("Capacity : " + queue.getCapacity() + "   Size : " + queue.getSize());
          System.exit(1);

        }
        outcount++;
      }
    }
  }



  public static void main(String args[]) {
    if (args.length != 2) {
      new TestQueue();
    } else {
      new TestQueue(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
    }
  }

  /**
   *  Description of the Class
   *
   * @author     Danny Ayers
   * @created    20 March 2001
   */
  class Producer extends Thread {

    public void run() {
      while (true) {
        queue.add(null);
        try {
          Thread.sleep((int) (Math.random() * 100));
        } catch (Exception e) {
        }
      }
    }
  }

  /**
   *  Description of the Class
   *
   * @author     Danny Ayers
   * @created    20 March 2001
   */
  class Consumer extends Thread {

    public void run() {
      while (true) {
        try {
          Thread.sleep((int) (Math.random() * 100));
        } catch (Exception e) {
        }
        Object ob = queue.peek();
        if (ob == null) {
          queue.get();
        }
        //   System.out.println(queue.getCount()+"  "+queue.getSize());
      }
    }
  }

}

