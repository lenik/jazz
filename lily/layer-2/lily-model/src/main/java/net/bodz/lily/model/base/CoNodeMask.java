package net.bodz.lily.model.base;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.variant.IVariantMap;

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
    public void readObject(IVariantMap<String> map)
            throws ParseException {
        super.readObject(map);
        maxDepth = map.getInt("maxdepth", maxDepth);
    }

}
