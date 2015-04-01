package net.bodz.lily.model.base.impl;

import net.bodz.bas.site.viz.input.ITagTyper;

import net.bodz.lily.model.base.CoCode;

public class CoCodeTagTyper
        implements ITagTyper<CoCode<?>> {

    @Override
    public String getTagValue(CoCode<?> obj) {
        Integer id = obj.getId();
        if (id != null)
            return id.toString();
        return "new";
    }

    @Override
    public String getTagText(CoCode<?> obj) {
        if (obj == null)
            return "null";

        String label = obj.getLabel();
        if (label != null && !label.isEmpty())
            return label;

        String code = obj.getCode();
        if (code != null)
            return code;

        Integer id = obj.getId();
        if (id != null)
            return id.toString();

        return "new";
    }

}
