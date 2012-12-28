package net.bodz.swt.viz.form.vbo;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

import net.bodz.bas.c.type.TypeName;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.IllegalUsageError;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.gui.viz.ViewBuilderException;
import net.bodz.bas.potato.ref.IRefEntry;
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IParser;
import net.bodz.swt.c.control.DynamicControl;
import net.bodz.swt.c.layout.LineLayout;
import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.viz.SwtRenderContext;
import net.bodz.swt.viz.SwtViewBuilder;
import net.bodz.swt.viz.MappedSwtVizStyleClass;

public class CreatorVbo
        extends SwtViewBuilder {

    static String createIcon = "/icons/full/obj16/add_obj.gif";
    static String deleteIcon = "/icons/full/obj16/delete_obj.gif";

    Image getIcon(AnnotatedElement... tries)
            throws CreateException {
        for (AnnotatedElement elm : tries) {
            MappedSwtVizStyleClass style = MappedSwtVizStyleClass.get(elm);
            if (style == null)
                continue;
            Image icon = style.getIcon();
            if (icon == null)
                continue;
        }
        return null;
    }

    public Control renderTextParserCreator(final SwtRenderContext rc, final DynamicControl parent, final int style,
            final IParser<?> parser) {
        Composite comp = new Composite(parent, style);
        comp.setLayout(new LineLayout());
        final Text text = new Text(comp, SWT.BORDER);
        text.setLayoutData(LineLayout.EXPAND);
        Button parseButton = new Button(comp, SWT.NONE);
        parseButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                String s = text.getText();
                try {
                    Object obj = parser.parse(s);
                    parent.clear();
                    // rerenderObject(obj);
                } catch (ParseException pe) {
                    rc.getUserDialogs(parent).alert(tr._("Parse Failure"), pe);
                    text.setFocus();
                }
            }
        });
        return comp;
    }

    public Control renderCtorCreator(final DynamicControl parent, final int style, final Constructor<?> ctor,
            final Object... ctorArgs) {
        ctor.getParameterAnnotations();
        return null;
    }

    @Override
    public Control buildView(final SwtRenderContext rc, IRefEntry<?> entry, MappedSwtVizStyleClass styleClass, Composite parent,
            int _style)
            throws ViewBuilderException, SWTException {

        Menu createMenu = new Menu(parent);
        Class<?> type = entry.getValueType();
        IParser<?> parser;
        try {
            parser = Traits.getTrait(type, IParser.class);
            MappedSwtVizStyleClass style = MappedSwtVizStyleClass.get(type);
            Image icon = MappedSwtVizStyleClass.get(type).getIcon();

        } catch (CreateException e) {
            throw new IllegalUsageError(tr._("failed to get parser for ") + type);
        }
        if (parser != null) {
            MenuItem menuItem = new MenuItem(createMenu, 0);
            menuItem.setText(tr._("From Text"));
            menuItem.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {

                }
            });
        }

        for (Constructor<?> ctor : type.getConstructors()) {
            MappedSwtVizStyleClass style = MappedSwtVizStyleClass.get(ctor);
            String label = style.getLabel();
            if (label == null) {
                String pt = TypeName.join(", ", true, ctor.getParameterTypes());
                label = tr._("new(") + pt + ")";
            }
        }
        Composite comp = new Composite(parent, _style);
        Button createButton = new Button(comp, SWT.FLAT);
        createButton.setImage(SWTResources.getImageRes("/icons/full/obj16/add_obj.gif"));
        createButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

            }
        });
        return comp;
    }

}
