package LeetcodeHot100.linkList;

import org.junit.Test;

public class q8_removeNthFromEnd {
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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }  // 当在头节点开头再接一个结点的时候，这里的判断就可以省去了
        ListNode newHead = new ListNode(0, head);

        ListNode fast = newHead;
        ListNode slow = newHead;

        n += 1;

        while (n > 0 && fast != null) {
            fast = fast.next;
            n--;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return newHead.next;
    }

    public ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode next = null;
        ListNode temp = null;
        while (cur != null) {
            next = cur.next;
            cur.next = temp;
            temp = cur;
            cur = next;
        }
        return temp;
    }

    @Test
    public void test() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next = new ListNode(3);
    }
}
