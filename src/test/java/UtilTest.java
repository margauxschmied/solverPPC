

import solver.graph.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilTest {

    public static List<Pair> DE1 = new ArrayList<>();
    public static List<Pair> DE2 = new ArrayList<>();
    public static List<Pair> DE3 = new ArrayList<>();

    public static Map<String, Variable> graph1;

    public static Map<String, Variable> graph2;
    public static Map<String, Variable> graph3;

    public static Map<String, Variable> graph4;




    public static void setUp() {
        Domaine ia=new Domaine("ia", null, "ib", "i");
        Domaine ib=new Domaine("ib", "ia", "ic", "i");
        Domaine ic=new Domaine("ic", "ib", "id", "i");
        Domaine id=new Domaine("id", "ic", "ie", "i");
        Domaine ie=new Domaine("ie", "id", null, "i");

        Domaine ja=new Domaine("ja", null, "jb", "j");
        Domaine jb=new Domaine("jb", "ja", "jc", "j");
        Domaine jc=new Domaine("jc", "jb", "jd", "j");
        Domaine jd=new Domaine("jd", "jc", "je", "j");
        Domaine je=new Domaine("je", "jd", null, "j");

        Domaine ka=new Domaine("ka", null, "kb", "k");
        Domaine kb=new Domaine("kb", "ka", "kc", "k");
        Domaine kc=new Domaine("kc", "kb", null, "k");


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

        Variable i=new Variable("i", List.of(ia, ib, ic, id, ie), List.of(iaja, iajc, ibja, ibje, icjb, icjd, idjc, idje, ieje));
        Variable j=new Variable("i", List.of(ja, jb, jc, jd, je), new ArrayList<>());
        Variable j2=new Variable("i", List.of(ja, jb, jc, jd, je), List.of(jbkb));
        Variable k=new Variable("k", List.of(ka, kb, kc), new ArrayList<>());

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


        Domaine Aa=new Domaine("Aa", null, "Ab", "A");
        Domaine Ab=new Domaine("Ab", "Aa", null, "A");
        Domaine Ba=new Domaine("Ba", null, "Bb", "B");
        Domaine Bb=new Domaine("Bb", "Ba", null, "B");
        Domaine Ca=new Domaine("Ca", null, "Cb", "C");
        Domaine Cb=new Domaine("Cb", "Ca", null, "C");


        Contrainte AaBa=new Contrainte(Aa, Ba);
        Contrainte BaCb=new Contrainte(Ba, Cb);
        Contrainte CbAb=new Contrainte(Cb, Ab);
        Contrainte AbBb=new Contrainte(Ab, Bb);
        Contrainte BbCa=new Contrainte(Bb, Ca);
        Contrainte CaAa=new Contrainte(Ca, Aa);

        Variable A=new Variable("A", List.of(Aa, Ab), List.of(AaBa, AbBb));
        Variable B=new Variable("B", List.of(Ba, Bb), List.of(BaCb, BbCa));
        Variable C=new Variable("C", List.of(Ca, Cb), List.of(CaAa, CbAb));

        graph4=new HashMap<>();
        graph4.put("A", A);
        graph4.put("B", B);
        graph4.put("C", C);


        DE1.add(new Pair("j", "jc"));

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
