package net.bodz.bas.cli.util;

import java.io.File;
import java.net.URL;

import net.bodz.bas.io.Files;
import net.bodz.bas.snm.SJProject;

public class MkbatTest {

    public static void main(String[] args) throws Throwable {
        File projBase = SJProject.findProjectBase(Mkbat.class);
        if (projBase == null) {
            System.err.println("can't find project base"); //$NON-NLS-1$
            return;
        }
        URL outurl = Files.classDataURL(MkbatTest.class, "out"); //$NON-NLS-1$
        File outdir = Files.canoniOf(outurl);
        outdir.mkdirs();

        File lapiBase = new File(projBase.getParentFile(), "net.bodz.lapiota"); //$NON-NLS-1$
        File moddir = Files.canoniOf(lapiBase, "mod/GUI Utilities"); //$NON-NLS-1$
        File indir = new File(moddir, "bin"); //$NON-NLS-1$
        String[] mkbatArgs = {
        //
                "-O", outdir.getPath(), // //$NON-NLS-1$
                "-l", "bodz_swt", //  //$NON-NLS-1$ //$NON-NLS-2$
                "-l", "bodz_lapiota", //  //$NON-NLS-1$ //$NON-NLS-2$
                "-rq", "--", // recursive, quiet //$NON-NLS-1$ //$NON-NLS-2$
                indir.getPath(), //
        };

        Mkbat.main(mkbatArgs);
    }

}
