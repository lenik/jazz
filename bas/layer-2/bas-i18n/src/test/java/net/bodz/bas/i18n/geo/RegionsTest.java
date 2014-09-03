package net.bodz.bas.i18n.geo;

import org.junit.Assert;

import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;

public class RegionsTest
        extends Assert {

    static void dump(ITreeOut out, Region r) {
        out.println(r.joinId() + " - " + r.getLocaleName());
        out.enter();
        for (Region child : r.getChildren())
            dump(out, child);
        out.leave();
    }

    public static void main(String[] args) {
        ITreeOut out = TreeOutImpl.from(Stdio.cout);
        dump(out, Regions.getChina());
    }

}
