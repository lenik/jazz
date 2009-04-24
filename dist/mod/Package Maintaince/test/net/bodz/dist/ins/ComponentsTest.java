package net.bodz.dist.ins;

import net.bodz.bas.io.FileResFolder;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.xml.XMLs;

import org.junit.Test;

public class ComponentsTest {

    @Test
    public void test1() throws Exception {
        Project project = new TestProject();
        Components components = Components.collect(project);
        System.out.println("Components Count: " + components.size()); //$NON-NLS-1$

        ProjectExecutor executor = new ConsoleExecutor(project);
        ISession session = executor.getSession();
        session.addResFolder(0, new FileResFolder(TestConfig.outDir, true));
        executor.pack();

        TextMap<Object> registry = components.exportRegistry();
        String xml = XMLs.encode(registry);
        System.out.println(xml);
    }

}
