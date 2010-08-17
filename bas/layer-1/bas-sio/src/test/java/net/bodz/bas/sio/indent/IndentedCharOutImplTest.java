package net.bodz.bas.sio.indent;

import junit.framework.TestCase;
import net.bodz.bas.sio.BCharOut;

import org.junit.Test;

public class IndentedCharOutImplTest
        extends TestCase {

    @Test
    public void testIndentPrint()
            throws Exception {
        BCharOut buffer = new BCharOut();
        IIndentedCharOut out = new IndentedCharOutImpl(buffer);
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
