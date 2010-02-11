package net.bodz.bas.sio.indent;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.sio.BCharOut;

import org.junit.Test;

public class IndentedCharOutImplTest {

    @Test
    public void testIndentPrint()
            throws Exception {
        BCharOut buffer = new BCharOut();
        IIndentedCharOut out = new IndentedCharOutImpl(buffer);
        out.setIndentSize(2);
        out.println("a");
        out.increaseIndentLevel();
        out.println("b");
        out.increaseIndentLevel();
        out.println("c");
        out.decreaseIndentLevel();
        out.println("d");
        out.decreaseIndentLevel();
        out.println("e");
        assertEquals("a\n  b\n    c\n  d\ne\n", buffer.toString());
    }

}
