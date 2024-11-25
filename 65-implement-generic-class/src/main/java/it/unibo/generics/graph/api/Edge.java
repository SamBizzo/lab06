package it.unibo.generics.graph.api;

public class Edge<N>{
    private N source;
    private N target;

    public Edge(N source, N target){
        this.source = source;
        this.target = target;
    }

    public N getSource(){
        return this.source;
    }
    public N getTarget(){
        return this.target;
    }
}
