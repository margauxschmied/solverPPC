package org.AC;

import org.graph.Edge;
import org.graph.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AC3 implements AC{
    private List<Variable> graph;
    private List<Edge> support;


    @Override
    public boolean init(List<Variable> graph) {
        this.graph=graph;
        this.support=new ArrayList<>();
        for(int i=0; i<graph.size(); i++){
            if(graph.get(i).getDomaine().size()!=0) {
                support.add(new Edge(graph.get(i).getName(), graph.get(i).getDomaine().get(0)));
            }
            else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean filtre(List<String> DE) {
        this.support=new ArrayList<>();
        for(int i=0; i<graph.size(); i++){
            if(graph.get(i).getDomaine().size()==0) {
                return false;
            }
            boolean found=false;
            for(int j=0; j<graph.get(i).getDomaine().size(); j++) {
                if(!DE.contains(graph.get(i).getDomaine().get(j))) {
                    found=true;
                    support.add(new Edge(graph.get(i).getName(), graph.get(i).getDomaine().get(j)));
                    break;
                }
            }
            if(!found){
                return false;
            }
        }
        return true;
    }

    public List<Edge> getSupport() {
        return support;
    }
}
