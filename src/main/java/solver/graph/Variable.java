package solver.graph;

import java.util.List;

public class Variable {
    private String name;
    private List<Domaine> domaine;
    private List<Contrainte> contraintes;

    public Variable(String name, List<Domaine> domaine, List<Contrainte> contraintes) {
        this.name = name;
        this.domaine = domaine;
        this.contraintes=contraintes;
    }

    public String getName() {
        return name;
    }

    public List<Domaine> getDomaine() {
        return domaine;
    }

    public List<Contrainte> getContraintes() {
        return contraintes;
    }

    @Override
    public String toString() {
        return "{" +
                "" + name + '=' + domaine +
                '}';
    }
}
