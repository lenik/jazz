package net.bodz.swt.reflect.styles.base;

import java.beans.EventHandler;
import java.io.IOException;
import java.util.EventObject;

import net.bodz.bas.c.java.awt.DesktopApps;
import net.bodz.bas.c.system.SystemProperties;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.ui.RenderException;
import net.bodz.swt.reflect.GUIVar;
import net.bodz.swt.reflect.IAction;
import net.bodz.swt.reflect.SWTRenderContext;
import net.bodz.swt.reflect.SWTRenderer;
import net.bodz.swt.reflect._Action;
import net.bodz.swt.reflect.nls.GUINLS;
import net.bodz.swt.reflect.util.DialogUI;
import net.bodz.swt.util.SWTResources;
import net.bodz.swt.widgets.util.Controls;
import net.bodz.swt.widgets.util.EmptyComposite;
import net.bodz.swt.widgets.util.FixSizeComposite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

/**
 * @test R_ThrowableTest
 */
public class R_Throwable
        extends SWTRenderer {

    static final String expandedIcon = "/icons/full/obj16/remove_correction.gif"; //$NON-NLS-1$
    static final String collapsedIcon = "/icons/full/obj16/add_correction.gif"; //$NON-NLS-1$

    public static String mailAddress = GUINLS.getString("R_Throwable.mailAddress"); //$NON-NLS-1$

    static boolean usingColors = false;
    static boolean showTools = true;
    static boolean showDebug = SystemProperties.isDevelopMode();

    @Override
    public Control render(final SWTRenderContext rc, final GUIVar<?> var, final Composite parent, final int style)
            throws RenderException, SWTException {
        final Display display = parent == null ? Display.getCurrent() //
                : parent.getDisplay();
        final Composite comp = new Composite(parent, style);
        GridLayout layout = new GridLayout(2, false);
        layout.verticalSpacing = 0;
        comp.setLayout(layout);
        // assert var.getMeta().isReadOnly();
        Color errorColor = new Color(display, 0xCC, 0, 0);
        final Throwable throwable = (Throwable) var.get();
        int causeLevel = 0;
        int traceSkipped = 0;
        BCharOut errbuf = new BCharOut(4000);
        Throwable th = throwable;
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

            final FixSizeComposite callstackComp = new FixSizeComposite(comp, SWT.NONE);
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
                    int green = Math.min(255, (bg.getGreen() * (9 + i % 3)) / 10);
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
            final String errorText = errbuf.toString();
            Image copyImage = SWTResources.getImageRes("/icons/full/etool16/copy_edit.gif"); //$NON-NLS-1$
            IAction copyAction = new _Action(copyImage,
                    GUINLS.getString("R_Throwable.copy"), GUINLS.getString("R_Throwable.copy.doc")) {//$NON-NLS-1$ //$NON-NLS-2$
                @Override
                public void execute() {
                    Clipboard clipboard = new Clipboard(display);
                    clipboard.clearContents();
                    Object[] data = { errorText };
                    Transfer[] dataTypes = { TextTransfer.getInstance() };
                    clipboard.setContents(data, dataTypes);
                    clipboard.dispose();
                }
            };

            final Image mailImage = SWTResources.getImageRes("/icons/full/obj16/text_edit.gif"); //$NON-NLS-1$
            final String mailSubject = GUINLS.getString("R_Throwable.errorReport"); //$NON-NLS-1$
            IAction mailAction = new _Action(mailImage, //
                    GUINLS.getString("R_Throwable.report"), //$NON-NLS-1$
                    GUINLS.getString("R_Throwable.sendMail")) {//$NON-NLS-1$
                @Override
                public void execute() {
                    try {
                        DesktopApps.openMailer(mailAddress, mailSubject, errorText);
                    } catch (IOException e) {
                        DialogUI iact = rc.interact(parent);
                        iact.alert(GUINLS.getString("R_Throwable.cantSend"), e); //$NON-NLS-1$
                    }
                }
            };

            rc.addAction(copyAction);
            rc.addAction(mailAction);
        }

        if (showDebug) {
            final Image debugImage = SWTResources.getImageRes("/icons/full/eview16/debug_view.gif"); //$NON-NLS-1$
            IAction debugAction = new _Action(debugImage, //
                    GUINLS.getString("R_Throwable.debug"), //$NON-NLS-1$
                    GUINLS.getString("R_Throwable.debug.doc")) {//$NON-NLS-1$
                @Override
                public void execute() {
                    throw new ExpectedException("debug", throwable); //$NON-NLS-1$
                }
            };
            rc.addAction(debugAction);
        }

        return comp;
    }

}
