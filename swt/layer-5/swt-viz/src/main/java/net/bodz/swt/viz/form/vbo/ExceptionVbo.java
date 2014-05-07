package net.bodz.swt.viz.form.vbo;

import java.io.IOException;
import java.util.EventObject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;

import net.bodz.bas.c.event.EventHandler;
import net.bodz.bas.c.java.awt.DesktopApps;
import net.bodz.bas.c.system.SystemProperties;
import net.bodz.bas.err.ExpectedException;
import net.bodz.bas.repr.viz.ViewBuilderException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.ui.dialog.IUserDialogs;
import net.bodz.swt.c.composite.EmptyComposite;
import net.bodz.swt.c.composite.FixSizeComposite;
import net.bodz.swt.c.control.Controls;
import net.bodz.swt.c.resources.SWTResources;
import net.bodz.swt.ui.model.AbstractCommand;
import net.bodz.swt.ui.model.ICommand;
import net.bodz.swt.viz.AbstractSwtViewBuilder;
import net.bodz.swt.viz.ISwtControlStyleDeclaration;
import net.bodz.swt.viz.ISwtUiRef;
import net.bodz.swt.viz.SwtRenderContext;

public class ExceptionVbo
        extends AbstractSwtViewBuilder<Throwable> {

    static final String expandedIcon = "/icons/full/obj16/remove_correction.gif";
    static final String collapsedIcon = "/icons/full/obj16/add_correction.gif";

    public static String mailAddress = tr._("errorbox@bodz.net");

    static boolean usingColors = false;
    static boolean showTools = true;
    static boolean showDebug = SystemProperties.isDevelopMode();

    public ExceptionVbo() {
        super(Throwable.class);
    }

    @Override
    public Widget buildSwtView(final Composite parent, ISwtUiRef<Throwable> ref, int styleInt, IOptions options)
            throws ViewBuilderException {
        final ISwtControlStyleDeclaration styleDecl = ref.getStyle();
        final SwtRenderContext rc = options.get(SwtRenderContext.class);

        final Display display = parent == null ? Display.getCurrent() : parent.getDisplay();

        final Composite comp = new Composite(parent, styleInt);
        GridLayout layout = new GridLayout(2, false);
        layout.verticalSpacing = 0;
        comp.setLayout(layout);

        // assert var.getMeta().isReadOnly();
        Color errorColor = new Color(display, 0xCC, 0, 0);
        final Throwable throwable = ref.get();
        int causeLevel = 0;
        int traceSkipped = 0;
        StringBuilder errbuf = new StringBuilder(4000);
        Throwable th = throwable;
        while (th != null) {
            String mesg = String.valueOf(th); // th.getMessage();
            if (mesg == null)
                mesg = tr._("(n/a)");
            if (causeLevel++ != 0)
                mesg = tr._("Caused by: ") + mesg;
            final Label swithcerIcon = new Label(comp, SWT.NONE);
            swithcerIcon.setImage(SWTResources.getImageRes(collapsedIcon));

            final Label mesgLabel = new Label(comp, SWT.NONE);
            mesgLabel.setText(mesg);
            mesgLabel.setForeground(errorColor);
            errbuf.append(mesg + "\n");

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
                Label traceLabel = new Label(callstackComp, SWT.NONE);
                String textline = traceElement.toString();
                traceLabel.setText(textline);
                errbuf.append("    ");
                errbuf.append(textline + "\n");
                if (usingColors) {
                    Color bg = traceLabel.getBackground();
                    int red = Math.min(255, (bg.getRed() * (9 + i % 3)) / 10);
                    int green = Math.min(255, (bg.getGreen() * (9 + i % 3)) / 10);
                    int blue = Math.min(255, (bg.getBlue() * (9 + i % 3)) / 10);
                    bg = new Color(parent.getDisplay(), red, green, blue);
                    traceLabel.setBackground(bg);
                }
            }
            errbuf.append("\n");
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
            Image copyImage = SWTResources.getImageRes("/icons/full/etool16/copy_edit.gif");
            ICommand copyAction = new AbstractCommand(copyImage, tr._("&Copy"),
                    tr._("Copy exception text to clipboard")) {
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

            final Image mailImage = SWTResources.getImageRes("/icons/full/obj16/text_edit.gif");
            final String mailSubject = tr._("Error Report");
            ICommand mailAction = new AbstractCommand(mailImage, //
                    tr._("&Report"), tr._("Send error report to the responsib")) {
                @Override
                public void execute() {
                    try {
                        DesktopApps.openMailer(mailAddress, mailSubject, errorText);
                    } catch (IOException e) {
                        IUserDialogs dialogs = rc.getUserDialogs(parent);
                        dialogs.alert(tr._("Can\'t send mail"), e);
                    }
                }
            };

            rc.addAction(copyAction);
            rc.addAction(mailAction);
        }

        if (showDebug) {
            final Image debugImage = SWTResources.getImageRes("/icons/full/eview16/debug_view.gif");
            ICommand debugAction = new AbstractCommand(debugImage, //
                    tr._("&Debug"), tr._("Go into the source where the exception happens. ")) {
                @Override
                public void execute() {
                    throw new ExpectedException("debug", throwable);
                }
            };
            rc.addAction(debugAction);
        }

        return comp;
    }

}
