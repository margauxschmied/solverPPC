package solver;

import solver.AC.AC;
import solver.AC.AC3;
import solver.AC.AC4;
import solver.AC.AC6;
import solver.graph.Variable;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Objects;

public class Main {

    private static AC ac;
    private static String path;
    private static Propagation propagation;
    private static Parser parser;
    public static void main(String[] args) throws FileNotFoundException {

        for(int i=0; i<args.length; i++){
            if(Objects.equals(args[i], "-AC")){
                switch (args[i + 1]) {
                    case "AC3" -> ac = new AC3();
                    case "AC4" -> ac = new AC4();
                    case "AC6" -> ac = new AC6();
                    default -> System.out.println("AC non reconnue");
                }
            }
        }
        for(int i=0; i<args.length; i++) {
            if (Objects.equals(args[i], "-Path")) {
                path=args[i+1];
            }
        }


        parser =new Parser();

        Map<String, Variable> graph=parser.parse(path);

        propagation =new Propagation(ac, graph);


        propagation.accept(0);

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());

    }

}
