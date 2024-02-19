package LeetcodeHot100.linkList;

public class q11_copyRandomList {
    class Node{
        int val;
        Node next;
        Node random;

        public Node(int val){
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        Node cur = head;
        Node next = head.next;
        while(cur != null){
            Node copyCur = new Node(cur.val);
            cur.next = copyCur;
            copyCur.next = next;

            cur = next;
            next = (cur==null? null:cur.next);
        }

        cur = head;
        next = head.next;
        while(cur != null){
            next.random = (cur.random == null? null:cur.random.next);

            cur = next.next;
            next = (cur==null?null:cur.next);
        }

        cur = head;
        next = head.next;
        Node newHead = head.next;
        while(cur!=null){
            cur.next = next.next;
            next.next = (cur.next == null? null:cur.next.next);

            cur = cur.next;
            next = next.next;
        }

        return newHead;
    }
}
