package net.bodz.swt.gui;

import java.lang.reflect.AnnotatedElement;

import net.bodz.bas.gui.a.PreferredSize;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.mod.Factory;
import net.bodz.bas.types.util.Ns;
import net.bodz.swt.gui.a.A_gui;
import net.bodz.swt.gui.a.MenuItem;
import net.bodz.swt.gui.a.ToolItem;
import net.bodz.swt.gui.a.View;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

public class GUIHint {

    public Boolean  enabled;
    public Boolean  visible;
    private int     order;
    private int     tabOrder;
    private Factory iconFactory;
    private Factory labelFactory;
    private Factory fontFactory;
    public RGB      color;
    public RGB      backColor;
    public Point    preferredSize;

    private String  menuItem;
    private String  toolItem;
    private String  viewId;

    public GUIHint(GUIHint copy) {
        if (copy == null)
            return;
        this.enabled = copy.enabled;
        this.visible = copy.visible;
        this.order = copy.order;
        this.tabOrder = copy.tabOrder;
        this.iconFactory = copy.iconFactory;
        this.labelFactory = copy.labelFactory;
        this.fontFactory = copy.fontFactory;
        this.color = copy.color;
        this.backColor = copy.backColor;
        this.preferredSize = copy.preferredSize;

        this.menuItem = copy.menuItem;
        this.toolItem = copy.toolItem;
        this.viewId = copy.viewId;
    }

    static String MORE = "MORE";

    static String prep(String id, AnnotatedElement elm) {
        if (id.isEmpty())
            id = MORE + "." + Ns.getName(elm);
        return id;
    }

    public GUIHint(GUIHint copy, AnnotatedElement elm) {
        this(copy);

        Boolean enabled = (Boolean) Ns.getValue(elm,
                net.bodz.bas.gui.a.Enabled.class);
        if (enabled != null)
            this.enabled = enabled;

        Boolean visible = (Boolean) Ns.getValue(elm,
                net.bodz.bas.gui.a.Visible.class);
        if (visible != null)
            this.visible = visible;

        Integer order = (Integer) Ns.getValue(elm,
                net.bodz.bas.gui.a.Order.class);
        if (order != null)
            this.order = order;

        Integer tabOrder = (Integer) Ns.getValue(elm,
                net.bodz.bas.gui.a.TabOrder.class);
        if (tabOrder != null)
            this.tabOrder = tabOrder;

        Factory iconFactory = A_gui.getIconFactory(Ns.getN(elm,
                net.bodz.bas.gui.a.Icon.class));
        if (iconFactory != null)
            this.iconFactory = iconFactory;

        Factory labelFactory = A_gui.getLabelFactory(Ns.getN(elm,
                net.bodz.bas.gui.a.Label.class));
        if (labelFactory != null)
            this.labelFactory = labelFactory;

        Factory fontFactory = A_gui.getFontFactory(Ns.getN(elm,
                net.bodz.bas.gui.a.Font.class));
        if (fontFactory != null)
            this.fontFactory = fontFactory;

        net.bodz.bas.gui.a.Color colorN = Ns.getN(elm,
                net.bodz.bas.gui.a.Color.class);
        if (colorN != null) {
            RGB color = A_gui.parseColor(colorN.value());
            RGB back = A_gui.parseColor(colorN.back());
            if (color != null)
                this.color = color;
            if (back != null)
                this.backColor = back;
        }

        Point preferredSize = A_gui
                .parseSize(Ns.getN(elm, PreferredSize.class));
        if (preferredSize != null)
            this.preferredSize = preferredSize;

        String menuItem = (String) Ns.getValue(elm, MenuItem.class);
        if (menuItem != null)
            this.menuItem = prep(menuItem, elm);

        String toolItem = (String) Ns.getValue(elm, ToolItem.class);
        if (toolItem != null)
            this.toolItem = prep(toolItem, elm);

        String viewId = (String) Ns.getValue(elm, View.class);
        if (viewId != null)
            this.viewId = prep(viewId, elm);
    }

    private Class<? extends Throwable> exceptionHandler;

    public Image getIcon(Object... args) throws CreateException {
        if (iconFactory == null)
            return null;
        Object icon = iconFactory.create(args);
        return SWTResources.getImage(icon);
    }

    public String getLabel(Object... args) {
        if (labelFactory == null)
            return null;
        try {
            return (String) labelFactory.create(args);
        } catch (CreateException e) {
            throw new IllegalUsageError(e);
        }
    }

    public Font getFont(Device device, Object... args) {
        if (fontFactory == null)
            return null;
        try {
            FontData fd = (FontData) fontFactory.create(args);
            return new Font(device, fd);
        } catch (CreateException e) {
            throw new IllegalUsageError(e);
        }
    }

}
