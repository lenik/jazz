package net.bodz.mda.tmpl.xtx.langs;

import java.io.File;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.mda.tmpl.xtx.XtxMain;

import org.junit.Test;

public class PerlTest {

    String tmpdir = Files.getTmpDir().getPath();

    @Test
    public void test() throws Exception {
        String encoding = "utf-8";
        URL _template = Files.classData(getClass(), "plt");
        File template = new File(_template.toURI());

        String opts = "-B";
        // String opts = "-Bst";
        XtxMain.main(new String[] { opts, "-e", encoding, //
                "-o", tmpdir, //
                template.getPath(), });
        File scriptFile = new File(tmpdir, "PerlTest.pl");
        File resultFile = new File(tmpdir, "PerlTest");

        String scriptExpected = Files.readAll(Files.classData(getClass(), "pl.expected"), encoding);
        String scriptActual = Files.readAll(scriptFile, encoding);
        Files.equals(scriptExpected, scriptActual);

        String resultExpected = Files.readAll(Files.classData(getClass(), "expected"), encoding);
        String resultActual = Files.readAll(resultFile, encoding);
        Files.equals(resultExpected, resultActual);
    }

}
