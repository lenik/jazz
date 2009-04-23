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
import net.bodz.dist.ins.builtins.DefaultSection;
import net.bodz.dist.ins.builtins.FileCopy;
import net.bodz.dist.ins.builtins.OptionalSection;
import net.bodz.dist.ins.builtins.RequiredSection;
import net.bodz.dist.ins.builtins.Section;
import net.bodz.dist.ins.builtins.SimpleProject;

@DisplayName("ABC Program")
@Doc("A test program for z.dist")
@RcsKeywords(id = "$Id$")
@Version( { 1, 2, 3 })
public class TestProject extends SimpleProject {

    public static final String BASE_TEST = "BASE_TEST";

    public Section             classesSection;
    public Section             sourceSection;
    public Section             testSection;

    public FileCopy            copyClassFiles;
    public FileCopy            copySrcFiles;
    public FileCopy            copyTestClassesFiles;
    public FileCopy            copyTestSrcFiles;

    public TestProject() throws IOException {
        super(TestProject.class);

        File parent = (File) get(BASE_PROGRAMS).getDefaultValue();
        Variable var = new BasedirVariable(//
                "Test Files", //
                "A place to put test related files.", //
                new File(parent, "tests"));
        define("BASE_TEST", var);

        FileFilter filter = FileCopy.NoSVN;

        classesSection = new RequiredSection("classes", "Java Class Files");
        {
            File binDir = SJProject.getOutputBase(Component.class);
            FileFinder binfiles = new FileFinder(filter, binDir);
            copyClassFiles = new FileCopy(BASE_PROGRAMS, binfiles);
            classesSection.add(copyClassFiles);
        }

        sourceSection = new DefaultSection("src", "Source Files");
        {
            File srcDir = SJProject.getSrcBase(Component.class);
            FileFinder srcfiles = new FileFinder(filter, srcDir);
            copySrcFiles = new FileCopy(BASE_PROGRAMS, srcfiles);
            sourceSection.add(copySrcFiles);
        }

        testSection = new OptionalSection("test", "Test source and classes");
        {
            File testBinDir = SJProject.getOutputBase(TestProject.class);
            File testSrcDir = SJProject.getSrcBase(TestProject.class);

            FileFinder testbin = new FileFinder(filter, testBinDir);
            FileFinder testsrc = new FileFinder(filter, testSrcDir);
            copyTestClassesFiles = new FileCopy(BASE_TEST, testbin);
            copyTestSrcFiles = new FileCopy(BASE_TEST, testsrc);
            testSection.add(copyTestClassesFiles);
            testSection.add(copyTestSrcFiles);
        }

        add(classesSection);
        add(sourceSection);
        add(testSection);
    }

}
