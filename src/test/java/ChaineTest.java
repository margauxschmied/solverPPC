import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solver.AC.AC3;
import solver.graph.Chaine;
import solver.graph.Domaine;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChaineTest {

    Chaine chaine;
    Domaine Aa;
    Domaine Ab;
    Domaine Ac;


    @BeforeEach
    void setUp() {
        UtilTest.setUp();

        chaine=new Chaine();

        Aa=new Domaine("Aa", "A", 0);
        Ab=new Domaine("Ab", "A", 1);
        Ac=new Domaine("Ac", "A", 2);

        Aa.setNext(Ab);
        Ab.setPrecedent(Aa);
        Ab.setNext(Ac);
        Ac.setPrecedent(Ab);


    }
    @Test
    void chaine() {
        System.out.println(chaine);

        chaine.add(Aa);
        System.out.println(chaine);

        chaine.add(Ab);
        System.out.println(chaine);

        chaine.add(Ac);
        System.out.println(chaine);

        Chaine c=chaine.remove(Ab);
        System.out.println(chaine);
        System.out.println(c);

        chaine.update(c);
        System.out.println(chaine);

        System.out.println("ici");
        c=chaine.remove(Aa);
        System.out.println(chaine);
        System.out.println(c);

        chaine.update(c);
        System.out.println(chaine);

        System.out.println("remove Ac");
        c=chaine.remove(Ac);
        System.out.println(chaine);
        System.out.println(c);

        chaine.update(c);
        System.out.println(chaine);

    }

    @Test
    void chaine2() {
        System.out.println(chaine);

        chaine.add(Aa);
        System.out.println(chaine);

        chaine.add(Ab);
        System.out.println(chaine);

        Chaine c=chaine.remove(Ab);
        System.out.println(chaine);
        System.out.println(c);

        chaine.update(c);
        System.out.println(chaine);
    }

//    @Test
//    void chaine3() {
//        System.out.println(chaine);
//
//        chaine.add(Aa);
//        System.out.println(chaine);
//
//        chaine.add(Ab);
//        System.out.println(chaine);
//
//        Chaine c=chaine.remove(Ab);
//        System.out.println(chaine);
//        System.out.println(c);
//
//        chaine.update(Ab);
//        System.out.println(chaine);
//    }
//
//    @Test
//    void chaine4() {
//        System.out.println(chaine);
//
//        chaine.add(Aa);
//        System.out.println(chaine);
//
//        chaine.add(Ab);
//        System.out.println(chaine);
//
//        chaine.add(Ac);
//        System.out.println(chaine);
//
//        Chaine c=chaine.remove(Ab);
//        System.out.println(chaine);
//        System.out.println(c);
//
//        chaine.update(Ab);
//        System.out.println(chaine);
//
//        System.out.println("ici");
//        c=chaine.remove(Aa);
//        System.out.println(chaine);
//        System.out.println(c);
//
//        chaine.update(Aa);
//        System.out.println(chaine);
//
//    }

}
