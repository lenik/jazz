package net.bodz.bas.log;

import static org.junit.Assert.assertArrayEquals;
import net.bodz.bas.lang.err.EncodeException;
import net.bodz.bas.xml.XMLs;

import org.junit.Test;

public class EvalTreeTest {

    class JoinConvert implements TreeConvert<String, String> {

        @Override
        public String convert(String parent, String child) {
            if (parent == null)
                return child;
            return parent + child;
        }

    }

    @Test
    public void testConvert() throws EncodeException {
        Object[] tree = {
        //
                "hello", //
                "world", //
                "america", //
                "pond", //
                new Object[] {
                //
                        "big", //
                        new Object[] { "cat", "dog", }, //
                        "sexy", //
                        new Object[] { "fish", "girl", }, //
                }, //
                new Object[] { "small", "baby", }, };
        Object[] expected = {
        //
                "hello", //
                "helloworld", //
                "helloamerica", //
                "hellopond", //
                new Object[] {
                //
                        "hellobig", //
                        new Object[] { "hellobigcat", "hellobigcatdog" }, //
                        "hellobigsexy", // 
                        new Object[] { "hellobigfish", "hellobigfishgirl", }, //
                }, //
                new Object[] { "hellosmall", "hellosmallbaby", }, //
        };
        Object[] converted = (Object[]) EvalTree.convert(tree, new JoinConvert());
        String xml = XMLs.encode(converted);
        System.out.println("Converted: ");
        System.out.println(xml);

        assertArrayEquals(expected, converted);
    }

}
