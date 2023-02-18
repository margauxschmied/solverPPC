package solver.AC;

import solver.graph.*;

import java.util.*;

public class AC6 implements AC{
    private Map<String, Variable> graph;
    private Map<String, Map<String, Chaine>> support;

    @Override
    public boolean init(Map<String, Variable> graph) {
        this.graph=graph;
        this.support=new HashMap();

        for (Map.Entry<String, Variable> variable : graph.entrySet()) {
            support.computeIfAbsent(variable.getKey(), k1 -> new HashMap<>());
            for(int j=0; j<variable.getValue().getDomaine().size(); j++){
                boolean found=false;
                for(int k=0; k<variable.getValue().getContraintes().size(); k++){
                    if(!found && variable.getValue().getDomaine().get(j).equals(variable.getValue().getContraintes().get(k).getD1())){
                        support.computeIfAbsent(variable.getValue().getContraintes().get(k).getD2().getVariable(), k1 -> new HashMap<>());
                        support.get(variable.getValue().getContraintes().get(k).getD2().getVariable()).computeIfAbsent(variable.getValue().getContraintes().get(k).getD2().getDomaine(), k1 -> new Chaine());
                        support.get(variable.getValue().getContraintes().get(k).getD2().getVariable()).get(variable.getValue().getContraintes().get(k).getD2().getDomaine()).add(variable.getValue().getContraintes().get(k).getD1());
                        found=true;
                    }

                    if(found && variable.getValue().getDomaine().get(j).equals(variable.getValue().getContraintes().get(k).getD1())){
                        support.computeIfAbsent(variable.getValue().getContraintes().get(k).getD2().getVariable(), k1 -> new HashMap<>());
                        support.get(variable.getValue().getContraintes().get(k).getD2().getVariable()).computeIfAbsent(variable.getValue().getContraintes().get(k).getD2().getDomaine(), k1 -> new Chaine());
                    }
                }
            }
        }
        for (Map.Entry<String, Variable> variable : graph.entrySet()) {
            if (support.get(variable.getKey()).size() == 0) {
                for (Map.Entry<String, Variable> var : graph.entrySet()) {
                    for (Contrainte c : var.getValue().getContraintes()) {
                        if (Objects.equals(c.getD2().getVariable(), variable.getKey())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean filtre(List<Domaine> DE) {
        for (Domaine value : DE) {
            Chaine chaineDeSupportAUpdate = support.get(value.getVariable()).get(value.getDomaine());
            if (chaineDeSupportAUpdate != null) {
                Domaine domaine=chaineDeSupportAUpdate.getDomaine();
                while(domaine!=null){
//                for (Domaine domaine : chaineDeSupportAUpdate) {
                    int found = -1;

                    for (int j = 0; j < graph.get(domaine.getVariable()).getContraintes().size(); j++) {

                        if (found == -1
                                && graph.get(domaine.getVariable()).getContraintes().get(j).getD1().equals(domaine)) {
                            found = 0;
                        }
                        else if(found==0
                                && graph.get(domaine.getVariable()).getContraintes().get(j).getD1().equals(domaine)
                                && !support.get(graph.get(domaine.getVariable()).getContraintes().get(j).getD2().getVariable()).get(graph.get(domaine.getVariable()).getContraintes().get(j).getD2().getDomaine()).contain(domaine)){
                            support.get(graph.get(domaine.getVariable()).getContraintes().get(j).getD2().getVariable()).get(graph.get(domaine.getVariable()).getContraintes().get(j).getD2().getDomaine()).add(domaine);
                           found = 1;
                       }

                    }
                    if (found == -1) {
                        return false;
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

        return true;
    }

    @Override
    public void backtrack(int pop, List<Domaine> de) {
        for(int n=0; n<pop; n++){
            Chaine chaineDeSupportAUpdate = support.get(de.get(de.size()-1).getVariable()).get(de.get(de.size()-1).getDomaine());
            if (chaineDeSupportAUpdate != null) {
                Domaine domaine=chaineDeSupportAUpdate.getDomaine();
                while(domaine!=null){
//                for (Domaine domaine : chaineDeSupportAUpdate) {
                    int found = -1;
                    for (int j = 0; j < graph.get(domaine.getVariable()).getContraintes().size(); j++) {

                        if (found == -1
                                && graph.get(domaine.getVariable()).getContraintes().get(j).getD1().equals(domaine)) {
                            found = 0;
                        }
                        else if((!graph.get(domaine.getVariable()).getContraintes().get(j).getD2().equals(de.get(de.size()-1))
                                && graph.get(domaine.getVariable()).getContraintes().get(j).getD1().equals(domaine))){
                            Chaine c=support.get(graph.get(domaine.getVariable()).getContraintes().get(j).getD2().getVariable()).get(graph.get(domaine.getVariable()).getContraintes().get(j).getD2().getDomaine()).remove(domaine);
                            if(c!= null){
                                if(c.equals(support.get(graph.get(domaine.getVariable()).getContraintes().get(j).getD2().getVariable()).get(graph.get(domaine.getVariable()).getContraintes().get(j).getD2().getDomaine()))) {
                                    if (c.getSuivant() != null) {
                                        support.get(graph.get(domaine.getVariable()).getContraintes().get(j).getD2().getVariable()).put(graph.get(domaine.getVariable()).getContraintes().get(j).getD2().getDomaine(), c.getSuivant());
                                    } else {
                                        support.get(graph.get(domaine.getVariable()).getContraintes().get(j).getD2().getVariable()).put(graph.get(domaine.getVariable()).getContraintes().get(j).getD2().getDomaine(), new Chaine());
                                    }
                                }
                            }

                            found = 1;
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
            }
            de.remove(de.size()-1);
        }

    }

    @Override
    public boolean validChoice(Stack<Domaine> peek, String v, String d, List<Domaine> DE) { //TODO: a revoir
        return graph.get(peek.peek().getVariable()).getContraintes().stream().anyMatch(o -> peek.peek().equals(o.getD1())
                && v.equals(o.getD2().getVariable()) && d.equals(o.getD2().getDomaine()))
                && DE.stream().noneMatch(o -> v.equals(o.getVariable()) && d.equals(o.getDomaine()));
    }

    @Override
    public Map<String, Map<String, Chaine>> getSupport() {
        return support;
    }
}
