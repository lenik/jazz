package net.bodz.bas.ar.zip;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.ZipInputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.bodz.bas.c.java.io.DbgInputStream;
import net.bodz.bas.io.impl.RafIn;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.builtin.InputStreamSource;
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
        String comment = eoc.getComment();
        comment.length();
    }

    @Test
    public void readCenEntries()
            throws IOException {
        for (ZipEntry entry : unarchiver.entries()) {
            System.out.print("Entry: " + entry.getName() + ": ");

            IStreamInputSource src = entry.getInputSource();
            byte[] data = src.tooling()._for(StreamReading.class).read();

            System.out.print(data.length + " bytes");

            CRC32 crc32 = new CRC32();
            crc32.update(data);
            int value = (int) crc32.getValue();
            // assertEquals(entry.crc32, value);
            if (value != entry.crc32)
                System.out.print(" crc failed: " + entry.crc32 + "/" + value);

            System.out.println();
        }
    }

    // @Test
    public void readEntries_JUZ()
            throws IOException {
        InputStream in = new FileInputStream(FILENAME);
        ZipInputStream zin = new ZipInputStream(new DbgInputStream(in));
        java.util.zip.ZipEntry entry;
        while ((entry = zin.getNextEntry()) != null) {
            System.out.print("Entry: " + entry.getName() + ": ");

            byte[] data = new InputStreamSource(zin).tooling()._for(StreamReading.class).read();
            System.out.print(data.length + " bytes");

            CRC32 crc32 = new CRC32();
            crc32.update(data);
            long value = crc32.getValue();
            assertEquals(entry.getCrc(), value);

            System.out.println();
        }
    }

}
