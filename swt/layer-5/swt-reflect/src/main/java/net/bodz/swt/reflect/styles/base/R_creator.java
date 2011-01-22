package net.bodz.swt.reflect.styles.base;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;

import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;
import net.bodz.bas.types.util.Types;
import net.bodz.bas.ui.RenderException;
import net.bodz.swt.gui.GUIHint;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.SWTRenderContext;
import net.bodz.swt.gui.SWTRenderer;
import net.bodz.swt.layouts.LineLayout;
import net.bodz.swt.nls.GUINLS;
import net.bodz.swt.util.SWTResources;
import net.bodz.swt.widgets.DynamicControl;

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

public class R_creator extends SWTRenderer {

    static String createIcon = "/icons/full/obj16/add_obj.gif";   //$NON-NLS-1$
    static String deleteIcon = "/icons/full/obj16/delete_obj.gif"; //$NON-NLS-1$

    Image getIcon(AnnotatedElement... tries) throws CreateException {
        for (AnnotatedElement elm : tries) {
            GUIHint hint = GUIHint.get(elm);
            if (hint == null)
                continue;
            Image icon = hint.getIcon();
            if (icon == null)
                continue;
        }
        return null;
    }

    public Control renderTextParserCreator(final SWTRenderContext rc, final DynamicControl parent,
            final int style, final TypeParser parser) {
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
                    rc.interact(parent).alert(GUINLS.getString("R_creator.parseFailure"), pe); //$NON-NLS-1$
                    text.setFocus();
                }
            }
        });
        return comp;
    }

    public Control renderCtorCreator(final DynamicControl parent, final int style,
            final Constructor<?> ctor, final Object... ctorArgs) {
        ctor.getParameterAnnotations();
        return null;
    }

    @Override
    public Control render(final SWTRenderContext rc, GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        Menu createMenu = new Menu(parent);
        Class<?> type = var.getMeta().getType();
        TypeParser parser;
        try {
            parser = TypeParsers.guess(type);
            GUIHint hint = GUIHint.get(type);
            Image icon = GUIHint.get(type).getIcon();

        } catch (CreateException e) {
            throw new IllegalUsageError(GUINLS.getString("R_creator.failedGetParser") + type); //$NON-NLS-1$
        }
        if (parser != null) {
            MenuItem menuItem = new MenuItem(createMenu, 0);
            menuItem.setText(GUINLS.getString("R_creator.fromText")); //$NON-NLS-1$
            menuItem.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {

                }
            });
        }

        for (Constructor<?> ctor : type.getConstructors()) {
            GUIHint hint = GUIHint.get(ctor);
            String label = hint.getLabel();
            if (label == null) {
                String pt = Types.joinNames(", ", true, ctor //$NON-NLS-1$
                        .getParameterTypes());
                label = GUINLS.getString("R_creator.new_") + pt + ")"; //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
        Composite comp = new Composite(parent, style);
        Button createButton = new Button(comp, SWT.FLAT);
        createButton.setImage(SWTResources.getImageRes("/icons/full/obj16/add_obj.gif")); //$NON-NLS-1$
        createButton.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

            }
        });
        return comp;
    }

}
