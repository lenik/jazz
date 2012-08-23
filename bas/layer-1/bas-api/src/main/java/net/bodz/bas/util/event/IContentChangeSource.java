package net.bodz.bas.util.event;

public interface IContentChangeSource {

    void addContentChangeListener(IContentChangeListener listener);

    void removeContentChangeListener(IContentChangeListener listener);

}
