package it.unibo.generics.graph;

import it.unibo.generics.graph.api.Graph;
import it.unibo.generics.graph.api.MyList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import it.unibo.generics.graph.api.Edge;

public final class UseGraph<N> implements Graph<N> {
    private Set<N> nodeList;
    private Set<Edge<N>> edgeList;
    private UseGraph() {
        this.nodeList = new HashSet<>();
        this.edgeList = new HashSet<>();
    }

    @Override
    public void addNode(N node) {
        this.nodeList.add(node);
    }

    @Override
    public void addEdge(N source, N target) {
        Edge<N> arch = new Edge<>(source, target);
        this.edgeList.add(arch);
    }

    @Override
    public Set<N> nodeSet() {
        return this.nodeList;
    }

    @Override
    public Set<N> linkedNodes(N node) {
        TreeSet<N> riporto = new TreeSet<>();
        for (Edge<N> arch : this.edgeList) {
            if (arch.getSource() == node){
                riporto.add(arch.getTarget());
            }
        }
        return riporto;
    }

    @Override
    public List<N> getPath(N source, N target) {
        // c, h
        MyList<N> riporto = new MyList<>();
        Set<N> collegati = linkedNodes(source);
        LinkedList<MyList<N>> routes = new LinkedList<>(); 
        if (collegati.contains(target)){
            riporto.add(source);
            riporto.add(target);
        }
        else {
            for (N n : collegati) {
                routes.add((MyList<N>)getPath(n, target));
            }

            riporto.add(source);
            riporto.addAll(routes.get(0));
        }
        return riporto;
    }

    public Set<N> getNodeList(){
        return new HashSet<>(this.nodeList);
    }
    public Set<N> getEdgeList(){
        return new HashSet<>(this.nodeList);
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        /*
         * Test your graph implementation(s) by calling testGraph
         */
        testGraph(new UseGraph<String>());
    }

    private static void testGraph(final Graph<String> graph) {
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addNode("d");
        graph.addNode("e");
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("c", "d");
        graph.addEdge("d", "a");
        graph.addEdge("d", "e");
        graph.addEdge("c", "a");
        graph.addEdge("e", "a");
        /*
         * Should be ["a","b","c","d","e"], in any order
         */
        assertIsAnyOf(graph.nodeSet(), Set.of(splitOnWhiteSpace("a b c d e")));
        /*
         * ["d","a"], in any order
         */
        assertIsAnyOf(graph.linkedNodes("c"), Set.of(splitOnWhiteSpace("a d")));
        /*
         * Either the path b,c,a or b,c,d,e,a
         */
        assertIsAnyOf(
            graph.getPath("b", "a"),
            Arrays.asList(splitOnWhiteSpace("b c a")),
            Arrays.asList(splitOnWhiteSpace("b c d e a"))
        );
    }

    private static void assertIsAnyOf(final Object actual, final Object... valid) {
        for (final var target: Objects.requireNonNull(valid)) {
            if (Objects.equals(target, actual)) {
                System.out.println("OK: " + actual + " matches " + target); // NOPMD
                return;
            }
        }
        throw new AssertionError("None of " + Arrays.asList(valid) + " matches " + actual);
    }

    private static String[] splitOnWhiteSpace(final String target) {
        return target.split("\\s+");
    }   
}
