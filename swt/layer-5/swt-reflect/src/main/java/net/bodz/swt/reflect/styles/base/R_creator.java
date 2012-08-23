package net.bodz.swt.reflect.styles.base;

import static net.bodz.swt.nls.GUINLS.GUINLS;

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
import net.bodz.bas.trait.Traits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.ui.RenderException;
import net.bodz.swt.c.control.DynamicControl;
import net.bodz.swt.c.layout.LineLayout;
import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.reflect.SwtEntryMetadata;
import net.bodz.swt.reflect.SwtEntry;
import net.bodz.swt.reflect.SWTRenderContext;
import net.bodz.swt.reflect.SWTRenderer;

public class R_creator
        extends SWTRenderer {

    static String createIcon = "/icons/full/obj16/add_obj.gif";
    static String deleteIcon = "/icons/full/obj16/delete_obj.gif";

    Image getIcon(AnnotatedElement... tries)
            throws CreateException {
        for (AnnotatedElement elm : tries) {
            SwtEntryMetadata hint = SwtEntryMetadata.get(elm);
            if (hint == null)
                continue;
            Image icon = hint.getIcon();
            if (icon == null)
                continue;
        }
        return null;
    }

    public Control renderTextParserCreator(final SWTRenderContext rc, final DynamicControl parent, final int style,
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
                    rc.interact(parent).alert(GUINLS.getString("R_creator.parseFailure"), pe);
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
    public Control render(final SWTRenderContext rc, SwtEntry<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        Menu createMenu = new Menu(parent);
        Class<?> type = var.getMetadata().getType();
        IParser<?> parser;
        try {
            parser = Traits.getTrait(type, IParser.class);
            SwtEntryMetadata hint = SwtEntryMetadata.get(type);
            Image icon = SwtEntryMetadata.get(type).getIcon();

        } catch (CreateException e) {
            throw new IllegalUsageError(GUINLS.getString("R_creator.failedGetParser") + type);
        }
        if (parser != null) {
            MenuItem menuItem = new MenuItem(createMenu, 0);
            menuItem.setText(GUINLS.getString("R_creator.fromText"));
            menuItem.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {

                }
            });
        }

        for (Constructor<?> ctor : type.getConstructors()) {
            SwtEntryMetadata hint = SwtEntryMetadata.get(ctor);
            String label = hint.getLabel();
            if (label == null) {
                String pt = TypeName.join(", ", true, ctor.getParameterTypes());
                label = GUINLS.getString("R_creator.new_") + pt + ")";
            }
        }
        Composite comp = new Composite(parent, style);
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
