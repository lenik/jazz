package net.bodz.bas.cli.util;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;

import net.bodz.bas.snm.EclipseProject;

public class MkbatTest {

    public static void main(String[] args) throws Throwable {
        File projBase = EclipseProject.findProjectBase(Mkbat.class);
        if (projBase == null) {
            System.err.println("can't find project base"); 
            return;
        }
        URL outurl = Files.classData(MkbatTest.class, "out"); 
        File outdir = Files.canoniOf(outurl);
        outdir.mkdirs();

        File lapiBase = new File(projBase.getParentFile(), "net.bodz.lapiota"); 
        File moddir = Files.canoniOf(lapiBase, "mod/GUI Utilities"); 
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
