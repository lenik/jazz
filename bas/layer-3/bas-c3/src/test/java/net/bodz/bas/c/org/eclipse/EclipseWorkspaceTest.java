package net.bodz.bas.c.org.eclipse;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class EclipseWorkspaceTest {

    /**
     * dump environ
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Map<String, String> env = System.getenv();
        for (String k : env.keySet())
            System.out.println(k + " = " + env.get(k));
        Properties props = System.getProperties();
        for (String k : (Set<String>) (Set<?>) props.keySet())
            System.out.println(k + " := " + props.getProperty(k));
    }

}
