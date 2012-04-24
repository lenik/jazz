package net.bodz.bas.sio;

import org.junit.Assert;
import org.junit.Test;

public class StackedOutImplTest
        extends Assert {

    @Test
    public void testIndentPrint()
            throws Exception {
        BCharOut buffer = new BCharOut();
        ITreeOut out = new TreeOutImpl(buffer);
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
    }

}
