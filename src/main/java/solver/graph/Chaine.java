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


//    public void update(Chaine chaine){
//
//        System.out.println(this);
//        System.out.println(chaine);
//
//        if(this.precedent==chaine.precedent){
//            if(precedent!=null)
//                precedent.setSuivant(chaine);
//            precedent=chaine;
//        }
//        else if(suivant !=null){
//            suivant.update(chaine);
//        }
//        else if(domaine.equals(chaine.getPrecedent().getDomaine())){
//            suivant=chaine;
//        }
//        else{
//            System.out.println("errrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
//        }
//    }

//    public void update(Domaine chaine){
//
//        if((precedent==null && chaine.getPrecedent()==null) || (precedent!=null && chaine.getPrecedent()!=null && this.precedent.getDomaine().equals(chaine.getPrecedent()))){
//            Chaine newChaine=new Chaine();
//            newChaine.setDomaine(chaine);
//            if(precedent!=null)
//                precedent.setSuivant(newChaine);
//            newChaine.setSuivant(this);
//            precedent=newChaine;
//        }
//        else if(suivant !=null){
//            suivant.update(chaine);
//        }
//        else if(domaine.equals(chaine.getPrecedent())){
//            Chaine newChaine=new Chaine();
//            newChaine.setDomaine(chaine);
//            newChaine.setPrecedent(this);
//            suivant=newChaine;
//        }
//        else{
//            System.out.println("errrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
//        }
//    }

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

    /*
    * FONCTION DOUBLE_LISTE:INSERER_APRES(LISTE, ELEMENT, NOUVEL_ELEMENT)
   SI LISTE.TETE = NIL ALORS
       LISTE.TETE := NOUVEL_ELEMENT
       LISTE.QUEUE := NOUVEL_ELEMENT
       NOUVEL_ELEMENT.PRECEDENT := NIL
       NOUVEL_ELEMENT.SUIVANT := NIL
   SINON
       NOUVEL_ELEMENT.PRECEDENT := ELEMENT
       NOUVEL_ELEMENT.SUIVANT := ELEMENT.SUIVANT
       SI ELEMENT.SUIVANT != NIL ALORS
         ELEMENT.SUIVANT.PRECEDENT := NOUVEL_ELEMENT
       SINON
         LISTE.QUEUE := NOUVEL_ELEMENT
       FIN SI
       ELEMENT.SUIVANT := NOUVEL_ELEMENT
   FIN SI
FIN FONCTION
    * */


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
