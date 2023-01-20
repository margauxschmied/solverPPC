import org.AC.AC3;
import org.AC.AC4;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AC4Test {

    private AC4 ac4;


    @BeforeEach
    void setUp() {
        UtilTest.setUp();

        ac4=new AC4();
    }
    @Test
    void ac41() {

        assertTrue(ac4.init(UtilTest.graph1));

        System.out.println((ac4.getVariable()));
        System.out.println((ac4.getDomaine()));

        assertTrue(ac4.filtre(UtilTest.DE1));

        System.out.println((ac4.getVariable()));
        System.out.println((ac4.getDomaine()));

    }
}
