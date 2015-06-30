package com.isacat.iou.threads;

import java.io.*;
import java.util.*;

/**
 *  Description of the Class
 *
 * @author     Danny Ayers
 * @created    26 March 2001
 */
public abstract class ThreadPool {
  Vector pool;
  WorkerThread[] threads;

  public final static int THREAD_COUNT = 4;

  public ThreadPool() {
    pool = new Vector();
    threads = new WorkerThread[THREAD_COUNT];
  }

  public abstract Object getNextObject();

  public void start() {
    for (int i = 0; i < threads.length; i++) {
      //   threads[i] = new WorkerThread(pool);
      //   threads[i].start();
    }
    while (objectsRemain()) {
      int totalJobs = 0;
      totalJobs++;

      synchronized (pool) {
        pool.add(getNextObject());
        pool.notifyAll();
      }
    }
    // end while

    // make sure that any waiting thread knows that no
    // more files will be added to the pool
    for (int i = 0; i < threads.length; i++) {
      threads[i].interrupt();
    }
  }

  public abstract boolean objectsRemain();

}
