package net.bodz.swt.viz.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

import net.bodz.bas.err.CreateException;
import net.bodz.swt.viz.IRefEntry_SWT;
import net.bodz.swt.viz.SwtVizStyleClass;

public class SwtInject {

    public static int styleFx(int styleBits, SwtVizStyleClass style) {
        if (style != null) {
            styleBits |= SWT.BORDER;
        }
        return styleBits;
    }

    public static int styleFx(int styleBits, IRefEntry_SWT<?> entry) {
        return styleFx(styleBits, entry.getStyle());
    }

    public void inject(Widget widget, SwtVizStyleClass data)
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

    public void inject(Item item, SwtVizStyleClass style)
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

    public void inject(Menu menu, SwtVizStyleClass style) {
        if (style.getVisibility() != null)
            switch (style.getVisibility()) {
            case visible:
                menu.setVisible(true);
                break;
            case hidden:
                menu.setVisible(false);
                break;
            default:
            }

        if (style.getEnabled() != null)
            menu.setEnabled(style.getEnabled());
    }

    public void inject(ScrollBar bar, SwtVizStyleClass style) {
        if (style.getVisibility() != null)
            switch (style.getVisibility()) {
            case visible:
                bar.setVisible(true);
                break;
            case hidden:
                bar.setVisible(false);
                break;
            default:
            }

        if (style.getEnabled() != null)
            bar.setEnabled(style.getEnabled());
    }

    public void inject(ToolTip tooltip, SwtVizStyleClass style) {
        if (style.getVisibility() != null)
            switch (style.getVisibility()) {
            case visible:
                tooltip.setVisible(true);
                break;
            case hidden:
                tooltip.setVisible(false);
                break;
            case collapse:
            }
    }

}
