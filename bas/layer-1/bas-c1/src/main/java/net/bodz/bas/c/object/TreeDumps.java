package net.bodz.bas.c.object;

import net.bodz.bas.io.BTreeOut;

public class TreeDumps {

    public static String dump(ITreeDump dumpable) {
        return dump(dumpable, TreeDumpFormat.DEFAULT);
    }

    public static String dump(ITreeDump dumpable, TreeDumpFormat format) {
        DefaultTreeDumping context = new DefaultTreeDumping();
        BTreeOut buf = new BTreeOut();
        dumpable.dump(buf, context);
        return buf.toString();
    }

}
