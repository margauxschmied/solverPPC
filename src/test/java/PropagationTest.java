import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solver.AC.AC2001;
import solver.AC.AC3;
import solver.AC.AC4;
import solver.AC.AC6;
import solver.Parser;
import solver.Propagation;
import solver.graph.Variable;
import solver.time.Stopwatch;

import java.io.FileNotFoundException;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropagationTest {

    private Propagation propagation;

    private Stopwatch stopwatch = new Stopwatch();


    @BeforeEach
    void setUp() {
        UtilTest.setUp();

    }
    @Test
    void propagationAC3() {
        propagation =new Propagation(new AC3(), UtilTest.graph1);
        System.out.println(propagation.getAc().getSupport());

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());

    }


    @Test
    void propagationAC32() {
        propagation =new Propagation(new AC3(), UtilTest.graph2);

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());
    }

    @Test
    void propagationAC33() {
        propagation =new Propagation(new AC3(), UtilTest.graph3);

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());
    }

    @Test
    void propagationAC34() {
        propagation =new Propagation(new AC3(), UtilTest.graph4);
        System.out.println(propagation.getAc().getSupport());

        assertFalse(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());
    }

    @Test
    void propagationAC35() throws FileNotFoundException {
        Parser parser =new Parser();

        Map<String, Variable> graph=parser.parse("/Users/margauxschmied/Documents/master/M2/solverPPC/data/test.txt");
        System.out.println(graph);
        propagation =new Propagation(new AC3(), graph);
        System.out.println(propagation.getAc().getSupport());

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());

    }

    @Test
    void propagationAC36() {
        propagation =new Propagation(new AC3(), UtilTest.graph5);
        System.out.println(propagation.getAc().getSupport());

        assertFalse(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());

    }


    @Test
    void propagationAC4() {
        propagation =new Propagation(new AC4(), UtilTest.graph1);

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());
    }

    @Test
    void propagationAC42() {
        propagation =new Propagation(new AC4(), UtilTest.graph2);

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());
    }

    @Test
    void propagationAC43() {
        propagation =new Propagation(new AC4(), UtilTest.graph3);
        System.out.println(propagation.getAc().getSupport());

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());

    }

    @Test
    void propagationAC44() {
        propagation =new Propagation(new AC4(), UtilTest.graph4);
        System.out.println(propagation.getAc().getSupport());

        assertFalse(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());
        System.out.println(propagation.getDE());

    }

    @Test
    void propagationAC45() throws FileNotFoundException {
        Parser parser =new Parser();

        Map<String, Variable> graph=parser.parse("/Users/margauxschmied/Documents/master/M2/solverPPC/data/test.txt");

        propagation =new Propagation(new AC4(), graph);
        System.out.println(propagation.getAc().getSupport());

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());

    }

    @Test
    void propagationAC46() {
        propagation =new Propagation(new AC4(), UtilTest.graph5);
        System.out.println(propagation.getAc().getSupport());

        assertFalse(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());
        System.out.println(propagation.getDE());
    }

    @Test
    void propagationAC6() {
        propagation =new Propagation(new AC6(), UtilTest.graph1);
        System.out.println(propagation.getAc().getSupport());

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());

    }


    @Test
    void propagationAC62() {
        propagation =new Propagation(new AC6(), UtilTest.graph2);

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());
    }

    @Test
    void propagationAC63() {
        propagation =new Propagation(new AC6(), UtilTest.graph3);

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());
    }

    @Test
    void propagationAC64() {
        propagation =new Propagation(new AC6(), UtilTest.graph4);
        System.out.println(propagation.getAc().getSupport());

        assertFalse(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());
    }

    @Test
    void propagationAC65() throws FileNotFoundException {
        Parser parser =new Parser();

        Map<String, Variable> graph=parser.parse("/Users/margauxschmied/Documents/master/M2/solverPPC/data/test.txt");

        propagation =new Propagation(new AC6(), graph);
        System.out.println(propagation.getAc().getSupport());

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());

    }

    @Test
    void propagationAC66() {
        propagation =new Propagation(new AC6(), UtilTest.graph5);
        System.out.println(propagation.getAc().getSupport());

        assertFalse(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());
    }

    @Test
    void propagationAC2001() {
        propagation =new Propagation(new AC2001(), UtilTest.graph1);
        System.out.println(propagation.getAc().getSupport());

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());

    }

    @Test
    void propagationAC20012() {
        propagation =new Propagation(new AC2001(), UtilTest.graph2);

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());
    }

    @Test
    void propagationAC20013() {
        propagation =new Propagation(new AC2001(), UtilTest.graph3);
        System.out.println(propagation.getAc().getSupport());

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());
    }

    @Test
    void propagationAC20014() {
        propagation =new Propagation(new AC2001(), UtilTest.graph4);
        System.out.println(propagation.getAc().getSupport());

        assertFalse(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());
    }

    @Test
    void propagationAC20015() throws FileNotFoundException {
        Parser parser =new Parser();

        Map<String, Variable> graph=parser.parse("/Users/margauxschmied/Documents/master/M2/solverPPC/data/test.txt");

        propagation =new Propagation(new AC2001(), graph);
        System.out.println(propagation.getAc().getSupport());

        assertTrue(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());

    }

    @Test
    void propagationAC20016() {
        propagation =new Propagation(new AC2001(), UtilTest.graph5);
        System.out.println(propagation.getAc().getSupport());

        assertFalse(propagation.accept(0));

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());
    }


    @Test
    void propagationAC37() throws FileNotFoundException {
        Parser parser =new Parser();

        Map<String, Variable> graph=parser.parse("/Users/margauxschmied/Documents/master/M2/solverPPC/data/message.txt");
        System.out.println(graph);
        stopwatch.start();
        propagation =new Propagation(new AC3(), graph);

        assertTrue(propagation.accept(0));
        stopwatch.stop();

        System.out.println(stopwatch.lastElapsedNanoSeconds());
        stopwatch.reset();

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());

    }

    @Test
    void propagationAC47() throws FileNotFoundException {
        Parser parser =new Parser();

        Map<String, Variable> graph=parser.parse("/Users/margauxschmied/Documents/master/M2/solverPPC/data/message.txt");
        System.out.println(graph);
        stopwatch.start();
        propagation =new Propagation(new AC4(), graph);

        assertTrue(propagation.accept(0));
        stopwatch.stop();

        System.out.println(stopwatch.lastElapsedNanoSeconds());
        stopwatch.reset();

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());

    }

    @Test
    void propagationAC67() throws FileNotFoundException {
        Parser parser =new Parser();

        Map<String, Variable> graph=parser.parse("/Users/margauxschmied/Documents/master/M2/solverPPC/data/message.txt");
        System.out.println(graph);
        stopwatch.start();
        propagation =new Propagation(new AC6(), graph);

        assertTrue(propagation.accept(0));
        stopwatch.stop();

        System.out.println(stopwatch.lastElapsedNanoSeconds());
        stopwatch.reset();

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());

    }

    @Test
    void propagationAC20017() throws FileNotFoundException {
        Parser parser =new Parser();

        Map<String, Variable> graph=parser.parse("/Users/margauxschmied/Documents/master/M2/solverPPC/data/message.txt");
        System.out.println(graph);
        stopwatch.start();
        propagation =new Propagation(new AC2001(), graph);

        assertTrue(propagation.accept(0));
        stopwatch.stop();

        System.out.println(stopwatch.lastElapsedNanoSeconds());
        stopwatch.reset();

        System.out.println(propagation.getAc().getSupport());
        System.out.println(propagation.getResulat());

    }

}
