package net.bodz.bas.ui.layout;

public interface IAutoLayoutWidget {

    <T> T getLayoutData(Class<T> layoutDataClass);

    SizeInfo computeSize(int wHint, int hHint);

    void setBounds(int x, int y, int width, int height);

}
