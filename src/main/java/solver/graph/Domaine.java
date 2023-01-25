package solver.graph;

public class Domaine {
    private String val;
    private String precedent;
    private String next;
    private String var;

    public Domaine(String val, String precedent, String next, String var) {
        this.val = val;
        this.precedent = precedent;
        this.next = next;
        this.var=var;
    }

    public String getVal() {
        return val;
    }

    public String getPrecedent() {
        return precedent;
    }

    public String getNext() {
        return next;
    }

    @Override
    public String toString() {
        return val;
    }

    public String getVar() {
        return var;
    }
}
