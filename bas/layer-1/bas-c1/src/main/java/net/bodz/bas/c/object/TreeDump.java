package net.bodz.bas.c.object;

import net.bodz.bas.sio.BTreeOut;

public class TreeDump {

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
