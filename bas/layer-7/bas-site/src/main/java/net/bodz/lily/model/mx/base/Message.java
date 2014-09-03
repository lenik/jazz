package net.bodz.lily.model.mx.base;

import java.util.List;

import net.bodz.bas.t.order.IPriority;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.lily.model.base.security.User;

public class Message
        extends CoEntity
        implements IPriority {

    private static final long serialVersionUID = 1L;

    int priority;
    String subject;
    String text;

    User sender;
    List<Object> attachments;

    Object emotion;

    @Override
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
