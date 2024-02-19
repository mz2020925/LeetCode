package LeetcodeHot100.linkList;

import org.junit.Test;

public class q7_addTwoNumbers {
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head1 = l1;
        ListNode head2 = l2;
        ListNode res = new ListNode(0);
        ListNode head = res;

        int value = 0;  // 存储进位信息
        while (head1 != null || head2 != null) {  // 当两个链表长度不一样时，还有这种遍历方式
            int x = head1 == null ? 0 : head1.val;
            int y = head2 == null ? 0 : head2.val;
            int sum = x + y + value;

            value = sum / 10;  // 这道题进位信息就是1
            head.next = new ListNode(sum % 10);
            head = head.next;

            if (head1 != null) {  // head1已经指向链表尾部的null，就不再往后移动了
                head1 = head1.next;
            }
            if (head2 != null) {  // head2已经指向链表尾部的null，就不再往后移动了
                head2 = head2.next;
            }
        }
        if (value == 1) {
            head.next = new ListNode(value);
        }

        return res.next;
    }

    @Test
    public void test() {
        ListNode head1 = new ListNode(2);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(3);

        ListNode head2 = new ListNode(5);
        head2.next = new ListNode(6);
        head2.next.next = new ListNode(4);

        ListNode res = addTwoNumbers(head1, head2);
        while (res != null) {
            System.out.print(res.val + "->");
            res = res.next;
        }
    }
}
