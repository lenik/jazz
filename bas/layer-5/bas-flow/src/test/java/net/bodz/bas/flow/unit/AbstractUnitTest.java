package net.bodz.bas.flow.unit;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import net.bodz.bas.flow.unit.arch.GrabberUnit;
import net.bodz.bas.flow.unit.arch.TeeUnit;
import net.bodz.bas.flow.unit.builtins.DumperSinkUnit;
import net.bodz.bas.flow.unit.builtins.InputStreamSourceUnit;
import net.bodz.bas.flow.unit.builtins.text.BreakLinesUnit;
import net.bodz.bas.flow.unit.builtins.text.BreakOrCutLinesUnit;
import net.bodz.bas.flow.unit.builtins.text.DecodeUnit;

import org.junit.Assert;
import org.junit.Test;

public class AbstractUnitTest
        extends Assert {

    InputStreamSourceUnit src;
    DecodeUnit decoder;
    TeeUnit tee;
    BreakLinesUnit breakLines;
    BreakOrCutLinesUnit breakOrCut;
    DumperSinkUnit dumper;
    GrabberUnit lineGrabber;

    static final boolean OVERLAP = true;
    static final int BLOCKSIZE = 8;
    static final int CUTSIZE = 10;

    void init(byte[] bin)
            throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(bin);
        src = new InputStreamSourceUnit(in, OVERLAP, BLOCKSIZE);
        src.setDst(decoder = new DecodeUnit("utf-8"));
        decoder.setDst(tee = new TeeUnit());
        tee.addOutPort(breakLines = new BreakLinesUnit());
        tee.addOutPort(breakOrCut = new BreakOrCutLinesUnit(CUTSIZE));
        breakLines.setChop(true);
        breakOrCut.setChop(false);
        breakLines.setDst(lineGrabber);
        breakOrCut.setDst(lineGrabber);
        lineGrabber = GrabberUnit.insert(breakLines);
    }

    @Test
    public void testDumpGraph()
            throws IOException {
        byte[] bin = "hello".getBytes();
        init(bin);
        String s = src.toString();
        System.out.println(s);
    }

}
