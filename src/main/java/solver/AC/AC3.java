package solver.AC;

import solver.graph.Pair;
import solver.graph.Variable;

import java.util.*;

public class AC3 implements AC{
    private Map<String, Variable> graph;
    private Map<String, Map<String, List<Pair>>> support;


    @Override
    public boolean init(Map<String, Variable> graph) {
        this.graph=graph;
        this.support=new HashMap();

        for (Map.Entry<String, Variable> variable : graph.entrySet()) {

            support.put(variable.getKey(), new HashMap<>());
            for(int j=0; j<variable.getValue().getDomaine().size(); j++){
                for(int k=0; k<variable.getValue().getContraintes().size(); k++) {
                    if (variable.getValue().getDomaine().get(j).getVariable().equals(variable.getValue().getContraintes().get(k).getD1().getVariable())
                            && variable.getValue().getDomaine().get(j).getVal().equals(variable.getValue().getContraintes().get(k).getD1().getVal())) {
                        support.get(variable.getKey()).computeIfAbsent(variable.getValue().getDomaine().get(j).getVal(), k1 -> new ArrayList<>());

                        support.get(variable.getKey()).get(variable.getValue().getContraintes().get(k).getD1().getVal()).add(new Pair(variable.getValue().getContraintes().get(k).getD2().getVariable(), variable.getValue().getContraintes().get(k).getD2().getVal()));
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
    public boolean filtre(List<Pair> DE) {
        this.support=new HashMap();

        for (Map.Entry<String, Variable> variable : graph.entrySet()) {
            support.put(variable.getKey(), new HashMap<>());
            for(int j=0; j<variable.getValue().getDomaine().size(); j++){
                for(int k=0; k<variable.getValue().getContraintes().size(); k++){
                    int kf=k;
                    if(variable.getValue().getDomaine().get(j).getVariable().equals(variable.getValue().getContraintes().get(k).getD1().getVariable())
                            && variable.getValue().getDomaine().get(j).getVal().equals(variable.getValue().getContraintes().get(k).getD1().getVal())
                            && DE.stream().noneMatch(o -> variable.getValue().getContraintes().get(kf).getD1().getVariable().equals(o.getVariable()) && variable.getValue().getContraintes().get(kf).getD1().getVal().equals(o.getDomaine()))
                            && DE.stream().noneMatch(o -> variable.getValue().getContraintes().get(kf).getD2().getVariable().equals(o.getVariable()) && variable.getValue().getContraintes().get(kf).getD2().getVal().equals(o.getDomaine()))){
                        support.get(variable.getKey()).computeIfAbsent(variable.getValue().getDomaine().get(j).getVal(), k1 -> new ArrayList<>());

                        support.get(variable.getKey()).get(variable.getValue().getContraintes().get(k).getD1().getVal()).add(new Pair(variable.getValue().getContraintes().get(k).getD2().getVariable(), variable.getValue().getContraintes().get(k).getD2().getVal()));
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
    public void backtrack(int pop, List<Pair> de) {
        for(int n=0; n<pop; n++){
            de.remove(de.size()-1);
        }
    }

    @Override
    public boolean validChoice(Stack<Pair> peek, String v, String d, List<Pair> DE) {
        return true;
    }


    @Override
    public Map<String, Map<String, List<Pair>>> getSupport() {
        return support;
    }

}
