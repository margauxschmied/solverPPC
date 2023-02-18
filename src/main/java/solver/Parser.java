package solver;

import solver.graph.Contrainte;
import solver.graph.Domaine;
import solver.graph.Variable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parser {
    Map<String, Variable> graph;
    Map<String, Map<String, Integer>> index=new HashMap<>();

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
            index.put(array[0], new HashMap<>());

            String[] domaine=array[1].split(" ");

            int debut= Objects.equals(domaine[0], "")? 1 : 0;


            for(int i=debut; i<domaine.length; i++){
                graph.get(array[0]).addDomaine(new Domaine(domaine[i], array[0], i));
                index.get(array[0]).put(domaine[i], i);
            }
        }
        while (obj.hasNextLine()) {
            text=obj.nextLine();

            text=text.substring(0, text.length()-1);
            String[] array=text.split(" ");
            graph.get(array[0]).addContrainte(new Contrainte(new Domaine(array[1], array[0], index.get(array[0]).get(array[1])), new Domaine(array[3], array[2], index.get(array[2]).get(array[3]))));
        }

        return graph;
    }

}
