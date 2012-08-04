package net.bodz.swt.c3.button;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;

public class RolloverButton
        extends Label {

    private static final int NORMAL = 1;
    private static final int HOVER = 2;
    private static final int ACTIVE = 3;
    private static final int DOWN = 3;

    private int state;
    private boolean active;

    private RolloverStyle normalStyle;
    private RolloverStyle hoverStyle;
    private RolloverStyle activeStyle;

    // RolloverStyle activeHover;

    class MouseAdapter
            implements MouseListener, MouseTrackListener, MouseMoveListener {

        private boolean down;

        boolean inside(MouseEvent e) {
            Point size = getSize();
            return 0 <= e.x && e.x < size.x && //
                    0 <= e.y && e.y < size.y;
        }

        @Override
        public void mouseDown(MouseEvent e) {
            // debug("mouseDown: ", e);
            if (e.button == 1) {
                setState(DOWN);
                down = true;
            }
        }

        @Override
        public void mouseMove(MouseEvent e) {
            if (!down)
                return;
            if (inside(e))
                setState(DOWN);
            else
                setState(active ? ACTIVE : HOVER);
        }

        @Override
        public void mouseUp(MouseEvent e) {
            // debug("mouseUp: ", e);
            if (!down)
                return;
            down = false;
            if (inside(e)) {
                setState(active ? ACTIVE : HOVER);
                Event _e = new Event();
                _e.button = e.button;
                _e.x = e.x;
                _e.y = e.y;
                _e.stateMask = e.stateMask;
                _e.widget = e.widget;
                fireSelection(new SelectionEvent(_e));
            } else
                setState(active ? ACTIVE : NORMAL);
        }

        @Override
        public void mouseDoubleClick(MouseEvent e) {
        }

        @Override
        public void mouseEnter(MouseEvent e) {
            // debug("mouseEnter: ", e);
            setState(HOVER);
        }

        @Override
        public void mouseExit(MouseEvent e) {
            // debug("mouseExit: ", e);
            setState(active ? ACTIVE : NORMAL);
        }

        @Override
        public void mouseHover(MouseEvent e) {
        }

    }

    public RolloverButton(Composite parent, int style) {
        super(parent, style);
        normalStyle = new RolloverStyle(this);
        MouseAdapter adapter = new MouseAdapter();
        addMouseListener(adapter);
        addMouseMoveListener(adapter);
        addMouseTrackListener(adapter);
    }

    public RolloverStyle getNormalStyle() {
        return normalStyle;
    }

    public void setNormalStyle(RolloverStyle normalStyle) {
        this.normalStyle = normalStyle;
    }

    public RolloverStyle getHoverStyle() {
        return hoverStyle;
    }

    public void setHoverStyle(RolloverStyle hoverStyle) {
        this.hoverStyle = hoverStyle;
    }

    public RolloverStyle getActiveStyle() {
        return activeStyle;
    }

    public void setActiveStyle(RolloverStyle activeStyle) {
        this.activeStyle = activeStyle;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        _setState(state);
    }

    @Override
    protected void checkSubclass() {
        // return getClass().getPackage().equals(SWT_PACKAGE);
    }

    void debug(Object... args) {
        for (Object arg : args)
            System.out.print(arg);
        System.out.println();
    }

    void setState(int state) {
        if (!isEnabled())
            return;
        if (this.state == state)
            return;
        this.state = state;
        _setState(state);
    }

    void _setState(int state) {
        switch (state) {
        case NORMAL:
            apply(normalStyle);
            break;
        case HOVER:
            apply(hoverStyle);
            break;
        case ACTIVE:
            apply(activeStyle);
            break;
        }
    }

    void apply(RolloverStyle style) {
        if (style == null)
            style = normalStyle;

        Color foreground = style.getForeground();
        if (foreground == null)
            foreground = normalStyle.getForeground();
        setForeground(foreground);

        Color background = style.getBackground();
        if (background == null)
            background = normalStyle.getBackground();
        setBackground(background);

        Image backgroundImage = style.getBackgroundImage();
        if (backgroundImage == null)
            backgroundImage = normalStyle.getBackgroundImage();
        setBackgroundImage(backgroundImage);

        Font font = style.getFont();
        if (font == null)
            font = normalStyle.getFont();
        setFont(font);
    }

    private List<SelectionListener> selectionListeners;

    public void addSelectionListener(SelectionListener l) {
        if (selectionListeners == null)
            selectionListeners = new ArrayList<SelectionListener>(1);
        selectionListeners.add(l);
    }

    public void removeSelectionListener(SelectionListener l) {
        if (selectionListeners != null)
            selectionListeners.remove(l);
    }

    protected void fireSelection(SelectionEvent e) {
        if (selectionListeners != null)
            for (SelectionListener l : selectionListeners)
                l.widgetSelected(e);
    }

}
