package solver;

import solver.graph.Contrainte;
import solver.graph.Domaine;
import solver.graph.Variable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parser {
    Map<String, Variable> graph;

    public Map<String, Variable> parse(String path) throws FileNotFoundException {
        graph=new HashMap<>();
        File doc =
                new File(path);  //"/Users/margauxschmied/Documents/master/M2/solverPPC/data/test.txt"
        Scanner obj = new Scanner(doc);

        String text;
        while (obj.hasNextLine()) {
            text=obj.nextLine();

            if(text.equals("--")){
                break;
            }

            String[] array=text.split(":");
            graph.put(array[0], new Variable(array[0]));

            String[] domaine=array[1].split(" ");

            for(int i=1; i<domaine.length; i++){
                graph.get(array[0]).addDomaine(new Domaine(domaine[i], array[0]));
            }
        }
        while (obj.hasNextLine()) {
            text=obj.nextLine();

            text=text.substring(0, text.length()-1);
            String[] array=text.split(" ");
            graph.get(array[0]).addContrainte(new Contrainte(new Domaine(array[1], array[0]), new Domaine(array[3], array[2])));
        }

        return graph;
    }

}
