package net.bodz.swt.c.control;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.bodz.bas.lang.fn.Pred1;
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
                if (!doit) {
                    control.setFocus();
                }
            }
        });
    }

    public static void onresizeChangeFontSize(final Control control) {
        Point origSize = control.getSize();
        FontData origFontData = control.getFont().getFontData()[0];
        int origFontHeight = origFontData.getHeight();

        // final float fontRatioX = (float) (origFontHeight) / origSize.x;
        final float origRows = origSize.y / (float) (origFontHeight);

        control.addControlListener(new ControlAdapter() {
            @Override
            public void controlResized(final ControlEvent e) {
                Control control = (Control) e.widget;
                // int width = control.getBounds().width;
                int height = control.getBounds().height;

                FontData fontData = control.getFont().getFontData()[0];
                float newFontHeight = Math.round(height / origRows);
                fontData.setHeight((int) newFontHeight);

                Font font = new Font(control.getDisplay(), fontData);
                control.setFont(font);
            }
        });
    }

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
