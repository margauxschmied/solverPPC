package solver.AC;

import solver.graph.Pair;
import solver.graph.Variable;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public interface AC {

    boolean init(Map<String, Variable> graph);

    boolean filtre(List<Pair> DE);

    Map<String, Map<String, List<Pair>>> getSupport();

    void backtrack(int pop, List<Pair> de);

    boolean validChoice(Stack<Pair> peek, String v, String d, List<Pair> DE);
}
