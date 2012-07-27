package net.bodz.swt.reflect.util;

import net.bodz.bas.err.CreateException;
import net.bodz.swt.reflect.GUIHint;
import net.bodz.swt.reflect.GUIVar;
import net.bodz.swt.reflect.GUIVarMeta;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.Widget;

public class SWTInject {

    public static int styleFx(int style, GUIHint hint) {
        if (hint != null) {
            if (hint.border != 0)
                style |= SWT.BORDER;
        }
        return style;
    }

    public static int styleFx(int style, GUIVarMeta meta) {
        return styleFx(style, meta.getHint());
    }

    public static int styleFx(int style, GUIVar<?> var) {
        return styleFx(style, var.getMeta().getHint());
    }

    public void inject(Widget widget, GUIHint data)
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

    public void inject(Item item, GUIHint data)
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

    public void inject(Menu menu, GUIHint data) {
        if (data.visible != null)
            menu.setVisible(data.visible);
        if (data.enabled != null)
            menu.setEnabled(data.enabled);
    }

    public void inject(ScrollBar bar, GUIHint data) {
        if (data.visible != null)
            bar.setVisible(data.visible);
        if (data.enabled != null)
            bar.setEnabled(data.enabled);
    }

    public void inject(ToolTip tooltip, GUIHint data) {
        if (data.visible != null)
            tooltip.setVisible(data.visible);
    }

}
