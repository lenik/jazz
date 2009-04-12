package net.bodz.dist.ins;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import net.bodz.bas.a.DisplayName;
import net.bodz.bas.a.Doc;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.Version;
import net.bodz.bas.io.FileFinder;
import net.bodz.bas.snm.SJProject;
import net.bodz.dist.ins.builtins.FileCopy;
import net.bodz.dist.ins.builtins.FileCopyTest;
import net.bodz.dist.ins.builtins.SimpleProject;

@DisplayName("ABC Program")
@Doc("A test program for z.dist")
@RcsKeywords(id = "$Id: TestProject.java 10 2009-04-05 04:00:52Z Shecti $")
@Version( { 1, 2, 3 })
public class TestProject extends SimpleProject {

    public FileCopy copySrcFiles;

    public TestProject() throws IOException {
        super(TestProject.class);

        File srcdir = SJProject.findBase(FileCopyTest.class);

        FileFilter srcfilter = null;// new NoSVN();
        FileFinder srcfiles = new FileFinder(srcfilter, srcdir);

        copySrcFiles = new FileCopy(PROGRAMS, srcfiles);

        add(copySrcFiles);
    }

    class NoSVN implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            if (".svn".equals(pathname.getName().toLowerCase()))
                return false;
            return true;
        }
    }

}
