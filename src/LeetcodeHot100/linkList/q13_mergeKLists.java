package LeetcodeHot100.linkList;

public class q13_mergeKLists {
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

    // 自底向上归并排序 // TODO 但是耗时并不少
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        if(lists.length == 1){
            return lists[0];
        }

        ListNode newHead = lists[0];
        for(int i = 1;i < lists.length;i++){
            newHead = merge(newHead, lists[i]);
        }

        return newHead;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode newHead = new ListNode(0);
        ListNode p0 = newHead;
        ListNode p1 = head1;
        ListNode p2 = head2;

        while(p1 !=null && p2 != null) {
            if(p1.val < p2.val) {
                p0.next = p1;
                p1 = p1.next;
            } else {
                p0.next = p2;
                p2 = p2.next;
            }

            p0 = p0.next;
        }

        if(p1 == null){
            p0.next = p2;
        } else {
            p0.next = p1;
        }

        return newHead.next;
    }
}
