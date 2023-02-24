package solver.graph;

public class Chaine {

    private Domaine domaine;
    private Chaine precedent;
    private Chaine suivant;


    public Chaine() {
    }

    public Chaine(Domaine domaine, Chaine precedent) {
        this.domaine = domaine;
        this.precedent = precedent;
    }

    public Chaine(Domaine d) {
        this.domaine=d;
    }

    public void add(Domaine domaine){
        if(this.domaine==null){
            this.domaine=domaine;
        }
        else if(suivant!=null){
            suivant.add(domaine);
        }
        else{
            suivant=new Chaine(domaine, this);
        }
    }

//    public Chaine remove(Chaine elementSupprime){
//        if(elementSupprime.getSuivant()!=null) {
//            elementSupprime.getSuivant().setPrecedent(elementSupprime.getPrecedent());
//        }
//        if(elementSupprime.getPrecedent()!=null) {
//            elementSupprime.getPrecedent().setSuivant(elementSupprime.getSuivant());
//        }
//        return elementSupprime;
//    }

    public Chaine remove(Chaine elementSupprime){
        if(elementSupprime.getSuivant()!=null) {
            elementSupprime.getSuivant().setPrecedent(elementSupprime.getPrecedent());
        }
        if(elementSupprime.getPrecedent()!=null) {
            elementSupprime.getPrecedent().setSuivant(elementSupprime.getSuivant());
        }
        return elementSupprime;
    }

    public void update(Chaine nouvelElement){
        if(domaine==null){
            this.domaine=nouvelElement.getDomaine();
            this.precedent=nouvelElement.getPrecedent();
            this.suivant=nouvelElement.getSuivant();
        }
        else{
            if(nouvelElement.getSuivant()!=null) {
                nouvelElement.getSuivant().setPrecedent(nouvelElement);
            }
            if(nouvelElement.getPrecedent()!=null) {
                nouvelElement.getPrecedent().setSuivant(nouvelElement);
            }
        }
    }


    public boolean contain(Domaine d){

        if(domaine!=null && domaine.equals(d)){
            return true;
        }
        else {
            if(suivant!=null)
                return suivant.contain(d);
            return false;
        }
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    public Chaine getPrecedent() {
        return precedent;
    }

    public void setPrecedent(Chaine precedent) {
        this.precedent = precedent;
    }

    public Chaine getSuivant() {
        return suivant;
    }

    public void setSuivant(Chaine suivant) {
        this.suivant = suivant;
    }

    @Override
    public String toString() {
        return "{" +
                domaine +
                ", " + (suivant!=null? suivant.toString(): null) +
                '}';
    }
}
