package org.example.cli;

import java.util.*;

public class Graph {
    private final Map<Integer, List<Edge>> adjacencyList;
    private final int numVertices;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
        adjacencyList = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    public void addEdge(int vertex1, int vertex2, int weight) {
        Edge edge = new Edge(vertex1, vertex2, weight);
        adjacencyList.get(vertex1).add(edge);
        adjacencyList.get(vertex2).add(edge); // Граф неориентированный
    }

    public List<Edge> getEdges() {
        List<Edge> allEdges = new ArrayList<>();
        for (List<Edge> edges : adjacencyList.values()) {
            allEdges.addAll(edges);
        }
        return allEdges;
    }

    public int getNumVertices() {
        return numVertices;
    }


    public List<Edge> getEdgesFromVertex(int vertex) {
        return adjacencyList.get(vertex);
    }
}
