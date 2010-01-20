package net.bodz.bas.flow.units.sinks;

import java.io.IOException;

import net.bodz.bas.flow.units.builtin.sinks.CharOutSink;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.ICharOut;

public class CharOutSinkTest {

    ICharOut charOut;
    CharOutSink charOutSink;

    public CharOutSinkTest()
            throws IOException {
        charOut = new BCharOut();
        charOutSink = new CharOutSink(charOut);
    }

    public void test1() {

    }

}
