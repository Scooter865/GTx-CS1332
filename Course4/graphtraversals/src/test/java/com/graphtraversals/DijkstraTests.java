package com.graphtraversals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class DijkstraTests {

    Set<Vertex<Character>> testVertices;
    Set<Edge<Character>> testEdges;
    Graph<Character> testGraph;
    Map<Vertex<Character>, Integer> expectedDM;

    @BeforeEach
    public void setup() {
        testVertices = new HashSet<>();
        testEdges = new HashSet<>();
        expectedDM = new HashMap<>();
    }

    @Test
    public void SmallGraphTest() {
        // This makes a 2 vertex undirected graph
        Vertex<Character> A = new Vertex<Character>('A');
        Vertex<Character> B = new Vertex<Character>('B');
        testVertices.add(A);
        testVertices.add(B);
        testEdges.add(new Edge<>(A, B, 5));
        testEdges.add(new Edge<>(B, A, 5));
        testGraph = new Graph<>(testVertices, testEdges);
        expectedDM.put(A, 0);
        expectedDM.put(B, 5);

        // Test BFS starting at A
        assertEquals(expectedDM, GraphAlgorithms.dijkstras(A, testGraph));
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
        H = new Vertex<Character>('H'),
        I = new Vertex<Character>('I');
        
        testVertices.addAll(Arrays.asList(A, B, C, D, E, F, G, H, I));

        Edge<Character>
        AB = new Edge<>(A, B, 4),
        BA = new Edge<>(B, A, 4),
        AH = new Edge<>(A, H, 8),
        HA = new Edge<>(H, A, 8),
        BC = new Edge<>(B, C, 8),
        CB = new Edge<>(C, B, 8),
        BH = new Edge<>(B, H, 11),
        HB = new Edge<>(H, B, 11),
        CD = new Edge<>(C, D, 7),
        DC = new Edge<>(D, C, 7),
        CF = new Edge<>(C, F, 4),
        FC = new Edge<>(F, C, 4),
        CI = new Edge<>(C, I, 2),
        IC = new Edge<>(I, C, 2),
        DE = new Edge<>(D, E, 9),
        ED = new Edge<>(E, D, 9),
        DF = new Edge<>(D, F, 14),
        FD = new Edge<>(F, D, 14),
        EF = new Edge<>(E, F, 10),
        FE = new Edge<>(F, E, 10),
        FG = new Edge<>(F, G, 2),
        GF = new Edge<>(G, F, 2),
        GI = new Edge<>(G, I, 6),
        IG = new Edge<>(I, G, 6),
        GH = new Edge<>(G, H, 1),
        HG = new Edge<>(H, G, 1),
        HI = new Edge<>(H, I, 7),
        IH = new Edge<>(I, H, 7);

        testEdges.addAll(Arrays.asList(AB, BA, AH, HA, BC, CB, BH, HB, CD, DC, CF, FC, CI, IC, DE, ED, DF, FD, EF, FE, FG, GF, GI, IG, GH, HG, HI, IH));
        testGraph = new Graph<>(testVertices, testEdges);

        expectedDM.put(A, 0);
        expectedDM.put(B, 4);
        expectedDM.put(C, 12);
        expectedDM.put(D, 19);
        expectedDM.put(E, 21);
        expectedDM.put(F, 11);
        expectedDM.put(G, 9);
        expectedDM.put(H, 8);
        expectedDM.put(I, 14);

        assertEquals(expectedDM, GraphAlgorithms.dijkstras(A, testGraph));
    }

    @Test
    public void DisconnectedTest() {
        Vertex<Character>
        A = new Vertex<Character>('A'), 
        B = new Vertex<Character>('B'), 
        C = new Vertex<Character>('C'),
        D = new Vertex<Character>('D'),
        E = new Vertex<Character>('E'),
        F = new Vertex<Character>('F'),
        G = new Vertex<Character>('G'),
        H = new Vertex<Character>('H'),
        I = new Vertex<Character>('I');
        
        testVertices.addAll(Arrays.asList(A, B, C, D, E, F, G, H, I));

        Edge<Character>
        AB = new Edge<>(A, B, 4),
        BA = new Edge<>(B, A, 4),
        AH = new Edge<>(A, H, 8),
        HA = new Edge<>(H, A, 8),
        BC = new Edge<>(B, C, 8),
        CB = new Edge<>(C, B, 8),
        BH = new Edge<>(B, H, 11),
        HB = new Edge<>(H, B, 11),
        CD = new Edge<>(C, D, 7),
        DC = new Edge<>(D, C, 7),
        CF = new Edge<>(C, F, 4),
        FC = new Edge<>(F, C, 4),
        CI = new Edge<>(C, I, 2),
        IC = new Edge<>(I, C, 2),
        //DE = new Edge<>(D, E, 9),
        //ED = new Edge<>(E, D, 9),
        DF = new Edge<>(D, F, 14),
        FD = new Edge<>(F, D, 14),
        //EF = new Edge<>(E, F, 10),
        //FE = new Edge<>(F, E, 10),
        FG = new Edge<>(F, G, 2),
        GF = new Edge<>(G, F, 2),
        GI = new Edge<>(G, I, 6),
        IG = new Edge<>(I, G, 6),
        GH = new Edge<>(G, H, 1),
        HG = new Edge<>(H, G, 1),
        HI = new Edge<>(H, I, 7),
        IH = new Edge<>(I, H, 7);

        testEdges.addAll(Arrays.asList(AB, BA, AH, HA, BC, CB, BH, HB, CD, DC, CF, FC, CI, IC, /*DE, ED,*/ DF, FD, /*EF, FE,*/ FG, GF, GI, IG, GH, HG, HI, IH));
        testGraph = new Graph<>(testVertices, testEdges);
        
        expectedDM.put(A, 0);
        expectedDM.put(B, 4);
        expectedDM.put(C, 12);
        expectedDM.put(D, 19);
        expectedDM.put(E, Integer.MAX_VALUE);
        expectedDM.put(F, 11);
        expectedDM.put(G, 9);
        expectedDM.put(H, 8);
        expectedDM.put(I, 14);

        assertEquals(expectedDM, GraphAlgorithms.dijkstras(A, testGraph));
    }
}