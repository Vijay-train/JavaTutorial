/**
 * MIN HEAP IMPLEMENTATION - From Scratch
 *
 * This file builds a min-heap from the ground up using an array.
 * A min-heap is a complete binary tree where every parent <= its children.
 *
 * Array representation:
 * - For index i, left child is at 2*i+1, right child at 2*i+2, parent at (i-1)/2.
 * - Index 0 is the root (smallest element in min-heap).
 *
 * Example heap of [3,5,10,7,8,20,15] in array form:
 *            3 (index 0)
 *          /   \
 *         5     10  (indices 1,2)
 *        / \   /  \
 *       7   8 20  15 (indices 3,4,5,6)
 *
 * Operations: insert O(log n), removeMin O(log n), peek O(1), size O(1).
 */
public class MinHeapImplementation {
    // Global trace flag for detailed logs
    public static boolean TRACE = false;

    private int[] heap;
    private int size;

    public MinHeapImplementation() {
        heap = new int[10]; // start with capacity 10
        size = 0;
    }

    /**
     * Title: Insert (Offer/Add)
     *
     * Problem: Add a new value to the heap while maintaining min-heap property.
     *
     * Output: Value inserted; heap size incremented.
     *
     * Approach (bubble-up):
     * - Place new element at end of array (next available position).
     * - Compare with parent; if smaller, swap and bubble up.
     * - Repeat until element >= parent or reaches root.
     * - Parent of element at index i is at (i-1)/2.
     *
     * Example Walkthrough (inserting 4 into heap [3,5,10,7,8,20,15]):
     * Array before: [3,5,10,7,8,20,15]
     * - Add 4 at end: [3,5,10,7,8,20,15,4]
     * - Index 7, parent at (7-1)/2=3, parent value=7
     * - 4 < 7, so swap: [3,5,10,4,8,20,15,7]
     * - Now index 3, parent at (3-1)/2=1, parent value=5
     * - 4 < 5, so swap: [3,4,10,5,8,20,15,7]
     * - Now index 1, parent at (1-1)/2=0, parent value=3
     * - 4 >= 3, stop
     * Array after: [3,4,10,5,8,20,15,7]
     *
     * Time Complexity: O(log n) in worst case (bubble all the way to root)
     * Space Complexity: O(1) if ignoring array expansion
     */
    public void insert(int value) {
        // Expand array if needed
        if (size == heap.length) {
            resize();
        }

        // Add at end and bubble up
        heap[size] = value;
        bubbleUp(size);
        size++;

        if (TRACE) System.out.println("[TRACE] inserted " + value + ", size now " + size);
    }

    /**
     * Title: Bubble Up (restore min-heap property upward)
     *
     * Problem: After placing a new element at a leaf, move it up to proper position.
     *
     * Output: Element moved to correct position; heap property restored.
     *
     * Approach:
     * - Compare element at index i with parent at (i-1)/2.
     * - If element < parent, swap and recurse upward.
     * - Stop when element >= parent or i == 0.
     *
     * Example: bubbling up 2 in [5,10,3,7,8,20,15,12,2] (2 is at index 8):
     * - Index 8, parent (8-1)/2=3, parent=12. 2 < 12, swap.
     * - Now [5,10,3,2,8,20,15,12,7]
     * - Index 3, parent (3-1)/2=1, parent=10. 2 < 10, swap.
     * - Now [5,2,3,10,8,20,15,12,7]
     * - Index 1, parent (1-1)/2=0, parent=5. 2 < 5, swap.
     * - Now [2,5,3,10,8,20,15,12,7]
     * - Index 0 is root, stop.
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIdx = (index - 1) / 2;
            if (heap[index] < heap[parentIdx]) {
                swap(index, parentIdx);
                index = parentIdx;
            } else {
                break;
            }
        }
    }

    /**
     * Title: Remove Minimum (Poll)
     *
     * Problem: Extract and remove the smallest element (at root) from the heap.
     *
     * Output: Value of the root; throws if heap is empty.
     *
     * Approach:
     * - Save root value (to return).
     * - Move last element to root position.
     * - Sink down (heapify) the new root until heap property restored.
     *
     * Example Walkthrough (removing from [3,5,10,7,8,20,15]):
     * Array before: [3,5,10,7,8,20,15]
     * - Save root=3 (return value).
     * - Move last element (15) to root: [15,5,10,7,8,20]
     * - Sink down from index 0:
     *   - Children of 0: left=1(5), right=2(10). Min child=5 at index 1.
     *   - 15 > 5, so swap: [5,15,10,7,8,20]
     * - Now at index 1, children: left=3(7), right=4(8). Min child=7 at index 3.
     *   - 15 > 7, so swap: [5,7,10,15,8,20]
     * - Now at index 3, children: left=7(none), right=8(none). No children, stop.
     * Array after: [5,7,10,15,8,20]
     * Return: 3
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public int removeMin() {
        if (size == 0) throw new IllegalStateException("Heap is empty");

        int minValue = heap[0];

        // Move last to root and shrink
        heap[0] = heap[size - 1];
        size--;

        // Sink down from root
        if (size > 0) sinkDown(0);

        if (TRACE) System.out.println("[TRACE] removed min " + minValue + ", size now " + size);
        return minValue;
    }

    /**
     * Title: Sink Down (Heapify downward)
     *
     * Problem: After moving last element to root, restore heap property downward.
     *
     * Output: Element moved to correct position; all parent < children restored.
     *
     * Approach:
     * - Check both children of element at index i.
     * - Find the smaller child (min).
     * - If element > min child, swap and recurse downward.
     * - Stop when element <= both children or no children.
     * - Children of i: left=2*i+1, right=2*i+2.
     *
     * Example: sinking down 15 in [15,7,10,8,20,3] (15 at index 0):
     * - Index 0, children: left=1(7), right=2(10). Min child=7 at index 1.
     * - 15 > 7, so swap: [7,15,10,8,20,3]
     * - Index 1, children: left=3(8), right=4(20). Min child=8 at index 3.
     * - 15 > 8, so swap: [7,8,10,15,20,3]
     * - Index 3, children: left=7(none), right=8(none). No children, stop.
     * Final: [7,8,10,15,20,3]
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    private void sinkDown(int index) {
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int minIdx = index;

            // Check left child
            if (leftChild < size && heap[leftChild] < heap[minIdx]) {
                minIdx = leftChild;
            }

            // Check right child
            if (rightChild < size && heap[rightChild] < heap[minIdx]) {
                minIdx = rightChild;
            }

            // If a child is smaller, swap and continue
            if (minIdx != index) {
                swap(index, minIdx);
                index = minIdx;
            } else {
                break;
            }
        }
    }

    /**
     * Title: Peek (Get minimum without removing)
     *
     * Problem: Return the smallest element without modifying the heap.
     *
     * Output: Value at root; throws if empty.
     *
     * Approach:
     * - Return heap[0] directly (root is always the minimum in min-heap).
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int peek() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        return heap[0];
    }

    /**
     * Title: Size (Number of elements)
     *
     * Problem: Get the count of elements currently in the heap.
     *
     * Output: Integer count.
     *
     * Approach:
     * - Return the size variable (incremented on insert, decremented on remove).
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int size() {
        return size;
    }

    /**
     * Title: Is Empty
     *
     * Problem: Check if heap has no elements.
     *
     * Output: true if size == 0, false otherwise.
     *
     * Approach:
     * - Return (size == 0).
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Title: Print Heap (array representation)
     *
     * Problem: Display heap contents for visualization/debugging.
     *
     * Output: Print all elements in array order.
     *
     * Approach:
     * - Iterate through first size elements and print.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void print() {
        System.out.print("Heap (array): [");
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i]);
            if (i < size - 1) System.out.print(",");
        }
        System.out.println("]");
    }

    /**
     * Title: Print as Tree (visual heap structure)
     *
     * Problem: Visualize heap as a tree (level by level).
     *
     * Output: Print elements grouped by tree levels.
     *
     * Approach:
     * - Print level 0 (root), then level 1, then level 2, etc.
     * - Elements at level L have indices 2^L - 1 to 2^(L+1) - 2.
     *
     * Example for heap [3,5,10,7,8,20,15]:
     * Level 0: 3
     * Level 1: 5 10
     * Level 2: 7 8 20 15
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public void printAsTree() {
        System.out.println("Heap (tree):");
        int level = 0;
        int idx = 0;
        while (idx < size) {
            int levelEnd = Math.min((1 << (level + 1)) - 1, size); // up to 2^(L+1) - 1
            System.out.print("  Level " + level + ": ");
            for (int i = idx; i < levelEnd; i++) {
                System.out.print(heap[i] + " ");
            }
            System.out.println();
            idx = levelEnd;
            level++;
        }
    }

    /**
     * Title: Swap (helper for maintaining heap structure)
     *
     * Problem: Exchange two elements in the array by their indices.
     *
     * Output: Array with elements at i and j swapped.
     *
     * Approach:
     * - Use temp variable to swap.
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
        if (TRACE) System.out.println("[TRACE] swapped heap[" + i + "]=" + heap[j] + " and heap[" + j + "]=" + heap[i]);
    }

    /**
     * Title: Resize (dynamic array expansion)
     *
     * Problem: When heap array is full, grow it to accommodate more elements.
     *
     * Output: Double-sized array; copy all elements over.
     *
     * Approach:
     * - Create new array of size 2 * capacity.
     * - Copy elements from old to new.
     * - Update heap reference.
     *
     * Time Complexity: O(n) amortized O(1) per insertion
     * Space Complexity: O(n)
     */
    private void resize() {
        int[] newHeap = new int[heap.length * 2];
        System.arraycopy(heap, 0, newHeap, 0, size);
        heap = newHeap;
        if (TRACE) System.out.println("[TRACE] resized heap to capacity " + heap.length);
    }

    public static void main(String[] args) {
        for (String a : args) if ("--trace".equals(a)) TRACE = true;

        MinHeapImplementation minHeap = new MinHeapImplementation();

        System.out.println("=== Insert Values ===");
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int val : values) {
            minHeap.insert(val);
        }
        System.out.println("Inserted: " + java.util.Arrays.toString(values));
        minHeap.print();

        System.out.println("\n=== Print as Tree ===");
        minHeap.printAsTree();

        System.out.println("\n=== Peek (min without removing) ===");
        System.out.println("Min (peek): " + minHeap.peek());

        System.out.println("\n=== Size ===");
        System.out.println("Size: " + minHeap.size());

        System.out.println("\n=== Remove Min (Poll) ===");
        System.out.print("Poll sequence: ");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.removeMin() + " ");
        }
        System.out.println();

        System.out.println("\n=== Build Heap from Array ===");
        MinHeapImplementation heap2 = new MinHeapImplementation();
        int[] arr = {10, 5, 3, 2, 8, 15};
        for (int val : arr) {
            heap2.insert(val);
        }
        System.out.println("Built heap from " + java.util.Arrays.toString(arr));
        heap2.print();
        heap2.printAsTree();

        System.out.println("\n=== Mixed Operations ===");
        MinHeapImplementation heap3 = new MinHeapImplementation();
        heap3.insert(100);
        heap3.insert(50);
        heap3.insert(75);
        heap3.insert(25);
        heap3.insert(60);
        System.out.println("After inserts [100,50,75,25,60]:");
        heap3.print();

        System.out.println("Remove min: " + heap3.removeMin());
        heap3.print();

        heap3.insert(15);
        System.out.println("After inserting 15:");
        heap3.print();
    }
}
