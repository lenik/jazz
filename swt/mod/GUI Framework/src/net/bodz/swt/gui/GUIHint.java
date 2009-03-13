package net.bodz.swt.gui;

import java.beans.PropertyDescriptor;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

import net.bodz.bas.a.A_bas;
import net.bodz.bas.a.Doc;
import net.bodz.bas.gui.a.Border;
import net.bodz.bas.gui.a.PreferredSize;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.mod.Factory;
import net.bodz.bas.types.util.Ns;
import net.bodz.swt.gui.a.A_gui;
import net.bodz.swt.gui.a.MenuContrib;
import net.bodz.swt.gui.a.View;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

public class GUIHint {

    public String           doc;

    public int              order;
    public Boolean          enabled;
    public Boolean          visible;
    public int              tabOrder;
    private Factory<?>      iconFactory;
    private Factory<String> labelFactory;
    private Factory<?>      fontFactory;
    public RGB              color;
    public RGB              backColor;
    public Point            preferredSize;
    public int              border;

    private String          menuItem;
    private String          toolItem;
    private String          viewId;

    public GUIHint(GUIHint copy) {
        if (copy == null)
            return;
        this.doc = copy.doc;

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
        this.border = copy.border;

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
        this.doc = A_bas.parse(Ns.getN(elm, Doc.class));

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

        Factory<?> iconFactory = A_gui.getIconFactory(Ns.getN(elm,
                net.bodz.bas.gui.a.Icon.class));
        if (iconFactory != null)
            this.iconFactory = iconFactory;

        Factory<String> labelFactory = A_gui.getLabelFactory(Ns.getN(elm,
                net.bodz.bas.gui.a.Label.class));
        if (labelFactory != null)
            this.labelFactory = labelFactory;

        Factory<?> fontFactory = A_gui.getFontFactory(Ns.getN(elm,
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

        Integer border = (Integer) Ns.getValue(elm, Border.class);
        if (border != null)
            this.border = border;

        String menuItem = (String) Ns.getValue(elm, MenuContrib.class);
        if (menuItem != null)
            this.menuItem = prep(menuItem, elm);

        String viewId = (String) Ns.getValue(elm, View.class);
        if (viewId != null)
            this.viewId = prep(viewId, elm);
    }

    protected GUIHint(AnnotatedElement elm) {
        this(null, elm);
    }

    public static GUIHint get(AnnotatedElement elm) {
        // parent...
        return new GUIHint(elm);
    }

    public static GUIHint get(PropertyDescriptor property) {
        Method readf = property.getReadMethod();
        Method writef = property.getWriteMethod();
        GUIHint hint = null;
        if (readf != null)
            hint = new GUIHint(hint, readf);
        if (writef != null)
            hint = new GUIHint(hint, writef);
        return hint;
    }

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
