package org.example.io;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.graph.Edge;
import org.example.graph.Graph;
import java.io.File;
import java.util.List;
import java.util.Map;

public class GraphLoader {
    public static Graph loadGraph(String path) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> jsonMap = mapper.readValue(new File(path), Map.class);

            List<Map<String, Object>> graphs = (List<Map<String, Object>>) jsonMap.get("graphs");
            if (graphs == null || graphs.isEmpty()) {
                System.err.println("No graphs found in file: " + path);
                return null;
            }
            Map<String, Object> graphData = graphs.get(0);
            String graphId = String.valueOf(graphData.getOrDefault("id", "Graph"));
            List<String> nodes = (List<String>) graphData.get("nodes");
            List<Map<String, Object>> edgesJson = (List<Map<String, Object>>) graphData.get("edges");

            List<Edge> edges = edgesJson.stream()
                    .map(e -> new Edge(
                            (String) e.get("from"),
                            (String) e.get("to"),
                            ((Number) e.get("weight")).intValue()
                    ))
                    .toList();

            return new Graph(graphId, false, nodes, edges);
        } catch (Exception e) {
            System.err.println("Error loading graph from file: " + path);
            e.printStackTrace();
            return null;
        }
    }
}
