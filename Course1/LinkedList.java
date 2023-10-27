public class LinkedList {

    private class Node {
        private int value;
        private Node next;
        private Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head;

    private LinkedList() {
        head = null;
    }

    public void addToBack(int value) {
        Node newNode = new Node(value, null);
        if(head == null) {
            head = newNode;
            return;
        }
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = newNode;
    }

    public void listPrint() {
        if (head == null) {
            System.out.println();
            return;
        }
        Node cur = head;
        while (cur.next != null) {
            System.out.println(cur.value);
            cur = cur.next;
        }
        System.out.println(cur.value);
        System.out.println();
        return;
    }

    public void removeDuplicates() {
        duplicateRemover(head);
    }

    private void duplicateRemover(Node cur) {
        if (cur == null || cur.next == null) {
            return;
        }
        duplicateRemover(cur.next);
        if (cur.value == cur.next.value) {
            cur.next = cur.next.next;
        }
    }

    public static void main(String[] args) {
        LinkedList myList = new LinkedList();
        myList.addToBack(1);
        myList.addToBack(1);
        myList.addToBack(2);
        myList.addToBack(2);
        myList.addToBack(2);
        myList.addToBack(2);
        myList.addToBack(3);
        myList.addToBack(3);
        myList.listPrint();
        myList.removeDuplicates();
        myList.listPrint();
    }
}
