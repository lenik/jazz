package net.bodz.bas.util.event;

import java.util.EventObject;

import net.bodz.bas.util.Ref;

public class ContentChangeEvent
        extends EventObject {

    private static final long serialVersionUID = 1L;

    private final Object oldContent;
    private final Object newContent;

    public ContentChangeEvent(Ref<?> source, Object oldContent, Object newContent) {
        super(source);
        this.oldContent = oldContent;
        this.newContent = newContent;
    }

    public Object getOldValue() {
        return oldContent;
    }

    public Object getNewValue() {
        return newContent;
    }

    @Override
    public String toString() {
        return String.format("ContentChange source=%s old=%s new=%d", getSource(), oldContent, newContent);
    }

}
