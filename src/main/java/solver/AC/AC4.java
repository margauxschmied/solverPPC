package solver.AC;

import solver.graph.Chaine;
import solver.graph.Domaine;
import solver.graph.Variable;

import java.util.*;

public class AC4 implements AC{
    private Map<String, Variable> graph;
    private Map<String, Map<String, Chaine>> support;

    private final Map<String, Map<String, List<Chaine>>> suppr=new HashMap<>();

    @Override
    public boolean init(Map<String, Variable> graph) {
        this.graph=graph;
        this.support=new HashMap<>();

        for (Map.Entry<String, Variable> variable : graph.entrySet()) {

            support.computeIfAbsent(variable.getKey(), k1 -> new HashMap<>());
            for (int k = 0; k < variable.getValue().getContraintes().size(); k++) {
                support.computeIfAbsent(variable.getValue().getContraintes().get(k).getD2().getVariable(), k1 -> new HashMap<>());

                support.get(variable.getValue().getContraintes().get(k).getD1().getVariable()).computeIfAbsent(variable.getValue().getContraintes().get(k).getD1().getDomaine(), k1 -> new Chaine());
                support.get(variable.getValue().getContraintes().get(k).getD2().getVariable()).computeIfAbsent(variable.getValue().getContraintes().get(k).getD2().getDomaine(), k1 -> new Chaine());

                support.get(variable.getValue().getContraintes().get(k).getD1().getVariable()).get(variable.getValue().getContraintes().get(k).getD1().getDomaine()).add(variable.getValue().getContraintes().get(k).getD2());
                support.get(variable.getValue().getContraintes().get(k).getD2().getVariable()).get(variable.getValue().getContraintes().get(k).getD2().getDomaine()).add(variable.getValue().getContraintes().get(k).getD1());

            }

            if (support.get(variable.getKey()).size() == 0 && !variable.getValue().getContraintes().isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean filtre(List<Chaine> DE) {

        for(int i=0; i<DE.size(); i++) {
            Chaine chaineDeSupportAUpdate = support.get(DE.get(i).getDomaine().getVariable()).get(DE.get(i).getDomaine().getDomaine());

            if (chaineDeSupportAUpdate != null) {
                Domaine domaine=chaineDeSupportAUpdate.getDomaine();
                while(domaine!=null){
                    suppr.computeIfAbsent(domaine.getVariable(), k1 -> new HashMap<>());
                    suppr.get(domaine.getVariable()).computeIfAbsent(domaine.getDomaine(), k1 -> new ArrayList<>());

                    Chaine c=support.get(domaine.getVariable()).get(domaine.getDomaine()).remove(DE.get(i));
                    if(c!= null){
                        if(c.equals(support.get(domaine.getVariable()).get(domaine.getDomaine()))) {
                            if (c.getSuivant() != null) {
                                support.get(domaine.getVariable()).put(domaine.getDomaine(), c.getSuivant());
                            } else {
                                support.get(domaine.getVariable()).put(domaine.getDomaine(), new Chaine());
                            }
                        }
                        suppr.get(domaine.getVariable()).get(domaine.getDomaine()).add(c);
                    }

                    chaineDeSupportAUpdate=chaineDeSupportAUpdate.getSuivant();
                    if(chaineDeSupportAUpdate==null){
                        domaine=null;
                    }
                    else{
                        domaine=chaineDeSupportAUpdate.getDomaine();
                    }
                }
            }
        }
        for (Map.Entry<String, Map<String, Chaine>> sup : support.entrySet()) {
            boolean find=false;
            if(!graph.get(sup.getKey()).getContraintes().isEmpty()) {
                for (Map.Entry<String, Chaine> supDom : sup.getValue().entrySet()) {
                    if (!(supDom.getValue().getDomaine()==null)) {
                        find = true;
                        break;
                    }
                }
                if (!find) {
                    return false;
                }
            }
        }

        return true;
    }



    @Override
    public void backtrack(int pop, List<Chaine> de) {
        for(int n=0; n<pop; n++){
            suppr.computeIfAbsent(de.get(de.size()-1).getDomaine().getVariable(), k1 -> new HashMap<>());
            suppr.get(de.get(de.size()-1).getDomaine().getVariable()).computeIfAbsent(de.get(de.size()-1).getDomaine().getDomaine(), k1 -> new ArrayList<>());

            Chaine chaineDeSupportAUpdate=support.get(de.get(de.size()-1).getDomaine().getVariable()).get(de.get(de.size()-1).getDomaine().getDomaine());
            Domaine domaine=chaineDeSupportAUpdate.getDomaine();


            while(domaine!=null){

                Chaine deChaine;

                List<Chaine> listChaine=suppr.get(domaine.getVariable()).get(domaine.getDomaine());

                for(int i=0; i<listChaine.size(); i++){
                    if(listChaine.get(i).getDomaine().equals(de.get(de.size()-1).getDomaine())){
                        deChaine=listChaine.get(i);
                        listChaine.remove(i);

                        support.get(domaine.getVariable()).get(domaine.getDomaine()).update(deChaine);

                        if (support.get(domaine.getVariable()).get(domaine.getDomaine()).getPrecedent() != null) {
                            support.get(domaine.getVariable()).put(domaine.getDomaine(), deChaine);
                        }
                        break;
                    }
                }


                chaineDeSupportAUpdate=chaineDeSupportAUpdate.getSuivant();
                if(chaineDeSupportAUpdate==null){
                    domaine=null;
                }
                else{
                    domaine=chaineDeSupportAUpdate.getDomaine();
                }
            }
            de.remove(de.size()-1);
        }
    }

    @Override
    public boolean validChoice(Stack<Chaine> peek, Domaine domaine, List<Chaine> DE) {
        if(support.get(domaine.getVariable()).get(domaine.getDomaine())==null){
            return false;
        }


        return support.get(domaine.getVariable()).get(domaine.getDomaine()).contain(peek.peek().getDomaine())
                && DE.stream().noneMatch(o -> o.getDomaine().equals(domaine));
    }

    @Override
    public Map<String, Map<String, Chaine>> getSupport() {
        return support;
    }

    @Override
    public String toString() {
        return support.toString();
    }
}
