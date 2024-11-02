package org.learn.SpringBootWorkAroundBranch;

import java.util.Stack;

public class Sam {
    public static void main(String[] args) {
//        implement queue using stack
        Queues que = new Queues(3);
        que.enQueue(1);
        que.enQueue(2);
        que.enQueue(3);
        que.peek();

        que.enQueue(4);
        que.peek();

        que.deQueue();
        que.peek();

        que.deQueue();
        que.deQueue();
        que.peek();

        System.out.println("que.count = " + que.count);
    }
}

class Queues {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    int count = 0;
    int size = 0;

    public Queues(int size) {
        this.size = size;
    }

    public void enQueue(int no) {
        if(size <= count) {
            throw new RuntimeException("Queue size Exceeded");
        }
        stack1.push(no);
        count++;
    }

    public void deQueue() {
        var index = 0;
        if(stack2.empty()) {
            if(stack1.empty()) {
                throw new RuntimeException("Queue is empty");
            }
            while(index!=count) {
                stack2.push(stack1.pop());
                index++;
            }
        }
        count--;
        stack2.pop();
    }

    public void peek() {
        var index = 0;
        if(stack2.empty()) {
            if(stack1.empty()) {
                throw new RuntimeException("Queue is empty");
            }
            while(index!=count) {
                stack2.push(stack1.pop());
                index++;
            }
        }
        System.out.println("top = " + stack2.peek());
        System.out.println();
    }

}
