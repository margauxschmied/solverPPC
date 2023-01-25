import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solver.AC.AC6;
import solver.Parser;
import solver.graph.Variable;

import java.io.FileNotFoundException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParseTest {
    private Parser parser;


    @BeforeEach
    void setUp() {
        UtilTest.setUp();

        parser =new Parser();
    }
    @Test
    void ac61() throws FileNotFoundException {

        Map<String, Variable> graph=parser.parse("/Users/margauxschmied/Documents/master/M2/solverPPC/data/test.txt");


    }
}
