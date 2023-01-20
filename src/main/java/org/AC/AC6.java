package org.AC;

import org.graph.Variable;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class AC6 implements AC{
    //TODO
    private List<Variable> graph;
    private Map<String, List<String>> domaine;

    @Override
    public boolean init(List<Variable> graph) {
        this.graph=graph;
        this.domaine=new HashMap<>();

        for(int i=0; i<graph.size(); i++){
            if(graph.get(i).getDomaine().size()==0){
                return false;
            }
            for(int j=0; j<graph.get(i).getDomaine().size(); j++){
                domaine.computeIfAbsent(graph.get(i).getDomaine().get(j), k -> new ArrayList<>());
            }
            domaine.get(graph.get(i).getDomaine().get(0)).add(graph.get(i).getName());
        }
        return true;
    }

    @Override
    public boolean filtre(List<String> DE) {
        for(String de: DE){
            for(String v:domaine.get(de)) {
                for(int i=0; i<graph.size(); i++){
                    if(Objects.equals(graph.get(i).getName(), v)){
                        if(graph.get(i).getDomaine().isEmpty()){
                            return false;
                        }
                        for(int j=0;j<graph.get(i).getDomaine().size(); j++){
                            if(!DE.contains(graph.get(i).getDomaine().get(j))){
                                domaine.get(graph.get(i).getDomaine().get(j)).add(v);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public Map<String, List<String>> getDomaine() {
        return domaine;
    }
}
