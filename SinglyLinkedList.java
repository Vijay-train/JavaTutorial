import java.util.ArrayList;

/**
 * SINGLY LINKED LIST - Practice Session
 *
 * This file covers fundamental singly linked list operations:
 * - Node insertion (beginning, end, middle)
 * - Node deletion (beginning, end, middle)
 * - Traversal and search
 * - Reversal
 * - Cycle detection
 * - Finding middle and kth element
 *
 * A singly linked list node has data and a next pointer:
 *   [data | next] -> [data | next] -> [data | next] -> null
 */

public class SinglyLinkedList {
    // Global trace flag: set to true when program started with `--trace`
    public static boolean TRACE = false;

    /**
     * Node class for singly linked list
     */
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public SinglyLinkedList() {
        this.head = null;
    }

    /**
     * Title: Insert at Beginning
     *
     * Problem (what it solves): Insert a new node with given data at the start of the
     * singly linked list. This is useful for LIFO (stack-like) insertion and is O(1) time.
     *
     * Output: The list now has the new value at head; all previous nodes shift logically.
     *
     * Approach:
     * - Create a new node with the data.
     * - Set the new node's next pointer to the current head.
     * - Update head to point to the new node.
     *
     * Example Walkthrough (list initially empty, then inserting 10, 20, 30):
     * - Initial: head = null
     * - Insert 10:
     *     newNode = Node(10), newNode.next = null
     *     head = newNode
     *     List: 10 -> null
     * - Insert 20:
     *     newNode = Node(20), newNode.next = head (10)
     *     head = newNode
     *     List: 20 -> 10 -> null
     * - Insert 30:
     *     newNode = Node(30), newNode.next = head (20)
     *     head = newNode
     *     List: 30 -> 20 -> 10 -> null
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        if (TRACE) System.out.println("[TRACE] inserted " + data + " at beginning");
    }

    /**
     * Title: Insert at End
     *
     * Problem (what it solves): Add a new node with given data at the end of the
     * singly linked list. Useful for FIFO (queue-like) insertion. Requires traversal
     * to find the last node, so O(n) time.
     *
     * Output: The list now has the new value at the tail; old structure preserved.
     *
     * Approach:
     * - If list is empty, insert at head (O(1)).
     * - Otherwise, traverse to the last node (next == null).
     * - Update the last node's next to point to the new node.
     *
     * Example Walkthrough (list initially 10 -> 20 -> null, inserting 30):
     * - Initial: head = Node(10), head.next = Node(20), head.next.next = null
     *     List: 10 -> 20 -> null
     * - Insert 30:
     *     newNode = Node(30)
     *     Traverse: current = head(10) -> current = head.next(20) -> current.next = null (found last)
     *     lastNode.next = newNode
     *     List: 10 -> 20 -> 30 -> null
     *
     * Time Complexity: O(n) where n is length of list
     * Space Complexity: O(1)
     */
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            if (TRACE) System.out.println("[TRACE] inserted " + data + " at end (empty list)");
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        if (TRACE) System.out.println("[TRACE] inserted " + data + " at end");
    }

    /**
     * Title: Insert at Position
     *
     * Problem (what it solves): Insert a new node at a specific position (index) in the list.
     * Position 0 is the head; position n is after n nodes. If position is invalid or out of
     * bounds, the insertion is skipped.
     *
     * Output: The list has the new node at the specified position; existing nodes shift.
     *
     * Approach:
     * - If position is 0, insert at beginning.
     * - Otherwise, traverse to the (position-1)th node.
     * - Create new node and insert it by updating pointers.
     * - If position is beyond list length, insertion may fail.
     *
     * Example Walkthrough (list initially 10 -> 20 -> 30 -> null, inserting 15 at position 1):
     * - Initial: head = Node(10) -> Node(20) -> Node(30) -> null
     * - Insert 15 at position 1:
     *     Position 0 is not requested, so find node at position (1-1)=0
     *     prevNode = head (Node 10)
     *     newNode = Node(15)
     *     newNode.next = prevNode.next (Node 20)
     *     prevNode.next = newNode
     *     List: 10 -> 15 -> 20 -> 30 -> null
     *
     * Time Complexity: O(n) for traversal to position
     * Space Complexity: O(1)
     */
    public void insertAtPosition(int data, int position) {
        if (position < 0) {
            if (TRACE) System.out.println("[TRACE] invalid position " + position);
            return;
        }

        if (position == 0) {
            insertAtBeginning(data);
            return;
        }

        Node current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }

        if (current == null) {
            if (TRACE) System.out.println("[TRACE] position " + position + " out of bounds");
            return;
        }

        Node newNode = new Node(data);
        newNode.next = current.next;
        current.next = newNode;
        if (TRACE) System.out.println("[TRACE] inserted " + data + " at position " + position);
    }

    /**
     * Title: Delete at Beginning
     *
     * Problem (what it solves): Remove the first node (head) from the list. O(1) operation.
     * If list is empty, nothing happens.
     *
     * Output: The list now has the second node as head (or becomes empty if only one node).
     *
     * Approach:
     * - Check if list is empty; if so, return.
     * - Update head to head.next (skip the first node).
     * - First node is dereferenced and garbage collected.
     *
     * Example Walkthrough (list initially 10 -> 20 -> 30 -> null, deleting from beginning):
     * - Initial: head = Node(10), head.next = Node(20), ...
     *     List: 10 -> 20 -> 30 -> null
     * - Delete from beginning:
     *     head = head.next (now points to Node 20)
     *     List: 20 -> 30 -> null
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int deleteAtBeginning() {
        if (head == null) {
            if (TRACE) System.out.println("[TRACE] list is empty, cannot delete");
            return -1; // sentinel value for error
        }

        int data = head.data;
        head = head.next;
        if (TRACE) System.out.println("[TRACE] deleted " + data + " from beginning");
        return data;
    }

    /**
     * Title: Delete at End
     *
     * Problem (what it solves): Remove the last node from the list. Requires traversal
     * to find the second-to-last node, so O(n) time.
     *
     * Output: The list now has the second-to-last node as tail; tail.next = null.
     *
     * Approach:
     * - If list is empty, return.
     * - If head.next is null (only one node), delete head.
     * - Otherwise, traverse to the second-to-last node.
     * - Set second-to-last's next to null.
     *
     * Example Walkthrough (list initially 10 -> 20 -> 30 -> null, deleting from end):
     * - Initial: head = Node(10) -> Node(20) -> Node(30) -> null
     * - Delete from end:
     *     Traverse: current = head -> current = head.next(20) -> current.next != null -> continue
     *     Traverse: current = head.next.next(30) is out (20.next = 30, so stop before 30)
     *     Actually, we track prevNode to find second-to-last.
     *     prevNode = head(10) -> current = head.next(20)
     *     current.next(30) != null, so move: prevNode = 20, current = 30
     *     current.next == null, so prevNode is second-to-last
     *     prevNode.next = null
     *     List: 10 -> 20 -> null
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int deleteAtEnd() {
        if (head == null) {
            if (TRACE) System.out.println("[TRACE] list is empty, cannot delete");
            return -1;
        }

        if (head.next == null) {
            int data = head.data;
            head = null;
            if (TRACE) System.out.println("[TRACE] deleted " + data + " from end (only node)");
            return data;
        }

        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        int data = current.next.data;
        current.next = null;
        if (TRACE) System.out.println("[TRACE] deleted " + data + " from end");
        return data;
    }

    /**
     * Title: Delete at Position
     *
     * Problem (what it solves): Remove the node at a specific index from the list.
     * Position 0 is the head. If position is invalid, deletion is skipped.
     *
     * Output: The list has the node removed; adjacent nodes re-linked.
     *
     * Approach:
     * - If position is 0, delete at beginning.
     * - Otherwise, traverse to the (position-1)th node.
     * - Update pointers to bypass the target node.
     * - If position is out of bounds, deletion fails silently.
     *
     * Example Walkthrough (list initially 10 -> 20 -> 30 -> null, deleting at position 1):
     * - Initial: head = Node(10) -> Node(20) -> Node(30) -> null
     * - Delete at position 1:
     *     Position 0 is not requested, so find node at position (1-1)=0
     *     prevNode = head (Node 10)
     *     nodeToDelete = prevNode.next (Node 20)
     *     prevNode.next = nodeToDelete.next (Node 30)
     *     List: 10 -> 30 -> null
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int deleteAtPosition(int position) {
        if (position < 0 || head == null) {
            if (TRACE) System.out.println("[TRACE] invalid position or empty list");
            return -1;
        }

        if (position == 0) {
            return deleteAtBeginning();
        }

        Node current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }

        if (current == null || current.next == null) {
            if (TRACE) System.out.println("[TRACE] position " + position + " out of bounds");
            return -1;
        }

        int data = current.next.data;
        current.next = current.next.next;
        if (TRACE) System.out.println("[TRACE] deleted " + data + " at position " + position);
        return data;
    }

    /**
     * Title: Traverse and Print
     *
     * Problem (what it solves): Display all nodes in the list in order. Useful for
     * verification and debugging. O(n) traversal.
     *
     * Output: Print each node's data in sequence, ending with "null" to show list termination.
     *
     * Approach:
     * - Start from head.
     * - Visit each node and print its data.
     * - Follow next pointers until reaching null.
     *
     * Example Walkthrough (list 10 -> 20 -> 30 -> null):
     * - current = head (Node 10)
     * - Print: 10 -> current = current.next
     * - Print: 20 -> current = current.next
     * - Print: 30 -> current = current.next
     * - current = null, stop
     * - Output: "10 -> 20 -> 30 -> null"
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void display() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    /**
     * Title: Search for an Element
     *
     * Problem (what it solves): Find if a given value exists in the list and return its
     * position (0-indexed). If not found, return -1. O(n) linear search.
     *
     * Output: Position (index) of the element if found; -1 otherwise.
     *
     * Approach:
     * - Traverse the list from head.
     * - Compare each node's data with the target.
     * - Return position on first match; return -1 if we reach null.
     *
     * Example Walkthrough (list 10 -> 20 -> 30 -> null, searching for 20):
     * - current = head (Node 10), position = 0
     * - data (10) != target (20), move to next
     * - current = Node 20, position = 1
     * - data (20) == target (20), return 1
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int search(int data) {
        Node current = head;
        int position = 0;
        while (current != null) {
            if (current.data == data) {
                if (TRACE) System.out.println("[TRACE] found " + data + " at position " + position);
                return position;
            }
            current = current.next;
            position++;
        }
        if (TRACE) System.out.println("[TRACE] " + data + " not found in list");
        return -1;
    }

    /**
     * Title: Reverse the List (iterative)
     *
     * Problem (what it solves): Reverse the order of all nodes in the list in-place.
     * After reversal, the last node becomes head and head becomes tail.
     * O(n) time, O(1) space (no extra data structures).
     *
     * Output: The list is reversed; original head now points to null.
     *
     * Approach (three-pointer technique):
     * - Maintain prev, current, and next pointers.
     * - Start with prev = null, current = head.
     * - Save current.next before reversing the link.
     * - Reverse: current.next = prev.
     * - Move: prev = current, current = next.
     * - Repeat until current becomes null.
     *
     * Example Walkthrough (list initially 10 -> 20 -> 30 -> null):
     * - Step 0: prev = null, current = Node(10), next = Node(20)
     * - Step 1: reverse link: Node(10).next = null
     *     prev = Node(10), current = Node(20), next = Node(30)
     * - Step 2: reverse link: Node(20).next = Node(10)
     *     prev = Node(20), current = Node(30), next = null
     * - Step 3: reverse link: Node(30).next = Node(20)
     *     prev = Node(30), current = null
     * - head = prev = Node(30)
     * - Result: 30 -> 20 -> 10 -> null
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void reverse() {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head = prev;
        if (TRACE) System.out.println("[TRACE] list reversed");
    }

    /**
     * Title: Detect Cycle (Floyd's cycle detection)
     *
     * Problem (what it solves): Determine if the linked list contains a cycle (a node
     * points back to an earlier node, creating an infinite loop). Uses two pointers at
     * different speeds (tortoise & hare). O(n) time, O(1) space.
     *
     * Output: true if a cycle exists; false if the list is acyclic (terminates at null).
     *
     * Approach (Floyd's algorithm):
     * - Use slow pointer (moves 1 step) and fast pointer (moves 2 steps).
     * - If both pointers meet at some node, a cycle exists.
     * - If fast reaches null, no cycle.
     * - Why it works: in a cycle, fast catches up to slow because it gains 1 position per iteration.
     *
     * Example Walkthrough (cycle: 10 -> 20 -> 30 -> 20):
     * - Initial: slow = Node(10), fast = Node(10)
     * - Iteration 1: slow = Node(20), fast = Node(30)
     * - Iteration 2: slow = Node(30), fast = Node(20)
     * - Iteration 3: slow = Node(20), fast = Node(30)
     * - Iteration 4: slow = Node(30), fast = Node(20)
     * (fast and slow are trapped in cycle between 20 and 30)
     * - Eventually they meet; return true
     *
     * Example Walkthrough (no cycle: 10 -> 20 -> 30 -> null):
     * - Initial: slow = Node(10), fast = Node(10)
     * - Iteration 1: slow = Node(20), fast = Node(30)
     * - Iteration 2: slow = Node(30), fast = null
     * - fast == null, return false
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public boolean hasCycle() {
        if (head == null || head.next == null) return false;

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                if (TRACE) System.out.println("[TRACE] cycle detected");
                return true;
            }
        }

        if (TRACE) System.out.println("[TRACE] no cycle found");
        return false;
    }

    /**
     * Title: Find the Middle Node
     *
     * Problem (what it solves): Find the middle node of the list. For even-length lists,
     * return the first of the two middle nodes. O(n) time, O(1) space.
     *
     * Output: The middle node's data; -1 if list is empty.
     *
     * Approach:
     * - Use slow and fast pointers.
     * - Slow moves 1 step, fast moves 2 steps.
     * - When fast reaches end, slow is at middle.
     *
     * Example Walkthrough (list 10 -> 20 -> 30 -> 40 -> 50 -> null):
     * - Initial: slow = Node(10), fast = Node(10)
     * - Iteration 1: slow = Node(20), fast = Node(30)
     * - Iteration 2: slow = Node(30), fast = Node(50)
     * - Iteration 3: slow = Node(40), fast = null
     * - fast.next == null, stop
     * - Middle node is slow = Node(40)
     *
     * Example Walkthrough (list 10 -> 20 -> 30 -> 40 -> null):
     * - Initial: slow = Node(10), fast = Node(10)
     * - Iteration 1: slow = Node(20), fast = Node(30)
     * - Iteration 2: slow = Node(30), fast = null
     * - fast.next == null, stop
     * - Middle node is slow = Node(30)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int findMiddle() {
        if (head == null) {
            if (TRACE) System.out.println("[TRACE] list is empty");
            return -1;
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if (TRACE) System.out.println("[TRACE] middle node data = " + slow.data);
        return slow.data;
    }

    /**
     * Title: Get the Kth Node from End
     *
     * Problem (what it solves): Find the kth node from the end of the list (1-indexed).
     * k=1 is the last node, k=2 is second-to-last, etc. O(n) time, O(1) space.
     *
     * Output: The kth node's data; -1 if k is invalid or out of bounds.
     *
     * Approach (two-pointer with gap):
     * - Use two pointers with a gap of k nodes between them.
     * - When the first pointer reaches the end, the second is at the kth node from end.
     *
     * Example Walkthrough (list 10 -> 20 -> 30 -> 40 -> 50 -> null, k=2):
     * - Setup: first = head, second = head
     * - Move first k steps: first = Node(20), count = 1; first = Node(30), count = 2
     * - Now gap is 2 nodes.
     * - Move both until first.next == null:
     *     first = Node(40), second = Node(20); first = Node(50), second = Node(30)
     * - first = Node(50), first.next = null, stop
     * - Second node is Node(30), data = 30 (2nd from end: 50->40->30)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int getKthFromEnd(int k) {
        if (head == null || k <= 0) {
            if (TRACE) System.out.println("[TRACE] invalid k or empty list");
            return -1;
        }

        Node first = head;
        Node second = head;

        for (int i = 0; i < k; i++) {
            if (first == null) {
                if (TRACE) System.out.println("[TRACE] k = " + k + " is out of bounds");
                return -1;
            }
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        if (TRACE) System.out.println("[TRACE] kth (" + k + ") node from end = " + second.data);
        return second.data;
    }

    public static void main(String[] args) {
        // enable trace if `--trace` provided on command line
        for (String a : args) if ("--trace".equals(a)) TRACE = true;

        SinglyLinkedList list = new SinglyLinkedList();

        System.out.println("=== Basic Insertion & Display ===");
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtEnd(30);
        System.out.print("List after insertAtEnd(10, 20, 30): ");
        list.display();

        System.out.println("\n=== Insert at Beginning ===");
        list.insertAtBeginning(5);
        System.out.print("List after insertAtBeginning(5): ");
        list.display();

        System.out.println("\n=== Insert at Position ===");
        list.insertAtPosition(15, 2);
        System.out.print("List after insertAtPosition(15, 2): ");
        list.display();

        System.out.println("\n=== Search ===");
        System.out.println("Search for 20: position = " + list.search(20));
        System.out.println("Search for 100: position = " + list.search(100));

        System.out.println("\n=== Delete at Beginning ===");
        int deleted = list.deleteAtBeginning();
        System.out.println("Deleted " + deleted + " from beginning");
        System.out.print("List: ");
        list.display();

        System.out.println("\n=== Delete at End ===");
        deleted = list.deleteAtEnd();
        System.out.println("Deleted " + deleted + " from end");
        System.out.print("List: ");
        list.display();

        System.out.println("\n=== Delete at Position ===");
        deleted = list.deleteAtPosition(1);
        System.out.println("Deleted " + deleted + " at position 1");
        System.out.print("List: ");
        list.display();

        System.out.println("\n=== Find Middle ===");
        System.out.println("Middle node data: " + list.findMiddle());

        System.out.println("\n=== Get Kth from End ===");
        System.out.println("2nd from end: " + list.getKthFromEnd(2));
        System.out.println("1st from end: " + list.getKthFromEnd(1));

        System.out.println("\n=== Reverse ===");
        System.out.print("List before reverse: ");
        list.display();
        list.reverse();
        System.out.print("List after reverse: ");
        list.display();

        System.out.println("\n=== Cycle Detection ===");
        SinglyLinkedList list2 = new SinglyLinkedList();
        list2.insertAtEnd(1);
        list2.insertAtEnd(2);
        list2.insertAtEnd(3);
        System.out.println("List2 (no cycle): " + list2.hasCycle());

        // Create a cycle manually (careful: only for testing)
        // list2.head.next.next.next = list2.head.next; // cycle: 1->2->3->2->...
        // System.out.println("List2 (with cycle): " + list2.hasCycle());
    }
}
