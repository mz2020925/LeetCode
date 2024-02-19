package LeetcodeHot100.linkList;

import org.junit.Test;

public class q10_reverseKGroup {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode newHead = new ListNode();
        newHead.next = head;

        ListNode preTail = newHead;
        ListNode curHead = head;
        ListNode nextHead;


        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }

        int n = len / k;
        ListNode cur;
        while (n > 0) {
            cur = curHead;
            int i = k - 1;
            while (i > 0) {
                cur = cur.next;
                i--;
            }
            nextHead = cur.next;
            cur.next = null;
            preTail.next = reverse(curHead);

            preTail = curHead;
            curHead = nextHead;

            n--;
        }

        if (curHead != null) {
            preTail.next = curHead;
        }


        return newHead.next;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;


        while (cur != null) {
            next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return pre;
    }

    @Test
    public void test() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        ListNode res = reverse(head);
        while (res != null) {
            System.out.print(res.val);
            res = res.next;
        }
    }
}



