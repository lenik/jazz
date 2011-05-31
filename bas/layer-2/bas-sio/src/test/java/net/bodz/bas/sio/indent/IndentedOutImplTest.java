package net.bodz.bas.sio.indent;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.sio.BCharOut;

public class IndentedOutImplTest
        extends Assert {

    @Test
    public void testIndentPrint()
            throws Exception {
        BCharOut buffer = new BCharOut();
        IIndentedOut out = new IndentedOutImpl(buffer);
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
