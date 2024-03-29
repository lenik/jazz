package net.bodz.bas.c.org.eclipse;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.bodz.bas.io.res.ResFn;
import net.bodz.bas.io.res.tools.StreamLoading;

public class EclipseWorkspace {

    private File base;
    private Map<String, String> cpVars;

    public EclipseWorkspace(File base)
            throws IOException {
        this.base = base;
        reload();
    }

    public File getBase() {
        return base;
    }

    public void setBase(File base) {
        this.base = base;
    }

    static String PREFS = "org.eclipse.core.runtime/.settings/org.eclipse.jdt.core.prefs";
    static String CPVAR = "org.eclipse.jdt.core.classpathVariable.";

    public void reload()
            throws IOException {
        Properties prefs = ResFn.file(new File(base, PREFS))//
                .to(StreamLoading.class).loadProperties();
        cpVars = new HashMap<String, String>();
        for (Object k : prefs.keySet()) {
            String name = (String) k;
            if (name.startsWith(CPVAR)) {
                name = name.substring(CPVAR.length());
                String value = prefs.getProperty(name);
                cpVars.put(name, value);
            }
        }
    }

    public String expandVar(String varName) {
        String value = cpVars.get(varName);
        if (value == null)
            value = "";
        return value;
    }

}
