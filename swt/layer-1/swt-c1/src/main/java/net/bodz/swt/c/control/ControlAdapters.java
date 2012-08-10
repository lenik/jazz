package net.bodz.swt.c.control;

import java.awt.Desktop;
import java.net.URI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import net.bodz.bas.lang.fn.Pred1;
import net.bodz.swt.c.canvas.ViewportCanvas;

public class ControlAdapters {

    // ...?
    public static void loseFocus(final Control control, final LoseFocusListener loseFocusListener) {
        control.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                boolean doit = loseFocusListener.loseFocus(e);
                if (!doit) {
                    control.setFocus();
                }
            }
        });
    }

    public static void commit(final Control control, final CommitListener commitListener,
            final CommitFailListener commitFailListener) {
        control.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent event) {
                try {
                    commitListener.commit(event);
                } catch (CommitException e) {
                    // focus problem..
                    if (commitFailListener != null)
                        commitFailListener.commitFail(event, e);
                    control.setFocus();
                }
            }
        });
    }

    public static void commit(Control control, CommitAdapter commitAdapter) {
        commit(control, commitAdapter, commitAdapter);
    }

    public static class FontAutoSize
            extends ControlAdapter {
        @Override
        public void controlResized(final ControlEvent e) {
            Control control = (Control) e.widget;
            int height = control.getBounds().height;
            FontData fontData = control.getFont().getFontData()[0];
            fontData.setHeight(height / 2);
            Font font = new Font(control.getDisplay(), fontData);
            control.setFont(font);
        }
    }

    public static final FontAutoSize fontAutoSize = new FontAutoSize();

    static final Pred1<MouseEvent> pass;
    static {
        pass = new Pred1<MouseEvent>() {
            @Override
            public boolean test(MouseEvent a) {
                return true;
            }
        };
    }

    public static Pred1<MouseEvent> getMaskEnabler(final int mask) {
        return new Pred1<MouseEvent>() {
            @Override
            public boolean test(MouseEvent e) {
                return mask == (e.stateMask & mask);
            }
        };
    }

    private static void fixMouseDownStateMask(MouseEvent e) {
        switch (e.button) {
        case 1:
            e.stateMask |= SWT.BUTTON1;
            break;
        case 2:
            e.stateMask |= SWT.BUTTON2;
            break;
        case 3:
            e.stateMask |= SWT.BUTTON3;
            break;
        case 4:
            e.stateMask |= SWT.BUTTON4;
            break;
        case 5:
            e.stateMask |= SWT.BUTTON5;
            break;
        }
    }

    public static void makeMoveable(Control control) {
        makeMoveable(control, pass);
    }

    public static void makeMoveable(Control control, int mask) {
        makeMoveable(control, getMaskEnabler(mask));
    }

    public static void makeMoveable(final Control control, final Pred1<MouseEvent> enabler) {
        class Moving
                extends MouseAdapter
                implements MouseMoveListener {
            int x0;
            int y0;
            boolean moving;

            @Override
            public void mouseDown(MouseEvent e) {
                fixMouseDownStateMask(e);
                moving = enabler.test(e);
                x0 = e.x;
                y0 = e.y;
            }

            @Override
            public void mouseUp(MouseEvent e) {
                moving = false;
            }

            @Override
            public void mouseMove(MouseEvent e) {
                if (moving && enabler.test(e)) {
                    int dx = e.x - x0;
                    int dy = e.y - y0;
                    Point loc = control.getLocation();
                    loc.x += dx;
                    loc.y += dy;
                    control.setLocation(loc);
                }
            }
        }
        Moving moving = new Moving();
        control.addMouseListener(moving);
        control.addMouseMoveListener(moving);
    }

    public static void makePannable(ViewportCanvas view) {
        makePannable(view, pass);
    }

    public static void makeMoveable(ViewportCanvas view, int mask) {
        makePannable(view, getMaskEnabler(mask));
    }

    public static void makePannable(final ViewportCanvas view, final Pred1<MouseEvent> enabler) {
        class Panning
                extends MouseAdapter
                implements MouseMoveListener {
            int x0;
            int y0;
            boolean panning;

            @Override
            public void mouseDown(MouseEvent e) {
                fixMouseDownStateMask(e);
                panning = enabler.test(e);
                x0 = e.x;
                y0 = e.y;
            }

            @Override
            public void mouseUp(MouseEvent e) {
                panning = false;
            }

            @Override
            public void mouseMove(MouseEvent e) {
                if (panning && enabler.test(e)) {
                    int dx = e.x - x0;
                    int dy = e.y - y0;
                }
            }
        }
        Panning panning = new Panning();
        view.addMouseListener(panning);
        view.addMouseMoveListener(panning);
    }

    public static void makeWheelPannable(ViewportCanvas view, int hMask, int vMask) {
        makeWheelPannable(view, getMaskEnabler(hMask), getMaskEnabler(vMask));
    }

    public static void makeWheelPannable(final ViewportCanvas view, final Pred1<MouseEvent> hEnabler,
            final Pred1<MouseEvent> vEnabler) {
        class Panning
                implements MouseWheelListener {
            @Override
            public void mouseScrolled(MouseEvent e) {
                if (hEnabler.test(e)) {
                    // h
                }
                if (vEnabler.test(e)) {
                    // v
                }
            }
        }
        Panning panning = new Panning();
        view.addMouseWheelListener(panning);
    }

    static class OpenBrowserAdapter
            extends SelectionAdapter {
        final Object address;

        public OpenBrowserAdapter() {
            this.address = null;
        }

        public OpenBrowserAdapter(Object address) {
            this.address = address;
        }

        @Override
        public void widgetSelected(SelectionEvent e) {
            try {
                String s = address != null ? address.toString() : e.text;
                URI uri = new URI(s);
                Desktop.getDesktop().browse(uri);
            } catch (Exception ex) {
                Shell shell = ((Control) e.widget).getShell();
                new DialogUI(shell).alert(ex.getMessage(), ex);
            }
        }
    }

    public static void openBrowser(Link link) {
        link.addSelectionListener(new OpenBrowserAdapter());
    }

    public static void openBrowser(Button button, Object address) {
        button.addSelectionListener(new OpenBrowserAdapter(address));
    }

    public static void openBrowser(MenuItem menuItem, Object address) {
        menuItem.addSelectionListener(new OpenBrowserAdapter(address));
    }

    public static void cascadeDispose(final Composite composite) {
        class CascadeDispose
                implements DisposeListener {
            @Override
            public void widgetDisposed(DisposeEvent e) {
                Control[] children = composite.getChildren();
                for (Control c : children)
                    if (!c.isDisposed())
                        c.dispose();
            }
        }
        composite.addDisposeListener(new CascadeDispose());
    }

}
