package com.graphtraversals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DFSTests 
{
    Set<Vertex<Character>> testVertices;
    Set<Edge<Character>> testEdges;
    Graph<Character> testGraph;
    List<Vertex<Character>> answer;

    @BeforeEach
    public void setup() {
        testVertices = new HashSet<>();
        testEdges = new LinkedHashSet<>();
        answer = new ArrayList<>();
    }

    /* Don't think you can do a single vertex graph because the graph's edges can't be null
    @Test
    public void OneVertex() {
        testGraph = new Graph<>(null, null);

        answer.add(new Vertex<Integer>(10));

        assertEquals(answer, GraphAlgorithms.bfs(null, testGraph));
    }
    */

    @Test
    public void SmallGraphTest() {
        // This makes a 2 vertex undirected graph
        Vertex<Character> A = new Vertex<Character>('A');
        Vertex<Character> B = new Vertex<Character>('B');
        testVertices.add(A);
        testVertices.add(B);
        testEdges.add(new Edge<>(A, B, 0));
        testEdges.add(new Edge<>(B, A, 0));
        testGraph = new Graph<>(testVertices, testEdges);

        // Build answer list
        answer.add(A);
        answer.add(B);

        // Test BFS starting at A
        assertEquals(answer, GraphAlgorithms.dfs(A, testGraph));
    }

    @Test
    public void RandomGraphTest() {
        Vertex<Character>
        A = new Vertex<Character>('A'), 
        B = new Vertex<Character>('B'), 
        C = new Vertex<Character>('C'),
        D = new Vertex<Character>('D'),
        E = new Vertex<Character>('E'),
        F = new Vertex<Character>('F'),
        G = new Vertex<Character>('G'),
        H = new Vertex<Character>('H');

        testVertices.addAll(Arrays.asList(A, B, C, D, E, F, G, H));
    
        testEdges.add(new Edge<>(A, B, 0));
        testEdges.add(new Edge<>(B, A, 0));
        testEdges.add(new Edge<>(A, C, 0));
        testEdges.add(new Edge<>(C, A, 0));
        testEdges.add(new Edge<>(A, D, 0));
        testEdges.add(new Edge<>(D, A, 0));
        testEdges.add(new Edge<>(A, E, 0));
        testEdges.add(new Edge<>(E, A, 0));
        testEdges.add(new Edge<>(B, G, 0));
        testEdges.add(new Edge<>(G, B, 0));
        testEdges.add(new Edge<>(D, F, 0));
        testEdges.add(new Edge<>(F, D, 0));
        testEdges.add(new Edge<>(E, G, 0));
        testEdges.add(new Edge<>(G, E, 0));
        testEdges.add(new Edge<>(F, G, 0));
        testEdges.add(new Edge<>(G, F, 0));
        testEdges.add(new Edge<>(G, H, 0));
        testEdges.add(new Edge<>(H, G, 0));

        // Make graph undirected
        Set<Edge<Character>> testEdges2 = new HashSet<>();
        for (Edge<Character> e : testEdges) {
            testEdges2.add(new Edge<>(e.getV(), e.getU(), 0));
        }
        testEdges.addAll(testEdges2);

        testGraph = new Graph<>(testVertices, testEdges);

        // Build answer list for BFS starting at B assuming aplhabetical order for tiebreaker
        // The problem statement says tiebreak based on order in the adjacency list. I think the adjacency list takes things in order from testEdges
        // This is tricky because testEdges/Graph.edges is a set so I don't really control the order of it
        answer.addAll(Arrays.asList(B, A, C, D, F, G, E, H));

        assertEquals(answer, GraphAlgorithms.dfs(B, testGraph));
        
        // Build answer list for BFS starting at G
        answer.clear();
        answer.addAll(Arrays.asList(G, B, A, C, D, F, E, H));

        assertEquals(answer, GraphAlgorithms.dfs(G, testGraph));
    }

    @Test
    public void SelfLoopTest() {
        
    }

    @Test
    public void ParallelEdgesTest() {

    }

    @Test
    public void DisconnectedTest() {

    }
}
