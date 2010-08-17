package net.bodz.bas.log.adapters;

import junit.framework.TestCase;
import net.bodz.bas.log.testapp.ChildJob;
import net.bodz.bas.log.testapp.ParentJob;

import org.junit.Test;

public class JdkLogLayerTest
        extends TestCase {

    ParentJob parent1 = new ParentJob("parent1");
    ChildJob child1 = new ChildJob(parent1, "child1");

    public JdkLogLayerTest() {
        parent1.setLogLayer(JdkLogLayer.getInstance(parent1.getClass()));
        child1.setLogLayer(JdkLogLayer.getInstance(child1.getClass()));
    }

    @Test
    public void testLogs()
            throws Exception {
        parent1.exec();
    }

}
