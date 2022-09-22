package net.bodz.lily.entity.attachment;

import net.bodz.bas.servlet.ctx.AbstractAnchor;
import net.bodz.bas.servlet.ctx.IAnchor;

public class VolumeAnchor
        extends AbstractAnchor {

    @Override
    public boolean isDirectory() {
        return false;
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
