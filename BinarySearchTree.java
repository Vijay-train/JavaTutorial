/**
 * BINARY SEARCH TREE - Practice Session
 *
 * This file covers fundamental binary search tree (BST) operations:
 * - Node insertion maintaining BST property (left < parent < right)
 * - In-order traversal for printing sorted values
 * - Counting total nodes in tree
 * - Computing sum of all node values
 * - Finding maximum value
 * - Computing tree height (longest path from root to leaf)
 *
 * A binary search tree node has data, left child, and right child:
 *       (parent)
 *       /      \
 *   (left)    (right)
 *   (smaller) (larger)
 * 
 * 
 * 1. Creating a BST
 * 2. Inserting nodes
 * 3. Traversing the tree (in-order, pre-order, post-order, level-order)
 * 4. Calculating size, sum, max, height
 * 5. Searching for values
 * 6. Validating BST property
 * 7. Printing in descending order
 * 
 */

public class BinarySearchTree {
    // Global trace flag: set to true when program started with `--trace`
    public static boolean TRACE = false;

    /**
     * Node class for binary search tree
     */
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }


/**
     * Title: Insert Node (maintaining BST property)
     *
     * Problem (what it solves): Add a new node with given data into the BST while
     * maintaining the BST property: all values in left subtree < node < all values in right subtree.
     * Average time O(log n) for balanced tree; O(n) worst case for skewed tree.
     *
     * Output: The tree now contains the new node in its correct sorted position.
     *
     * Approach (recursive):
     * - If tree is empty, create root node.
     * - If data < current node, insert into left subtree.
     * - If data > current node, insert into right subtree.
     * - If data == current node, ignore (no duplicates, or handle as per requirement).
     *
     * Example Walkthrough (inserting 50, 30, 70, 20, 40, 60, 80):
     * - Insert 50: tree is empty, so root = Node(50)
     *       50
     * - Insert 30: 30 < 50, go left (null), insert left of 50
     *       50
     *      /
     *     30
     * - Insert 70: 70 > 50, go right (null), insert right of 50
     *       50
     *      /  \
     *     30   70
     * - Insert 20: 20 < 50, go left to 30; 20 < 30, insert left of 30
     *       50
     *      /  \
     *     30   70
     *    /
     *   20
     * - Insert 40: 40 < 50, go left to 30; 40 > 30, insert right of 30
     *       50
     *      /  \
     *     30   70
     *    / \
     *   20  40
     * - Insert 60: 60 > 50, go right to 70; 60 < 70, insert left of 70
     *       50
     *      /  \
     *     30   70
     *    / \  /
     *   20 40 60
     * - Insert 80: 80 > 50, go right to 70; 80 > 70, insert right of 70
     *       50
     *      /  \
     *     30   70
     *    / \  / \
     *   20 40 60 80
     *
     * Time Complexity: O(log n) average; O(n) worst case (skewed tree)
     * Space Complexity: O(log n) average recursion depth; O(n) worst case
     */
    public void insert(int data) {
        root = insertHelper(root, data);
        if (TRACE) System.out.println("[TRACE] inserted " + data);
    }

    private Node insertHelper(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insertHelper(node.left, data);
        } else if (data > node.data) {
            node.right = insertHelper(node.right, data);
        }
        // If data == node.data, ignore (no duplicates)

        return node;
    }


    // ---------
    
    /**
     * Title: In-Order Traversal (Print in Sorted Order)
     *
     * Problem (what it solves): Display all nodes in the BST in sorted (ascending) order.
     * In-order traversal visits left subtree, then current node, then right subtree, which
     * produces sorted output for a BST. O(n) time.
     *
     * Output: Print all node values in ascending order, space-separated.
     *
     * Approach (recursive):
     * - Traverse left subtree recursively.
     * - Visit (print) current node.
     * - Traverse right subtree recursively.
     *
     * Example Walkthrough (in-order of tree with 50, 30, 70, 20, 40, 60, 80):
     *       50
     *      /  \
     *     30   70
     *    / \  / \
     *   20 40 60 80
     * - Visit left subtree of 50 (node 30):
     *   - Visit left subtree of 30 (node 20):
     *     - Visit left subtree of 20 (null), skip
     *     - Print 20
     *     - Visit right subtree of 20 (null), skip
     *   - Print 30
     *   - Visit right subtree of 30 (node 40):
     *     - Visit left subtree of 40 (null), skip
     *     - Print 40
     *     - Visit right subtree of 40 (null), skip
     * - Print 50
     * - Visit right subtree of 50 (node 70):
     *   - Visit left subtree of 70 (node 60):
     *     - Visit left subtree of 60 (null), skip
     *     - Print 60
     *     - Visit right subtree of 60 (null), skip
     *   - Print 70
     *   - Visit right subtree of 70 (node 80):
     *     - Visit left subtree of 80 (null), skip
     *     - Print 80
     *     - Visit right subtree of 80 (null), skip
     * - Output: 20 30 40 50 60 70 80
     *
     * Time Complexity: O(n) visit each node once
     * Space Complexity: O(log n) average recursion depth; O(n) worst case
     */
    public void printInOrder() {
        System.out.print("In-order (sorted): ");
        printInOrderHelper(root);
        System.out.println();
    }

    private void printInOrderHelper(Node node) {
        if (node == null) return;

        printInOrderHelper(node.left);
        System.out.print(node.data + " ");
        printInOrderHelper(node.right);
    }

     /**
     * Title: Level-Order Traversal (Breadth-First Search)
     *
     * Problem (what it solves): Visit all nodes level by level from top to bottom,
     * left to right. Useful for understanding tree structure, finding shortest paths,
     * or processing by depth. O(n) time.
     *
     * Output: Print all nodes level by level, showing tree hierarchy.
     *
     * Approach (using queue):
     * - Use a queue (FIFO) to process nodes level by level.
     * - Start by adding root to queue.
     * - While queue is not empty:
     *   - Dequeue a node and print it.
     *   - Enqueue its left child (if exists).
     *   - Enqueue its right child (if exists).
     *
     * Example Walkthrough (tree with 50, 30, 70, 20, 40, 60, 80):
     *       50          <- Level 0
     *      /  \
     *     30   70       <- Level 1
     *    / \  / \
     *   20 40 60 80     <- Level 2
     *
     * - Initial: queue = [50]
     * - Dequeue 50, print 50, enqueue children: queue = [30, 70]
     * - Dequeue 30, print 30, enqueue children: queue = [70, 20, 40]
     * - Dequeue 70, print 70, enqueue children: queue = [20, 40, 60, 80]
     * - Dequeue 20, print 20, no children: queue = [40, 60, 80]
     * - Dequeue 40, print 40, no children: queue = [60, 80]
     * - Dequeue 60, print 60, no children: queue = [80]
     * - Dequeue 80, print 80, no children: queue = []
     * - Output: 50 30 70 20 40 60 80
     *
     * Time Complexity: O(n) visit each node once
     * Space Complexity: O(w) where w is max width of tree (can be O(n) for complete tree)
     */
    public void printLevelOrder() {
        System.out.print("Level-order (BFS): ");
        if (root == null) {
            System.out.println("(empty)");
            return;
        }

        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.data + " ");

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        System.out.println();
    }

    /**
     * Title: Pre-Order Traversal (Root -> Left -> Right)
     *
     * Problem (what it solves): Visit nodes in pre-order: process root first,
     * then left subtree, then right subtree. Useful for creating a copy of the tree
     * or generating prefix expressions. O(n) time.
     *
     * Output: Print all nodes in pre-order sequence.
     *
     * Approach (recursive):
     * - Visit (print) current node.
     * - Traverse left subtree recursively.
     * - Traverse right subtree recursively.
     *
     * Example Walkthrough (tree with 50, 30, 70, 20, 40, 60, 80):
     *       50
     *      /  \
     *     30   70
     *    / \  / \
     *   20 40 60 80
     * - Visit root 50, print 50
     * - Visit left subtree of 50 (node 30):
     *   - Print 30
     *   - Visit left subtree of 30 (node 20):
     *     - Print 20
     *     - Visit left (null), skip
     *     - Visit right (null), skip
     *   - Visit right subtree of 30 (node 40):
     *     - Print 40
     *     - Visit left (null), skip
     *     - Visit right (null), skip
     * - Visit right subtree of 50 (node 70):
     *   - Print 70
     *   - Visit left subtree of 70 (node 60):
     *     - Print 60
     *     - Visit left (null), skip
     *     - Visit right (null), skip
     *   - Visit right subtree of 70 (node 80):
     *     - Print 80
     *     - Visit left (null), skip
     *     - Visit right (null), skip
     * - Output: 50 30 20 40 70 60 80
     *
     * Time Complexity: O(n) visit each node once
     * Space Complexity: O(log n) average recursion depth; O(n) worst case
     */
    public void printPreOrder() {
        System.out.print("Pre-order (Root-Left-Right): ");
        printPreOrderHelper(root);
        System.out.println();
    }

    private void printPreOrderHelper(Node node) {
        if (node == null) return;

        System.out.print(node.data + " ");
        printPreOrderHelper(node.left);
        printPreOrderHelper(node.right);
    }

    /**
     * Title: Post-Order Traversal (Left -> Right -> Root)
     *
     * Problem (what it solves): Visit nodes in post-order: process left subtree first,
     * then right subtree, then root. Useful for deleting tree (delete children before parent)
     * or generating postfix expressions. O(n) time.
     *
     * Output: Print all nodes in post-order sequence.
     *
     * Approach (recursive):
     * - Traverse left subtree recursively.
     * - Traverse right subtree recursively.
     * - Visit (print) current node.
     *
     * Example Walkthrough (tree with 50, 30, 70, 20, 40, 60, 80):
     *       50
     *      /  \
     *     30   70
     *    / \  / \
     *   20 40 60 80
     * - Visit left subtree of 50 (node 30):
     *   - Visit left subtree of 30 (node 20):
     *     - Visit left (null), skip
     *     - Visit right (null), skip
     *     - Print 20
     *   - Visit right subtree of 30 (node 40):
     *     - Visit left (null), skip
     *     - Visit right (null), skip
     *     - Print 40
     *   - Print 30
     * - Visit right subtree of 50 (node 70):
     *   - Visit left subtree of 70 (node 60):
     *     - Visit left (null), skip
     *     - Visit right (null), skip
     *     - Print 60
     *   - Visit right subtree of 70 (node 80):
     *     - Visit left (null), skip
     *     - Visit right (null), skip
     *     - Print 80
     *   - Print 70
     * - Print root 50
     * - Output: 20 40 30 60 80 70 50
     *
     * Time Complexity: O(n) visit each node once
     * Space Complexity: O(log n) average recursion depth; O(n) worst case
     */
    public void printPostOrder() {
        System.out.print("Post-order (Left-Right-Root): ");
        printPostOrderHelper(root);
        System.out.println();
    }

    private void printPostOrderHelper(Node node) {
        if (node == null) return;

        printPostOrderHelper(node.left);
        printPostOrderHelper(node.right);
        System.out.print(node.data + " ");
    }

     
    /**
     * Title: Size (Count Total Nodes)
     *
     * Problem (what it solves): Return the total number of nodes in the BST.
     * Useful for understanding tree structure and validation. O(n) time.
     *
     * Output: Integer count of all nodes (including root).
     *
     *
     * 
     * 
     * 
     * 
     * 
     * Approach (recursive):
     * - If node is null, return 0 (base case).
     * - Otherwise, return 1 + size(left subtree) + size(right subtree).
     *
     * Example Walkthrough (tree with 50, 30, 70, 20, 40, 60, 80):
     *       50
     *      /  \
     *     30   70
     *    / \  / \
     *   20 40 60 80
     * - size(50):
     *   - size(30):
     *     - size(20): size(null) + 1 + size(null) = 0 + 1 + 0 = 1
     *     - size(40): size(null) + 1 + size(null) = 0 + 1 + 0 = 1
     *     - return 1 + 1 + 1 = 3
     *   - size(70):
     *     - size(60): size(null) + 1 + size(null) = 0 + 1 + 0 = 1
     *     - size(80): size(null) + 1 + size(null) = 0 + 1 + 0 = 1
     *     - return 1 + 1 + 1 = 3
     *   - return 1 + 3 + 3 = 7
     * - Total: 7 nodes
     *
     * Time Complexity: O(n) visit each node once
     * Space Complexity: O(log n) average recursion depth; O(n) worst case
     */
    public int size() {
        int count = sizeHelper(root);
        if (TRACE) System.out.println("[TRACE] tree size = " + count);
        return count;
    }

    private int sizeHelper(Node node) {
        if (node == null) return 0;
        return 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }

    /**
     * Title: Sum (Sum of All Node Values)
     *
     * Problem (what it solves): Calculate the sum of all node values in the BST.
     * Useful for aggregate statistics and validation. O(n) time.
     *
     * Output: Integer sum of all node data values.
     *
     * Approach (recursive):
     * - If node is null, return 0 (base case).
     * - Otherwise, return node.data + sum(left subtree) + sum(right subtree).
     *
     * Example Walkthrough (tree with 50, 30, 70, 20, 40, 60, 80):
     *       50
     *      /  \
     *     30   70
     *    / \  / \
     *   20 40 60 80
     * - sum(50):
     *   - sum(30):
     *     - sum(20): 20 + sum(null) + sum(null) = 20 + 0 + 0 = 20
     *     - sum(40): 40 + sum(null) + sum(null) = 40 + 0 + 0 = 40
     *     - return 30 + 20 + 40 = 90
     *   - sum(70):
     *     - sum(60): 60 + sum(null) + sum(null) = 60 + 0 + 0 = 60
     *     - sum(80): 80 + sum(null) + sum(null) = 80 + 0 + 0 = 80
     *     - return 70 + 60 + 80 = 210
     *   - return 50 + 90 + 210 = 350
     * - Total sum: 350
     *
     * Time Complexity: O(n) visit each node once
     * Space Complexity: O(log n) average recursion depth; O(n) worst case
     */
    public int sum() {
        int total = sumHelper(root);
        if (TRACE) System.out.println("[TRACE] tree sum = " + total);
        return total;
    }

    private int sumHelper(Node node) {
        if (node == null) return 0;
        return node.data + sumHelper(node.left) + sumHelper(node.right);
    }


       /**
     * Title: Search for a Value
     *
     * Problem (what it solves): Determine if a given value exists in the BST.
     * Leverages BST property to skip half the tree at each step. O(h) time where h is height.
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     *
     * Output: true if value exists in tree; false otherwise.
     *
     * Approach:
     * - Start at root.
     * - If current node is null, value not found, return false.
     * - If target == current node data, found, return true.
     * - If target < current node data, search left subtree.
     * - If target > current node data, search right subtree.
     *
     * Example Walkthrough (searching for 40 in tree with 50, 30, 70, 20, 40, 60, 80):
     *       50
     *      /  \
     *     30   70
     *    / \  / \
     *   20 40 60 80
     * - Start at root (50): 40 < 50, search left
     * - At node 30: 40 > 30, search right
     * - At node 40: 40 == 40, found! Return true
     *
     * Example Walkthrough (searching for 25 - not in tree):
     * - Start at root (50): 25 < 50, search left
     * - At node 30: 25 < 30, search left
     * - At node 20: 25 > 20, search right
     * - Right of 20 is null, not found, return false
     *
     * Time Complexity: O(log n) average for balanced tree; O(n) worst case (skewed)
     * Space Complexity: O(log n) average recursion depth; O(n) worst case
     */
    public boolean search(int data) {
        boolean found = searchHelper(root, data);
        if (TRACE) System.out.println("[TRACE] search for " + data + ": " + found);
        return found;
    }

    private boolean searchHelper(Node node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        if (data < node.data) return searchHelper(node.left, data);
        return searchHelper(node.right, data);
    }


    /**
     * Title: Maximum Value
     *
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * Problem (what it solves): Find the maximum (rightmost) value in the BST.
     * In a BST, the maximum is always the rightmost node. O(h) time where h is height.
     *
     * Output: The maximum node value; -1 if tree is empty.
     *
     * Approach:
     * - If tree is empty, return sentinel value (e.g., -1) or throw exception.
     * - Traverse to the rightmost node (keep going right until right child is null).
     * - Return that node's data.
     *
     * Example Walkthrough (tree with 50, 30, 70, 20, 40, 60, 80):
     *       50
     *      /  \
     *     30   70
     *    / \  / \
     *   20 40 60 80
     * - Start at root (50), right child exists (70), move right
     * - At node 70, right child exists (80), move right
     * - At node 80, right child is null, stop
     * - Maximum value = 80
     *
     * Time Complexity: O(h) where h is height (typically O(log n) for balanced tree)
     * Space Complexity: O(1) no recursion needed (iterative approach)
     */
    public int max() {
        if (root == null) {
            if (TRACE) System.out.println("[TRACE] tree is empty");
            return -1;
        }

        Node current = root;
        while (current.right != null) {
            current = current.right;
        }

        if (TRACE) System.out.println("[TRACE] maximum value = " + current.data);
        return current.data;
    }

    /**
     * Title: Height (Longest Path from Root to Leaf)
     *
     * Problem (what it solves): Calculate the height of the BST, which is the length of
     * the longest path from root to any leaf node. Height is critical for understanding
     * tree balance and operation performance. O(n) time.
     *
     * Output: Height as an integer; -1 if tree is empty (some definitions use 0 for single node).
     *
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * Approach (recursive):
     * - If node is null, return -1 (or 0, depending on definition).
     * - Otherwise, return 1 + max(height(left subtree), height(right subtree)).
     * - The +1 accounts for the edge from current node to its child.
     *
     * Example Walkthrough (tree with 50, 30, 70, 20, 40, 60, 80):
     *       50          <- height 2 (root)
     *      /  \
     *     30   70       <- height 1
     *    / \  / \
     *   20 40 60 80     <- height 0 (leaves)
     *
     * - height(50):
     *   - height(30):
     *     - height(20): height(null) = -1, height(null) = -1
     *       return 1 + max(-1, -1) = 1 + (-1) = 0
     *     - height(40): height(null) = -1, height(null) = -1
     *       return 1 + max(-1, -1) = 1 + (-1) = 0
     *     - return 1 + max(0, 0) = 1
     *   - height(70):
     *     - height(60): height(null) = -1, height(null) = -1
     *       return 1 + max(-1, -1) = 0
     *     - height(80): height(null) = -1, height(null) = -1
     *       return 1 + max(-1, -1) = 0
     *     - return 1 + max(0, 0) = 1
     *   - return 1 + max(1, 1) = 2
     * - Height of tree = 2
     *
     * Time Complexity: O(n) visit each node once
     * Space Complexity: O(log n) average recursion depth; O(n) worst case
     */
    public int height() {
        int h = heightHelper(root);
        if (TRACE) System.out.println("[TRACE] tree height = " + h);
        return h;
    }

    private int heightHelper(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(heightHelper(node.left), heightHelper(node.right));
    }


   
    /**
     * Title: Validate BST Property
     *
     * Problem (what it solves): Check if the tree satisfies the BST property:
     * for every node, all values in left subtree < node < all values in right subtree.
     * Not just immediate children - ALL descendants must satisfy this. O(n) time.
     *
     * Output: true if tree is a valid BST; false otherwise.
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     *
     * Approach (range checking):
     * - Use recursion with valid range [min, max] for each node.
     * - Root can be any value: range is (-∞, +∞).
     * - For left child: range becomes (-∞, parent value).
     * - For right child: range becomes (parent value, +∞).
     * - At each node, check if node.data is within its valid range.
     *
     * Example Walkthrough (valid BST: 50, 30, 70, 20, 40, 60, 80):
     *       50
     *      /  \
     *     30   70
     *    / \  / \
     *   20 40 60 80
     * - Check 50: range (-∞, +∞), 50 is valid
     *   - Check 30: range (-∞, 50), 30 < 50, valid
     *     - Check 20: range (-∞, 30), 20 < 30, valid
     *     - Check 40: range (30, 50), 30 < 40 < 50, valid
     *   - Check 70: range (50, +∞), 70 > 50, valid
     *     - Check 60: range (50, 70), 50 < 60 < 70, valid
     *     - Check 80: range (70, +∞), 80 > 70, valid
     * - All nodes valid, return true
     *
     * Example Walkthrough (invalid BST):
     *       50
     *      /  \
     *     30   70
     *    / \  / \
     *   20 60 65 80  (60 is in wrong position - should be in right subtree)
     * - Check 50: range (-∞, +∞), valid
     *   - Check 30: range (-∞, 50), valid
     *     - Check 20: range (-∞, 30), valid
     *     - Check 60: range (30, 50), 60 > 50, INVALID! Return false
     *
     * Time Complexity: O(n) visit each node once
     * Space Complexity: O(log n) average recursion depth; O(n) worst case
     */
    public boolean isValidBST() {
        boolean valid = isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
        if (TRACE) System.out.println("[TRACE] tree is valid BST: " + valid);
        return valid;
    }

    private boolean isValidBSTHelper(Node node, long min, long max) {
        if (node == null) return true;

        if (node.data <= min || node.data >= max) return false;

        return isValidBSTHelper(node.left, min, node.data) &&
               isValidBSTHelper(node.right, node.data, max);
    }

    /**
     * Title: Print in Descending Order (Reverse In-Order)
     *
     * Problem (what it solves): Display all nodes in descending (largest to smallest) order.
     * This is the reverse of in-order traversal: visit right subtree, then root, then left.
     * O(n) time.
     *
     * Output: Print all node values in descending order, space-separated.
     *
     * Approach (reverse in-order):
     * - Traverse right subtree recursively.
     * - Visit (print) current node.
     * - Traverse left subtree recursively.
     *
     * Example Walkthrough (tree with 50, 30, 70, 20, 40, 60, 80):
     *       50
     *      /  \
     *     30   70
     *    / \  / \
     *   20 40 60 80
     * - Visit right subtree of 50 (node 70):
     *   - Visit right subtree of 70 (node 80):
     *     - Visit right (null), skip
     *     - Print 80
     *     - Visit left (null), skip
     *   - Print 70
     *   - Visit left subtree of 70 (node 60):
     *     - Visit right (null), skip
     *     - Print 60
     *     - Visit left (null), skip
     * - Print 50
     * - Visit left subtree of 50 (node 30):
     *   - Visit right subtree of 30 (node 40):
     *     - Visit right (null), skip
     *     - Print 40
     *     - Visit left (null), skip
     *   - Print 30
     *   - Visit left subtree of 30 (node 20):
     *     - Visit right (null), skip
     *     - Print 20
     *     - Visit left (null), skip
     * - Output: 80 70 60 50 40 30 20
     *
     * Time Complexity: O(n) visit each node once
     * Space Complexity: O(log n) average recursion depth; O(n) worst case
     */
    public void printDescending() {
        System.out.print("Descending order (reverse in-order): ");
        printDescendingHelper(root);
        System.out.println();
    }

    private void printDescendingHelper(Node node) {
        if (node == null) return;

        printDescendingHelper(node.right);
        System.out.print(node.data + " ");
        printDescendingHelper(node.left);
    }

    public static void main(String[] args) {
        // enable trace if `--trace` provided on command line
        for (String a : args) if ("--trace".equals(a)) TRACE = true;

        BinarySearchTree bst = new BinarySearchTree();

        System.out.println("=== Insert Nodes ===");
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int val : values) {
            bst.insert(val);
        }
        System.out.println("Inserted values: 50, 30, 70, 20, 40, 60, 80");

        System.out.println("\n=== Searching ===");
        System.out.println("Search for 40: " + bst.search(40));
        System.out.println("Search for 25: " + bst.search(25));
        System.out.println("Search for 80: " + bst.search(80));

        System.out.println("\n=== Tree Traversals ===");
        bst.printInOrder();
        bst.printPreOrder();
        bst.printPostOrder();
        bst.printLevelOrder();
        bst.printDescending();

        System.out.println("\n=== Size (Total Nodes) ===");
        int size = bst.size();
        System.out.println("Total nodes in tree: " + size);

        System.out.println("\n=== Sum of All Values ===");
        int sum = bst.sum();
        System.out.println("Sum of all values: " + sum);

        System.out.println("\n=== Maximum Value ===");
        int maxVal = bst.max();
        System.out.println("Maximum value: " + maxVal);

        System.out.println("\n=== Height of Tree ===");
        int treeHeight = bst.height();
        System.out.println("Tree height: " + treeHeight);

        System.out.println("\n=== Validate BST ===");
        System.out.println("Is valid BST: " + bst.isValidBST());

        System.out.println("\n=== Tree Structure ===");
        System.out.println("Visual representation:");
        System.out.println("       50");
        System.out.println("      /  \\");
        System.out.println("     30   70");
        System.out.println("    / \\  / \\");
        System.out.println("   20 40 60 80");

        System.out.println("\n=== Another Example (Skewed Tree) ===");
        BinarySearchTree bst2 = new BinarySearchTree();
        bst2.insert(10);
        bst2.insert(20);
        bst2.insert(30);
        bst2.insert(40);
        System.out.println("Inserted: 10, 20, 30, 40 (right-skewed)");
        bst2.printInOrder();
        bst2.printLevelOrder();
        System.out.println("Size: " + bst2.size());
        System.out.println("Sum: " + bst2.sum());
        System.out.println("Max: " + bst2.max());
        System.out.println("Height: " + bst2.height());
        System.out.println("Is valid BST: " + bst2.isValidBST());
        System.out.println("Note: Skewed tree acts like a linked list, height = " + bst2.height());
    }
}
