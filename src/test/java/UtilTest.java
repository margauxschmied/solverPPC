

import org.graph.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UtilTest {

    public static List<String> DE1 = new ArrayList<>();

    public static List<Variable> graph1 = new ArrayList<>();




    public static void setUp() {
        graph1.add(new Variable("a", List.of("a", "c")));
        graph1.add(new Variable("b", List.of("a", "e")));
        graph1.add(new Variable("c", List.of("b", "d")));
        graph1.add(new Variable("d", List.of("c", "e")));
        graph1.add(new Variable("e", List.of("e")));




        DE1.add("c");


    }

}
