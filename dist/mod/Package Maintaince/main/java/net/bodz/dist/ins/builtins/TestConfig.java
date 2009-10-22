package net.bodz.dist.ins.builtins;

import java.io.File;
import java.util.Map;

import net.bodz.bas.io.Files;
import net.bodz.bas.util.LogTerm;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.Project;
import net.bodz.dist.ins.Variable;

public class TestConfig {

    // <property name="packoutdir" value="${env.TMP}/PackagerTaskTest" />
    // <property name="packoutjar" value="${env.TMP}/PackagerTaskTest/a.jar" />

    public static File tmp        = Files.getTmpDir();
    public static File outDir     = new File(tmp, "pack.dir"); //$NON-NLS-1$
    public static File outDirZip  = new File(tmp, "pack.dir.zip"); //$NON-NLS-1$
    public static File outJar     = new File(tmp, "pack.jar"); //$NON-NLS-1$

    public static File targetHome = new File(tmp, "target"); //$NON-NLS-1$

    public static void setTestBaseDir(ISession session) {
        Project project = session.getProject();
        LogTerm L = session.getLogger();
        Map<String, Variable> variables = project.getVariables();
        for (Map.Entry<String, Variable> e : variables.entrySet()) {
            String name = e.getKey();
            Variable variable = e.getValue();
            if (variable.getType() == Variable.BASE_DIR) {
                File testbase = new File(TestConfig.targetHome, name);
                L.finfo("Set test basedir %s => %s\n", name, testbase); //$NON-NLS-1$
                session.set(name, testbase);
            }
        }
    }

}
