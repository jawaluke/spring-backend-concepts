package org.learn.SpringBootWorkAroundBranch;

import java.util.Arrays;

public class Demo {

    public static void main(String[] args) {
        Queue queue = new Queue(5);
        queue.enQueue("abhram");
        queue.enQueue("bhira");
        queue.enQueue("cataline");
        queue.enQueue("doug");
        queue.enQueue("Eleine");
        queue.print();
        queue.deQueue();
        queue.print();
        queue.deQueue();
        queue.deQueue();
        queue.print();
        queue.deQueue();
        queue.print();
        queue.enQueue("Felicia");
        queue.enQueue("Golam");
        queue.enQueue("harry");
        queue.print();
        queue.deQueue();
        queue.print();
    }
}

class Queue {
    private int size;
    private int count = 0;
    private String[] queue;
    private int front = 0;
    private int rear = -1;

    public Queue(int size) {
        if(count >= size) {
            throw new RuntimeException("Queue size should be greater than 0");
        }
        this.queue = new String[size];
        this.size = size;
    }

    public void enQueue(String value) {
        if(count >= size) {
            throw new RuntimeException("Queue size exceeded");
        }
        rear = (rear + 1) % size;
        queue[rear] = value;
        count++;
    }

    public String deQueue() {
        if(front==rear) {
            throw new RuntimeException("no element to delete");
        }
        String val = queue[front];
        front = (front + 1) % size;
        count--;
        return val;
    }

    public void print() {
        System.out.println(" = "+ Arrays.asList(queue).toString());
        System.out.println("front = " + front + " rear = "+ rear);
        System.out.println("top = " + queue[front]);
    }

}