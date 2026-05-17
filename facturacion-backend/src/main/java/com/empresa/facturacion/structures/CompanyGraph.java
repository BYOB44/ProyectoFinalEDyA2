package com.empresa.facturacion.structures;

import java.util.*;

public class CompanyGraph {
    private final Map<String, List<String>> adjacencyList = new LinkedHashMap<>();

    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(String from, String to) {
        addVertex(from);
        addVertex(to);
        adjacencyList.get(from).add(to);
    }

    public Map<String, List<String>> getAdjacencyList() {
        return adjacencyList;
    }

    public List<String> shortestPath(String start, String target) {
        if (!adjacencyList.containsKey(start) || !adjacencyList.containsKey(target)) {
            return List.of();
        }

        Queue<String> queue = new LinkedList<>();
        Map<String, String> previous = new HashMap<>();
        Set<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(target)) {
                break;
            }
            for (String neighbor : adjacencyList.getOrDefault(current, List.of())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    previous.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (!visited.contains(target)) {
            return List.of();
        }

        LinkedList<String> path = new LinkedList<>();
        String current = target;
        while (current != null) {
            path.addFirst(current);
            current = previous.get(current);
        }
        return path;
    }

    public static CompanyGraph defaultGraph() {
        CompanyGraph graph = new CompanyGraph();
        graph.addEdge("Admin", "Gestión Humana");
        graph.addEdge("Gestión Humana", "Trabajadores");
        graph.addEdge("Trabajadores", "Facturación");
        graph.addEdge("Facturación", "Tesorería");
        graph.addEdge("Tesorería", "Cola de Pagos");
        graph.addEdge("Cola de Pagos", "Pagos");
        graph.addEdge("Pagos", "Historial");
        graph.addEdge("Admin", "Métricas");
        graph.addEdge("Métricas", "Tesorería");
        return graph;
    }
}
