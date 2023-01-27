package solver;

import solver.AC.AC;
import solver.graph.Pair;
import solver.graph.Variable;

import java.util.*;

public class Propagation {
    private AC ac;

    private Map<String, Variable> graph;

    private Stack<Integer> marqueur = new Stack<>();
    private Stack<Pair> resulat = new Stack<>();

    private List<Pair> DE=new ArrayList<>();
    private boolean stop=false;
    private int nb=0;
    public Propagation(AC ac, Map<String, Variable> graph) {
        this.ac = ac;
        this.graph = graph;
        ac.init(graph);
    }

    public boolean accept(int v){
        if(v==graph.size()){
            if(graph.get(resulat.peek().getVariable()).getContraintes().isEmpty()){
                return true;
            }
            for(int i=0; i<graph.get(resulat.peek().getVariable()).getContraintes().size(); i++){
                int fi=i;
                if(resulat.peek().getVariable().equals(graph.get(resulat.peek().getVariable()).getContraintes().get(i).getD1().getVariable())
                && resulat.peek().getDomaine().equals(graph.get(resulat.peek().getVariable()).getContraintes().get(i).getD1().getVal())){
                    for(int j=0; j<resulat.size(); j++){
                        if (graph.get(resulat.peek().getVariable()).getContraintes().get(i).getD2().getVariable().equals(resulat.get(j).getVariable())
                        && graph.get(resulat.peek().getVariable()).getContraintes().get(i).getD2().getVal().equals(resulat.get(j).getDomaine())){
                            return true;
                        }
                    }

                }
            }
            System.out.println("fin");
            return false;
        }


        for (int d=0; d<((Variable) graph.values().toArray()[v]).getDomaine().size(); d++) {

            if(resulat.isEmpty() || ac.validChoice(resulat, (String) graph.keySet().toArray()[v], ((Variable) graph.values().toArray()[v]).getDomaine().get(d).getVal(), DE)) {
                choose((String) graph.keySet().toArray()[v], ((Variable) graph.values().toArray()[v]).getDomaine().get(d).getVal());

                if (ac.filtre(DE)) {
                    if (accept(v + 1)) {
                        return true;
                    } else if(!resulat.isEmpty()){
                        resulat.pop();
                        ac.backtrack(marqueur.pop(), DE);
                    }
                } else {
                    resulat.pop();
                    ac.backtrack(marqueur.pop(), DE);
                }
            }
            else if(d+1==((Variable) graph.values().toArray()[v]).getDomaine().size()){
                return false;
            }
        }

        return false;
    }

    private List choose(String v, String d) {
        nb=0;
        resulat.push(new Pair(v, d));
        for(int i=0; i<graph.get(v).getDomaine().size(); i++){
            if(!Objects.equals(graph.get(v).getDomaine().get(i).getVal(), d)){
                int fi=i;
                if(DE.stream().noneMatch(o -> graph.get(v).getDomaine().get(fi).getVariable().equals(o.getVariable()) && graph.get(v).getDomaine().get(fi).getVal().equals(o.getDomaine()))
                    && resulat.stream().noneMatch(o -> v.equals(o.getVariable()) && graph.get(v).getDomaine().get(fi).getVal().equals(o.getDomaine()))) {
                    nb += 1;
                    DE.add(new Pair(v, graph.get(v).getDomaine().get(i).getVal()));
                }
            }
        }
        List<Pair> stay=new ArrayList<>();
        for(int c=0; c<graph.get(v).getContraintes().size();c++){
            if(Objects.equals(graph.get(v).getContraintes().get(c).getD1().getVariable(), v) && Objects.equals(graph.get(v).getContraintes().get(c).getD1().getVal(), d)){
                stay.add(new Pair(graph.get(v).getContraintes().get(c).getD2().getVariable(), graph.get(v).getContraintes().get(c).getD2().getVal()));
            }
        }

        for(int c=0; c<graph.get(v).getContraintes().size();c++){
            int fc=c;
            if(stay.stream().noneMatch(o -> graph.get(v).getContraintes().get(fc).getD2().getVariable().equals(o.getVariable()) && graph.get(v).getContraintes().get(fc).getD2().getVal().equals(o.getDomaine()))) {
                if(DE.stream().noneMatch(o -> graph.get(v).getContraintes().get(fc).getD2().getVariable().equals(o.getVariable()) && graph.get(v).getContraintes().get(fc).getD2().getVal().equals(o.getDomaine()))
                        && resulat.stream().noneMatch(o -> graph.get(v).getContraintes().get(fc).getD2().getVariable().equals(o.getVariable()) && graph.get(v).getContraintes().get(fc).getD2().getVal().equals(o.getDomaine()))) {
                    DE.add(new Pair(graph.get(v).getContraintes().get(c).getD2().getVariable(), graph.get(v).getContraintes().get(c).getD2().getVal()));
                    nb += 1;
                }
            }
        }

        marqueur.add(nb);
        return DE;
    }


    public Map<String, Variable> getGraph() {
        return graph;
    }

    public AC getAc() {
        return ac;
    }

    public Stack<Pair> getResulat() {
        return resulat;
    }

    public List<Pair> getDE() {
        return DE;
    }
}
