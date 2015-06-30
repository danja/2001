package com.isacat.iou.threads;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    26 March 2001
 */
public abstract class WorkerThread extends Thread {

  private List pool;
  private static int jobsDone = 0;


  public WorkerThread(List pool) {
    this.pool = pool;
  }

  public abstract void process(Object ob);

  public abstract int remainingJobs();

  public void run() {

    while (remainingJobs() > 0) {

      Object input = null;

      synchronized (pool) {
        while (pool.isEmpty()) {
          if (remainingJobs() == 0) {
            return;
          }
          try {
            pool.wait();
          } catch (InterruptedException e) {
          }
        }

        input = pool.remove(pool.size() - 1);

        process(input);
        jobsDone++;
      }
      // end if
    }
    // end while
  }
  // end run
}
