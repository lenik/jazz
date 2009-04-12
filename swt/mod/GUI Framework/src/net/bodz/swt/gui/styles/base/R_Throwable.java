package net.bodz.swt.gui.styles.base;

import java.util.EventObject;

import net.bodz.bas.gui.RenderException;
import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.swt.controls.helper.EmptyComposite;
import net.bodz.swt.controls.helper.FixSizeComposite;
import net.bodz.swt.controls.util.Controls;
import net.bodz.swt.gui.GUIVar;
import net.bodz.swt.gui.RenderContext;
import net.bodz.swt.gui.SWTRenderer;
import net.bodz.swt.nls.GUINLS;
import net.bodz.swt.util.EventHandler;
import net.bodz.swt.util.SWTResources;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

/**
 * @test R_ThrowableTest
 */
public class R_Throwable extends SWTRenderer {

    static final String expandedIcon  = "/icons/full/obj16/remove_correction.gif"; //$NON-NLS-1$
    static final String collapsedIcon = "/icons/full/obj16/add_correction.gif";   //$NON-NLS-1$

    static boolean      usingColors   = false;
    static boolean      showTools     = false;

    public R_Throwable(RenderContext rc) {
        super(rc);
    }

    @Override
    public Control render(GUIVar<?> var, Composite parent, int style)
            throws RenderException, SWTException {
        final Display display = parent.getDisplay();
        final Composite comp = new Composite(parent, style);
        GridLayout layout = new GridLayout(2, false);
        layout.verticalSpacing = 0;
        comp.setLayout(layout);
        // assert var.getMeta().isReadOnly();
        Color errorColor = new Color(display, 0xCC, 0, 0);
        Throwable th = (Throwable) var.get();
        int causeLevel = 0;
        int traceSkipped = 0;
        BCharOut errbuf = new BCharOut(4000);
        while (th != null) {
            String mesg = String.valueOf(th); // th.getMessage();
            if (mesg == null)
                mesg = GUINLS.getString("R_Throwable.n_a"); //$NON-NLS-1$
            if (causeLevel++ != 0)
                mesg = GUINLS.getString("R_Throwable.causedBy") + mesg; //$NON-NLS-1$
            final Label swithcerIcon = new Label(comp, SWT.NONE);
            swithcerIcon.setImage(SWTResources.getImageRes(collapsedIcon));

            final Label mesgLabel = new Label(comp, SWT.NONE);
            mesgLabel.setText(mesg);
            mesgLabel.setForeground(errorColor);
            errbuf.println(mesg);

            // Empty skip =
            new EmptyComposite(comp, SWT.NONE);

            final FixSizeComposite callstackComp = new FixSizeComposite(comp,
                    SWT.NONE);
            GridLayout gridLayout = new GridLayout(1, false);
            gridLayout.marginHeight = 0;
            gridLayout.marginBottom = 2;
            gridLayout.verticalSpacing = 1;
            callstackComp.setLayout(gridLayout);
            // stackComp.setLayoutData(//
            // new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
            StackTraceElement[] stackTrace = th.getStackTrace();
            for (int i = stackTrace.length - 1 - traceSkipped; i >= 0; i--) {
                StackTraceElement traceElement = stackTrace[i];
                Label entry = new Label(callstackComp, SWT.NONE);
                String textline = traceElement.toString();
                entry.setText(textline);
                errbuf.print("    "); //$NON-NLS-1$
                errbuf.println(textline);
                if (usingColors) {
                    Color bg = entry.getBackground();
                    int red = Math.min(255, (bg.getRed() * (9 + i % 3)) / 10);
                    int green = Math.min(255,
                            (bg.getGreen() * (9 + i % 3)) / 10);
                    int blue = Math.min(255, (bg.getBlue() * (9 + i % 3)) / 10);
                    bg = new Color(parent.getDisplay(), red, green, blue);
                    entry.setBackground(bg);
                }
            }
            errbuf.println();
            traceSkipped = stackTrace.length;
            callstackComp.setFixedSize(0, 0);

            final EventHandler switcher = new EventHandler() {
                private boolean expanded;

                @Override
                public void handle(EventObject e) {
                    if (expanded) {
                        callstackComp.setFixedSize(0, 0);
                    } else {
                        callstackComp.unsetFixedSize();
                    }
                    expanded = !expanded;
                    String icon = expanded ? expandedIcon : collapsedIcon;
                    swithcerIcon.setImage(SWTResources.getImageRes(icon));
                    Controls.resizeToPreferredSize(comp.getShell());
                }
            };
            swithcerIcon.addMouseListener(new MouseAdapter() {
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

        if (showTools) {
            Composite tools = new Composite(comp, SWT.NONE);
            GridData gd_tools = new GridData();
            gd_tools.horizontalAlignment = GridData.END;
            gd_tools.horizontalSpan = 2;
            tools.setLayoutData(gd_tools);
            tools.setLayout(new RowLayout());

            final String mailBody = errbuf.toString();

            Button copyButton = new Button(tools, SWT.NONE);
            copyButton.setText(GUINLS.getString("R_Throwable.copy")); //$NON-NLS-1$
            copyButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    Clipboard clipboard = new Clipboard(display);
                    clipboard.clearContents();
                    Object[] data = { mailBody };
                    Transfer[] dataTypes = { TextTransfer.getInstance() };
                    clipboard.setContents(data, dataTypes);
                    clipboard.dispose();
                }
            });
            Button mailButton = new Button(tools, SWT.NONE);
            mailButton.setText(GUINLS.getString("R_Throwable.report")); //$NON-NLS-1$
            mailButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    // JavaMail...
                }
            });
        }
        return comp;
    }

}
