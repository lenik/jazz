package net.bodz.lily.storage;

import net.bodz.bas.servlet.ctx.AbstractAnchor;
import net.bodz.bas.servlet.ctx.IAnchor;

public class VolumeAnchor
        extends AbstractAnchor {

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public IAnchor enter() {
        return null;
    }

    @Override
    public String absoluteHref() {
        return null;
    }

    @Override
    public IAnchor join(String spec) {
        return null;
    }

}
