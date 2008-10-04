package net.bodz.xml.xmod.modcfg;

import java.util.Map;

import net.bodz.xml.util.JiBX;
import net.bodz.xml.util.JiBXTest;

import org.junit.Test;

public class Tests {

    @Test
    public void test1() throws Exception {
        String xml = JiBXTest.XMLROOT + "/xmod/test/cfg_2.xml";
        Object obj = JiBX.parse(Modcfg.class, xml);
        Modcfg modcfg = (Modcfg) obj;
        Map<String, Object> map = modcfg.eval();
        XMLDumper.dump(map);
        System.out.println(map);
    }

    public static void main(String[] args) throws Exception {
        Tests test = new Tests();
        test.test1();
    }

}
