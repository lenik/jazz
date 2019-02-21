package net.bodz.bas.i18n.geo;

import org.junit.Assert;

import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.impl.TreeOutImpl;

public class GeoRegionsTest
        extends Assert {

    static void dump(ITreeOut out, GeoZone r) {
        out.println(r.buildId() + " - " + r.getLocaleName());
        out.enter();
        for (GeoZone child : r.getChildren())
            dump(out, child);
        out.leave();
    }

    public static void main(String[] args) {
        ITreeOut out = TreeOutImpl.from(Stdio.cout);
        dump(out, GeoZones.getChina());
    }

}
