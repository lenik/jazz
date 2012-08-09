package net.bodz.bas.flow.unit.builtins.text;

import java.io.IOException;

import net.bodz.bas.flow.unit.builtins.CharOutSinkUnit;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.ICharOut;

public class CharOutSinkUnitTest {

    ICharOut charOut;
    CharOutSinkUnit charOutSink;

    public CharOutSinkUnitTest()
            throws IOException {
        charOut = new BCharOut();
        charOutSink = new CharOutSinkUnit(charOut);
    }

    public void test1() {

    }

}
