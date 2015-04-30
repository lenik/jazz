package net.bodz.bas.ui.layout;

import java.util.Collection;

public abstract class AbstractLayout
        implements ILayout {

    protected int marginTop;
    protected int marginRight;
    protected int marginBottom;
    protected int marginLeft;

    protected int horizontalSpacing;
    protected int verticalSpacing;

    @Override
    public SizeInfo layout(BoundType boundType, int width, int height, Collection<IAutoLayoutWidget> widgets) {
        if (widgets.isEmpty())
            return SizeInfo.size(width, height);

        int marginWidth = marginLeft + marginRight;
        int marginHeight = marginTop + marginBottom;

        int w = width - marginWidth;
        int h = height - marginHeight;

        SizeInfo size = layoutImpl(boundType, marginLeft, marginTop, w, h, widgets);
        size.add(marginWidth, marginHeight);
        return size;
    }

    /**
     * @param widgets
     *            never be empty.
     */
    public abstract SizeInfo layoutImpl(BoundType boundType, int x, int y, int width, int height,
            Collection<IAutoLayoutWidget> widgets);

}
