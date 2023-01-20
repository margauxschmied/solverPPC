package org.graph;

import java.util.List;

public class Variable {
    private String name;
    private List<String> domaine;

    public Variable(String name, List<String> domaine) {
        this.name = name;
        this.domaine = domaine;
    }

    public String getName() {
        return name;
    }

    public List<String> getDomaine() {
        return domaine;
    }


    @Override
    public String toString() {
        return "{" +
                "" + name + '=' + domaine +
                '}';
    }
}
