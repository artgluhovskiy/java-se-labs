package org.art.samples.core.concurrent.design.blocked_buffer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Consumer thread implementation.
 */
public class Consumer extends Thread {

    public static final Logger LOG = LogManager.getLogger(Consumer.class);

    private BoundedBlockedBuffer buffer;

    public Consumer(String threadName, BoundedBlockedBuffer buffer) {
        super(threadName);
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                buffer.getInt();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " has been interrupted.");
                return;
            }
        }
    }
}
