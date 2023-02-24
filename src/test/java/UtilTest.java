

import solver.graph.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilTest {

    public static List<Chaine> DE1 = new ArrayList<>();
    public static List<Pair> DE2 = new ArrayList<>();
    public static List<Pair> DE3 = new ArrayList<>();

    public static Map<String, Variable> graph1;

    public static Map<String, Variable> graph2;
    public static Map<String, Variable> graph3;

    public static Map<String, Variable> graph4;
    public static Map<String, Variable> graph5;





    public static void setUp() {
        Domaine ia=new Domaine("a", "i");
        Domaine ib=new Domaine("b", "i");
        Domaine ic=new Domaine("c",  "i");
        Domaine id=new Domaine("d",  "i");
        Domaine ie=new Domaine("e",  "i");

        Domaine ja=new Domaine("a",  "j");
        Domaine jb=new Domaine("b",  "j");
        Domaine jc=new Domaine("c",  "j");
        Domaine jd=new Domaine("d", "j");
        Domaine je=new Domaine("e",  "j");

        Domaine ka=new Domaine("a",  "k");
        Domaine kb=new Domaine("b",  "k");
        Domaine kc=new Domaine("c",  "k");


        Contrainte iaja=new Contrainte(ia, ja);
        Contrainte iajc=new Contrainte(ia, jc);
        Contrainte ibja=new Contrainte(ib, ja);
        Contrainte ibje=new Contrainte(ib, je);
        Contrainte icjb= new Contrainte(ic, jb);
        Contrainte icjd=new Contrainte(ic, jd);
        Contrainte idjc=new Contrainte(id, jc);
        Contrainte idje=new Contrainte(id, je);
        Contrainte ieje=new Contrainte(ie, je);

        Contrainte jaka=new Contrainte(ja, ka);
        Contrainte jbkb=new Contrainte(jb, kb);
        Contrainte jckc=new Contrainte(jc, kc);

        Variable i=new Variable("i", List.of(iaja, iajc, ibja, ibje, icjb, icjd, idjc, idje, ieje));
        i.addDomaine(ia);
        i.addDomaine(ib);
        i.addDomaine(ic);
        i.addDomaine(id);
        i.addDomaine(ie);

        Variable j=new Variable("i", new ArrayList<>());
        j.addDomaine(ja);
        j.addDomaine(jb);
        j.addDomaine(jc);
        j.addDomaine(jd);
        j.addDomaine(je);

        Variable j2=new Variable("i", List.of(jbkb));
        j2.addDomaine(ja);
        j2.addDomaine(jb);
        j2.addDomaine(jc);
        j2.addDomaine(jd);
        j2.addDomaine(je);

        Variable k=new Variable("k", new ArrayList<>());
        k.addDomaine(ka);
        k.addDomaine(kb);
        k.addDomaine(kc);

        graph1=new HashMap<>();
        graph1.put("i", i);
        graph1.put("j", j);

        graph2=new HashMap<>();
        graph2.put("i", i);
        graph2.put("j", j);

        graph3=new HashMap<>();
        graph3.put("i", i);
        graph3.put("j", j2);
        graph3.put("k", k);


        Domaine Aa=new Domaine("Aa", "A");
        Domaine Ab=new Domaine("Ab", "A");
        Domaine Ba=new Domaine("Ba", "B");
        Domaine Bb=new Domaine("Bb", "B");
        Domaine Ca=new Domaine("Ca", "C");
        Domaine Cb=new Domaine("Cb", "C");


        Contrainte AaBa=new Contrainte(Aa, Ba);
        Contrainte BaCb=new Contrainte(Ba, Cb);
        Contrainte CbAb=new Contrainte(Cb, Ab);
        Contrainte AbBb=new Contrainte(Ab, Bb);
        Contrainte BbCa=new Contrainte(Bb, Ca);
        Contrainte CaAa=new Contrainte(Ca, Aa);

        Variable A=new Variable("A", List.of(AaBa, AbBb));
        A.addDomaine(Aa);
        A.addDomaine(Ab);

        Variable B=new Variable("B", List.of(BaCb, BbCa));
        B.addDomaine(Ba);
        B.addDomaine(Bb);

        Variable C=new Variable("C", List.of(CaAa, CbAb));
        C.addDomaine(Ca);
        C.addDomaine(Cb);

        graph4=new HashMap<>();
        graph4.put("A", A);
        graph4.put("B", B);
        graph4.put("C", C);

        Domaine A1=new Domaine("A1", "A");
        Domaine A2=new Domaine("A2", "A");
        Domaine B1=new Domaine("B1", "B");
        Domaine B2=new Domaine("B2", "B");
        Domaine B3=new Domaine("B3", "B");
        Domaine C1=new Domaine("C1", "C");


        Contrainte A1B1=new Contrainte(A1, B1);
        Contrainte A1B2=new Contrainte(A1, B2);
        Contrainte A1B3=new Contrainte(A1, B3);
        Contrainte B1C1=new Contrainte(B1, C1);
        Contrainte B2C1=new Contrainte(B2, C1);
        Contrainte B3C1=new Contrainte(B3, C1);
        Contrainte C1A2=new Contrainte(C1, A2);

        Variable Abis=new Variable("A", List.of(A1B1, A1B2, A1B3));
        Abis.addDomaine(A1);
        Abis.addDomaine(A2);

        Variable Bbis=new Variable("B", List.of(B1C1, B2C1, B3C1));
        Bbis.addDomaine(B1);
        Bbis.addDomaine(B2);
        Bbis.addDomaine(B3);

        Variable Cbis=new Variable("C", List.of(C1A2));
        Cbis.addDomaine(C1);

        graph5=new HashMap<>();
        graph5.put("A", Abis);
        graph5.put("B", Bbis);
        graph5.put("C", Cbis);


        DE1.add(new Chaine(jc));

        DE2.add(new Pair("i", "ib"));
        DE2.add(new Pair("i", "ic"));
        DE2.add(new Pair("i", "id"));
        DE2.add(new Pair("i", "ie"));
        DE2.add(new Pair("j", "je"));
        DE2.add(new Pair("j", "jb"));
        DE2.add(new Pair("j", "jd"));

        DE3.add(new Pair("j", "je"));

    }

}
