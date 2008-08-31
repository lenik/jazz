package net.bodz.bios;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import net.bodz.bios.units.sinks.DumperSink;
import net.bodz.bios.units.sources.InputStreamSource;
import net.bodz.bios.units.text.BreakLinesUnit;
import net.bodz.bios.units.text.BreakOrCutLinesUnit;
import net.bodz.bios.units.text.DecodeUnit;
import net.bodz.bios.units.util.GrabberUnit;
import net.bodz.bios.units.util.TeeUnit;

import org.junit.Test;

public class _UnitTest {

    InputStreamSource   src;
    DecodeUnit          decoder;
    TeeUnit             tee;
    BreakLinesUnit      breakLines;
    BreakOrCutLinesUnit breakOrCut;
    DumperSink          dumper;
    GrabberUnit         lineGrabber;

    static final int    BLOCKSIZE = 8;
    static final int    CUTSIZE   = 10;

    void init(byte[] bin) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(bin);
        src = new InputStreamSource(in, BLOCKSIZE);
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
    public void testDumpGraph() throws IOException {
        byte[] bin = "hello".getBytes();
        init(bin);
        String s = src.toString();
        System.out.println(s);
    }

}
