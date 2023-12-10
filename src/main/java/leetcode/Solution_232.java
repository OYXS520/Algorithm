package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
public class Solution_232{
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        myQueue.push(4);
        myQueue.push(5);
        myQueue.push(6);
        System.out.println(myQueue.peek());
        System.out.println(myQueue.peek());
    }
    static class MyQueue {
        Deque<Integer> pre = new ArrayDeque<>();
        Deque<Integer> end = new ArrayDeque<>();
        public MyQueue() {
        }

        public void push(int x) {
            end.addLast(x);
        }

        public int pop() {
            if (!pre.isEmpty()){
                return pre.pollLast();
            }
            int endElement = end.pollLast();
            while (!end.isEmpty()){
                pre.addLast(endElement);
                endElement = end.pollLast();
            }
            return endElement;
        }

        public int peek() {
            if (!pre.isEmpty()){
                return pre.getLast();
            }
            int endElement = end.pollLast();
            while (!end.isEmpty()){
                pre.addLast(endElement);
                endElement = end.pollLast();
            }
            pre.addLast(endElement);
            return endElement;
        }

        public boolean empty() {
            return end.isEmpty() && pre.isEmpty();
        }


    }
}
