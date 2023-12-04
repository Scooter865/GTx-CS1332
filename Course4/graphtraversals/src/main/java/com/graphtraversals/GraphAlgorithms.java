package com.graphtraversals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
* Your implementation of various graph traversal algorithms.
*/
public class GraphAlgorithms {
    
    /**
    * Performs a breadth first search (bfs) on the input graph, starting at
    * the parameterized starting vertex.
    *
    * When exploring a vertex, explore in the order of neighbors returned by
    * the adjacency list. Failure to do so may cause you to lose points.
    *
    * You may import/use java.util.Set, java.util.List, java.util.Queue, and
    * any classes that implement the aforementioned interfaces, as long as they
    * are efficient.
    *
    * The only instance of java.util.Map that you should use is the adjacency
    * list from graph. DO NOT create new instances of Map for BFS
    * (storing the adjacency list in a variable is fine).
    *
    * DO NOT modify the structure of the graph. The graph should be unmodified
    * after this method terminates.
    *
    * You may assume that the passed in start vertex and graph will not be null.
    * You may assume that the start vertex exists in the graph.
    *
    * @param <T>   The generic typing of the data.
    * @param start The vertex to begin the bfs on.
    * @param graph The graph to search through.
    * @return List of vertices in visited order.
    */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        Set<Vertex<T>> VS = new HashSet<>();
        Queue<Vertex<T>> Q = new LinkedList<Vertex<T>>();
        List<Vertex<T>> visitedList = new ArrayList<Vertex<T>>();
        
        VS.add(start);
        Q.add(start);
        
        while (!Q.isEmpty()) {
            Vertex<T> v = Q.remove();
            // Add v's unvisited neighbors to the queue
            // Find neighbors with adjacency list - a hash map where key is vertex, value is a list of vertex distances
            List<VertexDistance<T>> neighbors = graph.getAdjList().get(v);
            // Neighbors contains adjacency information for v
            // It's a list of vertex distances so the distance from v to the vertex in the VertexDistance object
            for (VertexDistance<T> w : neighbors) {
                Vertex<T> wVert = w.getVertex();
                if (!VS.contains(wVert)) {
                    VS.add(wVert);
                    Q.add(wVert);
                }
            }
            // Consider a vertex visited after IDing all of its neighbors
            // Could put this line at the beginning of the while loop
            visitedList.add(v);
        }
        return visitedList;
    }
    
    
    /**
    * Performs a depth first search (dfs) on the input graph, starting at
    * the parameterized starting vertex.
    *
    * When exploring a vertex, explore in the order of neighbors returned by
    * the adjacency list. Failure to do so may cause you to lose points.
    *
    * NOTE: This method should be implemented recursively. You may need to
    * create a helper method.
    *
    * You may import/use java.util.Set, java.util.List, and any classes that
    * implement the aforementioned interfaces, as long as they are efficient.
    *
    * The only instance of java.util.Map that you may use is the adjacency list
    * from graph. DO NOT create new instances of Map for DFS
    * (storing the adjacency list in a variable is fine).
    *
    * DO NOT modify the structure of the graph. The graph should be unmodified
    * after this method terminates.
    *
    * You may assume that the passed in start vertex and graph will not be null.
    * You may assume that the start vertex exists in the graph.
    *
    * @param <T>   The generic typing of the data.
    * @param start The vertex to begin the dfs on.
    * @param graph The graph to search through.
    * @return List of vertices in visited order.
    */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        Set<Vertex<T>> VS = new HashSet<>();
        List<Vertex<T>> visitedList = new ArrayList<Vertex<T>>();
        
        rDFS(start, graph, VS, visitedList);
        
        return visitedList;
    }
    
    private static <T> void rDFS(Vertex<T> u, Graph<T> G, Set<Vertex<T>> VS, List<Vertex<T>> visitedList) {
        VS.add(u);
        visitedList.add(u);
        List<VertexDistance<T>> neighbors = G.getAdjList().get(u);
        for (VertexDistance<T> w : neighbors) {
            Vertex<T> neighbor = w.getVertex();
            if (!VS.contains(neighbor)) {
                rDFS(neighbor, G, VS, visitedList);
            }
        }
        return;
    }
    
    
    /**
    * Runs Prim's algorithm on the given graph and returns the Minimum
    * Spanning Tree (MST) in the form of a set of Edges. If the graph is
    * disconnected and therefore no valid MST exists, return null.
    *
    * You may assume that the passed in graph is undirected. In this framework,
    * this means that if (u, v, 3) is in the graph, then the opposite edge
    * (v, u, 3) will also be in the graph, though as a separate Edge object.
    *
    * The returned set of edges should form an undirected graph. This means
    * that every time you add an edge to your return set, you should add the
    * reverse edge to the set as well. This is for testing purposes. This
    * reverse edge does not need to be the one from the graph itself; you can
    * just make a new edge object representing the reverse edge.
    *
    * You may assume that there will only be one valid MST that can be formed.
    *
    * You should NOT allow self-loops or parallel edges in the MST.
    *
    * You may import/use java.util.PriorityQueue, java.util.Set, and any
    * class that implements the aforementioned interface.
    *
    * DO NOT modify the structure of the graph. The graph should be unmodified
    * after this method terminates.
    *
    * The only instance of java.util.Map that you may use is the adjacency
    * list from graph. DO NOT create new instances of Map for this method
    * (storing the adjacency list in a variable is fine).
    *
    * You may assume that the passed in start vertex and graph will not be null.
    * You may assume that the start vertex exists in the graph.
    *
    * @param <T>   The generic typing of the data.
    * @param start The vertex to begin Prims on.
    * @param graph The graph we are applying Prims to.
    * @return The MST of the graph or null if there is no valid MST.
    */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        // Init the necessary structures
        Set<Vertex<T>> VS = new HashSet<>();
        Set<Edge<T>> MST = new HashSet<>();
        PriorityQueue<Edge<T>> PQ = new PriorityQueue<>();
        
        // Enqueue all edges coming out of start
        for (VertexDistance<T> v : graph.getAdjList().get(start)) {
            PQ.add(new Edge<>(start, v.getVertex(), v.getDistance()));
        }
        VS.add(start);
        //Alternatively loop through the edge set looking for edges coming out of start
        
        int gSize = graph.getVertices().size();
        // Loop through vertices until you find the MST or PQ is empty
        while (!PQ.isEmpty() && VS.size() < gSize) {
            // Examine the shortest path. Add it to the MST and visit the destination vertex if not already visited
            Edge<T> curEdge = PQ.remove();
            Vertex<T> curV = curEdge.getV();
            if (!VS.contains(curV)) {
                VS.add(curV);
                MST.add(curEdge);
                MST.add(new Edge<>(curEdge.getV(), curEdge.getU(), curEdge.getWeight()));
                
                // Find all paths out of v to unvisited vertices
                for (Edge<T> edge : graph.getEdges()) {
                    if ((edge.getU() == curV) && (!VS.contains(edge.getV()))) {
                        PQ.add(edge);
                    }
                }
            }
        }

        if (PQ.isEmpty() && VS.size() < gSize) {
            return null;
        }
        else {
            return MST;
        }
    }
}