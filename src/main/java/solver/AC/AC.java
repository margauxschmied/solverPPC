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

    boolean filtre(List<Chaine> DE);

    Map<String, Map<String, Chaine>> getSupport();

    void backtrack(int pop, List<Chaine> de);

    boolean validChoice(Stack<Chaine> peek, Domaine domaine, List<Chaine> DE);
}
