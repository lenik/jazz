package net.bodz.bas.cli.util;

import java.io.File;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.snm.SJProject;

public class MkbatTest {

    public static void main(String[] args) throws Throwable {
        File projBase = SJProject.findProjectBase(Mkbat.class);
        if (projBase == null) {
            System.err.println("can't find project base");
            return;
        }
        URL outurl = Files.classDataURL(MkbatTest.class, "out");
        File outdir = Files.canoniOf(outurl);
        outdir.mkdirs();

        File lapiBase = new File(projBase.getParentFile(), "net.bodz.lapiota");
        File moddir = Files.canoniOf(lapiBase, "mod/GUI Utilities");
        File indir = new File(moddir, "bin");
        String[] mkbatArgs = {
        //
                "-O", outdir.getPath(), //
                "-l", "bodz_swt", // 
                "-l", "bodz_lapiota", // 
                "-rq", "--", // recursive, quiet
                indir.getPath(), //
        };

        Mkbat.main(mkbatArgs);
    }

}
