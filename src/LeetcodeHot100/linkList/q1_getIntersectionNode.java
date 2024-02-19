package LeetcodeHot100.linkList;


import org.junit.Test;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class q1_getIntersectionNode {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode() {
        }
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode cur1 = headA;
        ListNode cur2 = headB;

        int n = 1;
        while (cur1.next != null) {
            cur1 = cur1.next;
            n++;
        }
        while (cur2.next != null) {
            cur2 = cur2.next;
            n--;
        }

        // 如果两个链表的末尾节点不相同，一定不相交
		if (cur1 != cur2) {
			return null;
		}

        // cur1指向较长的链表
        cur1 = n > 0 ? headA : headB;
        cur2 = cur1 == headA ? headB : headA;
        n = Math.abs(n);

        while (n > 0) {
            cur1 = cur1.next;
            n--;
        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    @Test
    public void test() {
        ListNode listA1 = new ListNode(4);
        ListNode listA2 = new ListNode(1);

        ListNode listB1 = new ListNode(5);
        ListNode listB2 = new ListNode(6);
        ListNode listB3 = new ListNode(1);

        ListNode share1 = new ListNode(8);
        ListNode share2 = new ListNode(4);
        ListNode share3 = new ListNode(5);

        ListNode headA = new ListNode();
        headA.next = listA1;
        listA1.next = listA2;
        listA2.next = share1;

        ListNode headB = new ListNode();
        headB.next = listB1;
        listB1.next = listB2;
        listB2.next = listB3;
        listB3.next = share1;

        share1.next = share2;
        share2.next = share3;
        share3.next = null;

        ListNode res = getIntersectionNode(headA, headB);
        System.out.println(res.val);
    }
}
