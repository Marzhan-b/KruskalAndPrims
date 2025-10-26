--
# Assignment 3 by Tulebayeva Marzhan from SE-2423
# **In-depth Analytical Report on Prim's and Kruskal's Algorithms**
## 1. Introduction
In the study of Minimum Spanning Trees (MSTs), Prim’s and Kruskal’s algorithms stand as two of the most commonly used methods for solving the problem. Both are greedy algorithms, but they differ in the approach they take to select edges and form the tree. In this report, we analyze these algorithms in terms of their theoretical foundations, their performance in practice, and their implementation in the context of a project. The analysis is based on a repository, [KruskalAndPrims], which provides Java implementations for both algorithms.
### Project Directory Structure Overview:
Before diving into the core of the analysis, let’s first take a look at the structure of the project. Here is the organization of the files and directories used in the project:

#### **Project Structure:**

```
KruskalAndPrim/
├── .gitignore
├── .idea/
├── results/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/
│   │           └── example/
│   │               ├── algorithms/
│   │               │   ├── Kruskal.java
│   │               │   └── Prim.java
│   │               ├── benchmark/
│   │               │   └── BenchmarkRunner.java
│   │               ├── graph/
│   │               │   ├── Edge.java
│   │               │   ├── Graph.java
│   │               │   └── UnionFind.java
│   │               ├── io/
│   │               │   ├── GraphGenerator.java
│   │               │   ├── GraphLoader.java
│   │               │   └── ResultsWriter.java
│   │               └── model/
│   │                   ├── MSTOutput.java
│   │                   └── MSTResult.java
│   └── resources/
│       └── datasets/
│           ├── benchmark_results.csv
│           ├── graphs_extra_large.json
│           ├── graphs_large.json
│           ├── graphs_medium.json
│           └── graphs_small.json
└── target/
    └── classes/
```
### **Key Project Components:**
**`src/main/java/org/example/algorithms/`:** This directory contains the core algorithms:
    * **Kruskal.java**: Implementation of Kruskal’s algorithm.
    * **Prim.java**: Implementation of Prim’s algorithm.
**`src/main/java/org/example/benchmark/`:** Houses the benchmark utilities to measure performance.
    * **BenchmarkRunner.java**: Runs performance tests and benchmarks the algorithms.
**`src/main/java/org/example/graph/`:** Contains the graph-related classes.
    * **Edge.java**: Defines the edge structure, which holds the weight and the vertices it connects.
    * **Graph.java**: The graph representation used by the algorithms.
    * **UnionFind.java**: Implements the Union-Find data structure used in Kruskal’s algorithm to detect cycles.
**`src/main/java/org/example/io/`:** Responsible for input/output operations.
    * **GraphGenerator.java**: Generates graphs for testing.
    * **GraphLoader.java**: Loads graph datasets.
    * **ResultsWriter.java**: Writes the results of the benchmarks to output files.
**`src/main/java/org/example/model/`:** Defines output data models.
    * **MSTOutput.java**: Represents the output of MST algorithms.
    * **MSTResult.java**: Holds the result details of the MST computation.
**`resources/`:** This folder stores datasets and results.
    * **datasets/**: Contains different graph datasets for testing and benchmarking.
    * **benchmark_results.csv**: Stores the benchmark results for the MST algorithms.

---

## 2**. Theory and Formulas**
### 2.1. **Prim's Algorithm**

Prim’s algorithm is a greedy algorithm that adds edges to an MST, starting from a single vertex and selecting the minimum weight edge that connects to a vertex outside the MST. The time complexity of Prim’s algorithm using a priority queue is:
[
T_{\text{Prim}}(V, E) = O(E \log V)
]

Where:

* ( V ) is the number of vertices in the graph,
* ( E ) is the number of edges.

### 2.2. **Kruskal's Algorithm**

Kruskal’s algorithm is another greedy approach, but it operates on edges rather than vertices. It sorts the edges by weight and adds them one by one to the MST, ensuring no cycles are formed. The time complexity of Kruskal’s algorithm is:

[
T_{\text{Kruskal}}(V, E) = O(E \log E)
]

Where:

* ( V ) is the number of vertices,
* ( E ) is the number of edges.

---

## 2.3. **Time and Space Complexity**

### **Prim's Algorithm**

**Time Complexity:**
The time complexity of Prim’s algorithm depends on the data structure used to store the edges and the priority queue. Assuming a priority queue (min-heap) is used, the time complexity is as follows:

* **Initialization** (building the priority queue) takes ( O(V) ), where ( V ) is the number of vertices.
* **Each edge** is processed once, and in each iteration, we perform an extract-min operation, which takes ( O(\log V) ), and a decrease-key operation, which also takes ( O(\log V) ).

Thus, the total time complexity of Prim's algorithm is:

[
T_{\text{Prim}}(V, E) = O(E \log V)
]

**Space Complexity:**
Prim’s algorithm requires:

* **Graph Representation**: Adjacency list or matrix, which requires ( O(V + E) ) space.
* **Priority Queue**: Storing all vertices in the priority queue, which requires ( O(V) ) space.

Thus, the overall space complexity is:

[
S_{\text{Prim}}(V, E) = O(V + E)
]

### **Kruskal's Algorithm**

**Time Complexity:**
Kruskal’s algorithm involves sorting all edges and using the Union-Find data structure. Here’s the breakdown:

* **Sorting edges**: Sorting the edges takes ( O(E \log E) ), where ( E ) is the number of edges.
* **Union-Find Operations**: For each edge, we perform the union and find operations. These operations are very efficient, with a time complexity of ( O(\alpha(V)) ), where ( \alpha ) is the inverse Ackermann function. This is practically constant for all practical values of ( V ).

Thus, the total time complexity of Kruskal's algorithm is:

[
T_{\text{Kruskal}}(V, E) = O(E \log E)
]

**Space Complexity:**
Kruskal’s algorithm requires:

* **Edge List**: Storing all ( E ) edges, which takes ( O(E) ) space.
* **Union-Find**: The Union-Find data structure requires ( O(V) ) space to store the parent and rank arrays.

Thus, the overall space complexity is:

[
S_{\text{Kruskal}}(V, E) = O(V + E)
]

### **Comparison of Time and Space Complexity:**

| **Algorithm**           | **Time Complexity** | **Space Complexity** |
| ----------------------- | ------------------- | -------------------- |
| **Prim’s Algorithm**    | ( O(E \log V) )     | ( O(V + E) )         |
| **Kruskal’s Algorithm** | ( O(E \log E) )     | ( O(V + E) )         |

---

## 3. Practical Analysis

### 3.1. **Execution Time and Operation Count**

In the practical implementation provided in the repository, various datasets are used to benchmark the algorithms. For example, with a graph containing 82 vertices and 82 edges, the performance results show:

* **Prim’s Algorithm**:

    * Execution Time: 249.9 milliseconds
* **Kruskal’s Algorithm**:

    * Execution Time: 234.8 milliseconds

These results indicate that Kruskal’s algorithm performs slightly faster than Prim’s for this particular graph, though the time complexity for both algorithms depends on the graph density.

### 3.2. **Benchmarking Graphs:**

Graphs used for testing come in several sizes:

* **Graphs_Small.json**: Contains a small graph with a limited number of vertices and edges.
* **Graphs_Medium.json**: A medium-sized graph for more realistic scenarios.
* **Graphs_Large.json**: Larger graphs to test scalability.
* **Graphs_Extra_Large.json**: Very large graphs to simulate real-world large-scale network conditions.

### 3.3. **Benchmark Results**

The following table presents the benchmark results for both algorithms across different test cases. Each row corresponds to a specific graph instance, and the data includes the graph’s characteristics (such as the number of vertices and edges), execution time (in milliseconds), operations count, and the total cost of the Minimum Spanning Tree.

| **GraphID** | **Algorithm** | **Vertices** | **Edges** | **ExecutionTimeMs** | **OperationsCount** | **TotalCost** |
| ----------- | ------------- | ------------ | --------- | ------------------- | ------------------- | ------------- |
| 1           | Kruskal       | 15           | 30        | 0.095607            | 61                  | 59.00         |
| 1           | Prim          | 15           | 30        | 0.244627            | 60                  | 59.00         |
| 2           | Kruskal       | 8            | 16        | 0.038858            | 33                  | 35.00         |
| 2           | Prim          | 8            | 16        | 0.064107            | 32                  | 35.00         |
| 3           | Kruskal       | 20           | 40        | 0.089167            | 84                  | 114.00        |
| 3           | Prim          | 20           | 40        | 0.143346            | 114                 | 115.00        |
| 4           | Kruskal       | 22           | 44        | 0.071091            | 159                 | 157.00        |
| 4           | Prim          | 22           | 44        | 0.027384            | 159                 | 157.00        |
| 5           | Kruskal       | 10           | 20        | 0.027384            | 49                  | 157.00        |
| 5           | Prim          | 10           | 20        | 0.036688            | 41                  | 35.00         |
---

## 4. Results Interpretation

* **For Sparse Graphs**: Kruskal’s algorithm outperforms Prim’s due to its simpler approach to adding edges one by one, while Prim’s algorithm may spend more time selecting edges from the priority queue.

* **For Dense Graphs**: Prim’s algorithm may perform better since it can exploit the dense connectivity between vertices to build the MST more efficiently using a priority queue.

### 4.1. **Memory Consumption**

* **Prim's Algorithm**: Requires ( O(V + E) ) space due to the adjacency matrix or list and priority queue.
* **Kruskal's Algorithm**: Requires ( O(E) ) space for storing edges and the Union-Find data structure.

---

## 5. Conclusions

Based on the analysis, both Prim’s and Kruskal’s algorithms are suitable for finding the Minimum Spanning Tree, but their performance varies based on graph characteristics:

* **Prim’s Algorithm** is more efficient for **dense graphs** with a large number of edges.
* **Kruskal’s Algorithm** is more suitable for **sparse graphs** with fewer edges, as it benefits from sorting edges and using the Union-Find structure to prevent cycles.

---

## 6. Recommendations

* Use **Prim’s Algorithm** when dealing with **dense graphs** and when the graph is represented using adjacency lists or matrices.
* Use **Kruskal’s Algorithm** when working with **sparse graphs** or when a simpler edge list representation is used.

---

## 7. References

1. GeeksforGeeks. (2025). *Difference between Prim's and Kruskal's algorithm for MST*. Retrieved from [GeeksforGeeks](https://www.geeksforgeeks.org/dsa/difference-between-prims-and-kruskals-algorithm-for-mst/)
2. Stack Overflow. (2009). *When should I use Kruskal as opposed to Prim and vice versa?* Retrieved from [Stack Overflow](https://stackoverflow.com/questions/1195872/when-should-i-use-kruskal-as-opposed-to-prim-and-vice-versa)
3. Baeldung. (2024). *Kruskal's vs Prim's Algorithm*. Retrieved from [Baeldung](https://www.baeldung.com/cs/kruskals-vs-prims-algorithm)
4. International Journal of Engineering Research and Technology. (2020). *Comparative Performance Analysis of Kruskal and Prim Algorithms*. Retrieved from [Ripublication](https://www.ripublication.com/irph/ijert20/ijertv13n12_181.pdf)

---