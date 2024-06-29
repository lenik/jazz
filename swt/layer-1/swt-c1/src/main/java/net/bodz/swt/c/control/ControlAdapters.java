package net.bodz.swt.c.control;

import java.util.function.Predicate;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.swt.c.canvas.ViewportCanvas;

public class ControlAdapters {

    /**
     * Auto re-grab the focus when the focus is lost.
     *
     * @param loseFocusListener
     *            Observer when it is try to lose focus.
     */
    public static void regrabFocus(final Control control, final LoseFocusListener loseFocusListener) {
        control.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                boolean doit = loseFocusListener.loseFocus(e);
                if (! doit) {
                    control.setFocus();
                }
            }
        });
    }

    static final Predicate<MouseEvent> pass = (e) -> true;

    public static Predicate<MouseEvent> getMaskEnabler(final int mask) {
        return (e) -> mask == (e.stateMask & mask);
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

    public static void makeMoveable(final Control control, final Predicate<MouseEvent> enabler) {
        class Moving
                extends MouseAdapter
                implements
                    MouseMoveListener {
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

    public static void makePannable(final ViewportCanvas view, final Predicate<MouseEvent> enabler) {
        class Panning
                extends MouseAdapter
                implements
                    MouseMoveListener {
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

    public static void makeWheelPannable(final ViewportCanvas view, final Predicate<MouseEvent> hEnabler,
            final Predicate<MouseEvent> vEnabler) {
        class Panning
                implements
                    MouseWheelListener {
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

    public static void cascadeDispose(final Composite composite) {
        class CascadeDispose
                implements
                    DisposeListener {
            @Override
            public void widgetDisposed(DisposeEvent e) {
                Control[] children = composite.getChildren();
                for (Control c : children)
                    if (! c.isDisposed())
                        c.dispose();
            }
        }
        composite.addDisposeListener(new CascadeDispose());
    }

}
