package solver.graph;

public class Domaine {
    private String val;
    private String precedent;
    private String next;
    private String variable;

    public Domaine(String val, String precedent, String next, String var) {
        this.val = val;
        this.precedent = precedent;
        this.next = next;
        this.variable =var;
    }

    public Domaine(String val, String var) {
        this.val = val;
        this.variable = var;
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

    public void setPrecedent(String precedent) {
        this.precedent = precedent;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return val;
    }

    public String getVariable() {
        return variable;
    }
}
