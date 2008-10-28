package net.bodz.swt.gui.styles.base;

import java.util.EventObject;

import net.bodz.bas.gui.RenderException;
import net.bodz.swt.controls.helper.Empty;
import net.bodz.swt.controls.helper.FixSizeComposite;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.RenderContext;
import net.bodz.swt.gui.SWTRenderer;
import net.bodz.swt.util.EventHandler;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class R_Throwable extends SWTRenderer {

    public R_Throwable(RenderContext rc) {
        super(rc);
    }

    static final String expandedIcon  = "/com/sun/java/swing/plaf/motif/icons/ScrollDownArrow.gif";
    static final String collapsedIcon = "/com/sun/java/swing/plaf/motif/icons/ScrollRightArrow.gif";

    @Override
    public Control render(GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        final Composite comp = new Composite(parent, style);
        GridLayout layout = new GridLayout(2, false);
        layout.verticalSpacing = 0;
        comp.setLayout(layout);
        // assert var.getMeta().isReadOnly();
        Color red = new Color(parent.getDisplay(), 0xCC, 0, 0);
        Throwable th = (Throwable) var.get();
        int causeLevel = 0;
        while (th != null) {
            String mesg = String.valueOf(th); // th.getMessage();
            if (mesg == null)
                mesg = "(n/a)";
            if (causeLevel++ != 0)
                mesg = "Caused by: " + mesg;
            final Label stateIcon = new Label(comp, SWT.NONE);
            stateIcon.setImage(SWTResources.getImageRes(collapsedIcon));

            final Label mesgLabel = new Label(comp, SWT.NONE);
            mesgLabel.setText(mesg);
            mesgLabel.setForeground(red);

            // Empty skip =
            new Empty(comp, SWT.NONE);

            final FixSizeComposite stackComp = new FixSizeComposite(comp,
                    SWT.NONE);
            GridLayout stackLayout = new GridLayout(1, false);
            stackLayout.marginHeight = 0;
            stackLayout.marginBottom = 2;
            stackLayout.verticalSpacing = 0;
            stackComp.setLayout(stackLayout);
            // stackComp.setLayoutData(//
            // new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
            StackTraceElement[] stackTrace = th.getStackTrace();
            for (int i = stackTrace.length - 1; i >= 0; i--) {
                StackTraceElement trace = stackTrace[i];
                Label entry = new Label(stackComp, SWT.NONE);
                entry.setText(trace.toString());
            }
            stackComp.setFixedSize(0, 0);

            final EventHandler switcher = new EventHandler() {
                private boolean expanded;

                @Override
                public void handle(EventObject e) {
                    if (expanded) {
                        stackComp.setFixedSize(0, 0);
                    } else {
                        stackComp.unsetFixedSize();
                    }
                    expanded = !expanded;
                    String icon = expanded ? expandedIcon : collapsedIcon;
                    stateIcon.setImage(SWTResources.getImageRes(icon));
                    comp.layout();
                }
            };
            stateIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseDown(MouseEvent e) {
                    switcher.handle(e);
                }
            });
            mesgLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseDown(MouseEvent e) {
                    switcher.handle(e);
                }
            });

            th = th.getCause();
        }
        return comp;
    }
}
