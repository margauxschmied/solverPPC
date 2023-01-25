package solver.AC;

import solver.graph.Pair;
import solver.graph.Variable;

import java.util.*;

public class AC4 implements AC{
    private Map<String, Variable> graph;
    private Map<String, Map<String, List<Pair>>> support;

    @Override
    public boolean init(Map<String, Variable> graph) {
        this.graph=graph;
        this.support=new HashMap();

        for (Map.Entry<String, Variable> variable : graph.entrySet()) {

            support.computeIfAbsent(variable.getKey(), k1 -> new HashMap<>());

            for(int j=0; j<variable.getValue().getDomaine().size(); j++){
                for(int k=0; k<variable.getValue().getContraintes().size(); k++){
                    if(variable.getValue().getDomaine().get(j).getVar().equals(variable.getValue().getContraintes().get(k).getD1().getVar())
                        && variable.getValue().getDomaine().get(j).getVal().equals(variable.getValue().getContraintes().get(k).getD1().getVal())){
                        support.get(variable.getKey()).computeIfAbsent(variable.getValue().getContraintes().get(k).getD1().getVal(), k1 -> new ArrayList<>());
                        support.get(variable.getKey()).get(variable.getValue().getContraintes().get(k).getD1().getVal()).add(new Pair(variable.getValue().getContraintes().get(k).getD2().getVar(), variable.getValue().getContraintes().get(k).getD2().getVal()));

                        support.computeIfAbsent(variable.getValue().getContraintes().get(k).getD2().getVar(), k1 -> new HashMap<>());
                        support.get(variable.getValue().getContraintes().get(k).getD2().getVar()).computeIfAbsent(variable.getValue().getContraintes().get(k).getD2().getVal(), k1 -> new ArrayList<>());
                        support.get(variable.getValue().getContraintes().get(k).getD2().getVar()).get(variable.getValue().getContraintes().get(k).getD2().getVal()).add(new Pair(variable.getValue().getContraintes().get(k).getD1().getVar(), variable.getValue().getContraintes().get(k).getD1().getVal()));

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

        for(int i=0; i<DE.size(); i++) {
            List<Pair> listDeSupportAUpdate = support.get(DE.get(i).getVariable()).get(DE.get(i).getDomaine());
            if (listDeSupportAUpdate != null) {
                for (Pair pair : listDeSupportAUpdate) {
                    int fi = i;
                    if (support.get(pair.getVariable()).get(pair.getDomaine()) != null) {
                        support.get(pair.getVariable()).get(pair.getDomaine()).removeIf(o -> Objects.equals(o.getVariable(), DE.get(fi).getVariable()) && Objects.equals(o.getDomaine(), DE.get(fi).getDomaine()));
                        if (support.get(pair.getVariable()).get(pair.getDomaine()).isEmpty()) {
                            support.get(pair.getVariable()).remove(pair.getDomaine());
                        }
                    }

                }
            }
        }
        for (Map.Entry<String, Map<String, List<Pair>>> sup : support.entrySet()) {
            if(sup.getValue().isEmpty() && !graph.get(sup.getKey()).getContraintes().isEmpty()){
                return false;
            }
        }


        return true;
    }



    @Override
    public void backtrack(int pop, List<Pair> de) {
        List<Pair> listDeSupportAUpdate=new ArrayList<>();
        for(int n=0; n<pop; n++){
            for (Map.Entry<String, Map<String, List<Pair>>> supVar : support.entrySet()) {
                for (Map.Entry<String, List<Pair>> supDom : supVar.getValue().entrySet()) {
                    for(int i=0; i<supDom.getValue().size(); i++){
                        if(Objects.equals(supDom.getValue().get(i).getVariable(), de.get(de.size() - 1).getVariable())
                                && Objects.equals(supDom.getValue().get(i).getDomaine(), de.get(de.size() - 1).getDomaine())){
                            listDeSupportAUpdate.add(new Pair(supVar.getKey(), supDom.getKey()));
                            support.get(de.get(de.size()-1).getVariable()).computeIfAbsent(de.get(de.size()-1).getDomaine(), k1 -> new ArrayList<>());
                            support.get(de.get(de.size() - 1).getVariable()).get(de.get(de.size() - 1).getDomaine()).add(new Pair(supVar.getKey(), supDom.getKey()));

                        }
                    }
//                    if(supDom.getValue().stream().anyMatch(o -> de.get(de.size()-1).getVariable().equals(o.getVariable()) && de.get(de.size()-1).getDomaine().equals(o.getDomaine()))){
//                        listDeSupportAUpdate.add(new Pair(supVar.getKey(), supDom.getKey()));
//                        support.get(de.get(de.size()-1).getVariable()).computeIfAbsent(de.get(de.size()-1).getDomaine(), k1 -> new ArrayList<>());
//                        if(support.get(de.get(de.size() - 1).getVariable()).get(de.get(de.size() - 1).getDomaine()).stream().noneMatch(o -> supVar.getKey().equals(o.getVariable()) && supDom.getKey().equals(o.getDomaine()))) {
//                            support.get(de.get(de.size() - 1).getVariable()).get(de.get(de.size() - 1).getDomaine()).add(new Pair(supVar.getKey(), supDom.getKey()));
//                        }
//                    }
                }
            }
            de.remove(de.size()-1);
        }

    }

    @Override
    public boolean validChoice(Stack<Pair> peek, String v, String d, List<Pair> DE) {
        if(support.get(v).get(d)==null){
            return false;
        }


        return support.get(v).get(d).stream().anyMatch(o -> peek.peek().getVariable().equals(o.getVariable()) && peek.peek().getDomaine().equals(o.getDomaine()));
    }

    @Override
    public Map<String, Map<String, List<Pair>>> getSupport() {
        return support;
    }

    @Override
    public String toString() {
        return support.toString();
    }
}
