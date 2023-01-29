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

    public Chaine remove(Domaine domaine){
        if (this.domaine!= null && this.domaine.equals(domaine)){
            Chaine tmp=null;
            if(suivant!=null) {
                tmp=suivant;
                suivant.setPrecedent(precedent);
            }
            if(precedent!=null) {
                precedent.setSuivant(tmp);
            }
            return this;
        }
        else{
            if(suivant!=null)
                return suivant.remove(domaine);
        }
        return null;
    }

    public void update(Chaine chaine){

        if(this.precedent==chaine.precedent){
            if(precedent!=null)
                precedent.setSuivant(chaine);
            precedent=chaine;
        }
        else if(suivant !=null){
            suivant.update(chaine);
        }
        else if(domaine.equals(chaine.getPrecedent().getDomaine())){
            suivant=chaine;
        }
        else{
            System.out.println("errrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
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
