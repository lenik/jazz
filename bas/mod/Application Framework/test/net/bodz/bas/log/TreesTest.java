package net.bodz.bas.log;

import org.junit.Test;

public class TreesTest {

    class JoinConvert implements TreeConvert<String, String> {

        @Override
        public String convert(String parent, String child) {
            if (parent == null)
                return child;
            return parent + child;
        }

    }

    @Test
    public void testConvert() {
        Object tree = new Object[] {
                "hello",
                "world",
                "america",
                "pond",
                new Object[] { "big", new Object[] { "cat", "dog", }, "sexy",
                        new Object[] { "fish", "girl", }, },
                new Object[] { "small", "baby", }, };
        Object expand = Trees.convert(tree, new JoinConvert());
        System.out.println(expand);
    }

}
