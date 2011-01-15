package net.bodz.bas.log.adapters;

import net.bodz.bas.log.impl.JCLLogComposite;
import net.bodz.bas.log.testapp.ChildJob;
import net.bodz.bas.log.testapp.ParentJob;

import org.junit.Assert;
import org.junit.Test;

public class JCLLogCompositeTest
        extends Assert {

    ParentJob parent1 = new ParentJob("parent1");
    ChildJob child1 = new ChildJob(parent1, "child1");

    public JCLLogCompositeTest() {
        parent1.setLogger(JCLLogComposite.getInstance(parent1.getClass()));
        child1.setLogger(JCLLogComposite.getInstance(child1.getClass()));
    }

    @Test
    public void testLogs()
            throws Exception {
        parent1.exec();
    }

}
