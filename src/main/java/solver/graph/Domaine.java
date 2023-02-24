package solver.graph;

public class Domaine {
    private String domaine;
//    private Domaine precedent;
//    private Domaine next;
    private String variable;

    public Domaine(String val, String var) {
        this.domaine = val;
        this.variable = var;
    }

    public boolean equals(Domaine d){
        return variable.equals(d.variable) && domaine.equals(d.domaine);
    }

    public String getDomaine() {
        return domaine;
    }


    @Override
    public String toString() {
        return variable+":"+domaine;
    }

    public String getVariable() {
        return variable;
    }
}
