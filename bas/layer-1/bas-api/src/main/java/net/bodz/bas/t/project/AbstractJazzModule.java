package net.bodz.bas.t.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.TreeSet;

/**
 * To make the default {@link #getPackageName()} and all the related methods work, your direct
 * derivation of this abstract class should be declared as <code>final</code>.
 */
public abstract class AbstractJazzModule
        implements IJazzModule {

    private String name;
    private String packageName;

    private Set<String> managedClassNames = new TreeSet<>();

    static Charset utf8 = Charset.forName("utf-8");

    public AbstractJazzModule() {
        String fqcn = getClass().getName();
        String baseName = getClass().getSimpleName();
        if (baseName.endsWith("Module"))
            baseName = baseName.substring(0, baseName.length() - 6);

        this.name = baseName;
        this.packageName = getClass().getPackage().getName();

        URL listFile = getClass().getResource("/META-INF/modules/" + fqcn);
        if (listFile != null)
            try {
                InputStream in = listFile.openStream();
                InputStreamReader isr = new InputStreamReader(in, utf8);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty())
                        continue;
                    if (line.startsWith("#"))
                        continue;
                    managedClassNames.add(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
    }

    @Override
    public String getName() {
        return name;
    }

    public String getPackageName() {
        return packageName;
    }

    @Override
    public Set<String> getManagedClassNames() {
        return managedClassNames;
    }

}
