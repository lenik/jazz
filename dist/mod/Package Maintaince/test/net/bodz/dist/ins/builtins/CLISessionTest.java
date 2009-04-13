package net.bodz.dist.ins.builtins;

import net.bodz.dist.ins.TestProject;

import org.junit.Test;

public class CLISessionTest {

    @Test
    public void testPack1() throws Exception {
        TestProject project = new TestProject();
        CLISession session = new CLISession(project);
        session.setBaseDir("output", null);
        session.pack();
    }

}
