package net.bodz.bas.flow.units.sinks;

import java.io.IOException;

import net.bodz.bas.flow.units.builtin.sinks.CharOutSink;
import net.bodz.bas.io.out.CharOut;
import net.bodz.bas.io.out.CharOuts;

public class CharOutSinkTest {

    CharOut charOut;
    CharOutSink charOutSink;

    public CharOutSinkTest() throws IOException {
        charOut = new CharOuts.BCharOut();
        charOutSink = new CharOutSink(charOut);
    }

    public void test1() {

    }

}
