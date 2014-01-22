package net.bodz.lily.model.base;

import java.util.List;

import net.bodz.bas.t.order.IPriority;
import net.bodz.lily.type.ManagedEntity;

public class Message
        extends ManagedEntity
        implements IPriority {

    private static final long serialVersionUID = 1L;

    String subject;
    String text;

    User sender;
    List<Object> attachments;

    int priority;
    Object emotion;

    @Override
    public int getPriority() {
        return priority;
    }

}
