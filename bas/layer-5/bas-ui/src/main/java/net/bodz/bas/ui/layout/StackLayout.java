package net.bodz.bas.ui.layout;

import java.awt.Point;
import java.util.Collection;

public class StackLayout
        extends AbstractLayout {

    @Override
    public SizeInfo layoutImpl(BoundType boundType, int x, int y, int width, int height,
            Collection<IAutoLayoutWidget> widgets) {
        SizeInfo max = new SizeInfo();
        for (IAutoLayoutWidget widget : widgets) {
            SizeInfo info = widget.computeSize(width, height);
            max.max(info);
        }

        if (boundType != BoundType.NONE) {
            Point size = max.getSize(boundType);
            for (IAutoLayoutWidget widget : widgets)
                widget.setBounds(x, y, size.x, size.y);
        }

        return max;
    }

}
