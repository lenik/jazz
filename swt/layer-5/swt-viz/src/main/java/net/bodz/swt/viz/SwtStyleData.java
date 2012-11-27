package net.bodz.swt.viz;

import java.beans.PropertyDescriptor;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

import javax.swing.Icon;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;

import net.bodz.bas.c.reflect.AnnotatedElements;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.gui.xjdoc.Order;
import net.bodz.bas.gui.xjdoc.StyleData;
import net.bodz.bas.model.meta.MenuContrib;
import net.bodz.bas.model.meta.View;
import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.gui.a.ColorAnnotation;
import net.bodz.swt.gui.a.FontAnnotation;
import net.bodz.swt.gui.a.IconAnnotation;

public class SwtStyleData
        extends StyleData {

    public RGB color;
    public RGB backColor;

    private String menuItem;
    private String toolItem;
    private String viewId;

    public SwtStyleData() {
    }

    public SwtStyleData(SwtStyleData other) {
        if (other == null)
            return;
        this.doc = other.doc;

        this.iconFactory = other.iconFactory;
        this.labelFactory = other.labelFactory;
        this.fontFactory = other.fontFactory;
        this.enabled = other.enabled;
        this.visible = other.visible;
        this.order = other.order;
        this.tabOrder = other.tabOrder;
        this.color = other.color;
        this.backColor = other.backColor;
        this.preferredSize = other.preferredSize;
        this.border = other.border;

        this.menuItem = other.menuItem;
        this.toolItem = other.toolItem;
        this.viewId = other.viewId;
    }

    static String MORE = "MORE";

    static String prep(String id, AnnotatedElement elm) {
        if (id.isEmpty())
            id = MORE + "." + AnnotatedElements.getName(elm);
        return id;
    }

    public SwtStyleData(SwtStyleData copy, AnnotatedElement aobject) {
        this(copy);

        ClassDoc classDoc = ClassDocs.loadFromResource(aobject.getClass());
        this.doc = classDoc.getText().toString();

        Order _order = aobject.getAnnotation(Order.class);
        if (_order != null)
            this.order = _order.value();

        Icon _icon = aobject.getAnnotation(Icon.class);
        if (_icon != null)
            this.iconFactory = IconAnnotation.getIconFactory(_icon.value(), _icon.factory());

        Font _font = aobject.getAnnotation(Font.class);
        if (_font != null)
            this.fontFactory = FontAnnotation.getFontFactory(_font);

        Color _color = aobject.getAnnotation(Color.class);
        if (_color != null) {
            RGB fore = ColorAnnotation.parseColor(_color.value());
            RGB back = ColorAnnotation.parseColor(_color.back());
            if (fore != null)
                this.color = fore;
            if (back != null)
                this.backColor = back;
        }

        MenuContrib _menu = aobject.getAnnotation(MenuContrib.class);
        if (_menu != null)
            this.menuItem = prep(menuItem, aobject);

        View _view = aobject.getAnnotation(View.class);
        if (_view != null)
            this.viewId = prep(viewId, aobject);
    }

    protected SwtStyleData(AnnotatedElement elm) {
        this(null, elm);
    }

    public static SwtStyleData get(AnnotatedElement elm) {
        // parent...
        return new SwtStyleData(elm);
    }

    public static SwtStyleData get(PropertyDescriptor property) {
        Method readf = property.getReadMethod();
        Method writef = property.getWriteMethod();
        SwtStyleData hint = null;
        if (readf != null)
            hint = new SwtStyleData(hint, readf);
        if (writef != null)
            hint = new SwtStyleData(hint, writef);
        return hint;
    }

    public Image getIcon(Object... args)
            throws CreateException {
        if (iconFactory == null)
            return null;
        Object icon = iconFactory.create(args);
        return SWTResources._castImage(icon);
    }

    public String getLabel(Object... args) {
        if (labelFactory == null)
            return null;
        try {
            return labelFactory.create(args);
        } catch (CreateException e) {
            throw new IllegalUsageError(e);
        }
    }

    public org.eclipse.swt.graphics.Font getFont(Device device, Object... args) {
        if (fontFactory == null)
            return null;
        try {
            FontData fd = (FontData) fontFactory.create(args);
            return new org.eclipse.swt.graphics.Font(device, fd);
        } catch (CreateException e) {
            throw new IllegalUsageError(e);
        }
    }

}
