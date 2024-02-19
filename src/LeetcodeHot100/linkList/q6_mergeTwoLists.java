package LeetcodeHot100.linkList;

public class q6_mergeTwoLists {
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode tempHead = new ListNode(0);
        ListNode p1 = list1;
        ListNode p2 = list2;
        ListNode p0 = tempHead;

        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p0.next = p1;
                p1 = p1.next;
            } else {
                p0.next = p2;
                p2 = p2.next;
            }
            p0 = p0.next;
        }
        if (p1 == null) {
            p0.next = p2;
        } else {
            p0.next = p1;
        }
        return tempHead.next;
    }
}
