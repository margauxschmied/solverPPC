package solver.graph;

import java.util.ArrayList;
import java.util.List;

public class Variable {
    private String name;
    private List<Chaine> domaine;
    private List<Contrainte> contraintes;



    public Variable(String name, List<Chaine> domaine, List<Contrainte> contraintes) {
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

    public List<Chaine> getDomaine() {
        return domaine;
    }

    public List<Contrainte> getContraintes() {
        return contraintes;
    }

    public void addDomaine(Domaine d){
        if(domaine.size()!=0){
            Chaine chaine=new Chaine(d, domaine.get(domaine.size()-1));
            domaine.get(domaine.size()-1).setSuivant(chaine);
        }
        Chaine chaine=new Chaine(d);
        domaine.add(chaine);
    }

    public void addContrainte(Contrainte c){
        contraintes.add(c);
    }

    public Chaine getChaineByDomaine(Domaine domaine){
        for (Chaine chaine: this.domaine){
            if(chaine.getDomaine().equals(domaine)){
                return chaine;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "{" +
                "" + name + '=' + domaine +
                '}';
    }
}
