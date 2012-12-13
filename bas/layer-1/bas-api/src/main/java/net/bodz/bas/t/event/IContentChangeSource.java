package net.bodz.bas.t.event;

public interface IContentChangeSource {

    void addContentChangeListener(IContentChangeListener listener);

    void removeContentChangeListener(IContentChangeListener listener);

}
