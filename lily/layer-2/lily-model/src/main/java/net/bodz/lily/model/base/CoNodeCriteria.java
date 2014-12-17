package net.bodz.lily.model.base;

import net.bodz.bas.err.ParseException;

import net.bodz.lily.model.sea.QVariantMap;

public class CoNodeCriteria
        extends CoEntityCriteria {

    Integer depth;

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        super.populate(map);
        depth = map.getInt("depth", depth);
    }

}
