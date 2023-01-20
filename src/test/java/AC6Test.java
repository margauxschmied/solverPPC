import org.AC.AC4;
import org.AC.AC6;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AC6Test {
    private AC6 ac6;


    @BeforeEach
    void setUp() {
        UtilTest.setUp();

        ac6 =new AC6();
    }
    @Test
    void ac61() {

        assertTrue(ac6.init(UtilTest.graph1));

        System.out.println((ac6.getDomaine()));

        assertTrue(ac6.filtre(UtilTest.DE1));

        System.out.println((ac6.getDomaine()));

    }
}
