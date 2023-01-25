package solver.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<String, Variable> variables = new HashMap<>();


    public Graph(Map<String, Variable> variables) {
        this.variables = variables;
    }

    public Map<String, Variable> getVariables() {
        return variables;
    }


    @Override
    public String toString() {
        return "{" +
                "variables=" + variables +
                '}';
    }
}
