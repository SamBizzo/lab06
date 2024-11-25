package it.unibo.generics.graph.api;

import java.util.ArrayList;

public class MyList<N> extends ArrayList<N> {
    @Override
    public boolean add(N e) {
        // Non fare nulla se il parametro è null
        if (e == null) {
            return false; // Indica che l'elemento non è stato aggiunto
        }
        return super.add(e); // Comportamento standard per elementi non null
    }

    @Override
    public void add(int index, N element) {
        // Non fare nulla se il parametro è null
        if (element != null) {
            super.add(index, element); // Comportamento standard
        }
        // Altrimenti, ignora senza errori
    }
}

