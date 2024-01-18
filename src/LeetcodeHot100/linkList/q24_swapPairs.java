package LeetcodeHot100.linkList;


import org.junit.Test;

public class q24_swapPairs {
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
    // 解法一：双指针
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        head = head.next;
        while (true) {
            ListNode temp = fast.next;
            fast.next = slow;
            if (temp != null) {
                if (temp.next != null) {  // 1、后面有两个节点
                    slow.next = temp.next;
                }else {
                    slow.next = temp;  // 2、后面有1个节点
                    break;
                }
            } else {  // 3、后面没有节点了
                slow.next = null;
                break;
            }
            fast = temp.next;
            slow = temp;
        }
        return head;
    }


    // 解法二：递归






    @Test
    public void test() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        ListNode res = swapPairs(head);
        while (res != null) {
            System.out.print(res.val + "->");
            res = res.next;
        }

    }
}
