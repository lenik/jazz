package net.bodz.swt.viz.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

import net.bodz.bas.err.CreateException;
import net.bodz.swt.viz.IRefEntry_SWT;
import net.bodz.swt.viz.SwtStyleData;

public class SWTInject {

    public static int styleFx(int style, SwtStyleData style) {
        if (style != null) {
            if (style.getBorderWidth()
                style |= SWT.BORDER;
        }
        return style;
    }

    public static int styleFx(int style, IRefEntry_SWT<?> entry) {
        return styleFx(style, entry.getStylesheet());
    }

    public void inject(Widget widget, SwtStyleData data)
            throws InjectException {
        if (widget instanceof Item)
            inject((Item) widget, data);
        else if (widget instanceof Menu)
            inject((Menu) widget, data);
        else if (widget instanceof ScrollBar)
            inject((ScrollBar) widget, data);
        else if (widget instanceof ToolTip)
            inject((ToolTip) widget, data);
    }

    public void inject(Item item, SwtStyleData data)
            throws InjectException {
        try {
            Image icon = data.getIcon();
            if (icon != null)
                item.setImage(icon);

            String label = data.getLabel();
            if (label != null) {
                // toolitem bugfix.
                boolean notext = item instanceof ToolItem && icon != null;
                if (!notext)
                    item.setText(label);
            }

            if (item instanceof MenuItem) {
                // MenuItem menuItem = (MenuItem) item;
                // do nothing

            } else if (item instanceof ToolItem) {
                ToolItem toolItem = (ToolItem) item;
                Image hot = data.getIcon("hot");
                if (hot != null)
                    toolItem.setHotImage(hot);
                Image disabled = data.getIcon("disabled");
                if (disabled != null)
                    toolItem.setDisabledImage(disabled);
                // toolItem.setToolTipText(doc);

            } else if (item instanceof ExpandItem) {
                // ExpandItem expandItem = (ExpandItem) item;
                // Point resize = expandItem.getControl().computeSize(0, 0);
                // expandItem.setHeight(resize.y);
            }

            // bind the item action
        } catch (CreateException e) {
            throw new InjectException(e);
        }
    }

    public void inject(Menu menu, SwtStyleData data) {
        if (data.visible != null)
            menu.setVisible(data.visible);
        if (data.enabled != null)
            menu.setEnabled(data.enabled);
    }

    public void inject(ScrollBar bar, SwtStyleData data) {
        if (data.visible != null)
            bar.setVisible(data.visible);
        if (data.enabled != null)
            bar.setEnabled(data.enabled);
    }

    public void inject(ToolTip tooltip, SwtStyleData data) {
        if (data.visible != null)
            tooltip.setVisible(data.visible);
    }

}
