package leetcode;

import java.util.Deque;
import java.util.LinkedList;

class Solution_225 {

    static class MyStack {
        private Deque<Integer> pre = new LinkedList();
        private Deque<Integer> end = new LinkedList();
        public MyStack() {

        }

        public void push(int x) {
            pre.addLast(x);
            while (!end.isEmpty()) {
                pre.addLast(end.pollFirst());
            }
            Deque<Integer> tem = pre;
            pre = end;
            end = tem;
        }

        public int pop() {
            return end.pollFirst();
        }

        public int top() {
            return end.getFirst();
        }

        public boolean empty() {
            return pre.isEmpty() && end.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        System.out.println(myStack.top());
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
        myStack.push(5);
        myStack.push(6);
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());

    }
}