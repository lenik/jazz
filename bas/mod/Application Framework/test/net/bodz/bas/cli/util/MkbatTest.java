package net.bodz.bas.cli.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.util.Classpath;
import net.bodz.bas.loader.LoadUtil;
import net.bodz.bas.snm.SJProject;

public class MkbatTest {

    static {
        try {
            String[] libs = { "bodz_bas", "bodz_swt", "bodz_lapiota" };
            for (String libspec : libs) {
                URL lib = LoadUtil.findLib(libspec);
                Classpath.addURL(lib);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
                "-rq", "--", // recursive, quiet
                indir.getPath(), //
        };

        Mkbat.main(mkbatArgs);
    }

}
