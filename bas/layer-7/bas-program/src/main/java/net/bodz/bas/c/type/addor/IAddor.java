package net.bodz.bas.c.type.addor;

public interface IAddor {

    Object add(Object prev, Object item);

    ReplaceAddor REPLACE = new ReplaceAddor();

}

class ReplaceAddor
        implements IAddor {

    @Override
    public Object add(Object prev, Object item) {
        return item;
    }

}
