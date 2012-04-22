package net.bodz.art.installer.builtins;

import java.io.File;
import java.util.Map;

import net.bodz.art.installer.IProject;
import net.bodz.art.installer.ISession;
import net.bodz.art.installer.Variable;
import net.bodz.bas.c.java.io.TempFile;
import net.bodz.bas.log.api.Logger;

public class TestConfig {

    // <property name="packoutdir" value="${env.TMP}/PackagerTaskTest" />
    // <property name="packoutjar" value="${env.TMP}/PackagerTaskTest/a.jar" />

    public static File tmp = TempFile.getTmpDir();
    public static File outDir = new File(tmp, "pack.dir"); //$NON-NLS-1$
    public static File outDirZip = new File(tmp, "pack.dir.zip"); //$NON-NLS-1$
    public static File outJar = new File(tmp, "pack.jar"); //$NON-NLS-1$

    public static File targetHome = new File(tmp, "target"); //$NON-NLS-1$

    public static void setTestBaseDir(ISession session) {
        IProject project = session.getProject();
        Logger L = session.getLogger();
        Map<String, Variable> variables = project.getVariables();
        for (Map.Entry<String, Variable> e : variables.entrySet()) {
            String name = e.getKey();
            Variable variable = e.getValue();
            if (variable.getType() == Variable.BASE_DIR) {
                File testbase = new File(TestConfig.targetHome, name);
                L.infoFormat("Set test basedir %s => %s\n", name, testbase); //$NON-NLS-1$
                session.set(name, testbase);
            }
        }
    }

}
