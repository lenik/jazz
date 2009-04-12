package net.bodz.swt.state;

import java.util.Map;

import net.bodz.bas.types.sg.State;
import net.bodz.bas.types.sg.StateGraphImpl;
import net.bodz.swt.ex.HistoryMouseListener;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;

public class SWTStateGraphImpl extends StateGraphImpl implements SWTStateGraph,
        MouseListener, MouseMoveListener, KeyListener {

    private static final long serialVersionUID = 2084698255485360902L;

    public SWTStateGraphImpl() {
        this(new SWTDummyState(), null);
    }

    public SWTStateGraphImpl(SWTState start) {
        this(start, null);
    }

    public SWTStateGraphImpl(SWTContext context) {
        this(new SWTDummyState(), context);
    }

    public SWTStateGraphImpl(SWTState start, SWTContext context) {
        super(start, context);
        assert start != null;
    }

    public SWTStateGraphImpl(Map<Object, State> registry, Object startKey) {
        super(registry, startKey);
    }

    public SWTStateGraphImpl(Map<Object, State> registry, Object startKey,
            SWTContext context) {
        super(registry, startKey, context);
    }

    // -o StateGraph

    @Override
    public SWTContext context() {
        return context();
    }

    @Override
    public SWTState current() {
        return (SWTState) super.current();
    }

    @Override
    public SWTState get(Object key) {
        return (SWTState) super.get(key);
    }

    @Override
    public SWTState pop() {
        return (SWTState) super.pop();
    }

    @Override
    public SWTState recv(Object message) {
        return (SWTState) super.recv(message);
    }

    // -o MouseListener

    private HistoryMouseListener hm = new HistoryMouseListener() {

                                        @Override
                                        protected void mouseDown2(MouseEvent e) {
                                            SWTStateGraphImpl.this
                                                    .mouseDown2(e);
                                        }

                                        @Override
                                        protected void mouseUp2(MouseEvent e,
                                                MouseEvent d) {
                                            SWTStateGraphImpl.this.mouseUp2(e,
                                                    d);
                                        }

                                        @Override
                                        public void mouseDoubleClick(
                                                MouseEvent e) {
                                            SWTStateGraphImpl.this
                                                    .mouseDoubleClick2(e);
                                        }

                                    };

    public final void mouseDown(MouseEvent e) {
        hm.mouseDown(e);
    }

    public final void mouseUp(MouseEvent e) {
        hm.mouseUp(e);
    }

    public final void mouseDoubleClick(MouseEvent e) {
        hm.mouseDoubleClick(e);
    }

    protected void mouseDown2(MouseEvent e) {
        SWTState state = (SWTState) current;
        SWTState target = state.onMouseDown(e);
        if (state != target)
            jump(target);
    }

    protected void mouseUp2(MouseEvent e, MouseEvent d) {
        SWTState state = (SWTState) current;
        SWTState target = state.onMouseUp(e, d);
        if (state != target)
            jump(target);
    }

    protected void mouseDoubleClick2(MouseEvent e) {
        SWTState state = (SWTState) current;
        SWTState target = state.onMouseDoubleClick(e);
        if (state != target)
            jump(target);
    }

    // -o MouseMoveListener

    public void mouseMove(MouseEvent e) {
        // d may be null.
        MouseEvent d = hm.getPrevious(e.button);

        SWTState state = (SWTState) current;
        SWTState target = state.onMouseMove(e, d);
        if (state != target)
            jump(target);
    }

    // -o KeyListener

    public void keyPressed(KeyEvent e) {
        SWTState state = (SWTState) current;
        SWTState target = state.onKeyPressed(e);
        if (state != target)
            jump(target);
    }

    public void keyReleased(KeyEvent e) {
        SWTState state = (SWTState) current;
        SWTState target = state.onKeyReleased(e);
        if (state != target)
            jump(target);
    }

}
