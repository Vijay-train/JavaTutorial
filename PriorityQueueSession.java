/**
 * PriorityQueueSession
 *
 * Using Java's built-in PriorityQueue (beginner-friendly, like BinarySearchTree's style).
 * Problems covered:
 * - Find K largest/smallest elements from a collection
 * - Top K Frequent Elements
 * - Sort Characters by Frequency
 * - K Closest Points to Origin
 * - Reorganize String
 *
 * Why PriorityQueue?
 * - It's a heap under the hood: fast insert/remove of best element.
 * - Default is a min-heap (smallest first). For max-heap, give a reverse comparator.
 * - Great for "keep top K", "pick next best", and greedy algorithms.
 *
 * We avoid import conflicts with local `Arrays.java` by using fully-qualified
 * calls, e.g., `java.util.Arrays.toString(arr)` and `java.util.Arrays.sort(arr)`.
 */
public class PriorityQueueSession {

    public static void main(String[] args) {
        // Demo: K largest / K smallest
        int[] nums = {5, 12, 3, 17, 1, 9, 21, 8, 7};
        int k = 3;
        System.out.println("Input: " + java.util.Arrays.toString(nums) + ", k=" + k);
        System.out.println("K largest: " + java.util.Arrays.toString(findKLargest(nums, k)));
        System.out.println("K smallest: " + java.util.Arrays.toString(findKSmallest(nums, k)));

        // Demo: Top K Frequent Elements
        int[] nums2 = {1,1,1,2,2,3,3,3,3,4,5,5};
        System.out.println("\nTop K Frequent (k=2) from " + java.util.Arrays.toString(nums2));
        System.out.println("Top 2: " + java.util.Arrays.toString(topKFrequent(nums2, 2)));

        // Demo: Sort Characters by Frequency
        String s = "Programming"; // mixed case is fine
        System.out.println("\nSort characters by frequency: '" + s + "'");
        System.out.println("Result: " + sortCharactersByFrequency(s));

        // Demo: K Closest Points to Origin
        int[][] points = {{3,3},{5,-1},{-2,4},{0,2},{1,1}};
        int kPoints = 3;
        System.out.println("\nPoints: " + deepToString(points) + ", k=" + kPoints);
        int[][] closest = kClosest(points, kPoints);
        System.out.println("K closest: " + deepToString(closest));

        // Demo: Reorganize String
        String r1 = "aab";
        String r2 = "aaab"; // impossible
        System.out.println("\nReorganize '" + r1 + "': " + reorganizeString(r1));
        System.out.println("Reorganize '" + r2 + "': " + reorganizeString(r2));
    }

    // -----------------------
    // K Largest / K Smallest
    // -----------------------

    /**
    * Find K Largest Elements (size-K min-heap)
    * Example: nums=[5,12,3,17,1,9,21,8,7], K=3 -> [12,17,21]
    * Problem: keep the biggest K numbers seen.
    * Approach 
    *   1) Use default PriorityQueue (min-heap). Root is current smallest of the kept set.
    *   2) For each number x:
    *        - If heap size < K: add x (we still need K items).
    *        - Else if x > root: remove root (current smallest) and add x (a bigger one).
    *        - Else: ignore x (it is not in top K).
    *   3) Heap now has the K largest; sort for a clean ascending print.
    * Trace (K=3): add 5 -> [5]; add 12 -> [5,12]; add 3 -> [3,12,5];
    *   see 17>3 pop3 add17 -> [5,12,17]; see 1 ignore; see 9>5 pop5 add9 -> [9,17,12];
    *   see 21>9 pop9 add21 -> [12,17,21]; 8 ignore; 7 ignore.
     */








    // Example: nums=[5,12,3,17,1,9,21,8,7], K=3 -> [12,17,21]
    public static int[] findKLargest(int[] nums, int k) {
        if (k <= 0) return new int[0];
        // Comparator: default (natural order) -> min-heap (smallest at root), so root is the
        // current smallest among the kept K elements.
        java.util.PriorityQueue<Integer> minHeap = new java.util.PriorityQueue<>();
        for (int idx = 0; idx < nums.length; idx++) {
            int x = nums[idx];
            if (minHeap.size() < k) { // still building up to K items
                minHeap.offer(x);
            } else if (!minHeap.isEmpty() && x > minHeap.peek()) { // replace smallest of kept
                minHeap.poll();
                minHeap.offer(x);
            } // else ignore; too small for top K
        }
        Integer[] heapArray = minHeap.toArray(new Integer[0]);
        int[] out = new int[heapArray.length];
        for (int i = 0; i < heapArray.length; i++) out[i] = heapArray[i];
        java.util.Arrays.sort(out);
        return out;
    }

    /**
    * Find K Smallest Elements (size-K )
    * Example: nums=[5,12,3,17,1,9,21,8,7], K=3 -> [1,3,5]
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
    * Problem: keep the smallest K numbers seen.
    * Approach (class steps):
    *   1) Use a max-heap comparator (largest on top) so root tracks the worst (largest) among kept.
    *   2) For each number x:
    *        - If heap size < K: add x.
    *        - Else if x < root: remove root (current largest of kept) and add x (a smaller one).
    *        - Else: ignore x (too large for the smallest-K set).
    *   3) Heap has K smallest; sort for display.
    * Trace (K=3): add5->[5]; add12->[12,5]; add3->[12,5,3]; see17 ignore (larger than root);
    *   see1<12 pop12 add1 -> [5,3,1]; 9 ignore; 21 ignore; 8 ignore; 7 ignore; sorted -> [1,3,5].
     */
    public static int[] findKSmallest(int[] nums, int k) {
        if (k <= 0) return new int[0];
        // Comparator: (a, b) -> b - a flips natural order, making a max-heap (largest at root).
        // Integer subtraction is safe here because inputs are small demo ints; for general use,
        // prefer Integer.compare(b, a) to avoid overflow.
        java.util.PriorityQueue<Integer> maxHeap = new java.util.PriorityQueue<>((a, b) -> b - a);
        for (int idx = 0; idx < nums.length; idx++) {
            int x = nums[idx];
            if (maxHeap.size() < k) { // still gathering first K
                maxHeap.offer(x);
            } else if (!maxHeap.isEmpty() && x < maxHeap.peek()) { // found smaller than current largest
                maxHeap.poll();
                maxHeap.offer(x);
            } // else ignore; too large for smallest K
        }
        Integer[] heapArray = maxHeap.toArray(new Integer[0]);
        int[] out = new int[heapArray.length];
        for (int i = 0; i < heapArray.length; i++) out[i] = heapArray[i];
        java.util.Arrays.sort(out);
        return out;
    }

    // -----------------------
    // Top K Frequent Elements
    // -----------------------

    /**
    * Top K Frequent Elements (max-heap on count)
    * Example: [1,1,1,2,2,3,3,3,3,4], K=2 -> [3,1]
    * Problem: return the K numbers that appear most often.
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
    * Approach :
    *   1) Count each number with a HashMap.
    *   2) Push (count, value) into a max-heap so highest count is on top.
    *   3) Pop K times to collect the answers.
    * 
    * Goal: return the K numbers that appear most often in an array.
    * Core idea: two phases—count, then pick the top counts with a max-heap.
    * 
    * 
    * Build a max-heap of (count, value) pairs

    Comparator orders by count descending, so the highest count sits at the root.
    Push one pair per distinct value.
    Why a heap? It gives fast “give me the current best” in O(log n) per push/pop.
    Pop K times

    Each pop returns the current most frequent value.
    Collect those values into the answer array.
    * 
    Walkthrough with the sample

Input: [1,1,1,2,2,3,3,3,3,4], K=2
Counts: 1→3, 2→2, 3→4, 4→1
Heap push order (max-heap on count): (3,1) → (2,2) → (4,3) → (1,4)
After all pushes, the root is (4,3) because 4 is the highest count.
Pop #1: (4,3) → output[0]=3
Pop #2: (3,1) → output[1]=1
Result: [3,1]
    * 
    * 
    * 
    * Trace (step-by-step):
    *   counts: 1->3, 2->2, 3->4, 4->1
    *   heap push order: (3,1) -> (2,2) -> (4,3) -> (1,4)  (root always biggest count)
    *   pop #1 => (4,3); pop #2 => (3,1); collected -> [3,1].
     */
    public static int[] topKFrequent(int[] nums, int k) {
        if (k <= 0) return new int[0]; // no items requested
        
        java.util.Map<Integer, Integer> freq = new java.util.HashMap<>(); // number -> count
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i]; // current number from input
            int current = freq.getOrDefault(val, 0); // existing count (0 if first time)
            freq.put(val, current + 1); // bump its count by 1
        }
 
        // Comparator: compare on count descending (b[0] - a[0]) so the pair with highest frequency
        // stays at the root. For safety with large counts, Integer.compare(b[0], a[0]) is better;
        // kept subtraction here for brevity in class demos.
        java.util.PriorityQueue<int[]> maxHeap = new java.util.PriorityQueue<>(
            (a, b) -> b[0] - a[0] // max-heap on count
        );

        Integer[] keys = freq.keySet().toArray(new Integer[0]); // distinct numbers list
        
        for (int idx = 0; idx < keys.length; idx++) {
            int val = keys[idx]; // current number we are processing
            int cnt = freq.get(val); // how many times that number appeared
            maxHeap.offer(new int[]{cnt, val}); // add this number + its count to the heap (heap holds all distinct numbers)
        }
        int take = Math.min(k, maxHeap.size()); // pop only top K (or fewer if not enough distinct values)
        int[] out = new int[take]; // result container (will hold the K most frequent numbers)
        for (int i = 0; i < take; i++) {
            out[i] = maxHeap.poll()[1]; // poll gives {count, value}; [1] grabs the value and saves it
        }
        return out; // return top K frequent values
    }

    // -----------------------------
    // Sort Characters by Frequency
    // -----------------------------

    /**
    * Sort Characters by Frequency (max-heap on count)
    * Example: "Programming" -> "ggmmrrnaiPo" (most frequent first; ties arbitrary)
    * Problem: print chars grouped by how often they appear, most frequent first.
    * Approach (class steps):
    *   1) Count each char in int[256].
    *   2) Push (count, charCode) into a max-heap so biggest count stays on top.
    *   3) Pop; append that char `count` times; repeat.
    * Trace (step-by-step): counts g2 r2 m2 p1 o1 a1 i1 n1
    *   push (2,g),(2,r),(2,m),(1,p),(1,o),(1,a),(1,i),(1,n) -> root always biggest count
    *   pop g -> append "gg"; pop r -> "ggrr"; pop m -> "ggrrmm"; then p,o,a,i,n => ggmmrrnaiPo.
     */
    public static String sortCharactersByFrequency(String s) {
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) count[s.charAt(i)]++; // frequency table
        // Comparator: same pattern—order by count descending so highest-frequency char stays on top.
        java.util.PriorityQueue<int[]> maxHeap = new java.util.PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int ch = 0; ch < 256; ch++) {
            if (count[ch] > 0) maxHeap.offer(new int[]{count[ch], ch}); // push (count, char)
        }
        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            int[] p = maxHeap.poll();
            for (int i = 0; i < p[0]; i++) sb.append((char) p[1]); // append char p[0] times
        }
        return sb.toString();
    }

    // -----------------------------
    // K Closest Points to Origin
    // -----------------------------

    /**
    * K Closest Points to Origin (size-K max-heap on dist^2)
    * Example: [(3,3),(5,-1),(-2,4),(0,2),(1,1)], K=3 -> closest: (1,1),(0,2),(3,3)
    * Problem: keep only the closest K points to (0,0).
    * Approach (class steps):
    *   1) Compute dist^2 = x*x + y*y (no sqrt needed).
    *   2) Keep a max-heap of at most K on dist^2 (root is farthest of kept).
    *   3) For each point: if heap not full push; else if dist^2 < root pop root then push.
    *   4) Pop everything to list the closest K (order not guaranteed).
    * Trace (dist^2 stream 18,26,20,4,2 with K=3):
    *   start [] ; add18 -> [18]; add26 -> [26,18]; add20 -> [26,18,20] (farthest=26)
    *   see 4 < 26 -> pop 26, add4 => [20,18,4] (farthest=20)
    *   see 2 < 20 -> pop 20, add2 => [18,4,2]; final kept distances 18,4,2 => points (3,3),(0,2),(1,1).
     */
    public static int[][] kClosest(int[][] points, int k) {
        if (k <= 0) return new int[0][0];
        // Comparator: order by distanceSquared descending (max-heap) so the farthest among the kept
        // K sits at root; when a closer point appears, we can pop the farthest.
        java.util.PriorityQueue<int[]> maxHeap = new java.util.PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < points.length; i++) {
            int dist2 = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (maxHeap.size() < k) {
                maxHeap.offer(new int[]{dist2, i});
            } else if (!maxHeap.isEmpty() && dist2 < maxHeap.peek()[0]) {
                maxHeap.poll();
                maxHeap.offer(new int[]{dist2, i});
            }
        }
        int take = Math.min(k, maxHeap.size());
        int[][] out = new int[take][2];
        for (int i = take - 1; i >= 0; i--) {
            int[] p = maxHeap.poll();
            out[i][0] = points[p[1]][0];
            out[i][1] = points[p[1]][1];
        }
        return out;
    }

    // -----------------
    // Reorganize String
    // -----------------

    /**
    * Reorganize String (no adjacent duplicates)
    * Example: "aab" -> "aba"; "aaab" -> "" (impossible)
    * Problem: arrange so the same letter never appears twice in a row.
    * Approach (class steps):
    *   1) Count chars.
    *   2) Max-heap on (freq, char) so most frequent is chosen first.
    *   3) Loop: pop top, append it, decrease its freq. Keep a "held" previous char; if it
    *      still has freq, push it back next round so it won't be adjacent.
    *   4) If we finish with a leftover held char that cannot be placed, it's impossible.
    * Trace:
    *   "aab": counts a2 b1; pop a (hold a1); pop b (push a1 back); pop a -> "aba" valid.
    *   "aaab": counts a3 b1; sequence leaves leftover a that cannot be placed -> return "".
     */
    public static String reorganizeString(String s) {
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) count[s.charAt(i)]++;
        // Comparator: order by remaining frequency descending so the most frequent char is chosen
        // first; ties break by array order (stable enough for this use). Again, Integer.compare
        // could be used for overflow-safe comparison.
        java.util.PriorityQueue<int[]> maxHeap = new java.util.PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int ch = 0; ch < 256; ch++) if (count[ch] > 0) maxHeap.offer(new int[]{count[ch], ch});
        StringBuilder sb = new StringBuilder();
        int[] prev = null;
        while (!maxHeap.isEmpty()) {
            int[] cur = maxHeap.poll();
            sb.append((char) cur[1]);
            cur[0]--;
            if (prev != null && prev[0] > 0) maxHeap.offer(prev);
            prev = cur;
        }
        if (prev != null && prev[0] > 0) return ""; // leftover cannot be placed
        return sb.toString();
    }

    // ------------------
    // Helper: pretty print
    // ------------------
    private static String deepToString(int[][] m) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < m.length; i++) {
            sb.append(java.util.Arrays.toString(m[i]));
            if (i < m.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // (Custom heap helpers removed; we now use java.util.PriorityQueue directly.)
}
