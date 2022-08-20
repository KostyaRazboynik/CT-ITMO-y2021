package queue;

import java.util.Arrays;

public class TestModule {

    public static void main(String[] args) {
        ArrayQueueModule queue = new ArrayQueueModule();

        fill(queue, 5);
        denqueTest(queue, 3);
        enqueueTest(queue, 100);
        enqueueTest(queue, 1001);
        enqueueTest(queue, 100);
        countTest(queue, 100);
        clearTets(queue);
    }

    public static void fill(ArrayQueueModule queue, int a) {
        for (int i = 0; i < a; i++) {
            queue.enqueue(i);
        }
        System.out.println("filled");
        System.out.print("new queue: ");
        printQueue(queue);
    }

    public static void enqueueTest(ArrayQueueModule queue, int a) {
        queue.enqueue(a);
        System.out.println("added " + a);
        System.out.print("new queue: ");
        printQueue(queue);
    }

    public static void denqueTest(ArrayQueueModule queue, int a) {
        for (int i = 0; i < a; i++) {
            queue.dequeue();
        }
        System.out.println("delited " + a + " elements");
        System.out.print("new queue: ");
        printQueue(queue);
    }

    public static void printQueue(ArrayQueueModule queue) {
        System.out.println("size: " + queue.size() + "; queue: " + Arrays.toString(queue.toArray()) + "; queue is emppty = " + queue.isEmpty() + '\n');
    }

    public static void clearTets(ArrayQueueModule queue) {
        queue.clear();
        System.out.println("cleared");
        printQueue(queue);
    }

    public static void countTest(ArrayQueueModule queue, Object el) {
        System.out.println("number of element " + el + " in queue = " + queue.count(el) + '\n');
    }
}

