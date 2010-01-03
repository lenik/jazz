package net.bodz.bas.snm;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import net.bodz.bas.commons.collection.TextMap;
import net.bodz.bas.commons.typealiases.HashTextMap;
import net.bodz.bas.io.Files;

public class EclipseWorkspace {

    private File base;
    private TextMap<String> cpVars;

    public EclipseWorkspace(File base) throws IOException {
        this.base = base;
        reload();
    }

    public File getBase() {
        return base;
    }

    public void setBase(File base) {
        this.base = base;
    }

    static String PREFS = "org.eclipse.core.runtime/.settings/org.eclipse.jdt.core.prefs"; //$NON-NLS-1$
    static String CPVAR = "org.eclipse.jdt.core.classpathVariable."; //$NON-NLS-1$

    public void reload() throws IOException {
        Properties prefs = Files.loadProperties(new File(base, PREFS));
        cpVars = new HashTextMap<String>();
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
            value = ""; //$NON-NLS-1$
        return value;
    }

}
