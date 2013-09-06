package net.bodz.bas.io;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.io.impl.TreeOutImpl;

public class TreeOutImplTest
        extends Assert {

    @Test
    public void testIndentPrint()
            throws Exception {
        BCharOut buffer = new BCharOut();
        ITreeOut out = TreeOutImpl.from(buffer);
        out.getTextIndention().setIndentSize(2);
        out.println("a");
        out.enter();
        out.println("b");
        out.enter();
        out.println("c");
        out.leave();
        out.println("d");
        out.leave();
        out.println("e");
        assertEquals("a\n  b\n    c\n  d\ne\n", buffer.toString());
        out.close();
    }

}
