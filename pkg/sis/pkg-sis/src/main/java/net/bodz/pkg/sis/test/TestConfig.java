package net.bodz.pkg.sis.test;

import java.io.File;
import java.util.Map.Entry;

import net.bodz.bas.c.java.io.TempFile;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.impl.pojf.PojfFile;
import net.bodz.pkg.sis.ISisProject;
import net.bodz.pkg.sis.SisVariable;

public class TestConfig {

    static final Logger logger = LoggerFactory.getLogger(TestConfig.class);

    // <property name="packoutdir" value="${env.TMP}/PackagerTaskTest" />
    // <property name="packoutjar" value="${env.TMP}/PackagerTaskTest/a.jar" />

    public static File tmp = TempFile.getTempRoot();
    public static IFile outDir = new PojfFile(new File(tmp, "pack.dir"));
    public static IFile outDirZip = new PojfFile(new File(tmp, "pack.dir.zip"));
    public static IFile outJar = new PojfFile(new File(tmp, "pack.jar"));

    public static File targetHome = new File(tmp, "target");

    public static void setTestBaseDir(ISisProject project) {
        for (Entry<String, SisVariable> e : project.getVariableMap().entrySet()) {
            String varName = e.getKey();
            SisVariable variable = e.getValue();
            if (File.class.isAssignableFrom(variable.getValueType())) {
                File testbase = new File(TestConfig.targetHome, varName);
                logger.infof("Set test basedir %s => %s\n", varName, testbase);
                variable.setValue(testbase);
            }
        }
    }

}
