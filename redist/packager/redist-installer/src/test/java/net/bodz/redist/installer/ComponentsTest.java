package net.bodz.redist.installer;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.util.TextMap;
import net.bodz.bas.vfs.impl.pojf.PojfFile;
import net.bodz.bas.xml.XMLs;
import net.bodz.redist.installer.builtins.TestConfig;

public class ComponentsTest
        extends Assert {

    @Test
    public void test1()
            throws Exception {
        IProject project = new TestProject();
        Components components = Components.collect(project);
        System.out.println("Components Count: " + components.size());

        System.out.println("Next Map: ");
        checkNextMap(components.getNextMap(), project.getId());

        ProjectExecutor executor = new ConsoleExecutor(project);
        ISession session = executor.getSession();
        PojfFile outDir = new PojfFile(TestConfig.outDir);
        session.addResFolder(0, outDir);
        executor.pack();

        TextMap<Object> registry = components.exportRegistry();
        String xml = XMLs.encode(registry);
        System.out.println(xml);
    }

    public void checkNextMap(Map<String, String> nextMap, String start) {
        int size = nextMap.size();
        String s = start;
        int index = 0;
        while (true) {
            String t = nextMap.get(s);
            if (t == null)
                break;
            System.out.printf("    %d. %s -> %s\n", ++index, s, t);
            s = t;
        }
        assertEquals(size, index);
    }

}
