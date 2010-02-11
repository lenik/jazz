package net.bodz.bas.fs.path;

public abstract class AbstractPathAnchorPoint
        implements IPathAnchorPoint {

    @Override
    public boolean isChopAnchor() {
        return false;
    }

    @Override
    public boolean isFixedLevel() {
        return false;
    }

    @Override
    public int getFixedLevel() {
        return 0;
    }

}
