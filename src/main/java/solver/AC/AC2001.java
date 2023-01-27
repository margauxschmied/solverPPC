package solver.AC;


import solver.graph.Pair;
import solver.graph.Variable;

import java.util.*;

public class AC2001 implements AC{
    private Map<String, Variable> graph;
    private Map<String, Map<String, List<Pair>>> support;

    @Override
    public boolean init(Map<String, Variable> graph) {
        this.graph=graph;
        this.support=new HashMap();

        for (Map.Entry<String, Variable> variable : graph.entrySet()) {

            support.put(variable.getKey(), new HashMap<>());
            for(int j=0; j<variable.getValue().getDomaine().size(); j++){
                support.get(variable.getKey()).computeIfAbsent(variable.getValue().getDomaine().get(j).getVal(), k1 -> new ArrayList<>());
                for(int k=0; k<variable.getValue().getContraintes().size(); k++) {
                    if (variable.getValue().getDomaine().get(j).getVariable().equals(variable.getValue().getContraintes().get(k).getD1().getVariable())
                            && variable.getValue().getDomaine().get(j).getVal().equals(variable.getValue().getContraintes().get(k).getD1().getVal())) {
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
        for (Pair value : DE) {
            for (Map.Entry<String, Variable> variable : graph.entrySet()) {
                for(int i=0; i<graph.get(variable.getKey()).getDomaine().size(); i++) {
                    if(!support.get(variable.getKey()).isEmpty()  //TODO: a refaire
                        &&!support.get(variable.getKey()).get(graph.get(variable.getKey()).getDomaine().get(i).getVal()).isEmpty()
                        && support.get(variable.getKey()).get(graph.get(variable.getKey()).getDomaine().get(i).getVal()).get(0).getVariable().equals(value.getVariable())
                        && support.get(variable.getKey()).get(graph.get(variable.getKey()).getDomaine().get(i).getVal()).get(0).getDomaine().equals(value.getDomaine())){

                        support.get(variable.getKey()).put(graph.get(variable.getKey()).getDomaine().get(i).getVal(), new ArrayList<>());

                        int found = -1;

                        for (int c = 0; c < variable.getValue().getContraintes().size(); c++) {
                            if (variable.getValue().getContraintes().get(c).getD1().getVariable().equals(variable.getKey())
                                && variable.getValue().getContraintes().get(c).getD1().getVal().equals(graph.get(variable.getKey()).getDomaine().get(i).getVal())
                                && variable.getValue().getContraintes().get(c).getD2().getVariable().equals(value.getVariable())
                                && variable.getValue().getContraintes().get(c).getD2().getVal().equals(value.getDomaine())){
                                found=0;
                            }else if (found == 0
                                && variable.getValue().getContraintes().get(c).getD1().getVariable().equals(variable.getKey())
                                && variable.getValue().getContraintes().get(c).getD1().getVal().equals(graph.get(variable.getKey()).getDomaine().get(i).getVal())) {
                            support.get(variable.getKey()).get(variable.getValue().getContraintes().get(c).getD1().getVal()).add(new Pair(variable.getValue().getContraintes().get(c).getD2().getVariable(), variable.getValue().getContraintes().get(c).getD2().getVal()));
                            found=1;
                            break;
                            }
                        }
                    }
                }
                if (!variable.getValue().getContraintes().isEmpty()) {
                    boolean b=false;
                    for(int i=0; i<graph.get(variable.getKey()).getDomaine().size(); i++) {
                        if(!support.get(variable.getKey()).get(graph.get(variable.getKey()).getDomaine().get(i).getVal()).isEmpty()){
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
    public void backtrack(int pop, List<Pair> de) {
        for(int n=0; n<pop; n++){
            for (Map.Entry<String, Variable> variable : graph.entrySet()) {
                for(int i=0; i<graph.get(variable.getKey()).getDomaine().size(); i++) {
                    for (int c = 0; c <variable.getValue().getContraintes().size(); c++) {
                        if(variable.getValue().getContraintes().get(c).getD1().getVariable().equals(graph.get(variable.getKey()).getDomaine().get(i).getVariable())
                                && variable.getValue().getContraintes().get(c).getD1().getVal().equals(graph.get(variable.getKey()).getDomaine().get(i).getVal())
                                && variable.getValue().getContraintes().get(c).getD2().getVariable().equals(de.get(de.size()-1).getVariable())
                                && variable.getValue().getContraintes().get(c).getD2().getVal().equals(de.get(de.size()-1).getDomaine())){
                            support.get(variable.getKey()).put(graph.get(variable.getKey()).getDomaine().get(i).getVal(), List.of(new Pair(de.get(de.size()-1).getVariable(), de.get(de.size()-1).getDomaine())));
                        }
                    }
//                    System.out.println("zzzz");
//                    if(!support.get(variable.getKey()).isEmpty() //TODO : probleme
//                            &&!support.get(variable.getKey()).get(graph.get(variable.getKey()).getDomaine().get(i).getVal()).isEmpty()) {
//                        System.out.println(support.get(variable.getKey()).get(graph.get(variable.getKey()).getDomaine().get(i).getVal()).get(0).getVariable().equals(de.get(de.size() - 1).getVariable()));
//                        System.out.println(support.get(variable.getKey()).get(graph.get(variable.getKey()).getDomaine().get(i).getVal()).get(0).getDomaine().equals(de.get(de.size() - 1).getDomaine()));
//                    }
//                    if(!support.get(variable.getKey()).isEmpty() //TODO : probleme
//                            &&!support.get(variable.getKey()).get(graph.get(variable.getKey()).getDomaine().get(i).getVal()).isEmpty()
//                            && support.get(variable.getKey()).get(graph.get(variable.getKey()).getDomaine().get(i).getVal()).get(0).getVariable().equals(de.get(de.size()-1).getVariable())
//                            && support.get(variable.getKey()).get(graph.get(variable.getKey()).getDomaine().get(i).getVal()).get(0).getDomaine().equals(de.get(de.size()-1).getDomaine())){
//                        support.get(variable.getKey()).get(graph.get(variable.getKey()).getDomaine().get(i).getVal()).remove(0);
//                        System.out.println("dedans");
//                        int found = -1;
//
//                        for (int c = variable.getValue().getContraintes().size()-1; c >-1; c--) {
//                            if (variable.getValue().getContraintes().get(c).getD1().getVar().equals(variable.getKey())
//                                    && variable.getValue().getContraintes().get(c).getD1().getVal().equals(graph.get(variable.getKey()).getDomaine().get(i).getVal())
//                                    && variable.getValue().getContraintes().get(c).getD2().getVar().equals(de.get(de.size()-1).getVariable())
//                                    && variable.getValue().getContraintes().get(c).getD2().getVal().equals(de.get(de.size()-1).getDomaine())){
//                                found=0;
//                                System.out.println("foud");
//                            }else if (found == 0
//                                    && variable.getValue().getContraintes().get(c).getD1().getVar().equals(variable.getKey())
//                                    && variable.getValue().getContraintes().get(c).getD1().getVal().equals(graph.get(variable.getKey()).getDomaine().get(i).getVal())) {
//                                support.get(variable.getKey()).get(variable.getValue().getContraintes().get(c).getD1().getVal()).add(new Pair(variable.getValue().getContraintes().get(c).getD2().getVar(), variable.getValue().getContraintes().get(c).getD2().getVal()));
//                                found=1;
//                                System.out.println("modif");
//                                break;
//                            }
//                        }
//
//                        if (found==-1) {
//
//                        }
//                    }
                }
            }
            de.remove(de.size()-1);
        }
    }

    @Override
    public boolean validChoice(Stack<Pair> peek, String v, String d, List<Pair> DE) {
        return Objects.equals(support.get(peek.peek().getVariable()).get(peek.peek().getDomaine()).get(0).getVariable(), v)
                && Objects.equals(support.get(peek.peek().getVariable()).get(peek.peek().getDomaine()).get(0).getDomaine(), d);
    }

    @Override
    public Map<String, Map<String, List<Pair>>> getSupport() {
        return support;
    }
}
