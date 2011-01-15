package net.bodz.bas.log.adapters;

import net.bodz.bas.log.impl.JdkLogComposite;
import net.bodz.bas.log.testapp.ChildJob;
import net.bodz.bas.log.testapp.ParentJob;

import org.junit.Assert;
import org.junit.Test;

public class JdkLogCompositeTest
        extends Assert {

    ParentJob parent1 = new ParentJob("parent1");
    ChildJob child1 = new ChildJob(parent1, "child1");

    public JdkLogCompositeTest() {
        parent1.setLogger(JdkLogComposite.getInstance(parent1.getClass()));
        child1.setLogger(JdkLogComposite.getInstance(child1.getClass()));
    }

    @Test
    public void testLogs()
            throws Exception {
        parent1.exec();
    }

}
