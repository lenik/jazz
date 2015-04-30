package net.bodz.bas.ui.layout;

import java.awt.Point;
import java.util.Collection;

public class FillLayout
        extends AbstractLayout {

    private boolean vertical;

    public FillLayout(boolean vertical) {
        this.vertical = vertical;
    }

    @Override
    public SizeInfo layoutImpl(BoundType boundType, int x, int y, int width, int height,
            Collection<IAutoLayoutWidget> widgets) {
        int n = widgets.size();
        int sw = width / n;
        int sh = height / n;

        SizeInfo outer = new SizeInfo();
        for (IAutoLayoutWidget widget : widgets) {
            SizeInfo info;
            if (vertical) {
                info = widget.computeSize(width, sh);
                outer.maxWidth(info);
                outer.addHeight(info);
            } else {
                info = widget.computeSize(sw, height);
                outer.maxHeight(info);
                outer.addWidth(info);
            }
        }

        for (IAutoLayoutWidget widget : widgets) {
            SizeInfo info;
            if (vertical) {
                info = widget.computeSize(width, sh);
                info.maxWidth(outer);
            } else {
                info = widget.computeSize(sw, height);
                info.maxHeight(outer);
            }

            Point size = info.getSize(boundType);
            if (boundType != BoundType.NONE)
                widget.setBounds(x, y, size.x, size.y);

            if (vertical)
                y += size.y + verticalSpacing;
            else
                x += size.x + horizontalSpacing;
        }

        if (vertical)
            outer.add(0, (n - 1) * verticalSpacing);
        else
            outer.add((n - 1) * horizontalSpacing, 0);
        return outer;
    }

}
