package solver.AC;

import solver.graph.Contrainte;
import solver.graph.Pair;
import solver.graph.Variable;

import java.util.*;

public class AC6 implements AC{
    private Map<String, Variable> graph;
    private Map<String, Map<String, List<Pair>>> support;

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
                        support.get(variable.getValue().getContraintes().get(k).getD2().getVariable()).computeIfAbsent(variable.getValue().getContraintes().get(k).getD2().getVal(), k1 -> new ArrayList<>());
                        support.get(variable.getValue().getContraintes().get(k).getD2().getVariable()).get(variable.getValue().getContraintes().get(k).getD2().getVal()).add(new Pair(variable.getValue().getContraintes().get(k).getD1().getVariable(), variable.getValue().getContraintes().get(k).getD1().getVal()));
                        found=true;
                    }

                    if(found && variable.getValue().getDomaine().get(j).equals(variable.getValue().getContraintes().get(k).getD1())){
                        support.get(variable.getValue().getContraintes().get(k).getD2().getVariable()).computeIfAbsent(variable.getValue().getContraintes().get(k).getD2().getVal(), k1 -> new ArrayList<>());
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
    public boolean filtre(List<Pair> DE) {
        for (Pair value : DE) {
            List<Pair> listDeSupportAUpdate = support.get(value.getVariable()).get(value.getDomaine());
            if (listDeSupportAUpdate != null) {
                for (Pair pair : listDeSupportAUpdate) {
                    int found = -1;

                    for (int j = 0; j < graph.get(pair.getVariable()).getContraintes().size(); j++) {

                        if (found == -1
                                && graph.get(pair.getVariable()).getContraintes().get(j).getD1().getVariable().equals(pair.getVariable())
                                && graph.get(pair.getVariable()).getContraintes().get(j).getD1().getVal().equals(pair.getDomaine())) {
                            found = 0;
                        }
                        else if(found==0
                                && graph.get(pair.getVariable()).getContraintes().get(j).getD1().getVal().equals(pair.getDomaine())
                                && support.get(graph.get(pair.getVariable()).getContraintes().get(j).getD2().getVariable()).get(graph.get(pair.getVariable()).getContraintes().get(j).getD2().getVal()).stream().noneMatch(o -> pair.getVariable().equals(o.getVariable()) && pair.getDomaine().equals(o.getDomaine()))){
                           support.get(graph.get(pair.getVariable()).getContraintes().get(j).getD2().getVariable()).get(graph.get(pair.getVariable()).getContraintes().get(j).getD2().getVal()).add(new Pair(pair.getVariable(), pair.getDomaine()));
                           found = 1;
                       }

                    }
                    if (found == -1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void backtrack(int pop, List<Pair> de) {
        for(int n=0; n<pop; n++){
            List<Pair> listDeSupportAUpdate = support.get(de.get(de.size()-1).getVariable()).get(de.get(de.size()-1).getDomaine());
            if (listDeSupportAUpdate != null) {
                for (Pair pair : listDeSupportAUpdate) {
                    int found = -1;
                    for (int j = 0; j < graph.get(pair.getVariable()).getContraintes().size(); j++) {

                        if (found == -1
                                && graph.get(pair.getVariable()).getContraintes().get(j).getD1().getVariable().equals(pair.getVariable())
                                && graph.get(pair.getVariable()).getContraintes().get(j).getD1().getVal().equals(pair.getDomaine())) {
                            found = 0;
                        }
                        else if((!graph.get(pair.getVariable()).getContraintes().get(j).getD2().getVariable().equals(de.get(de.size()-1).getVariable())
                                || !graph.get(pair.getVariable()).getContraintes().get(j).getD2().getVal().equals(de.get(de.size()-1).getDomaine()))
                                && graph.get(pair.getVariable()).getContraintes().get(j).getD1().getVariable().equals(pair.getVariable())
                                && graph.get(pair.getVariable()).getContraintes().get(j).getD1().getVal().equals(pair.getDomaine())){
                                //&& support.get(graph.get(pair.getVariable()).getContraintes().get(j).getD2().getVar()).get(graph.get(pair.getVariable()).getContraintes().get(j).getD2().getVal()).stream().noneMatch(o -> pair.getVariable().equals(o.getVariable()) && pair.getDomaine().equals(o.getDomaine()))){
                            support.get(graph.get(pair.getVariable()).getContraintes().get(j).getD2().getVariable()).get(graph.get(pair.getVariable()).getContraintes().get(j).getD2().getVal()).removeIf(o -> pair.getVariable().equals(o.getVariable()) && pair.getDomaine().equals(o.getDomaine()));
                            found = 1;
                        }

                    }
                }
            }
            de.remove(de.size()-1);
        }

    }

    @Override
    public boolean validChoice(Stack<Pair> peek, String v, String d, List<Pair> DE) { //TODO: a revoir
        return graph.get(peek.peek().getVariable()).getContraintes().stream().anyMatch(o -> peek.peek().getDomaine().equals(o.getD1().getVal())
                && v.equals(o.getD2().getVariable()) && d.equals(o.getD2().getVal()));
//        return support.get(v).get(d).stream().anyMatch(o -> peek.peek().getVariable().equals(o.getVariable()) && peek.peek().getDomaine().equals(o.getDomaine()));
    }

    @Override
    public Map<String, Map<String, List<Pair>>> getSupport() {
        return support;
    }
}
