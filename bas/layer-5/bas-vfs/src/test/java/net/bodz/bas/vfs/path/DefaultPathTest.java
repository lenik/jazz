package net.bodz.bas.vfs.path;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.vfs.RelativePath;
import net.bodz.bas.vfs.path.align.IPathAlignment;
import net.bodz.bas.vfs.path.align.ParentAlignment;

public class DefaultPathTest
        extends Assert {

    static IPathAlignment alignUp0 = new ParentAlignment(0);
    static IPathAlignment alignUp1 = new ParentAlignment(0);
    static IPathAlignment alignUp2 = new ParentAlignment(0);

    @Test
    public void testRelativeDifferentAlign() {
        RelativePath path1 = new RelativePath("a/b", alignUp0);
        System.out.println(path1);
    }

}
