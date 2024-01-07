package LeetcodeHot100.linkList;


public class q206_reverseList {
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

    // head指向第一个节点，是有值的
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode next = null;
        ListNode temp = null;
        while (cur != null) {
            next = cur.next;
            cur.next = temp;
            temp = cur;

            cur = next;
        }

        return temp;  // temp最后会成为反转后的头节点，就是第一个节点
    }
}
