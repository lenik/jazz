package net.bodz.bas.vcs.git;

import java.io.File;

import org.junit.Assert;

import net.bodz.bas.vcs.IVcsLogEntry;
import net.bodz.bas.vcs.IVcsWorkingCopy;
import net.bodz.bas.vcs.VcsLogOptions;

public class NativeGitVcsWorkingCopyTest
        extends Assert {

    static String jazzDir = "/mnt/istore/projects/jazz";

    public static void main(String[] args)
            throws Exception {
        NativeGitVcsProvider gitVcsProvider = new NativeGitVcsProvider();
        IVcsWorkingCopy jazzWc = gitVcsProvider.getWorkingCopy(new File(jazzDir));

        VcsLogOptions logOptions = new VcsLogOptions();
        Iterable<IVcsLogEntry> logs = jazzWc.log("bas/layer-7/bas-program", logOptions);

        int count = 0;
        for (IVcsLogEntry entry : logs) {
            System.out.println(entry);
            count++;
        }
        System.out.println("Revisions: " + count);
    }

}
