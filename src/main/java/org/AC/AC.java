package org.AC;

import org.graph.Variable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public interface AC {

    boolean init(List<Variable> graph);

    boolean filtre(List<String> DE);
}
