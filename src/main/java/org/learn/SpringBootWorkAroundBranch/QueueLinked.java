package org.learn.SpringBootWorkAroundBranch;

public class QueueLinked {
    public static void main(String[] args) {
        Queuel que = new Queuel();
        que.enQueue(1);
        que.enQueue(2);
        que.enQueue(3);
        que.print();
        que.enQueue(4);
        que.print();
        System.out.println("que.front.no = " + que.front.no);
        System.out.println("que.rear.no = " + que.rear.no);
        que.peek();
    }
}


class Node {
    int no;
    Node next;
    public Node(int no) {
        this.no = no;
    }
}


class Queuel {
    Node front;
    Node rear;

    public void enQueue(int data) {
        Node newNode = new Node(data);
        if(front==null) {
            front = newNode;
            rear = newNode;
            return;
        }
        Node temp = front;
        while(temp!=rear) {
            temp = temp.next;
        }
        temp.next = newNode;
        rear = temp.next;
    }

    public void deQueue() {
        if(front==null) {
            throw new RuntimeException("Queue is empty");
        }
        if(rear==null) {
            throw new RuntimeException("Queue is empty");
        }
        front = front.next;
    }

    public void peek() {
        System.out.println("front = " + front.no);
    }

    public void print() {
        Node temp = front;
        while(temp!=null) {
            System.out.println("temp = " + temp.no);
            temp = temp.next;
        }
        System.out.println();
    }
}
