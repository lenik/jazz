package net.bodz.dist.ins;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import net.bodz.bas.io.FileResFolder;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.xml.XMLs;
import net.bodz.dist.ins.builtins.TestConfig;

import org.junit.Test;

public class ComponentsTest {

    @Test
    public void test1() throws Exception {
        Project project = new TestProject();
        Components components = Components.collect(project);
        System.out.println("Components Count: " + components.size()); //$NON-NLS-1$

        System.out.println("Next Map: ");
        checkNextMap(components.getNextMap(), project.getId());

        ProjectExecutor executor = new ConsoleExecutor(project);
        ISession session = executor.getSession();
        session.addResFolder(0, new FileResFolder(TestConfig.outDir, true));
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
