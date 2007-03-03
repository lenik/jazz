package net.bodz.xml.util;

import static net.sf.freejava.test.TestInstance.OK;
import static net.sf.freejava.test.Tests.entry;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import net.bodz.xml.xmod.modcfg.Modcfg;
import net.bodz.xml.xmod.modpdb.Modpdb;
import net.sf.freejava.fp.dump.XMLDumper;
import net.sf.freejava.test.TestInstance;
import net.sf.freejava.util.Pair;
import net.sf.freejava.xml.XMLTools;

import org.junit.Test;

class TPair extends Pair<Class, String> {

    private static final long serialVersionUID = -7288699533418977746L;

    public TPair(Class first, String second) {
        super(first, second);
    }

}

public class JiBXTest {

    public static String  XMLROOT;
    public static boolean DUMP = true;
    static {
        // xml/src/java/test_bin
        URL test_bin = JiBXTest.class.getClassLoader().getResource("");

        File xmlroot0 = new File(test_bin.getFile(), "../../..");
        try {
            XMLROOT = xmlroot0.getCanonicalPath() + "/current";
        } catch (IOException e) {
            XMLROOT = null;
        }
    }

    @Test
    public void test1() throws Exception {
        TestInstance[] tests = new TestInstance[] {

        // entry(OK, new TPair(Modpdb.class, "/xmod/test/pdb_1.xml"),
        // Modpdb.class, "xsd unique check: object name"),

        entry(OK, new TPair(Modpdb.class, "/xmod/test/pdb_2.xml"),
                Modpdb.class, "pdb: victalk"),

        entry(OK, new TPair(Modcfg.class, "/xmod/test/cfg_1.xml"),
                Modcfg.class, "cfg: test1"),

        entry(OK, new TPair(Modcfg.class, "/xmod/test/cfg_2.xml"),
                Modcfg.class, "cfg: test2, with link(rel=base)"),

        };

        for (TestInstance test : tests) {
            TPair in = (TPair) test.in;
            Class objcls = (Class) in.first;
            String xmlpath = XMLROOT + in.second;
            Object obj = JiBX.parse(objcls, xmlpath);
            Class out = obj == null ? null : obj.getClass();
            test.validate(out, System.out);
            if (DUMP && obj != null) {
                System.out.println("--------- DUMP START ----------");
                if (false) {
                    XMLDumper.dump(obj);
                } else {
                    String xml = JiBX.rewrite(obj);
                    System.out.println(XMLTools.format(xml));
                }
                System.out.println("---------  DUMP END  ----------");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("XMLROOT = " + XMLROOT);

        JiBXTest test = new JiBXTest();
        test.test1();
    }

}
