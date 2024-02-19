package LeetcodeHot100.linkList;

import org.junit.Test;

public class q3_isPalindrome {

    public class ListNode {
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

    // 个人思路
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }

        StringBuilder builder1 = new StringBuilder();
        ListNode cur1 = head;
        while (cur1 != null) {
            builder1.append(cur1.val);
            cur1 = cur1.next;
        }
        String str1 = builder1.toString();

        StringBuilder builder2 = new StringBuilder();
        ListNode cur2 = reverseList(head);
        while (cur2 != null) {
            builder2.append(cur2.val);
            cur2 = cur2.next;
        }
        String str2 = builder2.toString();

        return str1.equals(str2);

    }

    // 官方思路
    public boolean isPalindrome2(ListNode head) {
        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = firstHalfEnd(head);
        ListNode secondHalfStart = firstHalfEnd.next;
        ListNode secondHalfRevertHead = reverseList(secondHalfStart);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfRevertHead;
        boolean res = true;
        while (p2 != null) {
            if (p1.val != p2.val) {
                res = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        // 还原链表并返回结果
        reverseList(secondHalfRevertHead);
        return res;


    }

    public ListNode firstHalfEnd(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode temp = null;
        ListNode next = null;

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
        head.next.next = new ListNode(2);
        // head.next.next.next = new ListNode(1);

        boolean res = isPalindrome2(head);
        System.out.println(res);

    }
}
