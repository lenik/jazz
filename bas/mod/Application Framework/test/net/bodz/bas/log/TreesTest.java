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
                "hello", //$NON-NLS-1$
                "world", //$NON-NLS-1$
                "america", //$NON-NLS-1$
                "pond", //$NON-NLS-1$
                new Object[] { "big", new Object[] { "cat", "dog", }, "sexy", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                        new Object[] { "fish", "girl", }, }, //$NON-NLS-1$ //$NON-NLS-2$
                new Object[] { "small", "baby", }, }; //$NON-NLS-1$ //$NON-NLS-2$
        Object expand = Trees.convert(tree, new JoinConvert());
        System.out.println(expand);
    }

}
