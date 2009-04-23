package net.bodz.bios.units.sinks;

import java.io.IOException;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;

public class CharOutSinkTest {

    CharOut     charOut;
    CharOutSink charOutSink;

    public CharOutSinkTest() throws IOException {
        charOut = new CharOuts.BCharOut();
        charOutSink = new CharOutSink(charOut);
    }

    public void test1() {

    }

}
