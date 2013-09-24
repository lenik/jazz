package net.bodz.bas.ar.zip;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.bodz.bas.io.impl.RafIn;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.tools.StreamReading;

public class ZipUnarchiverTest
        extends Assert {

    static final String FILENAME = "/home/lenik/android.zip";
// static final String FILENAME = "/tmp/c.zip";

    RafIn in;
    ZipUnarchiver unarchiver;

    @Before
    public void setup()
            throws IOException {
        in = new RafIn(FILENAME, "r");
        unarchiver = new ZipUnarchiver(in);
    }

    @Test
    public void readEocComment()
            throws IOException {
        EndOfCen eoc = unarchiver.getEndOfCen();
        assertFalse(eoc.getComment().isEmpty());
    }

    @Test
    public void readCenEntries()
            throws IOException {
        for (ZipEntry entry : unarchiver.entries()) {
            System.out.println("Entry: " + entry.getName() + ": ");

            IStreamInputSource src = entry.getInputSource();
            String text = src.tooling()._for(StreamReading.class).readString();
            System.out.print(text);

            System.out.println("====");
        }
    }

}
