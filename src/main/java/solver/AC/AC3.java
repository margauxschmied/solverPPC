package solver.AC;

import solver.graph.Chaine;
import solver.graph.Domaine;
import solver.graph.Variable;

import java.util.*;

public class AC3 implements AC{
    private Map<String, Variable> graph;
    private Map<String, Map<String, Chaine>> support;


    @Override
    public boolean init(Map<String, Variable> graph) {
        this.graph=graph;
        this.support=new HashMap();

        for (Map.Entry<String, Variable> variable : graph.entrySet()) {

            support.put(variable.getKey(), new HashMap<>());
            for(int j=0; j<variable.getValue().getDomaine().size(); j++){
                for(int k=0; k<variable.getValue().getContraintes().size(); k++) {
                    if (variable.getValue().getDomaine().get(j).getDomaine().equals(variable.getValue().getContraintes().get(k).getD1())){
                        support.get(variable.getKey()).computeIfAbsent(variable.getValue().getDomaine().get(j).getDomaine().getDomaine(), k1 -> new Chaine());
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
        this.support=new HashMap<>();

        for (Map.Entry<String, Variable> variable : graph.entrySet()) {
            support.put(variable.getKey(), new HashMap<>());
            for(int j=0; j<variable.getValue().getDomaine().size(); j++){
                for(int k=0; k<variable.getValue().getContraintes().size(); k++){
                    int kf=k;
                    if(variable.getValue().getDomaine().get(j).getDomaine().equals(variable.getValue().getContraintes().get(k).getD1())
                            && DE.stream().noneMatch(o -> variable.getValue().getContraintes().get(kf).getD1().equals(o.getDomaine()))
                            && DE.stream().noneMatch(o -> variable.getValue().getContraintes().get(kf).getD2().equals(o.getDomaine()))){
                        support.get(variable.getKey()).computeIfAbsent(variable.getValue().getDomaine().get(j).getDomaine().getDomaine(), k1 -> new Chaine());
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
    public void backtrack(int pop, List<Chaine> DE) {
        for(int n=0; n<pop; n++){
            DE.remove(DE.size()-1);
        }
    }

    @Override
    public boolean validChoice(Stack<Chaine> peek, Domaine domaine, List<Chaine> DE) {
        return true;
    }


    @Override
    public Map<String, Map<String, Chaine>> getSupport() {
        return support;
    }

}
