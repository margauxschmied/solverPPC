package solver;

import solver.AC.AC;
import solver.graph.Chaine;
import solver.graph.Domaine;
import solver.graph.Pair;
import solver.graph.Variable;

import java.util.*;

public class Propagation {
    private AC ac;

    private Map<String, Variable> graph;

    private Stack<Integer> marqueur = new Stack<>();
    private Stack<Chaine> resulat = new Stack<>();

    private List<Chaine> DE=new ArrayList<>();

    public Propagation(AC ac, Map<String, Variable> graph) {
        this.ac = ac;
        this.graph = graph;
        ac.init(graph);
    }

    public boolean accept(int v){
        if(v==graph.size()){
            if(graph.get(resulat.peek().getDomaine().getVariable()).getContraintes().isEmpty()){
                return true;
            }
            for(int i=0; i<graph.get(resulat.peek().getDomaine().getVariable()).getContraintes().size(); i++){
                int fi=i;
                if(resulat.peek().getDomaine().equals(graph.get(resulat.peek().getDomaine().getVariable()).getContraintes().get(fi).getD1())){
                    for(int j=0; j<resulat.size(); j++){
                        if (graph.get(resulat.peek().getDomaine().getVariable()).getContraintes().get(fi).getD2().equals(resulat.get(j).getDomaine())){
                            return true;
                        }
                    }

                }
            }
            System.out.println("fin");
            return false;
        }


        for (int d=0; d<((Variable) graph.values().toArray()[v]).getDomaine().size(); d++) {
            if(resulat.isEmpty() || ac.validChoice(resulat, ((Variable) graph.values().toArray()[v]).getDomaine().get(d).getDomaine(), DE)) {
                choose(((Variable) graph.values().toArray()[v]).getDomaine().get(d));
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
        }
        return false;
    }

    private List choose(Chaine d) {
        int nb=0;
        List<Domaine> stay=new ArrayList<>();
        String v=d.getDomaine().getVariable();
        resulat.push(d);
        for(int i=0; i<graph.get(v).getDomaine().size(); i++){
            int fi=i;
            if(!Objects.equals(graph.get(v).getDomaine().get(i).getDomaine(), d.getDomaine())
                && DE.stream().noneMatch(o -> graph.get(v).getDomaine().get(fi).getDomaine().equals(o.getDomaine()))){
                    nb += 1;
                    DE.add(graph.get(v).getDomaine().get(i));
            }

        }

        for(int c=0; c<graph.get(v).getContraintes().size();c++){
            if(graph.get(v).getContraintes().get(c).getD1().equals(d.getDomaine())){
                stay.add(graph.get(v).getContraintes().get(c).getD2());
            }
        }

        for(int c=0; c<graph.get(v).getContraintes().size();c++){
            int fc=c;
            if(stay.stream().noneMatch(o -> graph.get(v).getContraintes().get(fc).getD2().equals(o))
                && DE.stream().noneMatch(o -> graph.get(v).getContraintes().get(fc).getD2().equals(o.getDomaine()))){
                    Domaine domaineASupprime=graph.get(v).getContraintes().get(c).getD2();
                    DE.add(graph.get(domaineASupprime.getVariable()).getChaineByDomaine(domaineASupprime));
                    nb += 1;
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

    public Stack<Chaine> getResulat() {
        return resulat;
    }

    public List<Chaine> getDE() {
        return DE;
    }
}
