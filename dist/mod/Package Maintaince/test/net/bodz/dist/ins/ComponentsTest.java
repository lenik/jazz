package net.bodz.dist.ins;

import java.io.File;

import net.bodz.bas.io.Files;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.xml.XMLs;
import net.bodz.dist.ins.builtins.CLISession;

import org.junit.Test;

public class ComponentsTest {

    static File packout;
    static {
        File tmp = Files.getTmpDir();
        packout = new File(tmp, "pack.out");
    }

    @Test
    public void test1() throws Exception {
        IProject project = new TestProject();
        ISession session = new CLISession(project);
        session.setResFolder(packout);
        session.pack();

        Components components = Components.collect(project);
        System.out.println("size=" + components.size());
        TextMap<Object> registry = components.exportRegistry();
        String xml = XMLs.encode(registry);
        System.out.println(xml);
    }

}
