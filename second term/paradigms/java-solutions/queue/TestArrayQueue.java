package queue;

public class TestArrayQueue {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();

        fill(queue, 5);
        dequeueTest(queue, 3);
        enqueueTest(queue, 100);
        enqueueTest(queue, 1001);
        enqueueTest(queue, 100);
        countTest(queue, 100);
        clearTests(queue);
    }

    public static void fill(ArrayQueue queue, int a) {
        for (int i = 0; i < a; i++) {
            queue.enqueue(i);
        }
        System.out.println("filled");
        System.out.print("new queue: ");
        //printQueue(queue);
    }

    public static void enqueueTest(ArrayQueue queue, int i) {
        queue.enqueue(i);
        System.out.println("added " + i);
        System.out.print("new queue: ");
        //printQueue(queue);
    }

    public static void dequeueTest(ArrayQueue queue, int a) {
        for (int i = 0; i < a; i++) {
            queue.dequeue();
        }
        System.out.println("delited " + a + " elements");
        System.out.print("new queue: ");
        //printQueue(queue);
    }

    /*public static void printQueue(ArrayQueue queue) {
        System.out.println("size: " + queue.size() + "; queue: " + Arrays.toString(queue.toArray()) + "; queue is emppty = " + queue.isEmpty() + '\n');
    }*/

    public static void clearTests(ArrayQueue queue) {
        queue.clear();
        System.out.println("cleared");
        //printQueue(queue);
    }

    public static void countTest(ArrayQueue queue, Object el) {
        System.out.println("number of element " + el + " in queue = " + queue.count(el) + '\n');
    }
}