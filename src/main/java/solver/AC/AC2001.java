//package org.AC;
//
//import org.graph.Variable;
//
//import java.util.*;
//
//public class AC2001 implements AC{
//    //TODO
//    private List<Variable> graph;
//    private Map<String, List<String>> last;
//
//    @Override
//    public boolean init(List<Variable> graph) {
//        this.graph=graph;
//        this.last =new HashMap<>();
//
//        for(int i=0; i<graph.size(); i++){
//            if(graph.get(i).getDomaine().size()==0){
//                return false;
//            }
//            for(int j=0; j<graph.get(i).getDomaine().size(); j++){
//                last.computeIfAbsent(graph.get(i).getDomaine().get(j).getVal(), k -> new ArrayList<>());
//            }
//            last.get(graph.get(i).getDomaine().get(0).getVal()).add(graph.get(i).getName());
//        }
//        return true;
//    }
//
//    @Override
//    public boolean filtre(List<String> DE) {
//        for(String de: DE){
//            for(String v: last.get(de)) {
//                for(int i=0; i<graph.size(); i++){
//                    if(Objects.equals(graph.get(i).getName(), v)){
//                        if(graph.get(i).getDomaine().isEmpty()){
//                            return false;
//                        }
//                        for(int j=0;j<graph.get(i).getDomaine().size(); j++){
//                            if(!DE.contains(graph.get(i).getDomaine().get(j).getVal())){
//                                last.get(graph.get(i).getDomaine().get(j).getVal()).add(v);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return true;
//    }
//
//
//    public Map<String, List<String>> getLast() {
//        return last;
//    }
//}
