package net.bodz.bas.ui.layout;

import java.util.Collection;

public interface ILayout {

    SizeInfo layout(BoundType boundType, int width, int height, Collection<IAutoLayoutWidget> widgets);

}
