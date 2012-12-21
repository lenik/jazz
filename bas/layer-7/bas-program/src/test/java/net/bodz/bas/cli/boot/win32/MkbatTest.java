package net.bodz.bas.cli.boot.win32;

import java.io.File;
import java.net.URL;

import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.c.org.eclipse.JavaProjectBaseDir;

public class MkbatTest {

    public static void main(String[] args)
            throws Throwable {
        File baseDir = JavaProjectBaseDir.forClass(Mkbat.class);
        if (baseDir == null) {
            System.err.println("can't find project base dir");
            return;
        }
        URL outurl = ClassResource.getDataURL(MkbatTest.class, "out");
        File outdir = FilePath.canoniOf(outurl);
        outdir.mkdirs();

        File lapiBase = new File(baseDir.getParentFile(), "net.bodz.lapiota");
        File moddir = FilePath.canoniOf(lapiBase, "mod/GUI Utilities");
        File indir = new File(moddir, "bin");
        String[] mkbatArgs = { //
        "-O", outdir.getPath(), //
                "-l", "bodz_swt", //
                "-l", "bodz_lapiota", //
                "-rq", "--", // recursive, quiet
                indir.getPath(), //
        };

        Mkbat.main(mkbatArgs);
    }

}
