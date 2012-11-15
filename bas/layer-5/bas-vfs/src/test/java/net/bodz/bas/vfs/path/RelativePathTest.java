package net.bodz.bas.vfs.path;

import org.junit.Assert;
import org.junit.Test;

public class RelativePathTest
        extends Assert {

    @Test
    public void parseParent0() {
        RelativePath path = RelativePath.parse(0, "foo/bar");
        System.out.println(path);
    }

    @Test
    public void parseParent1() {
        RelativePath path = RelativePath.parse(1, "foo/bar");
        System.out.println(path);
    }

    @Test
    public void parseParent3() {
        RelativePath path = RelativePath.parse(3, "foo/bar");
        System.out.println(path);
    }

}
