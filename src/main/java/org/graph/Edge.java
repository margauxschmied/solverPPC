package org.graph;

public class Edge {

    private String variable;
    private String domaine;

    public Edge(String valeur, String domaine) {
        this.variable = valeur;
        this.domaine = domaine;
    }

    public String getVariable() {
        return variable;
    }

    public String getDomaine() {
        return domaine;
    }

    @Override
    public String toString() {
        return "[" + variable +
                "," + domaine +
                "]";
    }
}
