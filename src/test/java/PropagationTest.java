import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solver.AC.AC3;
import solver.AC.AC4;
import solver.AC.AC6;
import solver.Propagation;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropagationTest {

    private Propagation propagation;


    @BeforeEach
    void setUp() {
        UtilTest.setUp();

    }
    @Test
    void propagationAC3() {
        propagation =new Propagation(new AC3(), UtilTest.graph1);

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
//        propagation.accept(0);
//        propagation.getAc().getSupport();
//        System.out.println(propagation.getResulat());

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
}
