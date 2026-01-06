import java.util.*;

/**
 * PRIORITY QUEUE - Quick Start (Beginner Friendly)
 *
 * - Default: `PriorityQueue` is a min-heap (smallest comes out first).
 * - Max-heap: give a reverse comparator.
 * - Main ops: offer/add O(log n), poll O(log n), peek O(1), size O(1).
 * - Common uses: pick smallest/largest quickly, scheduling, top-K, merge sorted lists.
 */
public class PriorityQueueSession {
    // Global trace flag: set to true when program started with `--trace`
    public static boolean TRACE = false;

    /** Simple POJO for custom object demo (task with priority). */
    static class Task {
        int priority; // lower number = higher priority in this example
        String name;

        Task(int priority, String name) {
            this.priority = priority;
            this.name = name;
        }
    }

    /** Node used for merging k sorted arrays. */
    static class ArrayNode {
        int value;
        int arrIdx;
        int elemIdx;

        ArrayNode(int value, int arrIdx, int elemIdx) {
            this.value = value;
            this.arrIdx = arrIdx;
            this.elemIdx = elemIdx;
        }
    }

    /**
     * Min-Heap Demo (default)
     * What: show that PriorityQueue pulls the smallest first.
     * Steps: offer a few ints, then poll all.
     * Example with [5,1,7,3,2]: poll order -> 1,2,3,5,7.
     */
    static void demoMinHeapIntegers() {
        System.out.println("=== Min-Heap (default PriorityQueue) ===");
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min-heap
        int[] nums = {5, 1, 7, 3, 2};
        for (int n : nums) {
            pq.offer(n);
            if (TRACE) System.out.println("[TRACE] offer " + n + " -> head now " + pq.peek());
        }
        System.out.print("Poll order (ascending): ");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
        System.out.println();
    }

    /**
     * Max-Heap Demo (custom comparator)
     * What: make PQ return the largest first.
     * How: comparator (a,b) -> b - a.
     * Example with [5,1,7,3,2]: poll order -> 7,5,3,2,1.
     */
    static void demoMaxHeapIntegers() {
        System.out.println("=== Max-Heap (custom comparator) ===");
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int[] nums = {5, 1, 7, 3, 2};
        for (int n : nums) {
            maxHeap.offer(n);
            if (TRACE) System.out.println("[TRACE] offer " + n + " -> head now " + maxHeap.peek());
        }
        System.out.print("Poll order (descending): ");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
        System.out.println();
    }

    /**
     * Custom Objects (Task scheduler)
     * What: order objects by a field (priority).
     * How: comparator on priority ascending (smaller = more urgent).
     * Example: (1,Prod outage) -> (2,Code review) -> (3,Email).
     */
    static void demoCustomObjects() {
        System.out.println("=== Custom Objects (Task with priority) ===");
        // Lower priority number = higher urgency
        PriorityQueue<Task> tasks = new PriorityQueue<>((t1, t2) -> t1.priority - t2.priority);
        tasks.offer(new Task(3, "Email"));
        tasks.offer(new Task(1, "Prod outage"));
        tasks.offer(new Task(2, "Code review"));

        while (!tasks.isEmpty()) {
            Task t = tasks.poll();
            System.out.println("Processing task: " + t.name + " (priority=" + t.priority + ")");
        }
    }

    /**
     * Kth Smallest (keep only k items)
     * Idea: max-heap of size k. If it grows past k, drop the largest.
     * After all numbers, heap head = kth smallest.
     * Example arr=[7,10,4,3,20,15], k=3 -> answer 7.
     */
    static int kthSmallest(int[] arr, int k) {
        if (k <= 0 || k > arr.length) throw new IllegalArgumentException("k out of range");
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int n : arr) {
            maxHeap.offer(n);
            if (maxHeap.size() > k) maxHeap.poll();
            if (TRACE) System.out.println("[TRACE] push " + n + " heap size=" + maxHeap.size() + " head=" + maxHeap.peek());
        }
        return maxHeap.peek();
    }

    /**
     * Top-K Frequent
     * Idea: count with a map, keep a min-heap of size k on (freq, value).
     * If heap grows past k, drop the smallest freq.
     * Example arr=[1,1,1,2,2,3,3,3,3,4], k=2 -> [3,1].
     */
    static List<Integer> topKFrequent(int[] arr, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : arr) freq.put(n, freq.getOrDefault(n, 0) + 1);

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]); // [freq, value]
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            minHeap.offer(new int[]{e.getValue(), e.getKey()});
            if (minHeap.size() > k) minHeap.poll();
            if (TRACE) System.out.println("[TRACE] value=" + e.getKey() + " freq=" + e.getValue() + " heapSize=" + minHeap.size());
        }

        List<Integer> res = new ArrayList<>();
        while (!minHeap.isEmpty()) res.add(minHeap.poll()[1]);
        Collections.reverse(res); // highest freq first
        return res;
    }

    /**
     * Merge K Sorted Arrays
     * Idea: min-heap of current heads; always take the smallest next.
     * Steps: push first element of each array; pop+append; push that array's next element; repeat.
     * Example arrays [1,4,7], [2,5,8], [0,6,9] -> merged [0,1,2,4,5,6,7,8,9].
     */
    static int[] mergeKSortedArrays(List<int[]> arrays) {
        PriorityQueue<ArrayNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));
        int total = 0;
        for (int i = 0; i < arrays.size(); i++) {
            int[] arr = arrays.get(i);
            if (arr.length > 0) {
                pq.offer(new ArrayNode(arr[0], i, 0));
                total += arr.length;
            }
        }

        int[] result = new int[total];
        int idx = 0;
        while (!pq.isEmpty()) {
            ArrayNode node = pq.poll();
            result[idx++] = node.value;

            int nextIdx = node.elemIdx + 1;
            int[] source = arrays.get(node.arrIdx);
            if (nextIdx < source.length) {
                pq.offer(new ArrayNode(source[nextIdx], node.arrIdx, nextIdx));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        for (String a : args) if ("--trace".equals(a)) TRACE = true;

        demoMinHeapIntegers();
        demoMaxHeapIntegers();
        demoCustomObjects();

        System.out.println("\n=== Kth Smallest (k=3) ===");
        int[] arr = {7, 10, 4, 3, 20, 15};
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        System.out.println("3rd smallest: " + kthSmallest(arr, 3)); // expected 7

        System.out.println("\n=== Top-2 Frequent ===");
        int[] freqArr = {1,1,1,2,2,3,3,3,3,4};
        System.out.println("Array: " + java.util.Arrays.toString(freqArr));
        System.out.println("Top-2 frequent: " + topKFrequent(freqArr, 2));

        System.out.println("\n=== Merge K Sorted Arrays ===");
        List<int[]> arrays = new ArrayList<>();
        arrays.add(new int[]{1, 4, 7});
        arrays.add(new int[]{2, 5, 8});
        arrays.add(new int[]{0, 6, 9});
        int[] merged = mergeKSortedArrays(arrays);
        System.out.println("Merged: " + java.util.Arrays.toString(merged));
    }
}
