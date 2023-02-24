package solver.AC;


import solver.graph.Chaine;
import solver.graph.Domaine;
import solver.graph.Variable;

import java.util.*;

public class AC2001 implements AC{
    private Map<String, Variable> graph;
    private Map<String, Map<String, Chaine>> support;

    @Override
    public boolean init(Map<String, Variable> graph) {
        this.graph=graph;
        this.support=new HashMap<>();

        for (Map.Entry<String, Variable> variable : graph.entrySet()) {

            support.put(variable.getKey(), new HashMap<>());
            for(int j=0; j<variable.getValue().getDomaine().size(); j++){
                support.get(variable.getKey()).computeIfAbsent(variable.getValue().getDomaine().get(j).getDomaine().getDomaine(), k1 -> new Chaine());
                for(int k=0; k<variable.getValue().getContraintes().size(); k++) {
                    if (variable.getValue().getDomaine().get(j).getDomaine().equals(variable.getValue().getContraintes().get(k).getD1())) {
                        support.get(variable.getKey()).get(variable.getValue().getContraintes().get(k).getD1().getDomaine()).add(variable.getValue().getContraintes().get(k).getD2());
                        break;
                    }
                }
            }
            if(support.get(variable.getKey()).size()==0 && !variable.getValue().getContraintes().isEmpty()){
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean filtre(List<Chaine> DE) {
        for (Chaine value : DE) {
            for (Map.Entry<String, Variable> variable : graph.entrySet()) {
                for(int i=0; i<graph.get(variable.getKey()).getDomaine().size(); i++) {
                    if(!support.get(variable.getKey()).isEmpty()  //TODO: a refaire
                        &&!(support.get(variable.getKey()).get(graph.get(variable.getKey()).getDomaine().get(i).getDomaine().getDomaine()).getDomaine()==null)
                        && support.get(variable.getKey()).get(graph.get(variable.getKey()).getDomaine().get(i).getDomaine().getDomaine()).getDomaine().equals(value.getDomaine())){

                        support.get(variable.getKey()).put(graph.get(variable.getKey()).getDomaine().get(i).getDomaine().getDomaine(), new Chaine());

                        int found = -1;

                        for (int c = 0; c < variable.getValue().getContraintes().size(); c++) {
                            if (found == -1
                                && variable.getValue().getContraintes().get(c).getD1().equals(graph.get(variable.getKey()).getDomaine().get(i).getDomaine())
                                && variable.getValue().getContraintes().get(c).getD2().equals(value.getDomaine())){
                                found=0;
                            }else if (found == 0
                                && variable.getValue().getContraintes().get(c).getD1().equals(graph.get(variable.getKey()).getDomaine().get(i).getDomaine())) {
                                support.get(variable.getKey()).get(variable.getValue().getContraintes().get(c).getD1().getDomaine()).add(variable.getValue().getContraintes().get(c).getD2());
                            found=1;
                            break;
                            }
                        }
                    }
                }
                if (!variable.getValue().getContraintes().isEmpty()) {
                    boolean b=false;
                    for(int i=0; i<graph.get(variable.getKey()).getDomaine().size(); i++) {
                        if(!(support.get(variable.getKey()).get(graph.get(variable.getKey()).getDomaine().get(i).getDomaine().getDomaine()).getDomaine()==null)){
                            b=true;
                            break;
                        }
                    }
                    if(!b){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void backtrack(int pop, List<Chaine> de) {
        for(int n=0; n<pop; n++){
            for (Map.Entry<String, Variable> variable : graph.entrySet()) {
                for(int i=0; i<graph.get(variable.getKey()).getDomaine().size(); i++) {
                    int found=-1;
                    for (int c = 0; c <variable.getValue().getContraintes().size(); c++) {
                        if(found==-1 && variable.getValue().getContraintes().get(c).getD1().equals(graph.get(variable.getKey()).getDomaine().get(i).getDomaine())
                                && variable.getValue().getContraintes().get(c).getD2().equals(de.get(de.size()-1).getDomaine())){
                            Chaine tmp=new Chaine();
                            tmp.add(de.get(de.size()-1).getDomaine());
                            support.get(variable.getKey()).put(graph.get(variable.getKey()).getDomaine().get(i).getDomaine().getDomaine(), tmp);
                            found=0;
                        }

                        else if(found!=-1 && variable.getValue().getContraintes().get(c).getD1().equals(graph.get(variable.getKey()).getDomaine().get(i).getDomaine())
                                && variable.getValue().getContraintes().get(c).getD2().equals(de.get(de.size()-1).getDomaine())
                                && support.get(variable.getKey()).get(graph.get(variable.getKey()).getDomaine().get(i).getDomaine().getDomaine()).equals(de.get(de.size()-1))){
                            support.get(variable.getKey()).put(graph.get(variable.getKey()).getDomaine().get(i).getDomaine().getDomaine(), new Chaine());
                        }
                    }
                }
            }
            de.remove(de.size()-1);
        }
    }

    @Override
    public boolean validChoice(Stack<Chaine> peek, Domaine domaine, List<Chaine> DE) { //TODO: a revoir
        return graph.get(peek.peek().getDomaine().getVariable()).getContraintes().stream().anyMatch(o -> peek.peek().getDomaine().equals(o.getD1())
                && domaine.equals(o.getD2()))
                && DE.stream().noneMatch(o -> domaine.equals(o.getDomaine()));
    }


    @Override
    public Map<String, Map<String, Chaine>> getSupport() {
        return support;
    }
}
