package net.bodz.swt.viz;

import java.beans.PropertyDescriptor;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import net.bodz.bas.c.reflect.AnnotatedElements;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.gui.mda.*;
import net.bodz.bas.model.IFactory;
import net.bodz.bas.model.meta.MenuContrib;
import net.bodz.bas.model.meta.View;
import net.bodz.mda.xjdoc.conv.ClassDocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.gui.a.ColorAnnotation;
import net.bodz.swt.gui.a.FontAnnotation;
import net.bodz.swt.gui.a.IconAnnotation;
import net.bodz.swt.gui.a.LabelAnnotation;

public class SwtStylesheet {

    public String doc;

    public int order;
    public Boolean enabled;
    public Boolean visible;
    public int tabOrder;
    private IFactory<?> iconFactory;
    private IFactory<String> labelFactory;
    private IFactory<?> fontFactory;
    public RGB color;
    public RGB backColor;
    public Point preferredSize;
    public int border;

    private String menuItem;
    private String toolItem;
    private String viewId;

    public SwtStylesheet() {
    }

    public SwtStylesheet(SwtStylesheet copy) {
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
            id = MORE + "." + AnnotatedElements.getName(elm);
        return id;
    }

    public SwtStylesheet(SwtStylesheet copy, AnnotatedElement aobject) {
        this(copy);

        ClassDoc classDoc = ClassDocs.loadFromResource(aobject.getClass());
        this.doc = classDoc.getText().toString();

        Visible _visible = aobject.getAnnotation(Visible.class);
        if (_visible != null)
            this.visible = _visible.value();

        Order _order = aobject.getAnnotation(Order.class);
        if (_order != null)
            this.order = _order.value();

        TabOrder _tabOrder = aobject.getAnnotation(TabOrder.class);
        if (_tabOrder != null)
            this.tabOrder = _tabOrder.value();

        Icon _icon = aobject.getAnnotation(Icon.class);
        if (_icon != null)
            this.iconFactory = IconAnnotation.getIconFactory(_icon.value(), _icon.factory());

        Label _label = aobject.getAnnotation(Label.class);
        if (_label != null)
            this.labelFactory = LabelAnnotation.getLabelFactory(_label.value(), _label.factory());

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

        PreferredSize _preferredSize = aobject.getAnnotation(PreferredSize.class);
        if (_preferredSize != null)
            this.preferredSize = new Point(_preferredSize.width(), _preferredSize.height());

        Border _border = aobject.getAnnotation(Border.class);
        if (_border != null)
            this.border = _border.value();

        MenuContrib _menu = aobject.getAnnotation(MenuContrib.class);
        if (_menu != null)
            this.menuItem = prep(menuItem, aobject);

        View _view = aobject.getAnnotation(View.class);
        if (_view != null)
            this.viewId = prep(viewId, aobject);
    }

    protected SwtStylesheet(AnnotatedElement elm) {
        this(null, elm);
    }

    public static SwtStylesheet get(AnnotatedElement elm) {
        // parent...
        return new SwtStylesheet(elm);
    }

    public static SwtStylesheet get(PropertyDescriptor property) {
        Method readf = property.getReadMethod();
        Method writef = property.getWriteMethod();
        SwtStylesheet hint = null;
        if (readf != null)
            hint = new SwtStylesheet(hint, readf);
        if (writef != null)
            hint = new SwtStylesheet(hint, writef);
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