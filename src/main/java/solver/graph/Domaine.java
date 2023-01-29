package solver.graph;

public class Domaine {
    private String domaine;
    private Domaine precedent;
    private Domaine next;
    private String variable;

    private int lastIndex;

    public Domaine(String val, Domaine precedent, Domaine next, String var, int lastIndex) {
        this.domaine = val;
        this.precedent = precedent;
        this.next = next;
        this.variable =var;
        this.lastIndex=lastIndex;
    }

    public Domaine(String val, String var, int lastIndex) {
        this.domaine = val;
        this.variable = var;
        this.lastIndex=lastIndex;
    }

    public boolean equals(Domaine d){
        return variable.equals(d.variable) && domaine.equals(d.domaine);
    }

    public String getDomaine() {
        return domaine;
    }

    public Domaine getPrecedent() {
        return precedent;
    }

    public Domaine getNext() {
        return next;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setPrecedent(Domaine precedent) {
        this.precedent = precedent;
    }

    public void setNext(Domaine next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return domaine;
    }

    public String getVariable() {
        return variable;
    }
}
