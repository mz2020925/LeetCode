package LeetcodeHot100.linkList;

class q12_sortList {

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

    // 解法1 -- 15ms
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }

        ListNode newHead = new ListNode(0, head);
        for (int subLen = 1; subLen < len; subLen <<= 1) {
            ListNode preTail = newHead;
            ListNode cur = newHead.next;

            while (cur != null) {
                // 1.merge时的左边链表头节点
                ListNode sub1Head = cur;
                for (int i = 1; i < subLen && cur != null && cur.next != null; i++) {
                    cur = cur.next;
                }

                // 2.merge时的右边链表头节点
                ListNode sub2Head = cur.next;
                cur.next = null;// 断开第一个链表和第二个链表的连接
                cur = sub2Head;
                for (int i = 1; i < subLen && cur != null && cur.next != null; i++) {
                    cur = cur.next;
                }

                // 3.剩下部分
                ListNode restHead = null;
                if (cur != null) {
                    restHead = cur.next;  // 剩下部分的头节点
                    cur.next = null;// 断开第二个链表和剩余链表的连接
                }

                // 到这里，已经拆分完毕了，head_1、head_2 都为一条单独的链表，next 为剩下未拆分的链表

                ListNode merged = mergeTwoLists(sub1Head, sub2Head);

                preTail.next = merged;
                // 将mergedCur移动到subLen × 2 的位置（就是移动到尾巴位置），以连接下一次合并的结果
                while (preTail.next != null) {
                    preTail = preTail.next;
                }
                cur = restHead;// 将剩余链表next赋给cur，继续下一次的拆分（循环合并剩下的链表）
            }
        }
        return newHead.next;
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


    // 解法2 -- 12ms， // TODO 这种方法还没研读，这种方法就是把合并两条升序链表的方法囊括到本方法内部，没有单独写一个方法
    public ListNode sortList2(ListNode head) {
        ListNode h, h1, h2, pre, res;
        h = head;
        int length = 0, intv = 1;
        while (h != null) {
            h = h.next;
            length++;
        }
        res = new ListNode(0);
        res.next = head;
        while (intv < length) {
            pre = res;
            h = res.next;
            while (h != null) {
                int i = intv;
                h1 = h;
                while (i > 0 && h != null) {
                    h = h.next;
                    i--;
                }
                if (i > 0) break;
                i = intv;
                h2 = h;
                while (i > 0 && h != null) {
                    h = h.next;
                    i--;
                }
                int c1 = intv, c2 = intv - i;
                while (c1 > 0 && c2 > 0) {
                    if (h1.val < h2.val) {
                        pre.next = h1;
                        h1 = h1.next;
                        c1--;
                    } else {
                        pre.next = h2;
                        h2 = h2.next;
                        c2--;
                    }
                    pre = pre.next;
                }
                pre.next = c1 == 0 ? h2 : h1;
                while (c1 > 0 || c2 > 0) {
                    pre = pre.next;
                    c1--;
                    c2--;
                }
                pre.next = h;
            }
            intv *= 2;
        }
        return res.next;
    }
}
