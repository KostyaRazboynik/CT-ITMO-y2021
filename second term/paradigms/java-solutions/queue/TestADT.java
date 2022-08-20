package queue;

import java.util.Arrays;

public class TestADT {

    public static void main(String[] args) {
        ArrayQueueADT queue = new ArrayQueueADT();

        fill(queue, 5);
        denqueTest(queue, 3);
        enqueueTest(queue, 100);
        enqueueTest(queue, 1001);
        enqueueTest(queue, 100);
        countTest(queue, 100);
        clearTets(queue);
    }

    public static void fill(ArrayQueueADT queue, int a) {
        for (int i = 0; i < a; i++) {
            ArrayQueueADT.enqueue(queue, i);
        }
        System.out.println("filled");
        System.out.print("new queue: ");
        printQueue(queue);
    }

    public static void enqueueTest(ArrayQueueADT queue, int i) {
        ArrayQueueADT.enqueue(queue, i);

        System.out.println("added " + i);
        System.out.print("new queue: ");
        printQueue(queue);
    }

    public static void denqueTest(ArrayQueueADT queue, int a) {
        for (int i = 0; i < a; i++) {
            ArrayQueueADT.dequeue(queue);
        }
        System.out.println("delited " + a + " elements");
        System.out.print("new queue: ");
        printQueue(queue);
    }

    public static void printQueue(ArrayQueueADT queue) {
        System.out.println("size: " + queue.size(queue) + "; queue: " + Arrays.toString(ArrayQueueADT.toArray(queue)) + "; queue is emppty = " + ArrayQueueADT.isEmpty(queue) + '\n');
    }

    public static void clearTets(ArrayQueueADT queue) {
        ArrayQueueADT.clear(queue);
        System.out.println("cleared");
        printQueue(queue);
    }

    public static void countTest(ArrayQueueADT queue, Object el) {
        System.out.println("number of element " + el + " in queue = " + ArrayQueueADT.count(queue, el) + '\n');
    }
}