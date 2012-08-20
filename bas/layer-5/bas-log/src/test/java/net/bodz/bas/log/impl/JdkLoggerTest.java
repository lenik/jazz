package net.bodz.bas.log.impl;

import org.junit.Assert;
import org.junit.Test;

import user.logs.weave.ChildJob;
import user.logs.weave.ParentJob;

public class JdkLoggerTest
        extends Assert {

    ParentJob parent1 = new ParentJob("parent1");
    ChildJob child1 = new ChildJob(parent1, "child1");

    public JdkLoggerTest() {
        parent1.setLogger(JdkLogger.getInstance(parent1.getClass()));
        child1.setLogger(JdkLogger.getInstance(child1.getClass()));
    }

    @Test
    public void testLogs()
            throws Exception {
        parent1.exec();
    }

}
