package net.bodz.bas.ar.zip;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.CRC32;
import java.util.zip.ZipInputStream;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import net.bodz.bas.c.java.io.DbgInputStream;
import net.bodz.bas.c.java.nio.Charsets;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.rst.RstOutputImpl;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.RafIn;
import net.bodz.bas.io.impl.TreeOutImpl;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.builtin.InputStreamSource;
import net.bodz.bas.io.res.tools.StreamReading;

public class ZipUnarchiverTest
        extends Assert {

    static final String[] zipFileNames = {
            //
            "/tmp/a.zip", // 0
            "/home/lenik/android.zip", // 1
            "/home/lenik/.local/chrome/Huawei-G520-cofface.zip", // 2
            "/home/lenik/.local/chrome/S60_SDK_3.2_v1.1.1_en.zip", // 3
    };

    private ITreeOut out = TreeOutImpl.from(Stdio.cout);

    private RafIn in;
    private ZipUnarchiver unarchiver;

    @Before
    public void setup()
            throws IOException {
        File file = new File(zipFileNames[3]);
        Assume.assumeTrue(file.exists());

        in = new RafIn(file, "r");
        unarchiver = new ZipUnarchiver(in);
    }

    public void testLocalZips()
            throws Exception {
        String dir = "/home/lenik/.local/chrome/";
        for (String name : new File(dir).list())
            if (name.endsWith(".zip")) {
                String file = dir + name;
                out.println("File: " + file);
                out.enter();

                in = new RafIn(file, "r");
                unarchiver = new ZipUnarchiver(in);

                readCenEntries();
                out.leave();
            }
    }

    // @Test
    public void readEocComment()
            throws IOException {
        EndOfCen eoc = unarchiver.getEndOfCen();
        String comment = eoc.getComment();
        comment.length();
    }

    @Test
    public void readLocEntries()
            throws OutOfMemoryError, IOException, FormatException {
        ZipEntry entry;
        while ((entry = unarchiver.nextEntry()) != null) {
            out.print("Loc-Entry: " + entry.getName() + ": ");

            IStreamInputSource src = entry.getInputSource();
            byte[] data = src.to(StreamReading.class).read();

            out.print(data.length + " bytes");

            CRC32 crc32 = new CRC32();
            crc32.update(data);
            int value = (int) crc32.getValue();
            // assertEquals(entry.crc32, value);
            if (value != entry.crc32)
                out.print(" crc failed: " + entry.crc32 + "/" + value);

            out.println();
            out.enter();
            for (ExtraField extraField : entry.extraFields.values()) {
                out.println("Field " + extraField.getClass().getSimpleName() + ": ");
                out.enter();
                extraField.writeObject(RstOutputImpl.from(out));
                out.leave();
            }
            out.leave();
        }
    }

// @Test
    public void readCenEntries()
            throws Exception {
        for (ZipEntry entry : unarchiver.entries()) {
            out.print("Entry: " + entry.getName() + ": ");

            IStreamInputSource src = entry.getInputSource();
            byte[] data = src.to(StreamReading.class).read();

            out.print(data.length + " bytes");

            CRC32 crc32 = new CRC32();
            crc32.update(data);
            int value = (int) crc32.getValue();
            // assertEquals(entry.crc32, value);
            if (value != entry.crc32)
                out.print(" crc failed: " + entry.crc32 + "/" + value);

            out.println();
            out.enter();
            for (ExtraField extraField : entry.extraFields.values()) {
                out.println("Field " + extraField.getClass().getSimpleName() + ": ");
                out.enter();
                extraField.writeObject(RstOutputImpl.from(out));
                out.leave();
            }
            out.leave();
        }
    }

    // @Test
    public void readEntries_JUZ()
            throws IOException {
        // Charset charset = Charset.forName("gb2312");
        Charset charset = Charsets.UTF_8;
        ZipInputStream zin = new ZipInputStream(new DbgInputStream(in), charset);
        java.util.zip.ZipEntry entry;
        while ((entry = zin.getNextEntry()) != null) {
            out.print("Entry: " + entry.getName() + ": ");

            byte[] data = new InputStreamSource(zin).to(StreamReading.class).read();
            out.print(data.length + " bytes");

            CRC32 crc32 = new CRC32();
            crc32.update(data);
            long value = crc32.getValue();
            assertEquals(entry.getCrc(), value);

            out.println();
        }
    }

    public static void main(String[] args)
            throws Exception {
        new ZipUnarchiverTest().testLocalZips();
    }

}
