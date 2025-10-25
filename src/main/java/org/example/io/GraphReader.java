package org.example.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.example.graph.Graph;
public class GraphReader {

    public static Graph readGraphFromJson(String filename) {
        try {

            ObjectMapper objectMapper = new ObjectMapper();

            GraphData graphData = objectMapper.readValue(new File(filename), GraphData.class);

            Graph graph = new Graph(graphData.getNumVertices());

            for (EdgeData edge : graphData.getEdges()) {
                graph.addEdge(edge.getVertex1(), edge.getVertex2(), edge.getWeight());
            }

            return graph;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class GraphData {
    private int numVertices;
    private List<EdgeData> edges;

    public int getNumVertices() {
        return numVertices;
    }

    public List<EdgeData> getEdges() {
        return edges;
    }
}

class EdgeData {
    private int vertex1;
    private int vertex2;
    private int weight;

    public int getVertex1() {
        return vertex1;
    }

    public int getVertex2() {
        return vertex2;
    }

    public int getWeight() {
        return weight;
    }
}