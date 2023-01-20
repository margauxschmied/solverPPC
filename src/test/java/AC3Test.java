import org.AC.AC3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AC3Test {
    private AC3 ac3;


    @BeforeEach
    void setUp() {
        UtilTest.setUp();
        ac3=new AC3();

    }
    @Test
    void ac31() {

        assertTrue(ac3.init(UtilTest.graph1));

        System.out.println(UtilTest.graph1);
        System.out.println(ac3.getSupport());

        assertTrue(ac3.filtre(UtilTest.DE1));

        System.out.println(ac3.getSupport());
    }
}
