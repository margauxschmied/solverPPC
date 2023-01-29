package solver.AC;

import solver.graph.Chaine;
import solver.graph.Domaine;
import solver.graph.Pair;
import solver.graph.Variable;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public interface AC {

    boolean init(Map<String, Variable> graph);

    boolean filtre(List<Domaine> DE);

    Map<String, Map<String, Chaine>> getSupport();

    void backtrack(int pop, List<Domaine> de);

    boolean validChoice(Stack<Domaine> peek, String v, String d, List<Domaine> DE);
}
