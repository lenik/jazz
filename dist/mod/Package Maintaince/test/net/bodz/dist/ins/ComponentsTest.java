package net.bodz.dist.ins;

import net.bodz.bas.types.TextMap;
import net.bodz.bas.xml.XMLs;

import org.junit.Test;

public class ComponentsTest {

    @Test
    public void test1() throws Exception {
        IProject project = new TestProject();
        // _Session session = new CLISession(project);
        Components components = Components.collect(project);
        System.out.println("size=" + components.size());
        TextMap<Object> registry = components.exportRegistry();
        String xml = XMLs.encode(registry);
        System.out.println(xml);
    }

}
