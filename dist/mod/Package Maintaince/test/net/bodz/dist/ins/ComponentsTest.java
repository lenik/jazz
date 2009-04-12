package net.bodz.dist.ins;

import org.junit.Test;

public class ComponentsTest {

    @Test
    public void test1() throws Exception {
        TestSession session = new TestSession();
        IProject project = session.getProject();
        // session.dump(component, pnum)

        Components components = Components.collect(project);
        System.out.println("size=" + components.size());
        String xml = components.dump();
        System.out.println(xml);
    }

}
