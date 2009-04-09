package net.bodz.dist.ins.builtins;

import java.util.Arrays;
import java.util.Collection;

import net.bodz.dist.ins.IComponent;
import net.bodz.dist.ins.TestProject;
import net.bodz.dist.ins.TestSession;

import org.junit.Test;

public class FileCopyTest {

    @Test
    public void test1() throws Exception {
        FileCopy copy = new FileCopy("pfiles", "X:/", //
                null);
        final IComponent[] roots = { copy };
        TestProject project = new TestProject() {
            @Override
            public Collection<IComponent> getChildren() {
                return Arrays.asList(roots);
            }
        };
        TestSession session = new TestSession();
        // Packager
    }

}
