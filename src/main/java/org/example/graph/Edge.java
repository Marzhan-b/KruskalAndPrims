package org.example.graph;
import java.util.Objects;

public class Edge implements Comparable<Edge> {
    private final int vertex1;
    private final int vertex2;
    private final int weight;

    public Edge(int vertex1, int vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public int getVertex1() {
        return vertex1;
    }

    public int getVertex2() {
        return vertex2;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        return vertex1 == edge.vertex1 && vertex2 == edge.vertex2 && weight == edge.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex1, vertex2, weight);
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return String.format("Edge [vertex1=%d, vertex2=%d, weight=%d]", vertex1, vertex2, weight);
    }
}
