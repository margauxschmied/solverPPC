package solver.graph;

public class Pair {
    private String variable;
    private String domaine;


    public Pair(String variable, String domaine) {
        this.variable = variable;
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
        return domaine;
    }
}
