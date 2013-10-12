package net.bodz.swt.viz.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

import net.bodz.bas.err.CreateException;
import net.bodz.swt.viz.IRefEntry_SWT;
import net.bodz.swt.viz.SwtStyleClass;

public class SWTInject {

    public static int styleFx(int styleBits, SwtStyleClass style) {
        if (style != null) {
            styleBits |= SWT.BORDER;
        }
        return styleBits;
    }

    public static int styleFx(int styleBits, IRefEntry_SWT<?> entry) {
        return styleFx(styleBits, entry.getStyle());
    }

    public void inject(Widget widget, SwtStyleClass data)
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

    public void inject(Item item, SwtStyleClass style)
            throws InjectException {
        try {
            Image icon = style.getIcon();
            if (icon != null)
                item.setImage(icon);

            String label = style.getLabel();
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
                Image hot = style.getIcon("hot");
                if (hot != null)
                    toolItem.setHotImage(hot);
                Image disabled = style.getIcon("disabled");
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

    public void inject(Menu menu, SwtStyleClass style) {
        if (style.visible != null)
            menu.setVisible(style.visible);
        if (style.enabled != null)
            menu.setEnabled(style.enabled);
    }

    public void inject(ScrollBar bar, SwtStyleClass style) {
        if (style.visible != null)
            bar.setVisible(style.visible);
        if (style.enabled != null)
            bar.setEnabled(style.enabled);
    }

    public void inject(ToolTip tooltip, SwtStyleClass style) {
        if (style.visible != null)
            tooltip.setVisible(style.visible);
    }

}
