package solver.graph;

import java.util.ArrayList;
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

    public Variable(String name) {
        this.name = name;
        this.domaine = new ArrayList<>();
        this.contraintes=new ArrayList<>();
    }

    public Variable(String name, List<Contrainte> contraintes) {
        this.name = name;
        this.contraintes = contraintes;
        this.domaine = new ArrayList<>();
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

    public void addDomaine(Domaine d){
        if(domaine.size()!=0){
            d.setPrecedent(domaine.get(domaine.size()-1));
            domaine.get(domaine.size()-1).setNext(d);
        }
        domaine.add(d);
    }

    public void addContrainte(Contrainte c){
        contraintes.add(c);
    }

    @Override
    public String toString() {
        return "{" +
                "" + name + '=' + domaine +
                '}';
    }
}
