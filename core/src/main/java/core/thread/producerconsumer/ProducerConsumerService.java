package core.thread.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * Create the ArrayBlockingQueue
 * Initialise the Producer/Consumer with the queue
 * Create Producer/Consumer Thread
 */
public class ProducerConsumerService {

    public static void main(String...args) {
        BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
        System.out.println("Producer and Consumer has been started");
    }
}