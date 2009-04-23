package net.bodz.dist.ins;

import java.io.File;
import java.util.Map;

import net.bodz.bas.io.Files;
import net.bodz.bas.util.LogTerm;

public class TestConfig {

    // <property name="packoutdir" value="${env.TMP}/PackagerTaskTest" />
    // <property name="packoutjar" value="${env.TMP}/PackagerTaskTest/a.jar" />

    public static File tmp        = Files.getTmpDir();
    public static File outDir     = new File(tmp, "pack.dir");
    public static File outJar     = new File(tmp, "pack.jar");

    public static File targetHome = new File(tmp, "target");

    public static void setTestBaseDir(ISession session) {
        Project project = session.getProject();
        LogTerm L = session.getLogger();
        Map<String, Variable> variables = project.getVariables();
        for (Map.Entry<String, Variable> e : variables.entrySet()) {
            String name = e.getKey();
            Variable variable = e.getValue();
            if (variable.getType() == Variable.BASE_DIR) {
                File testbase = new File(TestConfig.targetHome, name);
                L.finfo("Set test basedir %s => %s\n", name, testbase);
                session.set(name, testbase);
            }
        }
    }

}
