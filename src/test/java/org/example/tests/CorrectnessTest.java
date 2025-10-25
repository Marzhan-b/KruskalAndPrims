package org.example.tests;
import org.example.algorithms.Kruskal;
import org.example.algorithms.Prim;
import org.example.graph.Edge;
import org.example.graph.Graph;
import org.example.graph.UnionFind;
import org.example.io.GraphLoader;
import org.example.model.MSTResult;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CorrectnessTest {
    @Test
    public void testMSTCorrectnessForAllGraphs() {
        String[] files = {
                "src/main/resources/datasets/graphs_small.json",
                "src/main/resources/datasets/graphs_medium.json",
                "src/main/resources/datasets/graphs_large.json",
                "src/main/resources/datasets/graphs_extra_large.json"
        };
        for (String file : files) {
            List<Graph> graphs = GraphLoader.loadGraphs(file);
            for (Graph graph : graphs) {
                assertNotNull(graph, "Graph must not be null: " + file);

                MSTResult kruskal = Kruskal.run(graph);
                MSTResult prim = new Prim().computeMST(graph);

                validateMST(graph, kruskal);
                validateMST(graph, prim);

                assertEquals(kruskal.getTotalCost(), prim.getTotalCost(),
                        "MST total cost must be identical for graph " + graph.id());
            }
        }
    }
    private void validateMST(Graph graph, MSTResult mst) {
        List<Edge> edges = mst.getEdges();
        assertEquals(graph.V() - 1, edges.size(), "MST must have V-1 edges");
        UnionFind uf = new UnionFind();
        for (String v : graph.getVertices()) uf.add(v);
        for (Edge e : edges) {
            String r1 = uf.find(e.getFrom());
            String r2 = uf.find(e.getTo());
            assertNotEquals(r1, r2, "MST must be acyclic");
            uf.union(e.getFrom(), e.getTo());
        }
        String root = uf.find(graph.getVertices().get(0));
        for (String v : graph.getVertices()) {
            assertEquals(root, uf.find(v), "MST must connect all vertices");
        }

        assertTrue(mst.getExecutionTimeMs() >= 0, "ExecutionTimeMs must be non-negative");
        assertTrue(mst.getOperationsCount() >= 0, "OperationsCount must be non-negative");
    }
}
