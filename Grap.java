import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Grap{
    private ArrayList<ArrayList<Integer>> adj;

    public Grap(int n){
        adj = new ArrayList<>(n);
        for (int i =0 ; i < n; i++){
            adj.add(new ArrayList<>()); // empty neibhours
        }
    }

    public void Print(){
        for (int i = 0; i < adj.size(); i++){
            System.out.println(i + "->" + adj.get(i));
        }
    }

    /**
     * BFS
     * 
     * 
     * 1. Mark the starting node as visited and add it to the queue
     * 2. While the queue is not empty:
     *    - Remove the front node from the queue (dequeue)
     *    - Process/print the node
     *    - For each unvisited neighbor of this node:
     *      - Mark it as visited
     *      - Add it to the queue (enqueue)
     * 
     
     * 
     * Adjacency List representation:
     * 0 -> [1, 2]
     * 1 -> [0, 3]
     * 2 -> [0, 4]
     * 3 -> [1]
     * 4 -> [2]
     * 
     * Initial: visited = [false, false, false, false, false], queue = []
     * 
     * Step 1: Start at 0
     *   - Mark visited[0] = true
     *   - Queue: [0]
     *   - Visited: [T, F, F, F, F]
     * 
     * Step 2: Process 0, check neighbors [1, 2]
     *   - 1 not visited → mark visited[1] = true, add to queue
     *   - 2 not visited → mark visited[2] = true, add to queue
     *   - Queue: [1, 2]
     *   - Visited: [T, T, T, F, F]
     * 
     * Step 3: Process 1, check neighbors [0, 3]
     *   - 0 already visited → SKIP (prevents going back)
     *   - 3 not visited → mark visited[3] = true, add to queue
     *   - Queue: [2, 3]
     *   - Visited: [T, T, T, T, F]
     * 
     * Step 4: Process 2, check neighbors [0, 4]
     *   - 0 already visited → SKIP
     *   - 4 not visited → mark visited[4] = true, add to queue
     *   - Queue: [3, 4]
     *   - Visited: [T, T, T, T, T]
     * 
     * Step 5: Process 3, check neighbors [1]
     *   - 1 already visited → SKIP
     *   - Queue: [4]
     * 
     * Step 6: Process 4, check neighbors [2]
     *   - 2 already visited → SKIP
     *   - Queue: []
     * 
     * Final Output: BFS: 0 1 2 3 4
     * 
     * Without visited array: Would create infinite loop (0→1→0→1→0...)
     */
    public void bfs(int start){
        int n = adj.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.offer(start); // enqueue, adding to the end of the queue

        System.out.print("BFS: ");
        while (!queue.isEmpty()) {
            int u = queue.poll();// dequeue, removing from the front of the queue
            System.out.print(u + " ");

            for (int v : adj.get(u)) {
                 
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(v);
                }
                
            }
        }
        System.out.println();
    }

    public void createEdge(int u, int v){
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    /**
     * Depth-First Search (DFS) - traverses graph by going as deep as possible before backtracking
     * 
     * This method initializes the visited array and calls the recursive helper dfsUtil()
     * 
     * Algorithm (in dfsUtil):
     * 1. Mark the current node as visited
     * 2. Process/print the current node
     * 3. For each unvisited neighbor:
     *    - Recursively call dfsUtil on that neighbor (go deeper)
     * 4. When no unvisited neighbors exist, backtrack to previous call
     * 
     * Key Difference from BFS:
     * - BFS explores level by level (uses Queue - FIFO)
     * - DFS explores depth first (uses Recursion/Stack - LIFO)
     * 
     * Adjacency List representation (same graph):
     * 0 -> [1, 2]
     * 1 -> [0, 3]
     * 2 -> [0, 4]
     * 3 -> [1]
     * 4 -> [2]
     * 
     * DFS Walkthrough with visited array (starting from node 0):
     * 
     * Initial: visited = [false, false, false, false, false], call stack = []
     * 
     * Step 1: dfsUtil(0)
     *   - Mark visited[0] = true, print 0
     *   - Visited: [T, F, F, F, F]
     *   - Check neighbors [1, 2]: 1 not visited → recurse to dfsUtil(1)
     * 
     * Step 2: dfsUtil(1) [called from 0]
     *   - Mark visited[1] = true, print 1
     *   - Visited: [T, T, F, F, F]
     *   - Check neighbors [0, 3]: 0 visited (SKIP), 3 not visited → recurse to dfsUtil(3)
     * 
     * Step 3: dfsUtil(3) [called from 1]
     *   - Mark visited[3] = true, print 3
     *   - Visited: [T, T, F, T, F]
     *   - Check neighbors [1]: 1 visited (SKIP)
     *   - No unvisited neighbors → BACKTRACK to dfsUtil(1)
     * 
     * Step 4: Back to dfsUtil(1)
     *   - All neighbors processed → BACKTRACK to dfsUtil(0)
     * 
     * Step 5: Back to dfsUtil(0)
     *   - Check next neighbor [2]: 2 not visited → recurse to dfsUtil(2)
     * 
     * Step 6: dfsUtil(2) [called from 0]
     *   - Mark visited[2] = true, print 2
     *   - Visited: [T, T, T, T, F]
     *   - Check neighbors [0, 4]: 0 visited (SKIP), 4 not visited → recurse to dfsUtil(4)
     * 
     * Step 7: dfsUtil(4) [called from 2]
     *   - Mark visited[4] = true, print 4
     *   - Visited: [T, T, T, T, T]
     *   - Check neighbors [2]: 2 visited (SKIP)
     *   - No unvisited neighbors → BACKTRACK to dfsUtil(2) → BACKTRACK to dfsUtil(0)
     * 
     * Final Output: DFS: 0 1 3 2 4
     * 
     * Call Stack Visualization:
     * dfsUtil(0) → dfsUtil(1) → dfsUtil(3) [backtrack] → [backtrack] → dfsUtil(2) → dfsUtil(4) [backtrack] → [done]
     * 
     * Compare with BFS output: BFS: 0 1 2 3 4 (level by level)
     *                           DFS: 0 1 3 2 4 (depth first, then backtrack)
     */
    // Depth-First Search from source 'src' - initializes and calls recursive helper
    public void dfs(int src) {
        boolean[] visited = new boolean[adj.size()];
        System.out.print("DFS: ");
        dfsUtil(src, visited);
        System.out.println();
    }

    private void dfsUtil(int u, boolean[] visited) {
        visited[u] = true;
        System.out.print(u + " ");
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfsUtil(v, visited);
            }
        }
    }


    public static void main(String[] args){
        Grap g = new Grap(5);

        g.createEdge(0,1);
        g.createEdge(0,2);
        g.createEdge(1,3);
        g.createEdge(2,4);

         g.Print();
         g.bfs(0);
         g.dfs(0);
    }
}