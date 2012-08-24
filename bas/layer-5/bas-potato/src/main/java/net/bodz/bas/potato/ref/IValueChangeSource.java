package net.bodz.bas.potato.ref;

public interface IValueChangeSource {

    void addValueChangeListener(IValueChangeListener listener);

    void removeValueChangeListener(IValueChangeListener listener);

}
