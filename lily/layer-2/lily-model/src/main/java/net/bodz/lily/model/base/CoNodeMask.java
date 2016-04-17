package net.bodz.lily.model.base;

import net.bodz.bas.err.ParseException;
import net.bodz.lily.model.sea.QVariantMap;

public class CoNodeMask
        extends CoObjectMask {

    public Integer maxDepth;

    public Integer getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(Integer maxDepth) {
        this.maxDepth = maxDepth;
    }

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        super.populate(map);
        maxDepth = map.getInt("maxdepth", maxDepth);
    }

}
