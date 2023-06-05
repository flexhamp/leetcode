package com.flexhamp.leetcode;

import java.util.Arrays;

public class MergeSortedLists {

    public static void main(String[] args) {
        ListNode[] lists = new ListNode[]{
                new ListNode(1, new ListNode(4, new ListNode(5))),
                new ListNode(1, new ListNode(3, new ListNode(4))),
                new ListNode(2, new ListNode(6))
        };
        mergeKLists(lists);
    }

    static class ListNodeHead {
        private ListNode head;
        private ListNode tail;

        public ListNode getHead() {
            return head;
        }

        public void setHead(ListNode head) {
            this.head = head;
        }

        public ListNode getTail() {
            return tail;
        }

        public void setTail(ListNode tail) {
            this.tail = tail;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNodeHead res = new ListNodeHead();

        int q = lists.length;
        while (q > 0) {
            q = aggregator(lists, res, q);
        }

//        System.out.println(res.getHead().val);
        return new ListNode();
    }

    public static int aggregator(ListNode[] lists, ListNodeHead node, int length) {
        int r = 0;
        int[] tmp = new int[length];
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                tmp[i] = lists[i].val;
                if (lists[i].next != null) {
                    lists[i] = lists[i].next;
                    r++;
                } else {
                    lists[i] = null;
                }
            }
        }

        Arrays.sort(tmp);
        for (int j : tmp) {
            if (node.getHead() == null) {
                node.setHead(new ListNode(j));
                node.setTail(node.getHead());
            } else {
                node.setHead(g(node, j));//                node.getTail().next = new ListNode(j);
//                node.setTail(node.getTail().next);
            }
        }

        return r;
    }

    static ListNode g(ListNodeHead node, int i) {
        ListNode newListNode = new ListNode(i);
        ListNode head = node.getHead();
        ListNode prev = null;
        ListNode curr = new ListNode(node.getHead().val, node.getHead().next);

        while (true) {
            if (i <= curr.val) {
                if (prev == null) {
                    prev = newListNode;
                    if (head == null) {
                        head = newListNode;
                    } else {
                        head = prev;
                        prev.next = curr;
                    }
                }  else {
                    newListNode.next = curr;
                    prev.next = newListNode;
                }
                break;
            } else {
                if (curr.next == null) {
                    curr.next = new ListNode(i);
                    break;
                }

                curr = curr.next;
                if (prev == null) {
                    prev = head;
                } else {
                    prev = prev.next;
                }
            }
        }

        return head;
    }

    public static class ListNode {
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
}
