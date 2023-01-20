package org.AC;

import org.graph.Edge;
import org.graph.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class AC4 implements AC{
    private List<Variable> graph;
    private Map<String, List<String>> variable;
    private Map<String, List<String>> domaine;

    @Override
    public boolean init(List<Variable> graph) {
        this.graph=graph;
        this.variable=new HashMap<>();
        this.domaine=new HashMap<>();

        for(int i=0; i<graph.size(); i++){
            variable.put(graph.get(i).getName(), new ArrayList<>());
            if(graph.get(i).getDomaine().size()==0){
                return false;
            }
            for(int j=0; j<graph.get(i).getDomaine().size(); j++){
                variable.get(graph.get(i).getName()).add(graph.get(i).getDomaine().get(j));
                domaine.computeIfAbsent(graph.get(i).getDomaine().get(j), k -> new ArrayList<>());
                domaine.get(graph.get(i).getDomaine().get(j)).add(graph.get(i).getName());
            }
        }
        return true;
    }

    @Override
    public boolean filtre(List<String> DE) {
        for (Map.Entry<String, List<String>> entry : variable.entrySet()) {
            for (int j=0; j<entry.getValue().size(); j++){
                if(DE.contains(entry.getValue().get(j))){
                    entry.getValue().remove(j);
                }
            }
            if(entry.getValue().isEmpty()){
                return false;
            }
        }
        return true;
    }

    public Map<String, List<String>> getVariable() {
        return variable;
    }

    public Map<String, List<String>> getDomaine() {
        return domaine;
    }
}
