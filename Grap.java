import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.Comparator;


public class Grap{
    // Global trace flag: set to true when program started with `--trace`
    public static boolean TRACE = false;
    private ArrayList<ArrayList<Integer>> adj;

    public Grap(int n){
        adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void Print(){
        for (int i = 0; i < adj.size(); i++){
            System.out.println(i + "->" + adj.get(i));
        }
    }

    /**
     * BFS - print nodes in breadth-first order from starting node
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
    * Adjacency List representation:
    * 0 -> [1, 2]
    * 1 -> [0, 3]
    * 2 -> [0, 4]
    * 3 -> [1]
    * 4 -> [2]
    *
    * Visual (undirected):
    *
    *   0
    *  / \
    * 1   2
    * |    \
    * 3     4
    *
     * 
     * Final Output: BFS: 0 1 2 3 4
     * 
     * 
     * Initial: visited = [false, false, false, false, false], queue = []
     * 
     * Step 1: Start at 0
     *   - Mark visited[0] = true
     *   - Queue: [0]
     *   - Visited: [T, F, F, F, F]
     * 
     * Step 2: DeQ 0, check neighbors [1, 2]
     *   - 1 not visited → mark visited[1] = true, add to queue
     *   - 2 not visited → mark visited[2] = true, add to queue
     *   - Queue: [1, 2]
     *   - Visited: [T, T, T, F, F]
     * 
     * Step 3: Deq 1, check neighbors [0, 3]
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

            ArrayList<Integer> neighbors_u = adj.get(u);
            for (int vi = 0; vi < neighbors_u.size(); vi++) {
                int v = neighbors_u.get(vi);
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
    * Cycle detection using BFS (for undirected graphs)
    *
    *  Example (walkthrough):
    * Consider a small graph with a triangle cycle between 0-1-2 and an extra node 3:
    * Adjacency List:     
    * 0 -> [1, 2]
    * 1 -> [0, 2]
    * 2 -> [0, 1, 3]
    * 3 -> [2]
    * 
    * Cycle between 0,1,2
    * 
    *     * 
    *   0
    *  / \
    * 1---2
    *      \
    *       3
    *
    *
   








     * Approach:
     * - We run a BFS for each connected component.
     * - Maintain a `visited[]` array and a `parent[]` array that stores the parent
     *   of each visited node in the BFS tree.
     * - When visiting a neighbor `v` of current node `u`:
     *   - If `v` is not visited: mark visited, set parent[v] = u and enqueue it.
     *   - If `v` is visited and `parent[u] != v`, then we found a back-edge
     *     (u <-> v) which indicates a cycle in an undirected graph.
     *
     
    
    
    
    
    
    
    
    
    
    
    * Why parent[] is needed:
     * - In undirected graphs every edge is two-way; without parent tracking
     *   the neighbour that leads back to the immediate parent would be
     *   interpreted as a cycle. The `parent[]` check prevents that.
     *
     *
 
    *   0
    *  / \
    * 1---2
    *      \
    *       3
    * Initial state:
    *   visited = [F, F, F, F]
    *   parent  = [-1, -1, -1, -1]
    *   queue   = []
    *
    * Start BFS at s = 0:

    *
    * Step-by-step (queue, visited, parent):
    *  1) enq 0 , mark visited[0] = T, parent[0] = -1, 
    *     queue = [0]
    *     visited = [T, F, F, F]
    *     parent  = [-1, -1, -1, -1]
    *
    *  2) dequeue u = 0, neighbors = [1,2]
    *     - v=1 not visited: visited[1]=T, parent[1]=0, enqueue 1
    *     - v=2 not visited: visited[2]=T, parent[2]=0, enqueue 2
    *     queue = [1,2]
    *     visited = [T, T, T, F]
    *     parent  = [-1, 0, 0, -1]
    *
    *  3) dequeue u = 1, neighbors = [0,2]
    * (if visisted , check visited[v] == True and parent[u] != v   => cycle)
    *     - v=0 visited and parent[1] == 0 -> it's the parent, ignore
    *     - v=2 visited and parent[1] (0) != 2 -> visited neighbor not parent
    *       => cycle detected here (1 sees 2 which is already visited but not its parent)
    *
    * Explanation: in an undirected graph the immediate parent will always be
    * visited; parent[] prevents treating that edge as a cycle. Any other
    * visited neighbor indicates a cycle.
     */
    public boolean hasCycleBFS() {
        int n = adj.size();
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = -1;

        Queue<Integer> q = new LinkedList<>();

        for (int s = 0; s < n; s++) {
            if (visited[s]) continue;
            visited[s] = true;
            parent[s] = -1;
            q.offer(s);

            while (!q.isEmpty()) {
                int u = q.poll();
                System.out.println("[TRACE] undirected BFS pop: " + u + " queue=" + q);
                ArrayList<Integer> neighbors3 = adj.get(u);
                for (int vi = 0; vi < neighbors3.size(); vi++) {
                    int v = neighbors3.get(vi);
                    if (!visited[v]) {
                        visited[v] = true;
                        parent[v] = u;
                        System.out.println("[TRACE] visit " + v + " parent[" + v + "]=" + u + " visited=" + Arrays.toString(visited));
                        q.offer(v);
                    } else if (parent[u] != v) {
                        System.out.println("[TRACE] found visited non-parent neighbor u=" + u + " v=" + v + " parent[u]=" + parent[u]);
                        // visited neighbor which is not the parent -> cycle
                        return true;
                    }
                }
            }
        }

        return false;
    }

  /**
     * Bipartite check using BFS (graph coloring)
     *
        * Simple definition 
        *   Can you color every node red or blue 
        *   so no edge connects two nodes of the same color? 
        *   If yes -> bipartite.
        *   If not -> not bipartite.
        *   
        * Examples:
        *  - Chain (bipartite):
        *      0(R) - 1(B) - 2(R) - 3(B)    -> colors: 0(R),1(B),2(R),3(B)
        * 
        * 
        * 
        * 
        * 
        *  - Triangle (not bipartite):
        *       0 
        *      / \
        *     1---2   -> impossible to 2-color (odd cycle)
        *
        * 
        * 0 - R
        * 1 - B
        * 2 - ? B? <- non bi-partitie
        * 2 - R? < non bi-partitie
        * 
        * 
        * 
        * 
        * 
        * 
        * 
        * 
     * Approach:
     * - A graph is bipartite if we can color nodes using two colors (0 and 1)
     *   so that no edge connects nodes of the same color.
     * 
     * - We perform a BFS for every connected component and assign alternating
     *   colors to neighbors: color[v] = 1 - color[u].
     * 
     * - If we ever find an edge (u, v) where color[u] == color[v], the graph
     *   is not bipartite.
     *
     *
     * Example (walkthrough) — bipartite chain:
     * Adjacency:
     * 0 -> [1]
     * 1 -> [0,2]
     * 2 -> [1,3]
     * 3 -> [2]
     *
     * Visual:
     * 0(R) - 1(B) - 2(R) - 3(B)
     *
    * Start at 0: color[0]=0
    *R = 0
    *B = 1
    * Chain (detailed): 0 - 1 - 2 - 3
    *  - Initial: color = [-1, -1, -1, -1], queue = []
    *  - Color 0 = 0, enqueue 0
    *    queue = [0], color = [0, -1, -1, -1]
    * 
    *  - Dequeue 0: my color = 0 ; neighbor 1 uncolored -> color[1] = 1 (opp of my color), enqueue 1
    *    queue = [1], color = [0, 1, -1, -1]
    * 
    *  - Dequeue 1: my color = 1; neighbors 0 (color 0 OK), 2 uncolored -> color[2] = 0 (opp of my color), enqueue 2
    *    queue = [2], color = [0, 1, 0, -1]
    * 
    *  - Dequeue 2: my color = 0; neighbors 1 (1 OK), 3 uncolored -> color[3] = 1 (opp of my color), enqueue 3
    *    queue = [3], color = [0, 1, 0, 1]
    * 
    *  - Dequeue 3: my color = 1; neighbor 2 (0 OK)
    *  - No conflicts found -> graph is bipartite
    *
    * Triangle (detailed): 0 - 1 - 2 - (back to 0)
    *   *       0 (0)
        *      / \
        *     1 (1)---2 (1/0?)  
    
    *  - Initial: color = [-1, -1, -1], queue = []
    * 
    *  - Color 0 = 0, enqueue 0
    *    queue = [0], color = [0, -1, -1]
    * 
    *  - Dequeue 0: neighbor 1 uncolored -> color[1] = 1 (opp to what we dequeued), enqueue 1
    *              neighbor 2 uncolored -> color[2] = 1, enqueue 2
    *    queue = [1,2], color = [0,1,1]
    * 
    *  - Dequeue 1: neighbor 0 (0 OK).    (Different than what was dequeued)
    *               neighbor 2 (1 NOT OK) (Same as what was dequeued)
    *    -> both endpoints of edge (1,2) have color 1 => not bipartite
    *
    * Explanation: BFS coloring assigns alternating colors along layers. If
    * an edge connects two nodes of the same color, an odd cycle exists and
    * the graph is not bipartite.
     */
    public boolean isBipartiteBFS() {
        int n = adj.size();
        int[] color = new int[n];
        for (int i = 0; i < n; i++) color[i] = -1; // -1 = uncolored

        Queue<Integer> q = new LinkedList<>();

        for (int s = 0; s < n; s++) {
            if (color[s] != -1) continue;
            color[s] = 0;
            System.out.println("[TRACE] start bipartite BFS at " + s + " color=" + Arrays.toString(color));
            q.offer(s);

            while (!q.isEmpty()) {
                int u = q.poll();
                System.out.println("[TRACE] bipartite pop: " + u + " queue=" + q + " color=" + Arrays.toString(color));
                ArrayList<Integer> neighbors = adj.get(u);
                for (int vi = 0; vi < neighbors.size(); vi++) {
                    int v = neighbors.get(vi);
                    if (color[v] == -1) {
                        color[v] = 1 - color[u];
                        System.out.println("[TRACE] color node " + v + " = " + color[v]);
                        q.offer(v);
                    } else if (color[v] == color[u]) {
                        System.out.println("[TRACE] bipartite conflict: " + u + " and " + v + " both color " + color[u]);
                        return false;
                    }
                }
            }
        }

        return true;
    }

        /**
     * Title: Shortest Path in Undirected Graph with Unit Weight (BFS)
     *
     * Approach:
     * - For graphs where every edge has weight 1, BFS from the source finds
     *   shortest path distances in O(V+E).
     * - Maintain `dist[]` initialized to -1 (unreached) and `parent[]` for
     *   optional path reconstruction. Set `dist[src]=0`, enqueue src.
     * - When visiting node u, any neighbor v with `dist[v]==-1` is discovered
     *   at distance `dist[u]+1` and enqueued.
     *
    weight - distance between two nodes for eg
    unit weight - all 1
     *
     * Example Walkthrough (adjacency + ASCII):
     * Adjacency (undirected chain):
     * 0 -> [1]
     * 1 -> [0,2]
     * 2 -> [1,3]
     * 3 -> [2]
     *
     * Visual:
     *
     * 0 - 1 - 2 - 3
     * 
     *  dist = [0,1,2,3] from src=0
     *
     * Walkthrough (step-by-step showing variables):
     * - Initial:
     *     dist = [-1, -1, -1, -1]
     *     parent = [-1, -1, -1, -1]
     *     queue = []
     *
     * - Start at src = 0:
     *     dist = [0, -1, -1, -1]
     *     parent = [-1, -1, -1, -1]
     *     queue = [0]
     *
     * - Pop 0, explore neighbors [1]:
     *     dist[1] = 1 (Popped node dist + 1), parent[1] = 0
     *     queue = [1]
     *     dist = [0, 1, -1, -1]
     *
     * - Pop 1, explore neighbors [0,2]: 0 already visited, 2 discovered:
     *     dist[2] = 2, parent[2] = 1
     *     queue = [2]
     *     dist = [0,1,2,-1]
     *
     * - Pop 2, discover 3:
     *     dist[3] = 3, parent[3] = 2
     *     queue = [3]
     *     dist = [0,1,2,3]
     *
     * - Pop 3, no new neighbors -> done.
     *
    * Result: distances from 0 are [0,1,2,3].
    *    * Additional example (branching):
    * Graph:
    *   0
    *  / \
    * 1   2
    *  \ /
    *   3
    * Edges: 0-1, 0-2, 1-3, 2-3 (unit weight)
    * Distances from src=0: dist = [0, 1, 1, 2]
    * 
    * Explanation: nodes 1 and 2 are discovered at distance 1; node 3
    * is discovered later via either 1 or 2 at distance 2.
    * 
     */ 

    public int[] shortestPathUnitWeight(int src) {
        int n = adj.size();
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) dist[i] = -1;

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = -1;

        Queue<Integer> q = new LinkedList<>();
      
        dist[src] = 0;
        q.offer(src);

        while (!q.isEmpty()) {
            int u = q.poll();
            ArrayList<Integer> nbrs = adj.get(u);
            for (int vi = 0; vi < nbrs.size(); vi++) {
                int v = nbrs.get(vi);
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    parent[v] = u;
                    q.offer(v);
                }
            }
        }

        // print distances for convenience
        if (TRACE) System.out.println("[TRACE] unit-weight dist from " + src + " = " + Arrays.toString(dist));
        return dist;
    }


    /**
     * Title: Dijkstra's Algorithm (PriorityQueue implementation)
     * shortestPathUnitWeight (BFS): for graphs where every edge has weight = 1. It finds shortest paths measured by number of edges (levels).
     * dijkstraPQ: for graphs with arbitrary nonnegative weights. It finds shortest paths minimizing total weight (sum of weights).
     *
     * Approach:
     * - Works for non-negative edge weights. Expects `wadj` where each
     *   element is a list of `int[]{v, w}` pairs representing edges u->v
     *   with weight w >= 0.
     * - Maintain `dist[]` initialized to INF, set `dist[src]=0` and push
     *   (0, src) to a min-heap keyed by distance.
     * - Repeatedly pop the heap: for node u with tentative distance d, if
     *   d != dist[u] skip (stale entry). Otherwise relax each neighbor v by
     *   checking `dist[v] > dist[u] + w` and pushing updated pairs to the heap.
     *
     * Complexity: O((V + E) log V) because each edge relaxation may push to
     * the heap and heap ops cost O(log V).
     *
     * Example Walkthrough (adjacency + ASCII) — weighted graph used in `main`:
     * Adjacency (undirected example represented as directed entries):
     * 0 -> [(1,1), (4,5)]
     * 1 -> [(0,1), (2,2)]
     * 2 -> [(1,2), (3,1)]
     * 3 -> [(2,1)]
     * 4 -> [(0,5)]
     *
     * Visual (schematic):
     *
     * 0 --1-- 1 --2-- 2 --1-- 3
     *  |
     *  5
     *  |
     *  4
     *
     * Walkthrough (step-by-step showing `dist[]` and `pq` contents):
     * - Initial:
     *     dist = [0, INF, INF, INF, INF]
     *     pq = [(0,0)]  // (distance,node)
     *
     * - Pop (0,0): relax edges (0->1 weight1),(0->4 weight5):
     *     dist -> [0,1,INF,INF,5]
     *     pq -> [(1,1),(5,4)]
     *
     * - Pop (1,1): relax (1->2 weight2):
     *     dist -> [0,1,3,INF,5]
     *     pq -> [(3,2),(5,4)]
     *
     * - Pop (3,2): relax (2->3 weight1):
     *     dist -> [0,1,3,4,5]
     *     pq -> [(4,3),(5,4)]
     *
     * - Pop (4,3): no better relaxations
     * - Pop (5,4): no better relaxations
     *
     * Final distances: [0,1,3,4,5]
     * ref - https://www.youtube.com/watch?v=V6H1qAeB-l4
     */
    public static int[] dijkstraPQ(ArrayList<ArrayList<int[]>> wadj, int src) {
        int n = wadj.size();
        final int INF = Integer.MAX_VALUE / 4;
        int[] dist = new int[n];

        for (int i = 0; i < n; i++) dist[i] = INF;
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, src}); // {dist, node}

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int d = top[0], u = top[1];
            
            if (d != dist[u]) continue; // stale entry
            ArrayList<int[]> nbrs = wadj.get(u);
            for (int i = 0; i < nbrs.size(); i++) {
                int v = nbrs.get(i)[0];
                int w = nbrs.get(i)[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        return dist;
    }



/////// ALL
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
    * Visual (undirected):
    *
    *   0
    *  / \
    * 1   2
    * |    \
    * 3     4
    *
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
        ArrayList<Integer> neighbors2 = adj.get(u);
        for (int vi = 0; vi < neighbors2.size(); vi++) {
            int v = neighbors2.get(vi);
            if (!visited[v]) {
                dfsUtil(v, visited);
            }
        }
    }


    


    /**
     * Cycle detection using DFS (for undirected graphs)
     *
     * Approach:
     * - Perform DFS from each unvisited node, passing the parent node in the recursion.
     * - When exploring neighbors of `u`:
     *   - If neighbor `v` is not visited, recursively visit it with parent = u.
     *   - If neighbor `v` is visited and `v` is not the parent of `u`, then we
     *     found a back-edge and therefore a cycle. 
     *
     * This mirrors the BFS approach but uses recursion (or an explicit stack).
     * Complexity: O(V + E) time, O(V) recursion/stack space for visited.
     *
    * Example (walkthrough):
    * Use the same triangle example as above:
    * 0 -> [1, 2]
    * 1 -> [0, 2]
    * 2 -> [0, 1, 3]
    * 3 -> [2]
    *
    * Visual (triangle + tail):
    *
    *   0
    *  / \
    * 1---2
    *      \
    *       3
    *
     *
    * DFS starting at 0 (detailed steps showing recursion stack and visited):
    *
    * 1) Call dfsUtil(0, parent=-1):
    *     visited = [T, F, F, F]
    *     recursion stack: [dfsUtil(0,-1)]
    *     neighbors(0) = [1,2] -> take 1 first
    *
    * 2) Call dfsUtil(1, parent=0):
    *     visited = [T, T, F, F]
    *     recursion stack: [dfsUtil(0,-1), dfsUtil(1,0)]
    *     neighbors(1) = [0,2]
    *       - neighbor 0 visited and 0 == parent(1) -> ignore
    *       - neighbor 2 not visited -> recurse
    *
    * 3) Call dfsUtil(2, parent=1):
    *     visited = [T, T, T, F]
    *     recursion stack: [dfsUtil(0,-1), dfsUtil(1,0), dfsUtil(2,1)]
    *     neighbors(2) = [0,1,3]
    *       - neighbor 0 is visited AND 0 != parent(2) (parent(2)=1)
    *         => back-edge from 2 to 0 found (ancestor that is not parent)
    *         => cycle detected, unwind recursion and return true
    *
    * Explanation: DFS uses the recursion/stack path to detect back-edges.
    * When a visited neighbor is not the immediate parent, it points to an
    * ancestor and thus indicates a cycle in an undirected graph.
     */
    public boolean hasCycleDFS() {
        int n = adj.size();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfsCycleUtil(i, -1, visited)) return true;
            }
        }
        return false;
    }

    private boolean dfsCycleUtil(int u, int parent, boolean[] visited) {
        visited[u] = true;
        System.out.println("[TRACE] enter dfsCycle u=" + u + " parent=" + parent + " visited=" + Arrays.toString(visited));
        ArrayList<Integer> neighbors4 = adj.get(u);
        for (int vi = 0; vi < neighbors4.size(); vi++) {
            int v = neighbors4.get(vi);
            System.out.println("[TRACE] dfsCycle exploring " + u + " -> " + v + ", visited[v]=" + visited[v]);
            if (!visited[v]) {
                if (dfsCycleUtil(v, u, visited)) return true;
            } else if (v != parent) {
                // visited and not parent -> cycle
                System.out.println("[TRACE] dfsCycle found back-edge " + u + " -> " + v + " (cycle)");
                return true;
            }
        }
        System.out.println("[TRACE] leave dfsCycle u=" + u);
        return false;
    }


  


    /**
     * Bipartite check using DFS (graph coloring)
     *
     * Approach:
     * - Use DFS to color nodes recursively. Assign color[u] = c, and for each
     *   neighbor v:
     *     - if uncolored, recurse with color 1-c
     *     - if colored and color[v] == color[u], graph is not bipartite
     * - Run DFS from each uncolored node to cover all components.
     *
     * Complexity: O(V + E) time, O(V) recursion/stack space.
     *
     * Example: same chain and triangle examples as in the BFS method above.
     */
    public boolean isBipartiteDFS() {
        int n = adj.size();
        int[] color = new int[n];
        for (int i = 0; i < n; i++) color[i] = -1;

        for (int i = 0; i < n; i++) {
            if (color[i] == -1) {
                if (!dfsColorUtil(i, 0, color)) return false;
            }
        }
        return true;
    }

    private boolean dfsColorUtil(int u, int c, int[] color) {
        color[u] = c;
        ArrayList<Integer> neighbors = adj.get(u);
        for (int vi = 0; vi < neighbors.size(); vi++) {
            int v = neighbors.get(vi);
            if (color[v] == -1) {
                if (!dfsColorUtil(v, 1 - c, color)) return false;
            } else if (color[v] == c) {
                return false;
            }
        }
        return true;
    }

    /**
     * Quick complexity summary (all these are for simple adjacency-list graph):
     *
     * - BFS traversal (`bfs`):
     *   Time: O(V + E)   Space: O(V) for visited + queue
     *
     * - DFS traversal (`dfs` / `dfsUtil`):
     *   Time: O(V + E)   Space: O(V) recursion/stack + visited
     *
     * - Cycle detection (BFS `hasCycleBFS`):
     *   Time: O(V + E)   Space: O(V) for visited + parent + queue
     *
     * - Cycle detection (DFS `hasCycleDFS`):
     *   Time: O(V + E)   Space: O(V) recursion/stack + visited
     *
     * - Bipartite check (BFS `isBipartiteBFS`):
     *   Time: O(V + E)   Space: O(V) for color array + queue
     *
     * - Bipartite check (DFS `isBipartiteDFS`):
     *   Time: O(V + E)   Space: O(V) recursion/stack + color array
     *
     * Notes:
     * - V = number of vertices, E = number of edges.
     * - Using adjacency lists ensures the O(V + E) time bound for these
     *   graph traversals and checks.
     */

    /**
     * Add a directed edge u -> v (helper):
     * - This keeps the same adjacency list but treats edges as one-way.
     * - Use this when testing topological sort / directed-cycle checks.
     */
    public void createEdgeDirected(int u, int v) {
        adj.get(u).add(v);
    }

    /**
     * Title: Dijkstra (PQ) single-target distance (early exit, no path rebuild)
     *
     * Problem (what it solves): Given a weighted graph with nonnegative edges, a source `src`
     * and a target `dst`, find the shortest distance from src to dst only. Stop as soon as dst
     * is finalized in the priority queue. This is for cases where you only care about one
     * destination and don’t need the full dist[] array or the path itself.
     *
     * Output: The shortest distance to dst (or INF/very large if unreachable).
     *
     * Approach: Standard Dijkstra with nonnegative weights; break when the popped node is dst.
     * We skip parent tracking here to keep it minimal.
     *
     * Example Walkthrough (adjacency + ASCII) — weighted graph from `main`:
     * Adjacency (undirected example represented as directed entries):
     * 0 -> [(1,1), (4,5)]
     * 1 -> [(0,1), (2,2)]
     * 2 -> [(1,2), (3,1)]
     * 3 -> [(2,1)]
     * 4 -> [(0,5)]
     *
     * Visual (schematic):
     *
     * 0 --1-- 1 --2-- 2 --1-- 3
     *  |
     *  5
     *  |
     *  4
     *
     * Walkthrough (step-by-step for src=0, dst=3 showing `dist[]` and `pq` contents):
     * - Initial:
     *     dist = [0, INF, INF, INF, INF]
     *     pq = [(0,0)]  // (distance,node)
     *
     * - Pop (0,0): u=0, d=0; u != dst(3), so continue relaxing
     *     relax edges: (0->1 weight1), (0->4 weight5)
     *     dist -> [0, 1, INF, INF, 5]
     *     pq -> [(1,1), (5,4)]
     *
     * - Pop (1,1): u=1, d=1; u != dst(3), so continue
     *     relax edge: (1->2 weight2)
     *     dist -> [0, 1, 3, INF, 5]
     *     pq -> [(3,2), (5,4)]
     *
     * - Pop (3,2): u=2, d=3; u != dst(3), so continue
     *     relax edge: (2->3 weight1)
     *     dist -> [0, 1, 3, 4, 5]
     *     pq -> [(4,3), (5,4)]
     *
     * - Pop (4,3): u=3, d=4; u == dst(3) -> BREAK and return 4
     *
     * Result: shortest distance from 0 to 3 is 4
     */
    public static int dijkstraPQToTargetDistance(ArrayList<ArrayList<int[]>> wadj, int src, int dst) {
        int n = wadj.size();
        final int INF = Integer.MAX_VALUE / 4;
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) dist[i] = INF;
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int d = top[0], u = top[1];
            if (d != dist[u]) continue; // stale
            if (u == dst) return d;      // earliest time dst is finalized
            ArrayList<int[]> nbrs = wadj.get(u);
            for (int i = 0; i < nbrs.size(); i++) {
                int v = nbrs.get(i)[0];
                int w = nbrs.get(i)[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }
        return INF; // unreachable
    }

    /**
     * Title: Dijkstra (PQ) early-exit to a target with path reconstruction
     *
     * Problem (what it solves): Shortest path from `src` to a single `dst` in a
     * nonnegative weighted graph, but we don’t need distances to every other node.
     * We stop as soon as dst is finalized by the PQ (earliest time its shortest distance
     * is known). Also return the actual node path.
     *
     * Output: List of nodes along the shortest path from src to dst (empty if unreachable).
     *
     * Approach: Standard Dijkstra with an early break when `u == dst` is popped. Keep parent[]
     * to rebuild the path; parent stays -1 for unreachable nodes.
     *
     * Walkthrough on the sample graph in main (edges: 0-1 (1), 1-2 (2), 2-3 (1), 0-4 (5)) for src=0, dst=3:
     * - dist progression: [0,1,3,4,5]
     * - parent progression: [-1,0,1,2,0]
     * - Early exit happens when node 3 is popped with distance 4.
     * - Path reconstruction: 3 <- 2 <- 1 <- 0 => [0,1,2,3]
     */
    public static ArrayList<Integer> dijkstraPQPathToTarget(ArrayList<ArrayList<int[]>> wadj, int src, int dst) {
        int n = wadj.size();
        final int INF = Integer.MAX_VALUE / 4;
        int[] dist = new int[n];
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = INF;
            parent[i] = -1;
        }
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int d = top[0], u = top[1];
            if (d != dist[u]) continue;
            if (u == dst) break; // earliest time dst is finalized
            ArrayList<int[]> nbrs = wadj.get(u);
            for (int i = 0; i < nbrs.size(); i++) {
                int v = nbrs.get(i)[0];
                int w = nbrs.get(i)[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        ArrayList<Integer> path = new ArrayList<>();
        if (dist[dst] == INF) return path; // unreachable
        for (int cur = dst; cur != -1; cur = parent[cur]) path.add(cur);
        for (int i = 0, j = path.size() - 1; i < j; i++, j--) {
            int tmp = path.get(i);
            path.set(i, path.get(j));
            path.set(j, tmp);
        }
        return path;
    }

    /**
     * Title: Dijkstra's Algorithm (TreeSet / ordered set implementation)
     *
     * Approach:
     * - Use a `TreeSet` ordered by (distance, node) to simulate a priority
     *   queue with explicit decrease-key via remove+add operations.
     * - Maintain `dist[]` initialized to INF and the set containing (dist[src], src).
     * - Repeatedly extract the smallest (dist,u) from the set, relax outgoing
     *   edges and when a neighbor's distance improves remove its old (dist,v)
     *   entry (if present) and insert the updated pair.
     *
     * Complexity: O((V + E) log V). This implementation is convenient when a
     * proper decrease-key operation is needed but comes with the overhead of
     * remove+add operations.
     *
     * Example Walkthrough (same weighted graph as above):
     * - Initial set: {(0,0)} and dist = [0,INF,INF,INF,INF]
     * - Poll (0,0) -> relax entries -> insert (1,1) and (5,4)
     * - Poll (1,1) -> relax -> insert (3,2)
     * - Poll (3,2) -> relax -> insert (4,3)
     * - Continue until set empty, final dist = [0,1,3,4,5]
     */
    public static int[] dijkstraSet(ArrayList<ArrayList<int[]>> wadj, int src) {
        int n = wadj.size();
        final int INF = Integer.MAX_VALUE / 4;
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) dist[i] = INF;
        dist[src] = 0;

        Comparator<int[]> cmp = (a,b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        };

        TreeSet<int[]> set = new TreeSet<>(cmp);
        set.add(new int[]{0, src});

        while (!set.isEmpty()) {
            int[] top = set.pollFirst();
            int d = top[0], u = top[1];
            if (d > dist[u]) continue;
            ArrayList<int[]> nbrs = wadj.get(u);
            for (int i = 0; i < nbrs.size(); i++) {
                int v = nbrs.get(i)[0];
                int w = nbrs.get(i)[1];
                if (dist[v] > dist[u] + w) {
                    // remove old entry if present
                    if (dist[v] != INF) set.remove(new int[]{dist[v], v});
                    dist[v] = dist[u] + w;
                    set.add(new int[]{dist[v], v});
                }
            }
        }

        return dist;
    }

    /**
     * Title: Topological Sort (Kahn's algorithm)
     * 
     * 
     * What: Produce a list of nodes so that every prerequisite comes before
     *       the task that depends on it.
     * Example: If A -> B and B -> C (A before B, B before C), the order is
     *          A, B, C.
     * 
     * 
     * You can apply topological sort to any directed graph, 
     * but it only produces a useful ordering when the graph is a DAG (no cycles). 
     * If the graph has cycles, topological sort fails — and that failure is useful: 
     * it tells you your dependency graph has a circular dependency that you must fix.
     * 
     * Real-world use:
    *   - Package managers / dependency resolution: order installation of packages by dependency graph to avoid missing prerequisites.
    *   - Task scheduling & CI pipelines: schedule jobs where some depend on others.
    * - Course or workflow planning: find a legal ordering for prerequisites.
     *
     * Approach:
     *
     *  - Compute the in-degree (number of incoming edges) for every node.
     *
     * - Put all nodes with in-degree 0 into a queue (these have no prerequisites).
     *
     *  - Repeatedly pop a node u from the queue, 
     *      append u to the result order,
     *      and decrement the in-degree of each outgoing neighbor v. 
     *      If indeg[v] becomes 0, enqueue v. 
     * 
     * - If the final order contains all nodes => graph is a DAG and order is valid.
     *   Otherwise a directed cycle exists (some nodes never reach indeg 0).
     *
     * Example Walkthrough (adjacency + ASCII) — DAG used in `main` (dg):
     * Adjacency list (directed):
     * 0 -> []
     * 1 -> []
     * 2 -> [3]
     * 3 -> [1]
     * 4 -> [0, 1]
     * 5 -> [2, 0]
     *
     * Visual (schematic):
     *

     *        5 --> 2 --> 3 --> 1
     *        |                 ^
     *        v                 |
     *        0 <-------------- 4   
     *    
     * Result Order : 4,5,2,0,3,1
     * 
     * Walkthrough (step-by-step with variable values):
     * - Initial: compute indeg by counting incoming (arrows incoming) edges:
     *     indeg = [2, 2, 1, 1, 0, 0]
     *     queue = [4, 5] // nodes with indeg 0
     *     order = [] // result
     *
     * - Step 1: pop 4
     *     popped = 4
     *     order = [4] // process node 4
     *     process neighbors of 4: 0,1
     *       indeg[0] 2 -> 1 // decrement indeg, enqueue if 0
     *       indeg[1] 2 -> 1 // decrement indeg, enqueue if 0
     *     queue = [5]
     *     indeg = [1,1,1,1,0,0]
     *
     * - Step 2: pop 5
     *     popped = 5
     *     order = [4,5] // process node 5
     *     process neighbors of 5: 2,0
     *       indeg[2] 1 -> 0 (enqueue 2) // decrement indeg, enqueue if 0
     *       indeg[0] 1 -> 0 (enqueue 0) // decrement indeg, enqueue if 0
     *     queue = [2,0]
     *     indeg = [0,1,0,1,0,0]
     *
     * - Step 3: pop 2
     *     popped = 2
     *     order = [4,5,2]
     *     process neighbors of 2: 3
     *       indeg[3] 1 -> 0 (enqueue 3)
     *     queue = [0,3]
     *     indeg = [0,1,0,0,0,0]
     *
     * - Step 4: pop 0
     *     popped = 0
     *     order = [4,5,2,0]
     *     neighbors of 0: none
     *     queue = [3]
     *
     * - Step 5: pop 3
     *     popped = 3
     *     order = [4,5,2,0,3]
     *     process neighbors of 3: 1
     *       indeg[1] 1 -> 0 (enqueue 1)
     *     queue = [1]
     *     indeg = [0,0,0,0,0,0]
     *
     * - Step 6: pop 1
     *     popped = 1
     *     order = [4,5,2,0,3,1]
     *     queue = []  (done)
     *
     * Final: order contains all 6 nodes -> valid topological order.
     *
  

    */
  
    public ArrayList<Integer> topologicalSortKahn() {
        int n = adj.size();
        int[] indeg = new int[n];
        // building our initial indeg array
        for (int u = 0; u < n; u++) {
            ArrayList<Integer> nbrs = adj.get(u);
            for (int vi = 0; vi < nbrs.size(); vi++) {
                int v = nbrs.get(vi);
                indeg[v]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) if (indeg[i] == 0) q.offer(i);
        System.out.println("[TRACE] initial indeg: " + Arrays.toString(indeg));
        System.out.println("[TRACE] initial queue: " + q);

        ArrayList<Integer> order = new ArrayList<>();

        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.println("[TRACE] pop: " + u + " queue-> " + q);
            order.add(u);
            ArrayList<Integer> nbrs = adj.get(u);
            for (int vi = 0; vi < nbrs.size(); vi++) {
                int v = nbrs.get(vi);
                indeg[v]--;
                System.out.println("[TRACE] decreased indeg[" + v + "] -> " + indeg[v]);
                if (indeg[v] == 0) {
                    q.offer(v);
                    System.out.println("[TRACE] enqueue: " + v + " queue-> " + q);
                }
            }
        }

        System.out.println("[TRACE] final order: " + order);

        return order;
    }

    /**
     * Title: Directed-Cycle Detection (Kahn / topological order check)
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
     * Approach:
     * - Use Kahn's topological process: compute indegree for each node,
     *   enqueue nodes with indeg == 0, repeatedly pop and decrement neighbors'.
     * - If after processing the produced ordering contains all nodes then the
     *   graph is acyclic (DAG). If the ordering size is less than V then at
     *   least one directed cycle exists: nodes within cycles never reach indeg 0.
     *
     * Example Walkthrough (adjacency + ASCII) — 3-node directed cycle used in `main`:
     * Adjacency list (directed):
     * 0 -> [1]
     * 1 -> [2]
     * 2 -> [0]
     *
     * Visual:
     *
     *   3->0 -> 1 -> 2
     *       ^         |
     *       |---------
     *
     * Step-by-step (variables shown):
     * - Initial:
     *     indeg = [1, 1, 1,0]
     *     queue = []
     *     order = []
     * - Kahn cannot pop any node (queue empty), so no node is processed.
     * - Final: order.length = 0 < V(3) => cycle detected.
     *
    * TRACE notes: "initial indeg" and "initial queue" make this immediate
     * to see (no indeg 0 nodes means a cycle exists). A non-empty partial order
     * at the end indicates some acyclic portion, but size != V still means cycle(s).
    *
    * Real-world use:
    * - Detecting circular dependencies in package managers (e.g., npm, pip)
    *   or build graphs where a cycle prevents installation/build.
    * - Scheduling and workflow validation: identify impossible task orders.
    * - Static analysis: flag cycles in module imports or service dependency
    *   graphs to prevent runtime failures.
    * - Pre-check before attempting to resolve or break cycles automatically.
     */
    public boolean hasCycleDirectedBFS() {
        ArrayList<Integer> order = topologicalSortKahn();
        return order.size() != adj.size();
    }

    /**
     * Title: Directed-Cycle Detection (DFS with recursion stack)
     *
     * Approach:
     * - Run DFS from every unvisited node and maintain two arrays:
     *     - visited[u]: true once node u has been visited at least once.
     *     - recStack[u]: true while node u is on the current recursion path.
     * - For each explored edge u -> v:
     *     - If v is not visited, recurse into v.
     *     - If v is visited and recStack[v] is true, we've found a back-edge to
     *       an ancestor on the current path, which implies a directed cycle.
     * - After finishing u's neighbors set recStack[u] = false (pop u from path).
     *
     * Example Walkthrough (adjacency + ASCII) — 3-node directed cycle used in `main`:
     * Adjacency list (directed):
     * 0 -> [1]
     * 1 -> [2]
     * 2 -> [0]
     *
     * Visual:
     *
     *   0 -> 1 -> 2
     *   ^         |
     *   |---------
     *
     * Walkthrough (step-by-step with variable values):
     * - Start (all false):
     *     visited = [F, F, F]
     *     recStack = [F, F, F]
     *
     * - Enter dfsDirectedUtil(0):
     *     visited = [T, F, F]
     *     recStack = [T, F, F]
     *     exploring edge 0 -> 1
     *
     * - Recurse into 1:
     *     visited = [T, T, F]
     *     recStack = [T, T, F]
     *     exploring edge 1 -> 2
     *
     * - Recurse into 2:
     *     visited = [T, T, T]
     *     recStack = [T, T, T]
     *     exploring edge 2 -> 0
     *
     * - Inspect edge 2 -> 0: visited[0] == true and recStack[0] == true ->
     *   back-edge to ancestor found -> cycle detected.
     *
    * TRACE notes: with `--trace` the method prints the recStack snapshot at
     * entry/exit and each edge exploration; a back-edge will be printed as
     * "back-edge detected: u -> v (cycle)" and the DFS will return true up the
     * recursion chain.
    *
    * Real-world use:
    * - Runtime or static analysis to detect cycles in call graphs or
    *   dependency graphs (helps prevent infinite recursion or initialization
    *   loops).
    * - Deadlock detection in resource-allocation graphs: cycles indicate
    *   potential deadlocks among processes holding resources.
    * - Detecting routing loops in networking / link graphs where packets may
    *   circulate indefinitely.
    * - Use during code/package refactoring to find and break unwanted cycles.
     */
    public boolean hasCycleDirectedDFS() {
        int n = adj.size();
        boolean[] visited = new boolean[n];
        boolean[] recStack = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfsDirectedUtil(i, visited, recStack)) return true;
            }
        }
        return false;
    }

    private boolean dfsDirectedUtil(int u, boolean[] visited, boolean[] recStack) {
        visited[u] = true;
        recStack[u] = true;
        System.out.println("[TRACE] enter dfsDirected " + u + " recStack=" + Arrays.toString(recStack));

        ArrayList<Integer> nbrs = adj.get(u);
        for (int vi = 0; vi < nbrs.size(); vi++) {
            int v = nbrs.get(vi);
            System.out.println("[TRACE] exploring edge " + u + " -> " + v + " visited[v]=" + visited[v] + " recStack[v]=" + recStack[v]);
            if (!visited[v]) {
                if (dfsDirectedUtil(v, visited, recStack)) return true;
            } else if (recStack[v]) {
                // found a back-edge to node on current recursion stack -> cycle
                System.out.println("[TRACE] back-edge detected: " + u + " -> " + v + " (cycle)");
                return true;
            }
        }

        recStack[u] = false; // pop u from recursion stack
        System.out.println("[TRACE] leave dfsDirected " + u + " recStack=" + Arrays.toString(recStack));
        return false;
    }


    public static void main(String[] args){
        // enable trace if `--trace` provided on command line
        for (String a : args) if ("--trace".equals(a)) TRACE = true;

        Grap g = new Grap(5);

        g.createEdge(0,1);
        g.createEdge(0,2);
        g.createEdge(1,3);
        g.createEdge(2,4);

         g.Print();
         g.bfs(0);
         g.dfs(0);
       //  System.out.println("Has cycle (BFS): " + g.hasCycleBFS());
       //  System.out.println("Has cycle (DFS): " + g.hasCycleDFS());

        // Create a graph that contains a cycle: 0-1-2-0 with a tail 2-3
        Grap g2 = new Grap(4);
        g2.createEdge(0,1);
        g2.createEdge(1,2);
        g2.createEdge(2,0);
        g2.createEdge(2,3);

        System.out.println();
        g2.Print();
        g2.bfs(0);
        g2.dfs(0);
        System.out.println("Has cycle (BFS): " + g2.hasCycleBFS());
        System.out.println("Has cycle (DFS): " + g2.hasCycleDFS());

        // Bipartite examples (undirected)
        // chain: 0-1-2-3 (bipartite)
        Grap g3 = new Grap(4);
        g3.createEdge(0,1);
        g3.createEdge(1,2);
        g3.createEdge(2,3);

        System.out.println();
        System.out.println("Chain graph (0-1-2-3):");
        g3.Print();
        System.out.println("isBipartiteBFS: " + g3.isBipartiteBFS());
        System.out.println("isBipartiteDFS: " + g3.isBipartiteDFS());

        // triangle (non-bipartite) example using g2's triangle portion
        System.out.println();
        System.out.println("Triangle+tail (g2) bipartite? BFS: " + g2.isBipartiteBFS() + ", DFS: " + g2.isBipartiteDFS());

        // -----------------------
        // Directed graph examples
        // -----------------------
        // DAG example for topological sort (nodes 0..5)
        Grap dg = new Grap(6);
        dg.createEdgeDirected(5, 2);
        dg.createEdgeDirected(5, 0);
        dg.createEdgeDirected(4, 0);
        dg.createEdgeDirected(4, 1);
        dg.createEdgeDirected(2, 3);
        dg.createEdgeDirected(3, 1);

        System.out.println();
        System.out.println("Directed DAG (for topological sort):");
        dg.Print();

        ArrayList<Integer> topo = dg.topologicalSortKahn();
        System.out.println("Topological sort (Kahn): " + topo);
        System.out.println("Has directed cycle (Kahn): " + dg.hasCycleDirectedBFS());
        System.out.println("Has directed cycle (DFS): " + dg.hasCycleDirectedDFS());

        // Directed cycle example
        Grap dg2 = new Grap(3);
        dg2.createEdgeDirected(0, 1);
        dg2.createEdgeDirected(1, 2);
        dg2.createEdgeDirected(2, 0);

        System.out.println();
        System.out.println("Directed graph with cycle:");
        dg2.Print();
        System.out.println("Topological sort (Kahn): " + dg2.topologicalSortKahn());
        System.out.println("Has directed cycle (Kahn): " + dg2.hasCycleDirectedBFS());
        System.out.println("Has directed cycle (DFS): " + dg2.hasCycleDirectedDFS());

        // -----------------------
        // Weighted graph examples (for shortest paths / Dijkstra)
        // -----------------------
        int wn = 5;
        ArrayList<ArrayList<int[]>> wadj = new ArrayList<>(wn);
        for (int i = 0; i < wn; i++) wadj.add(new ArrayList<>());
        // undirected weighted edges (u <-> v, weight)
        // 0 --1-- 1 --2-- 2 --1-- 3
        //  \
        //   4 (weight 5) connects 0-4
        wadj.get(0).add(new int[]{1, 1}); wadj.get(1).add(new int[]{0,1});
        wadj.get(1).add(new int[]{2, 2}); wadj.get(2).add(new int[]{1,2});
        wadj.get(2).add(new int[]{3, 1}); wadj.get(3).add(new int[]{2,1});
        wadj.get(0).add(new int[]{4, 5}); wadj.get(4).add(new int[]{0,5});

        System.out.println();
        System.out.println("Weighted graph (example) shortest paths from 0:");
        int[] distUnit = g.shortestPathUnitWeight(0);
        System.out.println("Unit-weight BFS distances: " + Arrays.toString(distUnit));

        int[] distPQ = dijkstraPQ(wadj, 0);
        System.out.println("Dijkstra (PQ) distances: " + Arrays.toString(distPQ));

        int[] distSet = dijkstraSet(wadj, 0);
        System.out.println("Dijkstra (Set) distances: " + Arrays.toString(distSet));
    }
}