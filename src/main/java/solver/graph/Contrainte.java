package solver.graph;

public class Contrainte {

    private Domaine d1;
    private Domaine d2;

    public Contrainte(Domaine d1, Domaine d2) {
        this.d1 = d1;
        this.d2 = d2;
    }

    public Domaine getD1() {
        return d1;
    }

    public Domaine getD2() {
        return d2;
    }

    @Override
    public String toString() {
        return "[" + d1 +
                "," + d2 +
                "]";
    }
}
