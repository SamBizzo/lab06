package it.unibo.generics.graph;

import it.unibo.generics.graph.api.Graph;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 */
public final class UseGraph<N> implements Graph<N> {
    private TreeSet<N> nodeList;
    private TreeSet<N> edgeList;
    private UseGraph() {
        this.nodeList = new TreeSet<>();
        this.edgeList = new TreeSet<>();
    }

    @Override
    public void addNode(N node) {
        this.nodeList.add(node);
    }

    @Override
    public void addEdge(N source, N target) {
        this.edgeList.add(new String({source, target}));
    }

    @Override
    public Set<N> nodeSet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nodeSet'");
    }

    @Override
    public Set<N> linkedNodes(N node) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'linkedNodes'");
    }

    @Override
    public List<N> getPath(N source, N target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPath'");
    }

    public TreeSet<N> getNodeList(){
        return new TreeSet<>(this.nodeList);
    }
    public TreeSet<N> getEdgeList(){
        return new TreeSet<>(this.nodeList);
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
