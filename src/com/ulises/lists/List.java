package com.ulises.lists;

public class List<T> {

    ListNode<T> head;

    public List() {
        this.head = null;
    }

    public List(T val) {
        this.head = new ListNode(val);
    }

    public void append(T val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            head = node;
            return;
        }
        ListNode r = this.head;
        while(r.next != null) {
            r = r.next;
        }
        r.next = node;
    }

    public void print() {
        ListNode r = this.head;
        while(r != null) {
            System.out.print(r.val + " -> ");
            r = r.next;
        }
        System.out.println("null");
    }

    public boolean delete(T val) {
        if (this.head.val == val) {
            head = head.next;
            return true;
        }
        ListNode r = this.head;
        while(r.next != null) {
            if (r.next.val == val) {
                r.next = r.next.next;
                return true;
            }
            r = r.next;
        }
        return false;
    }

    public void printReverseRecursive(ListNode node) {
        if (node == null) return;
        printReverseRecursive(node.next);
        System.out.print(node .val + " -> ");
    }

    public static void main(String[] args) {
        List<Integer> list = new List<>();
        list.append(4);
        list.append(6);
        list.append(7);
        list.append(9);
        list.printReverseRecursive(list.head);
    }
}
